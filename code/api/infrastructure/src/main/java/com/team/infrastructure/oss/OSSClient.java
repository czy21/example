package com.team.infrastructure.oss;

import java.io.InputStream;

public interface OSSClient {
    void upload(String objectName, InputStream fileStream, String bucketName) throws Exception;
}
