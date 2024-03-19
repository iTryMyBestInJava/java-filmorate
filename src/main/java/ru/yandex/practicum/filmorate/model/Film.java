package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;


/**
 * Film.
 */
@Data
@Builder
public class Film {
    int id;
    LocalDate releaseDate;
    @NotBlank
    String name;
    @Size(max = 200, message = "Длина описания превышает 200 символов.")
    String description;
    @Positive
    int duration;
}
