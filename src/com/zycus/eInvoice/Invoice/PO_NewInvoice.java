package com.zycus.eInvoice.Invoice;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

public class PO_NewInvoice extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	private String invoiceNo;
	private Date invoiceDt;
	private Date invoiceDueDt;
	private By invoiceHeader = By.xpath("//h1[@class='pgHead']/span[1]");
	//private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public PO_NewInvoice(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param invoiceNo
	 * @param invoiceDt
	 * 
	 */

	public PO_NewInvoice(WebDriver driver, ExtentTest logger, String invoiceNo, Date invoiceDt) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
		this.invoiceNo = invoiceNo;
		this.invoiceDt = invoiceDt;
	}

	/**
	 * <b>Function:</b> takeAction
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param action
	 * @return result - True/False
	 * @throws None
	 */

	// Need to complete
	public boolean takeAction(String action, PO_NewInvoice objCreateInvoice) {
		boolean result = false;
		try {
			findElement(By.xpath("//*[@id='actDrop']/span")).click();
			switch (action) {
			case "Preview PO":
				break;
			case "Print":
				break;
			case "Download as PDF":
				break;
			case "Email PO":
				break;
			case "Add Invoice":
				//PO_NewInvoice objCreateInvoice = new PO_NewInvoice(driver, logger);
				String invoiceHeader = findElement(objCreateInvoice.getInvoiceHeader()).getText();
				if (findElement(objCreateInvoice.getInvoiceHeader()).getText().equals(invoiceHeader))
					result = true;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> enterCreditMemoDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param buyer
	 * @param notes
	 * @param invoiceDesc
	 * @return result - True/False
	 * @throws None
	 */

	public boolean enterCreditMemoDetails(String buyer, String notes, String invoiceDesc) {
		boolean result = false;
		try {
			findElement(By.id("txtInvoiceNumber")).sendKeys(invoiceNo);
			WebElement objBuyer = findElement(By.id("txtBuyer"));
			objBuyer.clear();
			objBuyer.sendKeys(buyer);
			findElement(By.id("txtInvoiceDate")).click();
			selectDate(invoiceDt);
			findElement(By.id("txtInvoiceDueDate")).click();
			selectDate(invoiceDueDt);
			findElement(By.id("txtInvoiceComments")).sendKeys(notes);
			findElement(By.id("txtDescription")).sendKeys(invoiceDesc);
			// Add Attachments need to be added
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> enterSupplierInfo
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param currExchRate
	 * @param remitAddr
	 * @return result - True/False
	 * @throws None
	 */

	public boolean enterSupplierInfo(int currExchRate, String remitAddr) {
		boolean result = false;
		try {
			findElement(By.id("txtBaseExchangeRate")).sendKeys(String.valueOf(currExchRate));
			WebElement objRemitAddr = findElement(By.id("slctSupplierAddressRemit"));
			objRemitAddr.clear();
			objRemitAddr.sendKeys(remitAddr);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> enterItemDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param mktPrice
	 * @param creditQuantity
	 * @return result - True/False
	 * @throws None
	 */

	public boolean enterItemDetails(float mktPrice, int creditQuantity) {
		boolean result = true;

		WebElement objMktPrice = null;
		WebElement objCreditQty = null;
		try {
			findElement(By.xpath("//*[@id='ngInvoiceItems']/tr[1]/td[1]/input")).click();
			try {
				objMktPrice = findElement(
						By.xpath("//*[@id='ngInvoiceItems']/tr[1]//input[contains(@id,'txtMarketPrice')][@disabled]"));
				result = false;
			} catch (Exception e) {
				System.out.println("Market price field is enabled");
				objMktPrice.sendKeys(String.valueOf(mktPrice));
			}
			try {
				objCreditQty = findElement(
						By.xpath("//*[@id='ngInvoiceItems']/tr[1]//input[contains(@id,'txtInvoicedQty')][@disabled]"));
				result = false;
			} catch (Exception e) {
				System.out.println("Credit Qty/Amt field is disabled");
				objCreditQty.sendKeys(String.valueOf(creditQuantity));
			}

			WebElement objTotalprice = findElement(
					By.xpath("//*[@id='ngInvoiceItems']/tr[1]//span[contains(@id,'invoicedTotal')]"));
			objTotalprice.click();
			Float price = Float.parseFloat(objTotalprice.getText().split(" ", 2)[1]);
			if (mktPrice * creditQuantity != price) {
				System.out.println("Total price calculated is incorrect");
				result = false;
			}

			WebElement objTotal = findElement(By.xpath("//*[@id='vng-collapsibleGrid']/tfoot//span"));
			if (objTotalprice.getText() != objTotal.getText()) {
				System.out.println("Total price of all items calculated is incorrect");
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> modifyTotalDiscount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param discountAmt
	 * @return result - True/False
	 * @throws None
	 */

	public boolean modifyTotalDiscount(float discountAmt) {
		boolean result = false;
		try {
			findElement(By.id("modifyDiscount")).click();
			if (findElement(By.xpath("//div[@aria-describedby='dev_discountDialog']")).isDisplayed()) {
				findElement(By.id("dev_headerLevelRadio")).click();
				findElement(By.id("dev_txtHeaderAmount")).sendKeys(String.valueOf(discountAmt));
				findElement(By.id("dev_saveDiscounts")).click();
				if (Float.parseFloat(findElement(By.id("discountedPrice")).getText().split(" ", 2)[1]) == discountAmt)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> modifyTotalDiscount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param discountCategory
	 * @param discountPercent
	 * @return result - True/False
	 * @throws None
	 */

	public boolean modifyTotalDiscount(String discountCategory, float discountPercent) {
		boolean result = false;
		try {
			findElement(By.id("modifyDiscount")).click();
			if (findElement(By.xpath("//div[@aria-describedby='dev_discountDialog']")).isDisplayed()) {
				findElement(By.id("dev_itemLevelRadio")).click();
				findElement(By.id("dev_applyToAllRate")).sendKeys(String.valueOf(discountPercent));
				findElement(By.xpath("//*[@id='dev_itemLevelDiscountTable']//a[text()='Apply to all']")).click();
				findElement(By.id("dev_saveDiscounts")).click();
				WebElement objTotalprice = findElement(
						By.xpath("//*[@id='ngInvoiceItems']/tr[1]//span[contains(@id,'invoicedTotal')]"));
				Float price = Float.parseFloat(objTotalprice.getText().split(" ", 2)[1]);
				if (Float.parseFloat(findElement(By.id("discountedPrice")).getText().split(" ", 2)[1]) == price
						* discountPercent / 100)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> modifyTotalDiscount
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param discountCategory
	 * @param discountType
	 * @param discountValue
	 * @return result - True/False
	 * @throws None
	 */

	public boolean modifyTotalDiscount(String discountCategory, String discountType, float discountValue) {
		boolean result = false;
		try {
			findElement(By.id("modifyDiscount")).click();
			if (findElement(By.xpath("//div[@aria-describedby='dev_discountDialog']")).isDisplayed()) {
				findElement(By.id("dev_itemLevelRadio")).click();
				findElement(
						By.xpath("//*[@id='dev_itemLevelDiscountTable']//select/option[text()='" + discountType + "']"))
								.click();
				findElement(
						By.xpath("//*[@id='dev_itemLevelDiscountTable']//input[contains(@class,'dev_discountInput')]"))
								.sendKeys(String.valueOf(discountValue));
				findElement(By.id("dev_saveDiscounts")).click();
				if (Float.parseFloat(findElement(By.id("discountedPrice")).getText().split(" ", 2)[1]) == discountValue)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> modifyTaxDetails
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param taxInclusive
	 * @param freight
	 * @param insuranceCharges
	 * @param exciseDuties
	 * @return result - True/False
	 * @throws None
	 */

	public boolean modifyTaxDetails(boolean taxInclusive, float freight, float insuranceCharges, float exciseDuties) {
		boolean result = true;
		try {
			if (taxInclusive) {
				findElement(By.xpath("//div[@id='headerTaxes']//input[contains(@class,'dev_applyNoTaxes')]")).click();
				if (!findElement(By.xpath("//*[@id='headerTaxes']//table[contains(@class,'taxTable')]"))
						.getAttribute("style").contains("none"))
					result = false;
			}
			try {
				findElement(By.id("txtFreightCharges")).sendKeys(String.valueOf(freight));
				findElement(By.id("txtInsuranceCharges")).sendKeys(String.valueOf(insuranceCharges));
				findElement(By.id("txtExciseDuties")).sendKeys(String.valueOf(exciseDuties));
			} catch (Exception e) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the creditMemoHeader
	 */
	public By getInvoiceHeader() {
		return invoiceHeader;
	}

	/**
	 * @param creditMemoHeader
	 *            the creditMemoHeader to set
	 */
	public void setInvoiceHeader(By invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

}
