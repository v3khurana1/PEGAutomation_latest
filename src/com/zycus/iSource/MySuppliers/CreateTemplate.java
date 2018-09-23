package com.zycus.iSource.MySuppliers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.iSource_CommonFunctions;
import common.Functions.eInvoice_CommonFunctions;

public class CreateTemplate extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String eventTitle;
	private boolean isTestEvent;
	private String dept;
	private String links;
	private String category;
	private String eventType;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public CreateTemplate(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public CreateTemplate(WebDriver driver, ExtentTest logger, String eventTitle, String eventType) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.eventTitle = eventTitle;
		this.eventType = eventType;
	}

	public CreateTemplate(WebDriver driver, ExtentTest logger, String eventTitle, boolean isTestEvent, String dept,
			String links, String category) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.eventTitle = eventTitle;
		this.isTestEvent = isTestEvent;
		this.dept = dept;
		this.links = links;
		this.category = category;
	}

	public boolean createTemplateFromStart(String eventDesc, String purchasingGrp, boolean isPublicEvent) {
		boolean result = false;
		try {
			createNewRound(eventDesc, purchasingGrp, isPublicEvent);
			selectQuesItems();
			selectTCs();
			selectSupplierResponses();
			selectScoreSheets();
			//GOES TO DRAFT
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean createTemplateFromTemplate(String eventDesc, String purchasingGrp, boolean isPublicEvent, String searchTemplateBy, String searchTemplateName) {
		boolean result = false;
		try {
			findElement(By.xpath("//ul[@id='leftTabs']/li[@title='From Template']/span/span[2]")).click();
			createNewRound(eventDesc, purchasingGrp, isPublicEvent);
			selectTemplate(searchTemplateBy, searchTemplateName);
			//GOES TO DRAFT
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean selectTemplate(String searchBy, String searchValue){
		boolean result = false;
		String displayedEventType = null;
		try{
			
			switch(searchBy){
			case "Template Name":
				displayedEventType = "";
				break;
			case "Test Event":
				displayedEventType = "Test";
				break;
			case "Public Event":
				displayedEventType = "Public";
				break;
			case "Test Event and Public Event":
				displayedEventType = "Public & Test";
				break;
			}
			if(searchTemplate(searchBy, searchValue))
				findElement(By.xpath("//table[@id='browseEventGrid']/tbody//tr[td[label[contains(text(),'"+searchValue+"')] and position()='2'] and td[contains(text(),'"+displayedEventType+"') and position()='5']]/td[1]")).click();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean searchTemplate(String searchBy, String searchValue){
		boolean result = false;
		try{
			findElement(By.xpath("//select[@id='searchColumn']/option[text()='"+searchBy+"']")).click();
			findElement(By.id("searchText")).sendKeys(searchValue);
			findElement(By.id("go")).click();
			waitUntilVisibilityOfElement(By.xpath("//div[@class='zys-loader']"));
			List<WebElement> dispTemplateRows = driver.findElements(By.xpath("//table[@id='browseEventGrid']/tbody//tr"));
			int iCtr = 0;
			for(WebElement templateRow : dispTemplateRows){
				iCtr++;
				String displayedTemplateName = templateRow.findElement(By.xpath("//td[2]/label")).getText();
				if(!displayedTemplateName.equals(searchBy)){
					logger.log(LogStatus.INFO, "Expected: "+searchValue+" Actual:"+displayedTemplateName);
					break;
				}
			}
			if(iCtr==dispTemplateRows.size()){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean selectTemplate(String searchValue){
		boolean result = false;
		try{
			selectTemplate("Template Name", searchValue);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean selectDocument(String searchBy, String searchValue){
		boolean result = false;
		String displayedEventType = null;
		try{
			
			switch(searchBy){
			case "Document Name":
				displayedEventType = "";
				break;
			case "Test Event":
				displayedEventType = "Test";
				break;
			case "Public Event":
				displayedEventType = "Public";
				break;
			case "Test Event and Public Event":
				displayedEventType = "Public & Test";
				break;
			}
			if(searchTemplate(searchBy, searchValue))
				findElement(By.xpath("//table[@id='browseEventGrid']/tbody//tr[td[label[contains(text(),'"+searchValue+"')] and position()='2'] and td[contains(text(),'"+displayedEventType+"') and position()='5']]/td[1]")).click();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean copyTemplateFromExisting(String eventDesc, String purchasingGrp, boolean isPublicEvent, String searchDocBy, String searchDocName) {
		boolean result = false;
		try {
			findElement(By.xpath("//ul[@id='leftTabs']/li[@title='Copy From Existing']/span/span[2]")).click();
			createNewRound(eventDesc, purchasingGrp, isPublicEvent);
			selectDocument(searchDocBy, searchDocName);
			//GOES TO DRAFT
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void createNewRound(String eventDesc, String purchasingGrp, boolean isPublicEvent) {
		try {
			findElement(By.xpath("//input[@name='EVENT_NAME']")).sendKeys(eventTitle);
			findElement(By.xpath("//textarea[@name='EVENT_DESC']")).sendKeys(eventDesc);
			selectEventType(eventType, isPublicEvent);
			findElement(By.xpath(
					"//tr[td/label[@title='Is this a test event']]//select/option[@title='" + isTestEvent + "']"))
							.click();
			findElement(By.xpath("//tr[td/label[@title='Links']]//select/option[@title='" + isTestEvent + "']"))
					.click();
			findElement(By.xpath("//tr[td/label[@title='Department']]//select/option[@title='" + dept + "']")).click();
			findElement(By.xpath("//tr[td/label[@title='category']]//select/option[@title='" + category + "']"))
					.click();
			findElement(By
					.xpath("//tr[td/label[@title='Purchasing group']]//select/option[@title='" + purchasingGrp + "']"))
							.click();
			findElement(By.xpath("//input[@value='Save and Continue']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectEventType(String eventType, boolean isPublicEvent) {
		try {
			findElement(By.xpath("//label[text()[contains(.,'" + eventType + "')]]/input")).click();
			if (isPublicEvent)
				findElement(By.id("isPublicEvent")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void selectQuesItems() {
		try {
			selectAllLinks();
			findElement(By.xpath("//div[@id='questionDiv']//input[@value='Next']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void selectTCs() {
		try {
			selectAllLinks();
			findElement(By.xpath("//div[@id='tncMRDiv']//input[@value='Next']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectTCs(String attachmentName) {
		try {
			findElement(By.xpath("//div[@id='vendorDiv']//tr[@class='rowData'][td[2]/span[contains(text(),'"+attachmentName+"')]]/td[1]/input")).click();
			findElement(By.xpath("//div[@id='tncMRDiv']//input[@value='Next']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectSupplierResponses(){
		try {
			selectAllLinks();
			findElement(By.xpath("//div[@id='vendorDiv']//input[@value='Next']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectSupplierResponses(String supplier){
		try {
			findElement(By.xpath("//div[@id='vendorDiv']//tr[@class='rowData'][td[2]/span[contains(text(),'"+supplier+"')]]/td[1]/input")).click();
			findElement(By.xpath("//div[@id='vendorDiv']//input[@value='Next']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectScoreSheets(){
		try {
			selectAllLinks();
			findElement(By.xpath("//div[@id='scoreSheetDiv']//input[@value='Finish']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectScoreSheets(String scoreSheetName){
		try {
			findElement(By.xpath("//div[@id='scoreSheetDiv']//tr[@class='rowData'][td[2]/span[contains(text(),'"+scoreSheetName+"')]]/td[1]/input")).click();
			findElement(By.xpath("//div[@id='scoreSheetDiv']//input[@value='Finish']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectAllLinks() {
		List<WebElement> selectAllLink = driver
				.findElements(By.xpath("//div[@class='listingTopLinks']//a[contains(text(),'All')]"));
		for (WebElement link : selectAllLink) {
			link.click();
		}
	}

}
