package com.zycus.iSave.MyProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> QuickSavingsEntry.java</br>
 * <br>
 * <b> Description: </b> To perform operations on iSave - My Projects - Quick Savings Entry
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class QuickSavingsEntry extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public QuickSavingsEntry(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean createNewSavingsEntry(){
		boolean result = false;
		try{
			findElement(By.id("quickCreateProject")).click();
			ProjectDetails objDetails = new ProjectDetails(driver, logger);
			objDetails.enterProjectDetails();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
