package com.main;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zycus.eProc.Requisition.Requisition_OnlineStore;
import com.zycus.eProc.Requisition.Requisition_OnlineStore_SearchResults;

import DataProviders.EProc_DataProviderTestNG;
import Framework.ConfigurationProperties;
import Framework.FrameworkUtility;
import SanityDefault.Login;
import common.Functions.eProc_CommonFunctions;
//import Framework.CommonUtility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FlowEProc {

	private WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	//private Actions action;
	FrameworkUtility objFrameworkUtility = new FrameworkUtility();
	//private CommonUtility objUtility;
	eProc_CommonFunctions objFunctions = new eProc_CommonFunctions();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	private String Customer;

	
	public FlowEProc() throws Exception {
		super();
	}

	@BeforeTest
	public void startReport() throws Exception {
		this.driver = objFrameworkUtility.getWebDriverInstance(System.getProperty("user.dir") + configurationProperties.getProperty("chromedriverpath"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		extent = new ExtentReports(System.getProperty("user.dir") + configurationProperties.getProperty("reportpath") + "//Execution_Report_eProc "+ sdf.format(timestamp) + ".html", false);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/config/extent-config.xml"));
		/*modifiedExtent = new ModifiedExtentReport(extent,
				configurationProperties.getProperty("reportpath") + "//Execution_Report_ZSN.html");*/
		objFunctions = new eProc_CommonFunctions(driver, logger);
	}

	/**
	 * @param Product
	 * @param Username
	 * @param Password
	 * @param Customer
	 * @throws Exception
	 */
	@Test(dataProviderClass = EProc_DataProviderTestNG.class, dataProvider = "Login", priority = 1, alwaysRun = true)
	public void Login(String Product, String Username, String Password, String Customer) throws Exception {
		this.Customer = Customer;
		logger = extent.startTest("Login");
		Login objLogin = new Login(driver, logger, Product, Username, Password, Customer);
		callAndLog(objLogin.Login_via_PwdMgr(configurationProperties), "login successful", "Not logged in");
	}
	
	/**
	 * @param Product
	 * @param Username
	 * @param Password
	 * @param Customer
	 * @throws Exception
	 */
	@Test(dataProviderClass = EProc_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "OnlineStore", alwaysRun = true)
	public void OnlineStore(String SearchItem, String quantity) throws Exception {
		Requisition_OnlineStore objReqOnline = new Requisition_OnlineStore(driver, logger);
		if(objReqOnline.searchItemFromCatalog(SearchItem))
			System.out.println("Catalog item searched");
		Requisition_OnlineStore_SearchResults objSearchRes = new Requisition_OnlineStore_SearchResults(driver, logger);
		if(objSearchRes.addItemToCart(Integer.parseInt(quantity)))
			System.out.println(quantity + " items added to cart");
	}
	
	/**
	 * @param Product
	 * @param Username
	 * @param Password
	 * @param Customer
	 * @throws Exception
	 */
	@Test(dataProviderClass = EProc_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "OnlineStore", alwaysRun = true)
	public void Punchouts(String SearchItem, String quantity) throws Exception {
		Requisition_OnlineStore objReqOnline = new Requisition_OnlineStore(driver, logger);
		if(objReqOnline.viewAllPunchouts())
			System.out.println("Punchouts page displayed");
		/*if(objReqOnline.searchItemFromCatalog(SearchItem))
			System.out.println("Catalog item searched");
		Requisition_OnlineStore_SearchResults objSearchRes = new Requisition_OnlineStore_SearchResults(driver, logger);
		if(objSearchRes.addItemToCart(Integer.parseInt(quantity)))
			System.out.println(quantity + " items added to cart");*/
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else

			System.out.println("after method ");
		if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("after test ");
		extent.flush();
		extent.close();
		driver.quit();
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
