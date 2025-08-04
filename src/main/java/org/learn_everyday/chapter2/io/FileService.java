package org.learn_everyday.chapter2.io;

import reactor.core.publisher.Mono;

public interface FileService {

    Mono<String> read(String file);

    Mono<Void> write(String file, String content);

    Mono<Void> delete(String file);
}
