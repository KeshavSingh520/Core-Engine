package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentTest;
import com.testresults.TestResults;

import lombok.extern.slf4j.Slf4j;
import report.ReportHandler;

@Slf4j
public class QualityReport {

	static List<String> lstHeaders = new ArrayList<String>();
	static String strPath =IOUtility.getStrPath()+File.separator+"QualityReport.xlsx";
	static ReportHandler reporthandler = new ReportHandler();
	ITestContext context;
	ThreadLocal<ExtentTest> extentTest;
	/*
	 * public QualityReport(ITestContext context, ThreadLocal<ExtentTest>
	 * extentTest) { this.context = context; this.extentTest = extentTest; }
	 */

	public static void generateQualityReport(ConcurrentLinkedQueue<TestResults> resultsCache) throws FileNotFoundException, IOException {

		lstHeaders.add("Package Name");
		lstHeaders.add("Class Name");
		lstHeaders.add("Method Name");
		lstHeaders.add("Status");
		lstHeaders.add("Exception");

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("EXECUTION RESULTS");
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < lstHeaders.size(); i++) {
			row.createCell(i, CellType.STRING).setCellValue(lstHeaders.get(i));
		}
		int rowNum=1;
		for(TestResults result:resultsCache) {
			row=sheet.createRow(rowNum);
			row.createCell(0, CellType.STRING).setCellValue(result.getStrPackageName());
			row.createCell(1, CellType.STRING).setCellValue(result.getStrClassName());
			row.createCell(2, CellType.STRING).setCellValue(result.getStrMethodName());
			row.createCell(3, CellType.STRING).setCellValue(result.getStrStatus());
			row.createCell(4, CellType.STRING).setCellValue(result.getStrException());
			rowNum++;
		}
		workbook.write(new FileOutputStream(new File(strPath)));
		workbook.close();
		log.info("Generating Quality Report at: " + strPath);
	}

}
