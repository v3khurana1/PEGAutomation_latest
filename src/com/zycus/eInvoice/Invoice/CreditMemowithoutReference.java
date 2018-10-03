package com.zycus.eInvoice.Invoice;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> CreditMemowithoutReference.java</br>
 * <br>
 * <b> Description: </b> create Credit Memo without Reference</br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.createCreditMemo: user is able to create a Credit Memo without reference
 * </br>
 * <br>
 * 
 * @author Anisha
 * @since May 2018
 */

public class CreditMemowithoutReference extends eInvoice_CommonFunctions {

	//private ExtentTest logger;
	//private WebDriver driver;
	private String supplierName;
	private String currency_value;
	private String creditMemoDate;
	private String purchaseType;
	private String description;
	private String product_cat;
	private String market_prc;
	private String quantity;
	private String GLType;
	private String GLAccount;
	/*private By supplierNameID = By.id("txtSupplierName");
	private By creditMemoNoID = By.id("txtInvoiceNumber");
	private By invoiceDateImgXpath = By.xpath("//input[@name='txtInvoiceDate']/following-sibling::img");
	private By purchaseTypeXpath = By
			.xpath("//*[@id='slctPurchaseType']/option[contains(text(),'" + purchaseType + "')]");
	private By addItemXpath = By.xpath("//*[@id='addMoreItems']//span[contains(text(),'Add Item')]");
	private By submitBtnId = By.xpath("//a[@id='btnSubmit' and text()='Submit']");*/

	public CreditMemowithoutReference(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.logger = logger;
		//this.driver = driver;
	}

	public CreditMemowithoutReference(WebDriver driver, ExtentTest logger, String supplierName, String currency_value,
			String creditMemoDate, String purchaseType, String description, String product_cat, String market_prc,
			String quantity, String GLType, String GLAccount) {
		super(driver, logger);
		//this.logger = logger;
		//this.driver = driver;
		this.supplierName = supplierName;
		this.currency_value = currency_value;
		this.creditMemoDate = creditMemoDate;
		this.purchaseType = purchaseType;
		this.description = description;
		this.product_cat = product_cat;
		this.market_prc = market_prc;
		this.quantity = quantity;
		this.GLType = GLType;
		this.GLAccount = GLAccount;
	}

	/**
	 * <b>Function:</b> createCreditMemo
	 * 
	 * @author Anisha
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	/*public boolean createCreditMemo() {
		boolean result = false;
		String creditMemoNo = String.valueOf(generateNo());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			enterText_AutoComplete(supplierNameID, supplierName);
			selectCurrency(currency_value);
			selectAddress();
			sendKeys(creditMemoNoID, creditMemoNo);
			WebElement elem = findElement(invoiceDateImgXpath);
			js.executeScript("arguments[0].click()", elem);
			selectDate_v1(creditMemoDate);
			findElement(purchaseTypeXpath).click();
			addAttachment();
			scroll_into_view_element(findElement(addItemXpath));
			findElement(addItemXpath).click();
			Thread.sleep(3000);
			add_item(description, product_cat, market_prc, quantity, GLType);
			Thread.sleep(2000);
			findElement(submitBtnId).click();
			Thread.sleep(2000);
			logger.log(LogStatus.INFO, "created CM without reference : " + creditMemoNo);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean createCreditMemo() {
		boolean result = false;
		try {
			result = createNewInvoiceOrCreditMemo(purchaseType,
					supplierName, currency_value, creditMemoDate, description,
					product_cat, market_prc, quantity, GLType, GLAccount)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
