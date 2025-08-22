package org.learn_everyday.chapter10.file_writer_service;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class FileWriterDemo {

    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/chapter10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(stringFlux -> FileWriter.create(stringFlux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();

        Util.sleep(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "event-" + i);
    }

}

