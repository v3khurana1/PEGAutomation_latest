package com.zycus.iSupplier.ManageSuppliers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Reports.Common_Reports;

public class CreateSupplier extends Common_Reports {

	private WebDriver driver;
	//private ExtentTest logger;
	private String supplierName;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public CreateSupplier(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}
	
	public CreateSupplier(WebDriver driver, ExtentTest logger, String supplierName) {
		super(driver, logger);
		this.driver = driver;
		this.supplierName = supplierName;
	}
	

	public boolean createNewSupplier(String taxIDformat, String taxID, String country, String state, String city, String postalCode, String...address) throws Exception {
		boolean result = false;
		try {
			driver.findElement(By.id("suppliername")).sendKeys(supplierName);
			findElement(By.xpath("//select[@id='TAX_ID_FORMAT']/option[text()='"+taxIDformat+"']")).click();
			driver.findElement(By.id("taxid")).sendKeys(taxID);
			int i = 0;
			for(String addr:address){
				i++;
				driver.findElement(By.id("address"+String.valueOf(i))).sendKeys(addr);
			}
			findElement(By.xpath("//select[@id='country']/option[@title='"+country+"']")).click();
			findElement(By.xpath("//select[@id='state']/option[@title='"+state+"']")).click();
			driver.findElement(By.id("city")).sendKeys(city);
			driver.findElement(By.id("postal")).sendKeys(postalCode);
			findElement(By.id("createSupp")).click();
			waitUntilInvisibilityOfElement(By.id("globalLoadingIcon"));
			waitUntilVisibilityOfElement(By.id("frmCheckDuplicate"));
			findElement(By.id("btnCreateSupplier")).click();
			waitUntilInvisibilityOfElement(By.id("loadingIcon"));
			
				
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

}
