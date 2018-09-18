package com.zycus.eProc.Punchout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Punchouts_SearchPunchout.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.filterByStatus: user shall be able to filter by Status </br>
 * <br>
 * 2.filterByReceivedOn: user shall be able to filter by Received On </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Punchouts_SearchPunchout extends eProc_CommonFunctions{
	//private WebDriver driver;
	//private ExtentTest logger;
	
	private By AllPunchouts_popUp 		= By.xpath("//div[@aria-describedby='allPunchDiag']");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public Punchouts_SearchPunchout(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * @return the allPunchouts_popUp
	 */
	public By getAllPunchouts_popUp() {
		return AllPunchouts_popUp;
	}

	/**
	 * @param allPunchouts_popUp the allPunchouts_popUp to set
	 */
	public void setAllPunchouts_popUp(By allPunchouts_popUp) {
		this.AllPunchouts_popUp = allPunchouts_popUp;
	}

}
