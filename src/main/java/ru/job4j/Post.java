package ru.job4j;

import java.time.LocalDateTime;

public class Post {

    private final String id;
    private final String messageHeader;
    private final String msgTable;
    private final String link;
    private final LocalDateTime created;

    public Post(String id, String messageHeader, String msgTable, String link, LocalDateTime created) {
        this.id = id;
        this.messageHeader = messageHeader;
        this.msgTable = msgTable;
        this.link = link;
        this.created = created;
    }
}
