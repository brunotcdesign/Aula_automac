import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FrameTest {
	
	private WebDriver driver;
	
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
		driver.findElement(By.id("frameButton")).click();		
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);	
	}
	
	
	@Test
	public void popupTest() {		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("funciona diabo");
		driver.close();		
		driver.switchTo().window("");		
		driver.findElement(By.tagName("textarea")).sendKeys("funcionou carai");		
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

