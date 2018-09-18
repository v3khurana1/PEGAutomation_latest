package com.zycus.eProc.Approval;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

/**
 * <p>
 * <b> Title: </b> AllRequests.java</br>
 * <br>
 * <b> Description: </b> Approval - Approval Requests related class</br>
 * <br>
 * <b> Functions: </b> None </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */ 

public class AllRequests extends ApprovalDetails{

	//private WebDriver driver;
	//private ExtentTest logger; 
	
	/**
	 * Constructor for the class
	 * @param driver
	 */
	
	public AllRequests(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

}
