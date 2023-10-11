package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 사용자 기본페이지 설정용 객체 리턴용
 * 환자찾기
 * 전자동의서
 * 일정관리
 * 의료진홈
 *  Menu에 menuDesc에 해당 기본페이지 설정 하는 문구를 넣는다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDefalutPageDTO {
    private String pageGrpCd;  // 코드
    private String pageGrpNm; //": "환자찾기",
    private String pageGrpEngNm; //"Patient",
    private List<UserDefaultPageListDTO> pageList;
}
