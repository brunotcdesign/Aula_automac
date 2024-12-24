import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.DSL;
import core.DriverFactory;

public class TesteCadastro {
	
	
	//Criar toda a mecânica de interação.
	//Transformá-la em uma DSL para facilitar a criação de testes.
	//Utilizar a object page para criar o objeto de testes, centralizando a manutenção do alvo dos testes.
	//
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	
	@Before
	public void inicializa () {
		//instancia o driver através da classe DriverFactory
		DriverFactory.getDriver().get("file:///C:/Users/User/OneDrive/Documentos/AULA/Java/Workspace%20-%20eclipse/Deckbox/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
		System.out.println("ok");
	}
	
	@Test
	public void desafioForm () {
		page.setNome("Teste", "Testado");
		page.selectSexo("0");
		page.selectFood("0");
		page.selectFood("1");
		page.selecionarEscolaridade("2o grau incompleto");
		page.selecionarEsportes("Natacao");
		page.selecionarEsportes("Corrida");
		
		java.util.List<WebElement> todos = dsl.obterTodosValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, todos.size());
		
		driver.switchTo().frame("frame1");
		dsl.clicar("frameButton");
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();		
		driver.switchTo().defaultContent();
		dsl.escreve("elementosForm:sugestoes", msg);
		
		dsl.clicar("buttonDelay");
		
	  //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	//    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
	    
		dsl.espera_segundos(30);
		dsl.escreve("novoCampo", msg);

		page.cadastrar();
		dsl.validar_contains("resultado", "Cadastrado!");
	}
}
