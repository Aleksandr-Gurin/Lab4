package com.example.weblab4.repositories;

import com.example.weblab4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);

}
