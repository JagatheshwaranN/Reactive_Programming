package org.learn_everyday.chapter4.fileReader;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileReaderService {

    Flux<String> readFile(Path path);
}
