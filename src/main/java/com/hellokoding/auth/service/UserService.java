package com.hellokoding.auth.service;

import com.hellokoding.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(Long idUser);

    void saveAndFlush(Long id, String username, String password, String passwordConfirm, String email, String number, String address, String number1, String numberCard, Long idRole);
}
