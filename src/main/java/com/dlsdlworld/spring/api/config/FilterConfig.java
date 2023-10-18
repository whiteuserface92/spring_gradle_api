package com.dlsdlworld.spring.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *   Spring Data Rest 의 요청 정보를
 *   로그에 작성하기위해 추가함
 * </p>
 */
@Slf4j
@Configuration
public class FilterConfig {

  /**
   * <p>
   *    CommonsRequestLoggingFilter 설정하여 로그를 찍음.
   * </p>
   */
  @Bean
  public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilterExtend();
    filter.setIncludeClientInfo(true);
    filter.setIncludeHeaders(true);
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);

    return filter;
  }

  /**
   * <p>
   *   CommonsRequestLoggingFilter 확장해서 사용
   *   shouldNotFilter 필터 및 로깅레벨을 info 로 변경하기 위해서 확장 했음.
   * </p>
   */
  @Slf4j
  static class CommonsRequestLoggingFilterExtend extends CommonsRequestLoggingFilter{

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
      boolean ret = request.getRequestURI().matches(".*\\..*");
      log.trace("request.getRequestURI().matches(\".*\\\\..*\")");
      log.trace("request.getRequestURI() : {} matches : {}", request.getRequestURI(), ret);
      return ret;
    }

    /**
     * <p>
     *   log를 info Level로 내리기 위해 오버라이드
     * </p>
     * @param request
     * @return
     */
    @Override
    protected boolean shouldLog(HttpServletRequest request) {
      return this.log.isInfoEnabled();
    }

    /**
     * <p>
     *   log를 info Level로 내리기 위해 오버라이드
     * </p>
     * @param request
     * @param message
     */
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
      this.log.info(message);
    }

    /**
     * <p>
     *   log를 info Level로 내리기 위해 오버라이드
     * </p>
     * @param request
     * @param message
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
      this.log.info(message);
    }

  }

}



