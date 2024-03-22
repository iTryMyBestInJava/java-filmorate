package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validators.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmorateApplicationTests {
	UserController userController;
	FilmController filmController;

	@BeforeEach
	void refresh() {
		userController = new UserController();
		filmController = new FilmController();
	}

	@Test
	void userNormalAddTest() {
		User user = User.builder()
				.email("mail@yandex.ru")
				.login("test")
				.name("Kirill")
				.birthday(LocalDate.now().minusYears(24))
				.build();
		userController.addUser(user);
		assertEquals(user, userController.getUser(user.getId()));
	}

	@Test
	void userAddWithNullLoginShouldFail() {
		User user = User.builder()
				.email("mail@yandex.ru")
				.name("Kirill")
				.birthday(LocalDate.now().minusYears(24))
				.build();
		assertThrows(ValidationException.class, () -> userController.addUser(user));
	}

	@Test
	void userAddWithBlankOrWhiteSpaceLoginShouldFail() {
		User user = User.builder()
				.email("mail@yandex.ru")
				.login("  ")
				.name("Kirill")
				.birthday(LocalDate.now().minusYears(24))
				.build();
		assertThrows(ValidationException.class, () -> userController.addUser(user));

		user.setLogin("Te st");
		assertThrows(ValidationException.class, () -> userController.addUser(user));
	}

	@Test
	void userShouldGetLoginAsNameWhenNameIsNullOrBlank() {
		User user = User.builder()
				.email("mail@yandex.ru")
				.login("test")
				.birthday(LocalDate.now().minusYears(24))
				.build();
		userController.addUser(user);
		assertEquals(user.getLogin(), user.getName());
		User user1 = User.builder()
				.email("mail@yandex.ru")
				.login("test")
				.name("   ")
				.birthday(LocalDate.now().minusYears(24))
				.build();
		userController.addUser(user1);
		assertEquals(user1.getLogin(), user1.getName());
	}

	@Test
	void filmNormalAddTest() {
		Film film = Film.builder()
				.name("Avatar")
				.description("Blue guys fly")
				.releaseDate(LocalDate.now().minusYears(5))
				.duration(180)
				.build();
		filmController.addFilm(film);
		assertEquals(film, filmController.getFilm(film.getId()));
	}

	@Test
	void filmBefore1895ShouldFailAdding() {
		Film film = Film.builder()
				.name("Avatar")
				.description("Blue guys fly")
				.releaseDate(LocalDate.parse("1890-05-20"))
				.duration(180)
				.build();
		assertThrows(ValidationException.class, () -> filmController.addFilm(film));
	}
}
