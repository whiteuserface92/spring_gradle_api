package com.dlsdlworld.spring.api.cache;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * 레디스 프로그램 디플로이용 캐시
 * 단순 URL 저장용으로 사용되며, dev1에서 다운받아서 서버에서 설치한다.
 * 두대 일경우 두대다 다 진행 시켜야 한다.
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : woong.jang
 * Date : 2022-02-19
 * Time : 오후 12:10
 */
@Data
@NoArgsConstructor
@RedisHash("ProgDeployCache")
public class ProgDeployCache implements Serializable {

    /**
     * 서버 패치를 위한  download 관리 프로그램 캐시
     *  install_dir
     *  svr_download_temp_dir
     *  down_deployapp_url : download 명령어로 받을 full path  https://dev1.lemonhc.com/store/mcare-plus/jeuse/lemoncare-plus-app-deploy-0.2.5-SNAPSHOT.jar
     *  deployapp_jar_nm
     *  deployapp_svr_path
     *  deployapp_restart_sh
     *  down_plusui_url : download 명령어로 받을 full path  https://dev1.lemonhc.com/store/mcare-plus/jeuse/lemoncare-plus-app-deploy-0.2.5-SNAPSHOT.jar
     *  plusui_jar_nm
     *  plusui_svr_path
     *  plusui_restart_sh
     */
    @Id     // ID 객체는 로그라 필요 없음
    private String uuid;

    private String installDir;

    private String svrDownloadTempDir;

    private String downDeployappUrl;

    private String deployappJarNm;

    private String deployappSvrPath;

    private String deployappRestartSh;

    private String downPlusuiUrl;

    private String plusuiJarNm;

    private String plusuiSvrPath;
    private String plusuiRestartSh;

}
