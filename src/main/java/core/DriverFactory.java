package core;

import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	private DriverFactory () {}
	
	public static WebDriver getDriver() {
		if (driver == null) {
			String driverPath = Paths.get("src/drivers/chromedriver.exe").toAbsolutePath().toString();
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver  = new ChromeDriver();
			driver.manage().window().maximize();	
		}
		return driver;	
	}
	
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}	
}
