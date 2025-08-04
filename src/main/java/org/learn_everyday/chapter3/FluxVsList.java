package org.learn_everyday.chapter3;

import org.learn_everyday.chapter3.helper.NameGenerator;
import org.learn_everyday.reuse.Util;

public class FluxVsList {

    public static void main(String[] args) {

        var list = NameGenerator.getNameList(10);
        System.out.println(list);

        NameGenerator.getNamesFlux(10)
                .subscribe(Util.subscriber());

    }
}
