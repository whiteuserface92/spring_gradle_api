package com.dlsdlworld.spring.api.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Setter
public class FTPControl {


    private String encodingType;

    private FTPClient ftpClient;

    public FTPControl() {
        this.ftpClient = new FTPClient();
    }



    // FTP 연결 및 설정
    // ip : FTP IP, port : FTP port, id : FTP login Id, pw : FTP login pw, dir : FTP Upload Path
    public void connect(String ip, int port, String id, String pw, String dir) throws Exception{
        try {
            boolean result = false;

            ftpClient.setControlEncoding(encodingType);	//FTP 인코딩 설정
            ftpClient.connect(ip, port);//FTP 연결

            int reply = ftpClient.getReplyCode();	//응답코드 받기

            if (!FTPReply.isPositiveCompletion(reply)) {	//응답 False인 경우 연결 해제
                ftpClient.disconnect();
                throw new Exception("FTP서버 연결실패");
            }
            if(!ftpClient.login(id, pw)) {
                ftpClient.logout();
                throw new Exception("FTP서버 로그인실패");
            }

            ftpClient.setSoTimeout(1000 * 10);		//Timeout 설정
            ftpClient.login(id, pw);				//FTP 로그인
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);	//파일타입설정
            ftpClient.enterLocalPassiveMode();			//Active 모드 설정
            recursiveMakeDir(dir);
        /*    result = ftpClient.changeWorkingDirectory(dir);	//저장파일경로

            if(!result){	// result = False 는 저장파일경로가 존재하지 않음
                ftpClient.makeDirectory(dir);	//저장파일경로 생성
                ftpClient.changeWorkingDirectory(dir);
            }*/
        } catch (Exception e) {
            if(e.getMessage().indexOf("refused") != -1) {
                throw new Exception("FTP서버 연결실패");
            }
            throw e;
        }
    }

    public void recursiveMakeDir(String dir) throws IOException {
        // 디렉토리 만드는 부분
        boolean result = false;
        result = ftpClient.changeWorkingDirectory(dir);	//저장파일경로
        if(!result){
            String[] directory = dir.split("/");

            String newdir = "";
            for(int i=0, l=directory.length; i<l; i++) {
                newdir += ("/" + directory[i]);
                try {
                    result = ftpClient.changeWorkingDirectory(newdir);
                    if(!result) {
                        ftpClient.makeDirectory(newdir);
                        ftpClient.changeWorkingDirectory(newdir);
                    }
                } catch (IOException e) {
                    log.error(e.getLocalizedMessage());
                    throw e;
                }
            }
        }
    }

    // FTP 연결해제
    public void disconnect(){
        try {
            if(ftpClient.isConnected()){
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    // FTP 파일 업로드
    public void storeFile(String saveFileNm, InputStream inputStream) throws FTPConnectionClosedException {


        try {
            ftpClient.setBufferSize(0);
//            ftpClient.storeUniqueFile(saveFileNm, inputStream);
            ftpClient.storeFile(saveFileNm, inputStream);
        } catch(Exception e){
            throw new FTPConnectionClosedException();
        }





    }
    public Collection<String> listFiles(String path) throws IOException {
        FTPFile[] files = ftpClient.listFiles(path);

        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }
}

