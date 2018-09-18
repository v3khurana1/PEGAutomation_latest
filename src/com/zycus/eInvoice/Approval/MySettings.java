package com.zycus.eInvoice.Approval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Approval.java</br>
 * <br>
 * <b> Description: </b> Preform operations on a purchase order</br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.configureOOO: user is able to configure OOO</br>
 * <br>
 * 
 * @author Anisha
 * @since April 2018
 */
public class MySettings extends eInvoice_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;
	
	private By btnRevokeID = By.id("btnRevoke");
	private By delegateToID = By.id("delegateTo");
	private By btnSaveID = By.id("btnSave");
	private By notificationMsgXpath = By
			.xpath("//*[@id='status_overlay_loading']/div[text()='Approval Delegated successfully']");

	public MySettings(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * <b>Function:</b> configureOOO
	 * 
	 * @author Anisha
	 * @since May 2018
	 * @param
	 * @throws None
	 * @return result
	 * @throws Exception
	 */
	public boolean configureOOO() {
		boolean status = false;
		String delegateTo = "Chaitali";
		try {
			if (findElement(btnRevokeID).isDisplayed()) {
				findElement(btnRevokeID).click();
				Thread.sleep(4000);
			}
			enterText_AutoComplete(delegateToID, delegateTo);
			/*findElement(btnSaveID).click();
			waitUntilInvisibilityOfElement(notificationMsgXpath);*/
			clickAndWaitUntilLoaderDisappears(btnSaveID, notificationMsgXpath);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
