package com.jiraproject.messages;

public enum Messages {
	NO_FOUND_FILE ("El archivo no se encontro"),
	BRANCH_EXIST("El nombre del branch ya existe ");
	
	private Messages(String message) {
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	
}
