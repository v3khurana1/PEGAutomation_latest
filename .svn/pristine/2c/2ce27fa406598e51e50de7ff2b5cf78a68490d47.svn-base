package Framework;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;
	static DataFormatter objDefaultFormat = new DataFormatter();
	static FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) ExcelWBook);

	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path, String SheetName) throws Exception {

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	public static Object[][] getTableArray1(String FilePath, String SheetName, List<Integer> RowList) throws Exception {

		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int totalRows = RowList.size();
			int totalCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();

			tabArray = new String[totalRows][totalCols - 1];
			int i = 0;
			for (int itr : RowList) {
				for (int j = 0; j < totalCols - 1; j++)
					tabArray[i][j] = getCellData(itr, j + 1);
				i++;
			}

			System.out.println("---------------------------");
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception {

		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startCol = 1;
			int ci = 0, cj = 0;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();

			tabArray = new String[totalRows][totalCols - 1];

			for (int j = startCol; j <= totalCols - 1; j++) {
				tabArray[ci][cj] = getCellData(iTestCaseRow, j);
				cj++;
				System.out.println(tabArray[ci][cj]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			XSSFCell cellValue = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			// Cell cellValue = row.getCell(0);
			objFormulaEvaluator.evaluate(cellValue);
			String cellValueStr = objDefaultFormat.formatCellValue(cellValue, objFormulaEvaluator);
			// String CellData = Cell.getStringCellValue();
			return cellValueStr;
		} catch (Exception e) {
			return "";
		}
	}

	public static List<Integer> getRowContains(String sTestCaseName, int colNum) throws Exception {

		List<Integer> rowNum = new ArrayList<Integer>();
		try {
			int rowCount = ExcelUtils.getRowUsed();
			System.out.println("total Row count " + rowCount);
			for (int i = 1; i <= rowCount; i++) {
				if (ExcelUtils.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
					System.out.println("RowNumber = " + i);
					rowNum.add(i);
				}
			}
			return rowNum;
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowUsed() throws Exception {
		try {
			int RowCount = ExcelWSheet.getLastRowNum();
			return RowCount;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

}