package com.zycus.iManage.MyConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

public class MassUpdate extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	private By HeaderReqNum = By.xpath("//h1[@class='pgHead']/span[1]");
	private By HeaderReqName = By.xpath("//h1[@class='pgHead']/span[3]");
	//private String uploadfileConfigProp;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param uploadfileConfigProp 
	 */
	
	public MassUpdate(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	//	this.uploadfileConfigProp=uploadfileConfigProp;
	}

	/**
	 * @return the headerReqName
	 */
	public By getHeaderReqName() {
		return HeaderReqName;
	}

	/**
	 * @param headerReqName
	 *            the headerReqName to set
	 */
	public void setHeaderReqName(By headerReqName) {
		HeaderReqName = headerReqName;
	}

	/**
	 * @return the headerReqNum
	 */
	public By getHeaderReqNum() {
		return HeaderReqNum;
	}

	/**
	 * @param headerReqNum
	 *            the headerReqNum to set
	 */
	public void setHeaderReqNum(By headerReqNum) {
		HeaderReqNum = headerReqNum;
	}
	
	public boolean massUpdate(String uploadfileConfigProp) throws Exception
	{
		boolean result = false;
		findElement(By.xpath("//table[@id='dataGrid']//tr[1]/td[@class='filterGridTblTd']/input")).click();
		By uploadBtn = By.xpath("//*[@id='fileUploader']/following-sibling::label");
		//	String uploadfileConfigProp = null;
			if(addAttachment(uploadBtn, uploadfileConfigProp))
				result = true;	
		return result;
	}

}
