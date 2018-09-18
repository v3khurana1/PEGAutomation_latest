package com.zycus.eInvoice.PO;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.eInvoice.PO.CreditMemoagainstPO;
import com.zycus.eInvoice.PO.InvoiceAgainstPO;

import common.Functions.eInvoice_CommonFunctions;

public class PurchaseOrder extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String invoiceNo;
	private String invoiceDate;
	//private String poNumber;
	//private String supplierName;
	//private String buyerName;
	//private By pgHead = By.xpath("//h1[@class='pgHead' and text()='Purchase Orders']");
	private By alertBoxmsg = By.xpath("//div[@id='messageContainer']//span");
	private By POHeader = By.xpath("//h1[@class='pgHead']");
	private By processingLoader = By.id("polisting_processing");
	private By statusXpath = By.xpath("//table[contains(@class,'dataTable')]//td[2]/div");
	private By dateXpath = By.xpath("//table[contains(@class,'dataTable')]//td[contains(@class,'invoiceDate')]");
	private By amountXpath = By.xpath("//table[contains(@class,'dataTable')]//td[contains(@class,'totalAmountReq')]");
	/*private By filterBtnXpath = By.xpath(
			"//div[contains(@id,'qtip') and @aria-hidden='false']//div[contains(@class,'FilterBtnbx')]//a[text()='Filter']");*/
	//private By actionsLinkXpath = By.xpath("(//*[@id='reqList']//a[@class='icon actLnk'])[1]");
	//private By PONumTxtXpath = By.xpath("(//table[@id='polisting']//td[2]/a)[1]");
	private By descriptionId = By.id("txtDescription");
	private By saveDraftId = By.id("saveAsDraft");
	private By creditMemoPageId = By.xpath("//*[@id='cntInvoice']/h1/span[text()='Credit Memo']");
	//private By poTableId = By.id("polisting");
	private By updateInvStatusXpath = By.xpath(".//*[@id='status_overlay_updateInvoice']");
	private By tableElementXpath = By.xpath("//table[contains(@class,'dataTable')]/tbody/tr[1]/td[2]/a");
	private By pdfLinkId = By.id("pdfPOLink");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	public PurchaseOrder(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param invoiceNo
	 * @param invoiceDate
	 */
	/*public PurchaseOrder(WebDriver driver, ExtentTest logger, String poNumber, String supplierName, String buyerName) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.poNumber = poNumber;
		this.supplierName = supplierName;
		this.buyerName = buyerName;

	}*/

	public PurchaseOrder(WebDriver driver, ExtentTest logger, String invoiceNo, String invoiceDate) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
	}
	
	public PurchaseOrder(WebDriver driver, ExtentTest logger, String invoiceNo) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.invoiceNo = invoiceNo;
		
	}

	/**
	 * @return the pOHeader
	 */
	public By getPOHeader() {
		return POHeader;
	}

	/**
	 * @param pOHeader
	 *            the pOHeader to set
	 */
	public void setPOHeader(By pOHeader) {
		POHeader = pOHeader;
	}
	
	/**
	 * @return the alertBoxmsg
	 */
	public By getAlertBoxmsg() {
		return alertBoxmsg;
	}

	/**
	 * @param alertBoxmsg the alertBoxmsg to set
	 */
	public void setAlertBoxmsg(By alertBoxmsg) {
		this.alertBoxmsg = alertBoxmsg;
	}
	
	

	public By getProcessingLoader() {
		return processingLoader;
	}


	public void setProcessingLoader(By processingLoader) {
		this.processingLoader = processingLoader;
	}

	public boolean editInvoice() throws Exception {
		boolean result = false;
		try {
			// findElement(descriptionId).sendKeys(Keys.CLEAR);
			findElement(descriptionId).clear();
			sendKeys(descriptionId, "editing invoice");
			Thread.sleep(2000);
			clickAndWaitUntilLoaderDisappears(saveDraftId, updateInvStatusXpath);
			/*findElement(saveDraftId).click();
			waitUntilInvisibilityOfElement(updateInvStatusXpath);*/
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void addInvoice() throws Exception {
		try {
			clrAllFilters();
			filterByType("Standard");
			filterByStatus("Not Invoiced");
			selectActionPO("Add Invoice");
			InvoiceAgainstPO objInvoice = new InvoiceAgainstPO(driver, logger, invoiceDate);
			objInvoice.createNewInvoice();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean addCreditMemo() {
		boolean result = false;
		try {
			clrAllFilters();
			filterByType("Standard");
			filterByStatus("Partially Invoiced");
			filterByStatus("Partially Received");
			selectActionPO("Add Credit Memo");
			if (findElement(creditMemoPageId).isDisplayed()) {
				logger.log(LogStatus.INFO, "navigated to create credit memo page");
				CreditMemoagainstPO objCreditMemo = new CreditMemoagainstPO(driver, logger);
				objCreditMemo.createCreditMemo();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean downloadPO() {
		boolean result = false;
		try {
			filterByType("Standard");
			if (findElement(By.xpath("//table[contains(@class,'dataTable')]")).isDisplayed())
				findElement(tableElementXpath).click();
			findElement(By.xpath("//*[@id='actDrop']/span")).click();
			//Thread.sleep(2000);
			if (findElement(pdfLinkId).isDisplayed()){
				findElement(pdfLinkId).click();
				logger.log(LogStatus.INFO, "your PO is stored in Downloads");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addInvoicebyHoveringPO() throws Exception {
		boolean result = false;
		try {
			filterByStatus("Partially Invoiced");
			filterByType("Standard");
			String elemID = findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getAttribute("data-hasqtip");
			findElement(By.xpath("//div[contains(@id,'qtip-" + elemID + "-content')]//a[text()='Add Invoice']"))
					.click();
			InvoiceAgainstPO objInvoice = new InvoiceAgainstPO(driver, logger, invoiceDate);
			objInvoice.createNewInvoice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addCreditMemobyHoveringPO() throws Exception {
		boolean result = false;
		try {
			filterByStatus("Partially Invoiced");
			filterByType("Standard");
			Actions action = new Actions(driver);
			WebElement mainMenu = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]/tbody/tr[1]/td[2]/a"));
			action.moveToElement(mainMenu)
					.moveToElement(
							driver.findElement(By.xpath("//div[contains(@id,'qtip')]//a[text()='Add Credit Memo']")))
					.click().build().perform();
			CreditMemoagainstPO objCreditMemo = new CreditMemoagainstPO(driver, logger);
			objCreditMemo.createCreditMemo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByStatus
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param checkBoxLbl
	 * @return result - True/False
	 * @throws None
	 */

	/*
	 * public boolean filterByStatus(String checkBoxLbl) throws Exception {
	 * boolean result = false; try {
	 * findElement(By.xpath("//th[contains(@class,'postatusFltrHdr')]//b")).
	 * click(); filterByChkbox(checkBoxLbl); // if //
	 * (findElement(processingLoader).getAttribute("style").contains("block"))
	 * // { // 7th June 2018 - Anisha
	 * waitUntilInvisibilityOfElement(processingLoader); List<WebElement>
	 * objfilteredList = driver.findElements(statusXpath); for (WebElement obj :
	 * objfilteredList) { if (obj.getText().equals(checkBoxLbl)) result = true;
	 * else { result = false; break; } // } } } catch (Exception e) {
	 * e.printStackTrace(); throw new Exception(); } return result; }
	 */

	public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'postatusFltrHdr')]//b")).click();
			result = filterByChkbox(checkBoxLbl, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByType
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param checkBoxLbl
	 * @return result - True/False
	 * @throws None
	 */

	/*
	 * public boolean filterByType(String checkBoxLbl) throws Exception {
	 * boolean result = false; try { Thread.sleep(2000);
	 * findElement(By.xpath("//th[contains(@class,'potypeFltrHdr')]//b")).click(
	 * );
	 * 
	 * filterByChkbox(checkBoxLbl); // 7th June 2018 : Anisha
	 * waitUntilInvisibilityOfElement(processingLoader); List<WebElement>
	 * objfilteredList = driver.findElements(statusXpath); for (WebElement obj :
	 * objfilteredList) { if (obj.getText().equals(checkBoxLbl)) result = true;
	 * else { result = false; break; } } } catch (Exception e) {
	 * e.printStackTrace(); throw new Exception(); } return result; }
	 */

	public boolean filterByType(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'potypeFltrHdr')]//b")).click();
			result = filterByChkbox(checkBoxLbl, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByPODate
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 * @throws Exception
	 */

	/*
	 * public boolean filterByPODate(Date fromDt, Date ToDt) throws Exception {
	 * boolean result = false; try {
	 * findElement(By.xpath("//th[contains(@class,'podateFltrHdr')]//b")).click(
	 * ); filterByDateRange(fromDt, ToDt); if
	 * (findElement(processingLoader).getAttribute("style").contains("block")) {
	 * List<WebElement> objfilteredDateList = driver.findElements(dateXpath);
	 * for (WebElement obj : objfilteredDateList) { DateFormat format = new
	 * SimpleDateFormat("dd/mm/yyyy"); Date dt = format.parse(obj.getText()); if
	 * (dt.compareTo(fromDt) >= 0 && dt.compareTo(ToDt) <= 0) result = true;
	 * else { result = false; break; } } } } catch (Exception e) {
	 * e.printStackTrace(); } return result; }
	 */

	public boolean filterByPODate(Date fromDt, Date ToDt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'podateFltrHdr')]//b")).click();
			result = filterByDateRange(fromDt, ToDt, dateXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmountRange
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @param currType
	 * @return result - True/False
	 * @throws ParseException
	 */

	/*
	 * private boolean filterByAmountRange(float fromAmt, float ToAmt, String
	 * currType) throws ParseException { boolean result = false; try {
	 * filterByAmtRange(fromAmt, ToAmt, currType); if
	 * (findElement(processingLoader).getAttribute("style").contains("block")) {
	 * List<WebElement> objfilteredAmtList = driver.findElements(amountXpath);
	 * for (WebElement obj : objfilteredAmtList) { Float amount =
	 * Float.parseFloat((obj.getText().split(" "))[1]); String currencyType =
	 * null; if (amount >= fromAmt && amount <= ToAmt) { if (currType != "")
	 * currencyType = (obj.getText().split(" "))[0]; if (currencyType ==
	 * currType) result = true; else result = true; } else { result = false;
	 * break; } } } } catch (Exception e) { e.printStackTrace(); } return
	 * result; }
	 */

	/**
	 * <b>Function:</b> filterByAmount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'poamountFltrHdr')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @param currType
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt, String currType) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'poamountFltrHdr')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByPONumber
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param PONumber
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByPONumber(String PONumber) {
		boolean result = false;
		try {
			result = filterByText("PO Number", PONumber) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterBySupplier
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param supplier
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterBySupplier(String supplier) {
		boolean result = false;
		try {
			result = filterByText_AutoComplete("Supplier", supplier) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByBuyer
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param buyer
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByBuyer(String buyer) {
		boolean result = false;
		try {
			result = filterByText_AutoComplete("Buyer", buyer) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> verifyDisplayedAction
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param action
	 * @return result - True/False
	 * @throws None
	 */

	protected boolean verifyDisplayedAction(String action) {
		boolean result = false;
		try {
			String status = findElement(By.xpath("//table[contains(@class,'dataTable')]//td[1]/div")).getText();
			switch (action) {
			case "Add Credit Memo":
				if (status.equals("Released"))
					result = true;
				break;
			case "Add Invoice":
				if (status.equals("Released"))
					result = true;
				break;
			case "Download":
				if (status.equals("Closed"))
					result = true;
				break;
			case "View":
				if (status.equals("Expired"))
					result = true;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : takeAction
	 * 
	 * @param action
	 * @return result
	 *         -----------------------------------------------------------------
	 *         ----------------
	 */
	/*
	 * 
	 * public boolean takeAction(String action){ boolean result = false;
	 * 
	 * click(actionsLinkXpath); click(By. xpath(
	 * "(//*[@id='polisting']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
	 * +action+"']"));
	 * 
	 * if(verifyDisplayedAction(action)){
	 * 
	 * WebElement objPONum = findElement(PONumTxtXpath); String PONum =
	 * objPONum.getText();
	 * 
	 * switch(action){
	 * 
	 * case "Add credit Memo": PO_CreditMemo objCreateMemo = new
	 * PO_CreditMemo(driver);
	 * if(findElement(objCreateMemo.getCreditMemoHeader()).getText().equals(
	 * PONum)) result = true; else{ System.out.println(
	 * "Requested Requisition not opened for Create Receipt" ); result = false;
	 * } break;
	 * 
	 * case "Delete":
	 * if(findElement(DeletePopUpXpath).getAttribute("style").contains("block"))
	 * click(DeletePopUpYesLink);
	 * if(findElement(processingLoader).getAttribute("style").contains("block"))
	 * { WebElement objDeletedReqNum =
	 * findElement(By.xpath("//table[@id='reqList']//td[2]/a[text()='"+
	 * requisitionNum+"']")); if(objDeletedReqNum == null) result = true; else{
	 * System.out.println("Deleted Requisition still exists"); result = false; }
	 * }else System.out. println(
	 * "Are you sure want to delete this requisition - message not displayed" );
	 * break; case "Edit": CheckoutPg objReqCheckout = new CheckoutPg(driver,
	 * logger);
	 * if(findElement(objReqCheckout.getRequisitionNm()).getAttribute("value")
	 * == requisitionName) result = true; else{ System.out.println(
	 * "Requested Requisition not opened for editing"); result = false; } break;
	 * case "Review and submit": RequisitionSubmitPg objReqSubmit = new
	 * RequisitionSubmitPg(driver, logger);
	 * if(findElement(objReqSubmit.getRequisitionName()).getText() ==
	 * requisitionName) result = true; else{ System.out.println(
	 * "Requested Requisition not opened for review & submit" ); result = false;
	 * } break; case "Create Receipt": CreateReceipt_page objCreateReceipt = new
	 * CreateReceipt_page(driver, logger);
	 * if(findElement(objCreateReceipt.getHeaderReqNum()).getText() ==
	 * requisitionNum &&
	 * findElement(objCreateReceipt.getHeaderReqName()).getText() ==
	 * requisitionName) result = true; else{ System.out.println(
	 * "Requested Requisition not opened for Create Receipt" ); result = false;
	 * } break; case "Remind Approver":
	 * if(findElement(reminderMsgSentXpath)!=null) result = true; else{
	 * System.out.println("Reminder message has been sent not displayed");
	 * result = false; } break; case "Copy": if(findElement(By.xpath(
	 * "//div[div//td[contains(text(),'Items from" +objRequisitionNum+
	 * "will be added to the shopping cart')]]")).
	 * getAttribute("style").contains("block")){
	 * 
	 * Add code for clicking 'Proceed only with Req items'
	 * 
	 * click(By.xpath("//div[div//td[contains(text(),'Items from"
	 * +objRequisitionNum+
	 * "will be added to the shopping cart')]]//button/span[text()='Proceed with Req & existing cart items']"
	 * )); CheckoutPg objReqCheckout1 = new CheckoutPg(driver, logger);
	 * if(findElement(objReqCheckout1.getRequisitionNm()).getAttribute("value")
	 * == requisitionName) result = true; else{ System.out.println(
	 * "Requested Requisition not opened for editing"); result = false; } }else{
	 * System.out.println("Requested Requisition not opened for editing");
	 * result = false; } break; } }else System.out. println(
	 * "Requisition is not in required status for the requested action" );
	 * return result;
	 * 
	 * }
	 * 
	 *//**
		 * ---------------------------------------------------------------------
		 * ------------ Function : takeAction
		 * 
		 * @param action
		 * @param actionBasedReq_Comment
		 * @return result
		 *         -------------------------------------------------------------
		 *         --------------------
		 *//*
		 * 
		 * public boolean takeAction(String action, String
		 * actionBasedReq_Comment){ boolean result = false;
		 * 
		 * click(actionsLinkXpath); click(By. xpath(
		 * "(//*[@id='reqList']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
		 * +action+"']"));
		 * 
		 * if(verifyDisplayedAction(action)){
		 * 
		 * WebElement objRequisitionNum = findElement(requisitionNumTxtXpath);
		 * String requisitionNum = objRequisitionNum.getText();
		 * 
		 * switch(action){ case "Recall approval request":
		 * if(findElement(recallApprovalReqPopUp) != null){
		 * sendKeys(recallApprovalCmmntId, actionBasedReq_Comment);
		 * click(recallBtnId); if(findElement(recallReqActionMsgId) != null){
		 * WebElement objRecalledReqNum =
		 * findElement(By.xpath("//table[@id='reqList']//td[2]/a[text()='"+
		 * requisitionNum+"']")); if(objRecalledReqNum==null) result = true; }
		 * }else{ System.out.println(
		 * "Recall Approval Request pop up not displayed"); result = false; }
		 * break; case "Close": if(findElement(closeReqPopUp) != null){
		 * sendKeys(closeCmmntId, actionBasedReq_Comment); click(closeBtnId);
		 * 
		 * //Add code for validating the closed Requisition
		 * if(findElement(By.id("status_overlay_savingComment"))!=null){
		 * WebElement objRecalledReqNum =
		 * findElement(By.xpath("//table[@id='reqList']//td[2]/a[text()='"+
		 * requisitionNum+"']")); if(objRecalledReqNum==null) result = true; }
		 * 
		 * }else{ System.out.println(
		 * "Recall Approval Request pop up not displayed"); result = false; }
		 * break; } }else System.out. println(
		 * "Requisition is not in required status for the requested action" );
		 * return result; }
		 */

}
