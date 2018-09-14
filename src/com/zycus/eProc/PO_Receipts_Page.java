package com.zycus.eProc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class PO_Receipts_Page extends eProc_CommonFunctions{
	private WebDriver driver;
	private ExtentTest logger;
	
	private By createReceiptXpath 		= By.xpath("//a[@class='scLnk createReceipt']");
	private By createReturnNoteId 		= By.id("createReturnNote");
	
	public PO_Receipts_Page(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	//td[contains(text(),'accepted quantity > 0')]/ancestor::div[contains(@class,'iAlertBox')]
	//div[contains(@class,'iAlertBox')]/div[3]//span
	
	public boolean createReceipt() throws Exception{
		findElement(createReceiptXpath).click();
		//Validate Create Receipts page opened
		return true;	
	}
	
	public boolean createReturnNote() throws Exception{
		findElement(createReturnNoteId).click();
		return true;
	}
	 
}
