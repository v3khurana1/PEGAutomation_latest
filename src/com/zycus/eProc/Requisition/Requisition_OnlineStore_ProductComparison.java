package com.zycus.eProc.Requisition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Requisition_OnlineStore_ProductComparison.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * @author Varun Khurana
 * @since April 2018
 */

public class Requisition_OnlineStore_ProductComparison extends eProc_CommonFunctions{
	//private WebDriver driver;
	//private ExtentTest logger;
	
	private By compareItemsPg 		= By.id("compareItems");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public Requisition_OnlineStore_ProductComparison(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * @return the compareItemsPg
	 */
	public By getCompareItemsPg() {
		return compareItemsPg;
	}

	/**
	 * @param compareItemsPg the compareItemsPg to set
	 */
	public void setCompareItemsPg(By compareItemsPg) {
		this.compareItemsPg = compareItemsPg;
	}
	
}
