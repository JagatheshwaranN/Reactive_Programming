package org.learn_everyday.chapter12;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class MulticastDirectAllOrNothing {

    private static final Logger log = LoggerFactory.getLogger(MulticastDirectAllOrNothing.class);

    public static void main(String[] args) {
        multicastSinkWithDirectAllOrNothingDemo();
        Util.sleep(30);
    }

    private static void multicastSinkWithDirectAllOrNothingDemo() {
        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().directAllOrNothing();
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Subscriber1"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Subscriber2"));
        for(int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Item: {}, Result: {}", i, result);
        }
    }

}
