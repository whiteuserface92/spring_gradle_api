package com.dlsdlworld.spring.api.aop;

import com.dlsdlworld.spring.api.types.ActionTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAdminExecution {

    public ActionTypes code() default ActionTypes.READ;

    public String descriptions() default "";

}
