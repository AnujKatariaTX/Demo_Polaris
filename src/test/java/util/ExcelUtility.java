package util;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

// TODO: Auto-generated Javadoc
/**
 * <h1>ExcelUtility class</h1> <br>
 * Utility Class for Excel <br>
 * <br>
 * .
 *
 * @author TestingXperts
 * @version 1.0 <br>
 *          <br>
 */
public class ExcelUtility extends PropertiesUtility {

	/** The no TC. */
	static int rowIdx, sheetIdx, noTC;

	/** The excel data. */
	static String excel_Data;

	/** The i file. */
	static FileInputStream iFile;

	/** The workbook. */
	static Workbook workbook;

	/** The i sheet. */
	static Sheet iSheet;

	/** The o cell. */
	static Cell oCell;

	/** The o col. */
	static Column oCol;

	/** The o row. */
	static Row oRow;

	/** The cell number. */
	protected static int cellNumber = 0;

	/** The o file. */
	private static OutputStream oFile;

	/**
	 * This function reads data from an Excel file. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fPath
	 *            - Specify the path of the Excel File. <br>
	 *            <br>
	 * @param sheetName
	 *            - Specify the Sheet Name in the Excel file. <br>
	 *            <br>
	 * @param iRow
	 *            - Specify the Row number from which the data has to be
	 *            fetched. <br>
	 *            <br>
	 * @param iCol
	 *            - Specify the Column number from which the data has to be
	 *            fetched. <br>
	 *            <br>
	 * @return String value read from the excel file. <br>
	 *         <br>
	 * @exception NullPointerException
	 *                If the specified Cell is not found in the Excel file. <br>
	 *                <br>
	 * @category Utility Class for Excel
	 */
	public static String readFromExcel(String fPath, String sheetName, int iRow, int iCol) {

		excel_Data = null;

		try {

			iFile = new FileInputStream(new File(fPath));
			String extn = FilenameUtils.getExtension(fPath);

			switch (extn.toLowerCase()) {
			case "xls":
				workbook = new HSSFWorkbook(iFile);
				break;
			case "xlsx":
				workbook = new XSSFWorkbook(iFile);
				break;
			default:
				Reporter.log("Incorrect Extension - " + extn);

				fail("Incorrect Extension Provided");
			}

			iSheet = workbook.getSheet(sheetName);
			oRow = iSheet.getRow(iRow - 1);
			excel_Data = oRow.getCell(iCol - 1).getStringCellValue();
			Reporter.log("Data Read from Excel Successfull");

		} catch (Exception e) {

			System.out.println(e.getMessage());
			Reporter.log("Data Read from Excel Failed");

		}
		return excel_Data;

	}

	/**
	 * This function appends data from an Excel file. <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fPath
	 *            - Specify the path of the Excel File. <br>
	 *            <br>
	 * @param sheetName
	 *            - Specify the Sheet Name in the Excel file. <br>
	 *            <br>
	 * @param iRow
	 *            - Specify the Row number from which the data has to be
	 *            fetched. <br>
	 *            <br>
	 * @param iCol
	 *            - Specify the Column number from which the data has to be
	 *            fetched. <br>
	 *            <br>
	 * @param value
	 *            - Specify the value to be appended in the Excel file. <br>
	 *            <br>
	 * @exception IOException
	 *                If the file is not found on the specified location. <br>
	 *                <br>
	 * @exception NullPointerException
	 *                If the specified Cell is not found in the Excel file. <br>
	 *                <br>
	 * @category Utility Class for Excel
	 */
	public static void appendInExcel(String fPath, String sheetName, int iRow, int iCol, String value)
			throws IOException {

		iFile = new FileInputStream(new File(fPath));
		String extn = FilenameUtils.getExtension(fPath);
		switch (extn.toLowerCase()) {
		case "xls":
			workbook = new HSSFWorkbook(iFile);
			break;
		case "xlsx":
			workbook = new XSSFWorkbook(iFile);
			break;
		default:
			fail("Incorrect extension provided");

		}
		iSheet = workbook.getSheet(sheetName);

		try {
			oRow = iSheet.getRow(iRow - 1);
			oCell = oRow.getCell(iCol - 1);
		} catch (NullPointerException e) {
			oRow = iSheet.createRow(iRow - 1);
			oCell = oRow.getCell(iCol - 1);
		}

		try {
			oCell.setCellValue(value);
		} catch (NullPointerException e) {
			oCell = oRow.createCell(iCol - 1);
			oCell.setCellValue(value);
		}

		try {
			oFile = new FileOutputStream(fPath);
			workbook.write(oFile);
			oFile.close();
			Reporter.log("Data Appended to Excel Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Data Appending to Excel Failed");
		}
	}

	/**
	 * This function customizes the Excel report (adds description of the test
	 * case). <br>
	 * <br>
	 *
	 * @author TestingXperts <br>
	 *         <br>
	 * @version 1.0 <br>
	 *          <br>
	 * @param fPath
	 *            - Specify the path of the Excel File. <br>
	 *            <br>
	 * @exception NullPointerException
	 *                If the specified Cell is not found in the Excel file. <br>
	 *                <br>
	 * @category Utility Class for Excel
	 */
	public static void customizeExcelReport(String fPath) {

		excel_Data = null;

		excel_Data = readFromExcel(
				System.getProperty("user.dir") + readStringFromProperties("Framework.properties", "ExcelReportFilePath")
						+ readStringFromProperties("Framework.properties", "ExcelReportFileName"),
				"Summary", 5, 2);

		noTC = Integer.parseInt(excel_Data);

		for (int i = 0; i < noTC; i++) {

			try {
				iFile = new FileInputStream(new File(fPath));
				String extn = FilenameUtils.getExtension(fPath);

				switch (extn.toLowerCase()) {

				case "xls":
					workbook = new HSSFWorkbook(iFile);
					break;

				case "xlsx":
					workbook = new XSSFWorkbook(iFile);
					break;

				default:
					Reporter.log("Incorrect Extension - " + extn);

					fail("Incorrect Extension Provided");
				}

				for (sheetIdx = 1; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {

					iSheet = workbook.getSheet(workbook.getSheetName(sheetIdx));

					for (rowIdx = 1; rowIdx <= iSheet.getLastRowNum(); rowIdx++) {

						oRow = iSheet.getRow(rowIdx);
						excel_Data = oRow.getCell(0).getStringCellValue();

						excel_Data.toLowerCase();

						if (excel_Data.equalsIgnoreCase("testClasses.login.openwebsite")) {
							excel_Data = "This function opens the Website.";
						}

						else if (excel_Data.equalsIgnoreCase("testClasses.login.usernametest")) {
							excel_Data = "This function enters the username.";
						}

						else if (excel_Data.equalsIgnoreCase("testClasses.login.passwordtest")) {
							excel_Data = "This function enters password.";
						}

						else if (excel_Data.equalsIgnoreCase("testClasses.login.signinbutton")) {
							excel_Data = "This function clicks the Sign In button.";
						}

						oCell = oRow.getCell(6);
						oCell.setCellValue(excel_Data);
					}
				}

			} catch (NullPointerException nulle) {

				oCell = oRow.createCell(6);
				oCell.setCellValue(excel_Data);

			} catch (Exception e) {

				System.out.println(e.getMessage());
				Reporter.log("Data Read from Excel Failed");

			}

			try {
				oFile = new FileOutputStream(fPath);
				workbook.write(oFile);
				oFile.close();
				Reporter.log("Data Appended to Excel Successfully");

			} catch (Exception e) {
				e.printStackTrace();
				Reporter.log("Data Appending to Excel Failed");
			}
		}
	}

	/**
	 * Gets the test data from excel.
	 *
	 * @param sheetName            the sheet name
	 * @param rowName the row name
	 * @param columnName            the column name
	 * @return the test data from excel
	 */
	public static String getTestDataFromExcel(String sheetName, String rowName, String columnName) {

		excel_Data = null;

		try {

			iFile = new FileInputStream(new File(System.getProperty("user.dir")
					+ readStringFromProperties("Framework.properties", "ExcelInputFilePath")));
			String extn = FilenameUtils.getExtension(System.getProperty("user.dir")
					+ readStringFromProperties("Framework.properties", "ExcelInputFilePath"));

			switch (extn.toLowerCase()) {
			case "xls":
				workbook = new HSSFWorkbook(iFile);
				break;
			case "xlsx":
				workbook = new XSSFWorkbook(iFile);
				break;
			default:
				Reporter.log("Incorrect Extension - " + extn);

				fail("Incorrect Extension Provided");
			}

			iSheet = workbook.getSheet(sheetName);
			int rowNumber = 0;
			for (int i = 0; i < iSheet.getLastRowNum(); i++) {
				oCell = iSheet.getRow(i).getCell(0);
				oCell.setCellType(oCell.CELL_TYPE_STRING);
				String getCellName = oCell.getStringCellValue();
				if (getCellName.contains(rowName)) {
					rowNumber = i;
					break;
				}
			}
			oRow = iSheet.getRow(rowNumber);
			for (int y = 0; y < iSheet.getRow(0).getLastCellNum(); y++) {
				oCell = iSheet.getRow(0).getCell(y);
				oCell.setCellType(oCell.CELL_TYPE_STRING);
				String getCellName = oCell.getStringCellValue();
				if (getCellName.contains(columnName)) {
					cellNumber = oCell.getColumnIndex();
					break;
				}
			}
			if (oRow.getCell(cellNumber).getCellType() == oRow.getCell(cellNumber).CELL_TYPE_FORMULA) {
				excel_Data = "" + oRow.getCell(cellNumber).getCellFormula();
			} else if (oRow.getCell(cellNumber).getCellType() == oRow.getCell(cellNumber).CELL_TYPE_NUMERIC) {
				excel_Data = "" + oRow.getCell(cellNumber).getNumericCellValue();
			} else {
				excel_Data = oRow.getCell(cellNumber).getStringCellValue();
			}

			Reporter.log("Data Read from Excel Successfull");

		} catch (Exception e) {

			System.out.println(e.getMessage());
			Reporter.log("Data Read from Excel Failed");

		}
		return excel_Data;
	}

	
	/**
	 * Gets the test data from excel first row.
	 *
	 * @param sheetName the sheet name
	 * @param columnName the column name
	 * @return the test data from excel first row
	 */
	public static String getTestDataFromExcelFirstRow(String sheetName, String columnName) {

		excel_Data = null;

		try {

			iFile = new FileInputStream(new File(System.getProperty("user.dir")
					+ readStringFromProperties("Framework.properties", "ExcelInputFilePath")));
			String extn = FilenameUtils.getExtension(System.getProperty("user.dir")
					+ readStringFromProperties("Framework.properties", "ExcelInputFilePath"));

			switch (extn.toLowerCase()) {
			case "xls":
				workbook = new HSSFWorkbook(iFile);
				break;
			case "xlsx":
				workbook = new XSSFWorkbook(iFile);
				break;
			default:
				Reporter.log("Incorrect Extension - " + extn);

				fail("Incorrect Extension Provided");
			}

			iSheet = workbook.getSheet(sheetName);
			int rowNumber = 1;
			/*for (int i = 0; i < iSheet.getLastRowNum(); i++) {
				oCell = iSheet.getRow(i).getCell(0);
				oCell.setCellType(oCell.CELL_TYPE_STRING);
				String getCellName = oCell.getStringCellValue();
				if (getCellName.contains(rowName)) {
					rowNumber = i;
					break;
				}
			}*/
			oRow = iSheet.getRow(rowNumber);
			for (int y = 0; y < iSheet.getRow(0).getLastCellNum(); y++) {
				oCell = iSheet.getRow(0).getCell(y);
				oCell.setCellType(oCell.CELL_TYPE_STRING);
				String getCellName = oCell.getStringCellValue();
				if (getCellName.contains(columnName)) {
					cellNumber = oCell.getColumnIndex();
					break;
				}
			}
			if (oRow.getCell(cellNumber).getCellType() == oRow.getCell(cellNumber).CELL_TYPE_FORMULA) {
				excel_Data = "" + oRow.getCell(cellNumber).getCellFormula();
			} else if (oRow.getCell(cellNumber).getCellType() == oRow.getCell(cellNumber).CELL_TYPE_NUMERIC) {
				excel_Data = "" + oRow.getCell(cellNumber).getNumericCellValue();
			} else {
				excel_Data = oRow.getCell(cellNumber).getStringCellValue();
			}

			Reporter.log("Data Read from Excel Successfull");

		} catch (Exception e) {

			System.out.println(e.getMessage());
			Reporter.log("Data Read from Excel Failed");

		}
		return excel_Data;
	}
	
	/**
	 * Gets the datafrom xls.
	 *
	 * @param subSheetName            the sub sheet name
	 * @param columnName            the column name
	 * @return the datafrom xls
	 */
	@SuppressWarnings("static-access")
	public static String getdatafromXls(String subSheetName, String columnName) {
		String cellValue = null;

		try {

			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
					+ PropertiesUtility.readStringFromProperties("Framework.properties", "ExcelInputFilePath"));

			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(subSheetName);

			for (int y = 0; y < worksheet.getRow(0).getLastCellNum(); y++) {
				HSSFCell cellA2 = worksheet.getRow(0).getCell(y);
				cellA2.setCellType(cellA2.CELL_TYPE_STRING);
				String getCellName = cellA2.getStringCellValue();
				if (getCellName.contains(columnName)) {
					cellNumber = cellA2.getColumnIndex();
				}
			}

			cellValue = getCellData(worksheet, 1, cellNumber);

			Reporter.log("Get data from XLS method passed");

		} catch (Exception e) {
			Reporter.log("Get data from XLS method failed.");
			// LogUtil.errorLog(Utility.class, e.getMessage(), e);
			// logger.log(LogStatus.FAIL, e);
		}
		System.out.println("Excel value::" + cellValue);
		return cellValue;

	}

	/**
	 * Gets the cell data.
	 *
	 * @param sheet
	 *            the sheet
	 * @param RowNum
	 *            the row num
	 * @param ColNum
	 *            the col num
	 * @return the cell data
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unused")
	public static String getCellData(Sheet sheet, int RowNum, int ColNum) throws Exception {
		Cell cell;
		try {

			cell = sheet.getRow(RowNum).getCell(ColNum);
			cell.setCellType(cell.CELL_TYPE_STRING);
			String cellData = "";

			switch (cell.getCellType()) {

			case Cell.CELL_TYPE_FORMULA:
				cellData = "" + cell.getCellFormula();
				break;

			case Cell.CELL_TYPE_NUMERIC:
				cellData = "" + Double.toString(cell.getNumericCellValue());
				break;

			case Cell.CELL_TYPE_STRING:
				cellData = "" + cell.getStringCellValue();
				break;

			default:
			}
			Reporter.log("Cell Data method passed.");

			return cellData;

		} catch (Exception e) {
			// errorLog(ExcelDataUtil.class, e.getMessage(), e);

			Reporter.log("Cell Data method failed.");
			// logger.log(LogStatus.FAIL, e);
			return "";

		}

	}

}
