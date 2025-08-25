package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Sinks;

public class MulticastSink {

    public static void main(String[] args) {
        multicastSinkDemo();
        multicastSinkWarmUpDemo();
    }

    private static void multicastSinkDemo() {
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.subscribe(Util.subscriber("Subscriber2"));
        sink.tryEmitNext("Hi!");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("How things are going?");
        Util.sleep(2);
        flux.subscribe(Util.subscriber("Subscriber3"));
        sink.tryEmitNext("New Message");
    }

    private static void multicastSinkWarmUpDemo() {
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        sink.tryEmitNext("Hi!");
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("How things are going?");
        Util.sleep(2);
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.subscribe(Util.subscriber("Subscriber2"));
        flux.subscribe(Util.subscriber("Subscriber3"));
        sink.tryEmitNext("New Message");
    }

}
