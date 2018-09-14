package com.zycus.IContract.ManageContracts;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class SelectContractType extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//div[@id='main-action-button']/h3[text()='Author Contract']");
	private String contractType;
	private String contractSubType;

	public SelectContractType(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public SelectContractType(WebDriver driver, ExtentTest logger, String contractType, String contractSubType) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.contractType = contractType;
		this.contractSubType = contractSubType;
	}
	
	public boolean selectContractTypes(ContractDetails objDetails) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//ul[@id='subTypeSelector']//a[contains(text(),'"+contractType+"')]")).click();
			findElement(By.xpath("//div[@id='"+contractType.toUpperCase()+"' and contains(@style,'block')]//input[@type='radio'][following-sibling::label[contains(text(),'"+contractSubType+"')]]")).click();
			findElement(By.xpath("//input[@value='Continue']")).click();
			//ContractDetails objDetails =  new ContractDetails(driver, logger);
			if (objDetails.getPgHead() != null){
				logger.log(LogStatus.INFO, "navigated to 'Contract Details' page");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.INFO, "Not navigated to 'Contract Details' page");
			throw new Exception();
		}
		return result;
	}
	
	/**
	 * @return the pgHead
	 */
	public By getPgHead() {
		return pgHead;
	}

	/**
	 * @param pgHead
	 *            the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}

}
