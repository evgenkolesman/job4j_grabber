package ru.job4j.grabber;

import java.util.List;

public interface Parse {
    List<ru.job4j.grabber.Post> list(String link);

    Post detail(String link);

    List<String> getResources();
}
