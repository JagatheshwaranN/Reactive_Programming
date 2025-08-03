package org.learn_everyday.chapter2.io;

import reactor.core.publisher.Mono;

public interface FileService {

    public Mono<String> read(String file);

    public Mono<Void> write(String file, String content);

    Mono<Void> delete(String file);
}
