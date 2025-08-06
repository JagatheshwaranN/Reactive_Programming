package org.learn_everyday.chapter4;

import org.learn_everyday.chapter4.helper.NameGenerator;
import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxCreateRefactor {


    public static void main(String[] args) {

        var generator = new NameGenerator();
        Flux.create(generator)
                .subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}
