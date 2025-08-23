package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class SinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(SinkThreadSafety.class);

    public static void main(String[] args) {
//        withoutThreadSafe();
        withThreadSafe();
    }

    private static void withoutThreadSafe() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);
        for(int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }
        Util.sleep(2);
        log.info("List size {}", list.size());
    }

    private static void withThreadSafe() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);
        for(int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, (signalType, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }
        Util.sleep(2);

        log.info("Size of list {}", list.size());
    }

}
