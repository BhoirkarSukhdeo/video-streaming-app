package com.neosoft.app.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCategoryResponse {
	
	private Long categoryId;
	private String category;
	private LocalDate expireDate;
	private double cost;
}
