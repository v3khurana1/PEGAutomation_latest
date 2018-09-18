package com.zycus.eInvoice.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

//import common.Functions.eInvoice_CommonFunctions;
import common.Reports.Common_ReportDetail;

public class ReportDetail extends Common_ReportDetail {

	private WebDriver driver;
	//private ExtentTest logger;
	//private By reportLabel = By.xpath("//*[@id='reportNameHeader_RMS']/label");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public ReportDetail(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * <b>Function:</b> shareMyReports
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param reportName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean shareMyReports(String reportName, String sharedUserEmail) throws Exception {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			findElement(By.xpath("//div[@class='topTabNavigation_RMS']//a[contains(text(),'More Option')]")).click();
			WebElement objShare = findElement(By.xpath("//div[@class='topTabNavigation_RMS']//a[text()='Share']"));
			js.executeScript("arguments[0].click()", objShare);
			waitUntilVisibilityOfElement(By.id("shareDashList"));
			WebElement userChkBox = driver.findElement(By.xpath(
					"//table[@class='shareListTable_RMS']//div[text()='" + sharedUserEmail + "']/ancestor::tr//input"));
			if (userChkBox.getAttribute("disabled").equals("true")) {
				findElement(
						By.xpath("(//table[@class='shareListTable_RMS']//div/ancestor::tr//input[not(@disabled)])[1]"))
								.click();
				sharedUserEmail = findElement(By
						.xpath("(//table[@class='shareListTable_RMS']//div/ancestor::tr//input[not(@disabled)])[1]/ancestor::tr/td[3]/div"))
								.getText();
			} else
				userChkBox.click();

			if (driver.findElements(By.xpath("//ul[@id='ulUser']/li[@id='" + sharedUserEmail + "']")).size() > 0) {
				findElement(By.id("btnconfirm")).click();
				result = findElement(By.xpath("//div[@id='jqi'][div/div[text()='Success']]")).isDisplayed() ? true
						: false;
				findElement(By.xpath("//div[@id='jqi']//button[text()='Go to report']")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> closeReportDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param reportName
	 * @return result - True/False
	 * @throws Exception
	 */

	/*public boolean closeReportDetails() throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@id='rightButton_RMS']//input[@title='Close']")).click();
			waitUntilInvisibilityOfElement(By.id("viewdetailsloadingdiv"));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
*/
	/**
	 * @return the reportLabel
	 */
	/*public By getReportLabel() {
		return reportLabel;
	}*/

	/**
	 * @param reportLabel
	 *            the reportLabel to set
	 */
	/*public void setReportLabel(By reportLabel) {
		this.reportLabel = reportLabel;
	}*/

}
