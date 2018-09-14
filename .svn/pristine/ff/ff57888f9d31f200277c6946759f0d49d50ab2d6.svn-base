package Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameworkUtility {
	//ConfigurationProperties configurationProperties;
	
	public WebDriver getWebDriverInstance(String chromedriverpath) throws Exception {
		
		//System.out.println(configurationProperties.getProperty("chromedriverpath"));
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
}
