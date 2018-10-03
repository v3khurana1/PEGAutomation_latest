package com.zycus.iManage.MyConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Framework.ConfirmationDialog;
import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> TaskProgressSetting.java</br>
 * <br>
 * <b> Description: </b> To perform operations on iManage - My Configuration - Task Progress Setting 
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since Oct 2018
 */

public class TaskProgressSetting extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private int RFI;
	private int RFP;
	private int RFQ;
	private int Auction;
	private String[] iSourceStatusString;
	private String iSourceStatus;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public TaskProgressSetting(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}
	
	public TaskProgressSetting(WebDriver driver, ExtentTest logger, String...iSourceStatusString) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.iSourceStatusString = iSourceStatusString;
	}
	
	
	//TODO : Verify if fields are edited
	
	public boolean editTaskProgSetting(){
		boolean result = false;
		int txtBxValue = 0;
		try{
			findElement(By.xpath("//div[@class='actionBtnHolderPopup']//input[@value='Edit']")).click();
			if(driver.findElement(By.id("afterEdit")).getAttribute("style").contains("block")){
				for(String iSrcString : iSourceStatusString){
					iSourceStatus = (iSrcString.split(";"))[0];
					RFI = Integer.parseInt((iSrcString.split(";"))[1]);
					RFP = Integer.parseInt((iSrcString.split(";"))[2]);
					RFQ = Integer.parseInt((iSrcString.split(";"))[3]);
					Auction = Integer.parseInt((iSrcString.split(";"))[4]);
					for(int itr = 1; itr<5; itr++){
						if(itr==1)
							txtBxValue = RFI; 
						else if(itr==2)
							txtBxValue = RFP;
						else if(itr==3)
							txtBxValue = RFQ;
						else if(itr==4)
							txtBxValue = Auction;
						WebElement txtBx = driver.findElement(By.xpath("//table/tbody/tr[td[text()='"+iSourceStatus+"']]/td["+itr+1+"]/input"));
						txtBx.clear();
						txtBx.sendKeys(String.valueOf(txtBxValue));
					}
					findElement(By.name("savenapply")).click();
					if(driver.findElement(ConfirmationDialog.getDialogTitle()).isDisplayed()){
						if(driver.findElement(ConfirmationDialog.getPopupMsg()).getText().contains("You are changing % progress for iSource status")){
							findElement(ConfirmationDialog.getDialogYesBtn()).click();
							result = true;
						}else
							logger.log(LogStatus.INFO, "Incorrect Confirmation Dialog message displayed");
					}else
						logger.log(LogStatus.INFO, "Confirmation Dialog not displayed");
				}
			}else
				logger.log(LogStatus.INFO, "Edit button not working");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

}
