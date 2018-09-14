package com.zycus.IContract.Setup_ProdConfig.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

public class AliasUsers extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String emailId;

	public AliasUsers(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public AliasUsers(WebDriver driver, ExtentTest logger, String emailId) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.emailId = emailId;
	}

	public boolean searchUser() {
		boolean result = false;
		try {
			findElement(By.id("searchUser")).sendKeys(emailId);
			clickAndWaitUntilLoaderDisappears(By.id("searchGoBtn"));
			if (driver.findElement(By.xpath("//div[@id='userWorkfolwGridDiv']/table/tbody/tr/td[2]")).getText()
					.equals(emailId))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
