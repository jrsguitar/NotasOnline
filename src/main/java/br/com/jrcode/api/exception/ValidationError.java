package br.com.jrcode.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Problem {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError(LocalDateTime timestamp,Integer status, String title,String type,String detail,String userMessage) {
		super(timestamp,status, title,type,detail,userMessage);

	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}

}
