package com.zycus.iSave.MyProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> StrategicProject.java</br>
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

public class StrategicProject extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String commodity;
	private String division;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public StrategicProject(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public StrategicProject(WebDriver driver, ExtentTest logger, String commodity, String division) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.commodity = commodity;
		this.division = division;
	}

	public boolean createNewProject(){
		boolean result = false;
		try{
			findElement(By.id("strategicCreateProject")).click();
			ProjectDetails objDetails = new ProjectDetails(driver, logger);
			objDetails.addProjectDetails("");
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean selectCommodity(){
		boolean result = false;
		try{
			findElement(By.xpath("//form[@id='addProjectScope']//input[@value='Select Commodity']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			ProjectDetails objDetails = new ProjectDetails(driver, logger);
			if(driver.findElement(By.xpath("//div[@id='newJsTreeStructure']/div[span[text()='Select Commodity']]/following-sibling::div[contains(@id,'Commodity')]")).getAttribute("style").contains("block")){
				if(objDetails.searchCommodity(commodity))
					result = true;
			}else
				logger.log(LogStatus.INFO, "Select Commodity Pop up not displayed");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean selectDivision(){
		boolean result = false;
		try{
			findElement(By.xpath("//form[@id='addProjectScope']//input[@value='Select Division']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			ProjectDetails objDetails = new ProjectDetails(driver, logger);
			if(driver.findElement(By.xpath("//div[@id='newJsTreeStructure']/div[span[text()='Select Division']]/following-sibling::div[contains(@id,'Division')]")).getAttribute("style").contains("block")){
				if(objDetails.searchDivision(division))
					result = true;
			}else
				logger.log(LogStatus.INFO, "Select Division Pop up not displayed");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByProjectID(String projectID){
		boolean result = false;
		try{
			result = filterByText("Project Id", projectID)?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByProjectTitle(String projectTitle){
		boolean result = false;
		try{
			result = filterByText("Project Title", projectTitle)?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterBySavingsStage(String savingsStage){
		boolean result = false;
		try{
			findElement(By.xpath("//th[@id='savingsStage_filterColumn']/div/div/label")).click();
			result = filterByChkbox(savingsStage, By.xpath("//table[@id='itrack-project-grid']/tbody//td[3]/label"))?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByStatus(String status){
		boolean result = false;
		try{
			findElement(By.xpath("//th[@id='status_filterColumn']/div/div/label")).click();
			result = filterByChkbox(status, By.xpath("//table[@id='itrack-project-grid']/tbody//td[8]"))?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
