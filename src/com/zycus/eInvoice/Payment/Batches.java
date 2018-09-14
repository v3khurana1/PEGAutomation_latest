package com.zycus.eInvoice.Payment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class Batches extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public Batches(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> createNewBatch
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param None
	 * @return result - True/False
	 * @throws None
	 */

	public boolean createNewBatch() {
		boolean result = false;
		try {
			findElement(By.id("lnkCreatePaymentBatch")).click();
			NewPaymentBatch objPaymentBatch = new NewPaymentBatch(driver, logger);
			if (findElement(objPaymentBatch.getPgHead()) != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> searchBatchNo
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param batchNo
	 * @return result - True/False
	 * @throws None
	 */

	public boolean searchBatchNo(String batchNo) throws Exception {
		boolean result = false;
		try {
			result = filterByText("Batch No", batchNo) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> searchCreatedBy
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param createdBy
	 * @return result - True/False
	 * @throws None
	 */

	public boolean searchCreatedBy(String createdBy) throws Exception {
		boolean result = false;
		try {
			result = filterByText_AutoComplete("Created By", createdBy) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> searchBankAccount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param bankAcct
	 * @return result - True/False
	 * @throws None
	 */

	public boolean searchBankAccount(String bankAcct) throws Exception {
		boolean result = false;
		try {
			result = filterByText("Bank Account", bankAcct) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
