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

public class ProjectSummary extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	private By pgNameIdentifier = By.xpath("//div[@class='breadCrumb' and contains(text(),'Project Summary')]"); 


	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public ProjectSummary(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public boolean initiateProject(){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@title='Initiate Project']")).click();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the pgNameIdentifier
	 */
	public By getPgNameIdentifier() {
		return pgNameIdentifier;
	}

	/**
	 * @param pgNameIdentifier the pgNameIdentifier to set
	 */
	public void setPgNameIdentifier(By pgNameIdentifier) {
		this.pgNameIdentifier = pgNameIdentifier;
	}
	
	
}
