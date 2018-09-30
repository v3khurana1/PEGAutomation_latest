package com.main;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
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

public class FlowIContract {

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

	public FlowIContract() throws Exception {
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
	
	@BeforeMethod
	public void beforeMethod(Method method){
		logger = extent.startTest(method.getName());
	}


	@Test(description = "Login Method",
			dataProviderClass = iContract_DataProviderTestNG.class, 
			dataProvider = "Login", 
			priority = 1, 
			alwaysRun = true)
	public void Login(String Product, String Username, String Password, String Customer, String userAccount) throws Exception {
		objFunctions = new eInvoice_CommonFunctions(driver, logger, Product);
		Login objLogin = new Login(driver, logger, "iContract", Username, Password, Customer, userAccount);
		displayStyle = objLogin.Login_via_PwdMgr1(configurationProperties);
		callAndLog(displayStyle.equals(null)?false:true, "login successful and Product Selected", "Not logged in or Product Not selected");
	}
	
	
	/*@Test(description = "",
			dependsOnMethods = "Login",
			priority = 1)
	public void Repository() throws Exception {
		objFunctions = new eInvoice_CommonFunctions(driver, logger);
		//NavigationClass objNavigate = new NavigationClass(driver, logger, Product, tab, subTab)
		objFunctions.navigateToMainPage(displayStyle, "Manage Contracts", "Repository");
		//objFunctions.navigate_Rainbowpath("Manage Contracts", "Repository");
		Repository objRepo = new Repository(driver, logger); 
		SelectContractType objSelectContract = new SelectContractType(driver, logger, "Procurement", "Purchase Agreement");
		objRepo.uploadContract(objSelectContract);
		ContractDetails objDetails = new ContractDetails(driver, logger);
		objSelectContract.selectContractTypes(objDetails);
		ContractingParty objContParty = new ContractingParty(driver, logger);
		objDetails.enterContractDetails(objContParty);
	}
	
	@Test(description = "",
			dependsOnMethods = "Login",
			priority = 3)
	public void AuthorContract() throws Exception {
		objFunctions = new eInvoice_CommonFunctions(driver, logger);
		objFunctions.navigateToMainPage(displayStyle, "Manage Contracts", "Author Contract");
		//objFunctions.navigate_Rainbowpath("Manage Contracts", "Author Contract");
		AuthorContract objAuthor = new AuthorContract(driver, logger);
		CreateContract objContract = new CreateContract(driver, logger);
		objAuthor.createContract(objContract);
		//For IGT
		//objContract.startAuthoring("Procurement", "Direct Material", false,false);
		// For CDK
		//objContract.startAuthoring("Procurement", "Master Business Agreement", true, false);
		//For Mass Mutual - Staging
		//objContract.startAuthoring("Master Agreement", "Hosted Agreement", false, false);
		//For Securian - Staging
		//objContract.startAuthoring("Procurement Agreements", "Services Agreement", true, false);
		//For Lowes - Staging
		//objContract.startAuthoring("Indirect", "Facilities Management", true, false);
		//For Frontier Communications - Staging
		//objContract.startAuthoring("Procurement", "Master Agreement", true, true); //Currently no templates present
		//For AXA Equitable Life Insurance - Staging
		//objContract.startAuthoring("Master", "US MPSA", false, true);
		//For ZCS - GDQA-P2P - Production - Navisite
		//objContract.startAuthoring("Procurement", "Purchase Agreement", true,false);
		//For Sanity6 - Bruce - AWS Prod - UK
		ContractDetails objDetails = new ContractDetails(driver, logger);
		objContract.startAuthoring("Procurement", "Purchase Agreement", false,true, objDetails);
		ContractingParty objContParty = new ContractingParty(driver, logger);
		objDetails.enterContractDetails(objContParty);
		//ContractingParty objContParty = new ContractingParty(driver, logger);
		/*objContParty.addContractingParty("TEST IGT", "Suresh Jambhalkar");
		objContract.navigate_ContractSubTabs("Contract Summary");
		ContractSummary objSummary = new ContractSummary(driver, logger);
		this.setContractNumber(objSummary.getContractNum());
		objSummary.sendForNegotiation("negotiationComment");
		objSummary.sendToContractingParty();
		this.driver1 = objFrameworkUtility.getWebDriverInstance(
				System.getProperty("user.dir") + configurationProperties.getProperty("chromedriverpath"));
		Login objLogin = new Login(driver1, logger, "suresh.jambhalkar@zycus.com", "Pass@1234");
		callAndLog(objLogin.login(configurationProperties), "login successful", "Not logged in");
		CommonFunctions1 objZSNFunctions = new CommonFunctions1(driver1, logger);
		objZSNFunctions.navigate_path1("My Contracts", "View Contracts", "IGT");
		ViewContracts objViewContracts = new ViewContracts(driver1, logger);
		callAndLog(objViewContracts.performAction(contractNumber,"Mark as Reviewed"), "able to perform action on Contract", "unable to perform action on Contract");
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
		//driver.switchTo().window("Authoring Contract");
		objSummary.closeContract(objAuthor);
		objAuthor.filterByContractNum(contractNumber);
		objAuthor.viewContract(contractNumber, objSummary);
		Signers objSigners = new Signers(driver, logger);
		objSummary.proceedToSignOff("Offline Signing", objSigners);
		callAndLog(objViewContracts.performAction(contractNumber,"Download"), "able to perform action on Contract", "unable to perform action on Contract");
	}*/
	
	@Test(description = "",
			dependsOnMethods = "Login",
			priority = 3)
	public void AuthorContract() throws Exception {
		objFunctions = new eInvoice_CommonFunctions(driver, logger);
		objFunctions.navigateToMainPage(displayStyle, "Manage Contracts", "Author Contract");
		}
	
	/*@Test(description = "",
			dependsOnMethods = "AuthorContract",
			priority=3)
	public void AddContractingParty() throws Exception {
		ContractingParty objContParty = new ContractingParty(driver, logger);
		//For IGT & CDK in Partner
		//objContParty.addContractingParty("TEST IGT", "Suresh Jambhalkar");
		//For AXA in Staging
		objContParty.addContractingParty("USER TESTING, INC.", "Hinal Shah");
	}
	
	@Test(description = "",
			dependsOnMethods = "AddContractingParty",
			priority=3)
	public void SendToContractingPartyForNegotiation() throws Exception {
		CreateContract objContract = new CreateContract(driver, logger);
		objContract.navigate_ContractSubTabs("Contract Summary");
		ContractSummary objSummary = new ContractSummary(driver, logger);
		this.setContractNumber(objSummary.getContractNum());
		objSummary.sendForNegotiation("negotiationComment");
		objSummary.sendToContractingParty();
	}
	
	@Test(description = "",
			dependsOnMethods = "SendToContractingPartyForNegotiation",
			priority=3)
	public void ContractingPartMarkingReviewed() throws Exception {
		this.driver1 = objFrameworkUtility.getWebDriverInstance(
				System.getProperty("user.dir") + configurationProperties.getProperty("chromedriverpath"));
		Login objLogin = new Login(driver1, logger, "hinal.shah@zycus.com", "Mansi@123");
		callAndLog(objLogin.login(configurationProperties), "login successful", "Not logged in");
		CommonFunctions1 objZSNFunctions = new CommonFunctions1(driver1, logger);
		objZSNFunctions.navigate_path1("My Contracts", "View Contracts", "AXA Equitable Life Insurance Company");
		ViewContracts objViewContracts = new ViewContracts(driver1, logger);
		callAndLog(objViewContracts.performAction(contractNumber,"Mark as Reviewed"), "able to perform action on Contract", "unable to perform action on Contract");
		
	}
	
	@Test(description = "",
			dependsOnMethods = "ContractingPartMarkingReviewed",
			priority=3)
	public void SendingContractForSignoff() throws Exception {
		AuthorContract objAuthor = new AuthorContract(driver, logger);
		ContractSummary objSummary = new ContractSummary(driver, logger);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
		//driver.switchTo().window("Authoring Contract");
		objSummary.closeContract(objAuthor);
		objAuthor.filterByContractNum(contractNumber);
		objAuthor.viewContract(contractNumber, objSummary);
		Signers objSigners = new Signers(driver, logger);
		objSummary.proceedToSignOff("Offline Signing", objSigners);
		ViewContracts objViewContracts = new ViewContracts(driver1, logger);
		callAndLog(objViewContracts.performAction(contractNumber,"Download"), "able to perform action on Contract", "unable to perform action on Contract");
	}*/
	
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
