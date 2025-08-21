package org.learn_everyday.chapter10;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Window {

    public static void main(String[] args) {
        demo1();
        demo2();
        Util.sleep(60);
    }

    private static void demo1() {
        eventStream()
                .window(5)
                .flatMap(Window::processEvents)
                .subscribe();
    }

    private static void demo2() {
        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(Window::processEvents)
                .subscribe();
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux.doOnNext(_ -> System.out.print("$"))
                .doOnComplete(System.out::println)
                .then();
    }

}
