package com.dlsdlworld.spring.api.types;

/**

 */
public enum LoginTypes {

    /** id, password login*/
    PASSWORD,

    /** naver oauth login*/
    NAVER,

    /** google oauth login*/
    GOOGLE,

    /** kakao oauth login*/
    KAKAO,

    /** apple oauth login*/
    APPLE,

    /** plus legacy login*/
    LEGACY,

    /** pin simple login*/
    PIN,

    /** pattern simple login*/
    PATTERN,

    /** face simple login*/
    FACE,

    /** finger simple login*/
    FINGER,

    /** otp two factor login*/
    OTP,

    SEV, //삭제예정

    SNU;

    //    BC, //삭제예정

    //    BIO, //삭제예정
}
