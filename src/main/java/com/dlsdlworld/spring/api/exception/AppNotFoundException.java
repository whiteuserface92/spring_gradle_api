package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.AppDeploymentTypes;
import com.dlsdlworld.spring.api.types.ErrorTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;

/**
 * app정보를 찾지 못했을때 발생되는 예외
 */
public class AppNotFoundException extends RuntimeException {

    /**
     * 생성자
     * @param packageNm 앱 페키지명
     */
    public AppNotFoundException(String packageNm) {
        super(ErrorTypes.APP_NOT_FOUND, packageNm);
    }

    /**
     * 생성자
     * @param appDeploymentType 배포타입
     * @param platformType 플랫폼타입
     * @param packageNm 페키지명
     */
    public AppNotFoundException(AppDeploymentTypes appDeploymentType, PlatformTypes platformType, String packageNm) {
        super(ErrorTypes.APP_NOT_FOUND, String.format("deployType:%s, platformType:%s, package:%s"
            , appDeploymentType.name(), platformType.name(), packageNm));
    }

}
