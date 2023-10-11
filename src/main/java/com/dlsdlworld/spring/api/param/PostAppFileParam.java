package com.dlsdlworld.spring.api.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *   /rest/appFile 도메인 파라메터 dto
 * </p>
 * @author hyunmin.kim
 * @since 21.07
 */
@Getter
@Setter
@Slf4j
public class PostAppFileParam {

  private Long appId;
  private Long appVerId;
  private MultipartFile appFile;

  public String toString(){
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
