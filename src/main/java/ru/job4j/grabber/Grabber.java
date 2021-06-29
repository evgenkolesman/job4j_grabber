package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.util.Properties;

public class Grabber implements Grab {
    private final Properties properties = new Properties();

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
    }
}
