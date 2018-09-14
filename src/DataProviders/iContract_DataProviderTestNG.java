package DataProviders;

import org.testng.annotations.*;

import Framework.ConfigurationProperties;
import common.Functions.CommonFunctions1;

public class iContract_DataProviderTestNG {
	CommonFunctions1 objFunctions = new CommonFunctions1();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	String Datasheet_iContract = System.getProperty("user.dir")+configurationProperties.getProperty("Datasheet_iContract");
	
	
	public iContract_DataProviderTestNG() throws Exception {
		super();
		System.out.println(System.getProperty("user.dir"));
	}

	@DataProvider
	public Object[][] Login() throws Exception {
		return objFunctions.dataProvider("Login", Datasheet_iContract);
	}
	

	/*@DataProvider
	public Object[][] Invoices() throws Exception {
		return objFunctions.dataProvider("Invoices", Datasheet_iContract);
	}
	
	@DataProvider
	public Object[][] InvoiceNonPO() throws Exception {
		return objFunctions.dataProvider("InvoiceNonPO", Datasheet_iContract);
	}
	@DataProvider
	public Object[][] CreditMemowithoutReference() throws Exception {
		return objFunctions.dataProvider("CreditMemowithoutReference", Datasheet_iContract);
	}
	
	@DataProvider
	public Object[][] PurchaseOrders() throws Exception {
		return objFunctions.dataProvider("PurchaseOrders", Datasheet_iContract);
	}

	@DataProvider
	public Object[][] Workflow() throws Exception {
		return objFunctions.dataProvider("Workflow", Datasheet_iContract);
	}

	@DataProvider
	public Object[][] Reconciliation() throws Exception {
		return objFunctions.dataProvider("Reconciliation", Datasheet_iContract);
	}
	
	@DataProvider
	public Object[][] MyReports() throws Exception {
		return objFunctions.dataProvider("MyReports", Datasheet_iContract);
	}


	@DataProvider
	public Object[][] Reports() throws Exception {
		return objFunctions.dataProvider("Reports", Datasheet_iContract);
	}
	
	@DataProvider
	public Object[][] Uploads() throws Exception {
		return objFunctions.dataProvider("Uploads", Datasheet_iContract);
	}

	@DataProvider
	public Object[][] Batches() throws Exception {
		return objFunctions.dataProvider("Batches", Datasheet_iContract);
	}
	
	@DataProvider
	public Object[][] NewBatch() throws Exception {
		return objFunctions.dataProvider("NewBatch", Datasheet_iContract);
	}
	
	@DataProvider
	public Object[][] ProcessForm() throws Exception {
		return objFunctions.dataProvider("ProcessForm", Datasheet_iContract);
	}*/
	
}
