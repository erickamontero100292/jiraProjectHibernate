package com.jiraproject.main;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jiraproject.daoimpl.BranchDAOImpl;
import com.jiraproject.interfacedao.AssignationsDAO;
import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.interfacedao.TypeAssignationsDAO;
import com.jiraproject.model.Branch;
import com.jiraproject.model.TypeAssignations;

public class HibernateMain {

	public static void main(String[] args) {
			 generateMenu();
	}

	public static void generateMenu() {

		Scanner valueMenu = new Scanner(System.in);
		int option = -1;
		System.out.println("Introduzca el numero de la opcion que quiere realizar");
		System.out.println("1 - Crear tipo de asignacion");
		System.out.println("2 - Crear branch");
		System.out.println("3 - Crear asignacion");
		option= valueMenu.nextInt();
		optionSwithMenu( option);
	}

	public static void optionSwithMenu(int option) {

		switch (option) {
		case 1:
			createTypeAssignations();
			//System.out.println("Crear tipo de asignacion");
			break;
		case 2:
			createBranch();
			//System.out.println("Crear branch");
			break;
		case 3:
			System.out.println("Crear asignacion");
			break;


		default:
			System.out.println("La opcion no existe");
			break;
		}
	}

	public static void createBranch() {
		Scanner value =   new Scanner(System.in);
		String branchName ;

		System.out.println("Introduzca el nombre del branch");
		branchName = value.nextLine();

		Date date = new Date();
		Branch branch = new Branch();
		branch.setDescription(branchName);
		branch.setDatecreated(new Timestamp(date.getTime()));
		BranchDAO branchDAO = new BranchDAOImpl();
		boolean save = branchDAO.save(branch);

		System.out.println("BRANCH - "+ save+ " - "+branch.toString());

	}

	public static void createTypeAssignations() {
		Scanner value =   new Scanner(System.in);
		String typeAssignationsName ;

		System.out.println("Introduzca el nombre del tipo de asignacion");
		typeAssignationsName = value.nextLine();
		TypeAssignations assignations = new TypeAssignations();
		assignations.setDescription(typeAssignationsName);
		System.out.println("Type Assignations - "+ assignations.toString());

	}

}
