package com.dlsdlworld.spring.api.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private String rsaKeyValue;
    private String oauthClientId;
    private String oauthClientSecret;
    private String tokenUrl;
    private Integer oauthRequestTimeout;

    public SecurityConfig() {
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

    public Integer getOauthRequestTimeout() {
        return this.oauthRequestTimeout;
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

    public void setOauthRequestTimeout(final Integer oauthRequestTimeout) {
        this.oauthRequestTimeout = oauthRequestTimeout;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SecurityConfig)) {
            return false;
        } else {
            SecurityConfig other = (SecurityConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$oauthRequestTimeout = this.getOauthRequestTimeout();
                    Object other$oauthRequestTimeout = other.getOauthRequestTimeout();
                    if (this$oauthRequestTimeout == null) {
                        if (other$oauthRequestTimeout == null) {
                            break label71;
                        }
                    } else if (this$oauthRequestTimeout.equals(other$oauthRequestTimeout)) {
                        break label71;
                    }

                    return false;
                }

                Object this$rsaKeyValue = this.getRsaKeyValue();
                Object other$rsaKeyValue = other.getRsaKeyValue();
                if (this$rsaKeyValue == null) {
                    if (other$rsaKeyValue != null) {
                        return false;
                    }
                } else if (!this$rsaKeyValue.equals(other$rsaKeyValue)) {
                    return false;
                }

                label57: {
                    Object this$oauthClientId = this.getOauthClientId();
                    Object other$oauthClientId = other.getOauthClientId();
                    if (this$oauthClientId == null) {
                        if (other$oauthClientId == null) {
                            break label57;
                        }
                    } else if (this$oauthClientId.equals(other$oauthClientId)) {
                        break label57;
                    }

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
                    if (other$tokenUrl == null) {
                        return true;
                    }
                } else if (this$tokenUrl.equals(other$tokenUrl)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SecurityConfig;
    }

    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        Object $oauthRequestTimeout = this.getOauthRequestTimeout();
        result = result * 59 + ($oauthRequestTimeout == null ? 43 : $oauthRequestTimeout.hashCode());
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
        return "securityConfig(rsaKeyValue=" + this.getRsaKeyValue() + ", oauthClientId=" + this.getOauthClientId() + ", oauthClientSecret=" + this.getOauthClientSecret() + ", tokenUrl=" + this.getTokenUrl() + ", oauthRequestTimeout=" + this.getOauthRequestTimeout() + ")";
    }
}
