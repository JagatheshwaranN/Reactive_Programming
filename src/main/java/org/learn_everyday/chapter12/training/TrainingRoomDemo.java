package org.learn_everyday.chapter12.training;

import org.learn_everyday.reuse.Util;

public class TrainingRoomDemo {

    public static void main(String[] args) {

        var room = new TrainingRoom("Reactor");
        var sam = new TrainingMember("sam");
        var alex = new TrainingMember("alex");
        var sara = new TrainingMember("sara");

        room.addMember(sam);
        room.addMember(alex);

        sam.says("Hi All..");
        Util.sleep(4);

        alex.says("Hey!");
        sam.says("How are you doing?");
        Util.sleep(4);

        room.addMember(sara);
        sara.says("Hey Guys! Glad to be here..");
    }

}
