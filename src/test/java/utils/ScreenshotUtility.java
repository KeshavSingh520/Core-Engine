package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseTestCase;

public class ScreenshotUtility {
	
	public static String strPath;
	
	public  static void takeScreenshot() throws IOException{
		strPath =createScreenShotFolder()+File.separator+getCurrentDate()+".png";
		File srcFile = ((TakesScreenshot)  BaseTestCase.getWebdriver()).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(srcFile, new File(strPath));
	}
	
	public static String getScreenshotPath(){
		return strPath;
	}
	
	private static String createScreenShotFolder() {
            String screenshotFolderPath=IOUtility.getStrPath()+File.separator+"screenshots";
			File file = new File(screenshotFolderPath);
			if (!file.exists()) {
				if (file.mkdir()) {
				}
			}
			return screenshotFolderPath;
	}
	
	public static String getCurrentDate() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		return sdf.format(new Date()).replace("/", "_").replace(":", "_").replace(" ", "_");
	}

}
