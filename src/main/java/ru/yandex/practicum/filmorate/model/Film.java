package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.Instant;

/**
 * Film.
 */
@Data
public class Film {
    int id;
    @NotBlank
    String name;
    @Size(max = 200, message = "Длина описания превышает 200 символов.")
    String description;

    Instant releaseDate;
    @Positive
    Duration duration;
}
