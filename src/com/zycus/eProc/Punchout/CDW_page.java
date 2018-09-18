package com.zycus.eProc.Punchout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> CDW_page.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * @author Varun Khurana
 * @since April 2018
 */

public class CDW_page extends eProc_CommonFunctions{
	//private WebDriver driver;
	//private ExtentTest logger;
	
	private By CDWPg 		= By.id("MasterPageBodyTag");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public CDW_page(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * @return the cDWPg
	 */
	public By getCDWPg() {
		return CDWPg;
	}

	/**
	 * @param cDWPg the cDWPg to set
	 */
	public void setCDWPg(By cDWPg) {
		this.CDWPg = cDWPg;
	}

}
