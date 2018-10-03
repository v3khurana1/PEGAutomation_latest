package DataProviders;

import org.testng.annotations.*;

import Framework.ConfigurationProperties;
import common.Functions.CommonFunctions1;

public class iManage_DataProviderTestNG {
	CommonFunctions1 objFunctions = new CommonFunctions1();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	String Datasheet_iManage = System.getProperty("user.dir")
			+ configurationProperties.getProperty("Datasheet_iManage");

	public iManage_DataProviderTestNG() throws Exception {
		super();
		System.out.println(System.getProperty("user.dir"));
	}

	@DataProvider
	public Object[][] Login() throws Exception {
		return objFunctions.dataProvider("Login", Datasheet_iManage);
	}

	@DataProvider
	public Object[][] MyTemplates() throws Exception {
		return objFunctions.dataProvider("MyTemplates", Datasheet_iManage);
	}

	@DataProvider
	public Object[][] StandardDashboard() throws Exception {
		return objFunctions.dataProvider("StandardDashboard", Datasheet_iManage);
	}

	@DataProvider
	public Object[][] CustomDashboard() throws Exception {
		return objFunctions.dataProvider("CustomDashboard", Datasheet_iManage);
	}
	
	@DataProvider
	public Object[][] WorkbenchTrack() throws Exception {
		return objFunctions.dataProvider("WorkbenchTrack", Datasheet_iManage);
	}

	@DataProvider
	public Object[][] WorkbenchReview() throws Exception {
		return objFunctions.dataProvider("WorkbenchReview", Datasheet_iManage);
	}

	@DataProvider
	public Object[][] WorkbenchApproval() throws Exception {
		return objFunctions.dataProvider("WorkbenchApproval", Datasheet_iManage);
	}

	@DataProvider
	public Object[][] WorkbenchMyTasks() throws Exception {
		return objFunctions.dataProvider("WorkbenchMyTasks", Datasheet_iManage);
	}
	
	@DataProvider
	public Object[][] MyReports() throws Exception {
		return objFunctions.dataProvider("MyReports", Datasheet_iManage);
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
