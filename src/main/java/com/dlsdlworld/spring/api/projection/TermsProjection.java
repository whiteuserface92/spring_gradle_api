package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.Terms;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

/**
 * Project : IntelliJ IDEA
 * Created by ricky
 * Date : 2020/12/09
 * Time : 10:29 오전
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
