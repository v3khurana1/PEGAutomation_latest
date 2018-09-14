package com.zycus.eProc.Budget;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eProc_CommonFunctions;

public class BudgetDetails extends eProc_CommonFunctions{

	private WebDriver driver;
	private ExtentTest logger;
	
	private By budgetlabel = By.id("viewBudgetNameLabel");

	public BudgetDetails(WebDriver driver, ExtentTest logger) throws Exception {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * @return the budgetlabel
	 */
	public By getBudgetlabel() {
		return budgetlabel;
	}

	/**
	 * @param budgetlabel the budgetlabel to set
	 */
	public void setBudgetlabel(By budgetlabel) {
		this.budgetlabel = budgetlabel;
	}
	
	

}
