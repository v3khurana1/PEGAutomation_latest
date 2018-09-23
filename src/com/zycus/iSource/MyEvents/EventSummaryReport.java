package com.zycus.iSource.MyEvents;

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

public class EventSummaryReport extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	private By reportHeader = By.xpath("//label[@class='iconsR title']/strong[contains(text(),'Event Summary Report')]");
	private By eventAuditTrailTitle = By.xpath("//h3[@class='evtAudTrailTitle']");
	private By backBtn = By.xpath("//div[@id='topbar-inner']//a[contains(text(),'Back')]");
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public EventSummaryReport(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public boolean filterByEventHistory(String startDt, String endDt){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@id='frmDatePicker']/following-sibling::img")).click();
			selectDate_v1(startDt);
			findElement(By.xpath("//input[@id='frmDatePicker']/following-sibling::img")).click();
			selectDate_v1(endDt);
			findElement(By.id("activityFilterId")).click();
			//TODO : verify if it is getting filtered or not
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the reportHeader
	 */
	public By getReportHeader() {
		return reportHeader;
	}

	/**
	 * @param reportHeader the reportHeader to set
	 */
	public void setReportHeader(By reportHeader) {
		this.reportHeader = reportHeader;
	}

	/**
	 * @return the eventAuditTrailTitle
	 */
	public By getEventAuditTrailTitle() {
		return eventAuditTrailTitle;
	}

	/**
	 * @param eventAuditTrailTitle the eventAuditTrailTitle to set
	 */
	public void setEventAuditTrailTitle(By eventAuditTrailTitle) {
		this.eventAuditTrailTitle = eventAuditTrailTitle;
	}

}
