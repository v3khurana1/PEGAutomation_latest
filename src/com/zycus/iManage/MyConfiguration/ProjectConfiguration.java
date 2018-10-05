package com.zycus.iManage.MyConfiguration;

import java.util.Random;

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

public class ProjectConfiguration extends iManage_CommonFunctions {

	private WebDriver driver;
	//private ExtentTest logger;

	private By HeaderReqNum = By.xpath("//h1[@class='pgHead']/span[1]");
	private By HeaderReqName = By.xpath("//h1[@class='pgHead']/span[3]");
	
	Random rnd = new Random();
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public ProjectConfiguration(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}

	public boolean workflowSettings() throws Exception{
		boolean result = false;
		WebElement objSkipWorkflow = driver.findElement(By.xpath("//input[@id='skippingEnable']/../div[contains(@class,'text-off-btn')]"));
		flipSwitch(objSkipWorkflow);
		return result;
	}
	
	private void flipSwitch(WebElement obj){
		
		int temp = 0;
		try {
			temp = rnd.nextInt(1);
			String objClass = obj.getAttribute("class");
			if((temp==0)&&(!objClass.contains(" on"))||(temp==1)&&(objClass.contains(" on")))
				obj.click();				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void editCustomProjectType(){
		if(rnd.nextInt(1) == 0)
			if(!driver.findElement(By.xpath("//div[contains(@class,'customProjectTypeDataShowHide')]")).getAttribute("class").contains("hidden")){
				findElement(By.id("EnableCustomProjectType")).click();
				
			}else if(driver.findElement(By.xpath("//div[contains(@class,'customProjectTypeDataShowHide')]")).getAttribute("class").contains("hidden"))
				findElement(By.id("EnableCustomProjectType")).click();
	}
	
}
