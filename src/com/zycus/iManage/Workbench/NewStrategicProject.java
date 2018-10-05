package com.zycus.iManage.Workbench;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> NewStrategicProject.java</br>
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

public class NewStrategicProject extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String progName;
	private String category;
	private String businessUnit;
	private String sampleTemplateTitle;
	private String projectCategory;
	private String projTitle;
	private String projDesc;
	private String projPriority;
	private String customProjType;
	private String projStartDt;
	private String projEndDt;
	private String currency;
	private String searchBy;
	private String searchValue;
	private int potentialSpend;
	private int potentialSavings;
	private int targetSpend;
	private int targetSavings;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public NewStrategicProject(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public NewStrategicProject(WebDriver driver, ExtentTest logger, String category, String businessUnit, String sampleTemplateTitle, String projectCategory, String progName, String projTitle, String projDesc, String projPriority, String customProjType, String projStartDt, String projEndDt, String currency, String searchBy, String searchValue, int potentialSpend, int potentialSavings, int targetSpend, int targetSavings) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.category = category;
		this.businessUnit = businessUnit;
		this.sampleTemplateTitle = sampleTemplateTitle;
		this.projectCategory = projectCategory;
		this.progName = progName;
		this.projTitle = projTitle;
		this.projDesc = projTitle;
		this.projPriority = projPriority;
		this.customProjType = customProjType;
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
	
	public boolean createNewProjectFromExistingTemplate(){
		boolean result = false;
		try{
			/** Select Category */
			selectCategory(category);
			
			/** Select Business Unit */
			selectBusinessUnit(businessUnit);
			
			/** Select Template */
			findElement(By.xpath("//table[@id='search-template-grid']/tbody/tr/td[label[text()='"+sampleTemplateTitle+"']]/preceding-sibling::td/input")).click();
			findElement(By.xpath("//input[@title='Save & Continue']")).click();
			

			addProjectDetails(projectCategory, progName, projTitle, projDesc, projPriority, customProjType, projStartDt, projEndDt, currency);
			//Click Save & Next button
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			addMoreDetails();
			addprojectScope(potentialSpend, potentialSavings, targetSpend, targetSavings);
			//Click Save & Next button
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			addprojectPhases();
			selectStakeholders();
			ProjectSummary objSummary = new ProjectSummary(driver, logger);
			objSummary.initiateProject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean createNewProjectFromScratch(){
		boolean result = false;
		try{
			addProjectDetails(projectCategory, progName, projTitle, projDesc, projPriority, projStartDt, projEndDt, currency);
			selectCategory(category);
			selectBusinessUnit(businessUnit);
			//Click Save & Next button
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			addMoreDetails();
			addprojectScope(potentialSpend, potentialSavings, targetSpend, targetSavings);
			//Click Save & Next button
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			addprojectPhases();
			selectStakeholders();
			ProjectSummary objSummary = new ProjectSummary(driver, logger);
			objSummary.initiateProject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean createNewProjectFromExistingProject(){
		boolean result = false;
		try{
			selectCategory(category);
			selectBusinessUnit(businessUnit);
			findElement(By.xpath("//table[@id='search-project-grid']/tbody/tr/td[label[text()='"+sampleTemplateTitle+"']]/preceding-sibling::td/input")).click();
			findElement(By.xpath("//input[@title='Save & Continue']")).click();
			addProjectDetails(projectCategory, progName, projTitle, projDesc, projPriority, projStartDt, projEndDt, currency);
			//Click Save & Next button
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			addMoreDetails();
			addprojectScope(potentialSpend, potentialSavings, targetSpend, targetSavings);
			//Click Save & Next button
			findElement(By.xpath("//button[@value='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			addprojectPhases();
			selectStakeholders();
			ProjectSummary objSummary = new ProjectSummary(driver, logger);
			objSummary.initiateProject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addProjectDetails(String projectCategory, String progName, String projTitle, String projDesc, String projPriority, String customProjType, String projStartDt, String projEndDt, String currency){
		boolean result = false;
		try{
			super.addProjectDetails(projectCategory, progName, projTitle, projDesc, projPriority, projStartDt, projEndDt, currency);
			findElement(By.xpath("//select[@name='projectTypeList']/option[text()='"+customProjType+"']")).click();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addMoreDetails(){
		boolean result = false;
		try{
			findElement(By.id("saveAndNextFlBtnForSP")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
		
	
	public boolean addprojectPhases(String...phaseTitle){
		boolean result = false;
		try{
			int noOfPhasesDisplayedBefore = driver.findElements(By.xpath("//ul[@id='sortable']/li")).size();
			
			/** Add Phases */
			int phaseCtr = 0;
			for(String phase: phaseTitle){
				phaseCtr++;
				if(phaseCtr==1)
					addPhase(phase);
				else if(phaseCtr <= noOfPhasesDisplayedBefore)
					findElement(By.xpath("//ul[@id='sortable']/li["+phaseCtr+"]")).click();
				else
					addAnotherPhase(phase);
			}
			
			findElement(By.xpath("//input[@title='Save & Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result; 
	}
	
	public boolean selectStakeholders(){
		boolean result = false;
		try{
			if(searchStakeholder(searchBy, searchValue)){
				findElement(By.xpath("//table[@id='dataGrid']/tbody/tr[1]/td[1]/input")).click();
				findElement(By.id("stakeHolderPageNext")).click();
				ProjectSummary objSummary = new ProjectSummary(driver, logger);
				if(driver.findElement(objSummary.getPgNameIdentifier()).isDisplayed())
					result = true;
			}else
				logger.log(LogStatus.FAIL, "Stakeholder with "+searchBy+" : "+searchValue+" not found");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected boolean searchStakeholder(String searchBy, String searchValue){
		boolean result = false;
		try{
			findElement(By.xpath("//select[@id='searchSelect']/option[text()='"+searchBy+"']")).click();
			driver.findElement(By.id("stakeHolderSearchText")).sendKeys(searchValue);
			findElement(By.xpath("//input[@value='Go']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

}
