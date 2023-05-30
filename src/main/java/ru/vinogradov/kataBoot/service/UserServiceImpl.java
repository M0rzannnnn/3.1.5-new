package ru.vinogradov.kataBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vinogradov.kataBoot.model.Role;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        Collection<Role> roles = user.getRoles();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User editUser = optionalUser.get();
            editUser.setId(user.getId());
            editUser.setFirstName(user.getFirstName());
            editUser.setLastName(user.getLastName());
            editUser.setAge(user.getAge());
            editUser.setEmail(user.getEmail());
            editUser.setRoles(user.getRoles());

            if (!editUser.getPassword().equals(user.getPassword())) {
                editUser.setPassword(passwordEncoder.encode(user.getPassword()));
            } if(user.getPassword().isEmpty()){
                editUser.setPassword(editUser.getPassword());
            }
            userRepository.save(editUser);
        }
    }
    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
