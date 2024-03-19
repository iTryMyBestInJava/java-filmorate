package ru.yandex.practicum.filmorate.controller;

public class IdGenerator {
    public static Integer filmId = 0;
    public static Integer userId = 0;

    public static Integer generateFilmId() {
        return ++filmId;
    }

    public static Integer generateUserId() {
        return ++userId;
    }
}
