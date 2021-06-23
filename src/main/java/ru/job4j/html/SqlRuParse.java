package ru.job4j.html;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;

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
        /*Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");*/
        /*for (int i = 1; i < 6; i++) {
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
                //System.out.println(link);
                String a22 = SqlRuParse.getParse(url);
                //System.out.println(a22);
            }*/

            Document page = Jsoup.connect("https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t").get();
            Element page1 = page.select("table[class=msgTable]").first();
            Element name = page1.select("td[class=messageHeader]").first();
            String id = name.attr("id");
            Element date = page1.select("td[class=msgFooter]").first();
            SqlRuDateTimeParser a = new SqlRuDateTimeParser();
            String date1 = date.text().split("\\[")[0];
            Element context = page1.select("td[class=msgBody]").last();
            String context1 = context.text();
            System.out.println();
        }
    }







    /*public static String getParse(String url) throws IOException {
        String a = "";
        try {
            LocalDateTime b = null;
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select(".msgTable");
            /*String id = doc.id();
            String messageHeader = String.valueOf(row.eachAttr(".msgFooter"));
            String date = row.last().select(".msgFooter").text();
            a = date;*/
            /*for (Element td : row) {
                Element href = td.parent().child(4);
                //System.out.println(href.attr("href"));
                a = href.text();
            }

            //return String.format("%s%s",a, b); }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}*/
