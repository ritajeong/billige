package com.ssafy.billige.item.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ssafy.billige.image.domain.Image;
import com.ssafy.billige.item.dto.request.ItemUpdateRequest;
import com.ssafy.billige.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	private String itemname;

	private String category;

	private String description;
	private int price;
	private String position;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;

	@Enumerated(EnumType.STRING)
	private ActiveStatus isActive = ActiveStatus.Y;

	private int itemSigunguCode;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdTime;

	@LastModifiedDate
	private LocalDateTime modifiedTime;

	@OneToMany(mappedBy = "item")
	private List<Image> images = new ArrayList<>();

	//===CRUD 메소드===//
	public void updateItem(ItemUpdateRequest request) {
		this.category = request.getCategory();
		this.itemname = request.getItemname();
		this.description = request.getDescription();
		this.price = request.getPrice();
	}

	public void activeItem() {
		if (ActiveStatus.N.equals(this.isActive)) {
			this.isActive = ActiveStatus.Y;
		} else {
			this.isActive = ActiveStatus.N;
		}
	}
}
