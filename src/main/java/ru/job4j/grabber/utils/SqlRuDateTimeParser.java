package ru.job4j.grabber.utils;

import java.time.LocalDateTime;


public class SqlRuDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse);
    }
}
