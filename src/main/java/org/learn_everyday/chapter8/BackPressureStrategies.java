package org.learn_everyday.chapter8;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackPressureStrategies {

    private static final Logger log = LoggerFactory.getLogger(BackPressureStrategies.class);

    public static void main(String[] args) {

//        buffer();
//        error();
          drop();
    }

    private static void buffer() {

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
                .onBackpressureBuffer()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(BackPressureStrategies::timeConsumeTask)
                .subscribe(Util.subscriber());
        Util.sleep(60);
    }

    private static void error() {

        var producer = Flux.create(sink -> {
                            for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                                log.info("Generated {}", i);
                                sink.next(i);
                                Util.sleepInMS(Duration.ofMillis(50));
                            }
                            sink.complete();
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .onBackpressureError()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(BackPressureStrategies::timeConsumeTask)
                .subscribe(Util.subscriber());
        Util.sleep(60);
    }

    private static void drop() {

        var producer = Flux.create(sink -> {
                            for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                                log.info("Generate {}", i);
                                sink.next(i);
                                Util.sleepInMS(Duration.ofMillis(50));
                            }
                            sink.complete();
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .onBackpressureBuffer(10)
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(BackPressureStrategies::timeConsumeTask)
                .subscribe(Util.subscriber());
        Util.sleep(60);
    }

    private static int timeConsumeTask(int i) {
        Util.sleep(1);
        return i;
    }

}