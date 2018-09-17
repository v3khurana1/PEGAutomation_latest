package common.Reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

public class Common_ReportDetail extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By reportLabel = By.xpath("//*[@id='reportNameHeader_RMS']/label");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Common_ReportDetail(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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

	public boolean closeReportDetails() throws Exception {
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

	/**
	 * @return the reportLabel
	 */
	public By getReportLabel() {
		return reportLabel;
	}

	/**
	 * @param reportLabel
	 *            the reportLabel to set
	 */
	public void setReportLabel(By reportLabel) {
		this.reportLabel = reportLabel;
	}

}
