package com.zycus.iSource.Approval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

//import common.Functions.eInvoice_CommonFunctions;\
import common.Reports.Common_Reports;

public class Delegate extends Common_Reports {

	private WebDriver driver;
	//private ExtentTest logger;
	private String delegateTo;
	private String startDt;
	private String endDt;
	private String comments;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Delegate(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}
	
	public Delegate(WebDriver driver, ExtentTest logger, String delegateTo, String startDt, String endDt, String comments) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
		this.delegateTo = delegateTo; 
		this.startDt = startDt;
		this.endDt= endDt; 
		this.comments = comments;
	}



	public boolean delegateApprovalTo() throws Exception {
		boolean result = false;
		try {
			driver.findElement(By.id("searchUser")).sendKeys(delegateTo);
			findElement(By.xpath("//input[@id='fromDelgDate']/img")).click();
			selectDate_v1(startDt);
			findElement(By.xpath("//input[@id='toDelgDate']/img")).click();
			selectDate_v1(endDt);
			driver.findElement(By.id("comment")).sendKeys(comments);
			findElement(By.id("delSettingsBut")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

}
