package com.dlsdlworld.spring.api.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UploadFilesParam {
    @NotEmpty
    List<MultipartFile> files;

    @NotEmpty
    String userId;
}
