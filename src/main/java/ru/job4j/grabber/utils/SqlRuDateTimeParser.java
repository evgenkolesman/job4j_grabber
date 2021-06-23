package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/*
 * Парсинг даты
 * из типа String в тип LocalDate
 * сложности: месяцы из сокращений переводим в цифровое состояние,
 * а так же даты типа "вчера" и "сегодня", необходимо привести к нормальному виду,
 * так же необходим адаптивный подход для сборки так как мы имеем в результате 2 вида поступающих данных, как
 * String [] - из метода split(), который мы адаптируем под нас и метод LocalDate который получаем после
 * часных случаев: "вчера" и "сегодня"...
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */

public class SqlRuDateTimeParser implements DateTimeParser {

    private Map<String, Integer> months =  new HashMap();

    public SqlRuDateTimeParser() {
        months.put("янв", 1);
        months.put("фев", 2);
        months.put("мар", 3);
        months.put("апр", 4);
        months.put("май", 5);
        months.put("июн", 6);
        months.put("июл", 7);
        months.put("авг", 8);
        months.put("сен", 9);
        months.put("окт", 10);
        months.put("ноя", 11);
        months.put("дек", 12);
    }

    public LocalDateTime parse(String dateLine) {
        String[] dateParts = dateLine.split(", ");
        String[] timeParts = dateParts[1].split(":");
        if (dateParts[0].contains("сегодня")) {
            return parseDate(LocalDateTime.now(),
                    timeParts[0],
                    timeParts[1]);
        }
        if (dateParts[0].contains("вчера")) {
            return parseDate(LocalDateTime.now().minusDays(1),
                    timeParts[0],
                    timeParts[1]);
        }
        return parseDate(dateParts);
    }

    private LocalDateTime parseDate(LocalDateTime localDate,
                                           String hours, String minutes) {
        return LocalDateTime.of(localDate.getDayOfYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth(),
                getInt(hours),
                getInt(minutes));
    }

    private LocalDateTime parseDate(String[] dateTime) {
        String[] dateArr = dateTime[0].split(" ");
        String[] timeArr = dateTime[1].split(":");
        return LocalDateTime.of(getInt("20" + dateArr[2]),
                months.get(dateArr[1]),
                getInt(dateArr[0]),
                getInt(timeArr[0]),
                getInt(timeArr[1]));
    }

    private int getInt(String time) {
        /*if (time.matches("//d+")) {
            throw new IllegalArgumentException("Date wrong");
        }*/
        return Integer.parseInt(time);
    }
}
