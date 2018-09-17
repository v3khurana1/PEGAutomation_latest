package com.zycus.IContract.ManageContracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Framework.ConfigurationProperties;
import common.Functions.eInvoice_CommonFunctions;

public class Documents extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//span[@id='headerLabel']/following-sibling::h2[text()='Line Items']");

	public Documents(WebDriver driver, ExtentTest logger) {
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

	/*private boolean addDocument() {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@id='btnAddDoc']//input")).click();
			if (driver.findElement(By.xpath("//div[div[@id='zyDevPopup1']]")).isDisplayed())
				result = true;
			else
				logger.log(LogStatus.INFO, "Add Documents popup not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	private boolean addDocument() {
		boolean result = false;
		try {
			clickAndWaitUntilElementAppears(By.xpath("//div[@id='btnAddDoc']//input"), By.xpath("//div[div[@id='zyDevPopup1']]"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.INFO, "Add Documents popup not displayed");
		}
		return result;
	}

	public boolean selectDocuments() {
		boolean result = false;
		try {
			if (addDocument()) {
				ConfigurationProperties config = ConfigurationProperties.getInstance();
				driver.findElement(By.xpath("//div[@id='zyDevPopup1']//span[@class='newUploadBtn']"))
						.sendKeys(System.getProperty("user.dir") + config.getProperty("upload_file_path"));
				Thread.sleep(4000);
				findElement(By.id("addDocBtnDone")).click();
				
				//Verify if document is added in the Grid
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
