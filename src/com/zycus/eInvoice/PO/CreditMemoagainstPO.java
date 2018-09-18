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
 * @author Anisha
 * @since May 2018
 */

public class CreditMemoagainstPO extends eInvoice_CommonFunctions {
	private ExtentTest logger;
	private WebDriver driver;
	//private String creditMemoNo;
	//private Date creditMemoDate;

	public CreditMemoagainstPO(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/*public CreditMemoagainstPO(WebDriver driver, ExtentTest logger, Date creditMemoDate) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.creditMemoDate = creditMemoDate;
	}*/
	
	/**
	 * <b>Function:</b> createCreditMemo
	 * 
	 * @author Anisha
	 * @param none
	 * @since May 2018
	 * @throws Exception
	 * @return status
	 */
	
	public boolean createCreditMemo() {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String creditMemoNo = String.valueOf(generateNo());
			sendKeys(By.id("txtInvoiceNumber"), creditMemoNo);
			selectTodayDate();
			// selectDate(creditMemoDate);
			addAttachment();
			// scroll_into_view_element(findElement(By.xpath("//*[@id='vng-collapsibleGrid']//tr[1]/td[1]/input")));
			Thread.sleep(2000);
			if (driver.findElement(By.id("vng-collapsibleGrid")).isDisplayed()) {
				logger.log(LogStatus.INFO, "inside if block");
				Thread.sleep(2000);
				WebElement foo = driver.findElement(By.xpath(
						"(//table[@id='vng-collapsibleGrid']/tbody//tr[contains(@id,'itemRow')]//input[not(@disabled)])[1]"));
				Thread.sleep(2000);
				scroll_into_view_element(foo);
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", foo);

				// ExpectedCondition<WebElement> foo =
				// ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='vng-collapsibleGrid']//tr[1]/td[1]/input"));
				// ((WebElement) foo).click();
			}
			//DO NOT REMOVE THIS WAIT
			Thread.sleep(3000);
			logger.log(LogStatus.INFO, "before submit button");
			/*findElement(By.id("btnSubmit")).click();
			waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_updateInvoice']"));*/
			clickAndWaitUntilLoaderDisappears(By.id("btnSubmit"), By.xpath("//*[@id='status_overlay_updateInvoice']"));
			Thread.sleep(5000);
			if (driver
					.findElement(By.xpath("//*[@id='invoiceGrid']/tbody/tr[*]/td[1]/a[text()='" + creditMemoNo + "']"))
					.isDisplayed())
				result = true;
			logger.log(LogStatus.INFO, "created CM against PO : " + creditMemoNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
