package com.jiraproject.messages;

public enum Messages {
	NO_FOUND_FILE ("El archivo no se encontro"),
	BRANCH_EXIST("El nombre del branch ya existe "),
	TYPE_ASIGNATION_EXIST("La descripcion de la asignacion ya existe "),
	MENU_TYPE_ASSIGNATION("Introduzca el nombre del tipo de asignacion");
	
	private Messages(String message) {
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	
}
