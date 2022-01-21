package com.team.infrastructure.oss;

import java.io.InputStream;

public interface OSSClient {
    void put(String objectName, InputStream fileStream, String bucketName) throws Exception;

    InputStream get(String objectName, String bucketName) throws Exception;
}
