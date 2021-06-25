package ru.job4j.grabber;

import java.util.List;

public interface Store {

    // Post беру из модели данных расположенных по адресу  ru.job4j.Post
    void save(ru.job4j.Post post);

    List<ru.job4j.Post> getAll();

    ru.job4j.Post findById(String id);

}
