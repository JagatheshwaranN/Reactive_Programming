package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class MulticastDirectBestEffort {

    private static final Logger log = LoggerFactory.getLogger(MulticastDirectBestEffort.class);

    public static void main(String[] args) {
        multicastSinkWithLateCustomerImpactDemo();
        multicastSinkWithDirectBestEffortDemo();
        multicastSinkWithLateSubscriberFixDemo();
        Util.sleep(30);
    }

    private static void multicastSinkWithLateCustomerImpactDemo() {
        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Subscriber2"));
        for(int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Item: {}, Result: {}", i, result);
        }
    }

    private static void multicastSinkWithDirectBestEffortDemo() {
        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().directBestEffort();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Subscriber2"));
        for(int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Items: {}, Result: {}", i, result);
        }
    }

    private static void multicastSinkWithLateSubscriberFixDemo() {
        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().directBestEffort();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Subscriber2"));
        for(int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Items: {}, Results: {}", i, result);
        }
    }

}
