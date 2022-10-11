package com.jhm.freeTalkServer.controller;

import com.jhm.freeTalkServer.dto.ResponseDTO;
import com.jhm.freeTalkServer.dto.UserDTO;
import com.jhm.freeTalkServer.model.UserEntity;
import com.jhm.freeTalkServer.security.TokenProvider;
import com.jhm.freeTalkServer.service.FriendService;
import com.jhm.freeTalkServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> joinMembership(@RequestBody UserDTO userDTO){
        try{
            UserEntity user = UserEntity.builder()
                    .tel(userDTO.getTel())
                    .passwd(passwordEncoder.encode(userDTO.getPasswd()))
                    .name(userDTO.getName())
                    .build();
            userService.create(user);
            String msg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok().body(msg);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        UserEntity user = userService.getByCredentials(userDTO.getTel(), userDTO.getPasswd(), passwordEncoder);
        if (user != null){
            final String token = tokenProvider.create(user);
            return ResponseEntity.ok().body(token);
        }else{
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .error("Login failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
