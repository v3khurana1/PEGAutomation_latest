package com.zycus.eProc.Budget;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class Budget_NewBudget extends eProc_CommonFunctions {
	private WebDriver driver;
	private ExtentTest logger;
	
	private String ownerName;
	private String budgetName;
	private String companyName;
	private String currType;
	private Date ToDate;
	private By NewBudgetLbl = By.xpath("//h2[contains(@class,'pgHead') and contains(text(),'New Budget')]"); 

	public Budget_NewBudget(WebDriver driver, ExtentTest logger, String ownerName, String budgetName, String companyName, String currType, 
			Date ToDt) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.ownerName = ownerName;
		this.budgetName = budgetName;
		this.companyName = companyName;
		this.currType = currType;
		this.ToDate = ToDt;
	}

	/**
	 * <b>Function:</b> enterBudgetDetails
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param budgetDesc
	 * @return result - true/false
	 */

	public boolean enterBudgetDetails(String budgetDesc) {
		boolean result = false;
		try {
			sendKeys(By.xpath("//input[@name='ownerNameText']"), ownerName);
			sendKeys(By.xpath("//input[@name='budgetNameText']"), budgetName);
			sendKeys(By.xpath("//input[@name='companyNameText']"), companyName);
			sendKeys(By.xpath("//textarea[@name='budgetDescriptionText']"), budgetDesc);
			sendKeys(By.xpath("//input[@name='currencyTypeText']"), currType);
			findElement(By.xpath("(//form[@id='budgetDetailsForm']//li[label/text()[contains(.,'Validity')]]//img)[2]")).click();
			selectDate(ToDate);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> enterSettings
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param dispUtilizationToRequesters
	 * @param dispUtilizationToApprovers
	 * @param ObserverPercentage
	 * @param ApproverPercentage
	 * @param notifyBudgetOwner
	 * @return result - true/false
	 */

	public boolean enterSettings(boolean dispUtilizationToRequesters, boolean dispUtilizationToApprovers,
			int ObserverPercentage, int ApproverPercentage, boolean notifyBudgetOwner) {
		boolean result = false;
		try {
			if (dispUtilizationToRequesters)
				findElement(By.id("displayUtilApprover")).click();
			if (dispUtilizationToApprovers)
				findElement(By.id("displayUtilRequester")).click();
			sendKeys(By.id("ownerAsObserver"), String.valueOf(ObserverPercentage));
			sendKeys(By.id("ownerAsApprover"), String.valueOf(ApproverPercentage));
			if (notifyBudgetOwner)
				findElement(By.xpath("(//*[@id='radioNotifyOwner']//input)[1]")).click();
			result = true;
		} catch (Exception e) {
			logger.log(LogStatus.INFO, e.getMessage());
		}
		return result;
	}

	/**
	 * <b>Function:</b> addPeriod
	 * @author Varun Khurana
	 * @since April 2018
	 * @param StartDt
	 * @param EndDt
	 * @param periodName
	 * @param description
	 * @return result - true/false
	 */

	public boolean addPeriod(Date StartDt, Date EndDt, String periodName, String description) {
		boolean result = false;
		try {
			findElement(By.id("addBudgetPeriod")).click();
			Budget_AddPeriod objAddPeriod = new Budget_AddPeriod(driver, logger, StartDt, EndDt, periodName);
			if (objAddPeriod.addPeriod(description))
				result = true;
			else
				logger.log(LogStatus.INFO, "Period not added to the list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the newBudgetLbl
	 */
	public By getNewBudgetLbl() {
		return NewBudgetLbl;
	}

	/**
	 * @param newBudgetLbl the newBudgetLbl to set
	 */
	public void setNewBudgetLbl(By newBudgetLbl) {
		NewBudgetLbl = newBudgetLbl;
	}

}
