package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Esptran;

public interface EsptranRepository extends JpaRepository<Esptran, Integer> {

	List<Esptran> findByJpnLike(String jpn);

}