import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;

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
}
