package com.team.application.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class MultipartFileUtil {

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static String getExtension(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }

    /**
     * 生成唯一文件名
     *
     * @param file 文件
     * @author bruce
     */
    public static String generateFileName(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID() + (StringUtils.isEmpty(extension) ? "" : "." + extension);
    }
}
