package org.learn_everyday.chapter9;

import org.learn_everyday.reuse.Util;

public class StartWithUseCaseDemo {

    public static void main(String[] args) {

        var nameGenerator = new NameGenerator();

        nameGenerator.generateName()
                .take(2)
                .subscribe(Util.subscriber("Subscriber1"));

        nameGenerator.generateName()
                .take(2)
                .subscribe(Util.subscriber("Subscriber2"));

        nameGenerator.generateName()
                .take(3)
                .subscribe(Util.subscriber("Subscriber3"));
    }

}
