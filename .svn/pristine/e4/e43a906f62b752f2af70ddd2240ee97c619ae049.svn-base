package com.zycus.eProc;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

public class ReturnNote_Page extends eProc_CommonFunctions {
	private WebDriver driver;
	private ExtentTest logger;

	private By returnNoteNumId 		= By.id("txtReturnNote");
	private By returnNoteDescId 	= By.id("txtReturnNoteDesc");
	private By notesId 				= By.id("notes");
	private By notifySuppId 		= By.id("notifySupplier");
	private By suppContactId 		= By.id("txtSupplierContact");
	private By suppEmailId 			= By.id("txtSupplierContactEmailId");
	private By suppCommentsId 		= By.id("supplierComments");
	private By selectAllChkbox 		= By.id("chkAllItems");
	private By submitBtn 			= By.id("submitReturnNote");
	private By returnedQtyXpath 	= By.xpath("//input[contains(@id,'returnQty')]");
	private By addAttachmentsLink 	= By.xpath("//a[@title='Add Attachments']");
	private By attachFilePopUp 		= By.xpath("//*[@id='addAttachment']/parent::div");

	private String returnNoteNum;
	private int returnQty;
	private String returnReason;
	private String returnMethod;

	public ReturnNote_Page(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public ReturnNote_Page(WebDriver driver, ExtentTest logger, String returnNoteNum, int returnQty, String returnReason, 
			String returnMethod) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.returnNoteNum = returnNoteNum;
		this.returnQty = returnQty;
		this.returnReason = returnReason;
		this.returnMethod = returnMethod;
	}


	public ReturnNote_Page(WebDriver driver, ExtentTest logger, int returnQty, String returnReason, String returnMethod) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.returnQty = returnQty;
		this.returnReason = returnReason;
		this.returnMethod = returnMethod;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : createReturnNote
	 * 
	 * @param returnNoteDesc
	 * @param notes
	 * @param fileUploadPath
	 * @param notifySupplier
	 * @param supplierContact
	 * @param supplierEmail
	 * @param supplierComments
	 * @return result
	 * @throws Exception 
	 */

	public boolean createReturnNote(String returnNoteDesc, String notes, String fileUploadPath, boolean notifySupplier,
			String supplierContact, String supplierEmail, String supplierComments)
			throws Exception {
		boolean result = false;

		WebElement objAttachmentLink = findElement(addAttachmentsLink);
		String attachmentLinkTxt = objAttachmentLink.getText();

		// Supplier Details
		sendKeys(returnNoteNumId, returnNoteNum);
		sendKeys(returnNoteDescId, returnNoteDesc);
		sendKeys(notesId, notes);

		// Add Attachments
		objAttachmentLink.click();
		if (findElement(attachFilePopUp) != null)
			// if(this.uploadFile(fileUploadPath))
			uploadFile(fileUploadPath);
		findElement(By.xpath("//*[contains(@id,'closeHeaderAttachmentDialog')]")).click();
		// Verify if file is uploaded
		if (attachmentLinkTxt == objAttachmentLink.getText())
			System.out.println("Attachment file not uploaded");
		if (notifySupplier)
			findElement(notifySuppId).click();
		sendKeys(suppContactId, supplierContact);
		sendKeys(suppEmailId, supplierEmail);
		sendKeys(suppCommentsId, supplierComments);

		// Items Received
		findElement(selectAllChkbox).click();
		sendKeys(returnedQtyXpath, String.valueOf(returnQty));
		findElement(By.xpath("//select[contains(@id, 'reasonForReturn')]/option[text()='" + returnReason + "']"))
				.click();
		findElement(By.xpath("//select[contains(@id, 'returnMethod')]/option[text()='" + returnMethod + "']"))
				.click();

		findElement(submitBtn).click();;
		if (findElement(By.xpath(
				"//td[contains(text(),'System will finalize')]/ancestor::div[contains(@class,'promptbx')]")) != null) {
			findElement(By.xpath("//div[contains(@class,'promptbx')]/div[3]//span[text()='Continue']")).click();
			result = true;
		}
		return result;

	}
}
