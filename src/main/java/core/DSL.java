package core;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebgetDriver
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebgetDriver()Wait;

//Consiste em criar métodos específicos que podem ser facilmente re-utilizados.
//Promove a limpeza do código e uma linguagem mais objetiva e clara para o código.

public class DSL {
	
	
	
// Cria um métododo que usa como argumento, os argumentos do código em seu escopo.
	
	public void escreve (String id_campo, String texto ) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValor (String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	
	public void clicar (String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();
	}
	
	public WebElement clicarBotao (String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();
		return null;
	}
	
	public boolean	checarSim (String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).isSelected();
		
	}
	
	public void selecionarCombo (String id_campo, String valor) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo (String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<WebElement> obterTodosValoresCombo (String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		return combo.getAllSelectedOptions();
	}
	
	public  void validar_value (String valor_alvo, String id_campo) {
		Assert.assertEquals(valor_alvo, DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value"));

	}
	
	public  void validar_contains (String id_campo, String valor_alvo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Assert.assertTrue(element.getText().contains(valor_alvo));

	}

	public void espera_segundos (int time) {
	//	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		
	    WebgetDriver()Wait wait = new WebgetDriver()Wait(DriverFactory.getDriver(), Duration.ofSeconds(time));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		
	}
	
}

