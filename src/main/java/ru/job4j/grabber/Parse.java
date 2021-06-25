package ru.job4j.grabber;

import ru.job4j.Post;

import java.util.List;

public interface Parse {
    List<ru.job4j.Post> list(String link);

    Post detail(String link);
}
