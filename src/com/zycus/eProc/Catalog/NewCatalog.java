package com.zycus.eProc.Catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> NewCatalog.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.enterCatalogDetails: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class NewCatalog extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By newCatalogLbl = By.xpath("//h1[@class='pgHead' and text()='Catalog Details']");
	private String catalogName;
	private String supplier;

	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public NewCatalog(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public NewCatalog(WebDriver driver, ExtentTest logger, String catalogName, String supplier) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.catalogName = catalogName;
		this.supplier = supplier;
	}

	/**
	 * <b>Function:</b> enterCatalogDetails
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return None
	 */

	public void enterCatalogDetails(String serviceNum, String shortDesc, String prodCategory, String price) {
		try {
			findElement(By.id("txtCatalogName")).sendKeys(catalogName);
			objFunctions.enterText_AutoComplete(By.id("txtSupplierName"), supplier);

			// Select Address
			findElement(By.name("btnMyAddress")).click();
			findElement(By.xpath("//ul[contains(@style,'block')]/li[1]")).click();
			// Click Next button
			/*findElement(By.xpath("//form[@id='frmCreateCatalog']//a[@id='btnLnkNext']")).click();
			waitUntilVisibilityOfElement(
					By.xpath("//ul[@class='stepsMenu']/li[@class='active']/p[text()='Item Details']"));*/
			
			clickAndWaitUntilElementAppears(By.xpath("//form[@id='frmCreateCatalog']//a[@id='btnLnkNext']"), By.xpath("//ul[@class='stepsMenu']/li[@class='active']/p[text()='Item Details']"));
			
			ItemDetails objDetails = new ItemDetails(driver, logger, serviceNum, shortDesc, prodCategory, price);
			if (objDetails.enterItemDetails())
				findElement(By.xpath("//form[@id='frmCreateCatalog']//a[@id='btnLnkNext']")).click();
			waitUntilVisibilityOfElement(
					By.xpath("//ul[@class='stepsMenu']/li[@class='active']/p[text()='Scope & Validity']"));

			// Select organization Unit
			findElement(By.id("linkScopeSelected")).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean selectOrgUnit() {
		boolean result = false;
		try {
			findElement(
					By.xpath("//div[@id='diagBoxScopeDefConfig']//table[contains(@id,'ListTable')]/tbody/tr[1]/td[1]"))
							.click();
			findElement(By.xpath("//ul[@role='tablist']//a[text()='Business Units']")).click();
			findElement(By.xpath("//table[contains(@id,'ListGrid')]/tbody//a[text()='Select Organization Unit']")).click();
			findElement(By.id("saveSelectedScope")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the newCatalogLbl
	 */
	public By getNewCatalogLbl() {
		return newCatalogLbl;
	}

	/**
	 * @param newCatalogLbl
	 *            the newCatalogLbl to set
	 */
	public void setNewCatalogLbl(By newCatalogLbl) {
		this.newCatalogLbl = newCatalogLbl;
	}
}
