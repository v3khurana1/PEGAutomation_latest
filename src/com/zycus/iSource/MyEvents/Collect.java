package com.zycus.iSource.MyEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.iSource_CommonFunctions;

public class Collect extends iSource_CommonFunctions {

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

	public Collect(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean manageSuppliers(String closingDt, String closingTime, String approvalComments) {
		boolean result = false;
		try {
			selectSuppliers();
			waitUntilInvisibilityOfElement(By.id("LoaderForDiv"));
			// TODO: Verify if supplier added to the table
			findElement(By.xpath("//table[@id='collect-grid']/tbody/tr[1]/td[1]/input")).click();
			if(scheduleEvent(closingDt,closingTime)){
				findElement(By.xpath("//input[@value='Send For Approval']")).click();
				driver.findElement(By.id("popupCommentInst")).sendKeys(approvalComments);
				findElement(By.xpath("//div[@aria-describedby='wfInstancePopUp']//button[span[text()='Done']]")).click();
				waitUntilInvisibilityOfElement(By.xpath("//div[@class='blockUI blockMsg blockPage']"));
				waitUntilInvisibilityOfElement(By.id("loadingMessageGlobally1"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void selectSuppliers() {
		try {
			findElement(By.xpath("//input[@auto_att='selectVendors']")).click();
			SupplierContacts objSuppContacts = new SupplierContacts(driver, logger, supplierCompany, supplierContact);
			objSuppContacts.filterBySupplierCompanyName();
			objSuppContacts.filterBySupplierContactName();
			findElement(By.xpath("//table[@id='selectSIMSupplierContactListTable']/tbody/tr[1]/td[1]/input")).click();
			findElement(By.xpath("//div[@class='stickyButtonBar']//input[@value='Add']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean scheduleEvent(String closingDt, String closingTime){
		boolean result = false;
		try{
			findElement(By.xpath("//div[@class='leftMenuDiv']//li//span[@class='leftMenuTxt' and text()='Schedule Event']")).click();
			ScheduleEvent objEvent = new ScheduleEvent(driver, logger, closingDt, closingTime);
			objEvent.setDateTime();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
