package com.team.application.model.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BaseImportVO {
    private MultipartFile file;
}
