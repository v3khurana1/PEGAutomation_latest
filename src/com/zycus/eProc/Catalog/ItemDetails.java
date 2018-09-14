package com.zycus.eProc.Catalog;

import org.apache.commons.net.ftp.parser.EnterpriseUnixFTPEntryParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> NewCatalog.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.enterCatalogDetails: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class ItemDetails extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By itemDetailsLbl = By.xpath("//div[@aria-describedby='addItemDialog']//span[text()='Item Details']");
	private String catalogName;
	private String supplier;
	
	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);
	private String serviceNum;
	private String shortDesc;
	private String prodCategory;
	private String price;
	private String currency; 

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public ItemDetails(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public ItemDetails(WebDriver driver, ExtentTest logger, String serviceNum, String shortDesc, String prodCategory, String price) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.serviceNum = serviceNum;
		this.shortDesc = shortDesc;
		this.prodCategory = prodCategory;
		this.price = price;
	}


	/**
	 * <b>Function:</b> enterItemDetails
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return None
	 */

	public boolean enterItemDetails() {
		boolean result = false;
		try {
			findElement(By.id("txtItemNumber")).sendKeys(serviceNum);
			findElement(By.id("txtItemname")).sendKeys(shortDesc);
			objFunctions.enterText_AutoComplete(By.id("txtCatName"), prodCategory);
			findElement(By.id("txtPrice")).sendKeys(price);
			findElement(By.id("addItem")).click();
			String addedServiceNum = findElement(By.xpath("//table[@id='itemListGrid']/tbody/tr/td[contains(@class,'supplierPartId')]")).getText();
			if(addedServiceNum.equals(serviceNum))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
