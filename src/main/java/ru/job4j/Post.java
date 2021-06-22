package ru.job4j;

public class Post {

    private final String id;
    private final String messageHeader;
    private final String msgTable;
    private final String link;
    private final String created_data;

    public Post(String id, String messageHeader, String msgTable, String link, String created_data) {
        this.id = id;
        this.messageHeader = messageHeader;
        this.msgTable = msgTable;
        this.link = link;
        this.created_data = created_data;
    }
}