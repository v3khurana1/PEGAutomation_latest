package com.zycus.IContract.ManageContracts;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class LineItems extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader = By.xpath("//span[@class='zys-loader-icon']");
	private By pgHead = By.xpath("//span[@id='headerLabel']/following-sibling::h2[text()='Line Items']");
	private String itemNo;
	private String desc;
	private String measurementUnit;
	private int qty;
	private String priceType;
	private String currency;
	private float cost;

	public LineItems(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public LineItems(WebDriver driver, ExtentTest logger, String itemNo, String desc, String measuremenUnit, int qty,
			String priceType, String currency, float cost) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.itemNo = itemNo;
		this.desc = desc;
		this.measurementUnit = measuremenUnit;
		this.qty = qty;
		this.priceType = priceType;
		this.currency = currency;
		this.cost = cost;
	}

	/**
	 * @return the pgHead
	 */
	public By getPgHead() {
		return pgHead;
	}

	/**
	 * @param pgHead
	 *            the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}

	/*private boolean createLineItem() {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@id='addLineItemDiv']//button")).click();
			findElement(By.xpath("//div[@id='addLineItemDiv']//ul/li/a[text()='Create Line Item']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if (driver.findElement(By.xpath("//div[div[@id='addLineItemPopup']]")).isDisplayed())
				result = true;
			else
				logger.log(LogStatus.INFO, "Add Line Item popup not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	*/
	private boolean createLineItem() {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@id='addLineItemDiv']//button")).click();
			clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='addLineItemDiv']//ul/li/a[text()='Create Line Item']"), processingLoader);
			if (driver.findElement(By.xpath("//div[div[@id='addLineItemPopup']]")).isDisplayed())
				result = true;
			else
				logger.log(LogStatus.INFO, "Add Line Item popup not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public boolean addLineItem(String category, String pricingType, String copyPricingFrm) {
		boolean result = false;
		try {
			if (createLineItem()) {
				findElement(By.xpath("//div[@id='addLineItemDiv']//button")).click();
				clickAndWaitUntilLoaderDisappears(By.xpath("//div[@id='addLineItemDiv']//ul/li/a[text()='Create Line Item']"), processingLoader);
				if (driver.findElement(By.xpath("//div[div[@id='addLineItemPopup']]")).isDisplayed())
					result = true;
				int existingLineItems = driver
						.findElements(By.xpath("//table[@id='lineItems-grid']/tbody/tr[contains(@class,'dataRow')]"))
						.size();

				// Line Item Information
				findElement(By.id("lineItemNumber")).sendKeys(itemNo);
				findElement(By.id("description")).sendKeys(desc);
				findElement(By.xpath("//select[@id='uomId']/option[text()='" + measurementUnit + "']"));
				findElement(By.id("lineItemQuantity")).sendKeys(String.valueOf(qty));
				findElement(By.id("category")).sendKeys(category);
				findElement(By.xpath("//select[@id='priceType']/option[text()='" + priceType + "']"));
				findElement(By.xpath("//select[@id='currency']/option[@currencyname='" + currency + "']"));
				findElement(By.id("lineItemCost")).sendKeys(String.valueOf(cost));

				// Line Item Pricing
				findElement(By.xpath("//select[@id='pricingTypeId']/option[text()='" + pricingType + "']"));
				findElement(By.xpath("//select[@id='lineItemCopyId']/option[text()='" + copyPricingFrm + "']"));
				clickAndWaitUntilLoaderDisappears(By.xpath("//button/span[text()='Save']"), processingLoader);
				int lineItems = driver
						.findElements(By.xpath("//table[@id='lineItems-grid']/tbody/tr[contains(@class,'dataRow')]"))
						.size();
				if (lineItems == existingLineItems + 1)
					result = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
