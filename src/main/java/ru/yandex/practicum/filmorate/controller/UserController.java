package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validators.UserValidator;
import ru.yandex.practicum.filmorate.validators.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {
    Map<Integer, User> users = new HashMap<>();

    @GetMapping
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @PutMapping
    public User addUser(@Valid @RequestBody User user) {
        try {
            UserValidator.validateUser(user);
            log.trace("Юзер прошел валидацию.");
            user.setId(IdGenerator.generateId());
            log.trace("Юзеру установлен id: " + user.getId());
        } catch (ValidationException e) {
            log.warn("Юзер не прошел валидацию");
        }
        return user;
    }

    @PostMapping
    public User updateUser(@Valid @RequestBody User user) {
        try {
            UserValidator.validateUser(user);
            if (users.containsKey(user.getId())) {
                users.put(user.getId(), user);
                log.trace("Юзер обновлён");
            } else {
                log.warn("Нет Юзера с таким id: " + user.getId());
            }
        } catch (ValidationException e) {
            log.warn("Обновлённый юзер не прошел валидацию");
        }
        return user;
    }
}