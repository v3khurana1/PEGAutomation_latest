package com.zycus.eInvoice.Payment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class NewVoucher extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	private ExtentTest logger;
	private String supplier;
	private String chkNo;
	private String voucherNo;
	private By pgHead = By.xpath("//h1[@class='pgHead']/span[text()='Create new Voucher']");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public NewVoucher(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param supplier
	 * @param chkNo
	 * @param voucherNo
	 * 
	 */

	public NewVoucher(WebDriver driver, ExtentTest logger, String supplier, String chkNo, String voucherNo) {
		super(driver, logger);
		//this.driver = driver;
		this.logger = logger;
		this.supplier = supplier;
		this.chkNo = chkNo;
		this.voucherNo = voucherNo;
	}

	/**
	 * <b>Function:</b> createNewVoucher
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param voucherDate
	 * @param supplierEmail
	 * @param description
	 * @param invoiceNo
	 * @return result - True/False
	 * @throws None
	 */

	// Incomplete as Voucher line is not getting created

	// public boolean createNewVoucher(Date voucherDate, String supplierEmail,
	// String description, String invoiceNo) {
	public boolean createNewVoucher(String supplierEmail, String description, String invoiceNo) {
		boolean result = false;
		try {
			// enterVoucherDetails(voucherDate, supplierEmail, description);
			enterVoucherDetails(supplierEmail, description);
			try {
				createVoucherLine(invoiceNo);
				/*findElement(By.id("btnAddVoucher")).click();
				waitUntilInvisibilityOfElement(By.id("status_overlay_updateVoucher"));*/
				clickAndWaitUntilLoaderDisappears(By.id("btnAddVoucher"), By.id("status_overlay_updateVoucher"));
				Thread.sleep(5000);
				try{
					if (findElement(By.xpath("//label[contains(text(),'Duplicate voucher no')]")) != null){
						String voucherNo = String.valueOf(generateNo());
						WebElement objVoucherNum = findElement(By.id("txtVoucherNumber"));
						objVoucherNum.clear();
						objVoucherNum.sendKeys(voucherNo);
						/*findElement(By.id("btnAddVoucher")).click();
						waitUntilInvisibilityOfElement(By.id("status_overlay_updateVoucher"));*/
						clickAndWaitUntilLoaderDisappears(By.id("btnAddVoucher"), By.id("status_overlay_updateVoucher"));
					}
				}catch(Exception ex){}
				result = true;
			} catch (Exception e) {
				if (findElement(By
						.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'Please create atleast one voucher line')]]"))
								.isDisplayed())
					findElement(By.xpath("//button/span[text()='OK']")).click();
				logger.log(LogStatus.INFO, "Voucher Line not created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> enterVoucherDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param voucherDate
	 * @param supplierEmail
	 * @param description
	 * @return result - True/False
	 * @throws None
	 */

	// private void enterVoucherDetails(Date voucherDate, String supplierEmail,
	// String description) throws Exception {
	private void enterVoucherDetails(String supplierEmail, String description) throws Exception {
		try {
			/*findElement(By.id("txtSupplier")).sendKeys(supplier);
			waitUntilInvisibilityOfElement(By.xpath("//input[contains(@class,'ui-autocomplete-loading')]"));*/
			clickAndWaitUntilLoaderDisappears(By.id("txtSupplier"), By.xpath("//input[contains(@class,'ui-autocomplete-loading')]"));
			Thread.sleep(8000);
			findElement(By.xpath("//ul[contains(@style,'block')]/li/a//span[text()='" + supplier + "']")).click();
			Thread.sleep(3000);
			// selectDate(voucherDate);
			findElement(By.id("txtSupplierEmail")).sendKeys(supplierEmail);
			findElement(By.id("txtRefNo")).sendKeys(chkNo);
			findElement(By.id("txtVoucherNumber")).sendKeys(voucherNo);
			findElement(By.id("txtDescription")).sendKeys(description);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * <b>Function:</b> createVoucherLine
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param invoiceNo
	 * @return result - True/False
	 * @throws Exception
	 */

	private void createVoucherLine(String invoiceNo) throws Exception {
		try {
			findElement(By.id("lnkSelectInvoices")).click();
		} catch (Exception e) {
			if (findElement(By.xpath(
					"//div[contains(@class,'promptbx')][//td[contains(text(),'Please select a valid supplier')]]")) != null)
				findElement(By.xpath("//button/span[text()='OK']")).click();
			findElement(By.id("txtSupplier")).sendKeys(supplier);
		}
		try {
			Thread.sleep(2000);
			if (findElement(By.xpath("//div[div/span[text()='Create Voucher Lines']]")).isDisplayed()) {
				findElement(By.xpath("//table[@id='invoiceListing']//tr[1]/td[1]/input")).click();
				findElement(By.id("btnSelectInvoices")).click();
			} else
				logger.log(LogStatus.INFO, "Create Voucher Lines dialog not displayed");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
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

}
