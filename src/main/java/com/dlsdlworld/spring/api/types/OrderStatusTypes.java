package com.dlsdlworld.spring.api.types;

/**
 * 주문 상태 정보
 */
public enum OrderStatusTypes {
    CertateOrder("10") ,    /* 주문서작성 */
    PaymentConfirmation("20") , /* 결제확인 */
    ProductPreParation("30"),   /* 상품준비 */
    DeliveryStart("40"), /* 배송시작 */
    DeliveryCompleted("50"), /* 배송완료 */
    OrderCancellation("60"), /* 주문취소 */
    RefundRequest("70"), /* 환불신청 */
    RefundCompleted("80"), /* 환불완료 */
    ConfirmPurchase("90"); /* 구매확정 */

    private final String code;
    OrderStatusTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static OrderStatusTypes codeOf(String code) {
        for (OrderStatusTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }


}