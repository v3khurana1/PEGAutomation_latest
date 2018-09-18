package com.zycus.eProc;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eProc_CommonFunctions;

public class Category_FreeTextItem extends eProc_CommonFunctions{
	//private WebDriver driver;
	
	private By shortDescID 						= By.id("name");
	private By partNumID 						= By.id("supplierPartId");
	private By quantityID 						= By.id("quantity");
	private By longDescID 						= By.id("description");
	private By uomID 							= By.id("uom");
	private By priceCheckbox 					= By.id("zeroPriceCheckbox");
	private By priceID 							= By.id("price");
	private By suppQuotedSourcingStatus 		= By.id("sourcingStatus_requester");
	private By estimatedPriceSrcStatus 			= By.id("sourcingStatus_budgetary");
	private By needQuoteSrcStatus 				= By.id("sourcingStatus_quote");
	private By qtyReceiveBy 					= By.id("receivedBy_quantity");
	private By amtReceiveBy 					= By.id("receivedBy_amount");
	private By noReceiptReceiveBy 				= By.id("receivedBy_noReceipt");
	private By currencyID 						= By.id("currency");
	private By addAttachmentsLink 				= By.id("lnkGuidedAttachments");
	private By manufactNameID 					= By.id("manufacturerName");
	private By imgURLID 						= By.id("imageUrl");
	private By suppProdURLID 					= By.id("supplierProductURL");
	private By manufactProdURLID 				= By.id("manufacturerProductURL");
	private By manufactPartID 					= By.id("manufacturerPartId");
	private By specificNameID 					= By.id("guidedParametricName");
	private By existPhnInputID 					= By.id("existingPhoneInput");
	private By newSuppAddLinkID 				= By.id("newSupplierAddition");
	private By name_newSuppID 					= By.id("dev_newSupplierInput");
	private By addr_newSuppID 					= By.id("slctNewSupplierAddress");
	private By contact_newSuppID 				= By.id("txtNewSupplierContact");
	private By email_newSuppID 					= By.id("txtNewSupplierContactEmailId");
	private By phn_newSuppID 					= By.id("newSupplierPhoneInput");
	private By contractNo_newSuppID 			= By.id("dev_newContractNo");
	private By othrDet_newSuppID 				= By.id("newSupplierOtherDetails");
	private By addSuppBtn 						= By.id("addFreeTextSupplier");
	private By existSuppOtheDetID 				= By.id("existingSupplierOtherDetails");
	private By existSuppAddLinkID 				= By.id("existingSupplierAddition");
	private By disabledAddr_ExistSupp 			= By.xpath("//*[@id='slctExistingSupplierAddress'][@disabled]");
	private By disabledContact_ExistSupp 		= By.xpath("//*[@id='txtExistingSupplierContact'][@disabled]");
	private By disabledEmail_ExistSupp 			= By.xpath("//*[@id='txtExistingSupplierEmailId'][@disabled]");
	private By disabledContractNo_ExistSupp 	= By.xpath("//*[@id='dev_existingContractNo'][@disabled]");
	private By addSpecificBtn 					= By.xpath("(//div[@id='dev_guidedParametricDataContainer']//a[@title='Add'])[1]");
	private By attachFilePopUp 					= By.xpath("//*[@id='guidedAttachmentsDOM']/parent::div");
	private By GuidedProc_createItemPg 			= By.xpath("//div[@class='guidemepage clearfix']");
	
	private float price;
	private String shortDesc;
	private String quantity;
	private String uom;
	private String itemType;
	private String sourcingStatus;
	private String receiveBillBy;
	private String currency;
	
	private ExtentTest logger;

	public Category_FreeTextItem(WebDriver driver, ExtentTest logger, String shortDesc, String quantity, String uom, float price, String itemType, String sourcingStatus, String receiveBillBy, String currency) { 
		super(driver, logger);
		//this.driver = driver;
		this.logger = logger;
		this.shortDesc = shortDesc;
		this.quantity = quantity;
		this.uom = uom;
		this.price = price;
		this.itemType = itemType;
		this.sourcingStatus = sourcingStatus;
		this.receiveBillBy = receiveBillBy;
		this.currency = currency;
	}

	/**
	 * @return the guidedProc_createItemPg
	 */
	public By getGuidedProc_createItemPg() {
		return GuidedProc_createItemPg;
	}

	/**
	 * @param guidedProc_createItemPg the guidedProc_createItemPg to set
	 */
	public void setGuidedProc_createItemPg(By guidedProc_createItemPg) {
		this.GuidedProc_createItemPg = guidedProc_createItemPg;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : createFreeTextItem
	 * @param partNumber
	 * @param longDesc
	 * @param filePath
	 * @return result
	 * @throws Exception 
	 */
	
	public boolean createFreeTextItem(String partNumber, String longDesc, String filePath) throws Exception{
		boolean result = false;
		try{
			//Enter Item Details
			sendKeys(shortDescID, shortDesc);
			sendKeys(partNumID, partNumber);
			sendKeys(quantityID, quantity);
			sendKeys(longDescID, longDesc);
			sendKeys(uomID, uom);
			if(price==0)
				findElement(priceCheckbox).click();
			else
				sendKeys(priceID, String.valueOf(price));
			findElement(By.id("itemType_"+itemType.toLowerCase())).click();
			switch(sourcingStatus){
				case "Quoted by supplier":
					findElement(suppQuotedSourcingStatus).click();
					break;
				case "Estimated price":
					findElement(estimatedPriceSrcStatus).click();
					break;
				case "Need a quote":
					findElement(needQuoteSrcStatus).click();
					break;
			}
			switch(receiveBillBy){
				case "Quoted by supplier":
					findElement(qtyReceiveBy).click();
					break;
				case "Estimated price":
					findElement(amtReceiveBy).click();
					break;
				case "Need a quote":
					findElement(noReceiptReceiveBy).click();
					break;
			}
			sendKeys(currencyID, currency);
			//Add Attachments
			findElement(addAttachmentsLink).click();
			if(findElement(attachFilePopUp)!=null)
				uploadFile(filePath);
			findElement(By.xpath("//*[contains(@id,'closeGuidedAttachmentsBtn')]")).click();
				result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	/**
	 * ---------------------------------------------------------------------------------
	 * Function : addExtraFields
	 * @param manufacturerName
	 * @param imageURL
	 * @param supProductURL
	 * @param manufactProductURL
	 * @param manufactPartURL
	 * @param isGreen
	 * @param isPreferred
	 * @param specificationsName
	 * @param specifications
	 * @return result
	 * ---------------------------------------------------------------------------------
	 * @throws Exception 
	 */
	
	public boolean addExtraFields(String manufacturerName, String imageURL, String supProductURL, String manufactProductURL, String manufactPartURL, String isGreen, String isPreferred, String specificationsName, List<String> specifications) throws Exception{
		boolean result = false;
		//int i=1;
		try{
			sendKeys(manufactNameID, manufacturerName);
			sendKeys(imgURLID, imageURL);
			sendKeys(suppProdURLID, supProductURL);
			sendKeys(manufactProdURLID, manufactProductURL);
			sendKeys(manufactPartID, manufactPartURL);
			findElement(By.id("dev_isGreen_"+isGreen.toLowerCase())).click();
			findElement(By.id("dev_isPreferred_"+isPreferred.toLowerCase())).click();
			sendKeys(specificNameID, specificationsName);
			if(specifications.size()>2)
				findElement(addSpecificBtn).click();
			for(String spec:specifications){
				sendKeys(By.xpath("//div[@id='dev_guidedParametricDataContainer']//input[i]"), spec);
				//i++;
			}
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ---------------------------------------------------------------------------------
	 * Function : selectExistingSupplier
	 * @param searchBy
	 * @param searchByValue
	 * @param suppPhone
	 * @param suppOthrDetails
	 * @return result
	 * ---------------------------------------------------------------------------------
	 * @throws Exception 
	 */
	public boolean selectExistingSupplier(String searchBy, String searchByValue, String suppPhone, String suppOthrDetails) throws Exception{
		boolean result = false;
		try{
			findElement(existSuppAddLinkID).click();
			findElement(By.xpath("//*[@id='supplierNameDropdown']/div/a/span[text()='"+searchBy+"']")).click();
			sendKeys(By.id("dev_existingSupplierInput"), searchByValue);
			//if(findElement(By.xpath("//li[@class='ui-menu-item']/parent::ul")).getAttribute("style").contains("block")){
				//Select first menu item from the list
				findElement(By.xpath("//li[@class='ui-menu-item'][1]")).click();
				if(findElement(disabledAddr_ExistSupp)!=null)
					logger.log(LogStatus.INFO, "Address field gets enabled");
				else	
					logger.log(LogStatus.INFO, "Address field does not get enabled");
				if(findElement(disabledContact_ExistSupp)!=null)
					logger.log(LogStatus.INFO, "Supplier Contact field gets enabled");
				else	
					logger.log(LogStatus.INFO, "Supplier Contact field does not get enabled");
				if(findElement(disabledEmail_ExistSupp)!=null)
					logger.log(LogStatus.INFO, "Supplier Email ID field gets enabled");
				else	
					logger.log(LogStatus.INFO, "Supplier Email ID field does not get enabled");
				if(findElement(disabledContractNo_ExistSupp)!=null)
					logger.log(LogStatus.INFO, "Contract/OrderNo field gets enabled");
				else	
					logger.log(LogStatus.INFO, "Contract/OrderNo field does not get enabled");
	
				sendKeys(existPhnInputID, suppPhone);
				sendKeys(existSuppOtheDetID, suppOthrDetails);
				result = true;
		}catch(Exception e){
			logger.log(LogStatus.INFO, "Search By Menu list is not displayed");

		}
		return result;
	}
	
	/**
	 * ---------------------------------------------------------------------------------
	 * Function : addNewSupplier
	 * @param supplierType
	 * @param suppName
	 * @param suppAddr
	 * @param suppContact
	 * @param suppEmail
	 * @param suppPhone
	 * @param suppOrderNum
	 * @param suppOthrDetails
	 * @return result
	 * ---------------------------------------------------------------------------------
	 * @throws Exception 
	 */
	public boolean addNewSupplier(String supplierType, String suppName, String suppAddr, String suppContact, String suppEmail, String suppPhone, String suppOrderNum, String suppOthrDetails) throws Exception{
		boolean result = false;
		try{
			findElement(newSuppAddLinkID).click();
			sendKeys(name_newSuppID, suppName);
			sendKeys(addr_newSuppID, suppAddr);
			sendKeys(contact_newSuppID, suppContact);
			sendKeys(email_newSuppID, suppEmail);
			sendKeys(phn_newSuppID, suppPhone);
			sendKeys(contractNo_newSuppID, suppOrderNum);
			sendKeys(othrDet_newSuppID, suppOthrDetails);
			findElement(addSuppBtn).click();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
	
}
