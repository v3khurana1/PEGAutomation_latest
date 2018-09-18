package com.zycus.eInvoice.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

//import common.Functions.eInvoice_CommonFunctions;\
import common.Reports.Common_Reports;

public class Reports extends Common_Reports {

	//private WebDriver driver;
	//private ExtentTest logger;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Reports(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * <b>Function:</b> searchReport
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param reportType
	 * @param reportName
	 * @return result - True/False
	 * @throws Exception
	 */

	/*public boolean searchReport(String reportType, String reportName) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//select[@id='optionSelect_RMS']/option[text()='" + reportType + "']")).click();
			findElement(By.id("searchBox_RMS")).sendKeys(reportName);
			findElement(By.id("searchButton")).click();
			waitUntilInvisibilityOfElement(By.id("viewdetailsloadingdiv"));
			Thread.sleep(2000);
			// if(findElement(By.xpath("//table[@class='reportsListTable_RMS']//tr[*]//a/span[text()='"+reportName+"']"))!=null)
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}*/

	/**
	 * <b>Function:</b> viewReportDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param reportName
	 * @return result - True/False
	 * @throws Exception
	 */

	/*public boolean viewReportDetails(String reportName) throws Exception {
		boolean result = false;
		try {
			// findElement(By.xpath("//table[@class='reportsListTable_RMS']//tr[*]//a/span[contains(text(),'"+reportName+"')]")).click();
			findElement(By.xpath("//table[@class='reportsListTable_RMS']//tr[2]/td[1]/div/a/span")).click();
			waitUntilInvisibilityOfElement(By.id("viewdetailsloadingdiv"));
			ReportDetail objRepDetail = new ReportDetail(driver, logger);
			if (findElement(objRepDetail.getReportLabel()).getAttribute("title").equals(reportName)) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}*/

	/**
	 * <b>Function:</b> selectMyReports
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param reportName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean selectMyReports(String reportName) throws Exception {
		boolean result = false;
		try {
			/*findElement(By.xpath("//div[div[text()='My Reports']]//a[@title='" + reportName + "']")).click();
			waitUntilInvisibilityOfElement(By.id("viewdetailsloadingdiv"));*/
			clickAndWaitUntilLoaderDisappears(By.xpath("//div[div[text()='My Reports']]//a[@title='" + reportName + "']"), By.id("viewdetailsloadingdiv"));
			viewReportDetails(reportName);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

}
