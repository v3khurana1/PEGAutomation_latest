package com.zycus.iManage.Workbench;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Track.java</br>
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

public class NewProgram extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String entityType;
	private String progName;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public NewProgram(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public NewProgram(WebDriver driver, ExtentTest logger, String progName) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.progName = progName;
	}
	
	public boolean createNewProgram(String progDesc){
		boolean result = false;
		try{
			driver.findElement(By.id("prgName")).sendKeys(progName);
			driver.findElement(By.id("description")).sendKeys(progDesc);
			
			//More fields
			findElement(By.id("saveAndDraftID")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			Track objTrack = new Track(driver, logger);
			if(driver.findElement(objTrack.getPgNameIdentifier()).isDisplayed())
				result = true;
			else
				logger.log(LogStatus.FAIL, "Not navigated to Track page after creating new Program");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean createNewProgram(){
		boolean result = false;
		try{
			if(createNewProgram(""))
				result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
