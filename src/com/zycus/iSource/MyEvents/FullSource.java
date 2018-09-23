package com.zycus.iSource.MyEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.iSource_CommonFunctions;

public class FullSource extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public FullSource(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	

	public boolean filterByEventID(String eventID) {
		boolean result = false;
		try {
			result = filterByText("Event ID", eventID)? true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByEventTitle(String eventTitle) {
		boolean result = false;
		try {
			result = filterByText("Event Title", eventTitle)? true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByOwner(String owner) {
		boolean result = false;
		try {
			result = filterByText("Owner", owner)? true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//TODO : Incomplete functions
	
	/*public boolean filterByType(String type) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@id,'type_filterColumn')]//label")).click();
			result = filterByChkbox(type, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByStatus(String status) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@id,'status_filterColumn')]//label")).click();
			result = filterByChkbox(status, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByStages(String stages) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@id,'stage_filterColumn')]//label")).click();
			result = filterByChkbox(stages, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean takeAction(String action){
		
		boolean result = false;
		String eventID = null;
		String eventTitle = null;
		String type =  null;
		String reportAuditTitle = null;
		
		try{
			eventID = findElement(By.xpath("//table[@id='homePageGrid']/tbody/tr[1]/td[1]")).getText();
			eventTitle = findElement(By.xpath("//table[@id='homePageGrid']/tbody/tr[1]/td[2]//div")).getText();
			type = findElement(By.xpath("//table[@id='homePageGrid']/tbody/tr[1]/td[3]")).getText();
			reportAuditTitle = type +" "+"-"+" "+eventID+"|"+eventTitle;
			findElement(By.xpath("//table[@id='homePageGrid']/tbody/tr[1]/td[last()]//a[text()='Actions']")).click();
			findElement(By.xpath("//table[@id='homePageGrid']/tbody/tr[1]/td[last()]//span[text()]")).click();
			
			switch(action){
			
			case "Event Summary Report":
				EventSummaryReport objSummary = new EventSummaryReport(driver, logger);
				if(findElement(objSummary.getReportHeader()).isDisplayed()){
					String reportTitleDisplayed = findElement(objSummary.getEventAuditTrailTitle()).getText();
					if(reportTitleDisplayed.equals(reportAuditTitle))
						logger.log(LogStatus.INFO, reportAuditTitle +" "+ action + " opened");
					else
						logger.log(LogStatus.INFO, reportTitleDisplayed + " displayed, instead of  "+ reportAuditTitle);
				}else
					logger.log(LogStatus.INFO, findElement(objSummary.getReportHeader()).getText() +" displayed");
				break;
			
			case "Supplier Status Report":
				try{
					
				}catch(Exception e){
					findElement(By.xpath("//div[@id='zys-popup-msg'][contains(text(),'not available in document stage')]/..//input[@value='OK']")).click();
				}
				break;
			
			case "Add Another Round":
				try{
					
				}catch(Exception e){
					findElement(By.xpath("//div[@id='zys-popup-msg'][contains(text(),'Cannot add new round until current round is in Analyze stage')]/..//input[@value='OK']")).click();
				}
				break;
			
			case "Delete Event":
				break;
			
			case "Edit Event Details":
				break;
			
			case "Share Event":
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean createNewEvent(){
		boolean result = false;
		try{
			findElement(By.xpath("//div[@id='createEvent']/input")).click();
			if(driver.findElement(By.id("eventSelectionPopup")).isDisplayed())
				findElement(By.xpath("//div[@id='eventSelectionPopup']//a[text()='Create Full Source Event']")).click();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
