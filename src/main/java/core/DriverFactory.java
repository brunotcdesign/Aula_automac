package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	// declaração da variavel driver do tipo webdriver
	private static WebDriver driver;
	
	private DriverFactory() {}
	
	// inicializa o driver e faz um controle para não inicialzzar mais de um drive
	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
			driver  = new ChromeDriver();
			driver.manage().window().maximize();	
		}
		return driver;
		
	}
	
	// finaliza o driver e reseta a variável para null se ja houver um driver iniciado
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	
		
	}
	
	
	
	
	
	
	
	
	

}
