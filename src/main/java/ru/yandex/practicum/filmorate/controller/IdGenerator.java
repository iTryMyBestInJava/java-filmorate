package ru.yandex.practicum.filmorate.controller;

public class IdGenerator {
    public static Integer id = 0;

    public static Integer generateId() {
        return ++id;
    }
}
