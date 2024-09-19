package ru.truecasper.firstSpringBoot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.truecasper.firstSpringBoot.models.User;
import ru.truecasper.firstSpringBoot.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void add(User user) {
        userRepository.save(user);
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateUser(User user) {
        Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).update(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
