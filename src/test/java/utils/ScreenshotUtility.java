package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseTestCase;

public class ScreenshotUtility {
	
	public static String strPath = System.getProperty("user.dir")+ "\\test-output\\reports\\"+"screenshot.png";
	
	
	public  static void takeScreenshot() throws IOException{
		File srcFile = ((TakesScreenshot)  BaseTestCase.getDriver()).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(srcFile, new File(strPath));
	}
	
	public static String getScreenshotPath(){
		return strPath;
	}

}
