package com.zycus.IContract.Performance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

public class SpendByContracts extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;
	
	public SpendByContracts(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	public boolean searchContracts(String searchBy, String searchValue) {
		boolean result = false;
		try {
			findElement(By.xpath("//select[@name='searchSelect']/option[text()='"+searchBy+"']")).click();
			findElement(By.id("searchValue")).sendKeys(searchValue);
			result = clickAndWaitUntilElementAppears(By.xpath("//div[@id='search-bar']//input[@value='Go']"), By.id("gridMessageDiv"))?true:false;
			/*findElement(By.xpath("//div[@id='search-bar']//input[@value='Go']")).click();
			if(driver.findElement(By.id("gridMessageDiv")).isDisplayed())
			*/	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
