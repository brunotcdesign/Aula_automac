package teste;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import core.DSL;


public class FrameTest {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa () {
		System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
		driver  = new ChromeDriver();
		driver.manage().window().maximize();		
		driver.get("file:///C:/Users/User/OneDrive/Documentos/AULA/Java/Workspace%20-%20eclipse/Deckbox/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void frameTest() {		
		driver.switchTo().frame("frame1");
		dsl.clicar("frameButton");
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();		
		driver.switchTo().defaultContent();
		dsl.escreve("elementosForm:nome", msg);
	}
	
	
	@Test
	public void popupTest() {		
		dsl.clicar("buttonPopUpEasy");
		driver.switchTo().window("Popup");
		dsl.escreve("textarea", "funciona diabo");
		driver.close();		
		driver.switchTo().window("");
		dsl.escreve("textarea", "funcionou carai");
	}
	
	@Test
	public void findIdTest() {			
		driver.findElement(By.id("buttonPopUpHard")).click();		
		Set<String> trem = driver.getWindowHandles();		
		System.out.println(driver.getWindowHandle());
		System.out.println(trem);		
		driver.switchTo().window((String)trem.toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("funciona diabo");
		driver.close();		
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("funcionou carai");		
	}
}

