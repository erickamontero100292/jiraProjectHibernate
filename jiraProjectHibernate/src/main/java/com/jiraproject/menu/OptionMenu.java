package com.jiraproject.menu;

public enum OptionMenu {
	BRANCH("Rama"),
	ASSIGNATION("Asignacion"),
	TYPE_ASSIGNATION("Tipo de asignacion");

	private OptionMenu(String option) {
		this.option = option;
	}

	public String getOption() {
		return option;
	}

	private String option;
}
