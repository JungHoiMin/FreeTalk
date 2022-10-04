package com.jhm.freeTalkServer.persistence;

import com.jhm.freeTalkServer.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByTel(String tel);
    Boolean existsByTel(String tel);
}
