package com.zycus.eInvoice.Reconciliation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class ReconciliationSummary extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;
	private By pgHead = By.xpath("//h1[@class='pgHead']/span[text()='Reconciliation Summary']");
	private By batchName = By.xpath("//*[@id='uploadStatementFile']//li[label[contains(text(),'Batch Name')]]/div[1]/div");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public ReconciliationSummary(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}
	
	/**
	 * <b>Function:</b> reuploadStmtFile
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param matchChargesWithLine
	 * @param filePath
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean reuploadStmtFile(boolean matchChargesWithLine, String filePath) throws Exception {
		boolean result = false;
		try {
			if(matchChargesWithLine)
				findElement(By.id("txtLineLevel")).click();
			//Re-upload statement File
			sendKeys(By.xpath("//*[contains(@id,'attachmentInput')]"), filePath); 
			//Click 'Re-upload & Reconcile' button
			findElement(By.id("btnReUloadGcard")).click();
			result = true;
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
	 * @param pgHead the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}

	/**
	 * @return the batchName
	 */
	public By getBatchName() {
		return batchName;
	}

	/**
	 * @param batchName the batchName to set
	 */
	public void setBatchName(By batchName) {
		this.batchName = batchName;
	}

}
