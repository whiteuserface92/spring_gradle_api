package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**

 */
@Data
public class PostHospitalParam {

    /** 병원코드*/
    @NotEmpty
    private String hospitalCd;

    /** 병원명코드*/
    @NotEmpty
    private String hospitalNmCd;

    private String hospitalNm;

    /** 병원주소코드*/
    @NotEmpty
    private String hospitalAddrCd;

    /** 병원전화번호코드*/
    //@NotEmpty
    private String hospitalTelnoCd;

    private String telNo;

    private String bizNo;

    private String homeUrl;

    private String introContent;

    private String logoUrl;

    private String campainText;

    private LocalDateTime openedOn;

    private Short dailyVisitCnt;

    private Short bedCnt;

    /** 서버주소*/
    @NotEmpty
    @Pattern(regexp = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    private String serviceIp;

    /** 서비스주소*/
    //@NotEmpty
    @Pattern(regexp = "^https://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String serviceUrl;

    public Short maxPwdAge;

    public Short maxDormantAge;

    private String domainUrl;

    private Short pwdRetryCnt;

    /** 가족등록여부*/
    @NotNull
    private Boolean familyEnabled;

    private String repNm;


    /** api주소*/
    @NotEmpty
    @Pattern(regexp = "^https://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String apiUrl;

    /** 이미지주소*/
    private String imgUrl;

    /** 사용여부*/
    @NotNull
    private Boolean enabled;

    /** fido사용여부*/
    @NotNull
    private Boolean fidoEnabled;

    /** 병원종류*/
    @NotNull
    private String hospitalType;

    /** 메모*/
    private String memo;

    /** 소재지코드*/
    @NotEmpty
    private String locCd;

    /** 메뉴 복사대상 병원 식별자*/
//    @NotNull
    private Long baseMenuHospitalId;

    /** api 복사대상 병원 식별자*/
//    @NotNull
    private Long baseApiHospitalId;

    /** 그룹 식별자*/
    private Long groupId;

    private String ovrdImageUrl;
}
