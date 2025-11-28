package com.kubraevren.repository;

import com.kubraevren.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Login olurken kullanıcıyı bulmak için lazım olacak
    Optional<UserEntity> findByUsername(String username);

    // Kayıt olurken "bu email zaten var mı?" kontrolü için lazım
    Optional<UserEntity> findByEmail(String email);

    // Kayıt olurken username var mı kontrolü için
    boolean existsByUsername(String username);
}