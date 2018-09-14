package com.zycus.eInvoice.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class PurchaseOrders extends eInvoice_CommonFunctions{

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader = By.id("reconciliationList_processing");
	private By pgHead = By.xpath("//h1[@class='pgHead' and text()='Purchase Orders']");
	private By alertBoxmsg = By.xpath("//div[@id='messageContainer']//span");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public PurchaseOrders(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * @return the processingLoader
	 */
	public By getProcessingLoader() {
		return processingLoader;
	}

	/**
	 * @param processingLoader the processingLoader to set
	 */
	public void setProcessingLoader(By processingLoader) {
		this.processingLoader = processingLoader;
	}

	/**
	 * @return the alertBoxmsg
	 */
	public By getAlertBoxmsg() {
		return alertBoxmsg;
	}

	/**
	 * @param alertBoxmsg the alertBoxmsg to set
	 */
	public void setAlertBoxmsg(By alertBoxmsg) {
		this.alertBoxmsg = alertBoxmsg;
	}
	
	
}
