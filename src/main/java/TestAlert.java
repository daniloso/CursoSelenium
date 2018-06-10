import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAlert {

	@Test
	public void deveInteragirComAlertSimples() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("alert")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Alert Simples", alert.getText());
		String texto = alert.getText();
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertConfirm() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		String texto = alert.getText();
		alert.dismiss();
		Thread.sleep(2000);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void deveInteragirComPrompt() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("prompt")).click();
		
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		alerta.accept();
		
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void desafioCadastroComSucesso() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Danilo");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Oliveira");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement elementoEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEscolaridade = new Select(elementoEscolaridade);
		comboEscolaridade.selectByVisibleText("Mestrado");
		
		WebElement elementoEsporte = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsporte = new Select(elementoEsporte);
		comboEsporte.selectByVisibleText("Karate");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Archetype Explanation\r\n" + 
				"The aim of Token Druid is to use a combinations of small minions, spawns, and buff cards together to create extremely powerful turns and solid board states.\r\n" + 
				"");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Assert.assertEquals("Nome: Danilo", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Oliveira", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Karate", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Archetype Explanation The aim of Token Druid is to use a combinations of small minions, spawns, and buff cards together to create extremely powerful turns and solid board states.", driver.findElement(By.id("descSugestoes")).getText());
		driver.findElement(By.id("descEsportes")).click();	
	}
}
