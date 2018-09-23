package com.zycus.iSource.MyEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.iSource_CommonFunctions;

public class ItemSettings extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public ItemSettings(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public boolean setItemSettings(int noOfDefaultItems, String...settings){
		boolean result = false;
		try{
			for(String itemSetting : settings){
				findElement(By.xpath("//div[@class='itemSettings']//li[label[@for and contains(text(),'"+itemSetting+"')]]/input")).click();
			}
			driver.findElement(By.id("noOfDefaultItem")).sendKeys(String.valueOf(noOfDefaultItems));
			setItemSettings();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean setItemSettings(){
		boolean result = false;
		try{
			findElement(By.xpath("//div[@id='popup_apply']/input[@title='Apply']")).click();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
