package com.dlsdlworld.spring.api.adminApiService;

import com.jcraft.jsch.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class SftpTransferService {

    @Value("${common.sftp.host:localhost}")
    private String host;

    @Value("${common.sftp.port:22}")
    private Integer port;

    @Value("${common.sftp.username:sysop}")
    private String username;

    @Value("${common.sftp.password:P@ssw0rd}")
    private String password;

    @Value("${common.sftp.inputstream.enable:false}")
    private Boolean inputStreamEnable;

    @Value("${common.sftp.sessionTimeout:15000}")
    private Integer sessionTimeout;

    @Value("${common.sftp.channelTimeout:15000}")
    private Integer channelTimeout;

    boolean isUpload = false;

    @SneakyThrows
    public boolean uploadFile(File file,String remoteFilePath,String saveSftpLocalDir, String LoginAccount, String unitNo, String middleFolderName) {
        ChannelSftp channelSftp = createChannelSftp();

        try {
            channelSftp.setFilenameEncoding("UTF-8");

            SftpATTRS attrs;

            try {
                attrs = channelSftp.stat("ehrshare");
                channelSftp.cd("ehrshare");
                log.info(channelSftp.pwd());
                log.info("1");
            }catch (Exception e) {
                channelSftp.mkdir("ehrshare");
                channelSftp.cd("ehrshare");
                log.info(channelSftp.pwd());
                log.info("2");
            }
            try {
                attrs = channelSftp.stat(middleFolderName);
                channelSftp.cd(middleFolderName);
                log.info(channelSftp.pwd());
                log.info("3");
            }catch (Exception e) {
                channelSftp.mkdir(middleFolderName);
                channelSftp.cd(middleFolderName);
                log.info(channelSftp.pwd());
                log.info("4");
            }
            try {
                attrs = channelSftp.stat(unitNo);
                channelSftp.cd(unitNo);
                log.info(channelSftp.pwd());
            }catch (Exception e) {
                channelSftp.mkdir(unitNo);
                channelSftp.cd(unitNo);
                log.info(channelSftp.pwd());
                log.info("6");
            }

            String localLocation = saveSftpLocalDir + remoteFilePath;

            try{
                channelSftp.lcd(File.separator);
                log.info(channelSftp.lpwd());
                channelSftp.lcd(localLocation);
                log.info(channelSftp.lpwd());
            } catch(Exception e){
                log.error("lcd {} failed",localLocation);
            }



//
//
//            File file = new File(localLocation);
            FileInputStream in = new FileInputStream(file);
            log.info("localLocation+File.separator+file.getName() : {}",localLocation+File.separator+file.getName());

            log.info("before file.getName() pwd : "+channelSftp.lpwd());


            if(inputStreamEnable){
                log.info("put InputStream Logic start");
                channelSftp.put(in,file.getName());
            } else {
                log.info("put path Logic start");
                log.info("put localPath : {} , to remotePath : {}",localLocation+File.separator+file.getName(),remoteFilePath+File.separator+file.getName());
                channelSftp.put(localLocation+File.separator+file.getName(), file.getName());
            }




            this.isUpload = true;

            in.close();

            return this.isUpload;

        } catch(Exception e) {
            log.error("Error upload file", e.getLocalizedMessage());
            this.isUpload = false;
            return this.isUpload;
        } finally {
            disconnectChannelSftp(channelSftp);
        }


    }

    public boolean downloadFile(String localFilePath, String remoteFilePath) {
        ChannelSftp channelSftp = createChannelSftp();
        OutputStream outputStream;
        try {
            File file = new File(localFilePath);
            outputStream = new FileOutputStream(file);
            channelSftp.get(remoteFilePath, outputStream);
            file.createNewFile();
            return true;
        } catch(SftpException | IOException ex) {
            log.error("Error download file", ex);
        } finally {
            disconnectChannelSftp(channelSftp);
        }

        return false;
    }

    /**
     * 디렉토리( or 파일) 존재 여부
     * @param path 디렉토리 (or 파일)
     * @return
     */
    public SftpATTRS exists(String path, ChannelSftp channelSftp) {
        SftpATTRS res = null;
        try {
            res = channelSftp.stat(path);
        } catch (SftpException e) {
           log.error(e.getLocalizedMessage());
        }
        return res;
    }

    /**
     * 파일 업로드
     *
     * @param dir 저장할 디렉토리
     * @param file 저장할 파일
     * @return 업로드 여부
     */
    public boolean upload(String dir, File file, ChannelSftp channelSftp) {
        boolean isUpload = false;
        SftpATTRS attrs;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            channelSftp.cd(File.separator);
            channelSftp.cd(dir);
            channelSftp.put(in, file.getName());

            // 업로드했는지 확인
//            if (this.exists(dir+File.separator+file.getName(),channelSftp)) {
//                isUpload = true;
//            }
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isUpload;
    }

    private ChannelSftp createChannelSftp() {
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession(username, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect(sessionTimeout);
            Channel channel = session.openChannel("sftp");
            channel.connect(channelTimeout);
            return (ChannelSftp) channel;
        } catch(JSchException ex) {
            log.error("Create ChannelSftp error", ex);
        }

        return null;
    }

    private void disconnectChannelSftp(ChannelSftp channelSftp) {
        try {
            if( channelSftp == null)
                return;

            if(channelSftp.isConnected())
                channelSftp.disconnect();

            if(channelSftp.getSession() != null)
                channelSftp.getSession().disconnect();

        } catch(Exception ex) {
            log.error("SFTP disconnect error", ex);
        }
    }
}
