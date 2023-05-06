package com.example.demo.repository;

import com.example.demo.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterRepository extends JpaRepository<Water, Long> {

}
