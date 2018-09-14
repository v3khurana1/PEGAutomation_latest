package com.zycus.eProc.GuidedProcurement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> GuidedProcurement_SearchEform.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.searchEForm: </br>
 * <br>
 * 2.filterByReceivedOn: user shall be able to filter by Received On </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class GuidedProcurement_SearchEform extends eProc_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	private By EForms_popUp = By.xpath("//div[@class='guidemepage clearfix']");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public GuidedProcurement_SearchEform(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * @return the EForms_popUp
	 */
	public By getEForms_popUp() {
		return EForms_popUp;
	}

	/**
	 * @param eForms_popUp
	 *            the eForms_popUp to set
	 */
	public void setEForms_popUp(By eForms_popUp) {
		this.EForms_popUp = eForms_popUp;
	}

	/**
	 * <b>Function:</b> searchEForm -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param eForm
	 * @return result - True/False
	 */

	public boolean searchEForm(String eForm) {
		boolean result = false;
		try {
			if (eForm != "")
				findElement(By.xpath("(//*[@id='selectEform']//a[@class='scLnk eformitem efrmLnk'])[1]")).click();
			else {
				WebElement objItemsFound = findElement(By.xpath("(//*[@id='selectEform']/div[1]/div/div[2])[2]/span"));
				int itemsFound = Integer.parseInt(objItemsFound.getText());
				sendKeys(By.id("txtSearchEform"), eForm);
				if (Integer.parseInt(objItemsFound.getText()) == 1) {
					findElement(By.xpath(
							"//*[@id='selectEform']//a[@class='scLnk eformitem efrmLnk']/div[text()='" + eForm + "']"));
					result = true;
				} else
					System.out.println("No eForm displayed for the search item");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
