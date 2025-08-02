package com.example.AtharamanBackend1.repository;

import com.example.AtharamanBackend1.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideRepository extends JpaRepository<Guide, Long> {

}
