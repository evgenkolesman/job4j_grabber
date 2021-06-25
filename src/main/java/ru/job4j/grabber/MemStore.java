package ru.job4j.grabber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.job4j.quartz.AlertRabbit.init;

public class MemStore implements Store {
    Connection cn;

    // Post беру из модели данных расположенных по адресу  ru.job4j.Post
    @Override
    public void save(ru.job4j.Post post) {
        try (Connection connection = cn) {
            init();
            try (PreparedStatement st = connection.prepareStatement("insert into grabber(id, name, table, link, created) values(?,?,?,?,?);")) {
                st.setString(1, post.getId());
                st.setString(2, post.getName());
                st.setString(3, post.getTable());
                st.setString(4, post.getLink());
                st.setTimestamp(5, Timestamp.valueOf(post.getCreated()));
                st.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ru.job4j.Post> getAll() {
        List<ru.job4j.Post> posts = new ArrayList<>();
        try (Connection connection = cn) {
            init();
            try (PreparedStatement st = connection.prepareStatement("select*from grabber;")) {
                try (ResultSet resultSet = st.executeQuery()) {
                    while (resultSet.next()) {
                        posts.add(new ru.job4j.Post(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("table"),
                                resultSet.getString("link"),
                                resultSet.getTimestamp("created").toLocalDateTime()
                        ));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public ru.job4j.Post findById(String id) {
        ru.job4j.Post result1 = null;
        try (PreparedStatement statement = cn.prepareStatement("select * from items where id = ?;")) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result1 = new ru.job4j.Post(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("table"),
                            resultSet.getString("link"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result1;
    }
}
