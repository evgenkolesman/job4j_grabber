package ru.job4j;

import java.time.LocalDateTime;

/*
 * Парсинг html страниц
 * модель типа данных Post, для получения необходимых данных со страницы
 * @String id
 * @String name
 * @String table
 * @String link
 * @LocalDateTime created
 * @author Kolesnikov Evgeniy (evgeniysanich@mail.ru)
 * @version 1.0
 */
public class Post {

    private final String id;
    private final String name;
    private final String table;
    private final String link;
    private final LocalDateTime created;

    public Post(String id, String name, String table, String link, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.table = table;
        this.link = link;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getTable() {
        return table;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", table='" + table + '\''
                + ", link='" + link + '\''
                + ", created=" + created + '}';
    }
}
