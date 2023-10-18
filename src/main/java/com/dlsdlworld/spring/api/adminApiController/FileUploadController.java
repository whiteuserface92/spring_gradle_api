package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.dto.FileUploadResponse;
import com.dlsdlworld.spring.api.param.AppUploadHistoryParam;
import com.dlsdlworld.spring.api.repository.AppFileCodeRepository;
import com.dlsdlworld.spring.api.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@Slf4j
public class FileUploadController {

//    @Autowired
//    AppFileCodeRepository appFileCodeRepository;
    @Autowired
    AppFileCodeRepository appFileCodeRepository;

    @Autowired
    AppUploadHistoryController appUploadHistoryController;

    String fileName = null;
    Long size = null;
    String filecode = null;

    String uiAdminContext = null;

    @PreAuthorize("@security.hasPermission({'APP_WRITE'})")
    @PostMapping("/uploadFile")
    public List<ResponseEntity<FileUploadResponse>> uploadFile(
            String userId, List<MultipartFile> files,String fileVersion,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {

        List<ResponseEntity<FileUploadResponse>> returnResponse = new LinkedList<>();

//        String url = httpServletRequest.getRequestURL().toString();
//        String uri = httpServletRequest.getRequestURI();

        String forwardHost = httpServletRequest.getHeader("Origin");

        String url = httpServletRequest.getRequestURL().toString();

//        int port = httpServletRequest.getServerPort();
        log.info("userId : {}",userId.toString());


        FileUploadResponse response = null;

        String uiAdminContext = httpServletRequest.getHeader("x-forwarded-prefix").replace("/api/admin","");

        String redirectUrl = forwardHost+uiAdminContext+"/mplus/uploadPage";

        if(redirectUrl.contains("/api/admin")){
            redirectUrl.replace("/api/admin","");
        }
        for (MultipartFile multipartFile : files){

            response = new FileUploadResponse();

//            try{
//                apiAddress = url.replace(uri,"");
//                String os = System.getProperty("os.name").toLowerCase();
//
//                if (os.contains("win")) {
//                    apiAddress = apiAddress+context+"/api/admin";
//                } else if (os.contains("mac")) {
//                    apiAddress = apiAddress+context+"/api/admin";
//                } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
//                    apiAddress = (apiAddress+context+"/api/admin").replace("http","https");
//                } else if (os.contains("linux")) {
//                    apiAddress = (apiAddress+context+"/api/admin").replace("http","https");
//                } else if (os.contains("sunos")) {
//                    apiAddress = (apiAddress+context+"/api/admin").replace("http","https");
//                }
//            } catch (Exception e){
//                log.error("hostname get fail : {}", e.getLocalizedMessage());
//            }

            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            size = multipartFile.getSize();

            filecode = FileUploadUtil.saveFile(fileName, fileVersion ,multipartFile);

//            FileUploadResponse response = new FileUploadResponse();
            response.setFileName(fileName);
            response.setSize(size);
            response.setDownloadUri(uiAdminContext+"/api/admin/downloadFile/" + filecode);

            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

            AppUploadHistoryParam appUploadHistoryParam = new AppUploadHistoryParam();

//            Date nowDate = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
//            //app Upload history 현재시간 넣기
//            appUploadHistoryParam.setInsertDate(simpleDateFormat.format(nowDate));
            //upload history file code setting
            appUploadHistoryParam.setFileCode(filecode);
            //upload history userId setting
            appUploadHistoryParam.setUserId(userId.toString());

            appUploadHistoryParam.setFileVersion(fileVersion);

            if(ext.equals("apk")){
                try{
//                appFileCodeRepository.updateFileCodeByAppKind("apk",filecode);
                    appFileCodeRepository.updateFileCodeByAppKind("apk", filecode);
                    appUploadHistoryParam.setAppKind("apk");
                    appUploadHistoryController.writeAppUploadHistory(appUploadHistoryParam);
                    log.info("appFileCodeRepository.updateFileCodeByAppKind-apk filecode update success");
                } catch (Exception e) {
                    log.error("filecode update fail {}",e);
                }
            } else if (ext.equals("ipa")){
                try{
//                appFileCodeRepository.updateFileCodeByAppKind("ipa",filecode);
                    appFileCodeRepository.updateFileCodeByAppKind("ipa", filecode);
                    appUploadHistoryParam.setAppKind("ipa");
                    appUploadHistoryController.writeAppUploadHistory(appUploadHistoryParam);
                    log.info("appFileCodeRepository.updateFileCodeByAppKind-ipa filecode update success");
                } catch (Exception e){
                    log.error("filecode update fail {}",e);
                }
            } else if (ext.equals("plist")) {
                try{
//         appFileCodeRepository.updateFileCodeByAppKind("plist", filecode);
                    appFileCodeRepository.updateFileCodeByAppKind("plist", filecode);
                    appUploadHistoryParam.setAppKind("plist");
                    //fileVersion 공백으로 채우기. not undefined
//                    appUploadHistoryParam.setFileVersion("");
                    appUploadHistoryController.writeAppUploadHistory(appUploadHistoryParam);
                    log.info("appFileCodeRepository.updateFileCodeByAppKind-plist filecode update success");
                } catch (Exception e) {
                    log.error("filecode update fail {}",e);
                }
            } else {
                returnResponse.add(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
            }
            returnResponse.add(new ResponseEntity<>(response, HttpStatus.OK));
        }

        if(returnResponse.get(0).getStatusCodeValue() == 200){
            log.info("File upload Success");
            httpServletResponse.sendRedirect(redirectUrl);
        } else {
            httpServletResponse.sendRedirect(redirectUrl);
        }

        return returnResponse;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize(DataSize.parse("500MB"));

        factory.setMaxRequestSize(DataSize.parse("500MB"));

        return factory.createMultipartConfig();

    }
}
