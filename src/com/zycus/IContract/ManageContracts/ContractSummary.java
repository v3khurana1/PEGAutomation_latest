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

public class ContractSummary extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader = By.xpath("//span[@class='zys-loader-icon']");
	private By pgHead = By.xpath("//span[@id='headerLabel']/following-sibling::h2[text()='Contract Summary']");

	public ContractSummary(WebDriver driver, ExtentTest logger) {
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

	public String getContractNum() {
		String contractNum = null;
		try {
			contractNum = findElement(By.xpath("(//div[@id='summarypage']//h2)[1]")).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contractNum;
	}

	/*public boolean sendForNegotiation(String negotiationComment) {
		boolean result = false;
		try {
			findElement(By.id("sendToNegotiate")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			findElement(By.id("revisionComment")).sendKeys(negotiationComment);
			findElement(By.xpath("//div[@id='revisionCommentPopup']/ancestor::div//button[span[text()='Ok']]")).click();
			waitUntilVisibilityOfElement(processingLoader);
			waitUntilVisibilityOfElement(pgHead);
			if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Pending External Review")){
				LogScreenshot("INFO", "contract sent for negotiation");
				result = true;
			}else
				LogScreenshot("INFO", "contract not sent for negotiation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean sendForNegotiation(String negotiationComment) {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.id("sendToNegotiate"), processingLoader);
			findElement(By.id("revisionComment")).sendKeys(negotiationComment);
			findElement(By.xpath("//div[@id='revisionCommentPopup']/ancestor::div//button[span[text()='Ok']]")).click();
			waitUntilVisibilityOfElement(processingLoader);
			waitUntilVisibilityOfElement(pgHead);
			if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Pending External Review")){
				LogScreenshot("INFO", "contract sent for negotiation");
				result = true;
			}else
				LogScreenshot("INFO", "contract not sent for negotiation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*public boolean closeContract() {
		boolean result = false;
		try {
			findElement(By.id("closeAuthContract")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			AuthorContract objAuth = new AuthorContract(driver, logger);
			if (objAuth.getPgHead() != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	*/
	public boolean closeContract(AuthorContract objAuth) {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.id("closeAuthContract"), processingLoader);
			if (objAuth.getPgHead() != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*public boolean sendToContractingParty() {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.id("sendToContractingParty"));
			findElement(By.id("sendToContractingParty")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if (driver
					.findElements(By
							.xpath("//div[@aria-describedby='confirmationPopup'][div/span[text()='Redlines and Comments']]"))
					.size() > 0)
			findElement(By
						.xpath("//div[@id='confirmationPopup']/following-sibling::div//button[span[text()='Proceed']]"))
								.click();
			waitUntilInvisibilityOfElement(processingLoader);
			clickAndWaitUntilLoaderDisappears(By
					.xpath("//div[@id='confirmationPopup']/following-sibling::div//button[span[text()='Proceed']]"));
			if (driver.findElements(By.id("emailPopup")).size() > 0) {
				LogScreenshot("INFO", "sending contract to contracting party");
				findElement(By.xpath("//div[@id='emailPopup']/following-sibling::div//button[span[text()='Send']]"))
						.click();
				waitUntilInvisibilityOfElement(processingLoader);
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='emailPopup']/following-sibling::div//button[span[text()='Send']]"));
				if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Under External Review"))
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean sendToContractingParty() {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.id("sendToContractingParty"), processingLoader);
			if (driver
					.findElements(By
							.xpath("//div[@aria-describedby='confirmationPopup'][div/span[text()='Redlines and Comments']]"))
					.size() > 0)
			clickAndWaitUntilLoaderDisappears(By
					.xpath("//div[@id='confirmationPopup']/following-sibling::div//button[span[text()='Proceed']]"), processingLoader);
			if (driver.findElements(By.id("emailPopup")).size() > 0) {
				LogScreenshot("INFO", "sending contract to contracting party");
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='emailPopup']/following-sibling::div//button[span[text()='Send']]"), processingLoader);
				if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Under External Review"))
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*public boolean proceedToSignOff(String signingOption) {
		boolean result = false;
		try {
			if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Ready for Signoff")) {
				findElement(By.id("proceedToSignOffBtn")).click();
				waitUntilInvisibilityOfElement(processingLoader);
				Thread.sleep(3000);
				if (driver
						.findElements(By
								.xpath("//div[@aria-describedby='confirmationPopup'][div/span[text()='Redlines and Comments']]"))
						.size() > 0)
				findElement(By.xpath("//div[@id='confirmationPopup']/following-sibling::div//button[span[text()='Proceed']]"))
									.click();
				
				do{
					Thread.sleep(200);
				}while(driver.findElements(By.xpath("//div[contains(@class,'blockOverlay')]")).size()>0||driver.findElements(By.xpath("//div[contains(@class,'jqifade')]")).size()>0);
				
				waitUntilInvisibilityOfElement(processingLoader);
				waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'blockOverlay')]"));
				Thread.sleep(3000);
				try{
					WebElement signingBtn = findElement(By.id("sendForSigningBtn"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", signingBtn);
					System.out.println("signing btn clicked");
				}catch(Exception e){
					System.out.println("signing button not clicked");
				}
				try{
					String popUpFinderXpath = "//div[contains(@class,'zys-popup-container')][div/h1[text()='Confirm']]";
					findElement(By.xpath(popUpFinderXpath + "//button[text()='Yes']")).click();
				}catch(Exception e){}
				waitUntilInvisibilityOfElement(processingLoader);
				Signers objSigners = new Signers(driver, logger);
				if (objSigners.sendForSigning(signingOption))
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean proceedToSignOff(String signingOption, Signers objSigners) {
		boolean result = false;
		try {
			if (findElement(By.xpath("//span[@class='yellowbutton']")).getText().equals("Ready for Signoff")) {
				clickAndWaitUntilLoaderDisappears(By.id("proceedToSignOffBtn"));
				Thread.sleep(3000);
				findElement(By.xpath("//div[@id='confirmationPopup']/following-sibling::div//button[span[text()='Proceed']]"))
									.click();
				do{
					Thread.sleep(200);
				}while(driver.findElements(By.xpath("//div[contains(@class,'blockOverlay')]")).size()>0||driver.findElements(By.xpath("//div[contains(@class,'jqifade')]")).size()>0);
				
				waitUntilInvisibilityOfElement(processingLoader);
				waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'blockOverlay')]"));
				Thread.sleep(3000);
				try{
					WebElement signingBtn = findElement(By.id("sendForSigningBtn"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", signingBtn);
					System.out.println("signing btn clicked");
				}catch(Exception e){
					System.out.println("signing button not clicked");
				}
				try{
					String popUpFinderXpath = "//div[contains(@class,'zys-popup-container')][div/h1[text()='Confirm']]";
					findElement(By.xpath(popUpFinderXpath + "//button[text()='Yes']")).click();
				}catch(Exception e){}
				waitUntilInvisibilityOfElement(processingLoader);
				//Signers objSigners = new Signers(driver, logger);
				if (objSigners.sendForSigning(signingOption))
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
