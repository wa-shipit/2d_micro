package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Practices;

public interface PracticesRepository extends JpaRepository<Practices, Integer> {

}