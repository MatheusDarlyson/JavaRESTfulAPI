package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.repository.userRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final userRepository userRepository;

    public UserServiceImpl(userRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This user ID already exists!");
        }
        return userRepository.save(userToCreate);
    }
}
