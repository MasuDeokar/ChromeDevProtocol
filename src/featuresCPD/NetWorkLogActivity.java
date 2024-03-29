package featuresCPD;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.network.Network;
import org.openqa.selenium.devtools.v121.network.model.Request;
import org.openqa.selenium.devtools.v121.network.model.Response;

public class NetWorkLogActivity {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./Server/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		DevTools devTool = driver.getDevTools();
		devTool.createSession();
	
		devTool.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
		
		devTool.addListener(Network.requestWillBeSent(), request -> 
		{
			@SuppressWarnings("unused")
			Request req = request.getRequest();
//			System.out.println(req.getUrl());
			
			
		});
		devTool.addListener(Network.responseReceived(), response ->
		{
			Response res =response.getResponse();
//			System.out.println(res.getUrl());
//			System.out.println(res.getStatus());
			if(res.getStatus().toString().startsWith("4"))
			{
				System.out.println(res.getUrl()+" is failing with status code.");
			}
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	}

}
