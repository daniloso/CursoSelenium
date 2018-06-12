import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}	
	@After
	public void finaliza() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	@Test
	public void teste() {
		page.setNome("Danilo Oliveira");
//		dsl.escrever("elementosForm:nome", "Danilo Oliveira");
		Assert.assertEquals("Danilo Oliveira", dsl.obterValorCampo("elementosForm:nome"));
		
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "teste\\n\\fadsfasdfasdasdfasdfasdfasdfa\\n\\fsdfasdfasdfasd");
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.radioButton("elementosForm:sexo:0");
		dsl.isRadioMarcado("elementosForm:sexo:0");
	}
	
	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		dsl.isCheckMarcado("elementosForm:comidaFavorita:2");
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Doutorado");
		Assert.assertEquals("Doutorado", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() throws InterruptedException {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());;
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

	}
	
	@Test
	public void deveInteragirComBotoes() throws InterruptedException {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));				
	}
	
	@Test
	public void deveInteragirComLinks() throws InterruptedException {
		dsl.clicarLinks("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));	
	}
	
	@Test
	public void deveBuscarTestosNaPagina() throws InterruptedException {
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testTextFieldDuplo() throws InterruptedException {
		dsl.escrever("elementosForm:nome", "Danilo");
		Assert.assertEquals("Danilo", dsl.obterValorCampo("elementosForm:nome"));
		Thread.sleep(2000);
		dsl.escrever("elementosForm:nome", "Carol");
		Assert.assertEquals("Carol", dsl.obterValorCampo("elementosForm:nome"));
	}
}
