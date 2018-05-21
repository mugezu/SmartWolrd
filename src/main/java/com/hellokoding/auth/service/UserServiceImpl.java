package com.hellokoding.auth.service;


import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.RoleRepository;
import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(new Long(1)));
        userRepository.save(user);
    }

    public void updateUser(User user) {
        User oldUser = findById(user.getId());
        if (user.getUsername() == "") {
            user.setUsername(oldUser.getUsername());
        }
        if (user.getAddress() == "") {
            user.setAddress(oldUser.getAddress());
        }
        if (user.getEmail() == "") {
            user.setEmail(oldUser.getEmail());
        }
        if (user.getNumber() == "") {
            user.setNumber(oldUser.getNumber());
        }
        if (user.getNumberCard() == "") {
            user.setNumberCard(oldUser.getNumberCard());
        }
        if (user.getRole() == null) {
            user.setRole(oldUser.getRole());
        }
        if (user.getPassword() == "") {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.saveAndFlush(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new Exception("");
        }

        Object obj = auth.getPrincipal();
        String username;

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        User u = userRepository.findByUsername(username);
        if (u == null) {
            throw new AuthenticationException("Сначала надо авторизоваться на сайте");
        } else {
            return u;
        }
    }

    @Override
    public User findById(Long idUser) {
        return userRepository.findOne(idUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> usersFromPage(Integer page, List<User> users, Integer amount) {
        List<User> result = new ArrayList<>();
        try {
            for (int i = (page - 1) * amount; i < page * amount; i++) {
                result.add(users.get(i));
            }
        } finally {
            return result;
        }
    }
}
