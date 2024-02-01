package featuresCPD;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./Server/chromedriver.exe");
		ChromeDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		DevTools devTools = driver.getDevTools();	
		devTools.createSession();
		
		Map<String, Object> coordinates = new HashMap<String, Object>();
		
		coordinates.put("latitude",17);
		coordinates.put("longitude",78); 
		coordinates.put("accuracy",10);
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		driver.get("https://google.com");
		driver.findElement(By.cssSelector("textarea[class='gLFyf']")).sendKeys("netflix",Keys.ENTER);
		Thread.sleep(2000);
		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
		Thread.sleep(2000);
		String title= driver.findElement(By.cssSelector(".default-ltr-cache-jpuyb8")).getText();
		System.out.println(title);
	}

}
