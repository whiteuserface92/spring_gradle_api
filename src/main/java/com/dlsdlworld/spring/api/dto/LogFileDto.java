package com.dlsdlworld.spring.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogFileDto {

    private int id;

    private String fileName;

    private String downloadUrl;

}
