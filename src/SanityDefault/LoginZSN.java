package SanityDefault;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Framework.ConfigurationProperties;
import common.Functions.*;

public class LoginZSN extends CommonFunctions {

	private WebDriver driver;
	private String username;
	private String password;
	//private String customer;
	private ExtentTest logger;

	/*
	 * public LoginZSN(WebDriver driver, ExtentReports extent, String username,
	 * String password, String customer) throws Exception { super(driver,
	 * extent); this.driver = driver; this.extent = extent; this.username =
	 * username; this.password = password; this.customer = customer; }
	 */

	public LoginZSN(WebDriver driver, ExtentTest logger, String username, String password)
			throws Exception {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.username = username;
		this.password = password;
	//	this.customer = customer;
	}

	public boolean login(ConfigurationProperties configurationProperties) {
		boolean result = false;
		try {
			String URL = configurationProperties.getProperty("url_ZSN");
			
			//Launch URL
			driver.manage().window().maximize();
			driver.get(URL);
			
			//Enter credentials & click Login button
			sendKeys(By.id("login-email"), username);
			logger.log(LogStatus.INFO, "username entered is " + username);
			sendKeys(By.id("login-password"),
					new String(DatatypeConverter.parseBase64Binary(password), StandardCharsets.UTF_8));
			click(By.xpath("//div/input[contains(@class,'btn btn-primary')]"));
			
			try{
				if (findElement(By.xpath("//img[@class='zsp-logo-image']")).isDisplayed()) {
					waitUntilInvisibilityOfElement(By.id("notification-overlay"));
					LogScreenshot("INFO", "logged in to ZSN portal");
					result = true;
				}else{
					logger.log(LogStatus.INFO, "home page not displayed");
					return result;
				}
			}catch(Exception e){
				return result;
			}
			/*if (findElement(By.xpath("//div[contains(@class,'error-message')]")).isDisplayed()) 
				logger.log(LogStatus.INFO, "unable to log in to ZSN portal");*/
		} catch (Exception e) {
			System.out.println("I am in exception");
		}
		return result;

	}

}
