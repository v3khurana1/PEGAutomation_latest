package com.main;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataProviders.iContract_DataProviderTestNG;
import Framework.ConfigurationProperties;
import Framework.FrameworkUtility;
//import Framework.NavigationClass;
import SanityDefault.Login;
import common.Functions.CommonFunctions1;
import common.Functions.eInvoice_CommonFunctions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.IContract.ManageContracts.AuthorContract;
import com.zycus.IContract.ManageContracts.Repository;
import com.zycus.IContract.ManageContracts.SelectContractType;
import com.zycus.IContract.ManageContracts.Signers;
import com.zycus.IContract.ManageContracts.ContractDetails;
import com.zycus.IContract.ManageContracts.ContractSummary;
import com.zycus.IContract.ManageContracts.ContractingParty;
import com.zycus.IContract.ManageContracts.CreateContract;
import com.zycus.ZSN.MyContracts.ViewContracts;

public class FlowISource {

	private WebDriver driver;
	private WebDriver driver1;
	ExtentReports extent;
	ExtentTest logger;
	FrameworkUtility objFrameworkUtility = new FrameworkUtility();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	//private String Customer;
	private String Product = "iContract";
	private String contractNumber;
	eInvoice_CommonFunctions objFunctions = null;
	private String displayStyle;
	//CommonFunctions objZSNFunctions = null;

	public FlowISource() throws Exception {
		super();
	}
	
	/**
	 * @param contractNumber
	 *            the contractNumber to set
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@BeforeTest
	public void startReport() throws Exception {
		this.driver = objFrameworkUtility.getWebDriverInstance(
				System.getProperty("user.dir") + configurationProperties.getProperty("chromedriverpath"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		extent = new ExtentReports(System.getProperty("user.dir") + configurationProperties.getProperty("reportpath")
				+ "//Execution_Report_ " + Product +sdf.format(timestamp) + ".html", false);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/config/extent-config.xml"));
		objFunctions = new eInvoice_CommonFunctions(driver, logger);
	}


	@Test(description = "Login Method",
			dataProviderClass = iContract_DataProviderTestNG.class, 
			dataProvider = "Login", 
			priority = 1, 
			alwaysRun = true)
	public void Login(String Product, String Username, String Password, String Customer, String userAccount) throws Exception {
		//this.Customer = Customer;
		//this.Product = Product;
		objFunctions = new eInvoice_CommonFunctions(driver, logger, Product);
		//objFunctions = new eInvoice_CommonFunctions(driver, logger, "iContract");
		logger = extent.startTest("Login");
		Login objLogin = new Login(driver, logger, "iContract", Username, Password, Customer, userAccount);
		displayStyle = objLogin.Login_via_PwdMgr1(configurationProperties);
		callAndLog(displayStyle.equals(null)?false:true, "login successful and Product Selected", "Not logged in or Product Not selected");
	}
	
	
	
	@Test(description = "",
			dependsOnMethods = "Login",
			priority = 3)
	public void AuthorContract() throws Exception {
		logger = extent.startTest("Author Contract");
		objFunctions = new eInvoice_CommonFunctions(driver, logger);
		objFunctions.navigateToMainPage(displayStyle, "Manage Contracts", "Author Contract");
		}
	
	
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
