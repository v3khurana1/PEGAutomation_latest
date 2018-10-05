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

public class Track extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String entityType;
	
	private By pgNameIdentifier = By.xpath("//div[@class='breadCrumb' and contains(text(),'Track')]"); 

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public Track(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public Track(WebDriver driver, ExtentTest logger, String entityType) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.entityType = entityType;
	}
	
	public boolean createNewEntity(){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@title='Create New Entity']")).click();
			if(driver.findElements(By.xpath("//div[@aria-describedby='selectPopUp']")).size()>0){
				findElement(By.xpath("//div[@id='selectPopUp']//tbody/tr/td/div[h2[text()='"+entityType+"']]")).click();
				switch(entityType){
				case "Program":
					NewProgram objProg = new NewProgram(driver, logger);
					objProg.createNewProgram();
					break;
				case "Strategic Project":
					NewStrategicProject objStrategicProj = new NewStrategicProject(driver, logger);
					objStrategicProj.createNewProjectFromExistingTemplate();
					break;
				case "Quick Project":
					break;
				}
			}else
				logger.log(LogStatus.INFO, "Entity selection pop up not displayed");
				
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
