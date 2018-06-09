import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// maximize serve para abrir a pagina ja maximizada
		driver.manage().window().maximize();
		driver.get("https://www.google.com.br/");
		Assert.assertEquals("Google", driver.getTitle());
		
	}
}
