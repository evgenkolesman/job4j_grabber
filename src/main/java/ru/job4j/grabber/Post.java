package ru.job4j.grabber;

import java.time.LocalDateTime;

/*
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

public class Post {

    private String id;
    private String title;
    private String description;
    private String link;
    private LocalDateTime created;

    public Post() {
    }

    public Post(String title, String description, String link, LocalDateTime created) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getTable() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id='" + id + '\''
                + ", name='" + title + '\''
                + ", table='" + description + '\''
                + ", link='" + link + '\''
                + ", created=" + created + '}';
    }
}

