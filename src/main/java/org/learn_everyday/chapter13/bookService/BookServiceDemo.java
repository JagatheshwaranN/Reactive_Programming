package org.learn_everyday.chapter13.bookService;

import org.learn_everyday.reuse.Util;
import reactor.util.context.Context;

public class BookServiceDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        for(int i = 0; i < 10; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "Jose"))
                    .subscribe(Util.subscriber());
            Util.sleep(1);
        }
        Util.sleep(5);
    }

}
