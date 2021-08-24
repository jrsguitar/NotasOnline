package br.com.jrcode.api.exception;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStamp;	
}
