package com.zycus.eInvoice.Downloads;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class Downloads extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;
	private String APReportCriteria;
	//private By processingLoader = By.id("downloadsGrid_processing");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Downloads(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Downloads(WebDriver driver, ExtentTest logger, String APReportCriteria) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
		this.APReportCriteria = APReportCriteria;
	}

	/**
	 * <b>Function:</b> downloadReport
	 * 
	 * @author Varun Khurana
	 * @since June 2018
	 * @param startDt
	 * @param EndDt
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean downloadReport(String startDt, String EndDt) {
		boolean result = false;
		try {
			findElement(By.xpath("//select[@id='criteriabased']/option[text()='" + APReportCriteria + "']")).click();
			findElement(By.id("dateRadioRange"));
			findElement(By.xpath("//img[@class='ui-datepicker-trigger' and contains(@alt,'start date')]")).click();
			selectDate_v1(startDt);
			findElement(By.xpath("//img[@class='ui-datepicker-trigger' and contains(@alt,'end date')]")).click();
			selectDate_v1(EndDt);
			/*findElement(By.id("btnSubmit"));
			waitUntilInvisibilityOfElement(processingLoader);*/
			clickAndWaitUntilLoaderDisappears(By.id("btnSubmit"));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>Function:</b> downloadReport
	 * 
	 * @author Varun Khurana
	 * @since June 2018
	 * @param monthsSince
	 * @return result - True/False
	 * @throws Exception
	 */
	
	public boolean downloadReport(int monthsSince) {
		boolean result = false;
		try {
			findElement(By.xpath("//select[@id='criteriabased']/option[text()='" + APReportCriteria + "']")).click();
			findElement(By.xpath("//select[@id='monthVal']/option[@value='"+String.valueOf(monthsSince)+"']"));
			/*findElement(By.id("btnSubmit"));
			waitUntilInvisibilityOfElement(processingLoader);*/
			clickAndWaitUntilLoaderDisappears(By.id("btnSubmit"));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
