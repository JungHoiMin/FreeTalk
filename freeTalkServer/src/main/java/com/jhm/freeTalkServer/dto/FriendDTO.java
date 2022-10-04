package com.jhm.freeTalkServer.dto;

import com.jhm.freeTalkServer.model.FriendEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO {
    private Long id;
    private String userTel;
    private String friendTel;
    private String roomId;

    public FriendDTO(final FriendEntity entity){
        this.id = entity.getId();
        this.userTel = entity.getUserTel();
        this.friendTel = entity.getFriendTel();
        this.roomId = entity.getRoomId();
    }

    public static FriendEntity friendEntity(final FriendDTO dto){
        return FriendEntity.builder()
                .id(dto.getId())
                .userTel(dto.getUserTel())
                .friendTel(dto.getFriendTel())
                .roomId(dto.getRoomId())
                .build();
    }
}
