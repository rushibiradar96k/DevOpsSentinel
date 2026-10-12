package com.devopssentinel;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Log log = new Log();
        Log log1 = new Log();
        Log log2 = new Log();

        log.level = "ERROR";
        log.message = "Database connection failed";

        log1.level = "INFO";
        log1.message = "Application started";

        log2.level = "WARNING";
        log2.message = "Database response slow";

        ArrayList<Log> al = new ArrayList<>();

        al.add(log);
        al.add(log1);
        al.add(log2);

        for (Log l : al) {

            System.out.println("Level : " + l.level);
            System.out.println("Message : " + l.message);
            System.out.println("#######################");

        }

        LogAnalyzer analyzer = new LogAnalyzer();

        analyzer.readLog();

    }
}