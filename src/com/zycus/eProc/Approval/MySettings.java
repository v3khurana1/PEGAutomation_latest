package com.zycus.eProc.Approval;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;

//import com.zycus.eInvoice.Invoice.ApprovalDetails;

public class MySettings extends ApprovalDetails {

	private WebDriver driver;
	private ExtentTest logger;
	
	public MySettings(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	/**
	 * <b>Function:</b> delegateApprovalTo - To filter by Status 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param approvalFrom - Label of the checkbox which is to be selected, 
	 * @param approvalTo -gfghhg
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 */ 
	
	public boolean delegateApprovalTo(String approvalFrom, String approvalTo, Date fromDt, Date ToDt) {
		boolean result = false;
		try {
			WebElement enableBtn = findElement(By.id("btnSave"));
			WebElement revokeBtn = findElement(By.id("btnRevoke"));

			findElement(By.id("delegateFrom")).clear();
			sendKeys(By.id("delegateFrom"), approvalFrom);
			sendKeys(By.id("delegateFromId"), approvalTo);
			findElement(By.xpath("//input[@id='fromDate']/img")).click();
			selectDate(fromDt);
			findElement(By.xpath("//input[@id='toDate']/img")).click();
			selectDate(ToDt);
			enableBtn.click();
			if (revokeBtn.getAttribute("style") == "")
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
