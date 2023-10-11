package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.Terms;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

/**

 */
@Projection(name = "TermsProjection", types = {Terms.class})
public interface TermsProjection {

    Long getId();

    String getTermsOwnerCd();

    String getTermsCd();

    String getTermsVer();

    String getTermsNm();

    LocalDateTime getStartedOn();

    Boolean getRequired();

}
