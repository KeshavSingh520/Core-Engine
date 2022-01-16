package com.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://updeled.gov.in/Registration/Tet/DTetRegcandVerlog.aspx");
		driver.findElement(By.xpath("//input[@placeholder='Enter Registration']")).sendKeys("2906283192");
		driver.findElement(By.xpath("//input[@placeholder='Enter One Time Password']")).sendKeys("4F1VockK");
		
		
		
		driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();
		WebElement minQualifiation = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[1]"));
		Select select = new Select(minQualifiation);
		select.selectByValue("7");
		WebElement minQualifiationStatus = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[2]"));
		select = new Select(minQualifiationStatus);
		select.selectByValue("02");
		WebElement examAppFor = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[3]"));
		select = new Select(examAppFor);
		select.selectByValue("P");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement checkbox10 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
		checkbox10.click();
		Thread.sleep(1000);
		WebElement board10 = driver.findElement(By.xpath("(//select[contains(@id,'Board')])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(board10));
		select = new Select(board10);
		Thread.sleep(1000);
		select.selectByVisibleText("C.B.S.E.");
		WebElement passingyear10 = driver.findElement(By.xpath("(//input[@placeholder='Enter Passing Year'])[1]"));
		passingyear10.sendKeys("2014");
		WebElement rollNo10 = driver.findElement(By.xpath("(//input[@placeholder='Enter Rollno'])[1]"));
		rollNo10.sendKeys("5180407");
		WebElement marksheetNo10 = driver.findElement(By.xpath("(//input[@placeholder='Enter Marksheet No'])[1]"));
		marksheetNo10.sendKeys("1318869");
		WebElement totalMarksNo10 = driver.findElement(By.xpath("(//input[@placeholder='Total Marks'])[1]"));
		totalMarksNo10.sendKeys("500");
		Thread.sleep(2000);
		WebElement makrsObtained10 = driver.findElement(By.xpath("(//input[@placeholder='Marks Obtained'])[1]"));
		makrsObtained10.sendKeys("304");
		Thread.sleep(1000);
		
		WebElement checkbox12 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
		checkbox12.click();
		Thread.sleep(1000);
		WebElement board12 = driver.findElement(By.xpath("(//select[contains(@id,'Board')])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(board12));
		select = new Select(board12);
		select.selectByVisibleText("C.B.S.E.");
		WebElement passingyear12 = driver.findElement(By.xpath("(//input[@placeholder='Enter Passing Year'])[2]"));
		passingyear12.sendKeys("2016");
		WebElement rollNo12 = driver.findElement(By.xpath("(//input[@placeholder='Enter Rollno'])[2]"));
		rollNo12.sendKeys("9756590");
		WebElement marksheetNo12 = driver.findElement(By.xpath("(//input[@placeholder='Enter Marksheet No'])[2]"));
		marksheetNo12.sendKeys("0619159");
		WebElement totalMarksNo12 = driver.findElement(By.xpath("(//input[@placeholder='Total Marks'])[2]"));
		totalMarksNo12.sendKeys("500");
		Thread.sleep(2000);
		WebElement makrsObtained12 = driver.findElement(By.xpath("(//input[@placeholder='Marks Obtained'])[2]"));
		makrsObtained12.sendKeys("371");
		Thread.sleep(1000);
		
		WebElement checkboxGrad = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]"));
		checkboxGrad.click();
		Thread.sleep(1000);
		WebElement boardGrad = driver.findElement(By.xpath("(//select[contains(@id,'Board')])[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(boardGrad));
		select = new Select(boardGrad);
		select.selectByVisibleText("University of Delhi");
		WebElement passingyearGrad = driver.findElement(By.xpath("(//input[@placeholder='Enter Passing Year'])[3]"));
		passingyearGrad.sendKeys("2021");
		WebElement rollNoGrad = driver.findElement(By.xpath("(//input[@placeholder='Enter Rollno'])[3]"));
		rollNoGrad.sendKeys("18047516018");
		WebElement marksheetNoGrad = driver.findElement(By.xpath("(//input[@placeholder='Enter Marksheet No'])[3]"));
		marksheetNoGrad.sendKeys("18MIRHBHHI000018");
		WebElement totalMarksNoGrad = driver.findElement(By.xpath("(//input[@placeholder='Total Marks'])[3]"));
		totalMarksNoGrad.sendKeys("1950");
		Thread.sleep(2000);
		WebElement makrsObtainedGrad = driver.findElement(By.xpath("(//input[@placeholder='Marks Obtained'])[3]"));
		makrsObtainedGrad.sendKeys("1459.3");
		Thread.sleep(1000);
		
		WebElement checkboxDED = driver.findElement(By.xpath("(//input[@type='checkbox'])[5]"));
		checkboxDED.click();
		Thread.sleep(1000);
		WebElement boardDED = driver.findElement(By.xpath("(//select[contains(@id,'Board')])[5]"));
		wait.until(ExpectedConditions.elementToBeClickable(boardDED));
		select = new Select(boardDED);
		select.selectByVisibleText("Other University");
		WebElement passingyearDED = driver.findElement(By.xpath("(//input[@placeholder='Enter Passing Year'])[5]"));
		passingyearDED.sendKeys("2018");
		WebElement rollNoDED = driver.findElement(By.xpath("(//input[@placeholder='Enter Rollno'])[5]"));
		rollNoDED.sendKeys("00034397");
		WebElement marksheetNoDED = driver.findElement(By.xpath("(//input[@placeholder='Enter Marksheet No'])[5]"));
		marksheetNoDED.sendKeys("D/2016/VTI/000024");
		WebElement totalMarksNoDED = driver.findElement(By.xpath("(//input[@placeholder='Total Marks'])[5]"));
		totalMarksNoDED.sendKeys("2300");
		Thread.sleep(2000);
		WebElement makrsObtainedDED = driver.findElement(By.xpath("(//input[@placeholder='Marks Obtained'])[5]"));
		makrsObtainedDED.sendKeys("1729");
		Thread.sleep(1000);
		
		
		
		WebElement examCity1 = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[4]"));
		select = new Select(examCity1);
		select.selectByVisibleText("GHAZIABAD");
		
		WebElement examCity2 = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[5]"));
		select = new Select(examCity2);
		select.selectByVisibleText("KAUSHAMBI");
		
		WebElement examCity3 = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[6]"));
		select = new Select(examCity3);
		select.selectByVisibleText("GAUTAM BUDDH NAGAR");
		
		WebElement examSubGroup = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[7]"));
		select = new Select(examSubGroup);
		select.selectByVisibleText("ARTS");
		
		WebElement examSeconLang = driver.findElement(By.xpath("(//select[@placeholder='Gender'])[8]"));
		select = new Select(examSeconLang);
		select.selectByVisibleText("ENGLISH");
		
		
	}

}
