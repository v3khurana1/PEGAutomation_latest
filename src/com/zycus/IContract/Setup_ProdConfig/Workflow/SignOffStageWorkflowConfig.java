package com.zycus.IContract.Setup_ProdConfig.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class SignOffStageWorkflowConfig extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String contractType;
	private String workflowName;
	private String workflowDesc;
	
	public SignOffStageWorkflowConfig(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public SignOffStageWorkflowConfig(WebDriver driver, ExtentTest logger, String contractType, String workflowName, String workflowDesc) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.contractType = contractType;
		this.workflowName = workflowName;
		this.workflowDesc = workflowDesc; 
	}

	public boolean createWorkflow() {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.xpath("//input[@value='Create']"));
			if(driver.findElement(By.id("ruleForm")).isDisplayed()){
				findElement(By.xpath("//select[@id='workflowHierarchyId']/option[text()='"+contractType+"']")).click();
				findElement(By.id("ruleName")).sendKeys(workflowName);
				findElement(By.id("ruleDescription")).sendKeys(workflowDesc);
				
				//Add conditions and validate Formula
				clickAndWaitUntilLoaderDisappears(By.xpath("//*[@id='footerBar']//input[@value='Save']"));
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
