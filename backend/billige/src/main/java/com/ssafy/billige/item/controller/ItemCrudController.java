package com.ssafy.billige.item.controller;

import static com.ssafy.billige.utils.StringUtils.*;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.billige.image.domain.Image;
import com.ssafy.billige.image.service.ImageService;
import com.ssafy.billige.item.domain.Item;
import com.ssafy.billige.item.dto.request.ItemRegistryRequest;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;
import com.ssafy.billige.item.repository.ItemRepository;
import com.ssafy.billige.item.service.ItemCrudService;
import com.ssafy.billige.utils.S3UploadUtils;
import com.ssafy.billige.utils.TokenUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ITEM)
@RequiredArgsConstructor
public class ItemCrudController {

	private final ItemCrudService itemCrudService;
	private final ImageService imageService;
	private final S3UploadUtils s3UploadUtils;

	@PostMapping
	public ResponseEntity<?> createItem(@RequestHeader(AUTH_HEADER) String token
		, @RequestParam("itemname") String itemname
		, @RequestParam("category") String category
		, @RequestParam("description") String description
		, @RequestParam("price") int price
		, @RequestParam("position") String position
		, @RequestParam("images") List<MultipartFile> images) {

		ItemRegistryRequest itemRegistryRequest = ItemRegistryRequest.builder()
			.uid(TokenUtils.getUidFromToken(token))
			.itemSigunguCode(TokenUtils.getSigunguCodeFromToken(token))
			.itemname(itemname)
			.category(category)
			.description(description)
			.price(price)
			.position(position)
			.build();

		Item item = itemCrudService.saveItem(itemRegistryRequest);
		// logger.info(itemname);
		// Item item = itemRepository.findById(1L).get();
		for (MultipartFile image : images) {
			try {
				String src = s3UploadUtils.upload(image, "item", itemname);
				Image img = new Image(item, src);
				imageService.save(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok().body(SUCCESS);
	}

	@PutMapping
	public ResponseEntity<?> itemUpdate(@RequestBody ItemUpdateRequest itemUpdateRequest,
		@RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		itemCrudService.itemUpdate(itemUpdateRequest, uid);
		return ResponseEntity.ok().body(SUCCESS);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<?> removeItem(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		itemCrudService.removeItem(itemId, uid);
		return ResponseEntity.ok().body(SUCCESS);
	}

	@PutMapping("/active/{itemId}")
	public ResponseEntity<?> activeItem(@PathVariable("itemId") Long itemId, @RequestHeader(AUTH_HEADER) String token) {
		Long uid = TokenUtils.getUidFromToken(token);
		itemCrudService.activeItem(itemId, uid);
		return ResponseEntity.ok().body(SUCCESS);
	}
}
