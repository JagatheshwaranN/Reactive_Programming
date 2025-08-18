package org.learn_everyday.chapter9.client;

import org.learn_everyday.reuse.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public record Product(String productName, String price, String review) {
    }

    public Mono<Product> getProduct(int productId) {
        return Mono.zip(getProductName(productId), getPrice(productId), getReview(productId))
                .map(tuple -> new Product(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    private Mono<String> getProductName(int productId) {
        return get("/demo05/product/" + productId);
    }

    private Mono<String> getPrice(int productId) {
        return get("/demo05/price/" + productId);
    }

    private Mono<String> getReview(int productId) {
        return get("/demo05/review/" + productId);
    }

    private Mono<String> get(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }

}
