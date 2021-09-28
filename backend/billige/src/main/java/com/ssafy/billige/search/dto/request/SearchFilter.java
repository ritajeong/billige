package com.ssafy.billige.search.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilter {
	private String category;
	private String keyword;
	private String orderBy;
	private int min;
	private int max;
}
