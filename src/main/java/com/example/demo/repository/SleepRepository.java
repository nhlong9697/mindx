package com.example.demo.repository;

import com.example.demo.entity.Sleep;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findByUser(User user);
}