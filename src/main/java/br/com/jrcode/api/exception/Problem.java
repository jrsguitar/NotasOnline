package br.com.jrcode.api.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
@JsonInclude(value = Include.NON_NULL)
@Getter
@Builder
public class Problem implements Serializable{
	private static final long serialVersionUID = 1L;
	private LocalDateTime timestamp;
	private Integer status;
	private String type;
	private String title;
	private String detail;	
	private String userMessage;

		
}
