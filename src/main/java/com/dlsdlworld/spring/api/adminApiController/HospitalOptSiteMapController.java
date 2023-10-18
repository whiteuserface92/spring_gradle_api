package com.dlsdlworld.spring.api.adminApiController;


import com.dlsdlworld.spring.api.cachemodel.HospitalOptSiteMapCache;
import com.dlsdlworld.spring.api.cachemodel.HospitalOptionCodeCache;
import com.dlsdlworld.spring.api.cachemodel.UserConfigCache;
import com.dlsdlworld.spring.api.cacherepository.HospitalOptSiteMapCacheRepository;
import com.dlsdlworld.spring.api.cacherepository.HospitalOptionCodeCacheRepository;
import com.dlsdlworld.spring.api.cacherepository.UserConfigCacheRepository;
import com.dlsdlworld.spring.api.dto.CommonCodeDto;
import com.dlsdlworld.spring.api.dto.HospitalOptSiteMapDto;
import com.dlsdlworld.spring.api.dto.HospitalOptionCodeDto;
import com.dlsdlworld.spring.api.model.HospitalOptSiteMap;
import com.dlsdlworld.spring.api.param.HospitalOptSiteMapParam;
import com.dlsdlworld.spring.api.repository.CommonCodeRepository;
import com.dlsdlworld.spring.api.repository.HospitalOptSiteMapRepository;
import com.dlsdlworld.spring.api.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 병원별 옵션 Site Map 코드 테이블
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class HospitalOptSiteMapController {


    private CommonCodeRepository commonCodeRepository;
    private HospitalOptSiteMapRepository hospitalOptSiteMapRepository;
    private HospitalOptSiteMapCacheRepository hospitalOptSiteMapCacheRepository;
    private HospitalOptionCodeCacheRepository hospitalOptionCodeCacheRepository;
    private UserConfigCacheRepository userConfigCacheRepository;

    @Autowired
    public HospitalOptSiteMapController(HospitalOptSiteMapRepository hospitalOptSiteMapRepository
            ,  HospitalOptSiteMapCacheRepository hospitalOptSiteMapCacheRepository
            , CommonCodeRepository commonCodeRepository
            , HospitalOptionCodeCacheRepository hospitalOptionCodeCacheRepository
            , UserConfigCacheRepository userConfigCacheRepository) {

        this.hospitalOptSiteMapRepository       = hospitalOptSiteMapRepository;
         this.hospitalOptSiteMapCacheRepository = hospitalOptSiteMapCacheRepository;
         this.commonCodeRepository              = commonCodeRepository;
         this.hospitalOptionCodeCacheRepository = hospitalOptionCodeCacheRepository;
         this.userConfigCacheRepository         = userConfigCacheRepository;
    }


    @Transactional
    @GetMapping(value = "/hospitalOptSiteMap/findAllByHospitalOptSiteMapList")
    public Page<HospitalOptSiteMapDto> findAllByHospitalOptionList(Pageable page  // 옵션 코드를 100개로 정한다.
            , @RequestParam(required = false, defaultValue = "") Long hospitalId
            , @RequestParam(required = false, defaultValue = "") String keyword) {

        log.trace("###hospitalId : {}", hospitalId);
       // log.trace("###page : {}", page);

        if (null == hospitalId) {
            log.info("### init Page : {}");
            return new PageImpl(new ArrayList<HospitalOptionCodeDto>());
        }
        // SITE_CD_MAP 사이트코드 매핑에서 서브쿼리로 돌릴 상세 코드를 조회 하기 위해 공통코드 목록 조회
      /*  List<String> refVals = (List<String>) commonCodeRepository.findByCodeClsRefValSiteCdMap("SITE_CD_MAP").stream().map(
                commonCodeDto -> commonCodeDto.getCode()
        );*/
         List<CommonCodeDto>  lst= commonCodeRepository.findByCodeClsRefValSiteCdMap("SITE_CD_MAP");
        List<String> refVals = new ArrayList<String>();
        for(CommonCodeDto dto : lst)
        {  refVals.add(dto.getCode());  }

        Page<HospitalOptSiteMapDto> ret = hospitalOptSiteMapRepository.findAllByHospitalList(page, hospitalId, keyword, refVals);
       log.info("###ret : {}", ret.getContent());
        return ret;
    }

    /**
     * 병원별 옵션 정보 저장
     *
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = {"/hospitalOptSiteMap/saveOptSiteMap"})
    public ResponseEntity<?> saveOptCode(@RequestBody @Valid HospitalOptSiteMapParam param) {
        log.info("###param : {}", param);
        HospitalOptSiteMap optSiteMap = new HospitalOptSiteMap();


        // hospitalId를 테이블 매핑 하지 않아서 그대로 넘겨도 된다. id값이 있던지 없던지 두가지 다 던지면 된다.
            org.springframework.beans.BeanUtils.copyProperties(param, optSiteMap);
        //Hospital 값 매핑후  넣기

        hospitalOptSiteMapRepository.save(optSiteMap);
        return ResponseEntity.ok().build();
    }



    /**
     * HospitalCd로 한 병원 전체 옵션 값 조회
     * 클라이언트에서 로그인 후 한 병원에서 사용하는 병원옵션 코드 내려서 로컬 스토리지에 넣어서 사용
     * @param hosCd
     * @return
     */
    @Transactional
    @GetMapping(value = "/hospitalOptSiteMap/findOptCdAndSiteMapByHoCd")
    public Map<String,Object> findOptSiteMapCacheByHoCd(
            @RequestParam(name = "hosCd") String hosCd
           // @RequestBody @Valid PostHospitalOpcdParam param
    ) {

        Long loginUserId =   SecurityUtils.getCurrentUserId();

    //    List<HospitalOptSiteMapCache> lstHoSiteMap = this.hospitalOptSiteMapCacheRepository.findByHospitalCd(param.getHospitalCd());
        List<HospitalOptSiteMapCache> lstHoSiteMap = this.hospitalOptSiteMapCacheRepository.findByHospitalCd(hosCd);
        String strHoSiteMap = getHospSiteMapString(lstHoSiteMap);
        List<HospitalOptionCodeCache> lstHoCdOptCd = this.hospitalOptionCodeCacheRepository.findByHospitalCd(hosCd);
        String strHoOptCd = getHoOpCdString(lstHoCdOptCd);

        // 전체 적용 기본 환경 설정값 리턴

        String baseHosCd= "lemonhc";
        List<HospitalOptSiteMapCache>  lstBaseHoSiteMap = this.hospitalOptSiteMapCacheRepository.findByHospitalCd(baseHosCd);
        String strBaseHoSiteMap = getHospSiteMapString(lstBaseHoSiteMap);
        List<HospitalOptionCodeCache> lstBaseHoCdOptCd = this.hospitalOptionCodeCacheRepository.findByHospitalCd(baseHosCd);
        String strBaseHosCdHoOptCd = getHoOpCdString(lstBaseHoCdOptCd);
        
        //
        UserConfigCache userConfig = new UserConfigCache();
        Optional<UserConfigCache> userConfigOpt= userConfigCacheRepository.findByUserId(loginUserId);
        if(userConfigOpt.isPresent()){ userConfig = userConfigOpt.get();}

        Map<String,Object> m =new HashMap<>();
        m.put("baseOpCds",strBaseHosCdHoOptCd);
        m.put("baseSCMaps",strBaseHoSiteMap);

        m.put("hosOpCds",strHoOptCd);
        m.put("hosSCMaps",strHoSiteMap);
        m.put("userConfig", userConfig );
        return m;
    }

    /**
     * 화면 UI에서
     * REF_C0020이 10 코드의 사이트값은  A20이라 하면
     * REF_C0020_10:"A10",
     * REF_C0020_20:"A20"
     * 이런 식으로 내려가서 실제 코등서는 REF_C0020_20 ' 리턴된는 사이트 코드랑 비교 해서 처리한다. 실제는
     *  리턴되는 SITE값을 가지고 비교 하도록 한다.
     * @param lstHoSiteMap
     * @return
     */
    private String getHospSiteMapString(List<HospitalOptSiteMapCache> lstHoSiteMap) {

        StringBuffer sb = new StringBuffer();
        int nLength = lstHoSiteMap.size();
        sb.append("{");
        int i =0;
        for ( HospitalOptSiteMapCache cache : lstHoSiteMap){
            i++;
            sb.append("\"").append(cache.getRefCd())
                            .append("_")
                            .append(cache.getDefCd()).append("\": \"")
                           .append(cache.getSiteCd()).append("\"");
            if( i< nLength) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    private String getHoOpCdString(List<HospitalOptionCodeCache> lstHoCdOpCd) {

        StringBuffer sb = new StringBuffer();
        int nLength = lstHoCdOpCd.size();
        sb.append("{");
        int i =0;
        for ( HospitalOptionCodeCache cache : lstHoCdOpCd){
            i++;
            sb.append("\"").append(cache.getOptCd()).append("\": \"")
                    .append(cache.getOptVal()).append("\"");
            if( i< nLength) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
