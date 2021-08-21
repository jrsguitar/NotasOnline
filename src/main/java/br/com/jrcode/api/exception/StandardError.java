package br.com.jrcode.api.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStamp;	
}
