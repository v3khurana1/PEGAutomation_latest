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
import Framework.ConfigurationProperties;
import Framework.FrameworkUtility;
import SanityDefault.Login;
import common.Functions.eInvoice_CommonFunctions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.eInvoice.Approval.Approval;
import com.zycus.eInvoice.Approval.MySettings;
import com.zycus.eInvoice.Invoice.Invoices;
import com.zycus.eInvoice.PO.PurchaseOrder;
import com.zycus.eInvoice.Payment.Batches;
import com.zycus.eInvoice.Payment.NewPaymentBatch;
import com.zycus.eInvoice.Payment.NewVoucher;
import com.zycus.eInvoice.Reports.ReportDetail;
import com.zycus.eInvoice.Reports.Reports;
import com.zycus.eInvoice.eForms.AllForms;
import com.zycus.eInvoice.eForms.FormWizard;
import com.zycus.eInvoice.Reconciliation.ReconcileNewStatement;
import com.zycus.eInvoice.Reconciliation.Statements;
import com.zycus.eInvoice.Uploads.Uploads;
import com.zycus.eInvoice.Workflow.Workflow;
import com.zycus.eInvoice.Workflow.WorkflowWizard;

public class FlowEInvoice {

	private WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	FrameworkUtility objFrameworkUtility = new FrameworkUtility();
	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	//private String Customer;
	private String invoiceNo = null;
	private String displayStyle;
	private String Product;

	public FlowEInvoice() throws Exception {
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
		objFunctions = new eInvoice_CommonFunctions(driver, logger);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method){
		logger = extent.startTest(method.getName());
	}
	
	
	/**
	 * @param Product
	 * @param Username
	 * @param Password
	 * @param Customer
	 * @throws Exception
	 */
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dataProvider = "Login", 
			priority = 1, 
			alwaysRun = true)
	public void Login(String Product, String Username, String Password, String Customer, String userAccount) throws Exception {
		this.Product = Product;
		Login objLogin = new Login(driver, logger, Product, Username, Password, Customer, userAccount);
		this.displayStyle = objLogin.Login_via_PwdMgr1(configurationProperties);
		callAndLog(displayStyle.equals(null)?false:true, "login successful and Product Selected", "Not logged in or Product Not selected");
	}
	
	/*@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dataProvider = "Login", 
			priority = 1, 
			alwaysRun = true)
	public void Login(Map<String, Object> map) throws Exception {
		Login objLogin = new Login(driver, logger, (String)map.get("Product"), (String)map.get("Username"),(String)map.get("Password"), (String)map.get("Customer"), (String)map.get("userAccount"));
		this.displayStyle = objLogin.Login_via_PwdMgr1(configurationProperties);
		callAndLog(displayStyle.equals(null)?false:true, "login successful and Product Selected", "Not logged in or Product Not selected");
	}*/
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "Invoices",  
			priority = 2)
	public void Invoices(String DocStatus, String DocNo, String DocType, String DocDateFrom, String DocDateTo,
			String DueDateFrom, String DueDateTo, String FromAmtRange, String ToAmtRange, String Currency,
			String Reference, String Supplier, String NavigatetoPageNo, String RecordsperPage, String...navigationTabs) throws Exception {
		
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Invoices objInvoice = new Invoices(driver, logger);
		callAndLog(objInvoice.changeNoOfRecordsPerPage(Integer.valueOf(RecordsperPage)), "able to change no of records",
				"unable to change no of records");

		callAndLog(objInvoice.navigateToPageNo(NavigatetoPageNo), "able to navigate to another page",
				"unable to navigate to another page");

		callAndLog(objInvoice.saveViewAsFavorite(), "able to save ViewAsFavorite", "unable to save ViewAsFavorite");
		callAndLog(objInvoice.revertToDefaultView(), "able to revertToDefaultView", "unable to revertToDefaultView");
		//callAndLog(objInvoice.deleteFavView(), "Favorite view deleted", "unable to delete favorite view");
		 
		objFunctions.clrAllFilters();
		callAndLog(objInvoice.filterByStatus(DocStatus), "able to filter on doc status " + DocStatus,
				"unable to filter on doc status" + DocStatus);

		objFunctions.clrAllFilters();
		callAndLog(objInvoice.filterByDocType(DocType), "able to filter on doc type " + DocType,
				"unable to filter on doc type" + DocType);

		objFunctions.clrAllFilters();
		callAndLog(objInvoice.filterByReference(Reference, ""), "able to filter on Reference " + Reference,
				"unable to filter on Reference " + Reference);

		objFunctions.clrAllFilters();
		callAndLog(objInvoice.filterBySupplier(Supplier), "able to search supplier" + Supplier,
				"unable to search supplier" + Supplier);

		objFunctions.clrAllFilters();
		callAndLog(objInvoice.filterByAmount(Float.valueOf(FromAmtRange), Float.valueOf(ToAmtRange), Currency),
				"able to filter on amount range", "able to filter on amount range");
		// TODO Need to add Document No
		objFunctions.clrAllFilters();
		callAndLog(
				objInvoice.filterByDocumentDt(new SimpleDateFormat("dd/MM/yyyy").parse(DocDateFrom),
						new SimpleDateFormat("dd/MM/yyyy").parse(DocDateTo)),
				"able to filter on doc date", "unable to filter on doc date");
		objFunctions.clrAllFilters();
		callAndLog(
				objInvoice.filterByDueDt(new SimpleDateFormat("dd/MM/yyyy").parse(DueDateFrom),
						new SimpleDateFormat("dd/MM/yyyy").parse(DueDateTo)),
				"able to filter on Due Date", "unable to filter on Due Date");
		objFunctions.clrAllFilters();

	}
	
	 @Test(dataProviderClass = eInvoice_DataProviderTestNG.class,
			 dependsOnMethods = "Login",
			 dataProvider = "ApprovalAction",
			 priority=2)
		public void ApprovalAction(String...navigationTabs) throws Exception {
		 	objFunctions.navigateToMainPage(displayStyle,Product, navigationTabs);	
		 	//objFunctions.navigateToMainPage(displayStyle,"Approval", "All Requests");
		 	objFunctions.clrAllFilters();
		 	Approval objApproval = new Approval(driver, logger);
			objApproval.filterByStatus("Pending");
			Thread.sleep(3000);
			objApproval.performActionOnInvoice("Approve");
			Thread.sleep(3000);
			objApproval.performActionOnInvoice("Reject");
			Thread.sleep(3000);
			objApproval.performActionOnInvoice("Delegate");

		}
	 
		@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
				dataProvider = "InvoiceNonPO", 
				dependsOnMethods = "Login", 
				priority = 3)
		public void InvoiceNonPO(String supplierName, String paymentTerm, String currency_value, String invoiceDate,
				String purchaseType,String description, String product_cat, String market_prc,
				String quantity, String GLType, String...navigationTabs) throws Exception {
			Invoices objInvoice = new Invoices(driver, logger, supplierName, paymentTerm, currency_value, invoiceDate,
					purchaseType, description, product_cat, market_prc, quantity, GLType);
			System.out.println("-------Display style again ius ----"+ displayStyle);
			//objFunctions.navigateToMainPage(displayStyle,"Invoice", "Invoices");
			callAndLog(objInvoice.createInvoiceNonPO(), "able to create invoice non PO", "unable to create invoice non PO");

		}
	
	@Test(dependsOnMethods = "Login",
			priority = 4)
	public void InvoiceAction(String...navigationTabs) throws Exception {
		Invoices objInvoice = new Invoices(driver, logger);
		PurchaseOrder objPO = new PurchaseOrder(driver, logger);
		callAndLog(objInvoice.editInvoice(objPO), "able to edit the invoice", "unable to edit the invoice");
		
		objInvoice.filterByStatus("Approved");
		callAndLog(objInvoice.takeActionOnInvoice("Close"), "able to close invoice", "unable to close invoice");
		callAndLog(objInvoice.takeActionOnInvoice("Void Invoice"), "able to Void invoice", "unable to Void invoice");
		callAndLog(objInvoice.takeActionOnInvoice("Return"), "able to Return invoice", "unable to Return invoice");
		// callAndLog(objInvoice.takeActionOnInvoice("Restrict Payment"),"able
		// to restrict payment for invoice","unable to restrict payment for
		// invoice");

		// TODO Adjust credit, Restrict Payment pending - check with Hinal

	}
	

	/*@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "PurchaseOrders",
			priority = 6)
	public void searchPurchaseOrders(String PONumber, String SupplierName, String BuyerName, String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		//objFunctions.navigateToMainPage(displayStyle,"PO");
		PurchaseOrder objPurchaseOrder = new PurchaseOrder(driver, logger);
		// objPurchaseOrder.addInvoicebyHoveringPO();

		callAndLog(objPurchaseOrder.filterByPONumber(PONumber), "able to search by PO number",
				"unable to search by PO number");
		objFunctions.clrAllFilters();
		Thread.sleep(3000);

		callAndLog(objPurchaseOrder.filterBySupplier(SupplierName), "able to search by supplier name",
				"unable to search by supplier name");
		objFunctions.clrAllFilters();
		Thread.sleep(3000);

		callAndLog(objPurchaseOrder.filterByBuyer(BuyerName), "able to search by buyer name",
				"unable to search by buyer name");
		objFunctions.clrAllFilters();
		Thread.sleep(3000);
		callAndLog(objPurchaseOrder.downloadPO(), "able to download PO", "unable to download PO");
	}
	
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class,
		  dependsOnMethods = "Login",
		  dataProvider = "MySettings",
		  priority = 7)
	public void MySettings_ConfigureOOO(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		//objFunctions.navigateToMainPage(displayStyle,"Approval", "My Settings");
		MySettings objSetting = new MySettings(driver, logger);
		callAndLog(objSetting.configureOOO(), "able to configure OOO", "unable to configure OOO");

	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "CreditMemowithoutReference", 
			priority = 8)
	public void CreditMemoWithoutReference(String supplierName, String currency_value, String creditMemoDate,
			String purchaseType, String description, String product_cat, String market_prc,
			String quantity, String GLType, String...navigationTabs) throws Exception {
		Invoices objInvoice = new Invoices(driver, logger, supplierName, currency_value, creditMemoDate, purchaseType,
				 description, product_cat, market_prc, quantity, GLType);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		//objFunctions.navigateToMainPage(displayStyle,"Invoice", "Invoices");
		callAndLog(objInvoice.createCreditMemowithoutReference(), "able to create CreditMemowithoutReference",
				"unable to create CreditMemowithoutReference");
	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class,
			dependsOnMethods = "Login", 
			dataProvider = "CreditMemoAgainstPO",
			priority = 9)
	public void CreditMemoAgainstPO(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Invoices objInvoice = new Invoices(driver, logger);
		//objFunctions.navigateToMainPage(displayStyle,"Invoice", "Invoices");
		PurchaseOrder objPO = new PurchaseOrder(driver, logger, invoiceNo);
		callAndLog(objInvoice.createCreditMemoagainstPO(objPO), "able to create creditMemo against PO",
				"unable to create creditMemo against PO");
	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class,
			dependsOnMethods = "Login", 
			dataProvider = "InvoiceAgainstPO",
			priority = 10)
	public void InvoiceAgainstPO(String...navigationTabs) throws Exception {
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Invoices objInvoice = new Invoices(driver, logger);
		//objFunctions.navigateToMainPage(displayStyle,"Invoice", "Invoices");
		PurchaseOrder objPO = new PurchaseOrder(driver, logger, invoiceNo);
		callAndLog(objInvoice.createInvoiceagainstPO(objPO), "able to create invoice against PO",
				"unable to create invoice against PO");
	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "Reconciliation", 
			priority = 11)
	public void Statements(String batchName, String bankName, String statementDate, String actionOnStatement, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate);
		Statements objStatements = new Statements(driver, logger);
		if(objStatements.searchBatchName(batchName))
			logger.log(LogStatus.INFO, "Batch Name searched successfully");
		if(objStatements.searchBank(bankName))
			logger.log(LogStatus.INFO, "Bank Name searched successfully");
		if(objStatements.takeAction(actionOnStatement))
			logger.log(LogStatus.INFO, "Report reviewed successfully");	
	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "Reconciliation", 
			priority = 12)
	public void AddReconciliationStatements(String batchName, String bankName, String statementDate, String actionOnStatement, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle, Product, tabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, navigationTabs);
		Statements objStatements = new Statements(driver, logger);
		if(objStatements.addNewStmt()){
			ReconcileNewStatement objReconStmt = new ReconcileNewStatement(driver, logger,batchName, bankName, statementDate); 
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			if(objReconStmt.createNewStmt(true, System.getProperty("user.dir")+config.getProperty("upload_csv_path")))
					logger.log(LogStatus.INFO, "New Statement created successfully");
		}
	}

	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "Uploads", 
			priority = 13)
	public void Uploads(String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Uploads objUploads = new Uploads(driver, logger);
		ConfigurationProperties config = ConfigurationProperties.getInstance();
		if(objUploads.uploadNewFile(System.getProperty("user.dir")+config.getProperty("upload_Jpgfile_path")))
			logger.log(LogStatus.PASS, "File uploaded successfully");
		else
			logger.log(LogStatus.FAIL, "File not uploaded successfully");
	}
	

	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class,
			dependsOnMethods = "Login", 
			dataProvider = "Batches",
			priority = 14)
	public void Batches(String batchNo, String createdBy, String bankAcct, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate, subTabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Batches objbatches = new Batches(driver, logger);
		if(objbatches.searchBatchNo(batchNo))
			logger.log(LogStatus.INFO, "Batch Number searched successfully");
		if(objbatches.searchCreatedBy(createdBy))
			logger.log(LogStatus.INFO, "Created By searched successfully");
		if(objbatches.searchBankAccount(bankAcct))
			logger.log(LogStatus.INFO, "Bank Account searched successfully");
	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "MyReports", 
			priority = 15)
	public void shareMyReports(String myReportsName, String sharedUserEmail, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Reports objReports = new Reports(driver, logger);
		if(objReports.selectMyReports(myReportsName))
			logger.log(LogStatus.INFO, "My Report searched & viewed successfully");
		ReportDetail objRepDetail = new ReportDetail(driver, logger);
		if(objRepDetail.shareMyReports(myReportsName, sharedUserEmail));
			logger.log(LogStatus.INFO, "My Report shared successfully");
	}
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "NewBatch", 
			priority = 16)
	public void NewBatch(String organizationUnit, String bankAcct,
			String approver, String notes, String reviewer, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate, subTabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Batches objBatches = new Batches(driver, logger);
		NewPaymentBatch objNewPayment = new NewPaymentBatch(driver, logger, organizationUnit, bankAcct);
		if(objBatches.createNewBatch(objNewPayment)){
			//NewPaymentBatch objNewPayment = new NewPaymentBatch(driver, logger, organizationUnit, bankAcct);
			NewVoucher objVoucher = new NewVoucher(driver, logger, "GDQA_SUPPLIER","chk123","voucher123");
			objNewPayment.enterBatchDetails(approver, notes, reviewer, objVoucher);
		}
	}
		
	
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "ProcessForm", 
			priority = 17)
	public void createProcessForm(String formCreationProcess, String formName, String formType,
			String formRelatedProcess, String businessUnit, String sectionName, String fieldToDisplayInSection, String fieldName,
			String formDescription, String sectionDescription, String sectionLayout, String defaultValue, String maxChar, String hideField, String enterSpace, String enterSplChar,
			String mandatory, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate, subTabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		AllForms objAllForms = new AllForms(driver, logger);
		FormWizard objWizard = new FormWizard(driver, logger, formName, formType, formRelatedProcess,
				businessUnit, sectionName, fieldToDisplayInSection, fieldName, "Auto_display_Name"); 
		objAllForms.selectNewFormCreationProcess(formCreationProcess, objWizard);
		objWizard.createNewForm(formDescription, sectionDescription, sectionLayout, defaultValue, Integer.parseInt(maxChar), Boolean.parseBoolean(hideField), Boolean.parseBoolean(enterSpace), Boolean.parseBoolean(enterSplChar), Boolean.parseBoolean(mandatory));
	}
	
		
	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "Reports", 
			priority = 18)
	public void Reports(String reportType, String reportName, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Reports objReports = new Reports(driver, logger);
		if(objReports.searchReport(reportType, reportName)){
			logger.log(LogStatus.INFO, "Report searched successfully");
			if(objReports.viewReportDetails(reportName)){
				logger.log(LogStatus.INFO, "Report viewed successfully");
				ReportDetail objRepDetail = new ReportDetail(driver, logger);
				objRepDetail.closeReportDetails();
			}
		}
	}

	@Test(dataProviderClass = eInvoice_DataProviderTestNG.class, 
			dependsOnMethods = "Login", 
			dataProvider = "Workflow", 
			priority = 19)
	public void Workflow(String workflowProcess, String workflowDescription, String workflowType, String approveMoreThanOnce, String...navigationTabs) throws Exception {
		//eInvoice_CommonFunctions objCommon = new eInvoice_CommonFunctions(driver, logger);
		//objCommon.navigateToMainPage(displayStyle,tabToNavigate);
		objFunctions.navigateToMainPage(displayStyle, Product, navigationTabs);
		Workflow objWorkflow = new Workflow(driver, logger);
		objWorkflow.clickCreateWorkflow();
		WorkflowWizard objWizard = new WorkflowWizard(driver, logger, workflowProcess);
		objWizard.createWorkflow(workflowDescription, workflowType, Boolean.parseBoolean(approveMoreThanOnce));
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
