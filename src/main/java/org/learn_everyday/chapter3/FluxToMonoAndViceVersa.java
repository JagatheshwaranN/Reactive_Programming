package org.learn_everyday.chapter3;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxToMonoAndViceVersa {

    public static void main(String[] args) {

        monoToFlux();
        fluxToMono();
    }

    private static void fluxToMono() {
        System.out.println("********** Flux to Mono - Type 1 **********");
        var flux = Flux.range(1, 5);
        flux.next()
                .subscribe(Util.subscriber());

        System.out.println("********** Flux to Mono - Type 2 **********");
        Mono.from(flux)
                .subscribe(Util.subscriber());

    }

    private static  void monoToFlux() {
        System.out.println("********** Mono to Flux **********");
        for(int i = 1; i < 3; i++) {
            var mono = getUserName(i);
            save(Flux.from(mono));
        }
    }

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Subscriber1");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }
}
