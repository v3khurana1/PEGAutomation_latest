package com.zycus.iManage.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> AdminTasks.java</br>
 * <br>
 * <b> Description: </b> To perform operations on iManage - Admin - Admin Tasks
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class AdminTasks extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;


	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public AdminTasks(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	//Incomplete : TODO : FL Migration, Report Migration
	//How to return result
	
	public boolean performAdminTasks(String...adminTasks){
		boolean result = false;
		boolean tempResult = false;
		boolean dontChngResult;
		try{
			for(String task : adminTasks){
				tempResult = false;
				switch(task){
				case "Perform DB Check":
					WebElement dbChk = driver.findElement(By.id("dbCheck"));
					if(dbChk.isEnabled()){
						dbChk.click();
						if(dbChk.findElement(By.xpath("//../following-sibling::td")).getText().equals("Successful")){
							logger.log(LogStatus.INFO, task+" done");
							tempResult  = true;
						}
					}else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				case "Disable Email Notification":
					WebElement disableNotification = driver.findElement(By.id("disableNotification"));
					if(disableNotification.isEnabled()){
						if(disableNotification.getAttribute("value").equals("Disable Email Notification")){
							disableNotification.click();
							if(disableNotification.findElement(By.xpath("//../following-sibling::td")).getText().equals("Disabled")){
								logger.log(LogStatus.INFO, task+" done");
								tempResult  = true;
							}
						}
					}else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				case "Enable Email Notification":
					WebElement enableNotification = driver.findElement(By.id("disableNotification"));
					if(enableNotification.isEnabled()){
						if(enableNotification.getAttribute("value").equals("Enable Email Notification")){
							enableNotification.click();
							if(enableNotification.findElement(By.xpath("//../following-sibling::td")).getText().equals("Enabled")){
								logger.log(LogStatus.INFO, task+" done");
								tempResult  = true;
							}
						}
					}else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				case "Disable Self Notification":
					WebElement disableSelfNotification = driver.findElement(By.id("disableSelfNotification"));
					if(disableSelfNotification.isEnabled()){
						if(disableSelfNotification.getAttribute("value").equals("Disable Self Notification")){
							disableSelfNotification.click();
							if(disableSelfNotification.findElement(By.xpath("//../following-sibling::td")).getText().equals("Disabled")){
								logger.log(LogStatus.INFO, task+" done");
								tempResult  = true;
							}
						}	
					}else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				case "Enable Self Notification":
					WebElement enableSelfNotification = driver.findElement(By.id("disableSelfNotification"));
					if(enableSelfNotification.isEnabled()){
						if(enableSelfNotification.getAttribute("value").equals("Enable Self Notification")){
							enableSelfNotification.click();
							if(enableSelfNotification.findElement(By.xpath("//../following-sibling::td")).getText().equals("Enabled")){
								logger.log(LogStatus.INFO, task+" done");
								tempResult  = true;
							}
						}
					}else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				case "Initiate FL Migration":
					WebElement InitiateFLMigration = driver.findElement(By.id("flexiMigration"));
					if(InitiateFLMigration.isEnabled())
						InitiateFLMigration.click();
					else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				case "Initiate Report Migration":
					WebElement InitiateReportMigration = driver.findElement(By.id("reportMigrationBtn"));
					if(InitiateReportMigration.isEnabled())
						InitiateReportMigration.click();
					else
						logger.log(LogStatus.INFO, task+" button disabled");
					break;
				}
	
			}
			if(tempResult == true)
				result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
