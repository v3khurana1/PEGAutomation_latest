package com.zycus.iSource.MyEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.iSource_CommonFunctions;

public class SupplierContacts extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String supplierCompany;
	private String supplierContact;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public SupplierContacts(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public SupplierContacts(WebDriver driver, ExtentTest logger, String supplierCompany) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.supplierCompany = supplierCompany;
	}
	
	public SupplierContacts(WebDriver driver, ExtentTest logger, String supplierCompany, String supplierContact) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.supplierCompany = supplierCompany;
		this.supplierContact = supplierContact;
	}
	
	
	public boolean filterBySupplierCompanyName(){
		boolean result = false;
		try{
			result = filterByText("Supplier Company Name", supplierCompany);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterBySupplierContactName(){
		boolean result = false;
		try{
			result = filterByText("Supplier Contact Name", supplierContact);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	

}
