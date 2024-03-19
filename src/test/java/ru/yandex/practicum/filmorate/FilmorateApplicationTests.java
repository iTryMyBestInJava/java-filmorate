package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
class FilmorateApplicationTests {
	@Test
	void contextLoads() {
		Film film = Film.builder()
				.name("Аватар")
				.releaseDate(LocalDateTime.now().minusDays(2))
				.description("Фильм про синих человечков")
				.duration(Duration.ofMinutes(90))
				.build();
	}

}
