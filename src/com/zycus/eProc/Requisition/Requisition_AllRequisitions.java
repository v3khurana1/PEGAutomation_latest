package com.zycus.eProc.Requisition;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

/**
 * <p>
 * <b> Title: </b> Requisition_AllRequisitions.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * @author Varun Khurana
 * @since April 2018
 */

public class Requisition_AllRequisitions extends RequisitionDetails {
	
	//private ExtentTest logger;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public Requisition_AllRequisitions(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
	}
}
