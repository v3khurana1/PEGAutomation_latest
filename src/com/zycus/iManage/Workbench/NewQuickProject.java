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

public class NewQuickProject extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String progName;
	private String category;
	private String businessUnit;
	private String projectCategory;
	private String projTitle;
	private String projDesc;
	private String projPriority;
	private String projStartDt;
	private String projEndDt;
	private String currency;
	private String searchBy;
	private String searchValue;
	private int potentialSpend;
	private int potentialSavings;
	private int targetSpend;
	private int targetSavings;
	private int completionPercent;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public NewQuickProject(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public NewQuickProject(WebDriver driver, ExtentTest logger, int completionPercent, String category, String businessUnit, String sampleTemplateTitle, String projectCategory, String progName, String projTitle, String projDesc, String projPriority, String customProjType, String projStartDt, String projEndDt, String currency, String searchBy, String searchValue, int potentialSpend, int potentialSavings, int targetSpend, int targetSavings) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.completionPercent = completionPercent;
		this.category = category;
		this.businessUnit = businessUnit;
		this.projectCategory = projectCategory;
		this.progName = progName;
		this.projTitle = projTitle;
		this.projDesc = projTitle;
		this.projPriority = projPriority;
		this.projStartDt = projStartDt;
		this.projEndDt = projEndDt;
		this.currency = currency;
		this.searchBy = searchBy;
		this.searchValue = searchValue;
		this.potentialSpend = potentialSpend;
		this.potentialSavings = potentialSavings;
		this.targetSpend = targetSpend;
		this.targetSavings = targetSavings;
		
	}
	
	public boolean createNewQuickProject(){
		boolean result = false;
		try{
			addProjectDetails(projectCategory, progName, projTitle, projDesc, projPriority, projStartDt, projEndDt, currency);
			findElement(By.xpath("//select[@id='completion']/option[@id='"+completionPercent+"']")).click();
			selectCategory(category);
			selectBusinessUnit(businessUnit);
			addprojectScope(potentialSpend, potentialSavings, targetSpend, targetSavings);
			addMoreDetails();
			ProjectSummary objSummary = new ProjectSummary(driver, logger);
			objSummary.initiateProject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean addMoreDetails(){
		boolean result = false;
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addprojectScope(int potentialSpend, int potentialSavings, int TargetSpend, int TargetSavings){
		boolean result = false;
		try{
			//Add Potential Savings
			driver.findElement(By.id("potentialSpend")).sendKeys(String.valueOf(potentialSpend));
			driver.findElement(By.id("potentialSavings")).sendKeys(String.valueOf(potentialSavings));
			
			//Add Target Savings
			driver.findElement(By.id("estimatedSpend")).sendKeys(String.valueOf(TargetSpend));
			driver.findElement(By.id("targetSaving")).sendKeys(String.valueOf(TargetSavings));
			
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	

}
