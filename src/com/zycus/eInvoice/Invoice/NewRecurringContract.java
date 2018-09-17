package com.zycus.eInvoice.Invoice;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class NewRecurringContract extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private String searchValue;
	private String paymentTerms;
	private String contractName;
	private String contractOwner;
	private String autoInvoiceNo;
	private String purchaseType;
	private String frequencyNo;
	private String frequency;
	private Date fromDt;
	private String weekDay;
	private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public NewRecurringContract(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param searchValue
	 * @param paymentTerms
	 * @param contractName
	 * @param contractOwner
	 * @param autoInvoiceNo
	 * @param purchaseType
	 * @param frequencyNo
	 * @param frequency
	 * @param fromDt
	 * @param weekDay
	 * 
	 */
	
	public NewRecurringContract(WebDriver driver, ExtentTest logger, String searchValue, String paymentTerms, String contractName,
			String contractOwner, String autoInvoiceNo, String purchaseType, String frequencyNo, String frequency,
			Date fromDt, String weekDay) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.searchValue = searchValue;
		this.paymentTerms = paymentTerms;
		this.contractName = contractName;
		this.contractOwner = contractOwner;
		this.autoInvoiceNo = autoInvoiceNo;
		this.purchaseType = purchaseType;
		this.frequencyNo = frequencyNo;
		this.frequency = frequency;
		this.fromDt = fromDt;
		this.weekDay = weekDay;
	}
	
	/**
	 * <b>Function:</b> enterSupplierInfo
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param searchType
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean enterSupplierInfo(String searchType) throws Exception {
		boolean result = false;
		try {
			WebElement paymentTerms = findElement(By.xpath("//select[@id='slctPaymentTerms'][@disabled]"));
			WebElement address = findElement(By.xpath("//select[@id='slctSupplierAddress'][@disabled]"));
			WebElement remitToAddr = findElement(By.xpath("//select[@id='slctSupplierAddressRemit'][@disabled]"));
			WebElement currency = findElement(By.xpath("//select[@id='txtSupplierCurrency'][@disabled]"));
			WebElement suppContact = findElement(By.xpath("//select[@id='txtSupplierContact'][@disabled]"));

			findElement(By.xpath("//*[@id='supplierNameDropdown']/div/a[span[text()='" + searchType + "']]")).click();
			findElement(By.id("txtSupplierName")).sendKeys(searchValue);
			if (findElement(By.xpath("//form[@id='frmInvoice']/ul[contains(@style,'block')]")) != null) {
				findElement(By.xpath("//form[@id='frmInvoice']/ul[contains(@style,'block')]/li[1]")).click();
				Assert.assertNull(paymentTerms);
				Assert.assertNull(address);
				Assert.assertNull(remitToAddr);
				Assert.assertNull(currency);
				Assert.assertNull(suppContact);
				findElement(By.xpath("//select[@id='slctPaymentTerms']/option[text()='" + paymentTerms + "']")).click();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>Function:</b> enterContractDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param buyer
	 * @param requester
	 * @param notes
	 * @param description
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean enterContractDetails(String buyer, String requester, String notes, String description)
			throws Exception {
		boolean result = false;
		try {
			findElement(By.id("txtContractName")).sendKeys(contractName);
			findElement(By.id("txtBuyer")).sendKeys(buyer);
			findElement(By.id("txtContractOwner")).sendKeys(contractOwner);
			findElement(By.id("txtRequester")).sendKeys(requester);
			findElement(By.id("txtAutoInvoiceNo")).sendKeys(autoInvoiceNo);
			findElement(By.xpath("//select[@id='slctPurchaseType']/option[text()='" + purchaseType + "']")).click();
			findElement(By.id("txtInvoiceComments")).sendKeys(notes);
			findElement(By.id("txtDescription")).sendKeys(description);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>Function:</b> enterReleaseSchedule
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @return result - True/False
	 * @throws Exception
	 */
	
	public boolean enterReleaseSchedule() throws Exception {
		boolean result = false;
		try {
			findElement(By.id("txtFrequencyOccurance")).sendKeys(frequencyNo);
			findElement(By.xpath("//select[@id='txtFrequencyBy']/option[text()='" + frequency + "']")).click();
			findElement(By.id("txtFromDate")).click();
			selectDate(fromDt);
			findElement(By.id("txtNeverExpires")).click();
			findElement(By.xpath("//select[@id='txtDayOfWeek']/option[text()='weekDay']")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>Function:</b> enterReleaseSchedule
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param ToDt
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean enterReleaseSchedule(Date ToDt) throws Exception {
		boolean result = false;
		try {
			findElement(By.id("txtFrequencyOccurance")).sendKeys(frequencyNo);
			findElement(By.xpath("//select[@id='txtFrequencyBy']/option[text()='" + frequency + "']")).click();
			findElement(By.id("txtFromDate")).click();
			selectDate(fromDt);
			findElement(By.id("txtToDate")).click();
			selectDate(ToDt);
			findElement(By.xpath("//select[@id='txtDayOfWeek']/option[text()='weekDay']")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>Function:</b> editBillingCostBookingInfo
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param billingCompany
	 * @param billingBusinessUnit
	 * @param billingLocation
	 * @param CBCostCenter
	 * @param CBProject
	 * @return result - True/False
	 * @throws Exception
	 */

	//INCOMPLETE FUNCTION
	/*public boolean editBillingCostBookingInfo(String billingCompany, String billingBusinessUnit, String billingLocation,
			String CBCostCenter, String CBProject) throws Exception {
		boolean result = false;
		try {
			findElement(By.id("changeDelBillingSumm")).click();
			ContractSummary objContractSumm = new ContractSummary(driver, logger, billingCompany, billingBusinessUnit,
					billingLocation, CBCostCenter, CBProject);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

}
