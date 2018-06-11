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


public class TesteFramesEJanelas {
	
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
	public void deveInteragirComFrames() throws InterruptedException {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);			
	}
	
	@Test
	public void deveInteragirJanelas() throws InterruptedException {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo");
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window("");
		dsl.escrever(By.tagName("textarea"), "e agora");		
	}
	
	@Test
	public void deveInteragirJanelasSemTitulo() throws InterruptedException {
		dsl.clicarBotao("buttonPopUpHard");
		//Janela corrente (como se fosse o id da janela
		System.out.println(driver.getWindowHandle());
		//Aqui corresponde a todas as janelas (atual e popup)
		System.out.println(driver.getWindowHandles());
		dsl.trocarJanela((String)driver.getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		Thread.sleep(2000);
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "E agora");		
	}
	
	@Test
	public void desafioTestarRNNome() throws InterruptedException {
		dsl.clicarBotao("elementosForm:cadastrar");
		String msg = dsl.alertaObterTextoEAceita();		
		Assert.assertEquals("Nome eh obrigatorio", msg);		
	}
	
	@Test
	public void desafioTestarRNSobrenome() throws InterruptedException {
		dsl.escrever("elementosForm:nome", "Danilo");
		dsl.clicarBotao("elementosForm:cadastrar");		
		String msg = dsl.alertaObterTextoEAceita();		
		Assert.assertEquals("Sobrenome eh obrigatorio", msg);
	}
	
	@Test
	public void desafioTestarRNSexo() throws InterruptedException {
		dsl.escrever("elementosForm:nome", "Danilo");
		dsl.escrever("elementosForm:sobrenome", "Oliveira");
		dsl.clicarBotao("elementosForm:cadastrar");		
		String msg = dsl.alertaObterTextoEAceita();	
		Assert.assertEquals("Sexo eh obrigatorio", msg);		
	}
	
	@Test
	public void desafioTestarRNComidaFavorita() throws InterruptedException {
		dsl.escrever("elementosForm:nome", "Danilo");
		dsl.escrever("elementosForm:sobrenome", "Oliveira");
		dsl.radioButton("elementosForm:sexo:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:3");
				
		dsl.clicarBotao("elementosForm:cadastrar");
	
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);		
	}
	
	@Test
	public void desafioTestarRNEsportes() throws InterruptedException {
		dsl.escrever("elementosForm:nome", "Danilo");
		dsl.escrever("elementosForm:sobrenome", "Oliveira");
		dsl.radioButton("elementosForm:sexo:0");
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
				
		dsl.clicarBotao("elementosForm:cadastrar");
	
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Voce faz esporte ou nao?", msg);
	}
}
