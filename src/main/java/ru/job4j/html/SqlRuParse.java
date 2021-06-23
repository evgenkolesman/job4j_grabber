package ru.job4j.html;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Post;
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
        for (int i = 1; i < 6; i++) {
            Document doc = Jsoup.connect(String.format("https://www.sql.ru/forum/job-offers/%s", i)).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                //System.out.println(href.attr("href"));
                String vac = href.text();
                Element href1 = td.parent().child(5);
                String dat = href1.text();
                SqlRuDateTimeParser a = new SqlRuDateTimeParser();
                String url = String.format("https://www.sql.ru/forum/job-offers/%s", i);
                String link = String.format("%s%n%s%n%s%n", url, vac, a.parse(dat));

                /*Post post = GetParse.getParse(url);
                System.out.println(post.toString()); тут не работает*/
                System.out.println(link);
            }
        }
        String url1 = "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
        Post post = GetParse.getParse(url1);
        System.out.println(post.toString());
    }


}



