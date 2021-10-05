package com.ssafy.billige.image.service.Impl;

import org.springframework.stereotype.Service;

import com.ssafy.billige.image.domain.Image;
import com.ssafy.billige.image.repository.ImageRepository;
import com.ssafy.billige.image.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;

	@Override
	public void save(Image image) {
		imageRepository.save(image);
	}
}
