package common.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class Common_Reports extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Common_Reports(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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

	public boolean searchReport(String reportType, String reportName) throws Exception {
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
	}

	/**
	 * <b>Function:</b> viewReportDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param reportName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean viewReportDetails(String reportName) throws Exception {
		boolean result = false;
		try {
			// findElement(By.xpath("//table[@class='reportsListTable_RMS']//tr[*]//a/span[contains(text(),'"+reportName+"')]")).click();
			findElement(By.xpath("//table[@class='reportsListTable_RMS']//tr[2]/td[1]/div/a/span")).click();
			waitUntilInvisibilityOfElement(By.id("viewdetailsloadingdiv"));
			Common_ReportDetail objRepDetail = new Common_ReportDetail(driver, logger);
			if (findElement(objRepDetail.getReportLabel()).getAttribute("title").equals(reportName)) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

}
