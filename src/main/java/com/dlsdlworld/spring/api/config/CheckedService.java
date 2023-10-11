package com.dlsdlworld.spring.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@Service
public class CheckedService {

    @Value("${spring.api.ipchecklist}")
    private String ipchecklist;

    @Value("${spring.api.otpy.pattern.check}")
    private String patternOtpY;

    @Value("${spring.api.otpn.pattern.check}")
    private String patternOtpN;

    @Value("${spring.api.pass.pattern.check}")
    private String patternPass;

    /**
     * 내/외부망 IP체크
     * @param realIP
     * @return
     */
    public boolean getIPCheckYn(String realIP) {
        boolean ipCheckYn = false;
        String[] checkList = ipchecklist.split(",");
        for (int i = 0; i < checkList.length; i++) {
            /*
            int checkedLength = checkList[i].length();

            String realIPtemp = realIP.substring(0,checkedLength);
//            log.info("### {} {} {}", checkList[i], realIPtemp, realIP);
            */
            if (realIP.indexOf(checkList[i]) != -1) {
                ipCheckYn = true;
                break;
            }
        }
//        log.info("### ipCheckYn {}", ipCheckYn);

        return ipCheckYn;
    }



    /**
     * OTP Y일경우 체크해야하는 패턴
     * @param uri
     * @return
     */
    public boolean getPatternOtpY(String uri) {
        boolean checkYn = false;
        String[] arr = patternOtpY.split(",");

        for(int i=0;arr.length>i;i++) {
            checkYn = uri.indexOf(arr[i]) > -1;
            if(checkYn) {
                break;
            }
        }

        return checkYn;
    }

    /**
     * OTP N일경우 체크해야하는 패턴
     * @param uri
     * @return
     */
    public boolean getPatternOtpN(String uri) {
        boolean checkYn = false;
        String[] arr = patternOtpN.split(",");

        for(int i=0;arr.length>i;i++) {
            checkYn = uri.indexOf(arr[i]) > -1;
            if(checkYn) {
                break;
            }
        }

        return checkYn;
    }

    /**
     * Pass URL 체크해야하는 패턴
     * @param uri
     * @return
     */
    public boolean getPatternPass(String uri) {
        boolean checkYn = false;
        String[] arr = patternPass.split(",");

        for(int i=0;arr.length>i;i++) {
            checkYn = uri.indexOf(arr[i]) > -1;
            if(checkYn) {
                break;
            }
        }

        return checkYn;
    }

    /**
     * OTP YN 가져오기
     * @param req
     * @return
     */
    public boolean getOtpYn(HttpServletRequest req) {
        final Cookie[] cookies = req.getCookies();
        boolean otpYn = false;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase("otpResult")) {
                    if("OK_AUTH_SUCCESS".equals(cookies[i].getValue())) {
                        otpYn = true;
                    }
                }
            }
        }

        return otpYn;
    }
}
