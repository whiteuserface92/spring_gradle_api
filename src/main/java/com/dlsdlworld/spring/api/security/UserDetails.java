package com.dlsdlworld.spring.api.security;

import com.dlsdlworld.spring.api.exception.InvalidUserCredentialsException;
import com.dlsdlworld.spring.api.types.AuthTypes;
import com.dlsdlworld.spring.api.types.LoginTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import com.dlsdlworld.spring.api.validator.ValidationUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 프레임워크 공통 로그인 정보
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails {

    /**
     * 앱식별자
     */
    @NotNull
    @JsonProperty("appId")
    private Long appId;

    /**
     * 인증타입
     */
    @JsonProperty("authType")
    private AuthTypes authType;

    /**
     * 로그인아이디
     */
    @NotBlank
    @JsonProperty("userAccount")
    private String userAccount;

    /**
     * 사용자식별자
     */
    @JsonProperty("userId")
    private Long userId;

    /**
     * 로그인타입
     */
    @NotNull
    @JsonProperty("loginType")
    private LoginTypes loginType;

    /**
     * 로그인비밀번호
     */
    @JsonProperty("userPwd")
    private String userPwd;

    /**
     * oauth client id
     */
//    @JsonProperty("clientId")
//    private String clientId;

    /**
     * device uuid
     */
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 병원코드(auth_pwd에 hospitalCd가 필수컬럼이라서 옵션으로 받음)
     */
    @JsonProperty("hospitalCd")
    private String hospitalCd;

    //TODO 삭제예정
    @JsonProperty("pushToken")
    private String pushToken;

    //TODO 삭제예정
    @JsonProperty("deviceVer")
    private String deviceVer;

    //TODO 삭제예정
    @JsonProperty("modelNm")
    private String modelNm;

    /**
     * 플랫폼타입
     */
    @JsonProperty("platformType")
    private PlatformTypes platformType;

    //TODO 삭제예정
    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("userNm")
    private String userNm;

    /*
   22-05-19
    * app_mst의 id : Android, Ios의 마스터 아이디
    */
    @JsonProperty("appPushId")
    private Long appPushId;
    @Builder
    public UserDetails(@NotNull Long appId,
                            @NotNull AuthTypes authType,
                            @NotEmpty String userAccount,
                            Long userId,
                            LoginTypes loginType,
                            String userPwd,
//                            String clientId,
                            String uuid,
                            String hospitalCd,
                            String userNm) {
        this.appId = appId;
        this.authType = authType;
        this.userAccount = userAccount;
        this.userId = userId;
        this.loginType = loginType;
        this.userPwd = userPwd;
//        this.clientId = clientId;
        this.uuid = uuid;
        this.hospitalCd = hospitalCd;
        this.userNm = userNm;
    }

    //    if(loginType.equals("OTP")) {
//        username = "{\"appId\":\""+ appId +"\", \"authType\":\""+ authType +"\", \"userAccount\":\""+ userAccount +"\", \"userId\":\""+ UserDetails.getUserId() +"\", \"loginType\":\""+ loginType +"\", \"uuid\":\""+ uuid +"\"}";
//        username = username.replaceAll("\\\\", "" );
//    } else {
//        username = "{\"appId\":\""+ appId +"\", \"authType\":\""+ authType +"\", \"userAccount\":\""+ userAccount +"\", \"loginType\":\""+ loginType +"\", \"uuid\":\""+ uuid +"\"}";
//        username = username.replaceAll("\\\\", "" );
//    }
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserDetails readUserDetails(String username) {

        // log.info("###KKKH_LOG : {}", username);

        if (username.isEmpty())
            throw new InvalidUserCredentialsException();

        try {
            UserDetails userDetails = new ObjectMapper().readerFor(UserDetails.class).readValue(username);
            if(log.isDebugEnabled()) {
                //   log.debug("###KKH_LOG userDetails:{}", userDetails);
            }
            String message = ValidationUtils.validationBean(userDetails);
            //log.info("###KKH_LOG message:{}", message);
            if (!message.isEmpty())
                throw new InvalidUserCredentialsException();

            return userDetails;
        } catch (InvalidUserCredentialsException e) {
            // log.error("###KKH_LOG catch1: {}", e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            //  log.error("###KKH_LOG catch2: {}", e.getLocalizedMessage());
            throw new InvalidUserCredentialsException(e);
        }
    }

    /**
     *
     * @param authentication
     * @return
     */
    public static UserDetails readUserDetails(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails)authentication.getPrincipal();
            return readUserDetails(springSecurityUser.getUserNm());
        } else if (authentication.getPrincipal() instanceof String) {
            return readUserDetails((String)authentication.getPrincipal());
        }

        throw new UsernameNotFoundException("Invalid user principal");
    }

    /**
     *
     * @return
     */
    public static UserDetails readUserDetails() {
        return readUserDetails(SecurityContextHolder.getContext().getAuthentication());
    }

}

