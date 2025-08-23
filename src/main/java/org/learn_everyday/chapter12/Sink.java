package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Sink {

    private static final Logger log = LoggerFactory.getLogger(Sink.class);

    public static void main(String[] args) {
        sinkEmitValue();
    }

    private static void sinkValue() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitValue("Hi");
        mono.subscribe(Util.subscriber());
    }

    private static void sinkEmpty() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitEmpty();
        mono.subscribe(Util.subscriber());
    }

    private static void sinkError() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitError(new RuntimeException("oops"));
        mono.subscribe(Util.subscriber());
    }

    private static void sinkValueWithMultiSub() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitValue("Hi");
        mono.subscribe(Util.subscriber("Sub1"));
        mono.subscribe(Util.subscriber("Sub2"));
    }

    private static void sinkEmitValue() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("Sub1"));
        sink.emitValue("Hi", ((signalType, emitResult) -> {
            log.info("Hi");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));
        sink.emitValue("Hello", ((signalType, emitResult) -> {
            log.info("Hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));
    }

}
