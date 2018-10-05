package com.zycus.eProc.Budget;

import java.time.Month;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class Budget_AddPeriod extends eProc_CommonFunctions{
	
	private WebDriver driver;
	private ExtentTest logger;
	private Date StartDt;
	private Date EndDt;
	private String periodName;
	
	public Budget_AddPeriod(WebDriver driver, ExtentTest logger, Date StartDt, Date EndDt, String periodName) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.StartDt = StartDt;
		this.EndDt = EndDt;
		this.periodName = periodName;
	}
	
	/**
	 * <b>Function:</b> addPeriod
	 * @author Varun Khurana
	 * @since April 2018
	 * @param description
	 * @return result
	 * @throws Exception 
	 */
	
	public boolean addPeriod(String description) throws Exception{
		boolean result = false;
		
		String period = periodName;
		findElement(By.xpath("//form[@id='budgetPeriodForm_createBudget']//li[label/text()[contains(.,'Start Date')]]//img")).click();
		selectDate(StartDt);
		findElement(By.xpath("//form[@id='budgetPeriodForm_createBudget']//li[label/text()[contains(.,'End Date')]]//img")).click();
		selectDate(EndDt);
		if(periodName != "")
			sendKeys(By.xpath("//form[@id='budgetPeriodForm_createBudget']//li[label/text()[contains(.,'Period Name')]]//input"), periodName);
		else{
			period = findElement(By.xpath("//form[@id='budgetPeriodForm_createBudget']//li[label/text()[contains(.,'Period Name')]]//input")).getText();
			if(period != this.getPeriodName(StartDt, EndDt))
				logger.log(LogStatus.INFO, "Incorrect Period Name displayed or is blank");
		}
		if(description!="")
			sendKeys(By.xpath("//form[@id='budgetPeriodForm_createBudget']//li[label/text()[contains(.,'Description')]]//textarea"), description);
		findElement(By.xpath("//*[@id='addNewPeriod']//input[@value='Add']")).click();
		if(findElement(By.xpath("//*[@id='budgerPeriodDropDown']//a[text()='"+period+"']"))!=null)
			result = true;
		else
			logger.log(LogStatus.INFO, "Period not added to the list");
		return result;
	}
	
	/**
	 * <b>Function:</b> getPeriodName
	 * @author Varun Khurana
	 * @since April 2018
	 * @param StartDt
	 * @param EndDt
	 * @return periodName
	 */
	
	private String getPeriodName(Date StartDt, Date EndDt){
		int Start_dd = Integer.parseInt(StartDt.toString().split("/")[0]);
		int Start_mm = Integer.parseInt(StartDt.toString().split("/")[1]);
		int Start_yr = Integer.parseInt(StartDt.toString().split("/")[2].substring(2));
		
		int End_dd = Integer.parseInt(EndDt.toString().split("/")[0]);
		int End_mm = Integer.parseInt(EndDt.toString().split("/")[1]);
		int End_yr = Integer.parseInt(EndDt.toString().split("/")[2].substring(2));

		String periodName = "FY"+Start_yr+"-"+End_yr+"-"+Month.of(Start_mm).name()+"/"+Start_dd+"-"+Month.of(End_mm).name()+"/"+End_dd;
		return periodName;
	}
	
	/**
	 * <b>Function:</b> addPeriod
	 * 
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
	
}
