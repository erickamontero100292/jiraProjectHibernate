package com.jiraproject.processfile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.jiraproject.daoimpl.BranchDAOImpl;
import com.jiraproject.interfacedao.BranchDAO;
import com.jiraproject.messages.Messages;
import com.jiraproject.model.Branch;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

public class ExcelReader {

	// public static final String XLSX_FILE_PATH = "C:\\Users\\Ericka
	// Montero\\Desktop\\historias.xlsx";
	public static final String XLSX_FILE_PATH = "C:\\Users\\Ericka Montero\\Desktop\\historiasJira.xlsx";
	public static final String SHEET_BRANCH = "branch";
	public static final String SHEET_ASSIGNATIONS = "asignations";
	public static final String SHEET_TYPE_ASSIGNATIONS = "asignationType";
	public static final BranchDAO branchDAO = new BranchDAOImpl();

	public static void main(String[] args) {
		try {
			Workbook workbook = createFile(XLSX_FILE_PATH);
			processInformation(workbook, SHEET_BRANCH);
			System.out.println("---------------------------------------");

			processInformation(workbook, SHEET_TYPE_ASSIGNATIONS);
			System.out.println("---------------------------------------");
			processInformation(workbook, SHEET_ASSIGNATIONS);
		} catch (IOException io) {
			io.printStackTrace();
			System.out.println(Messages.NO_FOUND_FILE.getMessage());
		} catch (InvalidFormatException ife) {
			ife.printStackTrace();
		} catch (EncryptedDocumentException ede) {
			ede.printStackTrace();
		}
	}

	// @Override
	public void readFile(Workbook workbook) {
		// TODO Auto-generated method stub

		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

	}

	// @Override
	public void writeFile(String nameFile) {
		// TODO Auto-generated method stub

	}

	// @Override
	public static void processInformation(Workbook workbook, String nameSheet) throws IOException {
		// TODO Auto-generated method stub
		Sheet sheet = workbook.getSheet(nameSheet);// OBTENGO LA HOJA DEL LIBRO
		switch (nameSheet) {
		case SHEET_BRANCH:
			etl(sheet);
			break;
		case SHEET_TYPE_ASSIGNATIONS:
			break;
		case SHEET_ASSIGNATIONS:
			break;
		default:
			break;
		}
		workbook.close();

	}

	// @Override
	public static Workbook createFile(String nameFile)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		Workbook workbook = WorkbookFactory.create(new File(nameFile));

		return workbook;

	}

	public static void etl(Sheet sheet) throws IOException {

		DataFormatter dataFormatter = new DataFormatter();
		Iterator<Row> rowIterator = sheet.rowIterator(); // OBTENGO LAS FILAS DE LA HOJA
		int head = 0;

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next(); // OBTENGO UNA FILA EN ESPECIFICO
			if (row.getRowNum() != 0) {
				Iterator<Cell> cellIterator = row.cellIterator(); // OBTENGO LAS CELDAS DE ESA FILA
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					transformationData(cellValue, sheet.getSheetName());
					System.out.print(cellValue + "\t");
				}
				System.out.println();

			}
		}

	}

	private static void transformationData(String cellValue, String nameSheet) {
		// TODO Auto-generated method stub
		switch (nameSheet) {
		case SHEET_BRANCH:
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			Branch branch = new Branch(cellValue, timestamp);
			loadData(branch);
			break;
		case SHEET_TYPE_ASSIGNATIONS:
			break;
		case SHEET_ASSIGNATIONS:
			break;
		default:
			break;
		}
	}

	private static void loadData(Object model) {
		// TODO Auto-generated method stub
		if (model instanceof Branch) {
			Branch branch = (Branch) model;
			boolean save = branchDAO.save(branch);
			if (save) {
				System.out.println("Se guardo el branch  - " + branch.getDescription());
			}
		}
	}
}
