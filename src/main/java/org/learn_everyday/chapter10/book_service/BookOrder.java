package org.learn_everyday.chapter10.book_service;

import org.learn_everyday.reuse.Util;

public record BookOrder(String genre, String title, Integer price) {

    public static BookOrder create() {
        var book = Util.fake().book();
        return new BookOrder(book.genre(), book.title(), Util.fake().random().nextInt(10, 100));
    }

}

