package com.zycus.eProc.PO;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import com.zycus.eInvoice.Invoice.PurchaseOrder;
import com.zycus.eProc.*;

import common.Functions.eProc_CommonFunctions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * <p>
 * <b> Title: </b> PurchaseOrders.java</br>
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

public class PurchaseOrders extends eProc_CommonFunctions {
	private WebDriver driver;
	private ExtentTest logger;

	//private By processingLoader = By.id("polisting_processing");
	private By recallApprovalCmmntId = By.id("txtComment");
	private By recallBtnId = By.id("recallBtn");
	private By recallReqActionMsgId = By.id("status_overlay_savingComment");
	private By closeCmmntId = By.id("closeCommentTextArea");
	private By closeBtnId = By.id("closeAction");
	//private By statusXpath = By.xpath("//table[@id='polisting']//td[1]/div");
	//private By dateXpath = By.xpath("//table[@id='polisting']//td[contains(@class,'submittedOn')]");
	//private By amountXpath = By.xpath("//table[@id='polisting']//td[contains(@class,'totalAmountReq')]");
	private By statusTxtXpath = By.xpath("(//table[contains(@class,'dataTable')]//td[1]/div)[1]");
	private By actionsLinkXpath = By.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]");
	private By poNumTxtXpath = By.xpath("(//table[contains(@class,'dataTable')]//td[2]/a)[1]");
	private By poNameTxtXpath = By.xpath("(//table[contains(@class,'dataTable')]]//td[3]/a)[1]");
	private By DeletePopUpXpath = By.xpath("//div[div//td[text()='Are you sure want to delete this Purchase Order?']]");
	private By DeletePopUpYesLink = By.xpath(
			"//div[div//td[text()='Are you sure want to delete this Purchase Order?']]//button/span[text()='Yes']");
	private By reminderMsgSentXpath = By.xpath("//*[@id='status_overlay_sendingReminder']/div");
	private By recallApprovalReqPopUp = By.xpath("//*[@id='saveCommentDOM']/parent::div");
	private By closeReqPopUp = By.xpath("//*[@id='closeBox']/parent::div");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public PurchaseOrders(WebDriver driver, ExtentTest logger) { 
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

	public boolean filterByStatus(String checkBoxLbl) {
		boolean result = false;
		try {
			String colName = "Status";
			// findElement(By.xpath("//th[contains(@class,'postatusFltrHdr')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByChkbox(checkBoxLbl);
			result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByType - To filter by Type
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl
	 *            - Label of the checkbox which is to be selected
	 * @return result - True/False
	 */

	public boolean filterByType(String checkBoxLbl) {
		boolean result = false;
		try {
			String colName = "Type";
			// findElement(By.xpath("//th[contains(@class,'potypeFltrHdr')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByChkbox(checkBoxLbl);
			result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByPODate -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt
	 *            - From Date , ToDt - To Date
	 * @return result - True/False
	 */

	public boolean filterByPODate(Date fromDt, Date ToDt) {
		boolean result = false;
		try {
			String colName = "PO Date";
			// findElement(By.xpath("//th[contains(@class,'podateFltrHdr')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByDateRange(fromDt, ToDt);
			result = verifyFilteredDates(colNo, fromDt, ToDt) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmount
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromAmt
	 *            - From Amount , ToAmt - To Amount
	 * @return result - True/False
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt) {
		boolean result = false;
		try {
			String colName = "Amount";
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			// findElement(By.xpath("//th[contains(@class,'poamountFltrHdr')]//b"));
			filterByAmtRange(fromAmt, ToAmt);
			result = verifyFilteredAmount(colNo, fromAmt, ToAmt) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmount
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromAmt
	 *            - From Amount , ToAmt - To Amount, currType - Type of currency
	 * @return result - True/False
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt, String currType) {
		boolean result = false;
		String colName = "Amount";
		try {
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			// findElement(By.xpath("//th[contains(@class,'poamountFltrHdr')]//b"));
			filterByAmtRange(fromAmt, ToAmt, currType);
			result = verifyFilteredAmount(colNo, fromAmt, ToAmt, currType) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> verifyDisplayedAction
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param action
	 * @return result - True/False
	 */

	protected boolean verifyDisplayedAction(String action) {
		boolean result = false;
		try {
			String status = findElement(statusTxtXpath).getText();
			switch (action) {
			case "View":
				if (status.equals("Expired"))
					result = true;
				break;
			case "Delete":
				if (status.equals("Draft"))
					result = true;
				break;
			case "Copy":
				if (status.equals("Closed") || status.equals("In Approval") || status.equals("Parked")
						|| status.equals("Rejected") || status.equals("Released") || status.equals("Cancelled")
						|| status.equals("Expired"))
					result = true;
				break;
			case "Review and submit":
				if (status.equals("Ready for approval"))
					result = true;
				break;
			case "Close":
				if (status.equals("Released") || status.equals("In Process(Ordering)"))
					result = true;
				break;
			case "Create Receipt":
				if (status.equals("Released"))
					result = true;
				break;
			case "Add Invoice":
				if (status.equals("Released"))
					result = true;
				break;
			case "Amend PO":
				if (status.equals("Released") || status.equals("Expired"))
					result = true;
				break;
			case "Recall approval request":
			case "Remind Approver":
				if (status.equals("In Approval"))
					result = true;
				break;
			case "Release":
				if (status.equals("Parked"))
					result = true;
				break;
			case "Cancel":
				if (status.equals("Parked") || status.equals("Rejected"))
					result = true;
				break;
			case "Download":
				if (status.equals("Closed"))
					result = true;
				break;
			case "Re-open":
				if (status.equals("Closed"))
					result = true;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * <b>Function:</b> takeAction
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param action
	 * @return result - True/False
	 */

	public boolean takeAction(String action) {
		boolean result = false;
		try {
			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {

				WebElement objPONum = findElement(poNumTxtXpath);
				String poNum = objPONum.getText();

				WebElement objPOName = findElement(poNameTxtXpath);
				String poName = objPOName.getText();

				switch (action) {

				case "View":
					BlanketPurchaseOrder objBlanketPO = new BlanketPurchaseOrder(driver, logger);
					if (findElement(objBlanketPO.getHeaderReqNum()).getAttribute("value") == poNum
							&& findElement(objBlanketPO.getHeaderReqName()).getAttribute("value") == poName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested PO not opened for viewing");
						result = false;
					}
					break;
				case "Delete":
					if (findElement(DeletePopUpXpath).getAttribute("style").contains("block"))
						findElement(DeletePopUpYesLink).click();
					if (findElement(By.xpath("//div/em[contains(text(),'Deleting Purchase order')]")) != null) {
						WebElement objDeletedPONum = findElement(
								By.xpath("//table[@id='polisting']//td[2]/a[text()='" + poNum + "']"));
						if (objDeletedPONum == null)
							result = true;
						else {
							logger.log(LogStatus.INFO, "Deleted PO still exists");
							result = false;
						}
					} else
						logger.log(LogStatus.INFO, "Are you sure want to delete this requisition - message not displayed");
					break;
				case "Copy":
					/*
					 * PurchaseOrder objPO = new PurchaseOrder(driver);
					 * if(findElement(objPO.getRequisitionNm()).getAttribute(
					 * "value") == poName) result = true; else{ System.out.
					 * println("Requested Requisition not opened for editing");
					 * result = false; } break;
					 */

				case "Edit":
					CheckoutPg objReqCheckout = new CheckoutPg(driver, logger);
					if (findElement(objReqCheckout.getRequisitionNm()).getAttribute("value") == poName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for editing");
						result = false;
					}
					break;
				case "Review and submit":
					/*
					 * RequisitionSubmitPg objReqSubmit = new
					 * RequisitionSubmitPg(driver);
					 * if(findElement(objReqSubmit.getpoName()).getText() ==
					 * poName) result = true; else{ System.out.
					 * println("Requested Requisition not opened for review & submit"
					 * ); result = false; }
					 */
					break;
				case "Create Receipt":
					CreateReceipt_page objCreateReceipt = new CreateReceipt_page(driver, logger);
					if (findElement(objCreateReceipt.getHeaderReqNum()).getText() == poNum
							&& findElement(objCreateReceipt.getHeaderReqName()).getText() == poName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				case "Remind Approver":
					if (findElement(reminderMsgSentXpath) != null)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Reminder message has been sent not displayed");
						result = false;
					}
					break;

				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * <b>Function:</b> takeAction
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param action
	 * @param actionBasedReq_Comment
	 * @return result - True/False
	 */

	public boolean takeAction(String action, String actionBasedReq_Comment) {
		boolean result = false;
		try {
			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();

			if (verifyDisplayedAction(action)) {

				WebElement objPONum = findElement(poNumTxtXpath);
				String poNum = objPONum.getText();

				switch (action) {
				case "Recall approval request":
					if (findElement(recallApprovalReqPopUp) != null) {
						sendKeys(recallApprovalCmmntId, actionBasedReq_Comment);
						findElement(recallBtnId).click();
						if (findElement(recallReqActionMsgId) != null) {
							WebElement objRecalledReqNum = findElement(
									By.xpath("//table[contains(@class,'dataTable')]//td[2]/a[text()='" + poNum + "']"));
							if (objRecalledReqNum == null)
								result = true;
						}
					} else {
						logger.log(LogStatus.INFO, "Recall Approval Request pop up not displayed");
						result = false;
					}
					break;
				case "Close":
					if (findElement(closeReqPopUp) != null) {
						sendKeys(closeCmmntId, actionBasedReq_Comment);
						findElement(closeBtnId).click();

						// Add code for validating the closed Requisition
						/*
						 * if(findElement(By.id("status_overlay_savingComment"))
						 * != null){ WebElement objRecalledReqNum =
						 * findElement(By.xpath(
						 * "//table[@id='polisting']//td[2]/a[text()='"+poNum+
						 * "']")) ; if(objRecalledReqNum==null) result = true; }
						 */

					} else {
						logger.log(LogStatus.INFO, "Recall Approval Request pop up not displayed");
						result = false;
					}
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
