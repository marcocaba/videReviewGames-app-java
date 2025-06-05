package com.marcoMario.service;

import com.marcoMario.iService.IUserService;
import com.marcoMario.model.User;
import com.marcoMario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registerUser(String nameUser, String password) {
        boolean exist = false;
        Optional<User> existingUser = userRepository.findByName(nameUser);

        if (existingUser.isEmpty()) {
            User user = new User();
            user.setName(nameUser);
            user.setPsswd(password);
            userRepository.save(user);
            exist = true;
        }
        return exist;
    }
}
