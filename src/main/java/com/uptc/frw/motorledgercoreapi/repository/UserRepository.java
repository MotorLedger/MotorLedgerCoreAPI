package com.uptc.frw.motorledgercoreapi.repository;
import com.uptc.frw.motorledgercoreapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
