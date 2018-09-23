package com.zycus.iSource.MyEvents;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Framework.ConfigurationProperties;
import common.iSource_CommonFunctions;
import common.Functions.eInvoice_CommonFunctions;

public class Draft extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By freezeEventBtn = By.xpath("//input[@auto_att='freezeEvent']");
	private String[] quesTypes;
	private String sectionName;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public Draft(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public Draft(WebDriver driver, ExtentTest logger, String sectionName, String[] quesTypes) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.sectionName = sectionName;
		this.quesTypes = quesTypes;
	}
	
	

	public boolean createDraft(){
		boolean result = false;
		try{
			//File Upload
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			sendKeys(By.xpath("//form[@id='fileupload']/span"),
					System.getProperty("user.dir") + config.getProperty("upload_file_path"));
			waitUntilVisibilityOfElement(By.xpath("//tr[@id='fileUploadAtt']"));
			LogScreenshot("file uploaded successfully");
			
			findElement(By.xpath("//input[@value='Create Draft']")).click();
			QuestionType objQues = new QuestionType(driver, logger, sectionName, quesTypes);
			objQues.addSection();
			result = true;
			//TODO : verify if section added to Draft page
			findElement(freezeEventBtn).click();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean modifyDraft(){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@value='Modify Draft']")).click();
			//Upload Document
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

}
