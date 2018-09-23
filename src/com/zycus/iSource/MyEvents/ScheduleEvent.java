package com.zycus.iSource.MyEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.iSource_CommonFunctions;

public class ScheduleEvent extends iSource_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;
	private String closingDt;
	private String closingTime;
	private String startDt;
	private String startTime;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public ScheduleEvent(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}
	
	public ScheduleEvent(WebDriver driver, ExtentTest logger, String startDt, String startTime, String closingDt, String closingTime) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
		this.startDt = startDt;
		this.startTime = startTime;
		this.closingDt = closingDt;
		this.closingTime = closingTime;
	}
	
	public ScheduleEvent(WebDriver driver, ExtentTest logger, String closingDt, String closingTime) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
		this.closingDt = closingDt;
		this.closingTime = closingTime;
	}

	public boolean setDateTime() {
		boolean result = false;
		try {
			//Set Start Date & Time
			if(!startDt.equals(""))
				setDateTime("open", startDt, startTime);
			//Set End Date & Time
			setDateTime("close", closingDt, closingTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	private void setDateTime(String openOrCloseDtTime, String dateToSelect, String timeToSelect) {
		try {
			//Set Date & Time
			findElement(By.xpath("//input[@id='nonPricingSetting_'"+openOrCloseDtTime+"'DateTime_dateInString']/img")).click();
			selectDate_v1(dateToSelect);
			findElement(By.xpath(
					"//select[@id='nonPricingSetting_'"+openOrCloseDtTime+"'DateTime_hours']/option[@value='" + String.valueOf(getHrs(timeToSelect)) + "']"))
							.click();
			findElement(By.xpath(
					"//select[@id='nonPricingSetting_'"+openOrCloseDtTime+"'DateTime_mins']/option[@value='" + String.valueOf(getMins(timeToSelect)) + "']"))
							.click();
			findElement(By.xpath(
					"//select[@id='nonPricingSetting_'"+openOrCloseDtTime+"'DateTime_ampm']/option[@value='" + getAmPm(timeToSelect) + "']"))
							.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getHrs(String formattedTime){
		return Integer.parseInt(formattedTime.split(":")[0]);
	}
	
	private int getMins(String formattedTime){
		return Integer.parseInt(formattedTime.split(":")[1]);
	}
	
	private String getAmPm(String formattedTime){
		return formattedTime.split(":")[2];
	}

}
