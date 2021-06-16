package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/*
 * 1. Quartz [#175122]
 * работа с периодично повторяющимся функционалом
 * расписание, график плюс нам необходимо извлечь данные из rabbit.properties
 * и дать их в интервал
 *
 */
public class AlertRabbit {

    private static Properties prop = new Properties();

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            try (InputStream cl = Rabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
                prop.load(cl);
            }
            int interval = parseInt(prop.getProperty("rabbit.interval"));
            
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException | IOException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }
}
