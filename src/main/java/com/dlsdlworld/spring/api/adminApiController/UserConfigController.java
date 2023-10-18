package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserConfig;
import com.dlsdlworld.spring.api.repository.UserConfigRepository;
import com.dlsdlworld.spring.api.repository.UserRepository;
import com.dlsdlworld.spring.api.utils.SecurityUtils;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 *  수정: 사용자 Config에서 조회에 사용하는 id값에 대해서 로그인된 사용자 ID를 사용하도록 수정 처리함
 * 처음 로그인 시 Get호출 후 PUT도 호출이 됨.
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class UserConfigController {


    private UserConfigRepository userConfigRepository;
    private UserRepository userRepository;

    @Autowired
    public UserConfigController(UserConfigRepository userConfigRepository,
                                UserRepository userRepository) {
        this.userConfigRepository = userConfigRepository;
        this.userRepository = userRepository;
    }

    /*
    * etcFilter method
    * */
    private void etcSetting (Map<String, String> params, UserConfig userConfig) {
        if(StringUtils.isEmpty(params.get("etc1"))){
            userConfig.setEtc1("N");
        } else {
            userConfig.setEtc1(params.get("etc1"));
        }

        if(StringUtils.isEmpty(params.get("etc2"))){
            userConfig.setEtc2("N");
        } else {
            userConfig.setEtc2(params.get("etc2"));
        }

        if(StringUtils.isEmpty(params.get("etc3"))){
            userConfig.setEtc3("N");
        } else {
            userConfig.setEtc3(params.get("etc3"));
        }
    }

    @PutMapping(value = {"/userConfigs/{id}"})
    public ResponseEntity putUserConfig(@PathVariable(name = "id") Long id,
                                         @RequestBody Map<String, String> params) {

        Long loginUserId =   SecurityUtils.getCurrentUserId();

        log.trace("loginUserId : {}", loginUserId);


        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new EntityNotFoundException("No Usr found for the " + loginUserId));

        log.trace("params : {}", params);
        UserConfig userConfig = userConfigRepository.findByUserId(loginUserId);

        log.info( "userconfig : {}", userConfig);
        UserConfig savedUserConfig = null;
        if(userConfig == null) {
            UserConfig newUserConfig = new UserConfig();
            newUserConfig.setUser(user);
            newUserConfig.setDeptCd(params.get("deptCd"));
            newUserConfig.setLangCd(params.get("langCd"));
            savedUserConfig = userConfigRepository.save(newUserConfig);
        } else {
            userConfig.setDeptCd(params.get("deptCd"));
            userConfig.setLangCd(params.get("langCd"));
            savedUserConfig = userConfigRepository.save(userConfig);
        }
        URI uri = WebMvcLinkBuilder.linkTo(UserConfigController.class).slash(savedUserConfig.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * 사용자 정의 기본정보 조회 호출
     * 1. 처음 로그인 시 호출
     * 처음 로그인 시 Get호출 후 PUT도 호출이 됨.
     * @return
     * @throws Exception
     */
    @GetMapping(value={"/userConfigs/{id}"})
    public Map<String, Object> getUserConfigs(@PathVariable(name = "id") Long id) throws Exception {

        Long loginUserId =   SecurityUtils.getCurrentUserId();

        log.trace("loginUserId : {}", loginUserId);
        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new EntityNotFoundException("No Usr found for the " + loginUserId));

        UserConfig userConfig = userConfigRepository.findByUserId(loginUserId);

        log.trace( "userconfig : {}", userConfig);
        if(userConfig == null) {
            UserConfig newUserConfig = new UserConfig();
            newUserConfig.setUser(user);
            newUserConfig.setDeptCd("");
            newUserConfig.setLangCd("ko"); // kor
            newUserConfig.setPushOnOff("0"); // 방해금지 없음
            newUserConfig.setSessTimeoutMm("30"); //30분
            /* 조회 시 userConfig에 기존데이터가 없을 경우 etc옵션값을 모두 N으로 넣는다. */
            newUserConfig.setEtc1("N");
            newUserConfig.setEtc2("N");
            newUserConfig.setEtc3("N");
            userConfigRepository.save(newUserConfig);

            //userId에 해당되는 정보가 있으나, etc가 세팅되지 않은 경우가 있음.
            //etc1,2,3이 null 이나 빈값이라면 -> N으로 세팅하고, null이나 빈값이 아니라면, 그대로 진행.
        } else if(StringUtils.isEmpty(userConfig.getEtc1()) || StringUtils.isEmpty(userConfig.getEtc2()) || StringUtils.isEmpty(userConfig.getEtc3())){

            if (StringUtils.isEmpty(userConfig.getEtc1())){
                userConfig.setEtc1("N");
            }
            if (StringUtils.isEmpty(userConfig.getEtc2())){
                userConfig.setEtc2("N");
            }
            if (StringUtils.isEmpty(userConfig.getEtc3())){
                userConfig.setEtc3("N");
            }
            userConfigRepository.save(userConfig);

            //만약 PushOnOff 와 getSessTimeoutMm가 하나라도 비어있다면, 처음부터 다시 호출.
            if(StringUtils.isEmpty(userConfig.getPushOnOff()) || StringUtils.isEmpty(userConfig.getSessTimeoutMm())){
                getUserConfigs(id);
            }
        }
        //기존 else -> else if로 변경 2023-01-16
        //방해금지 여부 기존값 체크하여, 비어있으면 기본값 세팅하는 로직
        else if (StringUtils.isEmpty(userConfig.getPushOnOff()) || StringUtils.isEmpty(userConfig.getSessTimeoutMm())) { // 기존데이터중에서 pushOnOff 값과 SessTimeOutMM값이 없을경우에는 디폴트 값을 넣는다.
            boolean bSaveCheck= false;
            if(StringUtils.isEmpty(userConfig.getPushOnOff())){ // 방해금지 여부 기존값 체크
                userConfig.setPushOnOff("0"); // 방해금지 없음
                bSaveCheck= true;
            }
            if(StringUtils.isEmpty(userConfig.getSessTimeoutMm())){ // 방해금지 여부 기존값 체크
                userConfig.setSessTimeoutMm("30"); //30분
                bSaveCheck= true;
            }
            if(bSaveCheck) {
                log.info("===userConfig old value Default PushOnOff: 0,SessTimeoutMm: 30  get: userId:{}",id);
                userConfigRepository.save(userConfig);
            }
        }

        userConfig = userConfigRepository.findByUserId(loginUserId);
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("id", userConfig.getId());
        m.put("deptCd", userConfig.getDeptCd());
        m.put("langCd", userConfig.getLangCd());

        return ImmutableMap.of("content", m);
    }

    /**
     * 사용자 환경 설정 화면에서 사용할 내용으로 방해금지및 30qns로그아웃 시간 설정이 들어 간다.
     * 전체값 조회 위해 사용한다.
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value={"/userConfigs/uiPush/{id}"})
   // @ResponseBody
    public   Map<String, Object> getUiPushUserConfigs(@PathVariable(name = "id") Long id) throws Exception {

        Long loginUserId =   SecurityUtils.getCurrentUserId();

        log.trace("loginUserId : {}", loginUserId);
        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new EntityNotFoundException("No Usr found for the " + loginUserId));

        UserConfig userConfig = userConfigRepository.findByUserId(loginUserId);

        log.trace( "userconfig : {}", userConfig);
        if(userConfig == null) {
            UserConfig newUserConfig = new UserConfig();
            newUserConfig.setUser(user);
            newUserConfig.setDeptCd("");
            newUserConfig.setLangCd("ko"); // kor
            newUserConfig.setPushOnOff("0"); // 방해금지 없음
            newUserConfig.setSessTimeoutMm("30"); //30분
            userConfigRepository.save(newUserConfig);
        }

        userConfig = userConfigRepository.findByUserId(loginUserId);



        Map<String,Object> m = new HashMap<String,Object>();
        m.put("id", userConfig.getId());
        m.put("sessTimeoutMm", userConfig.getSessTimeoutMm());
        m.put("pushOnOff", userConfig.getPushOnOff());
        m.put("alarmOffStarttime", userConfig.getAlarmOffStarttime());
        m.put("alarmOffEndtime", userConfig.getAlarmOffEndtime());
        return ImmutableMap.of("content", m);
    }
    /**
     * 자동로그아웃 시간, 방해 금지 시간 입력
     * @param id
     * @param params
     * @return
     */
    @PutMapping(value = {"/userConfigs/uiPush/{id}"})
    public ResponseEntity putUiPushUserConfig(@PathVariable(name = "id") Long id,
                                        @RequestBody Map<String, String> params) {

        Long loginUserId =   SecurityUtils.getCurrentUserId();

        log.trace("loginUserId : {}", loginUserId);


        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new EntityNotFoundException("No Usr found for the " + loginUserId));

        log.trace("params ui : {}", params);
        UserConfig userConfig = userConfigRepository.findByUserId(loginUserId);

        log.info( "userconfig ui: {}", userConfig);
        UserConfig savedUserConfig = null;
        if(userConfig == null) {
            UserConfig newUserConfig = new UserConfig();
            newUserConfig.setUser(user);
            /*newUserConfig.setDeptCd(params.get("deptCd"));
            newUserConfig.setLangCd(params.get("langCd"));*/
            newUserConfig.setPushOnOff(params.get("pushOnOff")); // 방해금지 없음
            newUserConfig.setSessTimeoutMm(params.get("sessTimeoutMm")); //기본 30분
            newUserConfig.setAlarmOffStarttime(params.get("alarmOffStarttime")); //방해금지 시작 시간
            newUserConfig.setAlarmOffEndtime(params.get("alarmOffEndtime")); //방해금지 종료 시간
            savedUserConfig = userConfigRepository.save(newUserConfig);
        } else {
          /*  userConfig.setDeptCd(params.get("deptCd"));
            userConfig.setLangCd(params.get("langCd"));*/
            userConfig.setPushOnOff(params.get("pushOnOff")); // 방해금지 없음
            userConfig.setSessTimeoutMm(params.get("sessTimeoutMm")); //기본 30분
            userConfig.setAlarmOffStarttime(params.get("alarmOffStarttime")); //방해금지 시작 시간
            userConfig.setAlarmOffEndtime(params.get("alarmOffEndtime")); //방해금지 종료 시간
            savedUserConfig = userConfigRepository.save(userConfig);
        }
        URI uri = WebMvcLinkBuilder.linkTo(UserConfigController.class).slash(savedUserConfig.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
