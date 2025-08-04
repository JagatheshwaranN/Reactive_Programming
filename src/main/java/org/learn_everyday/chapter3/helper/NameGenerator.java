package org.learn_everyday.chapter3.helper;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static String generateName() {
        Util.sleep(1);
        return Util.fake().name().firstName();
    }

    public static List<String> getNameList(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> generateName())
                .toList();
    }

    public static Flux<String> getNamesFlux(int count) {
        return Flux.range(1, count)
                .map(i -> generateName());
    }

}
