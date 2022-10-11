package com.jhm.freeTalkServer.service;

import com.jhm.freeTalkServer.model.UserEntity;
import com.jhm.freeTalkServer.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(final UserEntity user){
        if (user == null || user.getTel() == null){
            throw new RuntimeException("Invalid arguments");
        }
        final String tel = user.getTel();
        if (userRepository.existsByTel(tel)){
            log.warn("Tel alread exists {}", tel);
            throw new RuntimeException("Tel alread exists");
        }
        userRepository.save(user);
    }

    public UserEntity getByCredentials(final String tel, final String passwd, final PasswordEncoder encoder){
        final UserEntity originalUser = userRepository.findByTel(tel);

        if (originalUser != null && encoder.matches(passwd, originalUser.getPasswd())){
            return originalUser;
        }
        return null;
    }
}
