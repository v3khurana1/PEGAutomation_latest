package DataProviders;

import org.testng.annotations.*;

import Framework.ConfigurationProperties;
import common.Functions.CommonFunctions1;

public class eInvoice_DataProviderTestNG {
	CommonFunctions1 objFunctions = new CommonFunctions1();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	String Datasheet_eInvoice = System.getProperty("user.dir")+configurationProperties.getProperty("Datasheet_eInvoice");

	public eInvoice_DataProviderTestNG() throws Exception {
		super();
		System.out.println(System.getProperty("user.dir"));
	}

	@DataProvider
	public Object[][] Login() throws Exception {
		return objFunctions.dataProvider("Login", Datasheet_eInvoice);
	}
	

	@DataProvider
	public Object[][] Invoices() throws Exception {
		return objFunctions.dataProvider("Invoices", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] ApprovalAction() throws Exception {
		return objFunctions.dataProvider("Approvals", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] MySettings() throws Exception {
		return objFunctions.dataProvider("MySettings", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] InvoiceNonPO() throws Exception {
		return objFunctions.dataProvider("InvoiceNonPO", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] CreditMemowithoutReference() throws Exception {
		return objFunctions.dataProvider("CreditMemowithoutReference", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] CreditMemoAgainstPO() throws Exception {
		return objFunctions.dataProvider("CreditMemoAgainstPO", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] InvoiceAgainstPO() throws Exception {
		return objFunctions.dataProvider("InvoiceAgainstPO", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] PurchaseOrders() throws Exception {
		return objFunctions.dataProvider("PurchaseOrders", Datasheet_eInvoice);
	}

	@DataProvider
	public Object[][] Workflow() throws Exception {
		return objFunctions.dataProvider("Workflow", Datasheet_eInvoice);
	}

	@DataProvider
	public Object[][] Reconciliation() throws Exception {
		return objFunctions.dataProvider("Reconciliation", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] MyReports() throws Exception {
		return objFunctions.dataProvider("MyReports", Datasheet_eInvoice);
	}


	@DataProvider
	public Object[][] Reports() throws Exception {
		return objFunctions.dataProvider("Reports", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] Uploads() throws Exception {
		return objFunctions.dataProvider("Uploads", Datasheet_eInvoice);
	}

	@DataProvider
	public Object[][] Batches() throws Exception {
		return objFunctions.dataProvider("Batches", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] NewBatch() throws Exception {
		return objFunctions.dataProvider("NewBatch", Datasheet_eInvoice);
	}
	
	@DataProvider
	public Object[][] ProcessForm() throws Exception {
		return objFunctions.dataProvider("ProcessForm", Datasheet_eInvoice);
	}
	
	static class TestData {
	    public String[] items;

	    public TestData(String... items) {
	        this.items = items; // should probably make a defensive copy
	    }

	    public String get(int x) {
	        return items[x];
	    }
	}
	
	
}

