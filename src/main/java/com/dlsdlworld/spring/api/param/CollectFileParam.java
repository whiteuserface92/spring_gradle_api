package com.dlsdlworld.spring.api.param;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CollectFileParam {
    private String hosCd;
    private String userId;
    private String unitNo;
    private String fileNames;

    @Override
    public String toString() {
        return super.toString();
    }
}
