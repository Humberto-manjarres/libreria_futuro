package com.libreria.libreria.infraestructure.entry_points.reactive_web.s3;

import com.libreria.libreria.domain.usecase.s3.S3FileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final S3FileUseCase s3FileUseCase;

    @PostMapping("/upload")
    public Mono<String> uploadFile(@RequestPart("file") Mono<FilePart> filePartMono) {
        return filePartMono.flatMap(filePart -> {
            String key = filePart.filename();
            return DataBufferUtils.join(filePart.content()) // Combina los DataBuffer en uno solo
                    .flatMap(dataBuffer -> {
                        // Convierte el DataBuffer a InputStream para subirlo
                        InputStream inputStream = dataBuffer.asInputStream();
                        long fileSize = dataBuffer.readableByteCount();
                        return s3FileUseCase.uploadFile(key, inputStream, fileSize)
                                .doFinally(signalType -> DataBufferUtils.release(dataBuffer)); // Libera el DataBuffer después de usarlo
                    }).onErrorResume(e -> Mono.error(new RuntimeException("Error al subir el archivo: " + e.getMessage())));
        });
    }


    @GetMapping(value = "/download/{key}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<Resource>> downloadFile(@PathVariable String key) {
        return s3FileUseCase.downloadFile(key).map(inputStream -> {
            InputStreamResource resource = new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        });
    }

}
