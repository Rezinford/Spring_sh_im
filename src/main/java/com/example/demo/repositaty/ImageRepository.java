package com.example.demo.repositaty;

import com.example.demo.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByPlayerId(Long id);
    Optional<ImageModel> findByPostId(Long id);

}
