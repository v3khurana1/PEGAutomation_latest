package com.zycus.IContract.Setup_ProdConfig.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class UserWorkflowRoles extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String emailId;

	public UserWorkflowRoles(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public UserWorkflowRoles(WebDriver driver, ExtentTest logger, String emailId) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.emailId = emailId;
	}

	public boolean editRole(String role) {
		boolean result = false;
		try {
			if (searchUser()) {
				driver.findElement(By.xpath("//div[@id='userWorkfolwGridDiv']/table/tbody/tr/td[4]//a")).click();
				if (driver
						.findElement(
								By.xpath("//div[@aria-describedby='dialog-confirm'][div/span[text()='Edit Roles']]"))
						.isDisplayed()) {
					findElement(By
							.xpath("//form[@id='editRolesFm']//tbody[tr/td/input/following-sibling::text()[contains(.,'"
									+ role + "')]]//input[1]")).click();
					clickAndWaitUntilLoaderDisappears(By.xpath("//button[span[text()='Update Role']]"));
					if (searchUser()) {
						if (driver.findElement(By.xpath("//div[@id='userWorkfolwGridDiv']/table/tbody/tr/td[3]")).getText()
								.equals(role))
							result = true;
						else
							logger.log(LogStatus.INFO, "Role not edited");
					}else
						logger.log(LogStatus.INFO, "Searched User not found");
					
				}
			}else
				logger.log(LogStatus.INFO, "Searched User not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
