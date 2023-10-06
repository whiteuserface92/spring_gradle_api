package com.dlsdlworld.spring.api.types;

/**

 */
public enum ClaimStatusTypes {
	START("10"), /* 청구시작 */
	FAXSEND("12"), /* FAX 발송 중 */
	NOTCLAIM("15"), /* 보험사 선택 실수 청구 */
	END("20"), /* 청구완료 */
	BC("30"), /* 블록체인 등록 */
	FAXFAIL("40"), /* fax 발송 실패 */
	REFUND("50"), /* 환불 완료 */
	PARTREFUND("51"), /* 부분 환불 완료 */
	HOSPITALERROR("60"), /* 병원오류 */
	INSURENCEERROR("70"); /* 보험사 오류 */

	final String code;

	ClaimStatusTypes(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	/**
	 *
	 * @param code
	 * @return
	 */
	public static ClaimStatusTypes codeOf(String code) {
		for (ClaimStatusTypes p : values()) {
			if (p.code.equals(code)) {
				return p;
			}
		}

		throw new IllegalArgumentException("Invalid code of claimStatusType: " + code);
	}
}
