package org.learn_everyday.chapter10.book_service;

import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookOrderService {

    public static void main(String[] args) {

        var allowedGenre = Set.of("Science fiction",
                "Fantasy",
                "Suspense/Thriller");

        bookOrderStream()
                .filter(bookOrder -> allowedGenre.contains(bookOrder.genre()))
                .buffer(Duration.ofSeconds(5))
                .subscribe(Util.subscriber());

        Util.sleep(60);
    }

    private static Flux<BookOrder> bookOrderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(_ -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orders) {
        var revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)
                ));
        return new RevenueReport(LocalTime.now(), revenue);
    }

}
