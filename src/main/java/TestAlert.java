import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAlert {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void deveInteragirComAlertSimples() throws InterruptedException {

		dsl.clicarBotao("alert");
		dsl.alertaObterTexto();
		String texto = dsl.alertaObterTextoEAceita();
		dsl.escrever(By.id("elementosForm:nome"), texto);
	}

	@Test
	public void deveInteragirComAlertConfirm() throws InterruptedException {
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTexto());
		String texto = dsl.alertaObterTextoENega();
		dsl.escrever(By.id("elementosForm:nome"), texto);
	}

	@Test
	public void deveInteragirComPrompt() throws InterruptedException {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void desafioCadastroComSucesso() throws InterruptedException {

		dsl.escrever("elementosForm:nome", "Danilo");
		dsl.escrever(By.id("elementosForm:sobrenome"), "Oliveira");
		dsl.radioButton("elementosForm:sexo:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.escrever(By.id("elementosForm:sugestoes"),"Eufemia Garcia desafia suspensão das buscas e sai todos os dias para tentar encontrar sinais de seus parentes em meio às cinzas. Autoridades alertam para risco de novas erupções do vulcão Fuego...");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Danilo", dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: Oliveira", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Karate", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Eufemia Garcia desafia suspensão das buscas e sai todos os dias para tentar encontrar sinais de seus parentes em meio às cinzas. Autoridades alertam para risco de novas erupções do vulcão Fuego...",
				dsl.obterTexto("descSugestoes"));
		driver.findElement(By.id("descEsportes")).click();
	}
}
