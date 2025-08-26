package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Replay {

    public static void main(String[] args) {
        replayDemo();
        replayWithLimitDemo();
        replayWithLimitDurationDemo();

    }


    private static void replayDemo() {
        var sink = Sinks.many().replay().all();
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

    private static void replayWithLimitDemo() {
        var sink = Sinks.many().replay().limit(1);
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

    private static void replayWithLimitDurationDemo() {
        var sink = Sinks.many().replay().limit(Duration.ofSeconds(3));
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

}
