package com.amoln.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

	private String message;
	
	private Object data;
}
