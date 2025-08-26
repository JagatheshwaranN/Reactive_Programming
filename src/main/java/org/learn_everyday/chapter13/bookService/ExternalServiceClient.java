package org.learn_everyday.chapter13.bookService;

import org.learn_everyday.reuse.AbstractHttpClient;
import reactor.core.publisher.Mono;


public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getBook() {
        return this.httpClient.get()
                .uri("/demo07/book")
                .responseContent()
                .asString()
                .next();
    }


}
