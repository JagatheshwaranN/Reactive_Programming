package org.learn_everyday.chapter2;

import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main(String[] args) {

        var list = List.of(1, 2, 3, 4, 5);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());
    }


    private static int sum(List<Integer> list) {
        return list.stream().mapToInt(num -> num).sum();
    }
}
