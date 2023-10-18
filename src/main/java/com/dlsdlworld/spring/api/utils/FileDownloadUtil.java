package com.dlsdlworld.spring.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileDownloadUtil {
    private Path foundFile;

    private static String staticfileLocation;

    @Value("${common.appdownload.location:/home/sysop}")
    public void setFileLocation(String fileLocation){
        staticfileLocation = fileLocation;
    }

    public UrlResource getFileAsResource(String fileName, String folderName) throws IOException {

        Path dirPath = Paths.get(staticfileLocation+ File.separator+folderName);

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().equals(fileName)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
