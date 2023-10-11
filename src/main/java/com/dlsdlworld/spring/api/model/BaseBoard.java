package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.BoardTypes;
import com.dlsdlworld.spring.api.types.CompTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseBoard extends BaseModifiable  {

    @Column(length = Columns.boardNm, nullable = false)
    private String boardNm;

    @Column(length = Columns.boardDesc, nullable = false)
    private String boardDesc;

    @Enumerated(EnumType.STRING)
    @Column(length = Columns.boardType, nullable = false)
    private BoardTypes boardType;

    @Enumerated(EnumType.STRING)
    @Column(length = Columns.compType, nullable = false)
    private CompTypes compType;

    @Column(nullable = false)
    private Long compId;

}