package org.learn_everyday.chapter12.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class TrainingMember {

    private static final Logger log = LoggerFactory.getLogger(TrainingMember.class);

    private final String name;
    private Consumer<String> messageConsumer;

    public TrainingMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void says(String message) {
        this.messageConsumer.accept(message);
    }

    void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    void receive(String message) {
        log.info(message);
    }

}
