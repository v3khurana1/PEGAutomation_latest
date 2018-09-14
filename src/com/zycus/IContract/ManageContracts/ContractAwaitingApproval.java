package com.zycus.IContract.ManageContracts;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Beta;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class ContractAwaitingApproval extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//div[contains(text(),'Contracts/Amendments Awaiting Approval')]");
	private String contractType;
	private String contractSubType;

	public ContractAwaitingApproval(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public ContractAwaitingApproval(WebDriver driver, ExtentTest logger, String contractType, String contractSubType) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.contractType = contractType;
		this.contractSubType = contractSubType;
	}
	
	
	@Beta
	public boolean approveContract(String stage, String contractNo, String action) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@id='level2tabs']//li[text()='"+stage+"']")).click();
			//Click on Actions link
			findElement(By.xpath("//table[@class='zytbl newFilterTableGrid']/tbody/tr[td[1]/span[text()='"+contractNo+"']]/td[last()]//a")).click();
			findElement(By.xpath("//ul[@class='actBxAll']/li/span[text()='"+action+"']")).click();
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
