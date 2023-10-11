package com.dlsdlworld.spring.api.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/04/09
 * Time : 5:44 오후
 */
@Slf4j
//@Component
public class EndpointsListener {

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		applicationContext.getBean(RequestMappingHandlerMapping.class)
			.getHandlerMethods().forEach((key, value) -> log.info("## Endpoint: {} {}", key, value));
	}
}
