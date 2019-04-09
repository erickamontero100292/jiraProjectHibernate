package com.jiraproject.main;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jiraproject.daoimpl.BranchDAOImpl;
import com.jiraproject.interfacedao.AssignationsDAO;
import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.interfacedao.TypeAssignationsDAO;
import com.jiraproject.menu.Menu;
import com.jiraproject.menu.OptionMenu;
import com.jiraproject.messages.Messages;
import com.jiraproject.model.Branch;
import com.jiraproject.model.TypeAssignations;

public class HibernateMain {

	public static void main(String[] args) {
		Menu.generateMenu();
	}

	public static void createBranch() {
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
			createBranch();
		}

	}

	public static void updateBranch() {
		Scanner value = new Scanner(System.in);
		String branchName;
		String nameNew;

		System.out.println("Introduzca el nombre del branch que quiere modificar");
		branchName = value.nextLine();
		BranchDAO branchDAO = new BranchDAOImpl();
		Branch branchBD = branchDAO.loadByDescription(branchName);
		System.out.println("Introduzca el nuevo nombre del branch");
		nameNew = value.nextLine();
		Date date = new Date();
		branchBD.setDescription(nameNew);
		branchBD.setDatecreated(new Timestamp(date.getTime()));
		boolean update = branchDAO.update(branchBD);
		if (update) {
			System.out.println("Se guardo el branch  - " + branchBD.getDescription());
		} else {
			updateBranch();
		}

	}

	public static void deleteBranch() {
		Scanner value = new Scanner(System.in);
		String branchName;

		System.out.println("Introduzca el nombre del branch a eliminar");
		branchName = value.nextLine();
		BranchDAO branchDAO = new BranchDAOImpl();
		Branch branchBD = branchDAO.loadByDescription(branchName);
		boolean delete = branchDAO.delete(branchBD);

		if (delete) {
			System.out.println("Se elimino el branch  - " + branchName);
		} else {
			deleteBranch();
		}

	}

	public static void loadBranch() {

		BranchDAO branchDAO = new BranchDAOImpl();
		List<Branch> list = branchDAO.loadBrachAll();
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Branch branch = (Branch) iterator.next();
			System.out.println("Nombre Branch: " + branch.getDescription());
		}

		System.out.println("Total de branch creados: " + list.size());

	}

	public static void createTypeAssignations() {
		Scanner value = new Scanner(System.in);
		String typeAssignationsName;

		System.out.println(Messages.MENU_TYPE_ASSIGNATION.getMessage());
		typeAssignationsName = value.nextLine();
		TypeAssignations assignations = new TypeAssignations();
		assignations.setDescription(typeAssignationsName);
		System.out.println("Type Assignations - " + assignations.toString());

	}

	public static void pruebaBranch() {

		BranchDAO branchDAO = new BranchDAOImpl();
//		List <Branch> list = branchDAO.loadBrachAll();
		Branch branch = branchDAO.loadById(5);
		// Iterator iterator = list.iterator();

		/*
		 * while(iterator.hasNext()) { Branch branch =(Branch) iterator.next();
		 * System.out.println("Nombre Branch: " +branch.getDescription()); }
		 */

		System.out.println("Branch pruebaBranch : " + branch.toString());

	}

}
