package ru.job4j.html;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

/*
 * Парсинг html страниц
 * выводим данные
 * по классу
 * @class .postslisttopic
 * даты, выборка по страницам
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");

        for (int i = 1; i < 6; i++) {
            for (Element td : row) {
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                String vac = href.text();
                Element href1 = td.parent().child(5);
                String dat = href1.text();
                SqlRuDateTimeParser a = new SqlRuDateTimeParser();
                String url = "https://www.sql.ru/forum/job-offers";
                String link = String.format("%s/%d%n%s%n%s%n", url, i, vac, a.parse(dat));
                System.out.println(link);
            }
        }
    }
}
