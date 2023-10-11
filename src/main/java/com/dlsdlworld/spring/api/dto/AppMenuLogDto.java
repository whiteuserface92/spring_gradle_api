package com.dlsdlworld.spring.api.dto;

import com.dlsdlworld.spring.api.types.PlatformTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 */

/**
 * AppMenuLog 테이블 조회용, 메뉴 ID 조회
 */
@Data
@NoArgsConstructor
public class AppMenuLogDto implements Serializable {
    Long id;
    private String userAgent;

    private PlatformTypes deviceType;
    private String userIp;
    private Long menuId;

    private Long hospitalMenuId;

    private String userAccnt;

    private Long appId;

    private String deviceUuid;

    private Long userId;

    private String patientNo;

    private Long hospitalId;

    private String hashKey;

    private String errorCd;

    private String errorMsg;

    private Short logType;
    private String transId;

    private String destUrl;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    /**
     */
    private  String menuNm;

    /**
     * menuNm 제외 처리해서 보여줌
     * @return
     */
    public String getMenuNm() {
        if(null != this.menuNm) {
            return this.menuNm.replaceAll("menu.name.", "");
        } else
        {
            return this.menuNm;
        }
    }
    public AppMenuLogDto(Long id, String userAgent, String userIp,
                         Long menuId, LocalDateTime createdOn, String menuNm) {
        this.id = id;
        this.userAgent = userAgent;
        this.userIp = userIp;
        this.menuId = menuId;
        this.createdOn = createdOn;
        this.menuNm = menuNm;
    }

    public AppMenuLogDto(Long id, String userAgent, PlatformTypes deviceType, String userIp, Long menuId, Long hospitalMenuId, String userAccnt, Long appId, String deviceUuid, Long userId, String patientNo, Long hospitalId, String hashKey, String errorCd, String errorMsg, Short logType, String transId, String destUrl, LocalDateTime createdOn, String menuNm) {
    // public AppMenuLogDto(Long id, String userAgent, String userIp, Long menuId, Long hospitalMenuId, String userAccnt, Long appId, String deviceUuid, Long userId, String patientNo, Long hospitalId, String hashKey, String errorCd, String errorMsg, Short logType, String transId, String destUrl, LocalDateTime createdOn, String menuNm) {
        this.id = id;
        this.userAgent = userAgent;
         this.deviceType = deviceType;
        this.userIp = userIp;
        this.menuId = menuId;
        this.hospitalMenuId = hospitalMenuId;
        this.userAccnt = userAccnt;
        this.appId = appId;
        this.deviceUuid = deviceUuid;
        this.userId = userId;
        this.patientNo = patientNo;
        this.hospitalId = hospitalId;
        this.hashKey = hashKey;
        this.errorCd = errorCd;
        this.errorMsg = errorMsg;
        this.logType = logType;
        this.transId = transId;
        this.destUrl = destUrl;
        this.createdOn = createdOn;
        this.menuNm = menuNm;
    }
}
