package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.AppFileService;
import com.dlsdlworld.spring.api.param.FindAppFileParam;
import com.dlsdlworld.spring.api.param.PostAppFileParam;
import com.dlsdlworld.spring.api.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * <p>
 *   /rest/appFile 도메인 컨트롤러
 * </p>
 * @author hyunmin.kim
 * @since 21.07
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class AppFileController {

  private AppFileService appFileService;

  @Autowired
  public AppFileController(AppFileService appFileService) {
    this.appFileService = appFileService;
  }

  /**
   * <p>
   *   App File 업로드
   * </p>
   * @param appFileParam 파일 업로드 파라메터
   * @return <pre>ResponseEntity<uri></pre>
   * @throws Exception
   */
  @PostMapping(value = {"/appFile"})
  public ResponseEntity<?> postAppFile(@ModelAttribute PostAppFileParam appFileParam) throws Exception {
    log.info("'/appFile' PostAppFile 시작");

    Assert.notNull("appId", appFileParam.getAppId());
    Assert.notNull("appVerId", appFileParam.getAppVerId());
    Assert.notNull("appFile", appFileParam.getAppFile());

    log.debug("파라메터 : {}", appFileParam);

    Long appFileId = this.appFileService.postAppFile(appFileParam);

    String uri = WebMvcLinkBuilder.linkTo(AppFileController.class).slash("appFile/download/"+appFileId).toString();
    log.info("'/appFile' PostAppFile 끝");

    return ResponseEntity.ok(uri);

  }

  /**
   * <p>
   *   App File 수정
   *   파일이 있으면 파일은 등록 후 기본 파일 삭제
   *   파일이 없으면 정보만 수정
   * </p>
   * @param appFileId app_file 테이블 아이디
   * @param appFileParam 업데이트 파라메터
   * @return <pre>ResponseEntity<uri></pre>
   * @throws Exception
   */
  @PutMapping(value = "/appFile/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> putAppFile(@PathVariable("id") Long appFileId,
                                      @ModelAttribute PostAppFileParam appFileParam) throws Exception {
    log.info("'/appFile' putAppFile 시작");

    Assert.notNull("appId", appFileParam.getAppId());
    Assert.notNull("appVerId", appFileParam.getAppVerId());

    log.debug("파라메터 : {}", appFileParam);

    this.appFileService.putAppFile(appFileId, appFileParam);

    String uri = WebMvcLinkBuilder.linkTo(AppFileController.class).slash("appFile/download/"+appFileId).toString();
    log.info("'/appFile' putAppFile 끝");
    return ResponseEntity.ok(uri);

  }

  /**
   * <p>
   *   /appFile/download/{id}
   *   파일 다운로드
   * </p>
   * @param appFileId
   * @return <pre>ResponseEntity<Resource></pre>
   * @throws IOException
   */
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  @GetMapping(value = {"/appFile/download/{id}"})
  public ResponseEntity<Resource> downloadAppFile(@PathVariable(name = "id") Long appFileId) throws IOException{
    log.info("'/rest/appFile/download/{id}' downloadAppFile 시작");

    log.debug("파라메터 : appFileId={}", appFileId);

    ResponseEntity<Resource> responseEntity = downloadAppFile(new FindAppFileParam(appFileId));

    log.info("'/rest/appFile/download/{id}' downloadAppFile 끝");

    return responseEntity;

  }

  /**
   * <p>
   *   /appFile/download?appId={appId}&appVerId={appVerId}
   *   파일 다운로드
   * </p>
   * @param appId app_mst id
   * @param appVerId app_version id
   * @return <pre>ResponseEntity<Resource></pre>
   * @throws IOException
   */
  @PreAuthorize("@security.hasPermission({'APP_READ'})")
  @GetMapping(value = {"/appFile/download"})
  public ResponseEntity<Resource> downloadAppFile(@RequestParam Long appId,
                                                  @RequestParam Long appVerId) throws IOException {
    log.info("'/rest/appFile/download' downloadAppFile 시작");

    log.debug("파라메터 : appId={}, appVerId={}", appId, appVerId);

    ResponseEntity<Resource> responseEntity = downloadAppFile(new FindAppFileParam(appId, appVerId));

    log.info("'/rest/appFile/download' downloadAppFile 끝");

    return responseEntity;
  }

  /**
   * /appFile/{id}
   * 파일 삭제
   * @param appFileId app_file id
   * @return <pre>ResponseEntity<uri></pre>
   */
  @PreAuthorize("@security.hasPermission({'APP_DELETE'})")
  @DeleteMapping(value = {"/appFile/{id}"})
  public ResponseEntity<?> deleteAppFile(@PathVariable("id") Long appFileId){
    log.info("'/rest/appFile/{id}' deleteAppFile 끝");

    log.debug("파라메터 : appFileId={}", appFileId);

    this.appFileService.deleteAppFile(new FindAppFileParam(appFileId));

    log.info("'/rest/appFile/{id}' deleteAppFile 끝");

    return ResponseEntity.ok(WebMvcLinkBuilder.linkTo(AppFileController.class).toUri());
  }

  /**
   * <p>
   *   /appFile?appId={appId}&appVerId={appVerId}
   *   파일 삭제
   * </p>
   * @param appId app_mst id
   * @param appVerId app_version id
   * @return <pre>ResponseEntity<uri></pre>
   */
  @PreAuthorize("@security.hasPermission({'APP_DELETE'})")
  @DeleteMapping(value = {"/appFile"})
  public ResponseEntity<?> deleteAppFile(@RequestParam Long appId, @RequestParam Long appVerId){
    log.info("'/rest/appFile' deleteAppFile 끝");

    log.debug("파라메터 : appId={}, appVerId={}", appId, appVerId);

    this.appFileService.deleteAppFile(new FindAppFileParam(appId, appVerId));

    log.info("'/rest/appFile' deleteAppFile 끝");

    return ResponseEntity.ok(WebMvcLinkBuilder.linkTo(AppFileController.class).toUri());
  }


  /**
   * <p>
   *   downloadAppFile 공통 처리 부분
   * </p>
   * @param findAppFileParam
   * @return <pre>ResponseEntity<Resource></pre>
   */
  private ResponseEntity<Resource> downloadAppFile(FindAppFileParam findAppFileParam)
      throws IOException{
    Object[] ret = this.appFileService.downloadAppFile(findAppFileParam);

    String fileOrgNm = (String) ret[0];
    Resource resource = (Resource) ret[1];

    ResponseEntity<Resource> responseEntity = ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .contentLength(resource.contentLength())
        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachement;filename=\"%s\"", fileOrgNm))
        .body(resource);

    return responseEntity;
  }

}
