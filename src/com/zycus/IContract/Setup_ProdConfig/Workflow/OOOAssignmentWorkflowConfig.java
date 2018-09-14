package com.zycus.IContract.Setup_ProdConfig.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class OOOAssignmentWorkflowConfig extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String assignFrom;
	private String assignTo;
	private String fromDt;
	private String ToDt;
	private String comments;
	
	public OOOAssignmentWorkflowConfig(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public OOOAssignmentWorkflowConfig(WebDriver driver, ExtentTest logger, String assignFrom, String assignTo, String fromDt, String ToDt, String comments) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.assignFrom = assignFrom;
		this.assignTo = assignTo;
		this.fromDt = fromDt;
		this.ToDt = ToDt; 
		this.comments = comments;
	}

	public boolean createWorkflow() {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.xpath("//input[@value='Create']"));
			if(driver.findElement(By.xpath("//div[@aria-describedby='oooAssignmentPopup']")).isDisplayed()){
				findElement(By.id("fromUserSearchBar")).sendKeys(assignFrom);
				if(findElement(By.xpath("//div[@id='fromUserList' and contains(@style,'block')]")).isDisplayed())
					findElement(By.xpath("//div[@id='fromUserList']/span[1]")).click();
				else
					logger.log(LogStatus.INFO, "Assign From user not found");
				findElement(By.id("toUserSearchBar")).sendKeys(assignTo);
				if(findElement(By.xpath("//div[@id='toUserList' and contains(@style,'block')]")).isDisplayed())
					findElement(By.xpath("//div[@id='toUserList']/span[1]")).click();
				else
					logger.log(LogStatus.INFO, "Assign To user not found");
				findElement(By.id("notifyFromDate")).click();
				selectDate_v1(fromDt);
				findElement(By.id("notifyToDate")).click();
				selectDate_v1(ToDt);
				findElement(By.xpath("//textarea[contains(@class,'commonComments')]")).sendKeys(comments);
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='oooAssignmentPopup']/following-sibling::div//span[text()='Save']"));
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
