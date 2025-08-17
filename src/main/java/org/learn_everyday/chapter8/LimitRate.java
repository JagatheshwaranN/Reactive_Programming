package org.learn_everyday.chapter8;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class LimitRate {

    private static final Logger log = LoggerFactory.getLogger(LimitRate.class);

    public static void main(String[] args) {


        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("Generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(LimitRate::timeConsumeTask)
                .subscribe(Util.subscriber());
        Util.sleep(60);
    }

    private static int timeConsumeTask(int i) {
        Util.sleep(1);
        return i;
    }

}
