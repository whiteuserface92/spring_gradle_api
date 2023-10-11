package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseKcdDisease extends BasePersistable {
    /**
     * COMMENT ON COLUMN kcd_disease.disease_cd IS '상병기호';
     * COMMENT ON COLUMN kcd_disease.disease_nm_kr IS '상병명칭';
     * COMMENT ON COLUMN kcd_disease.disease_nm_en IS '상병영문';
     * COMMENT ON COLUMN kcd_disease.category_yn IS '완전코드';
     * COMMENT ON COLUMN kcd_disease.main_yn IS '주상병여부';
     * COMMENT ON COLUMN kcd_disease.infect_yn IS '법정감염병구분';
     * COMMENT ON COLUMN kcd_disease.sex IS '성별';
     * COMMENT ON COLUMN kcd_disease.max_age IS '상한연령';
     * COMMENT ON COLUMN kcd_disease.min_age IS '하한연령';
     * COMMENT ON COLUMN kcd_disease.medicine_txt IS '양한방구분';
     * COMMENT ON COLUMN kcd_disease.remark_txt IS '통계청반영내역';
     * COMMENT ON TABLE kcd_disease IS '상병분류기호';
     */
    @Column(length = Columns.diseaseCd, nullable = false)
    private String diseaseCd;
    @Column(length = Columns.diseaseNmKr, nullable = false)
    private String diseaseNmKr;
    @Column(length = Columns.diseaseNmEn, nullable = false)
    private String diseaseNmEn;
    @Column(length = Columns.categoryYn, nullable = true)
    private String categoryYn;
    @Column(length = Columns.mainYn, nullable = true)
    private String mainYn;
    @Column(length = Columns.infectYn, nullable = true)
    private String infectYn;
    @Column(length = Columns.maxAge, nullable = true)
    private String maxAge;
    @Column(length = Columns.minAge, nullable = true)
    private String minAge;
    @Column(length = Columns.medicineTxt, nullable = true)
    private String medicineTxt0;
    @Column(length = Columns.remarkTxt, nullable = true)
    private String remarkTxt;
}
