package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.dto.LogFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/log")
public class LogFIleController {

    @Value("${lemoncare.log.location:/var/log/lemoncare}")
    private String logLocation;

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/getList")
    public List<LogFileDto> getDirList(HttpServletRequest httpServletRequest){
        File dir = new File(logLocation);

//        Pageable page = null;

//        List<LogFileDto> filesName = new LinkedList<>();

//        Page<LogFileDto> pages = null;
        //현재 파일명을 배열로 가져옴.
        File[] files = dir.listFiles();
        // 리스트 생성
        List<LogFileDto> logFileDtoList = new LinkedList<>();
        // 전체 카운트를 가져온다.
//        int totalCount = Objects.requireNonNull(files).length;
        //Make Download URL
        //이전 접속 페이지 주소가져오기
        String referer = httpServletRequest.getHeader("referer");
        log.info("referer : {}",referer);
        //get 파라미터 제거
        if(referer.contains("?")){
            referer = referer.substring(0, referer.indexOf("?"));
        }


        String newreferer = referer.replace("/logging/logDownload","");
        log.info("newreferer : {}",newreferer);


        for(int i = 0; i < files.length; i++ ){
            LogFileDto tmpDto = new LogFileDto();
            //id값 세팅
            tmpDto.setId(i+1);
            //파일네임
            String fileName = String.valueOf(files[i]).substring(String.valueOf(files[i]).lastIndexOf(File.separatorChar) + 1);
            //로그파일 네임 세팅
            tmpDto.setFileName(fileName);
            //파일 다운로드 URL 세팅
            tmpDto.setDownloadUrl(newreferer+"/api/admin/log/download/"+fileName);
            //appLists 에 담기
            logFileDtoList.add(i,tmpDto);
        }

//        if(logFileDtoList != null){
//            pages = new PageImpl<>(logFileDtoList, page, totalCount);
//        }





//        for(int i = 0; i < dir.listFiles().length; i++){
//            filesName.add(i,files[i]).substring(String.valueOf(files[i]).lastIndexOf(File.separatorChar) + 1));
//        }

        return logFileDtoList;
    }

    @PostMapping("/read")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileName) throws IOException {

        log.info("FileName : {}", fileName);

        // 파일 경로
        String path = logLocation + File.separator + fileName;

        File oldFile = new File(path);

        // 파일 존재 유무
        boolean fExist = oldFile.exists();
        if (fExist) {
            File file = new File(path);
            String fn = file.getName();
            // 파일 확장자
            String ext = fn.substring(fn.lastIndexOf(".") + 1);
            HttpHeaders header = new HttpHeaders();
            Path fPath = Paths.get(file.getAbsolutePath());

            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fn);
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            // 대용량일 경우 resource3을 사용해야함
//	        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(fPath ));
//	        Resource resouce2 = resourceLoader.getResource(path);
            InputStreamResource resource3 = new InputStreamResource(new FileInputStream(file));


            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource3);
        } else {
            return null;
        }

    }
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile( @PathVariable String fileName, HttpServletRequest request) throws FileNotFoundException, MalformedURLException {
        //make new file
        File newFile = new File(logLocation+File.separator+fileName);

        //make File Path
        Path filePath = newFile.toPath();

        // Load file as Resource
        Resource resource = new UrlResource(filePath.toUri());

        //resource not found FileNotFoundException
        if (!resource.exists()) {
            throw new FileNotFoundException("File not found " + fileName);
        }

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
