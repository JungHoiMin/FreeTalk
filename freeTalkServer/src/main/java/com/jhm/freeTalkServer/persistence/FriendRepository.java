package com.jhm.freeTalkServer.persistence;

import com.jhm.freeTalkServer.model.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, String> {
    List<FriendEntity> findByUserTel(String userTel);
}
