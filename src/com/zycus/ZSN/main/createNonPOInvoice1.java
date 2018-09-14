package com.zycus.ZSN.main;


//Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//Jad home page: http://www.kpdus.com/jad.html
//Decompiler options: packimports(3) 
//Source File Name:   createNonPOInvoice.java

import common.Functions.CommonFunctions1;
import Invoice.ViewInvoices;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Referenced classes of package Invoice:
//         ViewInvoices

public class createNonPOInvoice1 extends CommonFunctions1
{

 public createNonPOInvoice1(WebDriver driver, String location, String currency, String payment_term, String supplier_name, String item_no, String item_description, 
         String product_category, String market_price, String quantity, ExtentTest logger)
     throws Exception
 {
     super(driver, logger);
     custno = "1234";
     custNoid = By.id("txtCustomerNo");
     locId = By.id("location");
     invoiceNoid = By.id("txtInvoiceNumber");
     paymentTermid = By.id("txtPaymentTerm-customBtn");
     addItembtnid = By.id("addMoreItems");
     supplierCompanyId = By.id("slctSupplierCompany");
     slctsupplierCompanyId = By.id("slctSupplierCompany-customBtn");
     this.driver = driver;
     this.location = location;
     this.logger = logger;
     this.currency = currency;
     this.payment_term = payment_term;
     this.supplier_name = supplier_name;
     this.item_no = item_no;
     this.item_description = item_description;
     this.product_category = product_category;
     this.market_price = market_price;
     this.quantity = quantity;
 }

 public boolean create_nonPOinv1(String subtab)
     throws Exception
 {
     boolean status = false;
     try
     {
         if(subtab.contains("Invoice"))
         {
        	 if(driver.findElements(By.xpath("//form[@id='frmInvoice']//label[@for='slctSupplierCompany']/ancestor::li//span[2]")).size()==0){
	        	 findElement(slctsupplierCompanyId).click();
	             selectDropdown(supplier_name);
	             logger.log(LogStatus.INFO, (new StringBuilder("selected supplier: ")).append(supplier_name).toString());
        	 }
         }
       /*  sendKeys(custNoid, custno);
         logger.log(LogStatus.INFO, (new StringBuilder("entered customer no: ")).append(custno).toString());*/
         sendKeys(locId, location);
         logger.log(LogStatus.INFO, (new StringBuilder("selected location: ")).append(location).toString());
         Thread.sleep(2000L);
         findElement(By.xpath((new StringBuilder("//div[@id='cntInvoice']/ul[contains(@style,'block')]/li[contains(text(),'")).append(location).append("')]").toString())).click();
         Thread.sleep(3000L);
         LogScreenshot("INFO");
		//	select_AutoComplete_Dropdown(locId, location);
         String invoice_no = String.valueOf(generateNo());
         sendKeys(invoiceNoid, invoice_no);
         logger.log(LogStatus.INFO, (new StringBuilder("entered invoice no: ")).append(invoice_no).toString());
         selectCurrency(currency);
         logger.log(LogStatus.INFO, (new StringBuilder("selected currency : ")).append(currency).toString());
         selectDate("txtInvoiceDate");
         Thread.sleep(2000L);
         findElement(paymentTermid).click();
         selectDropdown(payment_term);
         logger.log(LogStatus.INFO, (new StringBuilder("selected payment term : ")).append(payment_term).toString());
         selectRemit_ToAddress();
         selectShip_ToAddress();
         LogScreenshot("INFO");
         scroll_into_view_element(findElement(addItembtnid));
         findElement(addItembtnid).click();
         Thread.sleep(2000L);
         add_item(item_no, item_description, product_category, market_price, quantity);
         click_Submit();
         LogScreenshot("INFO", "entered all fields and clicked on Submit");
         waitUntilInvisibilityOfElement(By.xpath("//div[@id='status_overlay_saveInvoice']/div[contains(text(),'Saving')]"));
         try
         {
             do_not_show();
         }
         catch(Exception exception) { }
         Thread.sleep(4000L);
         ViewInvoices objInvoice = new ViewInvoices(driver, logger);
         if(findElement(objInvoice.getInvoiceGridId()) != null)
         {
             logger.log(LogStatus.INFO, "successfully created nonPO invoice");
             status = true;
         }
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     return status;
 }

 private WebDriver driver;
 private String location;
 private String currency;
 private String payment_term;
 private String supplier_name;
 private String item_no;
 private String item_description;
 private String quantity;
 private String market_price;
 private String product_category;
 private String custno;
 private By custNoid;
 private By locId;
 private By invoiceNoid;
 private By paymentTermid;
 private By addItembtnid;
 private By supplierCompanyId;
 private By slctsupplierCompanyId;
 private ExtentTest logger;
}

