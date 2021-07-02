package ru.job4j.grabber;

import ru.job4j.quartz.AlertRabbit;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
 * Парсинг html страниц
 * модель типа данных Post - использование для парсинга, для получения необходимых данных со страницы
 * @String id - автоматически присваивается в БД, поэтому его не назначаяем в конструкторе
 * @String name
 * @String table
 * @String link
 * Данный класс реализует Интерфейс Store
 * @LocalDateTime created
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */
public class PsqlStore implements Store, AutoCloseable {
    Connection cn;

    public PsqlStore(Properties prop) {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            prop.load(in);
            Class.forName(prop.getProperty("driver"));
            cn = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("username"),
                    prop.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    // Post беру из модели данных расположенных по адресу  ru.job4j.Post
    @Override
    public void save(ru.job4j.grabber.Post post) {
        try (PreparedStatement st = cn.prepareStatement("insert into post(name, text, link, created) values(?,?,?,?);")) {
            st.setString(1, post.getTitle());
            st.setString(2, post.getDiscription());
            st.setString(3, post.getLink());
            st.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            st.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<ru.job4j.grabber.Post> posts = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select*from post;")) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    var a = new ru.job4j.grabber.Post(
                            resultSet.getString("name"),
                            resultSet.getString("text"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    );
                    a.setId(String.valueOf(resultSet.getInt("id")));
                    posts.add(a);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(String id) {
        ru.job4j.grabber.Post result1 = null;
        try (PreparedStatement statement = cn.prepareStatement("select * from post where id = ?;")) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result1 = new ru.job4j.grabber.Post(
                            resultSet.getString("name"),
                            resultSet.getString("table"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    );
                    result1.setId(String.valueOf(resultSet.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result1;
    }

    @Override
    public void saveAll(List<Post> post) {
        for (Post a : post) {
            save(a);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
