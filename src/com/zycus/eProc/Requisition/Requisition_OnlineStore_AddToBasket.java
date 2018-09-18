package com.zycus.eProc.Requisition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Requisition_OnlineStore_AddToBasket.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * @author Varun Khurana
 * @since April 2018
 */

public class Requisition_OnlineStore_AddToBasket extends eProc_CommonFunctions{
	//private WebDriver driver;
	//private ExtentTest logger;
	
	private By AddToBasketPg 		= By.xpath("//div[div/span[text()='Add to basket']]");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public Requisition_OnlineStore_AddToBasket(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	/**
	 * @return the addToBasketPg
	 */
	public By getAddToBasketPg() {
		return AddToBasketPg;
	}

	/**
	 * @param addToBasketPg the addToBasketPg to set
	 */
	public void setAddToBasketPg(By addToBasketPg) {
		AddToBasketPg = addToBasketPg;
	}
	
}
