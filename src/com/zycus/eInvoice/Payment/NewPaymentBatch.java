package com.zycus.eInvoice.Payment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class NewPaymentBatch extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.id("cntPayBatch");
	private String company;
	private String bankAcct;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public NewPaymentBatch(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param batchNo
	 * @param currency
	 * @param company
	 * @param bankAcct
	 * 
	 */
	
	//public NewPaymentBatch(WebDriver driver, ExtentTest logger, String batchNo, String currency, String company,
	public NewPaymentBatch(WebDriver driver, ExtentTest logger, String company,
			String bankAcct) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.company = company;
		this.bankAcct = bankAcct;
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
	
	/**
	 * <b>Function:</b> selectOrgUnit_and_Bank
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param None
	 * @return result - True/False
	 * @throws Exception
	 */
	
	public void selectOrgUnit_and_Bank() throws Exception {
		try {
			findElement(By.id("lnkOU")).click();
			Thread.sleep(2000);
			if (findElement(By.xpath("//div[div/span[text()='Select Organization Unit and Bank Details']]"))
					.isDisplayed()) {
				logger.log(LogStatus.INFO, "Company here is "+ company);
				findElement(By.id("txtOrganizationalUnit")).sendKeys(company);
				waitUntilInvisibilityOfElement(By.xpath("//div[contains(@aria-describedby,'status_overlay_loadingTree')]"));
				Thread.sleep(4000);
				findElement(By.xpath("//div[@id='diagCompanyUnitTree']/following-sibling::ul/li[text()='"+company+"']")).click();
				Thread.sleep(5000);
				if(findElement(By.xpath("//div[@class='treeContainer treeview']/ul"))!=null){
					//Selecting first organization unit from the displayed list tree
					findElement(By.xpath("//div[@class='treeContainer treeview']//li[1]//input")).click();
					findElement(By.xpath("//select[@id='txtBankName']/option[text()='"+bankAcct+"']")).click();
					try{
						findElement(By.xpath("//div[@id='diagCompanyUnitTree']//input[@value='Save']")).click();
					}catch(Exception e){
						if(findElement(By.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'Please select atleast one Organization Unit')]]")).isDisplayed())
							findElement(By.xpath("//button/span[text()='OK']")).click();
						logger.log(LogStatus.INFO, "Please select atleast one Organization Unit - message displayed");
					}
				}else
					logger.log(LogStatus.INFO, "No results found - message displayed");
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <b>Function:</b> enterBatchDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param scheduledFor
	 * @param approver
	 * @param notes
	 * @param reviewer
	 * @return result - True/False
	 * @throws Exception
	 */
	
	//public void enterBatchDetails(Date scheduledFor, String approver, String notes, String reviewer) throws Exception {
	public void enterBatchDetails(String approver, String notes, String reviewer) throws Exception {
		try {
			selectOrgUnit_and_Bank();
			/*findElement(By.id("txtBatchNo")).sendKeys(batchNo);
			findElement(By.id("txtCurrency")).sendKeys(currency);
			waitUntilInvisibilityOfElement(By.xpath("//input[contains(@class,'ui-autocomplete-loading')]"));
			Thread.sleep(2000);
			findElement(By.xpath("//ul[contains(@style,'block')]/li[text()='"+currency+"']")).click(); 
			Thread.sleep(10000);
			//selectDate(scheduledFor);
*/			if(approver==""){
				findElement(By.id("txtApprover")).clear();
				findElement(By.id("txtApprover")).sendKeys(approver);
				waitUntilInvisibilityOfElement(By.xpath("//input[contains(@class,'ui-autocomplete-loading')]"));
				Thread.sleep(2000);
				findElement(By.xpath("//ul[contains(@style,'block')]/li/a/span/span[text()='"+approver+"']")).click(); 	
			}
			findElement(By.id("txtBatchComments")).sendKeys(notes);
			if(reviewer==""){
				findElement(By.id("txtReviewer")).clear();
				findElement(By.id("txtReviewer")).sendKeys(reviewer);
				waitUntilInvisibilityOfElement(By.xpath("//input[contains(@class,'ui-autocomplete-loading')]"));
				Thread.sleep(2000);
				findElement(By.xpath("//ul[contains(@style,'block')]/li/a/span/span[text()='"+reviewer+"']")).click(); 
			}
			Thread.sleep(2000);
			//Add Voucher
			if(addVoucher())
				Thread.sleep(6000);
				findElement(By.id("btnSubmit")).click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public boolean addVoucher() throws Exception{
		boolean result = false;
		try{
			findElement(By.id("addVoucher")).click();
			waitUntilInvisibilityOfElement(By.id("status_overlay_savePaymentBatch"));
			try{
				if (driver.findElement(By.xpath("//label[contains(text(),'Duplicate batch number')]")) != null){
					String batchNo = String.valueOf(generateNo());
					WebElement objBatchNum = findElement(By.id("txtBatchNo"));
					objBatchNum.clear();
					objBatchNum.sendKeys(batchNo);
					//addVoucher();
					findElement(By.id("addVoucher")).click();
					waitUntilInvisibilityOfElement(By.id("status_overlay_savePaymentBatch"));
				}
			}catch(Exception ex){}
			NewVoucher objVoucher = new NewVoucher(driver, logger, "GDQA_SUPPLIER","chk123","voucher123");
			if(objVoucher.getPgHead()!=null){
				logger.log(LogStatus.INFO, "Navigated to 'Create New Voucher' page");
				//objVoucher.createNewVoucher(voucherDate, supplierEmail, description, invoiceNo);
				objVoucher.createNewVoucher("abc@zycus.com", "desc", "123abc4");
				result = true;
			}
			else
				logger.log(LogStatus.INFO, "Not Navigated to 'Create New Voucher' page");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
