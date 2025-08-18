package org.learn_everyday.chapter9;

import org.learn_everyday.chapter9.helper.MyTrip;
import org.learn_everyday.reuse.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class MergeWithUseCaseDemo {

    private static final Logger log = LoggerFactory.getLogger(MergeWithUseCaseDemo.class);

    public static void main(String[] args) {
        MyTrip.getFlights().subscribe(Util.subscriber());
        Util.sleep(3);
    }

}
