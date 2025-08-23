package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Sinks;

public class UnicastSink {

    public static void main(String[] args) {
        unicastSinkWithMultiSubDemo();
    }

    private static void unicastSinkDemo() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        sink.tryEmitNext("Hi!");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("How things are going?");
        flux.subscribe(Util.subscriber());
    }

    private static void unicastSinkWithMultiSubDemo() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        sink.tryEmitNext("Hi!");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("How things are going?");
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.subscribe(Util.subscriber("Subscriber2"));
    }

}
