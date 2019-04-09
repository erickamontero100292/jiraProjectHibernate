package com.jiraproject.menu;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import com.jiraproject.daoimpl.BranchDAOImpl;
import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.model.Branch;

/**
 * @author Ericka Montero
 *
 */
public class Menu {
	
	private String option;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public static void generateMenu() {
		generateMainMenu();
		int option = Menu.getOptionMenu();
		generateSwithMenu(option);
	}
	
	
	public static void generateMainMenu() {
		System.out.println("Introduzca el numero de la opcion que quiere realizar");
		System.out.println("1 - Menu tipo de asignacion");
		System.out.println("2 - Menu branch");
		System.out.println("3 - Menu asignacion");
		System.out.println("4 - Salir");
	}
	
	public static void generateOptionMenu(String option) {
		System.out.println("Introduzca el numero de la opcion que quiere realizar");
		System.out.println("1 - Crear "+ option);
		System.out.println("2 - Actualizar " + option);
		System.out.println("3 - Eliminar "+ option);
		System.out.println("4 - Listar " + option);
		System.out.println("5 - Regresar " );
	}
	
	public static void generateMenuOptionNoExist() {
		System.out.println("La opcion no existe");
	}
	
	public static void generateSwithMenu(int option) {

		switch (option) {
		case 1:
			generateMenuSpecific(OptionMenu.TYPE_ASSIGNATION.getOption());
			break;
		case 2:
			generateMenuSpecific(OptionMenu.BRANCH.getOption());
			
			break;
		case 3:
			generateMenuSpecific(OptionMenu.ASSIGNATION.getOption());
			break;
		case 4:
			System.exit(0);
			break;

		default:
			Menu.generateMenuOptionNoExist();
			break;
		}
	}
	
	public static void generateMenuSpecific(String optionName) {
		generateOptionMenu(optionName);
		int option = getOptionMenu();
		optionSwithMenuBranch(option);
	}
	
	public static int getOptionMenu() {
		Scanner valueMenu = new Scanner(System.in);
		int option = -1;
		option = valueMenu.nextInt();
		return option;
	}
	
	public static void optionSwithMenuBranch(int option) {

		switch (option) {
		case 1:
			create();
			generateMenu();
			break;
		case 2:
			update();
			generateMenu();
			break;
		case 3:
			delete();
			generateMenu();
			break;
		case 4:
			load();
			generateMenu();
			break;

		default:
			generateMenu();
			
			break;
		}
	}

	private static void load() {
		// TODO Auto-generated method stub
		
	}

	private static void delete() {
		// TODO Auto-generated method stub
		
	}

	private static void update() {
		// TODO Auto-generated method stub
		
	}

	private static void create() {
		// TODO Auto-generated method stub
		Scanner value = new Scanner(System.in);
		String branchName;
		System.out.println("Introduzca el nombre del branch");
		branchName = value.nextLine();

		Date date = new Date();
		Branch branch = new Branch();
		branch.setDescription(branchName);
		branch.setDatecreated(new Timestamp(date.getTime()));
		BranchDAO branchDAO = new BranchDAOImpl();
		boolean save = branchDAO.save(branch);
		if (save) {
			System.out.println("Se guardo el branch  - " + branch.getDescription());
		} else {
			create();
		}


		
	}


}
