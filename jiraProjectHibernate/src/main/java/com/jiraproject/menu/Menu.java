package com.jiraproject.menu;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import com.jiraproject.daoimpl.BranchDAOImpl;
import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.model.Branch;
import com.jiraproject.processfile.ExcelReader;

import javax.swing.*;

/**
 * @author Ericka Montero
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
        descriptionMainMenu();
        int option = Menu.getOptionMenu();
        selectOptionMainMenu(option);
    }

    public static int getOptionMenu() {
        Scanner valueMenu = new Scanner(System.in);
        int option = -1;
        option = valueMenu.nextInt();
        return option;
    }

    public static void descriptionMainMenu() {
        System.out.println("Introduzca el numero de la opcion que quiere realizar");
        System.out.println("1 - Insercion masiva");
        System.out.println("2 - Insercion manual");
        System.out.println("3 - Salir");
    }

    private static void selectOptionMainMenu(int option) {

        switch (option) {
            case 1:
                //TODO CREATE METHOD GENERATE WINDOWS
                WindowFile windowFile = new WindowFile();
                int seleccion = windowFile.selectionFile();
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = windowFile.getSelectedFile();
                    boolean stateReadFile = ExcelReader.readFileExcel(selectedFile);
                    if(stateReadFile){
                        System.out.println("Importacion exitosa");
                        System.exit(0);
                    }else{
                        System.out.println("Formato de archivo no valido");
                        System.exit(0);
                    }

                }
                break;
            case 2:
                descriptionManualInsertionMenu();
                break;
            case 3:
                System.exit(0);
                break;

            default:
                Menu.generateMenuOptionNoExist();
                Menu.generateMenu();
                break;
        }

    }

    public static void optionsManualInsertions(int option) {

        switch (option) {
            case 1:
                generateDetailOptionMenu(OptionMenu.TYPE_ASSIGNATION.getOption());
                break;
            case 2:
                generateDetailOptionMenu(OptionMenu.BRANCH.getOption());
                break;
            case 3:
                generateDetailOptionMenu(OptionMenu.ASSIGNATION.getOption());
                break;
            case 4:
                System.exit(0);
                break;

            default:
                Menu.generateMenuOptionNoExist();
                break;
        }
    }


    public static void descriptionManualInsertionMenu() {
        System.out.println("Introduzca el numero de la opcion que quiere realizar");
        System.out.println("1 - Menu tipo de asignacion");
        System.out.println("2 - Menu branch");
        System.out.println("3 - Menu asignacion");
        System.out.println("4 - Salir");
    }


    public static void descriptionDetailOptionMenu(String option) {
        System.out.println("Introduzca el numero de la opcion que quiere realizar");
        System.out.println("1 - Crear " + option);
        System.out.println("2 - Actualizar " + option);
        System.out.println("3 - Eliminar " + option);
        System.out.println("4 - Listar " + option);
        System.out.println("5 - Regresar ");
    }

    public static void generateMenuOptionNoExist() {
        System.out.println("La opcion no existe");
    }


    public static void generateDetailOptionMenu(String optionName) {
        descriptionDetailOptionMenu(optionName);
        int option = getOptionMenu();
        optionSpecificDetailMenu(option, optionName);
    }


    public static void optionSpecificDetailMenu(int option, String optionName) {

        switch (option) {
            case 1:
                OptionDetailMenu opyi = FactoryOptionMenu.getOptionDetail(optionName);
                opyi.create();
                create();
                generateMenu();
                break;
            case 2:

                generateMenu();
                break;
            case 3:
                generateMenu();
                break;
            case 4:
                generateMenu();
                break;

            default:
                generateMenu();

                break;
        }
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
