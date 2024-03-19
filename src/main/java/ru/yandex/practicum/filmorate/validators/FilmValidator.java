package ru.yandex.practicum.filmorate.validators;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDateTime;

@Slf4j
public class FilmValidator {
    public static boolean validateFilm(Film film) throws ValidationException {
        if (film.getReleaseDate().isBefore(LocalDateTime.parse("1985-12-28T00:00:00"))) {
            log.warn("Валидация не пройдена, введена неверная дата");
            throw new ValidationException("Неверная дата");
        }
        return true;
    }
}
