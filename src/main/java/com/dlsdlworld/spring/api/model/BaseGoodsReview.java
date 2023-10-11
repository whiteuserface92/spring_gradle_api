package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 상품후기
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/28
 * Time : 3:41 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseGoodsReview extends BasePersistable {

    /**
     * 상품명
     */
    @Column(length = Columns.goodsNm, nullable = false)
    private String goodsNm;

    /**
     * 제목
     */
    @Column(length = Columns.reviewTitle, nullable = false)
    private String reviewTitle;

    /**
     * 내용
     */
    @Column(length = Columns.reviewTxt, nullable = false)
    private String reviewTxt;

    /**
     * 평가점수
     */
    @Column(nullable = false)
    private Short userGrade;

    /** 
     * 첨부파일url
     */
    @Column(length = Columns.fileUrl)
    private String fileUrl;

    /** 
     * 썸네일url
     */
    @Column(length = Columns.thumbUrl)
    private String thumbUrl;

}
