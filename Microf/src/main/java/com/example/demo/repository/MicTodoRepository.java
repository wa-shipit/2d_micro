package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MicTodo;

public interface MicTodoRepository extends JpaRepository<MicTodo, Integer> {

}