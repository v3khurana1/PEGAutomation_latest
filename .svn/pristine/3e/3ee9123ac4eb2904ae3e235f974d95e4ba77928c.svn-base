package com.zycus.eProc.Approval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class Catalog extends eProc_CommonFunctions{

	private WebDriver driver;
	private ExtentTest logger;
	
	private By HeaderReqNum 	= By.xpath("//h1[@class='pgHead']/span[1]");
	private By HeaderReqName 	= By.xpath("//h1[@class='pgHead']/span[3]");
	
	public Catalog(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * @return the headerReqNum
	 */
	public By getHeaderReqNum() {
		return HeaderReqNum;
	}

	/**
	 * @param headerReqNum the headerReqNum to set
	 */
	public void setHeaderReqNum(By headerReqNum) {
		this.HeaderReqNum = headerReqNum;
	}

	/**
	 * @return the headerReqName
	 */
	public By getHeaderReqName() {
		return HeaderReqName;
	}

	/**
	 * @param headerReqName the headerReqName to set
	 */
	public void setHeaderReqName(By headerReqName) {
		HeaderReqName = headerReqName;
	}

}
