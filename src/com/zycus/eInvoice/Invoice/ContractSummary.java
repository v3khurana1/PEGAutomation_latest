package com.zycus.eInvoice.Invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class ContractSummary extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	private String billingCompany;
	//private String billingBusinessUnit;
	//private String billingLocation;
	//private String CBCostCenter;
	//private String CBProject;
	//private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public ContractSummary(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}
	
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param billingCompany
	 * @param billingBusinessUnit
	 * @param billingLocation
	 * @param CBCostCenter
	 * @param CBProject
	 * 
	 */
	
	/*public ContractSummary(WebDriver driver, ExtentTest logger, String billingCompany, String billingBusinessUnit, String billingLocation,
			String CBCostCenter, String CBProject) {
		super(driver, logger);
		//this.driver = driver;
		this.logger = logger;
		this.billingCompany = billingCompany;
		this.billingBusinessUnit = billingBusinessUnit;
		this.billingLocation = billingLocation;
		this.CBCostCenter = CBCostCenter;
		this.CBProject = CBProject;
	}*/

	/**
	 * <b>Function:</b> enterBillingInfo
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @return result - True/False
	 * @throws None
	 */

	public boolean enterBillingInfo(){
		boolean result = false;
		try {
			findElement(By.id("companyAutoComplete")).clear();
			findElement(By.id("companyAutoComplete")).sendKeys(billingCompany);
			findElement(By.id("buAutoComplete")).sendKeys(billingCompany);
			findElement(By.id("locationAutoComplete")).sendKeys(billingCompany);
			findElement(By.xpath("//div[@id='changeInvSummaryTabs']//a[contains(text(),'Cost Booking')]")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
