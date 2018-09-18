package com.zycus.eInvoice.Approval;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Approval.java</br>
 * <br>
 * <b> Description: </b> Perform operations on a purchase order</br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.filterByStatus: user shall be able to download PO as PDF </br>
 * <br>
 * 2.previewPOascXML: user shall be able to preview PO cXML </br>
 * <br>
 * 3.createShipmentNotice: user shall be able to create a shipment notice </br>
 * 
 * @author Anisha
 * @since April 2018
 */

public class Approval extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	//private By processingLoader = By.id("workflowApproval_processing");
	private By statusXpath = By.xpath("//table[contains(@class,'dataTable')]//td[2]/div");
	private By documentNoXpath = By.xpath("//table[contains(@class,'dataTable')]//td[2]/div");
	private By dateXpath = By.xpath("//table[contains(@class,'dataTable')]//td[contains(@class,'receivedOn')]");
	private By amountXpath = By.xpath("//table[contains(@class,'dataTable')]//td[contains(@class,'entityAmount')]");
	private By filterBtnXpath = By.xpath(
			"//div[contains(@id,'qtip') and @aria-hidden='false']//div[contains(@class,'FilterBtnbx')]//a[text()='Filter']");
	private By approveCommentId = By.id("approvalComments");
	private By approveBtnXpath = By.xpath(".//*[@id='frmApprove']//input[contains(@class,'dev_approve')]");
	private By approvedMsgXpath = By
			.xpath("//div[contains(@class,'globalMessage')]//span[contains(text(),'approved')]");
	private By rejectCommentId = By.id("rejectComments");
	private By rejectBtnXpath = By.xpath("//*[@id='frmReject']//input[contains(@class,'dev_reject')]");
	private By rejectMsgXpath = By.xpath("//div[contains(@class,'globalMessage')]//span[contains(text(),'rejected')]");
	private By delegateNameId = By.id("txtDelegateName");
	private By delegateCommentId = By.id("delegateComments");
	private By saveDelegateBtnId = By.id("btnDelegateSave");
	private By delegateMsgXpath = By.xpath("//div[contains(@class,'globalMessage')]//span[contains(text(),'delegated')]");
	//private By actionBtnXpath = By.xpath("//*[@id='workflowApproval']//tr[1]/td[8]//a[text()='Actions']");
	private By actionBtnXpath = By.xpath("//table[contains(@class,'dataTable')]//tr[1]/td[last()]//a[text()='Actions']");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public Approval(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean performActionOnInvoice(String action) {
		boolean status = false;
		try {
			findElement(actionBtnXpath ).click();
			findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[1]//li/a[contains(text(),'" + action + "')]"))
					.click();
			Thread.sleep(2000);
			switch (action) {
			case "Approve":
				// TODO need to add logic to input invoice No
				sendKeys(approveCommentId, "approving the document");
				/*findElement(approveBtnXpath).click();
				waitUntilVisibilityOfElement(approvedMsgXpath);*/
				clickAndWaitUntilElementAppears(approveBtnXpath, approvedMsgXpath);
				logger.log(LogStatus.INFO, "approved the invoice");
				status = true;
				break;
			case "Reject":
				sendKeys(rejectCommentId , "rejecting the document");
				/*findElement(rejectBtnXpath).click();
				waitUntilVisibilityOfElement(rejectMsgXpath);*/
				clickAndWaitUntilElementAppears(rejectBtnXpath, rejectMsgXpath);
				logger.log(LogStatus.INFO, "rejected the invoice");
				status = true;
				break;
			case "Delegate":
				sendKeys(delegateNameId ,"Chaitali");
				Thread.sleep(2000);
				findElement(By.xpath("//ul[contains(@style,'block')]/li")).click();
				sendKeys(delegateCommentId, "delegating the document");
				/*findElement(saveDelegateBtnId).click();
				waitUntilVisibilityOfElement(delegateMsgXpath);*/
				clickAndWaitUntilElementAppears(saveDelegateBtnId, delegateMsgXpath);
				logger.log(LogStatus.INFO, "delegated the invoice");
				status = true;
				break;
			default:
				logger.log(LogStatus.INFO, "select the right option");
				break;
			}

		} catch (Exception e) {
				e.printStackTrace();
		}
		return status;
	}

	/**
	 * <b>Function:</b> filterByStatus
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl
	 * @throws None
	 * @return result
	 * @throws Exception
	 */

	/*public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;

		findElement(By.xpath("//th[contains(@class,'statusFilter')]//b")).click();
		filterByChkbox(checkBoxLbl);
		waitUntilInvisibilityOfElement(processingLoader);
		List<WebElement> objfilteredList = driver.findElements(statusXpath);
		for (WebElement obj : objfilteredList) {
			if (obj.getText().equals(checkBoxLbl))
				result = true;
			else {
				result = false;
				break;
			}
		}
		// }
		return result;
	}*/
	
	public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'statusFilter')]//b")).click();
			result = filterByChkbox(checkBoxLbl, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByStatus(String checkBoxLbl, int pendingDays) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'statusFilter')]//b")).click();
			findElement(By.xpath("//input[contains(@class,'pendingSince') and @type='checkbox']")).click();
			findElement(By.xpath("//input[contains(@class,'pendingSince') and @type='text']")).sendKeys(String.valueOf(pendingDays));
			/*findElement(filterBtnXpath).click();
			waitUntilInvisibilityOfElement(processingLoader);*/
			clickAndWaitUntilLoaderDisappears(filterBtnXpath);
			List<WebElement> objfilteredList = driver.findElements(statusXpath);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(checkBoxLbl))
					result = true;
				else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * <b>Function:</b> filterByReceivedOn
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt,
	 *            ToDt
	 * @return result
	 * @throws Exception
	 */

	/*private boolean filterByReceivedOn(Date fromDt, Date ToDt) throws Exception {
		boolean result = false;

		findElement(By.xpath("//th[contains(@class,'receivedOnFilter')]//b")).click();
		filterByDateRange(fromDt, ToDt);
		waitUntilInvisibilityOfElement(processingLoader);
		// if(findElement(processingLoader).getAttribute("style").contains("block")){
		List<WebElement> objfilteredDateList = driver.findElements(dateXpath);
		for (WebElement obj : objfilteredDateList) {
			DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			Date dt = format.parse(obj.getText());
			if (dt.compareTo(fromDt) >= 0 && dt.compareTo(ToDt) <= 0)
				result = true;
			else {
				result = false;
				break;
			}
		}
		// }
		return result;
	}*/
	
	public boolean filterByReceivedOn(Date fromDt, Date ToDt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'receivedOnFilter')]//b")).click();
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
	 * @since April 2018
	 * @param fromDt,
	 *            ToDt, currType
	 * @return result
	 * @throws Exception
	 */

	/*private boolean filterByAmountRange(float fromAmt, float ToAmt, String currType) throws Exception {
		boolean result = false;
		try {
			filterByAmtRange(fromAmt, ToAmt, currType);
			waitUntilInvisibilityOfElement(processingLoader);
			// if(findElement(processingLoader).getAttribute("style").contains("block")){
			List<WebElement> objfilteredAmtList = driver.findElements(amountXpath);
			for (WebElement obj : objfilteredAmtList) {
				Float amount = Float.parseFloat((obj.getText().split(" "))[1]);
				String currencyType = null;
				if (amount >= fromAmt && amount <= ToAmt) {
					if (currType != "")
						currencyType = (obj.getText().split(" "))[0];
					if (currencyType == currType)
						result = true;
					else
						result = true;
				} else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	/**
	 * <b>Function:</b> filterByAmountToApprove
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt,ToDt
	 * @return result
	 * @throws Exception
	 */

	public boolean filterByAmountToApprove(float fromAmt, float ToAmt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmountToApprove
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt,
	 *            ToDt, currType
	 * @return result
	 * @throws Exception
	 */

	public boolean filterByAmountToApprove(float fromAmt, float ToAmt, String currType) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'amountFilter')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, currType, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterBySupplier
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param supplier
	 * @throws ParseException
	 * @return result
	 */
	@Deprecated
	public boolean filterBySupplier(String supplier) throws ParseException {
		boolean result = false;
		try {
			result = filterByText("Supplier", supplier) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByInitiator(String initiator) throws ParseException {
		boolean result = false;
		try {
			result = filterByText("Supplier", initiator) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByDocumentNo
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl
	 * @throws ParseException
	 * @return result
	 * @throws Exception
	 */

	/*public boolean filterByDocumentNo(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'numberFilter')]//b")).click();
			findElement(By.xpath("(//div[contains(@id,'qtip')]//input[following-sibling::text()[contains(.,'"
					+ checkBoxLbl + "')]])[1]")).click();
			findElement(filterBtnXpath).click();
			waitUntilInvisibilityOfElement(processingLoader);
			List<WebElement> objfilteredList = driver.findElements(documentNoXpath);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(checkBoxLbl))
					result = true;
				else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	/**
	 * <b>Function:</b> filterByDocumentNo
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl,
	 *            invoiceNo
	 * @throws ParseException
	 * @return result
	 * @throws Exception
	 */

	public boolean filterByDocumentNo(String checkBoxLbl, String invoiceNo) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'numberFilter')]//b")).click();
			sendKeys(By.id("txtFltrInvoiceNum"), invoiceNo);
			findElement(By.xpath("(//div[contains(@id,'qtip')]//input[following-sibling::text()[contains(.,'"
					+ checkBoxLbl + "')]])[1]")).click();
			/*findElement(filterBtnXpath).click();
			waitUntilInvisibilityOfElement(processingLoader);*/
			clickAndWaitUntilLoaderDisappears(filterBtnXpath);
			List<WebElement> objfilteredList = driver.findElements(documentNoXpath);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(invoiceNo))
					result = true;
				else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
