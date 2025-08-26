package org.learn_everyday.chapter12.training;

public record TrainingMessage(String sender, String message) {

    private static final String MESSAGE_FORMAT = "[%s -> %s] : %s";

    public String formatMessageDelivery(String receiver) {
        return MESSAGE_FORMAT.formatted(sender, receiver, message);
    }
}
