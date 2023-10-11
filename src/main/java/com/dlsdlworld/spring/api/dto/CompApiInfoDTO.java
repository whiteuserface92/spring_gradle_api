package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/06/24
 * Time : 15:39 오후
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompApiInfoDTO {

    private Number id;                      // 인터페이스id
    private String compType;                // 기관구분
    private Number compId;                  // 기관id
    private Number apiVersionId;            // 버전id
    private String ioType;                  // 송수신전문구분
    private Boolean headerEnabled;          // 헤더길이포함여부
    private String interfaceDesc;           // 인터페이스설명
    private Number createdBy;               // 입력자id
    private java.sql.Timestamp createdOn;   // 입력일시
    private Boolean tcpReuseEnabled;        // TCP재사용여부
    private String characterSet;            // 캐릭터셋
    private int headerCnt;                  // 헤더개수
    private String fieldIds;                // 전문키배열
    private String compParamCnstrs;         // 전문 제약 설정 목록
}
