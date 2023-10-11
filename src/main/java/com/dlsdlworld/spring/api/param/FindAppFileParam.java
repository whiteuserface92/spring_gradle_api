package com.dlsdlworld.spring.api.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
public class FindAppFileParam {

  private Long id;
  private Long appId;
  private Long appVerId;

  public FindAppFileParam(Long id) {
    this.id = id;
  }

  public FindAppFileParam(Long appId, Long appVerId) {
    this.appId = appId;
    this.appVerId = appVerId;
  }

  public FindAppFileParam(Long id, Long appId, Long appVerId) {
    this.id = id;
    this.appId = appId;
    this.appVerId = appVerId;
  }

  public String toString(){
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
