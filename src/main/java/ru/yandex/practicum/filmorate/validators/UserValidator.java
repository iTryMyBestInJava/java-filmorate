package ru.yandex.practicum.filmorate.validators;

import ru.yandex.practicum.filmorate.model.User;

public class UserValidator {
    public static boolean validateUser(User user) throws ValidationException {
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("Ошибка. Логин содержит пробелы.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return true;
    }
}
