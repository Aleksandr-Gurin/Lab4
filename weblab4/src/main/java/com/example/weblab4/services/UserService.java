package com.example.weblab4.services;

import com.example.weblab4.model.User;
import com.example.weblab4.repositories.UserRepository;
import com.example.weblab4.utils.HashUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findByLogin(String login){
        return userRepository.getByLogin(login);
    }

    @Transactional
    public User findByLoginAndPassword(String login, String password){
        return userRepository.getByLoginAndPassword(login, HashUtil.digestPassword(password));
    }

    @Transactional
    public String register(String login, String password){
        try {
            User user = new User(login, HashUtil.digestPassword(password));
            userRepository.save(user);
            return "Success";
        }catch (Exception e){
            return "Error";
        }

    }

    @Transactional
    public String deleteByUser(User user){
        try {
            userRepository.delete(user);
            return "Success";
        }catch (Exception e){
            return "Error";
        }
    }
}
