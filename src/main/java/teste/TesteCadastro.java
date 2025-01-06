package teste;

import static core.DriverFactory.getDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import core.DSL;
import core.DriverFactory;
import dev.failsafe.internal.util.Assert;
import page.CampoTreinamentoPage;

public class TesteCadastro {

	// Criar toda a mecânica de interação.
	// Transformá-la em uma DSL para facilitar a criação de testes.
	// Utilizar a object page para criar o objeto de testes, centralizando a
	// manutenção do alvo dos testes.
	//

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		// instancia o driver através da classe DriverFactory
		String driverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html";
		getDriver().get(driverPath);
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@After
	public void finaliza() {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String driverPath = System.getProperty("user.dir") + "\\src\\main\\screenshots\\satanas" + timestamp + ".png";
		DSL.takeScreenshot(DriverFactory.getDriver(), driverPath);
		DriverFactory.killDriver();
		System.out.println("ok");
	}

	@Test
	public void testeNomes() {
		page.setNome("Teste", "Testado");
	}

	@Test
	public void testSelectBoxes() {
		page.selectSexo("0");
		page.selectFood("0");
		page.selectFood("1");
	}

	@Test
	public void testSelectOptions() {
		page.selecionarEscolaridade("2o grau incompleto");
		page.selecionarEsportes("Natacao");
		page.selecionarEsportes("Corrida");

		java.util.List<WebElement> todos = dsl.obterTodosValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, todos.size());

	}

	@Test
	public void testFrame() {
		getDriver().switchTo().frame("frame1");
		dsl.clicar("frameButton");
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		getDriver().switchTo().defaultContent();
		dsl.escreve("elementosForm:sugestoes", msg);
	}

	@Test
	public void testLoading() {

		dsl.clicar("buttonDelay");

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));

		dsl.espera_segundos(30);
		dsl.escreve("novoCampo", "Testado!");
	}

	@Test
	public void testCadastro() {
		page.setNome("Teste", "Testado");
		page.selectSexo("0");
		page.selectFood("0");
		page.selectFood("1");
		page.selecionarEscolaridade("2o grau incompleto");
		page.selecionarEsportes("Natacao");
		page.selecionarEsportes("Corrida");
		page.cadastrar();
		dsl.validar_contains("resultado", "Cadastrado!");
	}
}
