package com.zycus.eProc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class ItemMaster extends eProc_CommonFunctions {

	private WebDriver driver;
	private String itemMasterName;
	private ExtentTest logger;
	
	public ItemMaster(WebDriver driver, ExtentTest logger, String itemMasterName) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.itemMasterName = itemMasterName;
	}
	
	public boolean createItemMaster(String itemMasterType, String desc) throws Exception{
		sendKeys(By.id("txtItemMasterName"), itemMasterName);
		if(itemMasterType == "Stock")
			findElement(By.id("itemMasterType_inventory")).click();
		else if(itemMasterType == "Non-Stock")			
			findElement(By.id("itemMasterType_nonInventory")).click();
		sendKeys(By.id("txtDescription"), desc);
		
		//Select Organizational Units
		findElement(By.id("linkScopeSelected")).click();
		if(findElement(By.xpath("//div[@id='diagBoxScopeDefConfig']/parent::div"))!=null){
			
		}
		findElement(By.id("btnLnkNext")).click();
		return false;
	}

}
