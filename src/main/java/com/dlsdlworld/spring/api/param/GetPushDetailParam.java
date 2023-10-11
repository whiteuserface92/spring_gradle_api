package com.dlsdlworld.spring.api.param;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetPushDetailParam {

    private int userId;

    private int pushRequestId;


}
