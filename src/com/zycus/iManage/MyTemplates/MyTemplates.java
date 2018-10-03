package com.zycus.iManage.MyTemplates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> BlanketPurchaseOrder.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class MyTemplates extends iManage_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;

	private By HeaderReqNum = By.xpath("//h1[@class='pgHead']/span[1]");
	private By HeaderReqName = By.xpath("//h1[@class='pgHead']/span[3]");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public MyTemplates(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
	}

	public boolean filterByTemplateTitle(String templateTitle){
		boolean result = false;
		try{
			result = filterByText("Template Title", templateTitle)?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean filterByTemplateDesc(String templateDescription){
		boolean result = false;
		try{
			result = filterByText("Template Title", templateDescription)?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
