package ru.yandex.practicum.filmorate.validators;

public class ValidationException extends IllegalArgumentException {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
