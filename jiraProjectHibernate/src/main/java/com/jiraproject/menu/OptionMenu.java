package com.jiraproject.menu;

public enum OptionMenu {
	BRANCH("Rama",1),
	ASSIGNATION("Asignacion",2),
	TYPE_ASSIGNATION("Tipo de asignacion",3);

	private OptionMenu(String option) {
		this.option = option;
	}

	private OptionMenu(String option, int optionValue) {
		this.option = option;
		this.optionValue = optionValue;
	}

	public String getOption() {
		return option;
	}
	
	public int getOptionValue() {
		return optionValue;
	}
	private String option;
	private int optionValue;
}
