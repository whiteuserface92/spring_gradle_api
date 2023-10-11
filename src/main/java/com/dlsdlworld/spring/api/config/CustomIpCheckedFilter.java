package com.dlsdlworld.spring.api.config;

import com.dlsdlworld.spring.api.utils.IPCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(1)
public class CustomIpCheckedFilter extends GenericFilterBean {

    private final CheckedService checkedService;

    public CustomIpCheckedFilter(CheckedService checkedService) {
        this.checkedService = checkedService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /**
         * 1. 외부 OTP Y -> 메뉴차단
         * 2. 외부 OTP N -> 전체차단
         */
        boolean inOutChecked = checkedService.getIPCheckYn(IPCheckUtils.getUserIP(req)); //내부망 true, 외부망 false
        boolean optYn = checkedService.getOtpYn(req); //OPT 여부 확인
        boolean patternY = checkedService.getPatternOtpY(req.getRequestURI()); //OTP Y일경우 차단해야 할 패턴
        boolean patternN = checkedService.getPatternOtpN(req.getRequestURI()); //OTP N일경우 차단해야 할 패턴
        boolean patternPass = checkedService.getPatternPass(req.getRequestURI()); //Pass프로퍼티 패턴

        //log.info("#### in otpyn patty pattn {} {} {} {}", temp, patternY, patternN, patternPass);


        if(patternPass){//pass패턴일땐 외부망 서비스
            chain.doFilter(req, res);
        }else if(!inOutChecked) { //외부망
            if(optYn) { //OTP 인증을 한 경우
                if(patternY) { //특정 메뉴 패턴
                    ServletContext context = request.getServletContext();
                    context.getRequestDispatcher("/externalContents").forward(request, response);
//                    res.sendRedirect(req.getContextPath() + "/externalContents");
                } else {
                    chain.doFilter(req, res);
                }
            } else { //전체 차단
                if(patternN) { //전체 패턴
                    ServletContext context = request.getServletContext();
                    context.getRequestDispatcher("/ipCheck").forward(request, response);
//                    res.sendRedirect(req.getContextPath() + "/ipCheck");
                } else {
                    chain.doFilter(req, res);
                }
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}