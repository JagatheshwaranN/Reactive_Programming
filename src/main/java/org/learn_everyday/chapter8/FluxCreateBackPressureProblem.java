package org.learn_everyday.chapter8;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxCreateBackPressureProblem {

    private static final Logger log = LoggerFactory.getLogger(FluxCreateBackPressureProblem.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        var producer = Flux.create(sink -> {
                            for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                                log.info("Generating {}", i);
                                sink.next(i);
                                Util.sleepInMS(Duration.ofMillis(50));
                            }
                            sink.complete();
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(FluxCreateBackPressureProblem::timeConsumeTask)
                .subscribe(Util.subscriber());
        Util.sleep(60);
    }

    private static int timeConsumeTask(int i) {
        Util.sleep(1);
        return i;
    }

}