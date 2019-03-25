package com.jiraproject.processfile;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

public interface FileExcel {
	
	Workbook createFile(String nameFile) throws EncryptedDocumentException, InvalidFormatException, IOException;
	
	void readFile(Workbook workbook);
	
	void writeFile(Workbook workbook);
	
	void readSheetFile(Workbook workbook, String nameSheet);
	
	

}
