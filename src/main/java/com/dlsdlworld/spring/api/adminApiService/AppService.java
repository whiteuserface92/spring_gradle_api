package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.AppMstDto;
import com.dlsdlworld.spring.api.model.App;
import com.dlsdlworld.spring.api.model.AppPlatform;
import com.dlsdlworld.spring.api.model.AppVersion;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.param.PostAppParam;
import com.dlsdlworld.spring.api.repository.AppPlatformRepository;
import com.dlsdlworld.spring.api.repository.AppRepository;
import com.dlsdlworld.spring.api.repository.AppVersionRepository;
import com.dlsdlworld.spring.api.repository.HospitalRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Service
@Qualifier("appService")
public class AppService {

    @Resource
    private EntityManager em;

    @Resource
    private ObjectMapper mapper;

    HospitalRepository hospitalRepository;

    AppRepository<App> appRepository;

    AppPlatformRepository appPlatformRepository;

    AppVersionRepository appVersionRepository;

    @Autowired
    public AppService(AppRepository appRepository,
                      AppPlatformRepository appPlatformRepository,
                      AppVersionRepository appVersionRepository,
                      HospitalRepository hospitalRepository) {
        this.appRepository = appRepository;
        this.appPlatformRepository = appPlatformRepository;
        this.appVersionRepository = appVersionRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @PostConstruct
    public void init() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, false);
    }

    final private String  SELECT_COUNT_SQL = "select count(1) ";

    final private String SELECT_SQL = " select a.id as \"appId\" , " +
            " a.app_nm as \"appNm\" , " +
            " a.app_state as \"appState\"  , " +
            // " a.app_ver as \"appVer\"  , " +
            " COALESCE((select max(r.version_cd) from app_version r where r.app_platform_id = c.id),a.app_ver) as \"appVer\" , " +
            " a.multi_enabled as \"multiEnabled\"  , " +
            " a.hospital_id as \"hospitalId\"  , " +
            " a.fcm_key as \"fcmKey\" , " +
            " a.fido_api_key as \"fidoApiKey\" , " +
            " b.hospital_nm as \"hospitalNm\"  , " +
            " b.hospital_cd as \"hospitalCd\"  , " +
            " b.hospital_nm_cd as \"hospitalNmCd\"  , " +
            " a.prod_cd as \"prodCd\" , " +
            " c.id as \"appPlatformId\" , " +
            " c.platform_type as \"platformType\" , " +
            " c.deploy_type as \"deployType\" , " +
            " c.store_url as \"storeUrl\" , " +
            " c.hash_key as \"hashKey\" , " +
            " c.pkg_nm as \"pkgNm\" , " +
            " COALESCE(c.ios_processed, false) as \"iosProcessed\" , " +
            " a.created_by as \"createdBy\"  , " +
            " a.created_on as \"createdOn\"  , " +
            " a.updated_by as \"updatedBy\"  , " +
            " a.updated_on as \"updatedOn\" " ;

    final private String FORM_SQL = " from app_mst a " +
            " left outer join hospital_mst b on a.hospital_id = b.id " +
            " left outer join app_platform c on a.id = c.app_id ";
         //   " left outer join app_version d on c.id = d.app_platform_id ";

    // 날짜는 무조건 조건절에서 조회되도록 한다.
    final private String WHERE_SQL = " where 1=1 ";


    @Transactional
    public Page<AppMstDto> findAllByKeyword(Long hospitalId, String keyword, Pageable page) {
        String selectSql = SELECT_SQL;
        String selectCountSql = SELECT_COUNT_SQL;
        String fromSql = FORM_SQL;
        String whereSql = WHERE_SQL;
        String orderSql = "";
        String countSql = "";
        String querySql = "";

        if(!StringUtils.isEmpty(keyword)) {
            whereSql += " and a.app_nm like CONCAT('%',:appNm,'%') ";
        }
        if(!StringUtils.isEmpty(hospitalId)) {
            whereSql += " and a.hospital_id =:hospitalId ";
        }

        orderSql = " order by c.id desc , a.id desc ";

        countSql = selectCountSql + fromSql + whereSql;
        querySql = selectSql + fromSql + whereSql + orderSql;

        log.trace("### countSql {}", countSql);
        log.trace("### querySql {}", querySql);

        // 결과 리스트를 가져온다.
        Query query = em.createNativeQuery(querySql);
        // 전체 카운트를 구한다.
        Query countQuery = em.createNativeQuery(countSql);

        if(!StringUtils.isEmpty(keyword)) {
            query.setParameter("appNm", keyword);
            countQuery.setParameter("appNm", keyword);
        }

        if(!StringUtils.isEmpty(hospitalId)) {
            query.setParameter("hospitalId", hospitalId);
            countQuery.setParameter("hospitalId", hospitalId);
        }

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        // 전체 카운트를 가져온다.
        int totalCount = ((Number)countQuery.getSingleResult()).intValue();
        // 현재 리스트를 가져온다.
        List<AppMstDto> appLists = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(AppMstDto.class)).list();

        Page<AppMstDto> pages = null;
        if(appLists != null){
            pages = new PageImpl<>(appLists, page, totalCount);
        }

        return pages;
    }

    @Transactional
    public AppMstDto findById(PostAppParam param) {
        String selectSql = SELECT_SQL;
        String fromSql = FORM_SQL;
        String whereSql = WHERE_SQL;
        String querySql = "";

        if(!StringUtils.isEmpty(param.getAppId())) {
            whereSql += " and a.id = :appId ";
        }
        if(!StringUtils.isEmpty(param.getAppPlatformId())) {
            whereSql += " and c.id = :appPlatformId ";
        }
        //if(!StringUtils.isEmpty(param.getAppVersionId())) {
        //    whereSql += " and d.id = :appVersionId ";
        //}
        querySql = selectSql + fromSql + whereSql;

        log.trace("### querySql {}", querySql);

        Query query = em.createNativeQuery(querySql);

        if(!StringUtils.isEmpty(param.getAppId())) {
            query.setParameter("appId", param.getAppId());
        }
        if(!StringUtils.isEmpty(param.getAppPlatformId())) {
            query.setParameter("appPlatformId", param.getAppPlatformId());
        }
        //if(!StringUtils.isEmpty(param.getAppVersionId())) {
        //    query.setParameter("appVersionId", param.getAppVersionId());
        //}

        List<AppMstDto> appLists = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(AppMstDto.class)).list();

        return appLists.get(0);
    }

    /**
     * 앱 마스터 save
     * @param param
     * @return
     */
    public App saveApp(PostAppParam param) {
        //앱마스터 save
        final App app = new App();
        BeanUtils.copyProperties(param, app);
         //Hospital 값 매핑후  넣기
        Hospital hospital = new Hospital();
        hospital.setId(param.getHospitalId());
        app.setHospital(hospital);
 
        return appRepository.save(app);
    }

    /**
     * 앱 마스터 수정
     * @param param
     * @return
     */
    public App updateApp(PostAppParam param) {
        App app = appRepository.findById(param.getAppId()).orElseThrow(() -> new EntityNotFoundException("No app_mst found for the " + param.getAppId()));

        app.setAppNm(param.getAppNm());
        app.setAppState(param.getAppState());
        app.setAppVer(param.getAppVer());
        app.setFcmKey(param.getFcmKey());
        app.setFidoApiKey(param.getFidoApiKey());
        app.setMultiEnabled(param.getMultiEnabled());
        app.setProdCd(param.getProdCd());
        return appRepository.save(app);
    }

    /**
     * 앱 플랫폼 save
     * @param param
     * @param app
     * @return
     */
    public AppPlatform saveAppPlatform(PostAppParam param, App app) {
        final AppPlatform appPlatform = new AppPlatform();
        BeanUtils.copyProperties(param, appPlatform);
        appPlatform.setApp(app);
        return appPlatformRepository.save(appPlatform);
    }

    /**
     * 앱 플랫폼 수정
     * @param param
     * @param app
     * @return
     */
    public AppPlatform updateAppPlatform(PostAppParam param, App app) {

        AppPlatform appPlatform = appPlatformRepository.findById(param.getAppPlatformId()).orElseThrow(() -> new EntityNotFoundException("No app_platform found for the " + param.getAppPlatformId()));
        appPlatform.setApp(app);
        appPlatform.setId(param.getAppPlatformId());
        appPlatform.setPlatformType(param.getPlatformType());
        appPlatform.setPkgNm(param.getPkgNm());
        appPlatform.setDeployType(param.getDeployType());
        appPlatform.setHashKey(param.getHashKey());
        appPlatform.setStoreUrl(param.getStoreUrl());
        appPlatform.setIosProcessed(param.getIosProcessed());
        return appPlatformRepository.save(appPlatform);
    }

    /**
     * 앱버전
     * @param param
     * @param appPlatform
     * @return
     */
    public AppVersion saveAppVersion(PostAppParam param, AppPlatform appPlatform) {
        final AppVersion appVersion = new AppVersion();
        BeanUtils.copyProperties(param, appVersion);
        appVersion.setAppPlatform(appPlatform);
        return appVersionRepository.save(appVersion);
    }

    public AppVersion updateAppVersion(PostAppParam param, AppPlatform appPlatform) {
        AppVersion appVersion = appVersionRepository.findById(param.getAppVersionId()).orElseThrow(() -> new EntityNotFoundException("No app_version found for the " + param.getAppVersionId()));

        appVersion.setAppPlatform(appPlatform);
        appVersion.setVersionCd(param.getVersionCd());
        appVersion.setReleaseNote(param.getReleaseNote());
        appVersion.setReleasedOn(param.getReleasedOn());
        appVersion.setRequired(param.getRequired());

        return appVersionRepository.save(appVersion);
    }

    public int insertAppVersionNative(PostAppParam param)
    {
        String query = " insert into app_version (app_platform_id, version_cd, release_note, released_on, required, created_on, created_by) " +
                " values (?1,?2,?3,now(), ?4, now(), 1 ) ";
        //em.persist(appVersion);
        //em.flush();

        Query u = em.createNativeQuery(query);

        u.setParameter(1, param.getAppPlatformId());
        u.setParameter(2, param.getVersionCd());
        u.setParameter(3, param.getReleaseNote());
        u.setParameter(4, param.getRequired());

        // 성공하면 1, 실패하면 0이 리턴됨
        int result = u.executeUpdate();

        return  result;
    }

    public int updateAppVersionNative(PostAppParam param, Long appVersionId)
    {
        // param.getAppVersionId()

        String query = " update app_version " +
                " set    app_platform_id = ?1 ," +
                "        version_cd = ?2, " +
                "        release_note = ?3, " +
                "        released_on = now(), " +
                "        required = ?4, " +
                "        updated_on = now() " +
                " where  id = ?5 ";

        Query u = em.createNativeQuery(query);

        //u.setHint(QueryHints.BIND_PARAMETERS, HintValues.FALSE);//<--the hint
        u.setParameter(1, param.getAppPlatformId());
        u.setParameter(2, param.getVersionCd());
        u.setParameter(3, param.getReleaseNote());
        u.setParameter(4, param.getRequired());
        u.setParameter(5, appVersionId);

        // 성공하면 1, 실패하면 0이 리턴됨
        int result = u.executeUpdate();

        return result;
    }
}
