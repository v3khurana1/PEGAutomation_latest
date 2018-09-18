package com.zycus.eProc.Requisition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zycus.eProc.GuidedProcurement.*;
import com.zycus.eProc.*;
import com.zycus.eProc.Punchout.*;

import common.Functions.eProc_CommonFunctions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * <p>
 * <b> Title: </b> Requisition_OnlineStore.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.selectItemFromCategoriesSideBar: </br>
 * <br>
 * 2.searchItemFromCatalog: </br>
 * <br>
 * 3.viewAllGuidedProcurements: </br>
 * <br>
 * 4.selectEForm: </br>
 * <br>
 * 3.searchItemFromCatalog: </br>
 * <br>
 * 4.viewAllPunchouts: </br>
 * <br>
 * 5.selectPunchouts: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Requisition_OnlineStore extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public Requisition_OnlineStore(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> selectItemFromCategoriesSideBar -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param category
	 * @param subCategory
	 * @param searchItem
	 * @return result - True/False
	 */

	public boolean selectItemFromCategoriesSideBar(String category, String subCategory, String searchItem) {
		boolean result = false;
		try {
			if (findElement(By.id("sideBarBox")) != null) {
				findElement(By.xpath("//ul[@id='categoryLinks']/li[span[contains(text(),'" + category + "')]]")).click();
				findElement(By.xpath("//ul[@class='catPop-list devCatgeoryPopUp']/li[div[contains(text(),'" + subCategory
						+ "')]]/div[2]/a[contains(text(),'" + searchItem + "')]")).click();
				Requisition_OnlineStore_SearchResults objReqSrchRes = new Requisition_OnlineStore_SearchResults(driver, logger);
				if (findElement(objReqSrchRes.getNo_catalog_items_found()) == null) {
					logger.log(LogStatus.INFO, "No catalog items found message displayed");
				} else if (driver.findElements(objReqSrchRes.getSearch_results()).size() > 0) {
					result = true;
				}
			} else {
				logger.log(LogStatus.INFO, "Side bar category is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> searchItemFromCatalog -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param searchItem
	 * @return result - True/False
	 */

	public boolean searchItemFromCatalog(String searchItem) {
		boolean result = false;
		try {
			// Enter Search Item and click Search button
			sendKeys(By.id("searchTerm"), searchItem);
			findElement(By.id("btnBigSearch")).click();
			Requisition_OnlineStore_SearchResults objReqSrchRes = new Requisition_OnlineStore_SearchResults(driver, logger);
			if (findElement(objReqSrchRes.getNo_catalog_items_found()) == null) {
				logger.log(LogStatus.INFO, "No catalog items found message displayed");
				result = true;
			} else if (driver.findElements(objReqSrchRes.getSearch_results()).size() > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> viewAllGuidedProcurements -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - True/False
	 */

	public boolean viewAllGuidedProcurements() {
		boolean result = false;
		try {
			// Click View All - Guided Procurement
			findElement(By.xpath("//div[@class='boxLayout guidedWrap']//span[text()='View All']")).click();

			GuidedProcurement_SearchEform objGPSrchEFrms = new GuidedProcurement_SearchEform(driver, logger);
			if (findElement(objGPSrchEFrms.getEForms_popUp()) != null)
				result = true;
			else
				logger.log(LogStatus.INFO, "All eForms pop up not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> selectEForm -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param EForm
	 * @param shortDesc
	 * @param quantity
	 * @param uom
	 * @param price
	 * @param itemType
	 * @param sourcingStatus
	 * @param receiveBillBy
	 * @param currency
	 * @return result - True/False
	 */

	public boolean selectEForm(String EForm, String shortDesc, String quantity, String uom, float price,
			String itemType, String sourcingStatus, String receiveBillBy, String currency) {
		boolean result = false;
		try {
			// Select a displayed eForm or select 1st eForm
			if (EForm != "")
				findElement(By.xpath("//div[@class='bullet']//a[text()='" + EForm + "']")).click();
			else
				findElement(By.xpath("(//div[@class='bullet']//a)[1]")).click();

			Category_FreeTextItem objCategItem = new Category_FreeTextItem(driver, logger, shortDesc, quantity, uom, price,
					itemType, sourcingStatus, receiveBillBy, currency);
			if (findElement(objCategItem.getGuidedProc_createItemPg()) != null)
				result = true;
			else
				logger.log(LogStatus.INFO, "Create free-item for category page not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> viewAllPunchouts -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - True/False
	 */

	public boolean viewAllPunchouts() {
		boolean result = false;
		try {
			// Click View All - Punchouts
			findElement(By.xpath("//div[@class='boxLayout punchWrap']//span[text()='View All']")).click();

			Punchouts_SearchPunchout objPnsSrchPnchout = new Punchouts_SearchPunchout(driver, logger);
			if (findElement(objPnsSrchPnchout.getAllPunchouts_popUp()) != null)
				result = true;
			else
				logger.log(LogStatus.INFO, "All Punchouts pop up not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> selectPunchouts -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param strPunchout
	 * @return result - True/False
	 */

	public boolean selectPunchouts(String strPunchout) {
		boolean result = false;
		try {
			// Select a displayed Punchout or select 1st Punchout
			if (strPunchout != "")
				findElement(By.xpath("//div[contains(@class,'punchImgWrap')]//a[@title='" + strPunchout + "']")).click();
			else
				findElement(By.xpath("(//div[contains(@class,'punchImgWrap')]//a)[1]")).click();

			CDW_page objCDWPg = new CDW_page(driver, logger);
			if (findElement(objCDWPg.getCDWPg()) != null)
				result = true;
			else
				logger.log(LogStatus.INFO, "Create free-item for category page not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
