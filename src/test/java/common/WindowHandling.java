package common;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandling {
	private WebDriver driver;
	private Set<String> setWindowHandle;

	public WindowHandling(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getActiveWindows()
	{
		setWindowHandle=driver.getWindowHandles();
	}
	
	public void switchToWindow(String strWindowId)
	{
		for(String s:setWindowHandle){
			
		}
	}

}
