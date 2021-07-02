package ru.job4j.grabber;

import java.util.List;

/*
 * Данный интерфейс дает модель методов для хранения и извлечения данных типа Post
 * реализация методов в PsqlStore
 * Парсинг html страниц
 * модель типа данных Post - использование для парсинга, для получения необходимых данных со страницы
 * @String id - автоматически присваивается в БД, поэтому его не назначаяем в конструкторе
 * @String name
 * @String table
 * @String link
 * @LocalDateTime created
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */
public interface Store {

    // Post беру из модели данных расположенных по адресу  ru.job4j.grabber.Post
    void save(ru.job4j.grabber.Post post);

    List<ru.job4j.grabber.Post> getAll();

    ru.job4j.grabber.Post findById(String id);

    void saveAll(List<Post> post);
}
