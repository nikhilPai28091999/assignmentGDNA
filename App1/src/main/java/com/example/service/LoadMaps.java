package com.example.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.model.IFSCObject;

@Service
public class LoadMaps {
	private static final Logger LOGGER = LogManager.getLogger(LoadMaps.class);

	public XSSFSheet readFromExcel(String path, String sheetName) throws IOException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
			LOGGER.info("File Found");

		} catch (FileNotFoundException e) {
			LOGGER.error("Excel File not Found. Please provide the right path");
		}
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sh = wb.getSheet(sheetName);
		return sh;
	}

	@PostConstruct
	public Map<String, IFSCObject> populateMapWithAllExcelData() throws IOException {
		XSSFSheet sh = readFromExcel("C:\\\\Users\\\\nikhi\\\\Downloads\\\\Book.xlsx", "Sheet1");

		HashMap<String, IFSCObject> map = new HashMap<String, IFSCObject>();

		for (int r = 1; r <= sh.getLastRowNum(); r++) {

			IFSCObject ifscObject = new IFSCObject();
			ifscObject.setBank(sh.getRow(r).getCell(0).getStringCellValue());
			ifscObject.setIfsc(sh.getRow(r).getCell(1).getStringCellValue());
			ifscObject.setMicr((int) sh.getRow(r).getCell(2).getNumericCellValue());
			ifscObject.setBranch(sh.getRow(r).getCell(3).getStringCellValue());
			ifscObject.setAddress(sh.getRow(r).getCell(4).getStringCellValue());
			ifscObject.setStdCode((int) sh.getRow(r).getCell(5).getNumericCellValue());
			ifscObject.setContact((int) sh.getRow(r).getCell(6).getNumericCellValue());
			ifscObject.setCity(sh.getRow(r).getCell(7).getStringCellValue());
			ifscObject.setDistrict(sh.getRow(r).getCell(8).getStringCellValue());
			ifscObject.setState(sh.getRow(r).getCell(9).getStringCellValue());

			map.put(ifscObject.getIfsc(), ifscObject);
		}
		return map;
	}

	@PostConstruct
	public Map<String, Integer> populateMapWithCount() throws IOException {
		
		XSSFSheet sh = readFromExcel("C:\\Users\\nikhi\\Downloads\\Book.xlsx", "Sheet2");
		
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();

		for (int r = 2; r <= sh.getLastRowNum(); r++) {
			countMap.put(sh.getRow(r).getCell(0).getStringCellValue(),
					(int) sh.getRow(r).getCell(1).getNumericCellValue());
		}
		return countMap;
	}
}
