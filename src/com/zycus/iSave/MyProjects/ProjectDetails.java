package com.zycus.iSave.MyProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Framework.ConfirmationDialog;
import common.Functions.eInvoice_CommonFunctions;
import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> ProjectDetails.java</br>
 * <br>
 * <b> Description: </b> To perform operations on iSave - My Project - Quick Savings Entry - Create New Savings Entry
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class ProjectDetails extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String projectTitle;
	private String savingsTrackingFreq;
	private String savingsStartMnth;
	private String savingsStartYr;
	private String savingsEndMnth;
	private String savingsEndYr;
	private String savingType;
	private String projectCurr;
	private String exchngRate;
	private String commodity;
	private String projectType;
	private String division;
	private String savingsStage;
	private String baselineSet;
	private String savingsFormula;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public ProjectDetails(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public ProjectDetails(WebDriver driver, ExtentTest logger, String projectTitle, String savingsTrackingFreq, String savingsStartMnth, String savingsStartYr, String savingsEndMnth, String savingsEndYr, String projectCurr, String savingType, String exchngRate, String commodity, String projectType, String division, String savingsStage, String baselineSet, String savingsFormula) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.projectTitle = projectTitle;
		this.savingsTrackingFreq = savingsTrackingFreq;
		this.savingsStartMnth = savingsStartMnth;
		this.savingsStartYr = savingsStartYr;
		this.savingsEndMnth = savingsEndMnth;
		this.savingsEndYr = savingsEndYr;
		this.projectCurr = projectCurr;
		this.savingType = savingType;
		this.exchngRate = exchngRate;
		this.commodity = commodity;
		this.projectType = projectType;
		this.division = division;
		this.savingsStage = savingsStage;
		this.baselineSet = baselineSet; 
		this.savingsFormula = savingsFormula;
	}

	public boolean enterProjectDetails(){
		boolean result = false;
		try{
			addSavingsDetails();
			addSavingsScope();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addSavingsDetails(String projDesc){
		boolean result = false;
		try{
			addSavingsDetails(projDesc);
			addSavingsScope();
			findElement(By.id("quickSaveBtn")).click();
			waitUntilInvisibilityOfElement(By.id("iSaveOverlayMsgBox"));
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addProjectDetails(String projectDesc){
		boolean result = false;
		try{
			/** Project Title */
			driver.findElement(By.id("project-title")).sendKeys(projectTitle);
			
			/** Project Description */
			driver.findElement(By.id("project-desc")).sendKeys(projectDesc);
			
			/** Frquency of Savings Tracking */
			findElement(By.xpath("//select[@id='projectTimelineNameQuickProject']/option[text()='"+savingsTrackingFreq+"']")).click();
			
			/** Start Date of Savings */
			findElement(By.xpath("//select[@id='startProjectTimelineNameQuickProject']/option[text()='"+savingsStartMnth+"']")).click();
			findElement(By.xpath("//select[@id='startYearQuickProject']/option[text()='"+savingsStartYr+"']")).click();
			
			/** End Date of Savings */
			findElement(By.xpath("//select[@id='endProjectTimelineNameQuickProject']/option[text()='"+savingsEndMnth+"']")).click();
			findElement(By.xpath("//select[@id='endYearQuickProject']/option[text()='"+savingsEndYr+"']")).click();
			
			/** Project Currency */
			enterText_AutoComplete(By.id("auto_sugg_project-currency"), projectCurr);
			
			/** Project Saving Stage */
			findElement(By.xpath("//select[@id='project-savingType']/option[text()='"+savingType+"']")).click();
			
			/** Select Exchange Rate */
			findElement(By.xpath("//select[@id='exchangeRate']/option[text()='"+exchngRate+"']")).click();
			
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addSavingsDetails(){
		boolean result = false;
		try{
			if(addSavingsDetails(""))
				result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addSavingsScope(){
		boolean result = false;
		try{
			selectCommodity();
			selectProjectType(projectType);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean selectCommodity(){
		boolean result = false;
		try{
			findElement(By.id("commoditySelectQuickPrj")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if(driver.findElement(By.xpath("//div[@id='selectPopUp']/preceding-sibling::div/../div/span[text()='Select Category']/../..")).getAttribute("style").contains("block")){
				if(searchCommodity(commodity))
					result = true;
			}else
				logger.log(LogStatus.INFO, "Select Commodity Pop up not displayed");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	protected boolean searchCommodity(String commodity){
		boolean result = false;
		try{
			driver.findElement(By.id("CommoditySearch")).sendKeys(commodity +Keys.ENTER);
			waitUntilInvisibilityOfElement(By.xpath("//li[contains(@id,'loading')])"));
		}catch(Exception e){
			findElement(By.id("CommoditySearchIcon")).click();
		}
			findElement(By.xpath("(//div[@id='CommodityJsTree']//a[contains(text(),'"+commodity+"')])/i[1]")).click();
			findElement(By.id("CommodityDone")).click();
			if(driver.findElement(By.xpath("//div[@id='categoryName1']//li/label[text()='"+commodity+"']")).isDisplayed())
				result = true;
		return result;
	}
	
	public boolean selectProjectType(String projectType){
		boolean result = false;
		try{
			findElement(By.xpath("//select[@id='purchaseType']/option[text()='"+projectType+"']")).click();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean manageSavings(){
		boolean result = false;
		try{
			switchBetweenManageSavingsStages();
			selectDivision();
			selectSavingsFormula();
			findElement(By.xpath("//div[@id='manageSavingsCnt']//input[@title='Save']")).click();
			waitUntilInvisibilityOfElement(By.id("iSaveOverlayMsgBox"));
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean switchBetweenManageSavingsStages(){
		boolean result = false;
		try{
			findElement(By.id("changeStageBtn")).click();
			waitUntilVisibilityOfElement(By.xpath("//div[@id='ajaxManageSavingPopupData']"));
			findElement(By.xpath("//div[@id='ajaxManageSavingPopupData']//div[@id='imageBoxInsideSec']//span[@title='"+savingsStage+"']")).click();
			if(driver.findElement(By.xpath("//a[@id='changeStageBtn']/span")).getText().equals(savingsStage))
				result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean selectDivision(){
		boolean result = false;
		try{
			findElement(By.id("selectBU")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if(driver.findElement(By.xpath("//div[@id='newJsTreeStructure']/div[span[text()='Select Division']]/following-sibling::div")).getAttribute("style").contains("block")){
				if(searchDivision(division))
					result = true;
			}else
				logger.log(LogStatus.INFO, "Select Division Pop up not displayed");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	protected boolean searchDivision(String division) throws InterruptedException{
		boolean result = false;
		try{
			driver.findElement(By.id("DivisionSearch")).sendKeys(division +Keys.ENTER);
			waitUntilInvisibilityOfElement(By.xpath("//li[contains(@id,'loading')])"));
		}catch(Exception e){
			findElement(By.id("DivisionSearchIcon")).click();
		}
			findElement(By.xpath("(//div[@id='DivisionJsTree']//a[contains(text(),'"+division+"')])/i[1]")).click();
			findElement(By.id("DivisionDone")).click();
			Thread.sleep(3000);
			if(driver.findElement(By.xpath("//div[@id='categoryName1']//li/label[text()='"+division+"']")).isDisplayed())
				result = true;
		return result;
	}
	
	public boolean selectSavingsFormula(){
		boolean result = false;
		try{
			findElement(By.xpath("//a[@title='Select Savings Formula']")).click();
			if(driver.findElement(By.xpath("//div[@class='dropdown-content formula-content']")).getAttribute("style").contains("block")){
				findElement(By.xpath("//select[@id='baselineCFSet']/option[text()='"+baselineSet+"']")).click();
				if(driver.findElement(By.id("allStageDivisionStatusPopup")).isDisplayed()){
					driver.findElement(By.xpath("//div[@id='allStageDivisionStatusPopup']//input[@title='OK']")).click();
					waitUntilInvisibilityOfElement(processingLoader);
				}
				findElement(By.xpath("//select[@id='selectFormulaOfQuick']/option[text()='"+savingsFormula+"']")).click();
				if(driver.findElement(ConfirmationDialog.getDialogTitle()).equals("Confirmation Dialog")){
					if(driver.findElement(ConfirmationDialog.getPopupMsg()).getText().contains("values entered in this stage for all divisions will be modified")){
						driver.findElement(ConfirmationDialog.getDialogYesBtn()).click();
						result = true;
					}else
						logger.log(LogStatus.INFO, "Incorrect Savings formula dialog displayed");
				}else
					logger.log(LogStatus.INFO, "Savings formula Confirmation dialog not displayed");
			}else
				logger.log(LogStatus.INFO, "Savings formula selection pop up not displayed");
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
}
