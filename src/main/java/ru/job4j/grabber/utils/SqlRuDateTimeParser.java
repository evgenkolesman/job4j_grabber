package ru.job4j.grabber.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;

import static ru.job4j.quartz.AlertRabbit.init;

public class SqlRuDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        String d = "";
        try {
            init();
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            Elements row = doc.select(".postslisttopic");
            for (Element a : row) {
                Element href1 = a.parent().child(5);
                d = href1.data();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LocalDateTime.parse(d);
    }
}
