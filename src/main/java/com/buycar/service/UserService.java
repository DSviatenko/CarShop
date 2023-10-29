package com.buycar.service;

import com.buycar.entity.Car;
import com.buycar.entity.User;
import com.buycar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.buycar.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return checkNotFoundWithId(userRepository.findById(id), id);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<Car> getUserCars(Long id) {
        User user = checkNotFoundWithId(userRepository.findById(id), id);

        if (!user.getCarList().isEmpty()) {
            return user.getCarList();
        } else {
            return null;
        }
    }

    @Transactional
    public User updateUser(User updatedUser, Long id) {
        User user = checkNotFoundWithId(userRepository.findById(id), id);

        user.setName(updatedUser.getName());
        user.setRole(updatedUser.getRole());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        userRepository.save(user);

        return checkNotFoundWithId(userRepository.findById(id), id);
    }

    @Transactional
    public void deleteUser(Long id) {
        checkNotFoundWithId(userRepository.findById(id), id);
        userRepository.deleteById(id);
    }
}
