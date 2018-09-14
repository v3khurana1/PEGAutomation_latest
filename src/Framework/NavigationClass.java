package Framework;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class NavigationClass extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private Object product;
	private String tab;
	private String subTab;
	
	public NavigationClass(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public NavigationClass(WebDriver driver, ExtentTest logger, String Product, String tab, String subTab) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.product = product;
		this.tab = tab;
		this.subTab = subTab;
		
	}
	
	
	
}
