package org.learn_everyday.chapter2.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path path = Path.of("src/main/java/org/learn_everyday/chapter2/io/resources");

    @Override
    public Mono<String> read(String file) {
        return Mono.fromCallable(() -> Files.readString(path.resolve(file)));
    }

    @Override
    public Mono<Void> write(String file, String content) {
        return Mono.fromRunnable(() -> writeFile(file, content));
    }

    @Override
    public Mono<Void> delete(String file) {
        return Mono.fromRunnable(() -> deleteFile(file));
    }

    private void writeFile(String fileName, String content) {
        try {
            Files.writeString(path.resolve(fileName), content);
            log.info("Created File: {}", fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String fileName) {
        try {
            Files.delete(path.resolve(fileName));
            log.info("Deleted File: {}", fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
