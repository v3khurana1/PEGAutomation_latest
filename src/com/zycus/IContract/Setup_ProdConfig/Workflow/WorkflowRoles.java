package com.zycus.IContract.Setup_ProdConfig.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class WorkflowRoles extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String roleName;
	private int seqNo;

	public WorkflowRoles(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public WorkflowRoles(WebDriver driver, ExtentTest logger, String roleName, int seqNo) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.roleName = roleName;
		this.seqNo = seqNo;
	}

	public boolean createWorkflow() {
		boolean result = false;
		//int addedRowPos = 0;
		int i = 1;
		String role = null;
		String seq = null;
		try {
			clickAndWaitUntilLoaderDisappears(By.id("addRole"));
			if (driver.findElement(By.xpath("//div[@aria-describedby='dialog-confirm']")).isDisplayed()) {
				findElement(By.id("roleName")).sendKeys(roleName);
				findElement(By.id("sequenceNumber")).sendKeys(String.valueOf(seqNo));
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='dialog-confirm']/following-sibling::div//span[text()='Add Role']"));
				int noOfRoles = driver.findElements(By.xpath("//form[@id='negotiateClauseReviewForm']/table/tbody/tr"))
						.size();
				for (i = 1; i <= noOfRoles; i++) {
					role = findElement(By.xpath("//form[@id='negotiateClauseReviewForm']/table/tbody/tr["
							+ String.valueOf(i) + "]/td[1]//span")).getText();
					if (role.equals(roleName))
						break;
				}
				if (i > 0) {
					seq = findElement(By.xpath("//form[@id='negotiateClauseReviewForm']/table/tbody/tr["
							+ String.valueOf(i) + "]/td[2]//span")).getText();
					if (seq.equals(String.valueOf(seqNo)))
						result = true;
				}else if(i==0)
					logger.log(LogStatus.INFO, "Role not added");
			} else
				logger.log(LogStatus.INFO, "Create Workflow pop up not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
