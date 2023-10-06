package com.dlsdlworld.spring.api.ui.web;

import com.dlsdlworld.spring.api.types.AppDeploymentTypes;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import javax.validation.constraints.NotBlank;

public class appCacheSearchParam {
    private @NotBlank String pkgNm;
    private AppDeploymentTypes deployType;
    private PlatformTypes platformType;

    public appCacheSearchParam() {
    }

    public String getPkgNm() {
        return this.pkgNm;
    }

    public AppDeploymentTypes getDeployType() {
        return this.deployType;
    }

    public PlatformTypes getPlatformType() {
        return this.platformType;
    }

    public void setPkgNm(final String pkgNm) {
        this.pkgNm = pkgNm;
    }

    public void setDeployType(final AppDeploymentTypes deployType) {
        this.deployType = deployType;
    }

    public void setPlatformType(final PlatformTypes platformType) {
        this.platformType = platformType;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof appCacheSearchParam)) {
            return false;
        } else {
            appCacheSearchParam other = (appCacheSearchParam)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$pkgNm = this.getPkgNm();
                    Object other$pkgNm = other.getPkgNm();
                    if (this$pkgNm == null) {
                        if (other$pkgNm == null) {
                            break label47;
                        }
                    } else if (this$pkgNm.equals(other$pkgNm)) {
                        break label47;
                    }

                    return false;
                }

                Object this$deployType = this.getDeployType();
                Object other$deployType = other.getDeployType();
                if (this$deployType == null) {
                    if (other$deployType != null) {
                        return false;
                    }
                } else if (!this$deployType.equals(other$deployType)) {
                    return false;
                }

                Object this$platformType = this.getPlatformType();
                Object other$platformType = other.getPlatformType();
                if (this$platformType == null) {
                    if (other$platformType != null) {
                        return false;
                    }
                } else if (!this$platformType.equals(other$platformType)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof appCacheSearchParam;
    }

    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        Object $pkgNm = this.getPkgNm();
        result = result * 59 + ($pkgNm == null ? 43 : $pkgNm.hashCode());
        Object $deployType = this.getDeployType();
        result = result * 59 + ($deployType == null ? 43 : $deployType.hashCode());
        Object $platformType = this.getPlatformType();
        result = result * 59 + ($platformType == null ? 43 : $platformType.hashCode());
        return result;
    }

    public String toString() {
        return "appCacheSearchParam(pkgNm=" + this.getPkgNm() + ", deployType=" + this.getDeployType() + ", platformType=" + this.getPlatformType() + ")";
    }
}

