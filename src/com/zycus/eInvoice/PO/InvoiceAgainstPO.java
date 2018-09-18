package com.zycus.eInvoice.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> InvoiceAgainstPO.java</br>
 * <br>
 * <b> Description: </b> create new Invoice against PO</br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.createNewInvoice: user is able to create an Invoice against a PO </br>
 * <br>
 * 
 * @author Anisha
 * @since May 2018
 */

public class InvoiceAgainstPO extends eInvoice_CommonFunctions {

	private ExtentTest logger;
	private WebDriver driver;
	private String invoiceDate;
	//private String invoiceNo;

	public InvoiceAgainstPO(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	// public InvoiceAgainstPO(WebDriver driver, ExtentTest logger, Date
	// invoiceDate) {
	public InvoiceAgainstPO(WebDriver driver, ExtentTest logger, String invoiceDate) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.invoiceDate = invoiceDate;

	}

	/**
	 * <b>Function:</b> createNewInvoice
	 * 
	 * @author Anisha
	 * @param invoiceNo
	 * @since May 2018
	 * @throws Exception
	 * @return status
	 */

	public boolean createNewInvoice() {

		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Thread.sleep(3000);
			String invoiceNo = String.valueOf(generateNo());
			sendKeys(By.id("txtInvoiceNumber"), invoiceNo);
			try{
				selectDate_v1(invoiceDate);
			}catch(Exception e){}
			// TODO invoice due date
			// add attachment
			selectTodayDate();
			addAttachment();
			// TODO add remit to address
			scroll_into_view_element(findElement(By.id("einvoiceItemList")));

			if (driver.findElement(By.id("vng-collapsibleGrid")).isDisplayed()) {
				logger.log(LogStatus.INFO, "inside if block");
				WebElement foo = driver.findElement(By.xpath(
						"(//table[@id='vng-collapsibleGrid']/tbody//tr[contains(@id,'itemRow')]//input[not(@disabled)])[1]"));
				Thread.sleep(2000);
				scroll_into_view_element(foo);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", foo);
			}
			Thread.sleep(3000);
			/*findElement(By.id("btnSubmit")).click();
			waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_updateInvoice']/div"));*/
			clickAndWaitUntilLoaderDisappears(By.id("btnSubmit"), By.xpath("//*[@id='status_overlay_updateInvoice']/div"));
			if (findElement(By.xpath("//span[text()='Submit for approval']")).isDisplayed())
				findElement(By.xpath("//div[@class='b-box']//input[@value='Send for confirmation']")).click();
			waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_updateInvoice']/div"));
			Thread.sleep(5000);
			logger.log(LogStatus.INFO, "created invoice against PO : " + invoiceNo);
			// validate if invoice is created
			if (findElement(By.xpath("//*[@id='invoiceGrid']/tbody/tr[*]/td[1]/a[text()='" + invoiceNo + "']"))
					.isDisplayed())
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
