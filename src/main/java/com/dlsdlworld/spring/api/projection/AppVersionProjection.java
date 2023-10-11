package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.AppFile;
import com.dlsdlworld.spring.api.model.AppVersion;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "app_version", types = {AppVersion.class})
public interface AppVersionProjection {

  String getId();
  String getVersionCd();
  String getReleaseNote();
  LocalDateTime getReleasedOn();
  Boolean getRequired();

  LocalDateTime getUpdatedOn();
  Long getUpdatedBy();
  LocalDateTime getCreatedOn();
  Long getCreatedBy();

  AppFile getAppFile();


}
