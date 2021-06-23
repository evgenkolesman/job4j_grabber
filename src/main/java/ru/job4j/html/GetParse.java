package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.job4j.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;

/*
 * Парсинг html страниц
 * приводим данные по url "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t"
 * к типу Post
 * @String id
 * @String name
 * @String table
 * @String link
 * @LocalDateTime created
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */
public class GetParse {
    public static Post getParse(String url) throws IOException {
        Document page = Jsoup.connect(url).get();
        Element page1 = page.select("table[class=msgTable]").first();
        Element name = page1.select("td[class=messageHeader]").first();
        String name1 = name.text();
        String id = name.attr("id");
        Element date = page1.select("td[class=msgFooter]").first();
        String date1 = date.text().split(" \\[")[0];
        Element context = page1.select("td[class=msgBody]").last();
        String context1 = context.text();
        SqlRuDateTimeParser dateD = new SqlRuDateTimeParser();
        String link = page1.select(("td[class=msgBody]")).first().text();
        return new Post(id, name1, context1, link, dateD.parse(date1));
    }
}
