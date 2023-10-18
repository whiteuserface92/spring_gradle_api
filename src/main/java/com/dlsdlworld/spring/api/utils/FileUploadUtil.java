package com.dlsdlworld.spring.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class FileUploadUtil {
    private static String staticfileLocation;

//    @Autowired
//    public FileUploadUtil(@Value("${lemoncare.appupload.location:/home/sysop}") String fileLocation) {
//        this.fileLocation = fileLocation;
//    }

    @Value("${common.appupload.location:/home/sysop}")
    public void setFileLocation(String fileLocation){
        staticfileLocation = fileLocation;
    }

    public static String saveFile(String fileName,String version, MultipartFile multipartFile) throws IOException {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String stringDate = now.format(formatter);

        Path newUploadPath = Paths.get(staticfileLocation+ File.separator+stringDate);

        Path uploadPath = Paths.get(staticfileLocation);

        if (!Files.exists(newUploadPath)) {
            Files.createDirectories(newUploadPath);
        }

//        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        //현재 YYYYmmddMMss

        log.debug("Make new Date File : {}",stringDate);


        try (InputStream inputStream = multipartFile.getInputStream()) {
            //Plist  파일 분기처리 제거 - 김동인프로
//            if(fileName.contains(".plist")){
//                Path filePath = newUploadPath.resolve(stringDate + "-"  + fileName);
//                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//
//                return stringDate + "-"  + fileName;
//            } else {
                Path filePath = newUploadPath.resolve(stringDate + "-" + version + "-" + fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                return  stringDate + "-" + version + "-" + fileName;
//            }

        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
    }
}
