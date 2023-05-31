package ru.vinogradov.kataBoot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.repositories.UserRepository;
import ru.vinogradov.kataBoot.security.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserDetailsImpl(user.get());
    }
}
