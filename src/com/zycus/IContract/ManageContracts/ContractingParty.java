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

public class ContractingParty extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader = By.xpath("//span[@class='zys-loader-icon']");
	private By pgHead = By.xpath("//span[@id='headerLabel']/following-sibling::h2[text()='Contracting Party']");

	public ContractingParty(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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

	/*public boolean addContractingParty(String contractingParty, String contactPerson) {
		boolean result = false;
		try {
			waitUntilInvisibilityOfElement(By.xpath("//div[@class='blockUI blockOverlay']"));
			clickAndWaitUntilElementAppears(By.id("addVendor"), By.id("vendor-grid"));
			findElement(By.id("addVendor")).click();
			waitUntilVisibilityOfElement(By.id("vendor-grid"));
			driver.findElement(By.id("vendorSearchValue")).sendKeys(contractingParty);
			clickAndWaitUntilLoaderDisappears(By.id("goButton"));
			findElement(By.id("goButton")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			findElement(By.xpath("//table[@id='vendor-grid']/tbody//span[contains(text(),'" + contractingParty
					+ "')]/ancestor::tr/td[1]")).click();
			findElement(By.xpath("//table[@id='vendor-grid']/tbody//span/ancestor::tr/td[4]//span[contains(text(),'"
					+ contactPerson + "')]/input")).click();
			String contractPartyFullNm = findElement(
					By.xpath("//table[@id='vendor-grid']/tbody//span[contains(text(),'" + contractingParty + "')]"))
							.getText();
			clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='contractingPartyButtons']/input"));
			findElement(By.xpath("//div[@id='contractingPartyButtons']/input")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			String contractingPartyTitle = findElement(By.xpath("//div[@id='contractingPartyDiv']/div/span")).getText();
			if (contractingPartyTitle.equals(contractPartyFullNm)){
				LogScreenshot("INFO", "Contract Party added");
				result = true;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	
	public boolean addContractingParty(String contractingParty, String contactPerson) {
		boolean result = false;
		try {
			waitUntilInvisibilityOfElement(By.xpath("//div[@class='blockUI blockOverlay']"));
			clickAndWaitUntilElementAppears(By.id("addVendor"), By.id("vendor-grid"));
			driver.findElement(By.id("vendorSearchValue")).sendKeys(contractingParty);
			clickAndWaitUntilLoaderDisappears(By.id("goButton"), processingLoader);
			findElement(By.xpath("//table[@id='vendor-grid']/tbody//span[contains(text(),'" + contractingParty
					+ "')]/ancestor::tr/td[1]")).click();
			findElement(By.xpath("//table[@id='vendor-grid']/tbody//span/ancestor::tr/td[4]//span[contains(text(),'"
					+ contactPerson + "')]/input")).click();
			String contractPartyFullNm = findElement(
					By.xpath("//table[@id='vendor-grid']/tbody//span[contains(text(),'" + contractingParty + "')]"))
							.getText();
			clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='contractingPartyButtons']/input"), processingLoader);
			String contractingPartyTitle = findElement(By.xpath("//div[@id='contractingPartyDiv']/div/span")).getText();
			if (contractingPartyTitle.equals(contractPartyFullNm)){
				LogScreenshot("INFO", "Contract Party added");
				result = true;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*public boolean addContractingParty() {
		boolean result = false;
		try {
			findElement(By.id("addVendor")).click();
			waitUntilVisibilityOfElement(By.id("vendor-grid"));
			clickAndWaitUntilElementAppears(By.id("addVendor"), By.id("vendor-grid"));
			String contractPartyFullNm = findElement(By.xpath("(//table[@id='vendor-grid']/tbody//span)[1]")).getText();
			clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='contractingPartyButtons']/input"));
			findElement(By.xpath("//div[@id='contractingPartyButtons']/input")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			String contractingPartyTitle = findElement(By.xpath("//div[@id='contractingPartyDiv']/div/span")).getText();
			if (contractingPartyTitle.equals(contractPartyFullNm)){
				LogScreenshot("INFO", "Contract Party added");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean addContractingParty() {
		boolean result = false;
		try {
			clickAndWaitUntilElementAppears(By.id("addVendor"), By.id("vendor-grid"));
			String contractPartyFullNm = findElement(By.xpath("(//table[@id='vendor-grid']/tbody//span)[1]")).getText();
			clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='contractingPartyButtons']/input"), processingLoader);
			String contractingPartyTitle = findElement(By.xpath("//div[@id='contractingPartyDiv']/div/span")).getText();
			if (contractingPartyTitle.equals(contractPartyFullNm)){
				LogScreenshot("INFO", "Contract Party added");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
