package com.zycus.iManage.MyTemplates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.eProc.Approval.Dialog;

import Framework.ConfirmationDialog;
import common.Functions.eInvoice_CommonFunctions;
import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> CreateNewTemplate.java</br>
 * <br>
 * <b> Description: </b> To Create a New Template
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since Oct 2018
 */


public class CreateNewTemplate extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	iManage_CommonFunctions objFunctions = new iManage_CommonFunctions(driver, logger);
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public CreateNewTemplate(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	

	//TODO: Add More Details - flexi fields
	
	public boolean createTemplateFromScratch(String templateName, String templateDesc, String category, String businessUnit, String...phaseTitle){
		boolean result = false;
		try{
			int noOfPhasesDisplayedBefore = driver.findElements(By.xpath("//ul[@id='sortable']/li")).size();
			
			driver.findElement(By.id("tmpName")).sendKeys(templateName);
			driver.findElement(By.id("description")).sendKeys(templateDesc);
			findElement(By.xpath("//input[@title='Save & Continue']")).click();
			
			//Add template Details
			AddTemplateDetails objTemplateDetails = new AddTemplateDetails(driver, logger);
			objTemplateDetails.selectCategory(category);
			objTemplateDetails.selectBusinessUnit(businessUnit);
			findElement(By.xpath("//input[@title='Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			
			/** Add More Details */
			findElement(By.xpath("//input[@title='Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			
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
			findElement(By.xpath("//input[@title='Next']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This function is used to create a Template from Existing template and then Save
	 * 
	 * @author Varun Khurana
	 * @since Oct 2018
	 * @param sampleTemplateTitle - existing Template to choose
	 * @param templateName - Name of the new Template
	 * @param templateDesc - Description of the Template
	 * @param category - Template Category
	 * @param businessUnit - Template Business Unit
	 * @return boolean true if the template gets created in Draft State
	 */
	
	public boolean createTemplateFromExistingAndSave(String sampleTemplateTitle, String templateName, String templateDesc, String category, String businessUnit){
		boolean result = false;
		try{
			if(createTemplateFromExistingTemplate(sampleTemplateTitle, templateName, templateDesc, category, businessUnit)){
				findElement(By.xpath("//input[@title='Save']")).click();
				waitUntilInvisibilityOfElement(processingLoader);
				MyTemplates objTemplate = new MyTemplates(driver, logger);
				objTemplate.filterByTemplateTitle(templateName);
				if(driver.findElement(By.xpath("//table[@id='template-grid']/tbody/tr[1]/td[3]")).getText().contains("Draft"))
					result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This function is used to create a Template from Existing template and then Publish
	 * 
	 * @author Varun Khurana
	 * @since Oct 2018
	 * @param sampleTemplateTitle - existing Template to choose
	 * @param templateName - Name of the new Template
	 * @param templateDesc - Description of the Template
	 * @param category - Template Category
	 * @param businessUnit - Template Business Unit
	 * @return boolean true if the template gets created in Published State
	 */
	
	public boolean createTemplateFromExistingAndPublish(String sampleTemplateTitle, String templateName, String templateDesc, String category, String businessUnit){
		boolean result = false;
		try{
			if(createTemplateFromExistingTemplate(sampleTemplateTitle, templateName, templateDesc, category, businessUnit)){
				findElement(By.xpath("//input[@title='Save & Publish']")).click();
				waitUntilInvisibilityOfElement(processingLoader);
				MyTemplates objTemplate = new MyTemplates(driver, logger);
				objTemplate.filterByTemplateTitle(templateName);
				if(driver.findElement(By.xpath("//table[@id='template-grid']/tbody/tr[1]/td[3]")).getText().contains("Published"))
					result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This function is used to create a Template from Existing template
	 * 
	 * @author Varun Khurana
	 * @since Oct 2018
	 * @param sampleTemplateTitle - existing Template to choose
	 * @param templateName - Name of the new Template
	 * @param templateDesc - Description of the Template
	 * @param category - Template Category
	 * @param businessUnit - Template Business Unit
	 * @param phaseTitle - 
	 * @return boolean true if the template gets created
	 */
	private boolean createTemplateFromExistingTemplate(String sampleTemplateTitle, String templateName, String templateDesc, String category, String businessUnit, String...phaseTitle){
		boolean result = false;
		try{
			/** Select Existing Template */
			AddTemplateDetails objTemplateDetails = new AddTemplateDetails(driver, logger);
			objTemplateDetails.selectCategory(category);
			objTemplateDetails.selectBusinessUnit(businessUnit);
			findElement(By.xpath("//table[@id='search-template-grid']/tbody/tr/td[label[text()='"+sampleTemplateTitle+"']]/preceding-sibling::td/input")).click();
			findElement(By.xpath("//input[@title='Save & Continue']")).click();
			
			if(!enterTemplateName(templateName, templateDesc))
				return false;
			
			//Add More Details - flexi fields
			
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean enterTemplateName(String templateName, String templateDesc){
		boolean result = false;
		try{
			/** Template Name & Description --popup */
			if(driver.findElement(By.id("saveTemplateNamePopUp")).isDisplayed()){
				driver.findElement(By.id("tmpName")).sendKeys(templateName);
				driver.findElement(By.id("description")).sendKeys(templateDesc);
				findElement(By.xpath("//input[@title='Done']")).click();
				waitUntilVisibilityOfElement(By.xpath("//div[@class='templateForm']"));
				result = true;
			}else{
				logger.log(LogStatus.FAIL, "Template Name & Description popup not displayed");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean createTemplateFromExistingProject(String sampleProjectName, String templateName, String templateDesc, String category, String businessUnit, String...phaseTitle){
		boolean result = false;
		try{
			/** Select Existing Template */
			AddTemplateDetails objTemplateDetails = new AddTemplateDetails(driver, logger);
			objTemplateDetails.selectCategory(category);
			objTemplateDetails.selectBusinessUnit(businessUnit);
			findElement(By.xpath("//table[@id='search-project-grid']/tbody/tr/td[label[text()='"+sampleProjectName+"']]/preceding-sibling::td/input")).click();
			findElement(By.xpath("//input[@title='Save & Continue']")).click();
			
			/** Template Name & Description --popup */
			if(driver.findElement(By.id("saveTemplateNamePopUp")).isDisplayed()){
				driver.findElement(By.id("tmpName")).sendKeys(templateName);
				driver.findElement(By.id("description")).sendKeys(templateDesc);
				findElement(By.xpath("//input[@title='Done']")).click();
				waitUntilVisibilityOfElement(By.xpath("//div[@class='templateForm']"));
			}else{
				logger.log(LogStatus.FAIL, "Template Name & Description popup not displayed");
				return false;
			}
			//Add More Details - flexi fields
			
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
}
