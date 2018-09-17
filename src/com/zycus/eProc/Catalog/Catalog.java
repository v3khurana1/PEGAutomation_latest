package com.zycus.eProc.Catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Catalog.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.addCatalog: </br>
 * <br>
 * 2.filterByStatus: </br>
 * <br>
 * 3.filterByCatalogName: </br>
 * <br>
 * 4.filterBySupplier: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Catalog extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	//private By processingLoader = By.id("catalogListing_processing");
	private By statusXpath = By.xpath("//table[@id='catalogListing']//td[2]/div");
	private By filterBtnXpath = By.xpath(
			"//div[contains(@id,'qtip') and @aria-hidden='false']//div[contains(@class,'FilterBtnbx')]//a[text()='Filter']");

	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public Catalog(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> addCatalog
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - true/false
	 */

	public boolean addCatalog() {
		boolean result = false;
		try {
			findElement(By.id("addNewCatalogBtn")).click();
			NewCatalog objCatalog = new NewCatalog(driver, logger);
			if(findElement(objCatalog.getNewCatalogLbl())!=null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByStatus - To filter by Status
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl
	 *            - Label of the checkbox which is to be selected
	 * @return result - True/False
	 */

	/*public boolean filterByStatus(String checkBoxLbl) {
		boolean result = false;
		try {
			String colName = "Status";
			// click(By.xpath("//th[contains(@class,'budgetStatusFltrHdr')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByChkbox(checkBoxLbl);
			result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean filterByStatus(String checkBoxLbl) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'budgetStatusFltrHdr')]//b")).click();
			result = objFunctions.filterByChkbox(checkBoxLbl, statusXpath) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByCatalogName -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param searchVal
	 *            -
	 * @return result - True/False
	 */

	public boolean filterByCatalogName(String searchVal) {
		boolean result = false;
		try {
			result = filterByText("Catalog Name", searchVal)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterBySupplier -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param supplierType
	 * @param supplierName
	 *            -
	 * @return result - True/False
	 */

	public boolean filterBySupplier(String supplierType, String supplierName) {
		boolean result = false;
		try {
			findElement(By.xpath("(//div[contains(@id,'qtip')]//input[following-sibling::text()[contains(.,'" + supplierType
					+ "')]])[1]")).click();
			if (supplierType == "Single Supplier")
				sendKeys(By.id("txtSupplierName"), supplierName);
			findElement(filterBtnXpath).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
