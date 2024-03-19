package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validators.FilmValidator;
import ru.yandex.practicum.filmorate.validators.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/films")
@RestController
@Slf4j
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();

    @GetMapping
    public List<Film> getFilmList() {
        return new ArrayList<>(films.values());
    }

    @PutMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        try {
            FilmValidator.validateFilm(film);
            log.trace("Фильм прошел валидацию, щас будем ставить id");
            film.setId(IdGenerator.generateId());
            log.trace("Фильму установлен id: " + film.getId());
            films.put(film.getId(), film);
        } catch (ValidationException e) {
            log.warn("Фильм не прошёл валидацию");
        }
        return film;
    }

    @PostMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        try {
            FilmValidator.validateFilm(film);
            if (films.containsKey(film.getId())) {
                films.put(film.getId(), film);
                log.trace("Фильм обновлён");
            } else {
                log.warn("Нет задачи с таким id: " + film.getId());
            }
        } catch (ValidationException e) {
            log.warn("Фильм не прошел валидацию");
        }
        return film;
    }
}
