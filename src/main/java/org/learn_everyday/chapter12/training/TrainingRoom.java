package org.learn_everyday.chapter12.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class TrainingRoom {

    private static final Logger log = LoggerFactory.getLogger(TrainingRoom.class);

    private final String name;

    private final Sinks.Many<TrainingMessage> sink;

    private final Flux<TrainingMessage> flux;

    public TrainingRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void addMember(TrainingMember trainingMember) {
        log.info("{} joined the room {}", trainingMember.getName(), this.name);
        this.subscribeToRoomMessages(trainingMember);
        trainingMember.setMessageConsumer(msg -> postMessage(trainingMember.getName(), msg));

    }

    private void subscribeToRoomMessages(TrainingMember trainingMember) {
        this.flux
                .filter(senderName -> !senderName.sender().equals(trainingMember.getName()))
                .map(tm -> tm.formatMessageDelivery(trainingMember.getName()))
                .subscribe(trainingMember::receive);
    }

    private void postMessage(String sender, String message) {
        this.sink.tryEmitNext(new TrainingMessage(sender, message));
    }
}
