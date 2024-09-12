package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = { "email" })
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Instant registrationDate;

    public User(String username, String email, String password, Instant registrationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

// Геттеры и сеттеры для всех полей
    }
