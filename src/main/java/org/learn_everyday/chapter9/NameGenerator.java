package org.learn_everyday.chapter9;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private final List<String> redis = new ArrayList<>();

    public Flux<String> generateName() {
        return Flux.generate(sink -> {
                    log.info("Generate Name");
                    Util.sleep(1);
                    var name = Util.fake().name().firstName();
                    redis.add(name);
                    sink.next(name);
                })
                .startWith(redis)
                .cast(String.class);
    }

}
