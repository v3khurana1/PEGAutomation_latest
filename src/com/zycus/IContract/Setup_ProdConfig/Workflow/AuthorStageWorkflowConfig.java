package com.zycus.IContract.Setup_ProdConfig.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class AuthorStageWorkflowConfig extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String contractType;
	private String workflowName;
	
	public AuthorStageWorkflowConfig(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public AuthorStageWorkflowConfig(WebDriver driver, ExtentTest logger, String contractType, String workflowName) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.contractType = contractType;
		this.workflowName = workflowName;
	}

	public boolean createWorkflow() {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.xpath("//input[@value='Create']"));
			if(driver.findElement(By.xpath("//div[@aria-describedby='createWorkflowPopupDiv']")).isDisplayed()){
				findElement(By.xpath("//select[@id='createWorkflowHierarchyId']/option[text()='"+contractType+"']")).click();
				findElement(By.id("createPopupWorkflowName")).sendKeys(workflowName);
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='createWorkflowPopupDiv']/following-sibling::div//span[text()='Save']"));
				//Flash error is received
				result= true;
			}else
				logger.log(LogStatus.INFO, "Create Workflow pop up not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
