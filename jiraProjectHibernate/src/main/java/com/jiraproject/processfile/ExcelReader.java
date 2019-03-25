package com.jiraproject.processfile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

	//public static final String XLSX_FILE_PATH = "C:\\Users\\Ericka Montero\\Desktop\\historias.xlsx";
	public static final String XLSX_FILE_PATH = "C:\\Users\\Ericka Montero\\Desktop\\historiasJira.xlsx";
	public static final String SHEET_BRANCH = "branch";
	public static final String SHEET_ASSIGNATIONS = "asignations";

	public static void main(String[] args) {
		try {
			Workbook workbook = createFile(XLSX_FILE_PATH);
			readSheetFile(workbook, SHEET_ASSIGNATIONS);
		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("EL ARCHIVO NO EXISTE");
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
	public static void readSheetFile(Workbook workbook, String nameSheet) throws IOException {
		// TODO Auto-generated method stub
		Sheet sheet = workbook.getSheet(nameSheet);// OBTENGO LA HOJA DEL LIBRO
		DataFormatter dataFormatter = new DataFormatter();

		Iterator<Row> rowIterator = sheet.rowIterator(); // OBTENGO LAS FILAS DE LA HOJA

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next(); // OBTENGO UNA FILA EN ESPECIFICO
			Iterator<Cell> cellIterator = row.cellIterator(); // OBTENGO LAS CELDAS DE ESA FILA
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = dataFormatter.formatCellValue(cell);
				System.out.print(cellValue + "\t");
			}
			System.out.println();

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
}
