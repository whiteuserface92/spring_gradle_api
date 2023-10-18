//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.Column;
//import javax.persistence.Lob;
//import javax.persistence.MappedSuperclass;
//
///**
// * 판매상품정보
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseGoodsMallInfo extends BasePersistable {
//
//    /**
//     * 상품이미지url
//     */
//    @Column(length = Columns.imgUrl)
//    private String imgUrl;
//
//    /**
//     * 목록이미지url
//     */
//    @Column(length = Columns.thumbUrl)
//    private String thumbUrl;
//
//    /**
//     * 상품설명
//     */
//    @Lob
//    @Type(type = "org.hibernate.type.TextType")
//    @Column(nullable = false)
//    private String goodsContent;
//
//    /**
//     * 검색키워드
//     */
//    @Column(length = Columns.hashtag)
//    private String hashtag;
//
//    /**
//     * 제조사
//     */
//    @Column(length =Columns.makerNm)
//    private String makerNm;
//
//    /**
//     * 원산지
//     */
//    @Column(length =Columns.originNm)
//    private String originNm;
//
//    /**
//     * 제조일
//     */
//    @Column(length =Columns.makeDtTxt)
//    private String makeDtTxt;
//
//    /**
//     * 유통기한설명
//     */
//    @Column(length =Columns.expirationTxt)
//    private String expirationTxt;
//
//    /**
//     * 재고관리여부
//     */
//    @Column(nullable = false)
//    private Boolean stockEnabled;
//
//    /**
//     * 재고수량
//     */
//    @Column(nullable = false)
//    private Short stockCnt;
//
//    /**
//     * 품절여부
//     */
//    @Column(nullable = false)
//    private Boolean soldout;
//
//    /**
//     * 상품관리메모
//     */
//    @Column(length = Columns.goodsMemo)
//    private String goodsMemo;
//
//    /**
//     * 개별배송비
//     */
//    @Column(nullable = false)
//    private Integer deliveryFee;
//
//    /**
//     * 판매자수수료
//     */
//    @Column(nullable = false)
//    private Integer mdFee;
//
//    /**
//     * 브로커수수료
//     */
//    @Column(nullable = false)
//    private Integer brokerFee;
//
//
//}
