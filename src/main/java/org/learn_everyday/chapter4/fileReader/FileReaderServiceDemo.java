package org.learn_everyday.chapter4.fileReader;

import org.learn_everyday.reuse.Util;

import java.nio.file.Path;

public class FileReaderServiceDemo {

    static Path path = Path.of("src/main/java/org/learn_everyday/chapter4/fileReader/file/readme.txt");
    static FileReaderService fileReader = new FileReaderServiceImpl();

    public static void main(String[] args) {

        readFullContent();
        System.out.println("**********************************");
        readContentWithTake();
        System.out.println("**********************************");
        readContentWithTakeUntil();
    }

    private static void readFullContent() {
        fileReader.readFile(path)
                .subscribe(Util.subscriber());
    }

    private static void readContentWithTake() {
        fileReader.readFile(path)
                .take(5)
                .subscribe(Util.subscriber());
    }

    private static void readContentWithTakeUntil() {
        fileReader.readFile(path)
                .takeUntil(line -> line.equalsIgnoreCase("line5"))
                .subscribe(Util.subscriber());
    }

}
