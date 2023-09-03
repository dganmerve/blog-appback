package com.md.blogapp.service;

import com.md.blogapp.entity.User;
import com.md.blogapp.exception.UserNotFoundException;
import com.md.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Tüm kullanıcıları listeleme
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID ile bir kullanıcı getirme
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Yeni bir kullanıcı oluşturma
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Bir kullanıcıyı güncelleme
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setSurname(updatedUser.getSurname());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("Kullanıcı bulunamadı: " + id);
        }
    }

    // Bir kullanıcıyı silme
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
