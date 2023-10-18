package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.SftpTransferService;
import com.dlsdlworld.spring.api.exception.ApiCallException;
import com.dlsdlworld.spring.api.param.CollectFileParam;
import com.dlsdlworld.spring.api.param.PostFileParam;
import com.dlsdlworld.spring.api.param.UserAccessHistoryParam;
import com.dlsdlworld.spring.api.repository.UserAccessHistoryRepository;
import com.dlsdlworld.spring.api.repository.UserRepository;
import com.dlsdlworld.spring.api.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/rest")
public class PlusImageFileController {

    @Value("${lemoncare.ftpconnect.info.ftpip:localhost}")
    private String ftpIp;

    @Value("${lemoncare.ftpconnect.info.port:21}")
    private int ftpPort;

    @Value("${lemoncare.ftpconnect.info.ftpid:test01}")
    private String ftpId;

    @Value("${lemoncare.ftpconnect.info.ftppassword:P@ssw0rd}")
    private String ftpPassword;

    @Value("${lemoncare.ftpconnect.info.savelocaldir:c:\\temp\\images}") // /home/lemoncare/lemoncare-mobile-plus-ui/9081/'c:\\temp\\images'
    protected String saveFtpLocalDir;

    @Value("${lemoncare.sftp.savelocaldir:/tmp}")
    protected String saveSftpLocalDir;

    @Value("${lemoncare.ftpcontrol.encoding.type:euc-kr}")
    private String encodingType;

    @Value("${lemoncare.sftp.enabled:false}")
    private Boolean sftpEnabled;

    @Value("${lemoncare.sftp.middle.foldername:prod}")
    private String middleFolderName;

    public String getSaveFtpLocalDir(){
        return this.saveFtpLocalDir;
    }

    public String getSaveSftpLocalDir(){
        return this.saveSftpLocalDir;
    }

    public boolean isContinue = false;

    int ftpIsSuccess;

    int count = 0;

    Long userId = null;

    boolean result = false;

//    List<String> errorsReason = new ArrayList<>();

    String insertUserAccessHistoryResult = "";

    //실패하든 성공하든 user_access_history 저장
    UserAccessHistoryParam userAccessHistoryParam = new UserAccessHistoryParam();

    @Autowired
    UserAccessHistoryRepository userAccessHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SftpTransferService sftpTransferService;

    FTPClient ftp = new FTPClient();

//    Boolean oldFileDeleteResult;

    File oldfile;

    Map<String, Object> resMap = new HashMap<>();

    @PostMapping(value = {"/uploadFiles"})
    public ResponseEntity<?> postUploadFile(@ModelAttribute PostFileParam fileParam){

        //만약 아무것도 들어오지 않았다면 default로 false로 세팅한다.
//        if(fileParam.getSftpEnabled() == null){
//            fileParam.setSftpEnabled(false);
//        }
//
//        oldFileDeleteResult = false;

        try {
            this.resMap.clear();
            this.isContinue = false; // 진행안함으로 초기화
            this.ftpIsSuccess = 2; // 실패로 초기화
            this.insertUserAccessHistoryResult = null; //초기화
            this.result = false; // result 실패로 초기화

            ArrayList<String> fileNames = new ArrayList<>();

            log.info("'/uploadFiles' postUploadFile 시작");

            if(fileParam.getFiles().get(0).getSize() != 0 && fileParam.getHosCd() != null && fileParam.getLoginAccount() != null && fileParam.getUnitNo() != null){
                this.isContinue = true;
                Assert.notNull("appFiles", fileParam.getFiles());
                List<MultipartFile> files = fileParam.getFiles();
                String fileNm="";
                String fullFilePathAndName ="";
                log.info("파라메터 : {}", fileParam);
//                String ftpUploadPath = File.separator+fileParam.getLoginAccount()+File.separator+fileParam.getUnitNo();
                String ftpUploadPath = File.separator+fileParam.getLoginAccount()+File.separator+fileParam.getUnitNo();
//                String sftpUploadPath = File.separator+"SFTP"+File.separator+fileParam.getLoginAccount()+File.separator+fileParam.getUnitNo();
                String ftpStoreDirectory = saveFtpLocalDir+ File.separator+fileParam.getLoginAccount()+File.separator+fileParam.getUnitNo();
                String sftpStoreDirectory = saveSftpLocalDir+File.separator+"ehrshare"+File.separator+middleFolderName+File.separator+fileParam.getUnitNo();
                String commonStoreDirectroy = "";
                if(sftpEnabled){
                    commonStoreDirectroy = sftpStoreDirectory;
                } else {
                    commonStoreDirectroy = ftpStoreDirectory;
                }

//                fileParam.getSftpEnabled();

                    for (MultipartFile file : files) {
                        //  byte[] bytes = file.getBytes();
                        fileNm = file.getOriginalFilename();
                        log.info("File Name:{} " , fileNm);
                        log.info("File Size:{} " , file.getSize());
                        log.info("File Content Type:{} "  ,file.getContentType());
                        //   log.info("File Content: {}"  , new String(bytes));
                        fullFilePathAndName = commonStoreDirectroy + File.separator + fileNm;


                        if(!Files.isDirectory(Paths.get(commonStoreDirectroy))){
                            log.info("파일 디렉토리를 생성합니다. : {}", commonStoreDirectroy);
                            Files.createDirectories(Paths.get(commonStoreDirectroy));
                        }
//                        // 지정한 경로에 파일이 존재하는 지 확인
//                        oldfile = new File(fullFilePathAndName);
//                        FileInputStream in = new FileInputStream(oldfile);
//                        in.close();
//
//                        if (oldfile.exists()) {
//                            Boolean fileWriteEnable = oldfile.canWrite();
//                            log.info("oldfile write enable : {}",fileWriteEnable);
//                            oldFileDeleteResult = oldfile.delete();
//                            log.info("oldfile delete success : {}",result);
//                        }
                        //  파일 생성 로직
                        Path filePath = Files.createFile(Paths.get(fullFilePathAndName));
                        file.transferTo(filePath);

                        //새로 생성된 파일 삭제를 위한 정의
                        File newFile = new File(fullFilePathAndName);
                        String fileName = newFile.getName();
                        //sftp / ftp 분기처리
                        if(sftpEnabled){
                            //sftp logic
                            //ftpIsSuccess = 0 이면 성공
                            //ftpIsSuccess = 2 이면 실패
                            //강북삼성병원 sftp 경로수정
//                            String remoteFilePath = File.separator+"ehrshare"+File.separator+"prod"+File.separator+fileParam.getUnitNo();
                            String remoteFilePath = File.separator+"ehrshare"+File.separator+middleFolderName+File.separator+fileParam.getUnitNo();
                            Boolean sftpResult;
                            sftpResult = this.sftpTransferService.uploadFile(newFile,remoteFilePath, saveSftpLocalDir,fileParam.getLoginAccount(), fileParam.getUnitNo(), middleFolderName);
                            if(sftpResult == true){
                                this.ftpIsSuccess = 0;
                            } else {
                                this.ftpIsSuccess = 2;
                            }
                        } else {
                            this.ftpIsSuccess = ftpFileUpload(fullFilePathAndName , ftpUploadPath, fileNm);
                        }
                        if(this.ftpIsSuccess == 0){
                            if(sftpEnabled){
                                log.info("SFTP 전송 성공");
                            } else {
                                log.info("FTP 전송 성공");
                            }
                        } else if(this.ftpIsSuccess == 2){
                            if(sftpEnabled){
                                log.info("SFTP 전송 실패");
                            } else {
                                log.info("FTP 전송 실패");
                            }
                        } else {
                                log.error("중복된 파일 전송으로 인한 실패");
                        }
                        if(newFile.exists()){
                            Boolean result = newFile.delete();
                            log.info("newFIle delete : {}",result);
                        }

                        log.info("ftp upload 종료");

                        fileNames.add(file.getOriginalFilename());
                    }
                }

            if(fileParam.getHosCd().isEmpty()){
                throw new IOException();
            }

            this.userId = userRepository.findByMyCi(fileParam.getLoginAccount());
            log.info("userId : {}",this.userId);

            if(this.ftpIsSuccess == 0 && this.isContinue){
                // 성공
                this.userAccessHistoryParam.setUserId(this.userId);
                this.userAccessHistoryParam.setHospitalCd(fileParam.getHosCd());
                this.userAccessHistoryParam.setCreatedBy(this.userId);
                this.userAccessHistoryParam.setAccessTarget(fileParam.getUnitNo());
                this.userAccessHistoryParam.setAccessDesc(fileParam.getLoginAccount() + " 가 " + fileParam.getUnitNo() + " 의 환부이미지를 전송하였습니다.");
                this.userAccessHistoryParam.setAccessType("환부이미지 전송");
            } else {
                // 실패
                this.userAccessHistoryParam.setUserId(this.userId);
                this.userAccessHistoryParam.setHospitalCd(fileParam.getHosCd());
                this.userAccessHistoryParam.setCreatedBy(this.userId);
                this.userAccessHistoryParam.setAccessTarget(fileParam.getUnitNo());
                this.userAccessHistoryParam.setAccessDesc(fileParam.getLoginAccount() + " 가 " + fileParam.getUnitNo() + " 의 환부이미지를 전송 실패하였습니다.");
                this.userAccessHistoryParam.setAccessType("환부이미지 전송실패");
            }
            //로그 insert
            this.insertUserAccessHistoryResult = userAccessHistoryRepository.insertUserAccessHistory(userAccessHistoryParam);

            if(this.insertUserAccessHistoryResult.equals("USERID")){
                this.result = false;
            }
            if(this.ftpIsSuccess == 0 && this.insertUserAccessHistoryResult.equals("성공") && this.isContinue){
                this.result = true;
            }
        } catch(Exception e){
            this.result = false;
            this.resMap.put("errors","파라미터 확인필요");
//            if(oldFileDeleteResult){
//                log.error("파라미터 확인필요");
//            } else {
//                log.error("파일이 중복됨.");
//            }

        } finally {
            if(this.result){
                Long tmpFileSize = fileParam.getFiles().get(0).getSize();
                log.info("tmpFile.getSize() : {}", tmpFileSize);
                if(tmpFileSize == 0){
                    log.info("tmpFile.getSize() : {}", tmpFileSize);
                    this.resMap.put("status", 400);
//                    if(!oldFileDeleteResult){
//                        this.resMap.put("errors","중복된 파일을 업로드하여, 에러발생");
//                    } else {
//                        this.resMap.put("errors", "Files 파라미터 확인필요");
//                    }
                    log.error("'/uploadFiles' Files 파라미터 확인필요");
                    return new ResponseEntity<>(resMap, HttpStatus.BAD_REQUEST);
                } else {
                    if(sftpEnabled){
                        this.resMap.put("status", 200);
                        this.resMap.put("result","SFTP 전송 및 로그저장 성공 |" + "bytes : "+tmpFileSize);
                        log.info("'/uploadFiles' PostAppFile 성공");
                        return new ResponseEntity<>(resMap, HttpStatus.OK);
                    } else {
                        this.resMap.put("status", 200);
                        this.resMap.put("result","FTP 전송 및 로그저장 성공 |" + "bytes : "+tmpFileSize);
                        log.info("'/uploadFiles' PostAppFile 성공");
                        return new ResponseEntity<>(resMap, HttpStatus.OK);
                    }

                }
            } else  {
                if(this.insertUserAccessHistoryResult == null){
                    this.insertUserAccessHistoryResult = "";
                }

                this.resMap.put("status",400);
                List<String> tmpObj = new LinkedList<>();

                if(ftpIsSuccess == 1){
                    tmpObj.add("FTP 전송파일이 이미 존재합니다.");
                }

                if(ftpIsSuccess == 2){
                    if(sftpEnabled){
                            tmpObj.add("SFTP 전송실패");
                    } else {
                            tmpObj.add("FTP 전송실패");
                    }

                }

                if(this.insertUserAccessHistoryResult.equals("USERID")){
                    tmpObj.add("해당 loginAccount의 user_id를 찾을 수 없습니다.");
                }

                if(fileParam.getFiles() == null || fileParam.getFiles().get(0).getSize() == 0){
                    tmpObj.add("files is empty");
                }

                if(fileParam.getLoginAccount() == null || fileParam.getLoginAccount() == "" || fileParam.getLoginAccount().isEmpty()){
                    tmpObj.add("loginAccount is empty");
                }
                if(fileParam.getHosCd() == null || fileParam.getHosCd() == "" || fileParam.getHosCd().isEmpty()){
                    tmpObj.add("hosCd  is empty");
                }
                if(fileParam.getUnitNo() == null || fileParam.getUnitNo() == "" || fileParam.getUnitNo().isEmpty()){
                    tmpObj.add("unitNo  is empty");
                }
                this.resMap.put("errors",tmpObj);

                log.info("'/uploadFiles' PostAppFile 실패");
                return new ResponseEntity<>(resMap, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public void recursiveMakeDir(String dir) throws IOException {
        // 디렉토리 만드는 부분
        boolean result = false;
        result = this.ftp.changeWorkingDirectory(dir);	//저장파일경로
        if(!result){
            String[] directory = dir.split("/");

            String newdir = "";
            for(int i=0, l=directory.length; i<l; i++) {
                newdir += ("/" + directory[i]);
                try {
                    result = this.ftp.changeWorkingDirectory(newdir);
                    if(!result) {
                        this.ftp.makeDirectory(newdir);
                        this.ftp.changeWorkingDirectory(newdir);
                    }
                } catch (IOException e) {
                    log.error(e.getLocalizedMessage());
                    throw e;
                }
            }
        }
    }

    //return 값 변경해야 됨.
    //정상적으로 upload됐을경우 0
    //이미 존재하는 파일명일 경우 1 - 이미 존재하는 파일명일 경우 덮어쓰기로 일단 해놈.
    //실패했을 경우 2
    @Transactional
    public int ftpFileUpload(String localFullPathAndName , String ftpUploadPath,  String  fileNm) {

        ftp.setControlEncoding(this.encodingType);

        this.ftpIsSuccess = 2;

        try{
            int reply = 0;

            FileInputStream fis = null;

            String FTP_IP =this.ftpIp;
            int FTP_PORT = this.ftpPort;
            String FTP_ID =this.ftpId;
            String FTP_PASSWORD = this.ftpPassword;
//            FTPControl ftp = new FTPControl();
//            ftp.setEncodingType(this.encodingType);
            this.ftp.connect(FTP_IP, FTP_PORT);
            this.ftp.login(FTP_ID, FTP_PASSWORD);

            reply = ftp.getReplyCode();

            log.info("FTPReply CODE : {}",reply);

            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftp.disconnect();
                this.ftpIsSuccess = 2;
                return ftpIsSuccess;
            }

            this.ftp.enterLocalPassiveMode();			//Active 모드 설정

            this.recursiveMakeDir(ftpUploadPath);

            this.ftp.changeWorkingDirectory(ftpUploadPath);

            this.ftp.setBufferSize(1024);

            this.ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            File file = new File(localFullPathAndName); // 로컬 파일 경로

            fis = new FileInputStream(file);

            String filename = fileNm;

            Boolean ftpStoreFileResult = this.ftp.storeFile(filename, fis);

            if(!ftpStoreFileResult){
                fis.close();

                this.ftp.logout();

                this.ftpIsSuccess = 2;

                return this.ftpIsSuccess;
            }

            fis.close();

            this.ftp.logout();

            log.info("ftp upload :from:{}, to:{}" ,localFullPathAndName,  ftpUploadPath+File.separator+fileNm);

            log.info("ftp session disconnect =================");

            this.ftp.disconnect();

            this.ftpIsSuccess = 0;

        } catch(Exception e){
            this.ftpIsSuccess = 2;
            log.error("ftpFileUpload failed");
        } finally {
            log.info("DisConnect Before replyCode : {}",ftp.getReplyCode());
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            log.info("ftpIsSuccess Before replyCode : {}",ftp.getReplyCode());
            return ftpIsSuccess;
        }



    }

    private String fileNameMaker(ArrayList<String> fileNames){

        String result;

        result = StringUtils.join(fileNames,"|");

        return result;
    }

    @Value("${lemoncare.collect.filenames.sendsite:https://mdoctor.jbuh.co.kr/qapiplus-dev/api/post_SendImageInfo/v4}")
    private String collectSendUrl;

    public boolean wasToQabService(CollectFileParam collectFileParam){
        log.info("QAB fileNames 정보 전송");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>();

        map.put("HosCd", collectFileParam.getHosCd());
        map.put("UserId", collectFileParam.getUserId());
        map.put("UnitNo", collectFileParam.getUnitNo());
        map.put("SndFileNm", collectFileParam.getFileNames());

        JSONObject body = new JSONObject(map);

        log.info("전송 URL : {}", collectSendUrl);

        HttpEntity<JSONObject> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> responseEntity;

        if(collectSendUrl == null){
            log.error("url is empty!!!");
        }

        try{
            responseEntity = restTemplate.postForEntity(collectSendUrl, request, String.class);
        } catch (Exception e){
            throw new ApiCallException("CollectFileNameCAll Error : {}", e.getCause());
        }

        String returnMsg = responseEntity.toString();

        log.info("returnMsg : {}",returnMsg);

        int statusCode = responseEntity.getStatusCodeValue();

        return statusCode == 200;
    }
}
