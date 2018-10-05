package com.zycus.iManage.MyTemplates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import common.Functions.eInvoice_CommonFunctions;
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

	private WebDriver driver;
	private ExtentTest logger;

	private By HeaderReqNum = By.xpath("//h1[@class='pgHead']/span[1]");
	private By HeaderReqName = By.xpath("//h1[@class='pgHead']/span[3]");
	private By statusXpath = By.xpath("//table[@id='template-grid']/tbody//td[3]");
	
	iManage_CommonFunctions objFunctions = new iManage_CommonFunctions(driver, logger);
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public MyTemplates(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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
	
	public boolean filterByTemplateStatus(String checkBoxLbl){
		boolean result = false;
		try{
			findElement(By.xpath("//th[@id='currentState_filterColumn']//label")).click();
			result = objFunctions.filterByChkbox(checkBoxLbl, statusXpath)?true:false;
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean createNewTemplate(){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@title='Create New Template']")).click();
			CreateNewTemplate objTemplate = new CreateNewTemplate(driver, logger);
			
		}
	}
	
}
