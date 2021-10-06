package com.ssafy.billige.image.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.billige.image.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findAllByItem_ItemId(Long itemId);

	@Query(value = "select * from image where item_id = :itemId limit 1", nativeQuery = true)
	Image findAllByItem_ItemIdLimit1(@Param("itemId") Long itemId);
}
