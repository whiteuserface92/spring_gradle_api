package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.param.AppUploadHistoryParam;
import com.dlsdlworld.spring.api.repository.AppUploadHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AppUploadHistoryController {

    private final AppUploadHistoryRepository appUploadHistoryRepository;

    public AppUploadHistoryController(AppUploadHistoryRepository appUploadHistoryRepository) {
        this.appUploadHistoryRepository = appUploadHistoryRepository;
    }
    @Transactional
    @PreAuthorize("@security.hasPermission({'APP_WRITE'})")
    @PostMapping(value = {"/writeAppUploadHistory"})
        public String writeAppUploadHistory(@RequestBody AppUploadHistoryParam appUploadHistoryParam, HttpServletRequest request) throws Exception {
        String returnMsg = null;

        Object authManagerAuthInfo = null;

        JSONObject json = null;

        request.getSession(true).setAttribute("user",authManagerAuthInfo);

        log.info("authManagerAuthInfo : {}",authManagerAuthInfo);

        JSONObject jsonObject = new JSONObject();

        try{
            appUploadHistoryRepository.insertAppUploadHistory(appUploadHistoryParam);
            returnMsg = "0";
        } catch(Exception e){
            returnMsg = "-1";
            log.error("AppUploadHistoryController Exception !!!");
        }

        return returnMsg;
    }
    @Transactional
    @PreAuthorize("@security.hasPermission({'APP_WRITE'})")
    public String writeAppUploadHistory(AppUploadHistoryParam appUploadHistoryParam) throws Exception {
        String returnMsg = null;

        Object authManagerAuthInfo = null;

        JSONObject json = null;

        log.info("authManagerAuthInfo : {}",authManagerAuthInfo);

        JSONObject jsonObject = new JSONObject();

        try{
            appUploadHistoryRepository.insertAppUploadHistory(
                    appUploadHistoryParam
            );
            returnMsg = "0";
        } catch(Exception e){
            returnMsg = "-1";
            log.error("AppUploadHistoryController Exception !!!");
        }

        return returnMsg;
    }
    @Transactional
    @PreAuthorize("@security.hasPermission({'APP_READ'})")
    @GetMapping(value={"/getAppUploadHistory"})
    public List<JSONObject> getAppUploadHistory(HttpServletRequest request){

        List<JSONObject> result = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        JSONObject authManagerAuthInfo = new JSONObject();

        Object object =request.getUserPrincipal();

        log.info("authManagerAuthInfo : {}",authManagerAuthInfo.toString());

        try{
            result = appUploadHistoryRepository.getHistory();
        } catch(Exception e){
            log.error("AppUploadHistoryController Exception !!!");
        }

        return result;
    }

    @PreAuthorize("@security.hasPermission({'APP_READ'})")
    @GetMapping(value="/getLastAppFileCode")
    public JSONObject getLastAppFileCode(){
        JSONObject result = new JSONObject();

        try{
            result.put("apkFileCode",appUploadHistoryRepository.getLastAppFileCode("apk"));
            result.put("ipaFileCode",appUploadHistoryRepository.getLastAppFileCode("ipa"));
            result.put("plistFileCode",appUploadHistoryRepository.getLastAppFileCode("plist"));
        } catch(Exception e){
            log.error("getLastAppFileCode Fail!!!");
        }
        return result;
    }



}
