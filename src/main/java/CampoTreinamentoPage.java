import core.DSL;

public class CampoTreinamentoPage {
	
	//O objetivo da classe page é centralizar a estrutura da página que será testada. 
	//Caso haja manutenção, ela será feita apenas na classe page, de forma a alterar todos os testes simultaneamente.
	//De forma contrária, a alteração teria que ser feita individualmente em cada teste.
	//Lista as interações com todos os campos da página.
	
	private DSL dsl;
	
	public CampoTreinamentoPage() {
		dsl = new DSL ();
	}
	
	public void setNome (String nome,String sobrenome) {
		dsl.escreve("elementosForm:nome", nome);
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void selectSexo (String option) {
		dsl.clicar("elementosForm:sexo:" + option);
	}
	
	public void selectFood (String option) {
		dsl.clicar("elementosForm:comidaFavorita:" + option);
	}
	
	public void selecionarEscolaridade (String texto_visivel) {
		dsl.selecionarCombo("elementosForm:escolaridade", texto_visivel);
	}
	
	public void selecionarEsportes (String texto_visivel) {
		dsl.selecionarCombo("elementosForm:esportes", texto_visivel);
	}
	
	public void cadastrar () {
		dsl.clicarBotao("elementosForm:cadastrar");
	}

}
