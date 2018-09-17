package com.zycus.eInvoice.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class Workflow extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//h1[@class='pgHead'][contains(text(),'All Workflows')]");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Workflow(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> clickCreateWorkflow
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param displayName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean clickCreateWorkflow() throws Exception {
		boolean result = true;
		try {
			findElement(By.xpath("//a[span[text()='Create Workflow']]")).click();
			WorkflowWizard objWizard = new WorkflowWizard(driver, logger);
			result = findElement(objWizard.getPgHead()) != null ? true : false;
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
}
