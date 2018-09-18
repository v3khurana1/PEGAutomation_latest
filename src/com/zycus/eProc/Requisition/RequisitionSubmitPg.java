package com.zycus.eProc.Requisition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> RequisitionSubmitPg.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class RequisitionSubmitPg extends eProc_CommonFunctions {
	//private WebDriver driver;
	//private ExtentTest logger;
	
	private By RequisitionName = By.id("lblRequisitionName");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public RequisitionSubmitPg(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * @return the requisitionName
	 */
	public By getRequisitionName() {
		return RequisitionName;
	}

	/**
	 * @param requisitionName
	 *            the requisitionName to set
	 */
	public void setRequisitionName(By requisitionName) {
		this.RequisitionName = requisitionName;
	}
}
