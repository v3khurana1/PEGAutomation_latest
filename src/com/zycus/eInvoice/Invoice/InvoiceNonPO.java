package com.zycus.eInvoice.Invoice;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> InvoiceNonPO.java</br>
 * <br>
 * <b> Description: </b> create new Invoice for nonPO</br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.createNewInvoice: user is able to create a Non PO Invoice </br>
 * <br>
 * 
 * @author Anisha
 * @since May 2018
 */

public class InvoiceNonPO extends eInvoice_CommonFunctions {

	private ExtentTest logger;
	private WebDriver driver;
	private String supplierName;
	private String paymentTerm;
	private String currency_value;
	private String purchaseType;
	private String invoiceDate;
	private String description;
	private String product_cat;
	private String market_prc;
	private String quantity;
	private String GLType;
	/*private By supplierNameId = By.id("txtSupplierName");
	private By paymentTermXpath = By.xpath("//*[@id='slctPaymentTerms']/option[contains(text(),'" + paymentTerm + "')]");
	private By invoiceNoId = By.id("txtInvoiceNumber");
	private By invoiceDateImgXpath = By.xpath("//input[@name='txtInvoiceDate']/following-sibling::img");
	private By addItemImgXpath = By.xpath(".//*[@id='addMoreItems']//span[contains(text(),'Add Item')]");
	private By purchaseTypeXpath = By.xpath("//*[@id='slctPurchaseType']/option[contains(text(),'" + purchaseType + "')]");
	private By btnSubmitId = By.xpath("//a[@id='btnSubmit' and text()='Submit']");
	private By confirmationPopupXpath = By.xpath("//div[contains(@class,'workflowDialog ')]//input[contains(@class,'dev_submit ')]");
	private By successBoxId = By.xpath("//div[@id='hedaerSuccessBox']//li");*/

	public InvoiceNonPO(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.logger = logger;
		this.driver = driver;
	}

	public InvoiceNonPO(WebDriver driver, ExtentTest logger, String supplierName, String paymentTerm,
			String currency_value, String invoiceDate, String purchaseType, String description, String product_cat,
			String market_prc, String quantity, String GLType) {
		super(driver, logger);
		this.logger = logger;
		this.driver = driver;
		this.supplierName = supplierName;
		this.paymentTerm = paymentTerm;
		this.currency_value = currency_value;
		this.purchaseType = purchaseType;
		this.invoiceDate = invoiceDate;
		this.description = description;
		this.product_cat = product_cat;
		this.market_prc = market_prc;
		this.quantity = quantity;
		this.GLType = GLType;
	}

	/**
	 * <b>Function:</b> createNewInvoice
	 * 
	 * @author Anisha
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	/*public boolean createNewInvoice() {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String invoiceNo = String.valueOf(generateNo());
		try {
			enterText_AutoComplete(supplierNameId , supplierName);
			Thread.sleep(3000);
			findElement(paymentTermXpath).click();
			Thread.sleep(2000);
			selectAddress();
			Thread.sleep(2000);
			selectCurrency(currency_value);
			sendKeys(invoiceNoId , invoiceNo);
			WebElement elem = findElement(invoiceDateImgXpath);
			js.executeScript("arguments[0].click()", elem);
			selectDate_v1(invoiceDate);
			findElement(purchaseTypeXpath).click();
			addAttachment();
			scroll_into_view_element(
			findElement(addItemImgXpath ));
			findElement(addItemImgXpath).click();
			Thread.sleep(2000);
			add_item(description, product_cat, market_prc, quantity, GLType);
			Thread.sleep(2000);
			findElement(btnSubmitId).click();
			findElement(confirmationPopupXpath ).click();
			waitUntilVisibilityOfElement(successBoxId );
			logger.log(LogStatus.INFO, "created invoice nonPO : " + invoiceNo);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}*/
	
	public boolean createNewInvoice() {
		boolean result = false;
		try {
			result = createNewInvoiceOrCreditMemo(paymentTerm, purchaseType,
					supplierName, currency_value, invoiceDate, description,
					product_cat, market_prc, quantity, GLType)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
