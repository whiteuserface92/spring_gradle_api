package com.dlsdlworld.spring.api.utils;

import com.dlsdlworld.spring.api.exception.FrameworkConfigException;
import com.dlsdlworld.spring.api.exception.InvalidApiParamException;
import com.dlsdlworld.spring.api.security.UserDetails;
import com.dlsdlworld.spring.api.types.AuthTypes;
import com.dlsdlworld.spring.api.types.SimpleAuthTypes;
import com.dlsdlworld.spring.api.types.SnsTypes;
import com.google.common.base.Enums;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 클래스정보를 조회하는 유틸리티.

 */
public class ClassUtils {
    private static final String TYPE_NAME_PREFIX = "class ";

    /**
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class<?> getReclusiveGenericClass(Class<?> clazz, int index) {
        Class<?> targetClass = clazz;
        while (targetClass != null) {
            Class<?> genericClass = getGenericClass(targetClass, index);
            if (genericClass != null) {
                return genericClass;
            }
            targetClass = targetClass.getSuperclass();
        }
        return null;
    }

    /**
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class<?> getGenericClass(Class<?> clazz, int index) {
        Type types[] = getParameterizedTypes(clazz);
        if ((types != null) && (types.length > index)) {
            try {
                return getClass(types[index]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @param target
     * @return
     */
    static public Type[] getParameterizedTypes(Class<?> target) {
        Type[] types = getGenericType(target);
        if (types.length > 0 && types[0] instanceof ParameterizedType) {
            return ((ParameterizedType) types[0]).getActualTypeArguments();
        }
        return null;
    }

    /**
     *
     * @param type
     * @return
     * @throws ClassNotFoundException
     */
    static public Class<?> getClass(Type type) throws ClassNotFoundException {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> componentClass = getClass(componentType);
            if (componentClass != null) {
                return Array.newInstance(componentClass, 0).getClass();
            }
        }
        String className = getClassName(type);
        if (className == null || className.isEmpty()) {
            return null;
        }
        return Class.forName(className);
    }

    /**
     *
     * @param type
     * @return
     */
    static public String getClassName(Type type) {
        if (type == null) {
            return "";
        }
        String className = type.toString();
        if (className.startsWith(TYPE_NAME_PREFIX)) {
            className = className.substring(TYPE_NAME_PREFIX.length());
        }
        return className;
    }

    /**
     *
     * @param target
     * @return
     */
    static public Type[] getGenericType(Class<?> target) {
        if (target == null) {
            return new Type[0];
        }
        Type[] types = target.getGenericInterfaces();
        if (types.length > 0) {
            return types;
        }
        Type type = target.getGenericSuperclass();
        if (type != null) {
            if (type instanceof ParameterizedType) {
                return new Type[]{type};
            }
        }
        return new Type[0];
    }

    /**
     *
     * @param packageName
     * @return
     */
    static public List<Class> getClassesInPackage(String packageName) {
        List<Class> classes = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith(packageName)) {
                    classes.add(info.load());
                }
            }
        } catch (IOException e) {
            throw new FrameworkConfigException(e);
        }

        return classes;
    }

    /**
     *
     * @param packageName
     * @return
     */
    static public List<Class> getAllClassesInPackage(String packageName) {
        List<Class> classes = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
                if (info.getName().startsWith(packageName)) {
                    classes.add(info.load());
                }
            }
        } catch (IOException e) {
            throw new FrameworkConfigException(e);
        }

        return classes;
    }


    static public UserDetails setAuthTypes(UserDetails userDetails) {

        if(Enums.getIfPresent(SnsTypes.class, userDetails.getLoginType().name()).isPresent()) {    // 전달받은 logintype이 sns인지 검증
            userDetails.setAuthType(AuthTypes.SNS);
        } else if(Enums.getIfPresent(SimpleAuthTypes.class, userDetails.getLoginType().name()).isPresent()) { // 전달받은 logintype이 simple인지 검증
            userDetails.setAuthType(AuthTypes.SIMPLE);
//            if(!StringUtils.isEmpty(userDetails.getUuid()))
//                userDetails.setUserAccount(userDetails.getUuid());
        } else if(AuthTypes.PASSWORD.name().equals(userDetails.getLoginType().name())) { //  전달받은 logintype이 password인지 검증
            userDetails.setAuthType(AuthTypes.PASSWORD);
        } else {
            throw new InvalidApiParamException("/login or /addAccount", userDetails.getLoginType().name());
        }

        return userDetails;
    }
}

