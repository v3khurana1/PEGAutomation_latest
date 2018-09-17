package com.zycus.IContract.ManageContracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class Signers extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader =By.xpath("//div[contains(@id,'Processing')]");
	private By filterBtnXpath = By.xpath(
			"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]//a[text()='Filter'][ancestor::div[@aria-hidden='false']]");
	private By contractSrcXpath = By.xpath("//table[@id='authorContractListing-grid']/tbody//td[4]/span");
	private By statusXpath = By.xpath("//table[@id='authorContractListing-grid']/tbody//td[6]/span");
	private By stageXpath = By.xpath("//table[@id='authorContractListing-grid']/tbody//td[8]//li[@class='inprogress']/span");
	
	public Signers(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean sendForSigning(String signingOption) {
		boolean result = false;
		try {
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'Overlay')]"));
			Thread.sleep(8000);
			if(signingOption.equals("e-Signature"))
				findElement(By.xpath("//input[@name='esigningStatus' and @value='true']")).click();
			else if(signingOption.equals("Offline Signing"))
				findElement(By.xpath("//input[@name='esigningStatus' and @value='false']")).click();
			String popUpFinderXpath = "//div[contains(@class,'zys-popup-container')][div/h1[text()='Confirm']]";
			//if(findElement(By.xpath(popUpFinderXpath)).isDisplayed())
			 try{
				findElement(By.xpath(popUpFinderXpath + "//button[text()='Yes']")).click();
			 }catch(Exception e){}
			waitUntilInvisibilityOfElement(processingLoader);
			waitUntilInvisibilityOfElement(By.xpath("//div[@class='jqifade']"));
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'blockOverlay')]"));
			clickAndWaitUntilLoaderDisappears(By.id("sendForSigningBtn"));
			Thread.sleep(3000);
			/*if (driver
					.findElements(By
							.xpath("//div[contains(@aria-describedby,'zydev-popup')][div/span[text()='Contract Document List']]"))
					.size() > 0)*/
			try{
				findElement(By
						.xpath("//div[@id='zydev-popup']/following-sibling::div//button[span[text()='Proceed']]"))
								.click();
			}catch(Exception e){}
			waitUntilInvisibilityOfElement(processingLoader);
			try{
			//if(findElement(By.xpath(popUpFinderXpath)).isDisplayed())
				findElement(By.xpath(popUpFinderXpath + "//button[text()='Yes']")).click();
			}catch(Exception e){}
			waitUntilInvisibilityOfElement(processingLoader);
			if (driver.findElements(By.id("emailPopup")).size() > 0) {
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='zydev-popup']/following-sibling::div//button[span[text()='Send']]"));
				if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Signing In progress")){
					logger.log(LogStatus.INFO, "contract sent to contracting party for signing");
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
