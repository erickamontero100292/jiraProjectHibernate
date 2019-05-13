package com.jiraproject.processfile;

import com.jiraproject.daoimpl.AssignationsDAOImpl;
import com.jiraproject.interfacedao.AssignationsDAO;
import com.jiraproject.model.Assignations;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.jiraproject.daoimpl.BranchDAOImpl;
import com.jiraproject.daoimpl.TypeAssignationsDAOImpl;
import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.interfacedao.TypeAssignationsDAO;
import com.jiraproject.messages.Messages;
import com.jiraproject.model.Branch;
import com.jiraproject.model.TypeAssignations;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

public class ExcelReader {

    public static final String XLSX_FILE_PATH = "C:\\Users\\Ericka Montero\\Desktop\\historiasJira.xlsx";
    public static final String SHEET_BRANCH = "branch";
    public static final String SHEET_ASSIGNATIONS = "asignations";
    public static final String SHEET_TYPE_ASSIGNATIONS = "asignationType";
    public static final BranchDAO branchDAO = new BranchDAOImpl();
    public static final TypeAssignationsDAO typeAssignationsDAO = new TypeAssignationsDAOImpl();
    public static final AssignationsDAO assignationsDAO = new AssignationsDAOImpl();
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    public static final String VALIDATE_EXTENSION = "xlsx";

    //	public static void main(String[] args) {
    public static boolean readFileExcel(File file) {
        boolean validateRead = false;
        try {
            if (validateExtensionFile(file.getName())) {
                Workbook workbook = readFile(file);
                processInformation(workbook, SHEET_BRANCH);
                System.out.println("---------------------------------------");
                processInformation(workbook, SHEET_TYPE_ASSIGNATIONS);
                System.out.println("---------------------------------------");
                processInformation(workbook, SHEET_ASSIGNATIONS);
                validateRead = true;
            }
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println(Messages.NO_FOUND_FILE.getMessage());
        }
        return validateRead;
    }

    private static boolean validateExtensionFile(String fileName) {
        boolean validate = false;
        if (fileName.endsWith(VALIDATE_EXTENSION)) {
            validate = true;
        }
        return validate;
    }

    public static void processInformation(Workbook workbook, String nameSheet) throws IOException {
        // TODO Auto-generated method stub
        Sheet sheet = workbook.getSheet(nameSheet);// OBTENGO LA HOJA DEL LIBRO
        switch (nameSheet) {
            case SHEET_BRANCH:
                System.out.println("SHEET_BRANCH");
                extractData(sheet);
                break;
            case SHEET_TYPE_ASSIGNATIONS:
                System.out.println("SHEET_TYPE_ASSIGNATIONS");
                extractData(sheet);
                break;
            case SHEET_ASSIGNATIONS:
                extractData(sheet);
                break;
            default:
                break;
        }
        workbook.close();

    }

    public static Workbook readFile(File file) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return workbook;

    }

    public static String getPathComplete(String path, String fileName) {
        StringBuilder pathAndFile = new StringBuilder();
        pathAndFile.append(path).append(FILE_SEPARATOR).append(fileName);
        return pathAndFile.toString();
    }

    public static void extractData(Sheet sheet) throws IOException {


        Iterator<Row> rowIterator = sheet.rowIterator(); // OBTENGO LAS FILAS DE LA HOJA
        int head = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); // OBTENGO UNA FILA EN ESPECIFICO
            if (row.getRowNum() != 0) {
                transformationData(row, sheet.getSheetName());

            }
        }

    }

    private static void transformationData(Row row, String nameSheet) {
        String cellValue = null;
        switch (nameSheet) {
            case SHEET_BRANCH:
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                cellValue = getCellValue(row);
                Branch branch = new Branch(cellValue, timestamp);
                loadData(branch);
                break;
            case SHEET_TYPE_ASSIGNATIONS:
                cellValue = getCellValue(row);
                TypeAssignations typeAssignations = new TypeAssignations(cellValue);
                loadData(typeAssignations);
                break;
            case SHEET_ASSIGNATIONS:
                Assignations assignations = new Assignations();
                assignations = (Assignations) getCellValue(row, assignations);
                loadData(assignations);
                break;
            default:
                break;
        }
    }

    private static String getCellValue(Row row) {
        String cellValue = "";
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Cell> cellIterator = row.cellIterator(); // OBTENGO LAS CELDAS DE ESA FILA
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            cellValue = dataFormatter.formatCellValue(cell);
        }
        return cellValue;
    }

    private static Object getCellValue(Row row, Object model) {
        String cellValue = "";
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Cell> cellIterator = row.cellIterator(); // OBTENGO LAS CELDAS DE ESA FILA
        int i = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (model instanceof Assignations) {
                model = (Assignations) model;
                TypeAssignationsDAO assignationsDAO = new TypeAssignationsDAOImpl();
                cellValue = dataFormatter.formatCellValue(cell);
                if (i == 0) {
                    TypeAssignations typeAssignations = assignationsDAO.loadByDescription(cellValue);
                    ((Assignations) model).setTypeAssignations(typeAssignations);
                    i++;
                } else if (i == 1) {
                    ((Assignations) model).setNameassignation(cellValue);
                    i++;
                } else if (i == 2) {
                    ((Assignations) model).setDescription(cellValue);
                }

            }
        }
        return model;
    }


    private static void loadData(Object model) {
        // TODO Auto-generated method stub
        if (model instanceof Branch) {
            Branch branch = (Branch) model;
            boolean save = branchDAO.save(branch);
            if (save) {
                System.out.println(Messages.SAVE_BRANCH.getMessage() + branch.getDescription());
            }
        } else if (model instanceof TypeAssignations) {
            TypeAssignations typeAssignations = (TypeAssignations) model;
            boolean save = typeAssignationsDAO.save(typeAssignations);
            if (save) {
                System.out.println(Messages.SAVE_TYPE_ASSIGNATION.getMessage() + typeAssignations.getDescription());
            }
        } else if (model instanceof Assignations) {
            Assignations assignations = (Assignations) model;
            boolean save = assignationsDAO.save(assignations);
            if (save) {
                System.out.println(Messages.SAVE_ASSIGNATION.getMessage() + assignations.getDescription());
            }
        }
    }

}
