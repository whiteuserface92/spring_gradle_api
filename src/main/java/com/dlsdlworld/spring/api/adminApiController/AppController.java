package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.AppService;
import com.dlsdlworld.spring.api.dto.AppMstDto;
import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.App;
import com.dlsdlworld.spring.api.model.AppPlatform;
import com.dlsdlworld.spring.api.param.PostAppParam;
import com.dlsdlworld.spring.api.repository.AppPlatformRepository;
import com.dlsdlworld.spring.api.repository.AppVersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@Validated
@RestController
public class AppController {

    private AppService appService;
    private AppPlatformRepository appPlatformRepository;
    private AppVersionRepository appVersionRepository;

    @Autowired
    public AppController(AppService appService, AppPlatformRepository appPlatformRepository,AppVersionRepository appVersionRepository) {
        this.appService = appService;
        this.appPlatformRepository = appPlatformRepository;
        this.appVersionRepository = appVersionRepository;
    }

    /**
     * 앱 버전 목록
     * @param page
     * @param hospitalId
     * @param keyword
     * @return
     */
    @Transactional
    @GetMapping(value = "/rest/apps/findAllByKeyword")
    public Page<AppMstDto> findAllByKeyword(Pageable page
            , @RequestParam(required=false, defaultValue = "") Long hospitalId
            , @RequestParam(required=false, defaultValue = "") String keyword) {

        log.trace("###hospitalId : {}", hospitalId);
        log.trace("###keyword : {}", keyword);

        return appService.findAllByKeyword(hospitalId, keyword ,page);
    }

    @Transactional
    @GetMapping(value = "/rest/apps")
    public AppMstDto findAllByKeyword(@RequestBody PostAppParam param) {

        log.trace("###appId : {}", param.getAppId());
        log.trace("###appPlatformId : {}", param.getAppPlatformId());
        // log.trace("###appVersionId : {}", param.getAppVersionId());

        return appService.findById(param);

    }

    /**
     * 1. 유입 파라미터 확인 id기준
     *   - app id
     *   - platform id
     *   - version id
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = "/rest/apps")
    public ResponseEntity postApp(@RequestBody @Valid PostAppParam param) {
        log.trace( "postAppParam : {} ", param.toString());

        //app id가 없으면 모두 save
        if(StringUtils.isEmpty(param.getAppId())) {
            final App savedApp = appService.saveApp(param); //앱 마스터 save
            final AppPlatform savedAppPlatform = appService.saveAppPlatform(param, savedApp); //앱 플랫폼 save
        } else { //수정
            final App savedApp = appService.updateApp(param);
            AppPlatform savedAppPlatform = null;

            savedAppPlatform = appService.updateAppPlatform(param, savedApp);

            param.setAppId(savedApp.getId());
            param.setAppPlatformId(savedAppPlatform.getId());
        }

        URI uri = WebMvcLinkBuilder.linkTo(AppController.class).slash(param).toUri();
        return ResponseEntity.ok(uri);
    }

    @Transactional
    @PostMapping(value = "/rest/appVersions")
    public ResponseEntity insertAppVersion(@RequestBody @Valid PostAppParam param) {

        appService.insertAppVersionNative(param);

        URI uri = WebMvcLinkBuilder.linkTo(AppController.class).slash(param).toUri();

        log.debug("PostAppParam : {}", param);

        return ResponseEntity.ok(uri);
    }

    @Transactional
    @PutMapping(value = "/rest/appVersions/{appVersionId}")
    public ResponseEntity updateAppVersion(@RequestBody @Valid PostAppParam param, @PathVariable Long appVersionId) {

        appService.updateAppVersionNative(param, appVersionId);

        log.trace("AppNm : {}",param.getAppNm());
        log.trace("AppVer : {}",param.getAppVer());
        log.trace("AppVer : {}",param.getAppId());
        log.trace("AppVer : {}",param.getAppVersionId());
        log.trace("AppVer : {}",param.getAppPlatformId());
        log.trace("AppVer : {}",param.getAppState());
        log.trace("AppVer : {}",param.getDeployType());
        log.trace("AppVer : {}",param.getFcmKey());
        log.trace("AppVer : {}",param.getFidoApiKey());
        log.trace("AppVer : {}",param.getHashKey());
        log.trace("AppVer : {}",param.getIosProcessed());
        log.trace("AppVer : {}",param.getMultiEnabled());
        log.trace("AppVer : {}",param.getPkgNm());
        log.trace("AppVer : {}",param.getPlatformType());
        log.trace("AppVer : {}",param.getProdCd());
        log.trace("AppVer : {}",param.getReleasedOn());



        URI uri = WebMvcLinkBuilder.linkTo(AppController.class).slash(param).toUri();
        return ResponseEntity.ok(uri);
    }

    @Transactional
    @DeleteMapping(value = "/rest/appPlatforms/forceDelete/{appPlatformId}")
    public ResponseEntity deleteAppVersion(@PathVariable Long appPlatformId) {

        AppPlatform appPlatform = this.appPlatformRepository.findById(appPlatformId).orElseThrow(()-> new EntityNotFoundException("appPlatform", appPlatformId));
        this.appVersionRepository.deleteAll(appPlatform.getAppVersions());
        this.appPlatformRepository.deleteById(appPlatformId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 날짜 포맷
     * @param ymd
     * @param value
     * @return
     */
//    private LocalDateTime getDateFormat(String ymd, int value)
//    {
//        LocalDateTime dateformat;
//        if(StringUtils.isEmpty(ymd)) {
//            dateformat = LocalDateTime.now();
//        } else {
//            ymd += " 00:00";
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            dateformat = LocalDateTime.parse(ymd, formatter);
//        }
//        return value >= 0 ?  dateformat.plusDays(value) : dateformat.minusDays(value);
//    }
}
