package com.zycus.eProc.Approval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;
import common.Functions.eProc_CommonFunctions;

public class Dialog extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);

	//private By processingLoader = By.xpath("//div[contains(@id,'processing')]");
	private String newDiscusssionTo;
	private String newDiscusssionmsg;

	public Dialog(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public Dialog(WebDriver driver, ExtentTest logger, String newDiscusssionTo, String newDiscusssionmsg) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.newDiscusssionTo = newDiscusssionTo;
		this.newDiscusssionmsg = newDiscusssionmsg;
	}

	public boolean approveDocument(String approveCmmnt) {
		boolean result = false;
		try {
			//if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'approvalDialog')")).size() > 0) {
				findElement(By.id("approvalComments")).sendKeys(approveCmmnt);
				/*findElement(By.id("approveCommentBtn")).click();
				waitUntilInvisibilityOfElement(processingLoader);*/
				clickAndWaitUntilLoaderDisappears(By.id("approveCommentBtn"));
				result = true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean rejectDocument(String rejectionCmmnt) {
		boolean result = false;
		try {
			//if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'rejectDialog')")).size() > 0) {
				findElement(By.id("rejectComments")).sendKeys(rejectionCmmnt);
				/*findElement(By.id("rejectCommentBtn")).click();
				waitUntilInvisibilityOfElement(processingLoader);*/
				clickAndWaitUntilLoaderDisappears(By.id("rejectCommentBtn"));
				result = true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delegateDocument(String delegateApprovalTo, String delegateCmmnt) {
		boolean result = false;
		try {
			//if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'delegateDialog')")).size() > 0) {
				objFunctions.enterText_AutoComplete(By.id("txtDelegateName"), delegateApprovalTo);
				findElement(By.id("delegateComments")).sendKeys(delegateCmmnt);
				/*findElement(By.id("btnDelegateSave")).click();
				waitUntilInvisibilityOfElement(processingLoader);*/
				clickAndWaitUntilLoaderDisappears(By.id("btnDelegateSave"));
				result = true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean askMoreInfo() {
		boolean result = false;
		try {
			//if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'newDiscussionForm')]")).size() > 0) {
				objFunctions.enterText_AutoComplete(By.name("toInputEmailIds"), newDiscusssionTo);
				findElement(By.xpath("//div[@id='newDiscussionForm']//textarea[@name='msgBox']"))
						.sendKeys(newDiscusssionmsg);
				/*findElement(By.id("postCommentBtn")).click();
				waitUntilInvisibilityOfElement(processingLoader);*/
				clickAndWaitUntilLoaderDisappears(By.id("postCommentBtn"));
				result = true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean askMoreInfo(String newDiscussionCC, String filePath) {
		boolean result = false;
		try {
			//if (driver.findElements(By.xpath("//div[contains(@aria-describedby,'newDiscussionForm')]")).size() > 0) {
				objFunctions.enterText_AutoComplete(By.name("toInputEmailIds"), newDiscusssionTo);
				objFunctions.enterText_AutoComplete(By.xpath("//input[contains(@class,'toCcTxtBx')]"), newDiscussionCC);
				findElement(By.xpath("//div[@id='newDiscussionForm']//textarea[@name='msgBox']"))
						.sendKeys(newDiscusssionmsg);
				sendKeys(By.xpath("//*[contains(@id,'attachmentInput')]"), filePath);
				/*findElement(By.id("postCommentBtn")).click();
				waitUntilInvisibilityOfElement(processingLoader);*/
				clickAndWaitUntilLoaderDisappears(By.id("postCommentBtn"));
				result = true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
