package com.zycus.eProc;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class CreateReceipt_page extends eProc_CommonFunctions{
	//private WebDriver driver;
	
	private Date receiptDt;
	
	private By selectItemsChkbox 		= By.id("chkAllItems");
	private By consignNoId 				= By.id("txt_consign");
	private By shippedViaId 			= By.id("txt_shipped_via");
	private By airwayBillId 			= By.id("txt_air_bill");
	private By commentsId 				= By.id("comment");
	private By submitBtnId 				= By.id("submitForm");
	private By HeaderReqNum 			= By.xpath("//h1[@class='pgHead']/span[1]");
	private By HeaderReqName 			= By.xpath("//h1[@class='pgHead']/span[3]");
	private By addAttachmentsLink 		= By.xpath("//a[@title='Add Attachments']");
	private By attachFilePopUp 			= By.xpath("//*[@id='addAttachment']/parent::div");

	private ExtentTest logger;
	
	public CreateReceipt_page(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		this.logger = logger;
	}
	

	public CreateReceipt_page(WebDriver driver, ExtentTest logger, Date receiptDt) { 
		super(driver, logger);
		//this.driver = driver;
		this.logger = logger;
		this.receiptDt = receiptDt;
	}

	/**
	 * @return the headerReqNum
	 */
	public By getHeaderReqNum() {
		return HeaderReqNum;
	}

	/**
	 * @param headerReqNum the headerReqNum to set
	 */
	public void setHeaderReqNum(By headerReqNum) {
		this.HeaderReqNum = headerReqNum;
	}

	/**
	 * @return the headerReqName
	 */
	public By getHeaderReqName() {
		return HeaderReqName;
	}

	/**
	 * @param headerReqName the headerReqName to set
	 */
	public void setHeaderReqName(By headerReqName) {
		this.HeaderReqName = headerReqName;
	}
	
	
	/**
	 * ---------------------------------------------------------------------------------
	 * Function : enterDeliveryInfo
	 * @param consignmentNo
	 * @param shippedVia
	 * @param AirwayBillNo
	 * @param Comments
	 * @param fileUploadPath
	 * @return result
	 * @throws Exception 
	 */
	
	public boolean enterDeliveryInfo(String consignmentNo, String shippedVia, String AirwayBillNo, String Comments, String fileUploadPath) throws Exception{
		boolean result = false;
		
		WebElement objAttachmentLink = findElement(addAttachmentsLink);
		String attachmentLinkTxt = objAttachmentLink.getText();
		
		findElement(selectItemsChkbox).click();
		findElement(By.xpath("//input[@id='txt_rcv_date']/following-sibling::img")).click();
		selectDate(receiptDt);
		sendKeys(consignNoId, consignmentNo);
		sendKeys(shippedViaId, shippedVia);
		sendKeys(airwayBillId, AirwayBillNo);
		sendKeys(commentsId, Comments);
		//Add Attachments
		objAttachmentLink.click();
		if(findElement(attachFilePopUp)!=null)
			uploadFile(fileUploadPath);
		findElement(By.xpath("//*[contains(@id,'closeAttachmentDialog')]")).click();
			
			//Verify if file is uploaded
			if(attachmentLinkTxt==objAttachmentLink.getText())
				logger.log(LogStatus.INFO, "Attachment file not uploaded");
			findElement(submitBtnId).click();
		if(findElement(By.xpath("//td[contains(text(),'The selected items will be marked received and invoices')]/ancestor::div[contains(@class,'promptbx')]"))!=null)
			findElement(By.xpath("//div[contains(@class,'promptbx')]/div[3]//span[text()='Yes']")).click();
			Thread.sleep(3000);
			if(findElement(By.xpath("//div[@id='hedaerSuccessBox']//li")).getText().equals("Receipt submitted successfully"))
				result = true;
		return result;
	}
	
}
