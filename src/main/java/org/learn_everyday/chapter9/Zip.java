package org.learn_everyday.chapter9;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Zip {

    private record Car(String body, String engine, String tyres) {
    }

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTyres())
                .map(car -> new Car(car.getT1(), car.getT2(), car.getT3()))
                .subscribe(Util.subscriber());
        Util.sleep(3);
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(obj -> "Body-" + obj)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(obj -> "Engine-" + obj)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTyres() {
        return Flux.range(1, 10)
                .map(obj -> "Tyres-" + obj)
                .delayElements(Duration.ofMillis(75));
    }

}
