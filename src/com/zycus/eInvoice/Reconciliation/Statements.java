package com.zycus.eInvoice.Reconciliation;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class Statements extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//h1[@class='pgHead'][text()='Statements']");
	private By processingLoader = By.id("reconciliationList_processing");
	private By statusXpath = By.xpath("//table[contains(@class,'dataTable')]//td[1]/div");
	private By typeXpath = By.xpath("//table[contains(@class,'dataTable')]//td[4]/div");
	private By stmtDtXpath = By.xpath("//table[contains(@class,'dataTable')]//td[5]");
	private By processedOnXpath = By.xpath("//table[contains(@class,'dataTable')]//td[6]");
	private By actionsLinkXpath = By.xpath("//table[contains(@class,'dataTable')]//tr[1]/td[last()]//a[text()='Actions']");
	private By batchNameXpath = By.xpath("//table[contains(@class,'dataTable')]//td[2]/a");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Statements(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> searchBatchName
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param batchName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean searchBatchName(String batchName) {
		boolean result = false;
		try {
			result = filterByText("Batch Name", batchName) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> searchBank
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param bank
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean searchBank(String bank) throws Exception {
		boolean result = false;
		try {
			result = filterByText("Bank", bank) ? true : false;
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
	 * @throws Exception
	 */

	/*
	 * public boolean filterByStatus(String checkBoxLbl) throws Exception {
	 * boolean result = false; try { findElement(By.xpath(
	 * "//th[contains(@class,'reconciliationStatusFltrHdr')]//b")).click();
	 * filterByChkbox(checkBoxLbl);
	 * waitUntilInvisibilityOfElement(processingLoader); // if //
	 * (findElement(processingLoader).getAttribute("style").contains("block"))
	 * // { List<WebElement> objfilteredList = driver.findElements(statusXpath);
	 * for (WebElement obj : objfilteredList) { if
	 * (obj.getText().equals(checkBoxLbl)) result = true; else { result = false;
	 * break; } } // } } catch (Exception e) { e.printStackTrace(); throw new
	 * Exception(); } return result; }
	 */

	public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'reconciliationStatusFltrHdr')]//b")).click();
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
	 * @throws Exception
	 */

	/*
	 * public boolean filterByType(String checkBoxLbl) throws Exception {
	 * boolean result = false; try { findElement(By.xpath(
	 * "//th[contains(@class,'reconciliationTypeFltrHdr')]//b")).click();
	 * filterByChkbox(checkBoxLbl);
	 * waitUntilInvisibilityOfElement(processingLoader); // if //
	 * (findElement(processingLoader).getAttribute("style").contains("block"))
	 * // { List<WebElement> objfilteredList = driver.findElements(typeXpath);
	 * for (WebElement obj : objfilteredList) { if
	 * (obj.getText().equals(checkBoxLbl)) result = true; else { result = false;
	 * break; } } // } } catch (Exception e) { e.printStackTrace(); throw new
	 * Exception(); } return result; }
	 */

	public boolean filterByType(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'reconciliationTypeFltrHdr')]//b")).click();
			result = filterByChkbox(checkBoxLbl, typeXpath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByStmtDate
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean filterByStmtDate(Date fromDt, Date ToDt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'reconciliationStatementDateFltrHdr')]//b")).click();
			result = filterByDateRange(fromDt, ToDt, stmtDtXpath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByProcessedOn
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean filterByProcessedOn(Date fromDt, Date ToDt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'reconciliationProcessedOnFltrHdr')]//b")).click();
			result = filterByDateRange(fromDt, ToDt, processedOnXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addNewStmt
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean addNewStmt() throws Exception {
		boolean result = false;
		try {
			findElement(By.id("addBatch")).click();
			ReconcileNewStatement objReconcilNewStmt = new ReconcileNewStatement(driver, logger);
			if (findElement(objReconcilNewStmt.getPgHead()) != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
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
	 * @throws Exception
	 */

	protected boolean verifyDisplayedAction(String action) {
		boolean result = false;
		try {
			// NO DATA AVAIALABLE FOR OTHER STATUS
			String status = findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[1]/td[1]/div")).getText();
			switch (action) {
			case "Review":
				if (status.equals("Not Matched"))
					result = true;
				break;
			case "Delete":
				if (status.equals("Not Matched"))
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
	 * @since May 2018
	 * @param action
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean takeAction(String action) throws Exception {
		boolean result = false;
		try {
			if (verifyDisplayedAction(action)) {
				WebElement objBatchName = findElement(batchNameXpath);
				String batchName = objBatchName.getText();

				findElement(actionsLinkXpath).click();
				findElement(By
						.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
								+ action + "']")).click();

				switch (action) {

				case "Review":
					ReconciliationSummary objReconcilSumm = new ReconciliationSummary(driver, logger);
					if (findElement(objReconcilSumm.getBatchName()).getText().equals(batchName))
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					findElement(By.id("btnCancelPCard")).click();
					break;

				case "Delete":
					if (findElement(By
							.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'Are you sure you want to delete this batch')]]"))
									.isDisplayed())
						findElement(By.xpath("//button/span[text()='OK']")).click();
					if (findElement(processingLoader).getAttribute("style").contains("block")) {
						try {
							WebElement objDeletedReqNum = findElement(
									By.xpath("//table[contains(@class,'dataTable')]//td[2]/a[text()='" + batchName + "']"));
							if (objDeletedReqNum != null)
								logger.log(LogStatus.INFO, "Deleted Requisition still exists");
						} catch (Exception e) {
							result = true;
						}
					} else
						logger.log(LogStatus.INFO, "Are you sure want to delete this batch - message not displayed");
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
	 * @return the pgHead
	 */
	public By getPgHead() {
		return pgHead;
	}

	/**
	 * @param pgHead
	 *            the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}
}
