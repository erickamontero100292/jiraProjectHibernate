package com.jiraproject.main;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jiraproject.dao.BranchDAO;
import com.jiraproject.model.Branch;

public class SpringHibernateMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		
//		BranchDAO branchDAO = context.getBean(BranchDAO.class);
//		
//		Branch branch = new Branch();
//		branch.setDescription("PRUEBA");
//		
//		branchDAO.save(branch);
//		System.out.println("BRANCH - "+ branch.toString());
//		context.close();
		 generateMenu();
	}
	
	public static void generateMenu() {

		Scanner valueMenu = new Scanner(System.in);
		int option = -1;
		System.out.println("Introduzca el numero de la opcion que quiere realizar");
		System.out.println("1 - Crear branch");
		option= valueMenu.nextInt();
		optionSwithMenu( option);
	}
	
	public static void optionSwithMenu(int option) {
		
		switch (option) {
		case 1:
			createBranch();
			break;

		default:
			break;
		}
	}
	
	public static void createBranch() {
		Scanner value =   new Scanner(System.in);
		String branchName ;
		
		System.out.println("Introduzca el nombre del branch");
		branchName = value.nextLine();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		BranchDAO branchDAO = context.getBean(BranchDAO.class);
		
		Branch branch = new Branch();
		branch.setDescription(branchName);
		
		branchDAO.save(branch);
		System.out.println("BRANCH - "+ branch.toString());
		context.close();

	}

}
