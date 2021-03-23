package de.home_skrobanek.browser.utils;

import java.util.ArrayList;

public class BackgroundTask {

    public static ArrayList<BackgroundTask> tasks = new ArrayList<>();
    private String name;

    public BackgroundTask( String name, Runnable run){
        this.name = name;

        new Thread(run).start();

        tasks.add(this);

        System.out.println("[Background task started]: " + name);
    }
}
