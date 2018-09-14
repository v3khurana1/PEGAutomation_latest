package com.main;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Framework.ConfigurationProperties;
import Framework.FrameworkUtility;
import Orders.Orders;
import Invoice.createCreditMemoagainstPO;
import Invoice.createCreditMemowithoutReference;
import Invoice.createInvoiceagainstBPO;
import Invoice.createNonPOInvoice;
//import Invoice.createPOInvoice;
import NewUploads.NewUploads;
import Orders.ViewOrders;
import Invoice.createPOInvoice;

import Catalog.CreateCatalog;
import Catalog.ViewUpdateCatalog;

import Dashboards.Dashboard;
import Dashboards.viewDashboardCompanyCustomer;
import DataProviders.ZSN_DataProviderTestNG;
import Invoice.ViewInvoices;
import Reference.Reference;
import SanityDefault.Login;
import common.Functions.CommonFunctions1;

public class flowZSN {

	private WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	// ModifiedExtentReport modifiedExtent;
	FrameworkUtility objFrameworkUtility = new FrameworkUtility();
	CommonFunctions1 objFunctions = new CommonFunctions1();
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	private String Customer = null;
	private String selectedPO;

	public flowZSN() throws Exception {
		super();
	}

	@BeforeTest
	public void startReport() throws Exception {
		this.driver = objFrameworkUtility.getWebDriverInstance(
				System.getProperty("user.dir") + configurationProperties.getProperty("chromedriverpath"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		extent = new ExtentReports(System.getProperty("user.dir") + configurationProperties.getProperty("reportpath")
				+ "//Execution_Report_ZSN " + sdf.format(timestamp) + ".html", false);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/config/extent-config.xml"));
		/*
		 * modifiedExtent = new ModifiedExtentReport(extent,
		 * configurationProperties.getProperty("reportpath") +
		 * "//Execution_Report_ZSN.html");
		 */
		//objFunctions = new CommonFunctions1(driver, logger);
	}

	
	/**
	 * -------------------------------------------------------
	 * 
	 * @param Username
	 * @param Password
	 * @param Customer
	 * @throws Exception
	 *             --------------------------------------------------------
	 */

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dataProvider = "Login", priority = 1, alwaysRun = true)
	public void Login(String Username, String Password, String Customer) throws Exception {
		try {
			this.Customer = Customer;
			logger = extent.startTest("Login");
			Login objLogin = new Login(driver, logger, Username, Password);
			callAndLog(objLogin.login(configurationProperties), "login successful", "Not logged in");
		} catch (Exception e) {
		}
	}
	
	/*@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "Dashboard", priority = 2, alwaysRun = true)
	public void Dashboard(String card_name, String company) throws Exception {
		logger = extent.startTest("Dashboard");
		Dashboard objDashboard = new Dashboard(driver, logger, card_name, company, Customer);
		callAndLog(objDashboard.addCard(), "TC01 -  added card to dashboard",
				"TC01 - Could not added card to dashboard");
		callAndLog(objDashboard.discardCard(), "TC02 -  discarded card to dashboard",
				"TC02 - Could not discard card to dashboard");
		callAndLog(objDashboard.switch_customer(), "TC04 - Switched the customer ",
				"TC04 - Unable to switch the customer");
		callAndLog(objDashboard.pending_task(), "TC05 -  clicked on Pending Task - view PO",
				"TC05 - Could not clicked on Pending Task - view PO");
		viewDashboardCompanyCustomer objView = new viewDashboardCompanyCustomer(driver, logger, company, Customer);
		callAndLog(objView.view_comp_cust(), "TC06 -  able to view selected company/customer",
				"TC06 -Could notview selected company/customer");

	}*/

	@Test(dependsOnMethods = "Login", priority = 4, alwaysRun = true)
	public void POInvoice() throws Exception {
		logger = extent.startTest("create PO Invoice");
		objFunctions = new CommonFunctions1(driver, logger);
		objFunctions.navigate_path1("My Invoices", "Create PO Invoice", Customer);
		createPOInvoice objCreatePO = new createPOInvoice(driver, logger);
		this.selectedPO = objCreatePO.create_inv("");
		callAndLog(selectedPO != null ? true : false,
				"TC 13 - create PO invoice , TC 15 : able to upload file and view in side by side panel ",
				"TC 13 - Couldnot create PO invoice , TC 15 : unable to upload file or view in side by side panel ");
	}

	/*@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", priority = 6, alwaysRun = true)
	public void CreditMemoagainstPO() throws Exception {
		logger = extent.startTest("create CreditMemo against PO");
		objFunctions.navigate_path1("My Invoices", "Create Credit Memo against PO", Customer);
		createCreditMemoagainstPO objCreateCM = new createCreditMemoagainstPO(driver, logger);
		callAndLog(objCreateCM.Create_new_Credit_Memo(selectedPO), "TC 16 - able to create CreditMemo against PO",
				"TC 16 - unable to create CreditMemo against PO ");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", priority = 15, dataProvider = "Order", alwaysRun = true)
	public void Order(String carrier, String shipmentNo, String shipped_via, String service_level, String description,
			String shipNotice_action) throws Exception {
		logger = extent.startTest("Order");
		Actions action = new Actions(driver);
		objFunctions.navigate_path1("My Orders", "View Orders", Customer);
		ViewOrders objViewOrders = new ViewOrders(driver, logger);
		callAndLog(objViewOrders.confirmPO(), "TC 23 - Successfully confirmed the PO",
				"TC 23 - unable to confirm the PO");
		callAndLog(objViewOrders.rejectPO("TC 24 - rejecting the PO"), "TC 24 - Successfully rejected the PO",
				"TC 24 - unable to reject the PO");
		Orders objOrders = new Orders(driver, logger, action, carrier, shipmentNo, shipped_via, service_level,
				description, shipNotice_action);
		callAndLog(objOrders.performOrderAction(), "TC 25_26_27_28 - Successfully performed action on View Orders page",
				"TC 25_26_27_28 - unable to perform action on View Orders page");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "NonPOInvoice", priority = 5, alwaysRun = true)
	public void NonPOInvoice(String location, String currency, String payment_term, String supplier_name,
			String item_no, String item_description, String product_category, String market_price, String quantity)
			throws Exception {
		logger = extent.startTest("create Non PO Invoice");
		objFunctions.navigate_path1("My Invoices", "Create Non PO Invoice", Customer);
		createNonPOInvoice objCreate = new createNonPOInvoice(driver, location, currency, payment_term, supplier_name,
				item_no, item_description, product_category, market_price, quantity, logger);
		callAndLog(objCreate.create_nonPOinv("Create Non PO Invoice"), "TC 14 - create nonPO invoice ",
				"TC 14 - Couldnot create nonPO invoice ");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "Catalog", priority = 13, alwaysRun = true)
	public void Catalog(String catalog_name, String supplier_name, String supplierPartID, String prodCategory,
			String price) throws Exception {
		logger = extent.startTest("Catalog");
		objFunctions.navigate_path1("My Catalogs", "Create New Catalog", Customer);
		CreateCatalog objCatalog0l = new CreateCatalog(driver, logger, catalog_name, supplier_name, supplierPartID,
				prodCategory, price);
		callAndLog(objCatalog0l.create_catalog_online(),
				"TC 30_32 -  able to create catalog online & user shall be able to validate & publish the catalog",
				"TC 30_32 -  unable to create catalog online & user shall be able to validate & publish the catalog");
		ViewUpdateCatalog objcatalog = new ViewUpdateCatalog(driver, logger);
		callAndLog(objcatalog.perform_action_on_catalog("Edit"), "TC 33 -  able to edit the catalog",
				"TC 33 -  unable to edit the catalog");
		callAndLog(objcatalog.perform_action_on_catalog("Delete"), "TC 35 -  able to delete the catalog",
				"TC 35 -  unable to delete the catalog");
		callAndLog(objcatalog.perform_action_on_catalog("Deactivate"), "TC 36 -  able to deactivate the catalog",
				"TC 36 -  unable to deactivate the catalog");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "ViewInvoice", priority = 3, alwaysRun = true)
	public void ViewInvoice(String invoice_name, String invoice_status, String doc_type) throws Exception {
		logger = extent.startTest("View Invoice");
		objFunctions.navigate_path1("My Invoices", "View Invoices", Customer);
		ViewInvoices objInv = new ViewInvoices(driver, logger);
		callAndLog(objInv.viewInvoiceGrid(), "TC 09 - Able to view invoice grid", "TC 09 -Unable to view invoice grid");
		// extent.endTest(logger);
		callAndLog(objInv.searchInvoice(invoice_name, doc_type), "TC 10 - Able to search invoice",
				"TC 10 - Unable to search invoice ");
		Thread.sleep(3000);
		callAndLog(objInv.filterByInvoiceStatus(invoice_status),
				"TC 10 - Able to filter invoice on invoice status " + invoice_status,
				"TC 10 - Unable to filter invoice on invoice status " + invoice_status);
		callAndLog(objInv.filterByDocumentType(doc_type), "TC 10 - Able to filter invoice on document type " + doc_type,
				"TC 10 - Unable to filter invoice on  document type " + doc_type);
		callAndLog(objInv.sortInvoiceonDueDate(), "TC 11 - Able to sort on due date",
				"TC 11 - Unable to sort on due date");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "CreditMemowithoutReference", priority = 7, alwaysRun = true)
	public void CreditMemowithoutreference(String currency, String supplier_company, String item_no,
			String item_description, String product_category, String market_price, String quantity) throws Exception {
		logger = extent.startTest("create CreditMemo without reference");
		objFunctions.navigate_path1("My Invoices", "Create Credit Memo without reference", Customer);
		createCreditMemowithoutReference objCreateref = new createCreditMemowithoutReference(driver, currency,
				supplier_company, item_no, item_description, product_category, market_price, quantity, logger);
		callAndLog(objCreateref.Create_new_Credit_Memo("Create Credit Memo without reference"),
				"TC 17 - able to create CreditMemo without reference",
				"TC 17 - unable to create CreditMemo without reference ");
	}


	@Test(dependsOnMethods = "Login", priority = 8, alwaysRun = true)
	public void Uploads_POInvoice() throws Exception {
		System.out.println(" I am in uploads something..idk");
		logger = extent.startTest("create PO Invoice via Uploads");
		objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		NewUploads objUpload = new NewUploads(driver, logger);
		callAndLog(objUpload.upload_file(configurationProperties), "able to upload file to ZSN",
				"unable to upload file to ZSN");
		// objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		objFunctions.select_action("Create PO Invoice");
		createPOInvoice objCreatePO = new createPOInvoice(driver, logger);
		// callAndLog(objFunctions.select_PO_to_create_Inv_Creditmemo(),
		// "selected PO to create PO invoice ",
		// "could not PO to create PO invoice ");
		this.selectedPO = objCreatePO.create_inv("");
		callAndLog(selectedPO != null ? true : false, "TC 19 - create PO invoice ",
				"TC 19 - Couldnot create PO invoice ");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "NonPOInvoice", priority = 9, alwaysRun = true)
	public void Uploads_NonPOInvoice(String location, String currency, String payment_term, String supplier_name,
			String item_no, String item_description, String product_category, String market_price, String quantity)
			throws Exception {
		logger = extent.startTest("create Non PO Invoice via uploads");
		objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		NewUploads objUpload = new NewUploads(driver, logger);
		callAndLog(objUpload.upload_file(configurationProperties), "able to upload file to ZSN",
				"unable to upload file to ZSN");
		// objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		objFunctions.select_action("Create Non-PO Invoice");
		createNonPOInvoice objCreate = new createNonPOInvoice(driver, location, currency, payment_term, supplier_name,
				item_no, item_description, product_category, market_price, quantity, logger);
		callAndLog(objCreate.create_nonPOinv("View Uploads"), "TC 20 - create nonPO invoice via uploads",
				"TC 20 - Couldnot create nonPO invoice via uploads");
	}

	@Test(dependsOnMethods = "Login", priority = 12, alwaysRun = true)
	public void Invoice_againstBPO() throws Exception {
		logger = extent.startTest("Create Invoice against BPO");
		// Actions action = new Actions(driver);
		objFunctions.navigate_path1("My Orders", "View Orders", Customer);
		createInvoiceagainstBPO objOrderBPO = new createInvoiceagainstBPO(driver, logger);
		callAndLog(objOrderBPO.createinvoiceBPO("View Orders"), "TC 29 - create direct invoice against BPO",
				"TC 29- unable to create direct invoice against BPO");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", priority = 10, alwaysRun = true)
	public void Uploads_CreditMemoagainstPO() throws Exception {
		logger = extent.startTest("create CreditMemo against PO via uploads");
		objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		NewUploads objUpload = new NewUploads(driver, logger);
		callAndLog(objUpload.upload_file(configurationProperties), "able to upload file to ZSN",
				"unable to upload file to ZSN");
		objFunctions.select_action("Create Credit Memo against PO");
		createCreditMemoagainstPO objCreateCM = new createCreditMemoagainstPO(driver, logger);
		callAndLog(objCreateCM.Create_new_Credit_Memo(selectedPO),
				"TC 21 - able to create CreditMemo against PO via uploads",
				"TC 21 - unable to create CreditMemo against PO via uploads");
	}

	@Test(dataProviderClass = ZSN_DataProviderTestNG.class, dependsOnMethods = "Login", dataProvider = "CreditMemowithoutReference", priority = 11, alwaysRun = true)
	public void Uploads_CreditMemowithoutreference(String currency, String supplier_company, String item_no,
			String item_description, String product_category, String market_price, String quantity) throws Exception {
		logger = extent.startTest("create CreditMemo without reference via uploads");
		objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		NewUploads objUpload = new NewUploads(driver, logger);
		callAndLog(objUpload.upload_file(configurationProperties), "able to upload file to ZSN",
				"unable to upload file to ZSN");
		// objFunctions.navigate_path1("My Invoices", "View Uploads", Customer);
		objFunctions.select_action("Create Credit Memo without reference");
		createCreditMemowithoutReference objCreateref = new createCreditMemowithoutReference(driver, currency,
				supplier_company, item_no, item_description, product_category, market_price, quantity, logger);
		callAndLog(objCreateref.Create_new_Credit_Memo("View Uploads"),
				"TC 22 - able to create CreditMemo without reference via uploads",
				"TC 22 - unable to create CreditMemo without reference via uploads");
	}

	@Test(dependsOnMethods = "Login", priority = 14, alwaysRun = true)
	public void Reference() throws Exception {
		logger = extent.startTest("Reference");
		Reference objRef = new Reference(driver, logger);
		callAndLog(objRef.view_ReferenceDoc(), "TC 38 -  able to open the reference docs related to customer",
				"TC 38 - Unable to open the reference docs related to customer");
	}*/

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
		//driver.quit();
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
