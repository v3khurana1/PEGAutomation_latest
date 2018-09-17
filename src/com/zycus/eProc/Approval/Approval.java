package com.zycus.eProc.Approval;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <p>
 * <b> Title: </b> Approval.java</br>
 * <br>
 * <b> Description: </b> Approval - Approval related class</br>
 * <br>
 * <b> Functions: </b> None </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Approval extends ApprovalDetails{

	private WebDriver driver;
	private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * @param driver
	 */
	
	public Approval(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

}
