package com.zycus.eInvoice.Reconciliation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class ReconcileNewStatement extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String batchName;
	private Date stmtDate;
	// private String stmtDate;
	private String bankName;

	private By pgHead = By.xpath("//h1[span[text()='Reconcile New Statement']]");
	private By processingLoader = By.id("reconciliationList_processing");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public ReconcileNewStatement(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param batchName
	 * @param bankName
	 * @param stmtDate
	 * @throws ParseException
	 * 
	 */

	public ReconcileNewStatement(WebDriver driver, ExtentTest logger, String batchName, String bankName,
			String stmtDate) throws ParseException {
		// String stmtDate) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.batchName = batchName + String.valueOf(generateNo());
		this.bankName = bankName;
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(stmtDate);
		this.stmtDate = date1;
	}

	/**
	 * <b>Function:</b> createNewStmt
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param matchChargesWithLine
	 * @param paymentDueDate
	 * @param filePath
	 * @return result - True/False
	 * @throws Exception
	 */

	// public boolean createNewStmt(boolean matchChargesWithLine, Date
	// paymentDueDate, String filePath) throws Exception {
	public boolean createNewStmt(boolean matchChargesWithLine, String filePath) throws Exception {
		boolean result = false;
		try {
			findElement(By.id("txtBatchName")).sendKeys(batchName);
			if (matchChargesWithLine)
				findElement(By.id("txtLineLevel")).click();
			enterText_AutoComplete(By.id("txtBankName"), bankName);
			// Thread.sleep(10000);
			findElement(By.xpath("//input[@id='txtStatementDate']/following-sibling::img")).click();
			selectDate(stmtDate);
			// selectDate(paymentDueDate);

			sendKeys(By.xpath("//*[contains(@id,'attachmentInput')]"), filePath);
			waitUntilInvisibilityOfElement(processingLoader);
			// Click 'Upload & Reconcile' button
			findElement(By.id("btnNextGcard")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			findElement(By
					.xpath("//div[contains(@style,'block')][div/span[text()='Information']]//button[span[text()='OK']]"))
							.click();
			waitUntilInvisibilityOfElement(processingLoader);
			Statements objStatements = new Statements(driver, logger);
			if (findElement(objStatements.getPgHead()) != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
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
