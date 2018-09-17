package Framework;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
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
