package org.learn_everyday.chapter6;

import org.learn_everyday.chapter4.helper.NameGenerator;
import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

public class FluxCreateIssueFix {


    public static void main(String[] args) {

        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
                flux.subscribe(Util.subscriber("Subscriber1"));
                flux.subscribe(Util.subscriber("Subscriber2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }

}
