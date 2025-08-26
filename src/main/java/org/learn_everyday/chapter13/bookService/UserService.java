package org.learn_everyday.chapter13.bookService;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

    private static final Map<String, String> USER_CATEGORY = Map.of(
            "Jose", "standard",
            "Emmi", "prime"
    );

    static Function<Context, Context> userCategoryContext() {
            return ctx -> ctx.getOrEmpty("user")
                    .filter(USER_CATEGORY::containsKey)
                    .map(USER_CATEGORY::get)
                    .map(category -> ctx.put("category", category))
                    .orElse(ctx);
    }

}
