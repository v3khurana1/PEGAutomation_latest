package com.zycus.iManage.MyTemplates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;
import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> BlanketPurchaseOrder.java</br>
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

public class AddTemplateDetails extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	iManage_CommonFunctions objFunctions = new iManage_CommonFunctions(driver, logger);
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public AddTemplateDetails(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	
	
}
