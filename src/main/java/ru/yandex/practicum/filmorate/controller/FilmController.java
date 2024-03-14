package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.Instant;
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
    public List<Film> filmList (){

        return new ArrayList<>(films.values());
    }
    @PutMapping
    public Film addFilm (@Valid @RequestBody Film film){
        if (film.getReleaseDate().isBefore(Instant.parse("1985-12-28T00:00:00"))){
            throw new RuntimeException("Неверная дата");
        }
        log.info("Фильм прошел валидацию, щас будем ставить id");
        film.setId(idGenerator.generateId());
        return film;
    }
}
