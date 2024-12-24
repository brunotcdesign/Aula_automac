import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import core.DSL;

public class TesteCampoTreinamento {

	//o before faz o método se inicializar antes de todos os testes.
	// o método inicializa, resume uma lógica que se repete várias vezes durane o código.
	// para que essa técnica de DRY (enxugar o código) funcione, é reciso criar uma variável global.
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
		
	@Before
	public void inicializa () {
		System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
		driver  = new ChromeDriver();
		driver.manage().window().maximize();		
		driver.get("file:///C:/Users/User/OneDrive/Documentos/AULA/Java/Workspace%20-%20eclipse/Deckbox/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage (driver);
		
	}
	
	
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void txtField () {	
		page.setNome("Teste", "Testado");

	}	
	
	@Test
	public void txtArea () {
		dsl.escreve("elementosForm:sugestoes","Teste01\nTeste02\nTeste03");
	}
	
	@Test
	public void checkBox () {
		dsl.clicar("elementosForm:sexo:0");
		Assert.assertTrue(dsl.checarSim("elementosForm:sexo:0"));
	}	
	
	@Test
	public void comboBox () {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}	
	
	@Test
	public void comboBoxMultiple () {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		java.util.List<WebElement> todos = combo.getAllSelectedOptions();
		Assert.assertEquals(2, todos.size());
	}
	
	@Test
	public void findButton () {	
		//instacia um novo objeto web element chamado button
		WebElement button = driver.findElement(By.id("buttonSimple"));
		//utiliza o método click da classe webelement no objeto button
		button.click();
		Assert.assertEquals("Obrigado!", button.getAttribute("value"));
	}	
	
	@Test
	public void findText () {
		WebElement body = driver.findElement(By.tagName("h3"));
		Assert.assertTrue(body.getText().contains("Campo de Treinamento"));
	}	
	
	@Test
	public void findAlert () {
		dsl.clicar("alert");
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		dsl.escreve("elementosForm:nome", texto);
	}		
	
	@Test
	public void verificarTabela () {
		
	}
}

