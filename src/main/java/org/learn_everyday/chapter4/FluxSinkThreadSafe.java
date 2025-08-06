package org.learn_everyday.chapter4;

import org.learn_everyday.chapter4.helper.NameGenerator;
import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class FluxSinkThreadSafe {

    private static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafe.class);

    public static void main(String[] args) {
        nonThreadSafeDemo();
        fluxSinkThreadSafeDemo();
    }

    private static void nonThreadSafeDemo() {

        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for(int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for(int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleep(2);
        log.info("List Size: {}", list.size());
    }

    private static void fluxSinkThreadSafeDemo() {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        Flux.create(generator)
                .subscribe(list::add);

        Runnable runnable = () -> {
            for(int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for(int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleep(2);
        log.info("Size of List: {}", list.size());
    }


}
