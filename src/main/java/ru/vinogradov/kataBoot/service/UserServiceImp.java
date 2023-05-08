package ru.vinogradov.kataBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vinogradov.kataBoot.DAO.UserDAO;
import ru.vinogradov.kataBoot.model.User;


import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void add (User user) {
        userDAO.add(user);
    }

    @Transactional
    public void update (Long id, User updatedUser) {
        userDAO.update(id, updatedUser);
    }

    @Transactional
    public void delete (Long id) {
        userDAO.delete(id);
    }

    @Transactional
    public List<User> getAll () {
        return userDAO.getAll();
    }

    @Transactional
    public User show (Long id) {
        return userDAO.show(id);
    }

}
