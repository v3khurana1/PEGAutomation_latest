package com.zycus.eProc.Requisition;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zycus.eProc.*;

import common.Functions.eProc_CommonFunctions;

import com.relevantcodes.extentreports.ExtentTest;

/**
 * <p>
 * <b> Title: </b> RequisitionDetails.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.addRequisition: </br>
 * <br>
 * 2.filterByStatus: </br>
 * <br>
 * 3.filterBySubmittedOn: </br>
 * <br>
 * 4.filterByAmount: </br>
 * <br>
 * 5.verifyDisplayedAction: </br>
 * <br>
 * 6.takeAction: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class RequisitionDetails extends eProc_CommonFunctions {
	private WebDriver driver;
	private ExtentTest logger;

	private By processingLoader = By.id("reqList_processing");
	private By OnlineCatalogPgXpath = By.id("searchCatalogHomeId");
	private By recallApprovalCmmntId = By.id("txtComment");
	private By recallBtnId = By.id("recallBtn");
	private By recallReqActionMsgId = By.id("status_overlay_savingComment");
	private By closeCmmntId = By.id("closeCommentTextArea");
	private By closeBtnId = By.id("closeAction");
	private By requsitionLoadedPromptXpath = By.xpath(
			"//div[//td[contains(text(),'requisition is already loaded')] and contains(@class,'promptCnt')]/parent::div");
	private By continueAddtoReqXpath = By.xpath("//span[text()='No, continue adding to existing requisition']");
	/*private By statusXpath = By.xpath("//table[@id='reqList']//td[1]/div");
	private By dateXpath = By.xpath("//table[@id='reqList']//td[contains(@class,'submittedOn')]");
	private By amountXpath = By.xpath("//table[@id='reqList']//td[contains(@class,'totalAmountReq')]");
	*/
	private By statusTxtXpath = By.xpath("(//table[@id='reqList']//td[1]/div)[1]");
	private By actionsLinkXpath = By.xpath("(//*[@id='reqList']//a[@class='icon actLnk'])[1]");
	private By requisitionNumTxtXpath = By.xpath("(//table[@id='reqList']//td[2]/a)[1]");
	private By requisitionNameTxtXpath = By.xpath("(//table[@id='reqList']//td[3]/a)[1]");
	private By DeletePopUpXpath = By.xpath("//div[div//td[text()='Are you sure want to delete this requisition?']]");
	private By DeletePopUpYesLink = By
			.xpath("//div[div//td[text()='Are you sure want to delete this requisition?']]//button/span[text()='Yes']");
	private By reminderMsgSentXpath = By.xpath("//*[@id='status_overlay_sendingReminder']/div");
	private By recallApprovalReqPopUp = By.xpath("//*[@id='saveCommentDOM']/parent::div");
	private By closeReqPopUp = By.xpath("//*[@id='closeBox']/parent::div");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public RequisitionDetails(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> addRequisition -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param requisitionFlow
	 * @return result - True/False
	 */

	public boolean addRequisition(String requisitionFlow) {
		boolean result = false;
		try {
			findElement(By.id("lnkAddRequisition")).click();
			try {
				WebElement requsitionLoadedPrompt = findElement(requsitionLoadedPromptXpath);
				if (requsitionLoadedPrompt.getAttribute("style").contains("block"))
					findElement(continueAddtoReqXpath).click();
			} catch (Exception e) {
			}
			if (findElement(OnlineCatalogPgXpath) != null)
				if (requisitionFlow == "Online Catalog") {

				} else if (requisitionFlow == "Guided Procurement") {

				} else if (requisitionFlow == "Punchout") {

				} else
					System.out.println("Online Store page not displayed");
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
			// findElement(By.xpath("//th[contains(@class,'reqStatusFltrHdr')]//b"));
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
	 * <b>Function:</b> filterBySubmittedOn - To filter by Submitted On
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt
	 *            - From Date , ToDt - To Date
	 * @throws None
	 * @return result - True/False
	 */

	public boolean filterBySubmittedOn(Date fromDt, Date ToDt) {
		boolean result = false;
		try {
			String colName = "Received On";
			// findElement(By.xpath("//th[contains(@class,'reqDateFltrHdr')]//b"));
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
			String colName = "Amount to be approved";
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			// findElement(By.xpath("//th[contains(@class,'reqAmountFltrHdr')]//b"));
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
		String colName = "Amount to be approved";
		try {
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			// findElement(By.xpath("//th[contains(@class,'reqAmountFltrHdr')]//b"));
			filterByAmtRange(fromAmt, ToAmt, currType);
			result = verifyFilteredAmount(colNo, fromAmt, ToAmt, currType) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : filterByTextField
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return result
	 *         ---------------------------------------------------------------------------------
	 *//*
		 * 
		 * public boolean filterByTextField1(String fieldName, String
		 * fieldValue){ boolean result = false;
		 * 
		 * String fieldID = null; String elemClassSubstring = null;
		 * switch(fieldName){ case "Requisition Number": fieldID =
		 * "txtFltrReqNum"; elemClassSubstring = "requisitionNo"; break; case
		 * "Name": fieldID = "txtFltrName"; elemClassSubstring = "name"; break;
		 * case "Requester": fieldID = "txtFltrRequesterName";
		 * elemClassSubstring = "behalfUserId"; break; }
		 * sendKeys(By.id(fieldID), fieldValue);
		 * if(findElement(processingLoader).getAttribute("style").contains(
		 * "block")){ List<WebElement> objfilteredTxtList =
		 * driver.findElements(By.xpath(
		 * "//table[@id='reqList']//td[contains(@class,'"+elemClassSubstring+
		 * "')]")); for(WebElement obj:objfilteredTxtList){
		 * if(obj.getText().equals(fieldValue)) result = true; else{ result =
		 * false; break; } } } return result; }
		 */

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
			case "Edit":
			case "Delete":
				if (status.equals("Draft"))
					result = true;
				break;
			case "Copy":
				if (status.equals("In Process(With Buyer)") || status.equals("In Process(Ready for approval)")
						|| status.equals("Released") || status.equals("Ordering"))
					result = true;
				break;
			case "Review and submit":
				if (status.equals("In Process(Ready for approval)"))
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
			case "Recall approval request":
			case "Remind Approver":
				if (status.equals("In Process(In Approval)"))
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
			findElement(By.xpath("(//*[@id='reqList']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='" + action
					+ "']")).click();

			if (verifyDisplayedAction(action)) {

				WebElement objRequisitionNum = findElement(requisitionNumTxtXpath);
				String requisitionNum = objRequisitionNum.getText();

				WebElement objRequisitionName = findElement(requisitionNameTxtXpath);
				String requisitionName = objRequisitionName.getText();

				switch (action) {
				case "Delete":
					if (findElement(DeletePopUpXpath).getAttribute("style").contains("block"))
						findElement(DeletePopUpYesLink).click();
					if (findElement(processingLoader).getAttribute("style").contains("block")) {
						WebElement objDeletedReqNum = findElement(
								By.xpath("//table[@id='reqList']//td[2]/a[text()='" + requisitionNum + "']"));
						if (objDeletedReqNum == null)
							result = true;
						else {
							System.out.println("Deleted Requisition still exists");
							result = false;
						}
					} else
						System.out.println("Are you sure want to delete this requisition - message not displayed");
					break;
				case "Edit":
					CheckoutPg objReqCheckout = new CheckoutPg(driver, logger);
					if (findElement(objReqCheckout.getRequisitionNm()).getAttribute("value") == requisitionName)
						result = true;
					else {
						System.out.println("Requested Requisition not opened for editing");
						result = false;
					}
					break;
				case "Review and submit":
					RequisitionSubmitPg objReqSubmit = new RequisitionSubmitPg(driver, logger);
					if (findElement(objReqSubmit.getRequisitionName()).getText() == requisitionName)
						result = true;
					else {
						System.out.println("Requested Requisition not opened for review & submit");
						result = false;
					}
					break;
				case "Create Receipt":
					CreateReceipt_page objCreateReceipt = new CreateReceipt_page(driver, logger);
					if (findElement(objCreateReceipt.getHeaderReqNum()).getText() == requisitionNum
							&& findElement(objCreateReceipt.getHeaderReqName()).getText() == requisitionName)
						result = true;
					else {
						System.out.println("Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				case "Remind Approver":
					if (findElement(reminderMsgSentXpath) != null)
						result = true;
					else {
						System.out.println("Reminder message has been sent not displayed");
						result = false;
					}
					break;
				case "Copy":
					if (findElement(By.xpath("//div[div//td[contains(text(),'Items from" + objRequisitionNum
							+ "will be added to the shopping cart')]]")).getAttribute("style").contains("block")) {

						/*
						 * Add code for clicking 'Proceed only with Req items'
						 */

						findElement(By.xpath("//div[div//td[contains(text(),'Items from" + objRequisitionNum
								+ "will be added to the shopping cart')]]//button/span[text()='Proceed with Req & existing cart items']")).click();
						CheckoutPg objReqCheckout1 = new CheckoutPg(driver, logger);
						if (findElement(objReqCheckout1.getRequisitionNm()).getAttribute("value") == requisitionName)
							result = true;
						else {
							System.out.println("Requested Requisition not opened for editing");
							result = false;
						}
					} else {
						System.out.println("Requested Requisition not opened for editing");
						result = false;
					}
					break;
				}
			} else
				System.out.println("Requisition is not in required status for the requested action");
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
			findElement(By.xpath("(//*[@id='reqList']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='" + action
					+ "']")).click();

			if (verifyDisplayedAction(action)) {

				WebElement objRequisitionNum = findElement(requisitionNumTxtXpath);
				String requisitionNum = objRequisitionNum.getText();

				switch (action) {
				case "Recall approval request":
					if (findElement(recallApprovalReqPopUp) != null) {
						sendKeys(recallApprovalCmmntId, actionBasedReq_Comment);
						findElement(recallBtnId).click();
						if (findElement(recallReqActionMsgId) != null) {
							WebElement objRecalledReqNum = findElement(
									By.xpath("//table[@id='reqList']//td[2]/a[text()='" + requisitionNum + "']"));
							if (objRecalledReqNum == null)
								result = true;
						}
					} else {
						System.out.println("Recall Approval Request pop up not displayed");
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
						 * "//table[@id='reqList']//td[2]/a[text()='"+
						 * requisitionNum +"']")); if(objRecalledReqNum==null)
						 * result = true; }
						 */

					} else {
						System.out.println("Recall Approval Request pop up not displayed");
						result = false;
					}
					break;
				}
			} else
				System.out.println("Requisition is not in required status for the requested action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
