import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
		System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
		driver  = new ChromeDriver();
		driver.manage().window().maximize();		
		driver.get("file:///C:/Users/User/OneDrive/Documentos/AULA/Java/Workspace%20-%20eclipse/Deckbox/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
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
		
		page.cadastrar();
		dsl.validar_contains("resultado", "Cadastrado!");
	}
}
