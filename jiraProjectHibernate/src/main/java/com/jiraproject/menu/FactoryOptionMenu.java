package com.jiraproject.menu;


public  class FactoryOptionMenu {
	public static String BRANCH = "Rama";

	public static OptionDetailMenu getOptionDetail(String option) {
		OptionDetailMenu detailMenu = null;
		
		switch (option) {
		case "Rama" :
			detailMenu = new OptionBranch();
			break;
		case "Asignacion" :
			detailMenu = new OptionBranch();
			break;

		default:
			break;
		}
		return detailMenu;
	}

}
