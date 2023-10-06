package com.dlsdlworld.spring.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public abstract class HttpStatusException extends RuntimeException {

    protected String group = "000";
    protected String code = "000";
    protected String message;
    protected String techMessage;

    public abstract HttpStatus getHttpStatus();

    @Override
    public abstract String getMessage();

    public String getDetailMessage() {
        if (StringUtils.isEmpty(this.techMessage)) {
            return this.message;
        } else {
            return techMessage;
        }
    }
}
