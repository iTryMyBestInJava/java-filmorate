package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validators.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {
    private Map<Integer, User> users = new HashMap<>();
    private final IdGenerator idGenerator = new IdGenerator();

    @GetMapping
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        UserValidator.validateUser(user);
        log.debug("Юзер прошел валидацию.");
        user.setId(idGenerator.generateId());
        log.debug("Юзеру установлен id: {}", user.getId());
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        UserValidator.validateUser(user);
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.debug("Юзер c id: {} обновлён", user.getId());
        } else {
            log.warn("Нет Юзера с таким id: {}", user.getId());
            throw new RuntimeException("Ошибка обновления Юзера");
        }
        return user;
    }

    public User getUser(int id) {
        return users.get(id);
    }
}
