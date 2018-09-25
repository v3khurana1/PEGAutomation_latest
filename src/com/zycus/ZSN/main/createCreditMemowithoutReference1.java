// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   createCreditMemowithoutReference.java

package com.zycus.ZSN.main;
import common.Functions.*;
import Invoice.ViewInvoices;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;

// Referenced classes of package Invoice:
//            ViewInvoices

public class createCreditMemowithoutReference1 extends CommonFunctions
{

    public createCreditMemowithoutReference1(WebDriver driver, String currency, String supplier_company, String item_no, String item_description, String product_category, String market_price, 
            String quantity, ExtentTest logger)
        throws Exception
    {
        super(driver, logger);
        custNoId = By.id("txtCustomerNo");
        invoiceNoId = By.id("txtInvoiceNumber");
        addItemId = By.id("addMoreItems");
        itemLessId = By.id("itemLess");
        slctsupplierCompanyId = By.id("slctSupplierCompany-customBtn");
        this.driver = driver;
        this.logger = logger;
        this.currency = currency;
        this.supplier_company = supplier_company;
        this.item_no = item_no;
        this.item_description = item_description;
        this.product_category = product_category;
        this.market_price = market_price;
        this.quantity = quantity;
    }

    public boolean Create_new_Credit_Memo(String subtab)
        throws Exception
    {
        boolean status = false;
        try
        {
            if(subtab.contains("Credit Memo"))
            {
           	 if(driver.findElements(By.xpath("//form[@id='frmInvoice']//label[@for='slctSupplierCompany']/ancestor::li//span[2]")).size()==0){
            	click(slctsupplierCompanyId);
                selectDropdown(supplier_company);
                logger.log(LogStatus.INFO, (new StringBuilder("selected supplier company: ")).append(supplier_company).toString());
           	 }
          	}
            String creditMemono = String.valueOf(generateNo());
            scroll_into_view_element(findElement(invoiceNoId));
            sendKeys(invoiceNoId, creditMemono);
            logger.log(LogStatus.INFO, (new StringBuilder("entered creditmemo no: ")).append(creditMemono).toString());
            selectCurrency(currency);
            logger.log(LogStatus.INFO, (new StringBuilder("selected currency")).append(currency).toString());
            selectDate("txtInvoiceDate");
            scroll_into_view_element(findElement(itemLessId));
            selectRemit_ToAddress();
            add_attachment("C:\\Users\\anisha.jain\\Downloads\\test_11July_automation.pdf");
            if(!findElement(itemLessId).isSelected())
            {
                try
                {
                    click(addItemId);
                    Thread.sleep(2000L);
                }
                catch(Exception exception) { }
                add_item(item_no, item_description, product_category, market_price, quantity);
            }
            click_Submit();
            LogScreenshot("INFO", "entered all fields and clicked on Submit");
            waitUntilInvisibilityOfElement(By.xpath("//div[@id='status_overlay_saveInvoice']/div[contains(text(),'Saving')]"));
            try
            {
                do_not_show();
            }
            catch(Exception exception1) { }
            Thread.sleep(4000L);
            ViewInvoices objInvoice = new ViewInvoices(driver, logger);
            if(findElement(objInvoice.getInvoiceGridId()) != null)
            {
                logger.log(LogStatus.INFO, (new StringBuilder("successfully created credit memo ")).append(creditMemono).append(" without reference").toString());
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
    private ExtentTest logger;
    private By custNoId;
    private By invoiceNoId;
    private By addItemId;
    private By itemLessId;
    private By slctsupplierCompanyId;
    private String currency;
    private String supplier_company;
    private String item_description;
    private String product_category;
    private String market_price;
    private String quantity;
    private String item_no;
}
