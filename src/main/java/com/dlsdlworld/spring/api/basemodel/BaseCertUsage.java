package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertUsage extends BasePersistable {


	/**
	 * 사용일시
	 */
	@Column(nullable = false)
	private LocalDateTime usedOn;


	/**
	 * 사용구분
	 */
	@Column(nullable = false)
	private String useFg;

	/**
	 * 사용용도
	 */
	@Column(nullable = false)
	private String useType;

	/**
	 * 암호설정여부
	 */
	@Column(nullable = false)
	private Boolean pwdEnabled;

	/**
	 * 암호설정값
	 */
	@Column(length = Columns.setPwd)
	private String setPwd;


	/**
	 * 문서열람키
	 */
	@Column(nullable = true)
	private String docOpenKey;

	/**
	 * 이메일
	 */
	@Column(length = Columns.email)
	private String email;


	/**
	 * 팩스번호
	 */
	@Column(length = Columns.faxNo)
	private String faxNo;

	/**
	 * 메일수신처명
	 */
	@Column(length = Columns.emailNm)
	private String emailNm;

	/**
	 * 팩스수신처명
	 */
	@Column(length = Columns.faxNm)
	private String faxNm;

	/**
	 * 성공여부
	 */
	@Column(nullable = false)
	private Boolean successEnabled;

	/**
	 * 미리보기 구분
	 */
	@Column(nullable = false)
	private Boolean previewEnabled;

	/**
	 * 미리보기 일시
	 */
	@Column(nullable = true)
	private LocalDateTime previewOn;

	/**
	 * 발행 매수
	 */
	@Column(nullable = true)
	private Integer issueCnt;

	/**
	 * 사용 매수
	 */
	@Column(nullable = true)
	private Integer useCnt;


	@Column(nullable = true)
	private String userIp;


	/**
	 * 입력일시
	 */
	@Column(nullable = false)
	private LocalDateTime createdOn;
}
