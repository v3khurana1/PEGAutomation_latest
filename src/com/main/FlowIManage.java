package com.main;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataProviders.eInvoice_DataProviderTestNG;
import DataProviders.iManage_DataProviderTestNG;
import Framework.ConfigurationProperties;
import Framework.FrameworkUtility;
import SanityDefault.Login;
import common.Functions.iManage_CommonFunctions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.eInvoice.Invoice.Invoices;
import com.zycus.iManage.Admin.*;
import com.zycus.iManage.Dashboard.*;
import com.zycus.iManage.MyConfiguration.*;
import com.zycus.iManage.MyReports.*;
import com.zycus.iManage.MyTemplates.*;
import com.zycus.iManage.Workbench.*;


public class FlowIManage {

	private WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	FrameworkUtility objFrameworkUtility = new FrameworkUtility();
	iManage_CommonFunctions objFunctions = new iManage_CommonFunctions(driver, logger);
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	// private String Customer;
	private String displayStyle;
	private String Product;

	public FlowIManage() throws Exception {
		super();
	}

	@BeforeTest
	public void startReport() throws Exception {
		this.driver = objFrameworkUtility.getWebDriverInstance(
				System.getProperty("user.dir") + configurationProperties.getProperty("chromedriverpath"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		extent = new ExtentReports(System.getProperty("user.dir") + configurationProperties.getProperty("reportpath")
				+ "//Execution_Report_eInvoice " + sdf.format(timestamp) + ".html", false);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/config/extent-config.xml"));
		objFunctions = new iManage_CommonFunctions(driver, logger);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		logger = extent.startTest(method.getName());
	}

	/**
	 * @param Product
	 * @param Username
	 * @param Password
	 * @param Customer
	 * @throws Exception
	 */
	
	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "Login",
			priority = 1, 
			alwaysRun = true)
	public void Login(String Product, String Username, String Password, String Customer, String userAccount)
			throws Exception {
		this.Product = Product;
		Login objLogin = new Login(driver, logger, Product, Username, Password, Customer, userAccount);
		this.displayStyle = objLogin.Login_via_PwdMgr1(configurationProperties);
		callAndLog(displayStyle.equals(null) ? false : true, "login successful and Product Selected",
				"Not logged in or Product Not selected");
	}
	
	
/*	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "MyTemplates", 
			dependsOnMethods = "Login", 
			priority = 2)
	public void MyTemplates(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	
	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "StandardDashboard", 
			dependsOnMethods = "Login", 
			priority = 3)
	public void StandardDashboard(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	
	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "CustomDashboard", 
			dependsOnMethods = "Login", 
			priority = 4)
	public void CustomDashboard(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	*/
	
	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "WorkbenchTrack", 
			dependsOnMethods = "Login", 
			priority = 5)
	public void WorkbenchTrack(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
		Track objTrack = new Track(driver, logger);
		
		callAndLog(objTrack.createProgram(), "able to create Strategic Project",
				"able to create Strategic Project");
	}
	
	/*@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "WorkbenchReview", 
			dependsOnMethods = "Login", 
			priority = 6)
	public void WorkbenchReview(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	

	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "WorkbenchApproval", 
			dependsOnMethods = "Login", 
			priority = 7)
	public void WorkbenchApproval(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	

	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "WorkbenchMyTasks", 
			dependsOnMethods = "Login", 
			priority = 8)
	public void WorkbenchMyTasks(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	
	@Test(dataProviderClass = iManage_DataProviderTestNG.class, 
			dataProvider = "MyReports", 
			dependsOnMethods = "Login", 
			priority = 9)
	public void MyReports(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle,Product,navigationTabs);
	}
	*/
	
	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void tearDown() throws Exception {
		extent.flush();
		extent.close();
		// driver.quit();
	}

	/**
	 * call and log
	 * 
	 * @throws Exception
	 */
	public void callAndLog(boolean condition, String passMsg, String failMsg) throws Exception {

		String msg = condition ? passMsg : failMsg;
		if (condition)
			logger.log(LogStatus.PASS, msg);
		else {
			String screenshotPath = objFunctions.getScreenhot(driver, "screenshot_error");
			logger.log(LogStatus.FAIL, msg + logger.addScreenCapture(screenshotPath));
		}
	}

}
