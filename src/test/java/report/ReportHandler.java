package report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import lombok.extern.slf4j.Slf4j;
import utils.ScreenshotUtility;

@Slf4j
public class ReportHandler {

	public static String strPath = System.getProperty("user.dir")+ "\\test-output\\reports\\"+getCurrentDate()+".html";
	public static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(strPath);
	public static ExtentReports extentReports = new ExtentReports();
	public static ExtentTest extentTest;
	public void initiateReport() {
		
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setCSS("css-string");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setDocumentTitle("AutomationReport");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		extentReports.attachReporter(htmlReporter);
		extentReports.setSystemInfo("User", System.getProperty("user.name"));
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	public void endLogger() {
		extentReports.flush();
	}
	
	public ExtentTest createTest(String strMethod){
	extentTest=extentReports.createTest(strMethod);
	return extentTest;
	}
	
	/*
	 * 
	 * Pass, Fail
	 */
	public static void logTestResults(ITestResult result) {
		
		if (result.getStatus() == ITestResult.SUCCESS) {

		} else if (result.getStatus() == ITestResult.FAILURE) {
			
			try {
				
				String strPath = ScreenshotUtility.getScreenshotPath();
				ScreenshotUtility.takeScreenshot();
				StringBuilder stringbuilder= new StringBuilder();
				
				stringbuilder.append("<p style='color:red'>"+result.getThrowable().getMessage()+"</p>");
				stringbuilder.append("<u>StackTrace:=</u><br>");
				stringbuilder.append(strackTraceBuilder(result));
				stringbuilder.append("<br><u><a href='"+strPath+"' target='blank'>Screenshot</a></u><br>");
				extentTest.log(Status.FAIL, stringbuilder.toString());
				log.info(ExceptionUtils.getStackTrace(result.getThrowable()));
			} catch (IOException e) {
				extentTest.log(Status.FAIL,"<u>StackTrace:=</u><br>"+result.getThrowable().getMessage()+ "<br>Screenshot not captured.<br>"+e.getMessage());
				log.info(ExceptionUtils.getStackTrace(result.getThrowable()));
			}

		}

		else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, result.getThrowable().getMessage());

		}
	}
	
	
	public ExtentTest getExtentTestObj(){
	return extentTest;
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentDate());
	}
	
	public static String getCurrentDate(){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:m a");
		return sdf.format(new Date()).replace("/", "_").replace(":", "_").replace(" ", "_");
	}
	
	public static String strackTraceBuilder(ITestResult result){
		StringBuilder stackTrace= new StringBuilder();
		for(StackTraceElement s:result.getThrowable().getStackTrace()){
			stackTrace.append(s);
			stackTrace.append("<br>");
		}
		return stackTrace.toString();
	}

}
