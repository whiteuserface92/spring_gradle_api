package com.dlsdlworld.spring.api.adminApiService;


import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.AppFile;
import com.dlsdlworld.spring.api.param.FindAppFileParam;
import com.dlsdlworld.spring.api.param.PostAppFileParam;
import com.dlsdlworld.spring.api.repository.AppFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * <p>
 *   /rest/appFile 도메인 비즈니스 로직
 * </p>
 * @author hyunmin.kim
 * @since 21.07
 */
@Slf4j
@Service
public class AppFileService {

  final private String STORED_DIR = Paths.get("stored").toAbsolutePath().toString();

  private AppFileRepository appFileRepository;

  @Autowired
  public AppFileService(AppFileRepository appFileRepository) {
    this.appFileRepository = appFileRepository;
  }

  /**
   * <p>
   *   app_file 테이블 등록 처리 및 파일 업로드
   * </p>
   * @param postAppFileParam
   * @throws Exception
   */
  @Transactional
  public Long postAppFile(PostAppFileParam postAppFileParam) throws Exception{

    String storedFileNm = UUID.randomUUID().toString();

    log.info("App 파일 저장 경로 : {} ", STORED_DIR);
    log.info("App 파일 실제 파일명 : {}, 저장 파일명 : {}",
        postAppFileParam.getAppFile().getOriginalFilename(), storedFileNm);

    // DB 처리
    AppFile appFile = new AppFile();

    BeanUtils.copyProperties(postAppFileParam, appFile);
    appFile.setFilePath(this.STORED_DIR);
    appFile.setFileNm(storedFileNm);
    appFile.setFileOrgNm(postAppFileParam.getAppFile().getOriginalFilename());

    appFile = this.appFileRepository.save(appFile);

    // App File 처리
    try {
      if(!Files.isDirectory(Paths.get(this.STORED_DIR))){
        Files.createDirectories(Paths.get(this.STORED_DIR));
      }
      Path filePath = Files.createFile(Paths.get(this.STORED_DIR + File.separator + storedFileNm));
      postAppFileParam.getAppFile().transferTo(filePath);
    }
    catch (Exception e){
      log.error("App 설치 파일 저장 에러", e);
      throw e;
    }

    log.trace("AppFile 객체 : {}", appFile);

    return appFile.getId();
  }

  /**
   * <p>
   *   app_file 테이블 수정<br>
   *   파일이 있을 경우 기존 파일 삭제 후 업로드
   *   파일이 없을 경우 테이블 데이터만 수정
   * </p>
   * @param appFileId
   * @param postAppFileParam
   * @throws Exception
   */
  @Transactional
  public void putAppFile(Long appFileId, PostAppFileParam postAppFileParam) throws Exception{

    String storedFileNm = UUID.randomUUID().toString();

    // DB 처리
    AppFile appFile = this.appFileRepository.findById(appFileId)
        .orElseThrow(()-> new EntityNotFoundException("App File 테이블에 데이터가 없습니다.", appFileId));

    // App File 삭제 파일명
    String delFileNm = appFile.getFilePath() + File.separator + appFile.getFileNm();

    appFile.setAppId(postAppFileParam.getAppId());
    appFile.setAppVerId(postAppFileParam.getAppVerId());

    if(postAppFileParam.getAppFile() != null) {

      log.info("App 파일 저장 경로 : {} ", STORED_DIR);
      log.info("App 파일 실제 파일명 : {}, 저장 파일명 : {}",
          postAppFileParam.getAppFile().getOriginalFilename(), storedFileNm);

      appFile.setFilePath(this.STORED_DIR);
      appFile.setFileNm(storedFileNm);
      appFile.setFileOrgNm(postAppFileParam.getAppFile().getOriginalFilename());

      appFile = this.appFileRepository.save(appFile);

      // App File 처리
      try {
        if(!Files.isDirectory(Paths.get(this.STORED_DIR))){
          Files.createDirectories(Paths.get(this.STORED_DIR));
        }
        Path filePath = Files.createFile(Paths.get(this.STORED_DIR + File.separator + storedFileNm));
        postAppFileParam.getAppFile().transferTo(filePath);
      }
      catch (Exception e){
        log.error("App 설치 파일 저장 에러", e);
        throw e;
      }

      if(!Paths.get(delFileNm).toFile().delete()){
        log.warn("{} 파일을 삭제하지 못했습니다.", delFileNm);
      }
    }
    else {
      appFile = this.appFileRepository.save(appFile);
    }

    log.trace("AppFile 객체 : {}", appFile);

  }

  /**
   * <p>
   *   조건에 따라 app_file 테이블을 조회 후 파일 다운로드 프로세스 진행
   * </p>
   * @param findAppFileParam
   * @return
   */
  public Object[] downloadAppFile(FindAppFileParam findAppFileParam){

    AppFile appFile;
    if(findAppFileParam.getId() != null) {
      appFile = this.appFileRepository.findById(findAppFileParam.getId())
          .orElseThrow(()-> new EntityNotFoundException("App File 테이블에 데이터가 없습니다.", findAppFileParam.getId()));
    }
    else{
      appFile = this.appFileRepository.findByAppIdAndAppVerId(findAppFileParam.getAppId(), findAppFileParam.getAppVerId());
    }

    if(appFile == null || appFile.getId() == null){
      throw new EntityNotFoundException("App File 테이블에 데이터가 없습니다.", findAppFileParam.getId());
    }

    String filePath = appFile.getFilePath() + File.separator + appFile.getFileNm();
    log.debug("다운로드 파일 : {}", filePath);

    Object[] ret = {appFile.getFileOrgNm(), new PathResource(Paths.get(filePath))};

    return ret;

  }

  /**
   * <p>
   *   조건에 따라 app_file 테이블을 조회 후
   *   테이블 데이터 및 파일 삭제 실행
   * </p>
   * @param findAppFileParam
   */
  public void deleteAppFile(FindAppFileParam findAppFileParam){

    AppFile appFile;
    if(findAppFileParam.getId() != null) {
      appFile = this.appFileRepository.findById(findAppFileParam.getId())
          .orElseThrow(()-> new EntityNotFoundException("App File 테이블에 데이터가 없습니다.", findAppFileParam.getId()));
    }
    else{
      appFile = this.appFileRepository.findByAppIdAndAppVerId(findAppFileParam.getAppId(), findAppFileParam.getAppVerId());
    }

    if(appFile == null || appFile.getId() == null){
      throw new EntityNotFoundException("App File 테이블에 데이터가 없습니다.", findAppFileParam.getId());
    }

    // App File 삭제 파일명
    String delFileNm = appFile.getFilePath() + File.separator + appFile.getFileNm();
    if(!Paths.get(delFileNm).toFile().delete()){
      log.warn("{} 파일을 삭제하지 못했습니다.", delFileNm);
    }

    this.appFileRepository.delete(appFile);

  }

}
