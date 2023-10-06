package com.dlsdlworld.spring.api.ui.web;

import javax.validation.constraints.NotEmpty;

public class WithdrawalParam {
    private @NotEmpty String withdrawalType;
    private @NotEmpty String withdrawalDesc;

    public WithdrawalParam() {
    }

    public String getWithdrawalType() {
        return this.withdrawalType;
    }

    public String getWithdrawalDesc() {
        return this.withdrawalDesc;
    }

    public void setWithdrawalType(final String withdrawalType) {
        this.withdrawalType = withdrawalType;
    }

    public void setWithdrawalDesc(final String withdrawalDesc) {
        this.withdrawalDesc = withdrawalDesc;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof WithdrawalParam)) {
            return false;
        } else {
            WithdrawalParam other = (WithdrawalParam)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$withdrawalType = this.getWithdrawalType();
                Object other$withdrawalType = other.getWithdrawalType();
                if (this$withdrawalType == null) {
                    if (other$withdrawalType != null) {
                        return false;
                    }
                } else if (!this$withdrawalType.equals(other$withdrawalType)) {
                    return false;
                }

                Object this$withdrawalDesc = this.getWithdrawalDesc();
                Object other$withdrawalDesc = other.getWithdrawalDesc();
                if (this$withdrawalDesc == null) {
                    if (other$withdrawalDesc != null) {
                        return false;
                    }
                } else if (!this$withdrawalDesc.equals(other$withdrawalDesc)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WithdrawalParam;
    }

    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        Object $withdrawalType = this.getWithdrawalType();
        result = result * 59 + ($withdrawalType == null ? 43 : $withdrawalType.hashCode());
        Object $withdrawalDesc = this.getWithdrawalDesc();
        result = result * 59 + ($withdrawalDesc == null ? 43 : $withdrawalDesc.hashCode());
        return result;
    }

    public String toString() {
        return "WithdrawalParam(withdrawalType=" + this.getWithdrawalType() + ", withdrawalDesc=" + this.getWithdrawalDesc() + ")";
    }
}

