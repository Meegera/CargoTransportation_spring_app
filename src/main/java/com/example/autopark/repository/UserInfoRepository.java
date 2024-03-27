package com.example.autopark.repository;

import com.example.autopark.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// класс, с помощью которого извлекаются пользователи из UserInfo по username

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
}
