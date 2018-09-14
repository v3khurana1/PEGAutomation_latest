package com.zycus.eInvoice.Invoice;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.eInvoice.PO.PurchaseOrder;

import common.Functions.eInvoice_CommonFunctions;

public class Invoices extends eInvoice_CommonFunctions {

	private ExtentTest logger;
	private WebDriver driver;

	private String invoiceDate;
	private String invoiceNo;
	private String supplierName;
	private String paymentTerm;
	private String purchaseType;
	private String currency_value;
	private String description;
	private String product_cat;
	private String market_prc;
	private String quantity;
	private String creditMemoDate;
	private String GLType;
	
	private By processingLoader = By.id("invoicelisting_processing");
	//private By tableXpath = By.xpath("//table[@id='invoicelisting']");
	//private By docTypeXpath = By.xpath("//table[@id='invoicelisting']//tr[*]/td[3]");
	private By statusXpath = By.xpath("//table[@id='invoicelisting']//td[2]/div");
	private By docDateXpath = By.xpath("//table[@id='invoicelisting']//td[contains(@class,'invoiceDate')]");
	private By docDueDateXpath = By.xpath("//table[@id='invoicelisting']//td[contains(@class,'invoiceDueDate')]");
	private By amountXpath = By.xpath("//table[@id='invoicelisting']//td[contains(@class,'totalAmountReq')]");
	private By filterBtnXpath = By.xpath(
			"//div[contains(@id,'qtip') and @aria-hidden='false']//div[contains(@class,'FilterBtnbx')]//a[text()='Filter']");
	private By paginateXpath = By.xpath("//*[@id='invoicelisting_paginate']//input[@name='text']");
	private By saveFavId = By.id("saveFavorite");
	private By favFilterPopupId = By.id("favouriteFilterPopup");
	private By favContinueId = By.id("favourite-continue");
	private By revertFavId = By.id("revertFavorite");
	private By voidInvoiceCommentId = By.id("txtInvoiceCancelComment");
	private By closeInvoiceCommentId = By.id("txtInvoiceCloseComment");
	private By returnInvoiceCommentId = By.id("txtInvoiceReturnComment");
	private By adjustAmtCommentId = By.id("txtAdjustAmountComment");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Invoices(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	/**
	 * Constructor for the class
	 * @param driver
	 * @param logger
	 * @param supplierName
	 * @param purchaseType
	 * @param creditMemoDate
	 * @param currency_value
	 * @param description
	 * @param product_cat
	 * @param market_prc
	 * @param quantity
	 * @param GLType
	 */

	// for CM without reference
	public Invoices(WebDriver driver, ExtentTest logger, String supplierName, String currency_value,
			String creditMemoDate, String purchaseType, String description, String product_cat, String market_prc,
			String quantity, String GLType) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.supplierName = supplierName;
		this.purchaseType = purchaseType;
		this.creditMemoDate = creditMemoDate;
		this.currency_value = currency_value;
		this.description = description;
		this.product_cat = product_cat;
		this.market_prc = market_prc;
		this.quantity = quantity;
		this.GLType = GLType;
	}
	
	/**
	 * Constructor for the class
	 * @param driver
	 * @param logger
	 * @param supplierName
	 * @param invoiceDate
	 * @param paymentTerm
	 * @param purchaseType
	 * @param currency_value
	 * @param description
	 * @param product_cat
	 * @param market_prc
	 * @param quantity
	 * @param GLType
	 */

	// for nonPO
	public Invoices(WebDriver driver, ExtentTest logger, String supplierName, String paymentTerm, String currency_value,
			String invoiceDate, String purchaseType, String description, String product_cat, String market_prc,
			String quantity, String GLType) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.invoiceDate = invoiceDate;
		this.supplierName = supplierName;
		this.paymentTerm = paymentTerm;
		this.purchaseType = purchaseType;
		this.currency_value = currency_value;
		this.description = description;
		this.product_cat = product_cat;
		this.market_prc = market_prc;
		this.quantity = quantity;
		this.GLType = GLType;
	}

	/*
	 * public boolean viewList() { boolean status = false; try { String docType;
	 * if (findElement(tableXpath).isDisplayed()) {
	 * 
	 * docType = findElement(docTypeXpath).getText(); if
	 * (docType.contains("Invoice") || docType.contains("CreditMemo"))
	 * logger.log(LogStatus.INFO, "list is displayed"); status = true; } } catch
	 * (Exception e) { } return status; }
	 */

	/**
	 * <b>Function:</b> changeNoOfRecordsPerPage
	 * 
	 * @author Anisha
	 * @param noOfRecord
	 * @since May 2018
	 * @throws Exception
	 * @return status
	 */
	public boolean changeNoOfRecordsPerPage(int noOfRecord) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//select[@name='invoicelisting_length']/option[text()='" + noOfRecord + "']"))
					.click();
			// TODO add validation
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * <b>Function:</b> navigateToPageNo
	 * 
	 * @author Anisha
	 * @param pageNo
	 * @since May 2018
	 * @throws Exception
	 * @return status
	 */
	public boolean navigateToPageNo(String pageNo) throws Exception {
		boolean result = false;
		try {
			findElement(paginateXpath).clear();
			sendKeys(paginateXpath, pageNo + Keys.ENTER);
			waitUntilInvisibilityOfElement(processingLoader);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * <b>Function:</b> saveViewAsFavorite
	 * 
	 * @author Anisha
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean saveViewAsFavorite() {
		boolean result = false;
		try {
			findElement(saveFavId).click();
			if (findElement(favFilterPopupId).isDisplayed())
				findElement(favContinueId).click();
			// TODO add wait until invisible - processing element
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> revertToDefaultView
	 * 
	 * @author Anisha
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean revertToDefaultView() {
		boolean result = false;
		try {
			findElement(revertFavId ).click();
			findElement(By.xpath("//div/button[contains(@class,'pri')]")).click();
			// TODO page loading
			if (!findElement(revertFavId).isDisplayed())
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> editInvoice
	 * 
	 * @author Anisha
	 * @param none
	 * @since May 2018
	 * @throws Exception
	 * @return status
	 */
	public boolean editInvoice() throws Exception {
		boolean result = false;
		try {
			filterByDocType("Invoice");
			filterByStatus("Draft");
			selectActionInvoice("Edit");
			PurchaseOrder objPO = new PurchaseOrder(driver, logger);
			objPO.editInvoice();
			if (findElement(objPO.getPOHeader()).getText() == "Purchase Orders") {
				logger.log(LogStatus.INFO, "Redirected to Purchase Orders page");
				result = true;
			} else
				logger.log(LogStatus.INFO, "Not Redirected to Purchase Orders page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * <b>Function:</b> createInvoiceagainstPO
	 * 
	 * @author Anisha
	 * @param invoiceNo
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean createInvoiceagainstPO() throws Exception {
		boolean result = false;
		try {
			addInvoiceOrCreditMemo("Invoice", "Against PO");
			findElement(By.xpath("//div/button[contains(@class,'pri')]/span[text()='Continue']")).click();
			PurchaseOrder objPO = new PurchaseOrder(driver, logger, invoiceNo, invoiceDate);
			objPO.addInvoice();
			if (findElement(objPO.getPOHeader()).getText() == "Purchase Orders") {
				logger.log(LogStatus.INFO, "Redirected to Purchase Orders page");
				result = true;
			} else
				logger.log(LogStatus.INFO, "Not Redirected to Purchase Orders page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> createInvoiceNonPO
	 * 
	 * @author Anisha
	 * @param invoiceNo
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean createInvoiceNonPO() throws Exception {
		boolean result = false;
		try {
			addInvoiceOrCreditMemo("Invoice", "Non-PO");
			InvoiceNonPO objInv = new InvoiceNonPO(driver, logger, supplierName, paymentTerm, currency_value,
					invoiceDate, purchaseType, description, product_cat, market_prc, quantity, GLType);
			objInv.createNewInvoice();
			if (findElement(By.xpath("//h1[@class='pgHead' and text()='Invoice']")).isDisplayed()) {
				logger.log(LogStatus.INFO, "Navigated to create Invoice page");
				result = true;
			} else
				logger.log(LogStatus.INFO, "unable to navigate to create Invoice page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * <b>Function:</b> createCreditMemoagainstPO
	 * 
	 * @author Anisha
	 * @param invoiceNo
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean createCreditMemoagainstPO() throws Exception {
		boolean result = false;
		try {
			addInvoiceOrCreditMemo("Credit Memo", "Against PO");
			findElement(By.xpath("//div/button[contains(@class,'pri')]/span[text()='Continue']")).click();
			PurchaseOrder objPO = new PurchaseOrder(driver, logger, invoiceNo, invoiceDate);
			objPO.addCreditMemo();
			if (findElement(objPO.getPOHeader()).getText() == "Purchase Orders") {
				logger.log(LogStatus.INFO, "Redirected to Purchase Orders page");
				result = true;
			} else
				logger.log(LogStatus.INFO, "Not Redirected to Purchase Orders page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * <b>Function:</b> createCreditMemowithoutReference
	 * 
	 * @author Anisha
	 * @param
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean createCreditMemowithoutReference() throws Exception {
		boolean result = false;
		try {
			addInvoiceOrCreditMemo("Credit Memo", "Without Reference");
			CreditMemowithoutReference objCreditMemo = new CreditMemowithoutReference(driver, logger, supplierName,
					currency_value, creditMemoDate, purchaseType, description, product_cat, market_prc, quantity,
					GLType);
			objCreditMemo.createCreditMemo();
			if (findElement(By.xpath("//*[@id='cntInvoice']//span[text()='Credit Memo']")).isDisplayed()) {
				logger.log(LogStatus.INFO, "Navigated to Invoice => Credit Memo page");
				result = true;
			} else
				logger.log(LogStatus.INFO, "unable to navigate to Invoice => Credit Memo page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> user is able to Void/ Close/ Return/ Adjust Credits/
	 * Restrict Payment of an invoice.
	 * 
	 * @author Anisha
	 * @param
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return status
	 */
	public boolean takeActionOnInvoice(String action) {
		boolean result = false;
		try {
			// filterByStatus("Approved");
			selectActionInvoice(action);
			Thread.sleep(2000);
			switch (action) {
			case "Void Invoice":
				sendKeys(voidInvoiceCommentId , "void invoice comment");
				findElement(By.xpath("//input[contains(@class,'invoiceCancel')]")).click();
				waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_cancellingInvoice']/div"));
				result = true;
				break;
			case "Close":
				sendKeys(closeInvoiceCommentId , "close invoice comment");
				findElement(By.xpath("//input[contains(@class,'invoiceClose')]")).click();
				waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_closingInvoice']/div"));
				result = true;
				break;
			case "Return":
				sendKeys(returnInvoiceCommentId , "return invoice comment");
				findElement(By.xpath("//input[contains(@class,'invoiceReturn')]")).click();
				waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_returnInvoice']/div"));
				result = true;
				break;
			case "Adjust Credit":
				break;
			case "Restrict Payment":
				sendKeys(adjustAmtCommentId , "restrict payment comment");
				findElement(By.xpath("//input[contains(@class,'adjustAmount')]")).click();
				waitUntilInvisibilityOfElement(By.xpath("//*[@id='status_overlay_adjustingAmount']/div"));
				result = true;
				break;
			case "Edit":
				break;
			case "Resend for integration":
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addInvoiceOrCreditMemo
	 * 
	 * @author Anisha
	 * @param addItem,subItem
	 * @since May 2018
	 * @param none
	 * @throws Exception
	 * @return result
	 */
	public boolean addInvoiceOrCreditMemo(String addItem, String subItem) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//*[@id='wrapper']//a[span[2][text()='Add']]")).click();
			findElement(
					By.xpath("//*[@id='wrapper']//a[span[2][text()='Add']]/following-sibling::div/div/div/span[text()='"
							+ addItem + "']")).click();
			findElement(
					By.xpath("//*[@id='wrapper']//a[span[2][text()='Add']]/following-sibling::div/div/div[span[text()='"
							+ addItem + "']]//a[span[text()='" + subItem + "']]")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addInvoiceOrCreditMemo
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param addItem
	 * @param subItem
	 * @return result - True/False
	 * @throws Exception
	 */

	/*
	 * public boolean addInvoiceOrCreditMemo(String addItem, String subItem)
	 * throws Exception { boolean result = false; try {
	 * click(By.xpath("//*[@id='wrapper']//a[span[2][text()='Add']]"));
	 * click(By.xpath(
	 * "//*[@id='wrapper']//a[span[2][text()='Add']]/following-sibling::div/div/div/span[text()='"
	 * + addItem + "']")); click(By.xpath(
	 * "//*[@id='wrapper']//a[span[2][text()='Add']]/following-sibling::div/div/div[span[text()='"
	 * + addItem + "']]//a[span[text()='" + subItem + "']]")); try { if
	 * (subItem.equals("Against PO")) findElement(By .xpath(
	 * "//div[contains(@class,'iConfirmBox')][//td[contains(text(),'You will be redirected to PO Listing')]]//span[text()='Continue']"
	 * )) .click(); PurchaseOrder objPO = new PurchaseOrder(driver, logger); if
	 * (findElement(objPO.getPOHeader()).getText() == "Purchase Orders")
	 * System.out.println("Redirected to Purchase Orders page"); else
	 * System.out.println("Not Redirected to Purchase Orders page"); } catch
	 * (Exception e) { System.out.println(
	 * "You will be directed to PO listing pop up not displayed"); } } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * return result; }
	 */

	/**
	 * <b>Function:</b> filterByStatus
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param checkBoxLbl
	 * @return result - True/False
	 * @throws Exception
	 */

	/*public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			WebElement objElem = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//th[contains(@class,'invstatusFltrHdr')]//b")));
			// findElement(By.xpath("//th[contains(@class,'invstatusFltrHdr')]//b")).click();
			objElem.click();
			filterByChkbox(checkBoxLbl);
			waitUntilInvisibilityOfElement(processingLoader);
			// if
			// (findElement(processingLoader).getAttribute("style").contains("block"))
			// {
			List<WebElement> objfilteredList = driver.findElements(statusXpath);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(checkBoxLbl))
					result = true;
				else {
					result = false;
					break;
				}
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean filterByStatus(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invstatusFltrHdr')]//b")).click();
			result = filterByChkbox(checkBoxLbl, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByDate
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 * @throws ParseException
	 */

	/*private boolean filterByDate(Date fromDt, Date ToDt) throws ParseException {
		boolean result = false;
		try {
			filterByDateRange(fromDt, ToDt);
			waitUntilInvisibilityOfElement(processingLoader);
			// if
			// (findElement(processingLoader).getAttribute("style").contains("block"))
			// {
			List<WebElement> objfilteredDateList = driver.findElements(dateXpath);
			for (WebElement obj : objfilteredDateList) {
				DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
				Date dt = format.parse(obj.getText());
				if (dt.compareTo(fromDt) >= 0 && dt.compareTo(ToDt) <= 0)
					result = true;
				else {
					result = false;
					break;
				}
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	/**
	 * <b>Function:</b> filterByDocumentDt
	 * 
	 * @author Varun Khurana
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByDocumentDt(Date fromDt, Date ToDt) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invdateFltrHdr')]//b")).click();
			result = filterByDateRange(fromDt, ToDt, docDateXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByDueDt
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByDueDt(Date fromDt, Date ToDt) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invduedateFltrHdr')]//b")).click();
			result = filterByDateRange(fromDt, ToDt, docDueDateXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmountRange
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @param currType
	 * @return result - True/False
	 * @throws ParseException
	 */

	/*private boolean filterByAmountRange(float fromAmt, float ToAmt, String currType) throws ParseException {
		boolean result = false;
		try {
			filterByAmtRange(fromAmt, ToAmt, currType);
			waitUntilInvisibilityOfElement(processingLoader);
			// if
			// (findElement(processingLoader).getAttribute("style").contains("block"))
			// {
			List<WebElement> objfilteredAmtList = driver.findElements(amountXpath);
			for (WebElement obj : objfilteredAmtList) {
				Float amount = Float.parseFloat((obj.getText().split(" "))[1]);
				String currencyType = null;
				if (amount >= fromAmt && amount <= ToAmt) {
					if (currType != "")
						currencyType = (obj.getText().split(" "))[0];
					if (currencyType == currType)
						result = true;
					else
						result = true;
				} else {
					result = false;
					break;
				}
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	/**
	 * <b>Function:</b> filterByAmount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invamountFltrHdr')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @param currType
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt, String currType) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invamountFltrHdr')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, currType, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByPaidAmount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByPaidAmount(float fromAmt, float ToAmt) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invpaidamountFltrHdr')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByPaidAmount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param fromAmt
	 * @param ToAmt
	 * @param currType
	 * @return result - True/False
	 * @throws None
	 */

	public boolean filterByPaidAmount(float fromAmt, float ToAmt, String currType) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invpaidamountFltrHdr')]//b")).click();
			result = filterByAmtRange(fromAmt, ToAmt, currType, amountXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterBySupplier
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param supplier
	 * @return result - True/False
	 * @throws ParseException
	 */

	/*public boolean filterBySupplier(String supplier) throws ParseException {
		boolean result = false;
		try {
			// modified by Anisha in try block
			sendKeys(By.id("txtFltrSupplier"), supplier);
			Thread.sleep(3000);
			findElement(By.xpath("//ul[contains(@style,'block')]//a")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			// int intColNo = getColNum("Supplier");
			List<WebElement> objfilteredTxtList = driver.findElements(By.xpath("//tbody//td[4]"));
			for (WebElement obj : objfilteredTxtList) {
				if (obj.getText().contains(supplier))
					result = true;
				else {
					result = false;
					break;
				}
				// result = filterByText("Supplier", supplier) ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	
	public boolean filterBySupplier(String supplier) throws ParseException {
		boolean result = false;
		try {
			result = filterByText_AutoComplete("Supplier", supplier) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByDocType
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param checkBoxLbl
	 * @return result - True/False
	 * @throws Exception
	 */

	/*public boolean filterByDocType(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			waitUntilInvisibilityOfElement(processingLoader);
			// Thread.sleep(3000);
			findElement(By.xpath("//th[contains(@class,'docTypeFltrHdr')]//b")).click();
			System.out.println("checkbox here is " + checkBoxLbl);
			filterByChkbox(checkBoxLbl);
			waitUntilInvisibilityOfElement(processingLoader);
			// if
			// (findElement(processingLoader).getAttribute("style").contains("block"))
			// {
			List<WebElement> objfilteredList = driver.findElements(statusXpath);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(checkBoxLbl))
					result = true;
				else {
					result = false;
					break;
				}
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean filterByDocType(String checkBoxLbl) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'docTypeFltrHdr')]//b")).click();
			result = filterByChkbox(checkBoxLbl, statusXpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByReference
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param checkBoxLbl
	 * @param optionTxt
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean filterByReference(String referenceOption, String optionTxt) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'invPoNumFltrHdr')]//b")).click();
			if (referenceOption == "PO") {
				findElement(By.id("txtFltrWithPo")).click();
				sendKeys(By.id("txtFltrPoNum"), optionTxt);
			} else
				findElement(By.id("txtFltrWithoutPo")).click();
			findElement(filterBtnXpath).click();
			waitUntilInvisibilityOfElement(processingLoader);
			List<WebElement> objfilteredList = driver.findElements(statusXpath);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(referenceOption))
					result = true;
				else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
