package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Long, User> users = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public Map<Long, User> getUsers() {
        return users;
    }

    @PostMapping
    public User addUser(@RequestBody User newUser) {
        if (newUser.getEmail() == null) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }
        for (User user : users.values()) {
            if (user.getEmail().equals(newUser.getEmail())) {
                throw new DuplicatedDataException("Этот имейл уже используется");
            }
        }
        long id = getNextId();
        newUser.setId(id);
        newUser.setRegistrationDate(Instant.now());
        users.put(id, newUser);
        return newUser;
    }

    @PutMapping
    public User updateUser(@RequestBody User updatedUser) {
        if (updatedUser.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        for (User user : users.values()) {
            if (!user.getId().equals(updatedUser.getId()) && user.getEmail() != null && user.getEmail().equals(updatedUser.getEmail())) {
                throw new DuplicatedDataException("Этот имейл уже используется");
            }
        }
        User userToUpdate = users.get(updatedUser.getId());
        if (updatedUser.getEmail() != null) {
            userToUpdate.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getUsername() != null) {
            userToUpdate.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null) {
            userToUpdate.setPassword(updatedUser.getPassword());
        }
        return userToUpdate;
    }

    private long getNextId() {
        return idCounter++;
    }
}
