package com.dlsdlworld.spring.api.param;

import com.dlsdlworld.spring.api.types.AppDeploymentTypes;
import com.dlsdlworld.spring.api.types.AppStateTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 */
@Data
public class PostAppParam {

    /**앱id**/
    private Long appId;

    /** 앱이름 **/
//    @NotEmpty
    private String appNm;

    /**앱 제품단계 **/
//    @NotEmpty
    @Enumerated(EnumType.STRING)
    private AppStateTypes appState;

    /**최신앱버전**/
 //   @NotEmpty
    private String appVer;

    /**FCM키**/
    private String fcmKey;

    /**FIDO API키**/
    private String fidoApiKey;

    /**다중단말허용**/
//    @NotEmpty
    private Boolean multiEnabled;

    /**플랫폼id**/
//    @NotEmpty
    private Long appPlatformId;

    /**플랫폼타입**/
//    @NotEmpty
    @Enumerated(EnumType.STRING)
    private PlatformTypes platformType;

    /**패키지명**/
//    @NotEmpty
    private String pkgNm;

    /**배포타입**/
//    @NotEmpty
    @Enumerated(EnumType.STRING)
    private AppDeploymentTypes deployType;

    /**해시값**/
    private String hashKey;

    /**스토어URL**/
    private String storeUrl;

    /**버전 id**/
//    @NotEmpty
    private Long appVersionId;

    /**버전코드**/
//    @NotEmpty
    private String versionCd;

    /**릴리즈노트**/
//    @NotEmpty
    private String releaseNote;

    /**릴리즈일자**/
//    @NotBlank
    private LocalDateTime releasedOn;

    /**필수여부**/
//    @NotBlank
    private Boolean required;

    /**IOS심사여부*/
    private Boolean iosProcessed;


    private Long hospitalId;

    private String prodCd;

}
