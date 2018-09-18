package com.zycus.eProc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class CheckoutPg extends eProc_CommonFunctions{
	//private WebDriver driver;
	//private ExtentTest logger;
	private By RequisitionNm = By.id("txtRequisitionName");
	
	public CheckoutPg(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * @return the requisitionNm
	 */
	public By getRequisitionNm() {
		return RequisitionNm;
	}

	/**
	 * @param requisitionNm the requisitionNm to set
	 */
	public void setRequisitionNm(By requisitionNm) {
		this.RequisitionNm = requisitionNm;
	}

}
