package com.ssafy.billige.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.billige.image.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
