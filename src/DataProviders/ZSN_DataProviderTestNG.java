package DataProviders;

import org.testng.annotations.*;

import Framework.ConfigurationProperties;
import common.Functions.CommonFunctions1;

public class ZSN_DataProviderTestNG {
	CommonFunctions1 objFunctions = new CommonFunctions1();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	String Datasheet_ZSN = System.getProperty("user.dir")+configurationProperties.getProperty("Datasheet_ZSN");
	
	
	public ZSN_DataProviderTestNG() throws Exception {
		super();
		System.out.println(System.getProperty("user.dir"));
	}

	@DataProvider
	public Object[][] Login() throws Exception {
		return objFunctions.dataProvider("Login", Datasheet_ZSN);
	}

	@DataProvider
	public Object[][] ViewInvoice() throws Exception {
		return objFunctions.dataProvider("ViewInvoice", Datasheet_ZSN);
	}

	@DataProvider
	public Object[][] POInvoice() throws Exception {
		return objFunctions.dataProvider("POInvoice", Datasheet_ZSN);
	}
	
	@DataProvider
	public Object[][] NonPOInvoice() throws Exception {
		return objFunctions.dataProvider("NonPOInvoice", Datasheet_ZSN);
	}


	@DataProvider
	public Object[][] Dashboard() throws Exception {
		return objFunctions.dataProvider("Dashboard", Datasheet_ZSN);
	}
	@DataProvider
	public Object[][] 	CreditMemowithoutReference() throws Exception {
		return objFunctions.dataProvider("CreditMemowithoutReference", Datasheet_ZSN);
	}

	@DataProvider
	public Object[][] Order() throws Exception {
		return objFunctions.dataProvider("Order", Datasheet_ZSN);
	}
	
	@DataProvider
	public Object[][] Catalog() throws Exception {
		return objFunctions.dataProvider("Catalog", Datasheet_ZSN);
	}
}
