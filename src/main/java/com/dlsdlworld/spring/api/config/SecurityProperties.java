package com.dlsdlworld.spring.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(
        prefix = "common"
)
@Validated
public class SecurityProperties {
    private @NotEmpty String rsaKeyValue;
    private String oauthClientId;
    private String oauthClientSecret;
    private String tokenUrl;

    public SecurityProperties() {
    }

    public String getRsaKeyValue() {
        return this.rsaKeyValue;
    }

    public String getOauthClientId() {
        return this.oauthClientId;
    }

    public String getOauthClientSecret() {
        return this.oauthClientSecret;
    }

    public String getTokenUrl() {
        return this.tokenUrl;
    }

    public void setRsaKeyValue(final String rsaKeyValue) {
        this.rsaKeyValue = rsaKeyValue;
    }

    public void setOauthClientId(final String oauthClientId) {
        this.oauthClientId = oauthClientId;
    }

    public void setOauthClientSecret(final String oauthClientSecret) {
        this.oauthClientSecret = oauthClientSecret;
    }

    public void setTokenUrl(final String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SecurityProperties)) {
            return false;
        } else {
            SecurityProperties other = (SecurityProperties)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$rsaKeyValue = this.getRsaKeyValue();
                    Object other$rsaKeyValue = other.getRsaKeyValue();
                    if (this$rsaKeyValue == null) {
                        if (other$rsaKeyValue == null) {
                            break label59;
                        }
                    } else if (this$rsaKeyValue.equals(other$rsaKeyValue)) {
                        break label59;
                    }

                    return false;
                }

                Object this$oauthClientId = this.getOauthClientId();
                Object other$oauthClientId = other.getOauthClientId();
                if (this$oauthClientId == null) {
                    if (other$oauthClientId != null) {
                        return false;
                    }
                } else if (!this$oauthClientId.equals(other$oauthClientId)) {
                    return false;
                }

                Object this$oauthClientSecret = this.getOauthClientSecret();
                Object other$oauthClientSecret = other.getOauthClientSecret();
                if (this$oauthClientSecret == null) {
                    if (other$oauthClientSecret != null) {
                        return false;
                    }
                } else if (!this$oauthClientSecret.equals(other$oauthClientSecret)) {
                    return false;
                }

                Object this$tokenUrl = this.getTokenUrl();
                Object other$tokenUrl = other.getTokenUrl();
                if (this$tokenUrl == null) {
                    if (other$tokenUrl != null) {
                        return false;
                    }
                } else if (!this$tokenUrl.equals(other$tokenUrl)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SecurityProperties;
    }

    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        Object $rsaKeyValue = this.getRsaKeyValue();
        result = result * 59 + ($rsaKeyValue == null ? 43 : $rsaKeyValue.hashCode());
        Object $oauthClientId = this.getOauthClientId();
        result = result * 59 + ($oauthClientId == null ? 43 : $oauthClientId.hashCode());
        Object $oauthClientSecret = this.getOauthClientSecret();
        result = result * 59 + ($oauthClientSecret == null ? 43 : $oauthClientSecret.hashCode());
        Object $tokenUrl = this.getTokenUrl();
        result = result * 59 + ($tokenUrl == null ? 43 : $tokenUrl.hashCode());
        return result;
    }

    public String toString() {
        return "SecurityProperties(rsaKeyValue=" + this.getRsaKeyValue() + ", oauthClientId=" + this.getOauthClientId() + ", oauthClientSecret=" + this.getOauthClientSecret() + ", tokenUrl=" + this.getTokenUrl() + ")";
    }
}
