package com.jhm.freeTalkServer.dto;

import com.jhm.freeTalkServer.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String tel;
    private String passwd;
    private String name;

    public UserDTO(final UserEntity entity){
        this.tel = entity.getTel();
        this.passwd = entity.getPasswd();
        this.name = entity.getName();
    }

    public static UserEntity userEntity(final UserDTO dto){
        return UserEntity.builder()
                .tel(dto.getTel())
                .name(dto.getName())
                .build();
    }
}
