package org.learn_everyday.chapter14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.learn_everyday.reuse.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

public class AssertNextCollectAllTest {

    record Book(int id, String author, String title) {

    }

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.fake().book().author(), Util.fake().book().title()));
    }

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(book -> Assertions.assertEquals(1, book.id()))
                .thenConsumeWhile(book -> Objects.nonNull(book.author))
                .expectComplete()
                .verify();
    }

    @Test
    public void collectAllTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }

}
