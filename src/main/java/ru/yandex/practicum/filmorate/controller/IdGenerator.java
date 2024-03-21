package ru.yandex.practicum.filmorate.controller;

public class IdGenerator {
    private Integer id = 0;

    public Integer generateId() {
        return ++id;
    }
}
