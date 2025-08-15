package org.learn_everyday.chapter7;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Parallel {

    private static final Logger log = LoggerFactory.getLogger(Parallel.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel(2)
                .runOn(Schedulers.parallel())
                .map(Parallel::process)
                .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleep(10);

    }

    private static int process(int i) {
        log.info("Time consuming task {}", i);
        Util.sleep(1);
        return i * 2;
    }

}
