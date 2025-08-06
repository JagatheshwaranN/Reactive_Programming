package org.learn_everyday.chapter4;

import org.learn_everyday.chapter1.subscriber.SubscriberImpl;
import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        produceEarly();
        produceOnDemand();
    }

    private static void produceEarly() {
         var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
                    for (int i = 0; i < 10; i++) {
                        var name = Util.fake().name().firstName();
                        log.info("Generated: {}", name);
                        fluxSink.next(name);
                    }
                    fluxSink.complete();
                })
                .subscribe(subscriber);

        Util.sleep(2);
        subscriber.getSubscription().request(2);
        Util.sleep(2);
        subscriber.getSubscription().request(2);
        Util.sleep(2);
        subscriber.getSubscription().cancel();
    }

    private static void produceOnDemand() {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(stringFluxSink -> {
            stringFluxSink.onRequest(request -> {
                for (int i = 0; i < request && !stringFluxSink.isCancelled(); i++) {
                    var name = Util.fake().name().firstName();
                    log.info("Generated Name: {}", name);
                    stringFluxSink.next(name);
                }
            });
        }).subscribe(subscriber);

        Util.sleep(2);
        subscriber.getSubscription().request(2);
        Util.sleep(2);
        subscriber.getSubscription().request(2);
        Util.sleep(2);
        subscriber.getSubscription().cancel();
    }

}
