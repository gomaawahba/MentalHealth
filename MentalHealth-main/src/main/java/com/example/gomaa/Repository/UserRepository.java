package com.example.gomaa.Repository;

import com.example.gomaa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findById(Long id);

    Optional<Users> findByResetCode(String resetCode);


    Users findUsersByEmail(String email);
}
