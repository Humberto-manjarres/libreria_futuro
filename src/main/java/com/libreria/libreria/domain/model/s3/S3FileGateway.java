package com.libreria.libreria.domain.model.s3;

import reactor.core.publisher.Mono;

import java.io.InputStream;

public interface S3FileGateway {
    Mono<String>  uploadFile(String key, InputStream fileInputStream, long fileSize);
    Mono<InputStream>  downloadFile(String key);
}
