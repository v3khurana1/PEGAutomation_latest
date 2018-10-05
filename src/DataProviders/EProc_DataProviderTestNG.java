package DataProviders;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentTest;

import Framework.ConfigurationProperties;
import common.Functions.eProc_CommonFunctions;

public class EProc_DataProviderTestNG {
	private WebDriver driver;
	private ExtentTest logger;
	
	eProc_CommonFunctions objFunctions = new eProc_CommonFunctions(driver, logger);
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	String Datasheet_eProc = System.getProperty("user.dir")+configurationProperties.getProperty("Datasheet_eProc");

	public EProc_DataProviderTestNG() throws Exception {
		super();
	}

	@DataProvider
	public Object[][] Login() throws Exception {
		// System.out.println(configurationProperties.getProperty("filepath"));
		return objFunctions.dataProvider("Login", Datasheet_eProc);
		// }
	}

	@DataProvider
	public Object[][] OnlineStore() throws Exception {
		return objFunctions.dataProvider("Requisition_OnlineStore", Datasheet_eProc);
	}

}
