package com.jhm.freeTalkServer.controller;

import com.jhm.freeTalkServer.dto.ResponseDTO;
import com.jhm.freeTalkServer.dto.UserDTO;
import com.jhm.freeTalkServer.model.UserEntity;
import com.jhm.freeTalkServer.service.FriendService;
import com.jhm.freeTalkServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> joinMembership(@RequestBody UserDTO userDTO){
        try{
            UserEntity user = UserDTO.userEntity(userDTO);
            UserEntity registeredUser = userService.create(user);
            String msg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok().body(msg);
        } catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
