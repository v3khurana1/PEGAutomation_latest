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

public class CompanyList extends iSource_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public CompanyList(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}
	
	

	public boolean filterBySupplierCompName(String companyName) {
		boolean result = false;
		try {
			result = filterByText("Supplier Company Name", companyName)? true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterBySupplierContactName(String contactName) {
		boolean result = false;
		try {
			result = filterByText("Supplier Contact Name", contactName)? true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	//TODO : Incomplete functions
	
	/*
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
	
	*/
	
	public boolean takeAction(String action){
		
		boolean result = false;
		String eventID = null;
		String eventTitle = null;
		String type =  null;
		String reportAuditTitle = null;
		
		try{
			eventID = findElement(By.xpath("//table[@id='supplierCustomListTable']/tbody/tr[1]/td[1]")).getText();
			eventTitle = findElement(By.xpath("//table[@id='supplierCustomListTable']/tbody/tr[1]/td[2]//div")).getText();
			type = findElement(By.xpath("//table[@id='supplierCustomListTable']/tbody/tr[1]/td[3]")).getText();
			reportAuditTitle = type +" "+"-"+" "+eventID+"|"+eventTitle;
			findElement(By.xpath("//table[@id='supplierCustomListTable']/tbody/tr[1]/td[last()]//a[text()='Actions']")).click();
			findElement(By.xpath("//table[@id='supplierCustomListTable']/tbody/tr[1]/td[last()]//span[text()]")).click();
			
			switch(action){
			
			case "View":
				
			
			case "Edit":
				
				break;
			
			case "Initiate Evaluation":
				
				break;
			
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean createSupplier(){
		boolean result = false;
		try{
			findElement(By.xpath("//label[@title='Create Supplier']")).click();
			/*if(driver.findElement(By.id("eventSelectionPopup")).isDisplayed())
				findElement(By.xpath("//div[@id='eventSelectionPopup']//a[text()='Create Full Source Event']")).click();*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
