package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 *
 */
@Setter
@Getter
@Entity
@Table(name = "app_file")
public class AppFile extends BaseModifiable {

  @Column(name = "app_id")
  private Long appId;
  @Column(name = "app_ver_id")
  private Long appVerId;
  @Column(name = "file_path")
  private String filePath;
  @Column(name = "file_nm")
  private String fileNm;
  @Column(name = "file_org_nm")
  private String fileOrgNm;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "app_ver_id", referencedColumnName = "id", insertable = false, updatable = false)
  private AppVersion appVersion;

  public String toString(){
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
