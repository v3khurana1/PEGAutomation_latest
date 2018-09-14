package com.zycus.eProc.Approval;

import java.text.ParseException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> ApprovalDetails.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.filterByStatus: user shall be able to filter by Status </br>
 * <br>
 * 2.filterByReceivedOn: user shall be able to filter by Received On </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class ApprovalDetails extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	// private By processingLoader = By.id("workflowApproval_processing");
	private By processingLoader = By.xpath("//div[contains(@id,'processing')]");
	private By statusXpath = By.xpath("//table[@id='workflowApproval']//td[2]/div");
	private By dateXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'receivedOn')]");
	private By amtToApproveXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'entityAmount')]");
	private By statusTxtXpath = By.xpath("(//table[@id='workflowApproval']//td[2]/div)[1]");
	private By actionsLinkXpath = By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]");
	private By requisitionNumTxtXpath = By.xpath("(//table[@id='workflowApproval']//td[3]/a)[1]");
	private By requisitionNameTxtXpath = By.xpath("(//table[@id='workflowApproval']//td[4])[1]");
	
	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public ApprovalDetails(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> filterByStatus - To filter by Status
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl
	 *            - Label of the checkbox which is to be selected
	 * @return result - True/False
	 */

	/*public boolean filterByStatus(String checkBoxLbl) {
		boolean result = false;
		try {
			String colName = "Status";
			// click(By.xpath("//th[contains(@class,'statusFilter')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByChkbox(checkBoxLbl);
			result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;
		try{
			findElement(By.xpath("//th[contains(@class,'statusFilter')]//b")).click();
			result = objFunctions.filterByChkbox(checkBoxLbl, statusXpath)?true:false;
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByReceivedOn - To filter by Received On
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt
	 *            - From Date , ToDt - To Date
	 * @throws ParseException
	 * @return result - True/False
	 */

	/*public boolean filterByReceivedOn(Date fromDt, Date ToDt) throws ParseException {
		boolean result = false;
		try {
			String colName = "Received On";
			// click(By.xpath("//th[contains(@class,'receivedOnFilter')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByDateRange(fromDt, ToDt);
			result = verifyFilteredDates(colNo, fromDt, ToDt) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean filterByReceivedOn(Date fromDt, Date ToDt) throws ParseException {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'receivedOnFilter')]//b")).click();
			result = objFunctions.filterByDateRange(fromDt, ToDt, dateXpath);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>
	 * <b> Title: </b> Requisition.java</br>
	 * <br>
	 * <b> Description: </b> To perform operations on pages Approval - Approval
	 * Requests - Requisition </br>
	 * <br>
	 * <b> Functions: </br>
	 * </b> <br>
	 * 1.filterByStatus: user shall be able to filter by Status </br>
	 * <br>
	 * 2.filterByReceivedOn: user shall be able to filter by Received On </br>
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 */

	private class Requisition extends ApprovalDetails {
		private WebDriver driver;

		private By amountXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'entityAmount')]");
		
		String requisitionNo = "1056";
		String requisitionName = "Mobile API testing";
		String requester = "Steffy TL";

		private ExtentTest logger;
		
		public Requisition(WebDriver driver, ExtentTest logger) { 
				super(driver, logger);
				this.driver = driver;
				this.logger = logger;
			}
		
		
		public boolean filterByRequisitionNo(String requisitionNo) {
			boolean result = false;
			try {				
				result = objFunctions.filterByText("Requistion No", requisitionNo) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public boolean filterByRequisitionName(String requisitionName) {
			boolean result = false;
			try {				
				result = objFunctions.filterByText("Requistion Name", requisitionName) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public boolean filterByRequester(String requester) {
			boolean result = false;
			try {				
				result = objFunctions.filterByText_AutoComplete("Requester", requester) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> filterByAmountToApprove - To filter by 'Amount to be
		 * approved'
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param fromAmt
		 *            - From Amount , ToAmt - To Amount
		 * @return result - True/False
		 */

		/*public boolean filterByAmountToApprove(float fromAmt, float ToAmt) {
			boolean result = false;
			try {
				String colName = "Amount to be approved";
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'amountFilter')]//b"));
				filterByAmtRange(fromAmt, ToAmt);
				result = verifyFilteredAmount(colNo, fromAmt, ToAmt) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByAmountToApprove(float fromAmt, float ToAmt) {
			boolean result = false;
			try {				
				findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
				result = objFunctions.filterByAmtRange(fromAmt, ToAmt, amtToApproveXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> filterByAmountToApprove - To filter by 'Amount to be
		 * approved'
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param fromAmt
		 *            - From Amount , ToAmt - To Amount, currType - Type of
		 *            currency
		 * @return result - True/False
		 */

		/*public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) {
			boolean result = false;
			String colName = "Amount to be approved";
			try {
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'amountFilter')]//b"));
				filterByAmtRange(fromAmt, ToAmt, currType);
				result = verifyFilteredAmount(colNo, fromAmt, ToAmt, currType) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) {
			boolean result = false;
			try {				
				findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
				result = objFunctions.filterByAmtRange(fromAmt, ToAmt, currType, amtToApproveXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> verifyDisplayedAction - To verify if the action
		 * displayed under 'Actions' list is correct as per the Status
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param action
		 *            - Action from the 'Actions' list
		 * @return result - True/False
		 */

		protected boolean verifyDisplayedAction(String action) {
			boolean result = false;

			String status = findElement(statusTxtXpath).getText();

			switch (action) {
			case "View":
				if (status.equals("Pending") || status.equals("Approved") || status.equals("Rejected"))
					result = true;
				break;
			case "Approve":				
			case "Reject":
			case "Delegate":
			case "Ask more info":
				if (status.equals("Pending"))
					result = true;
				break;
			}
			return result;

		}

		/**
		 * <b>Function:</b> selectAction - To verify if the action displayed
		 * under 'Actions' list is correct as per the Status
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param action
		 *            - Action from the 'Actions' list
		 * @return result - True/False
		 * @throws Exception 
		 */

		public boolean selectAction(String action) throws Exception {
			boolean result = false;
			
			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {
				Dialog objDialog;
				switch (action) {
				case "View":
					WebElement objRequisitionNum = findElement(requisitionNumTxtXpath);
					String requisitionNum = objRequisitionNum.getText();

					WebElement objRequisitionName = findElement(requisitionNameTxtXpath);
					String requisitionName = objRequisitionName.getText();
					
					RequisitionInfo objReqInfo = new RequisitionInfo(driver, logger);
					if (findElement(objReqInfo.getHeaderReqNum()).getText() == requisitionNum
							&& findElement(objReqInfo.getHeaderReqName()).getText() == requisitionName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				case "Approve":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'approveDialog')")).size() > 0) 
						result=true;
					break;
				case "Reject":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'rejectDialog')")).size() > 0) 
						result=true;
					break;
				case "Delegate":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'delegateDialog')")).size() > 0) 
						result=true;
					break;
				case "Ask more info":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'newDiscussionForm')")).size() > 0) 
						result=true;
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

		/**
		 * <b>Function:</b> selectAction - To verify if the action displayed
		 * under 'Actions' list is correct as per the Status
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param action
		 *            - Action from the 'Actions' list, actionComments -
		 *            Comments when action is Approve/Reject
		 * @return result - True/False
		 * @throws Exception 
		 */

		public boolean selectAction(String action, String actionComments) throws Exception {
			boolean result = false;

			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();
			Dialog objDialog;
			if (verifyDisplayedAction(action)) {

				switch (action) {
				case "Approve":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'approveDialog')")).size() > 0) 
					objDialog.approveDocument(actionComments);
					/*sendKeys(By.id("approvalComments"), actionComments);
					findElement(By.id("approveCommentBtn")).click();*/
				case "Reject":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'rejectDialog')")).size() > 0) 
					objDialog.rejectDocument(actionComments);
					/*sendKeys(By.id("rejectComments"), actionComments);
					findElement(By.id("rejectCommentBtn")).click();*/
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

		/**
		 * <b>Function:</b> selectAction - To verify if the action displayed
		 * under 'Actions' list is correct as per the Status
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param action
		 *            - Action from the 'Actions' list, actionToUser - 'To'
		 *            field for Delegate/Ask more info actions, actionComments -
		 *            Comments
		 * @return result - True/False
		 * @throws Exception 
		 */

		public boolean selectAction(String action, String actionToUser, String actionComments) throws Exception {
			boolean result = false;

			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {
				Dialog objDialog;
				switch (action) {
				case "Delegate":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'delegateDialog')")).size() > 0) 
					objDialog.delegateDocument(actionToUser, actionComments);
					
					/*findElement(By.id("txtDelegateName")).sendKeys(actionToUser);
					if (findElement(By.xpath("//div[@id='dev_delegateDialog']/following-sibling::ul[1]")) != null)
						findElement(By.xpath("//div[@id='dev_delegateDialog']/following-sibling::ul[1]/li[1]/a")).click();
					else
						logger.log(LogStatus.INFO, "'Delegate Approval To' list to select names not displayed");
					sendKeys(By.id("delegateComments"), actionComments);
					findElement(By.id("btnDelegateSave")).click();*/
				case "Ask more info":
					objDialog = new Dialog(driver, logger, actionToUser, actionComments);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'newDiscussionForm')")).size() > 0) 
					objDialog.askMoreInfo();
					/*sendKeys(
							By.xpath(
									"//*[contains(@id,'viewNewDiscussionDOMComponent')]//li[contains(@class,'convMsg')]//textarea"),
							actionComments);
					findElement(By.id("postCommentBtn")).click();*/
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

		/**
		 * <b>Function:</b> selectAction - To verify if the action displayed
		 * under 'Actions' list is correct as per the Status
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param action
		 *            - Action from the 'Actions' list
		 * @return result - True/False
		 * @throws Exception 
		 */

		public boolean selectAction(String action, String msgTo, String msgCc, String actionComments, String sharedWith,
				String filePath) throws Exception {
			boolean result = false;

			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {
				Dialog objDialog;
				switch (action) {
				case "Ask more info":
					objDialog = new Dialog(driver, logger, msgTo, actionComments);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'newDiscussionForm')")).size() > 0) 
					objDialog.askMoreInfo(msgCc, filePath);
					
					/*if (findElement(By.xpath("//div[@id='newDiscussionForm']/following-sibling::ul[1]")) != null)
						findElement(By.xpath("//div[@id='newDiscussionForm']/following-sibling::ul[1]/li[1]/a")).click();
					else
						logger.log(LogStatus.INFO, "'To' list to select names not displayed");
					if (findElement(By.xpath("//div[@id='newDiscussionForm']/following-sibling::ul[2]")) != null)
						findElement(By.xpath("//div[@id='newDiscussionForm']/following-sibling::ul[2]/li[1]/a")).click();
					else
						logger.log(LogStatus.INFO, "'CC' list to select names not displayed");
					sendKeys(
							By.xpath(
									"//*[contains(@id,'viewNewDiscussionDOMComponent')]//li[contains(@class,'convMsg')]//textarea"),
							actionComments);
					WebElement sharedWithChkbox = findElement(By.xpath(
							"//*[contains(@id,'viewNewDiscussionDOMComponent')]//li[contains(@class,'discAreaTree')]/div/label[text()[contains(.,'All')]]/input"));
					if (!sharedWithChkbox.isSelected())
						sharedWithChkbox.click();
					uploadFile(filePath);
					findElement(By.id("postCommentBtn")).click();*/
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

	}

	private class Standard_PO extends ApprovalDetails {
		private WebDriver driver;

		private By amountXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'entityAmount')]");
		private By typeXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'entityType')]");
		
		String poNo = "616";
		String poName = "Copy of Copy of Copy of O";
		String buyer = "Steffy TL";
		
		private ExtentTest logger;
	public Standard_PO(WebDriver driver, ExtentTest logger) throws Exception { 
			super(driver, logger);
			this.driver = driver;
			this.logger = logger;
			findElement(By.xpath("//div[contains(@class,'productSelectionTab')]/ul/li/a[text()='Standard PO']")).click();
		}
	
		
	public boolean filterByPONo(String poNo) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText("PO No", poNo) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByPOName(String poName) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText("PO Name", poName) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByBuyer(String buyer) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText_AutoComplete("Buyer", buyer) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : filterByType
		 * 
		 * @param typeValue
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 */

		/*public boolean filterByType(String checkBoxLbl) {
			boolean result = false;
			try {
				String colName = "Type";
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'documentTypeFilter')]//b"));
				filterByChkbox(checkBoxLbl);
				result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByType(String checkBoxLbl) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'documentTypeFilter')]//b")).click();
				result = objFunctions.filterByChkbox(checkBoxLbl, typeXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> filterByAmountToApprove - To filter by 'Amount to be
		 * approved'
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param fromAmt
		 *            - From Amount , ToAmt - To Amount
		 * @return result - True/False
		 */

		/*public boolean filterByAmountToApprove(float fromAmt, float ToAmt) {
			boolean result = false;
			try {
				String colName = "Amount to be approved";
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'amountFilter')]//b"));
				filterByAmtRange(fromAmt, ToAmt);
				result = verifyFilteredAmount(colNo, fromAmt, ToAmt) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByAmountToApprove(float fromAmt, float ToAmt) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
				result = objFunctions.filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> filterByAmountToApprove - To filter by 'Amount to be
		 * approved'
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param fromAmt
		 *            - From Amount , ToAmt - To Amount, currType - Type of
		 *            currency
		 * @return result - True/False
		 */

		/*public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) {
			boolean result = false;
			String colName = "Amount to be approved";
			try {
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'amountFilter')]//b"));
				filterByAmtRange(fromAmt, ToAmt, currType);
				result = verifyFilteredAmount(colNo, fromAmt, ToAmt, currType) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
				result = objFunctions.filterByAmtRange(fromAmt, ToAmt, currType, amountXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * ---------------------------------------------------------------------------------
		 * Function : verifyDisplayedAction
		 * 
		 * @param action
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 */

		protected boolean verifyDisplayedAction(String action) {
			boolean result = false;

			String status = findElement(statusTxtXpath).getText();
			switch (action) {
			case "View":
				if (status.equals("Approved") || status.equals("Rejected"))
					result = true;
				break;
			}
			return result;

		}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : takeAction
		 * 
		 * @param action
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 * @throws Exception 
		 */

		public boolean takeAction(String action) throws Exception {
			boolean result = false;

			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {

				switch (action) {
				case "View":
					WebElement objRequisitionNum = findElement(requisitionNumTxtXpath);
					String requisitionNum = objRequisitionNum.getText();

					WebElement objRequisitionName = findElement(requisitionNameTxtXpath);
					String requisitionName = objRequisitionName.getText();
					
					PurchaseOrder objPurchaseOrder = new PurchaseOrder(driver, logger);
					if (findElement(objPurchaseOrder.getHeaderReqNum()).getText() == requisitionNum
							&& findElement(objPurchaseOrder.getHeaderReqName()).getText() == requisitionName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

	}

	private class Blanket_PO extends ApprovalDetails {
		private WebDriver driver;

		private By amountXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'entityAmount')]");
		private By typeXpath = By.xpath("//table[@id='workflowApproval']//td[contains(@class,'entityType')]");
		
		String poNo = "616";
		String poName = "Copy of Copy of Copy of O";
		String buyer = "Steffy TL";
		
		private ExtentTest logger;
	public Blanket_PO(WebDriver driver, ExtentTest logger) throws Exception { 
			super(driver, logger);
			this.driver = driver;
			this.logger = logger;
			findElement(By.xpath("//div[contains(@class,'productSelectionTab')]/ul/li/a[text()='Blanket PO']")).click();
		}
	
		
	public boolean filterByPONo(String poNo) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText("PO No", poNo) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByPOName(String poName) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText("PO Name", poName) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByBuyer(String buyer) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText_AutoComplete("Buyer", buyer) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
		

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : filterByStatus
		 * 
		 * @param typeValue
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 */

		/*public boolean filterByType(String checkBoxLbl) {
			boolean result = false;
			try {
				String colName = "Type";
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'documentTypeFilter')]//b"));
				filterByChkbox(checkBoxLbl);
				result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByType(String checkBoxLbl) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'documentTypeFilter')]//b")).click();
				result = objFunctions.filterByChkbox(checkBoxLbl, typeXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> filterByAmountToApprove - To filter by 'Amount to be
		 * approved'
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param fromAmt
		 *            - From Amount , ToAmt - To Amount
		 * @return result - True/False
		 */

		/*public boolean filterByAmountToApprove(float fromAmt, float ToAmt) {
			boolean result = false;
			try {
				String colName = "Amount to be approved";
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'amountFilter')]//b"));
				filterByAmtRange(fromAmt, ToAmt);
				result = verifyFilteredAmount(colNo, fromAmt, ToAmt) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByAmountToApprove(float fromAmt, float ToAmt) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
				result = objFunctions.filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * <b>Function:</b> filterByAmountToApprove - To filter by 'Amount to be
		 * approved'
		 * 
		 * @author Varun Khurana
		 * @since April 2018
		 * @param fromAmt
		 *            - From Amount , ToAmt - To Amount, currType - Type of
		 *            currency
		 * @return result - True/False
		 */

		/*public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) {
			boolean result = false;
			String colName = "Amount to be approved";
			try {
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'amountFilter')]//b"));
				filterByAmtRange(fromAmt, ToAmt, currType);
				result = verifyFilteredAmount(colNo, fromAmt, ToAmt, currType) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
		
		public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
				result = objFunctions.filterByAmtRange(fromAmt, ToAmt, currType, amountXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : verifyDisplayedAction
		 * 
		 * @param action
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 */

		protected boolean verifyDisplayedAction(String action) {
			boolean result = false;

			String status = findElement(statusTxtXpath).getText();
			switch (action) {
			case "View":
				if (status.equals("Approved") || status.equals("Rejected"))
					result = true;
				break;
			}
			return result;

		}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : takeAction
		 * 
		 * @param action
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 * @throws Exception 
		 */

		public boolean takeAction(String action) throws Exception {
			boolean result = false;

			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {
				switch (action) {
				case "View":
					WebElement objRequisitionNum = findElement(requisitionNumTxtXpath);
					String requisitionNum = objRequisitionNum.getText();

					WebElement objRequisitionName = findElement(requisitionNameTxtXpath);
					String requisitionName = objRequisitionName.getText();
					
					PurchaseOrder objPurchaseOrder = new PurchaseOrder(driver, logger);
					if (findElement(objPurchaseOrder.getHeaderReqNum()).getText() == requisitionNum
							&& findElement(objPurchaseOrder.getHeaderReqName()).getText() == requisitionName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

	}

	private class Catalog extends ApprovalDetails {
		private WebDriver driver;

		private By supplierXpath = By.xpath("//table[@id='workflowApproval']//td[5]");
		private By catalogNameTxtXpath = By.xpath("(//table[@id='workflowApproval']//td[3]/a)[1]");
		private By receivedOnTxtXpath = By.xpath("(//table[@id='workflowApproval']//td[4])[1]");
		
		String catalogName = "firewall sanity";
		
		private ExtentTest logger;
	public Catalog(WebDriver driver, ExtentTest logger) throws Exception { 
			super(driver, logger);
			this.driver = driver;
			this.logger = logger;
			findElement(By.xpath("//div[contains(@class,'productSelectionTab')]/ul/li/a[text()='Catalog']")).click();
		}
	
	public boolean filterBycatalogName(String catalogName) {
		boolean result = false;
		try {				
			result = objFunctions.filterByText("Catalog Name", catalogName) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : filterBySupplier
		 * 
		 * @param checkBoxLbl
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 */

		/*public boolean filterBySupplier(String checkBoxLbl) {
			boolean result = false;
			try {
				String colName = "Supplier";
				int colNo = getColNum(colName);
				findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
				// click(By.xpath("//th[contains(@class,'documentTypeFilter')]//b"));
				filterByChkbox(checkBoxLbl);
				result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			// click(By.xpath("//th[contains(@class,'supplierFilter')]//b"));
			return result;
		}*/
		
		public boolean filterBySupplier(String checkBoxLbl) {
			boolean result = false;
			try {
				findElement(By.xpath("//th[contains(@class,'supplierFilter')]//b")).click();
				result = objFunctions.filterByChkbox(checkBoxLbl, supplierXpath) ? true : false;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : verifyDisplayedAction
		 * 
		 * @param action
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 */

		protected boolean verifyDisplayedAction(String action) {
			boolean result = false;

			String status = findElement(statusTxtXpath).getText();
			switch (action) {
			case "View":
				if (status.equals("Approved") || status.equals("Rejected"))
					result = true;
				break;
			case "Approve":				
			case "Reject":
			case "Delegate":
				if (status.equals("Pending"))
					result = true;
				break;
			}
			return result;

		}

		/**
		 * ---------------------------------------------------------------------------------
		 * Function : takeAction
		 * 
		 * @param action
		 * @return result
		 *         ---------------------------------------------------------------------------------
		 * @throws Exception 
		 */

		public boolean takeAction(String action) throws Exception {
			boolean result = false;

			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {
				Dialog objDialog;
				switch (action) {
				case "View":
					WebElement objCatalogName = findElement(catalogNameTxtXpath);
					String catalogName = objCatalogName.getText();

					WebElement objReceivedOn = findElement(receivedOnTxtXpath);
					String receivedOn = objReceivedOn.getText();
					
					PurchaseOrder objPurchaseOrder = new PurchaseOrder(driver, logger);
					if (findElement(objPurchaseOrder.getHeaderReqNum()).getText() == catalogName
							&& findElement(objPurchaseOrder.getHeaderReqName()).getText() == receivedOn)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				case "Approve":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'approveDialog')")).size() > 0) 
						result=true;
					break;
				case "Reject":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'rejectDialog')")).size() > 0) 
						result=true;
					break;
				case "Delegate":
					objDialog = new Dialog(driver, logger);
					if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'delegateDialog')")).size() > 0) 
						result=true;
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
			return result;

		}

	}

}
