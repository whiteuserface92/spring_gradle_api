package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.cacherepository.AppCacheRepository;
import com.dlsdlworld.spring.api.exception.*;
import com.dlsdlworld.spring.api.security.UserDetails;
import com.dlsdlworld.spring.api.types.InvalidApiException;
import com.dlsdlworld.spring.api.ui.web.WithdrawalParam;
import com.dlsdlworld.spring.api.utils.ClassUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/rest"})
public abstract class AuthRestController {
    private static final Logger log = LoggerFactory.getLogger(AuthRestController.class);

    @Value("${routes.auth.url}")
    protected String authServiceUrl;
    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${web.secure-cookie:true}")
    private Boolean enableSecureCookie;
    private AppCacheRepository appCacheRepository;
    protected RestTemplate restTemplate = new RestTemplate();

//    @Autowired
//    public AuthRestController(AppCacheRepository appCacheRepository) {
//        this.appCacheRepository = appCacheRepository;
//    }

    @PostMapping({"/login"})
    protected ResponseEntity<Map<String, Object>> commonLogin(HttpServletResponse response, @RequestBody UserDetails userDetails) throws IOException {
        log.info("AuthRestController /login start");
        userDetails = ClassUtils.setAuthTypes(userDetails);
        String username = this.setUsername(userDetails);
        String password = StringUtils.isEmpty(userDetails.getUserPwd()) ? "" : userDetails.getUserPwd();
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("grant_type", "password");
        multiValueMap.add("username", username);
        multiValueMap.add("password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(this.clientId, this.clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> restParams = new HttpEntity(multiValueMap, headers);
        ResponseEntity<Map> responseEntity = null;

        try {
            responseEntity = this.restTemplate.exchange(this.authServiceUrl + "/oauth/token", HttpMethod.POST, restParams, Map.class, new Object[0]);
        } catch (HttpClientErrorException var11) {
            this.httpErrorThrow(var11);
        } catch (Exception var12) {
            InvalidApiException InvalidApiException = new InvalidApiException("/login", var12.getMessage());
            InvalidApiException.addSuppressed(var12);
            throw InvalidApiException;
        }

        this.setAuthenticationCookie(response, (Map)responseEntity.getBody());
        if (!StringUtils.isEmpty(userDetails.getPushToken()) && !StringUtils.isEmpty(userDetails.getUuid())) {
            this.setLoginPushToken((Map)responseEntity.getBody(), userDetails);
        }

        log.info("AuthRestController /login end");
        return new ResponseEntity((Map)responseEntity.getBody(), HttpStatus.OK);
    }

//    @PostMapping({"/login/appProcessed"})
//    protected ResponseEntity<Map<String, Object>> appProcessedLogin(HttpServletResponse response, @RequestBody appCacheSearchParam param) throws IOException {
//        AppCache cache = (AppCache)Optional.ofNullable(this.appCacheRepository.findByPkgNmAndDeployTypeAndPlatformType(param.getPkgNm(), param.getDeployType().name(), param.getPlatformType().name())).orElseThrow(() -> {
//            return new InvalidApiException("/login/appProcessed", "앱 정보를 찾을 수 없습니다.");
//        });
//        if (cache.getIsProcessed() != null && cache.getIsProcessed()) {
//            return this.commonLogin(response, UserDetails.builder().appId(1L).userAccount("care@hc.com").userPwd("P@ssw0rd").loginType(LoginTypes.PASSWORD).build());
//        } else {
//            throw new InvalidApiException("/login/appProcessed", "심사 중인 앱의 앱 정보가 아닙니다. ");
//        }
//    }

    @PostMapping({"/refreshToken"})
    protected ResponseEntity<Map<String, Object>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("###KKH_LOG AuthRestController /refreshToken start");
        String refreshToken = this.extractRefreshToken(request);
        if (StringUtils.isEmpty(refreshToken)) {
            throw new TokenExpiredException();
        } else {
            MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap();
            multiValueMap.add("grant_type", "refresh_token");
            multiValueMap.add("refresh_token", refreshToken);
            multiValueMap.add("client_id", this.clientId);
            log.info("###KKH_LOG AuthRestController /refreshToken refreshToken : " + refreshToken);
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(this.clientId, this.clientSecret);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, Object>> restParams = new HttpEntity(multiValueMap, headers);
            ResponseEntity<Map> responseEntity = null;
            log.info("###KKH_LOG AuthRestController /refreshToken clientId : " + this.clientId);
            log.info("###KKH_LOG AuthRestController /refreshToken clientSecret : " + this.clientSecret);

            try {
                responseEntity = this.restTemplate.exchange(this.authServiceUrl + "/oauth/token", HttpMethod.POST, restParams, Map.class, new Object[0]);
            } catch (HttpClientErrorException var9) {
                this.httpErrorThrow(var9);
            } catch (Exception var10) {
                throw new InvalidApiException("/refreshToken", var10.getMessage());
            }

            log.info("###KKH_LOG AuthRestController /refreshToken > setAuthenticationCookie start");
            this.setAuthenticationCookie(response, (Map)responseEntity.getBody());
            log.info("###KKH_LOG AuthRestController /refreshToken > setAuthenticationCookie end");
            log.info("###KKH_LOG AuthRestController /refreshToken end");
            return new ResponseEntity((Map)responseEntity.getBody(), HttpStatus.OK);
        }
    }

    @PostMapping({"/logout"})
    protected ResponseEntity<Map<String, Object>> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("common-ui logout init!");
        Cookie cookie = new Cookie("refreshToken", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        cookie = new Cookie("accessToken", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping({"/withdrawal"})
    protected ResponseEntity<Map<String, Object>> withdrawal(@RequestBody WithdrawalParam param, HttpServletRequest request, HttpServletResponse response) {
        log.info("###KKH_LOG AuthRestController withdrawal start");
        String authorization = request.getHeader("Authorization");
        String accessToken = "";
        if (authorization != null && authorization.contains("Bearer")) {
            accessToken = authorization.substring("Bearer".length() + 1);
            log.info("###KKH_LOG AuthRestController withdrawal accessToken :{}", accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            log.info("###KKH_LOG AuthRestController withdrawalType:{}, withdrawalDesc:{}", param.getWithdrawalType(), param.getWithdrawalDesc());
            HashMap paramMap = new HashMap();
            paramMap.put("withdrawalType", param.getWithdrawalType());
            paramMap.put("withdrawalDesc", param.getWithdrawalDesc());
            HttpEntity restParams = new HttpEntity(paramMap, headers);
            ResponseEntity<Map> responseEntity = null;

            try {
                responseEntity = this.restTemplate.exchange(this.authServiceUrl + "/rest/withdrawal", HttpMethod.POST, restParams, Map.class, new Object[0]);
            } catch (HttpClientErrorException var11) {
                var11.printStackTrace();
                this.httpErrorThrow(var11);
            } catch (Exception var12) {
                var12.printStackTrace();
                throw new InvalidApiException("/withdrawal", var12.getMessage());
            }

            this.logout(request, response);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new AuthTokenNotFoundException();
        }
    }

    private HttpServletResponse setAuthenticationCookie(HttpServletResponse response, Map<String, String> resMap) {
        String authUrl = this.authServiceUrl + "/oauthClientDetailses/search/findByClientId?clientId=" + this.clientId;
        ResponseEntity<Map> responseEntity = null;

        try {
            responseEntity = this.restTemplate.getForEntity(authUrl, Map.class, new Object[0]);
        } catch (Exception var9) {
            throw new ApiCallException("/oauthClientDetailses/search/findByClientId", var9.getCause());
        }

        Map<String, Object> bodyMap = (Map)responseEntity.getBody();
        int accessTokenValidity = Integer.parseInt(bodyMap.get("accessTokenValidity").toString());
        int refreshTokenValidity = Integer.parseInt(bodyMap.get("refreshTokenValidity").toString());
        Cookie cookie = new Cookie("refreshToken", (String)resMap.get("refresh_token"));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(refreshTokenValidity);
        cookie.setSecure(this.enableSecureCookie);
        response.addCookie(cookie);
        log.trace("{} refresh_token 쿠키 셋팅 완료", resMap.get("refresh_token"));
        cookie = new Cookie("accessToken", (String)resMap.get("access_token"));
        cookie.setPath("/");
        cookie.setMaxAge(accessTokenValidity);
        cookie.setSecure(this.enableSecureCookie);
        response.addCookie(cookie);
        log.trace("{} access_token 쿠키 셋팅", resMap.get("access_token"));
        cookie = new Cookie("otpResult", "OK_AUTH_SUCCESS");
        cookie.setPath("/");
        cookie.setMaxAge(accessTokenValidity);
        response.addCookie(cookie);
        log.trace("otpResult 쿠키 셋팅");
        return response;
    }

    private void setLoginPushToken(Map<String, String> resMap, UserDetails userDetails) {
        String accessToken = (String)resMap.get("access_token");
        ObjectMapper mapper = new ObjectMapper();
        String encodedTokenPayload = accessToken.split("\\.")[1];
        String tokenPayload = new String((new Base64(true)).decode(encodedTokenPayload));
        new HashMap();
        new HashMap();

        try {
            Map<String, Object> payloadMap = (Map)mapper.readValue(tokenPayload, new TypeReference<Map<String, Object>>() {
            });
            Map<String, Object> usernameMap = (Map)mapper.readValue(payloadMap.get("user_name").toString(), new TypeReference<Map<String, Object>>() {
            });
            usernameMap.put("pushToken", userDetails.getPushToken());
            usernameMap.put("uuid", userDetails.getUuid());
            usernameMap.put("deviceVer", userDetails.getDeviceVer());
            usernameMap.put("modelNm", userDetails.getModelNm());
            usernameMap.put("platformType", userDetails.getPlatformType());
            usernameMap.put("appPushId", userDetails.getAppPushId());
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            HttpEntity<Map<String, Object>> restParams = new HttpEntity(usernameMap, headers);
            this.restTemplate.exchange(this.authServiceUrl + "/rest/setLoginDeviceToken", HttpMethod.POST, restParams, Map.class, new Object[0]);
        } catch (Exception var11) {
            throw new InvalidApiException("/setLoginPushToken", userDetails.toString());
        }
    }

    private String extractRefreshToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equalsIgnoreCase("refreshToken")) {
                    return cookies[i].getValue();
                }
            }
        }

        return null;
    }

    private String extractAccessToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equalsIgnoreCase("accessToken")) {
                    return cookies[i].getValue();
                }
            }
        }

        return null;
    }

    protected String setUsername(UserDetails UserDetails) {
        String userAccount = UserDetails.getUserAccount();
        String appId = String.valueOf(UserDetails.getAppId());
        String authType = UserDetails.getAuthType().name();
        String loginType = UserDetails.getLoginType().name();
        String uuid = StringUtils.isEmpty(UserDetails.getUuid()) ? "" : UserDetails.getUuid();
        String username = "";
        String appPushId = String.valueOf(UserDetails.getAppPushId());
        if (loginType.equals("OTP")) {
            username = "{\"appId\":\"" + appId + "\",\"appPushId\":\"" + appPushId + "\", \"authType\":\"" + authType + "\", \"userAccount\":\"" + userAccount + "\", \"userId\":\"" + UserDetails.getUserId() + "\", \"loginType\":\"" + loginType + "\", \"uuid\":\"" + uuid + "\"}";
            username = username.replaceAll("\\\\", "");
        } else {
            username = "{\"appId\":\"" + appId + "\",\"appPushId\":\"" + appPushId + "\", \"authType\":\"" + authType + "\", \"userAccount\":\"" + userAccount + "\", \"loginType\":\"" + loginType + "\", \"uuid\":\"" + uuid + "\"}";
            username = username.replaceAll("\\\\", "");
        }

        return username;
    }

    protected void httpErrorThrow(HttpClientErrorException e) {
        if (HttpStatus.BAD_REQUEST == e.getStatusCode()) {
            throw new InvalidUserCredentialsException(e.getCause());
        } else if (HttpStatus.UNAUTHORIZED == e.getStatusCode()) {
            throw new UnauthorizedException(e.getCause());
        } else if (HttpStatus.FORBIDDEN == e.getStatusCode()) {
            throw new ForbiddenException(e.getCause());
        }
    }
}

