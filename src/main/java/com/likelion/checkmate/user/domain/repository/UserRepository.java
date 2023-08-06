package com.likelion.checkmate.user.domain.repository;

import com.likelion.checkmate.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
