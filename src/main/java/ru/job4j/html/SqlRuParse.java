package ru.job4j.html;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 * Парсинг html страниц
 * выводим данные
 * по классу
 * @class .postslisttopic
 * даты, выборка по страницам
 * добавлены методы detail формирование типа Post и list формирует список Post
 * реализует интерфейс Parse
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */

public class SqlRuParse implements Parse {
    public static void main(String[] args) throws Exception {
        List<ru.job4j.grabber.Post> listPost1 = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Document doc = Jsoup.connect(String.format("https://www.sql.ru/forum/job-offers/%s", i)).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                String vac = href.text();
                Element href1 = td.parent().child(5);
                //System.out.println(vac);
                String dat = href1.text();
                SqlRuDateTimeParser a = new SqlRuDateTimeParser();
                String url = String.format("https://www.sql.ru/forum/job-offers/%s", i);
                String link = String.format("%s%n%s%n%s%n", url, vac, a.parse(dat));
                //System.out.println(link); //получение link, данные из прошлого задания */
                SqlRuParse e1 = new SqlRuParse();
                //String url11 = e1.getURL(td);
                listPost1 = e1.list(url);
                //System.out.println(url11);
            }
        }
        for (ru.job4j.grabber.Post rr : listPost1) {
            System.out.println(rr.toString());
        }
        /*String url1 = "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
        Post post = GetParse.getParse(url1);
        SqlRuParse e1 = new SqlRuParse();
        System.out.println(e1.list(url1))*/
    }


    @Override
    public List<ru.job4j.grabber.Post> list(String link) {
        List<ru.job4j.grabber.Post> listPost = new ArrayList<>();
        List<String> listLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            if(doc.select(".postslisttopic").hasText()) {
                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    //Element href = td.child(0);
                    listLinks.add(getURL(td));
                }
            }
            else {
                System.out.println( "Wrong url");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // тут мы получаем url и по нему готовим список постов, с помощью detail
        for (String a : listLinks) {
            listPost.add(detail(a));
        }
        return listPost;
    }

    @Override
    public ru.job4j.grabber.Post detail(String link) {
        //получаем ссылку и с нее выводим модель POST
        try {
            Document page = Jsoup.connect(link).get();
            if(!(Stream.of("table[class=msgTable]", "td[class=messageHeader]", "td[class=msgFooter]").allMatch(s -> page.select(s).isEmpty()))) {
            Element page1 = page.select("table[class=msgTable]").first();
            String name1 = page1.select("td[class=messageHeader]").first().text();
            String date1 = page1.select("td[class=msgFooter]").first().text().split(" \\[")[0];
            String context1 = page1.select("td[class=msgBody]").last().text();
            SqlRuDateTimeParser dateD = new SqlRuDateTimeParser();
            String link1 = page1.select(("td[class=msgBody]")).first().text();
            return new ru.job4j.grabber.Post(name1, context1, link1, dateD.parse(date1));
            }
            else {
                 System.out.println("it`s wrong url, can`t find some details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getURL(Element row) {
        Element href111 = row.select("td[class=postslisttopic]").first().child(0);
        return href111.attr("href");
    }
}



