package ru.yandex.practicum.filmorate.validators;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;

@Slf4j
public class UserValidator {
    public static boolean validateUser(User user) throws ValidationException {
        if (user.getLogin().contains(" ")) {
            log.warn("Юзер не прошел валидацию");
            throw new ValidationException("Ошибка. Логин содержит пробелы.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        return true;
    }
}
