import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//Consiste em criar métodos específicos que podem ser facilmente re-utilizados.
//Promove a limpeza do código e uma linguagem mais objetiva e clara para o código.

public class DSL {
	
	
	private WebDriver driver;
	
	public DSL (WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	
// Cria um métododo que usa como argumento, os argumentos do código em seu escopo.
	
	public void escreve (String id_campo, String texto ) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValor (String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	
	public void clicar (String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	
	public WebElement clicarBotao (String id_campo) {
		driver.findElement(By.id(id_campo)).click();
		return null;
	}
	
	public boolean	checarSim (String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
		
	}
	
	public void selecionarCombo (String id_campo, String valor) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo (String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<WebElement> obterTodosValoresCombo (String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		return combo.getAllSelectedOptions();
	}
	
	public  void validar_value (String valor_alvo, String id_campo) {
		Assert.assertEquals(valor_alvo, driver.findElement(By.id(id_campo)).getAttribute("value"));

	}
	
	public  void validar_contains (String id_campo, String valor_alvo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Assert.assertTrue(element.getText().contains(valor_alvo));

	}
	
}

