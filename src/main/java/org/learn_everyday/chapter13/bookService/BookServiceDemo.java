package org.learn_everyday.chapter13.bookService;

import org.learn_everyday.reuse.Util;

public class BookServiceDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        for(int i = 0; i < 10; i++) {
            client.getBook()
                    .subscribe(Util.subscriber());
        }
        Util.sleep(5);
    }

}
