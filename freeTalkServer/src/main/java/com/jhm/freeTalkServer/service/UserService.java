package com.jhm.freeTalkServer.service;

import com.jhm.freeTalkServer.model.UserEntity;
import com.jhm.freeTalkServer.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity user){
        if (user == null || user.getTel() == null){
            throw new RuntimeException("Invalid arguments");
        }
        final String tel = user.getTel();
        if (userRepository.existsByTel(tel)){
            log.warn("Tel alread exists {}", tel);
            throw new RuntimeException("Tel alread exists");
        }
        return userRepository.save(user);
    }
}
