package com.dlsdlworld.spring.api.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *   환부 이미지 전송 파일
 *   1.이미지 파일 업로드 , 2. ftp로 파일 업로드 처리
 * </p>
 * @author woong.jang
 * @since 22.06
 */

@Getter
@Setter
@Slf4j
public class PostFileParam  {
    /***
     * 병원 코드
     */
    @NotNull
    private String hosCd;
    /**
     * 의료진 로그인 account
     */
    @NotNull
    private String loginAccount;

    @NotNull
    private String unitNo;

    @NotNull.List({})
    private List<MultipartFile> files;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
