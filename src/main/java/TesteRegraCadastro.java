import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class TesteRegraCadastro {
	
	@Test
	public void desafioForm () {
		page.setNome("Teste");
		dsl.validar_value("Teste", "elementosForm:nome");
		page.setSobrenome("Testado");
		dsl.validar_value("Testado", "elementosForm:sobrenome");
		page.selectSexoM();
		dsl.checarSim("elementosForm:sexo:0");
		page.selecionarCombo("2o grau incompleto");
		dsl.validar_value("2grauincomp", "elementosForm:escolaridade");
		page.selecionarMultiCombo("Natacao");
		page.selecionarMultiCombo("Corrida");
		
		java.util.List<WebElement> todos = dsl.obterTodosValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, todos.size());
		
		page.cadastrar();
		dsl.validar_contains("resultado", "Cadastrado!");
	}

}
