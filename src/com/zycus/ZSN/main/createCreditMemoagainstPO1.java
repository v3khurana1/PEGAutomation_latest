// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   createCreditMemoagainstPO.java

package com.zycus.ZSN.main;

import common.Functions.CommonFunctions1;
import Invoice.ViewInvoices;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;

// Referenced classes of package Invoice:
//            ViewInvoices

public class createCreditMemoagainstPO1 extends CommonFunctions1
{

    public createCreditMemoagainstPO1(WebDriver driver, ExtentTest logger)
        throws Exception
    {
        super(driver, logger);
        invoiceNoid = By.id("txtInvoiceNumber");
        itemLessId = By.id("itemLess");
        firstRowTableXpath = By.xpath("//*[@id='frmInvoice']//table//tr/td[1]/input[@class='input chkItem']");
        this.driver = driver;
        this.logger = logger;
    }

    public boolean Create_new_Credit_Memo(String selectedPO)
        throws Exception
    {
        boolean status = false;
        try
        {
            select_PO_to_create_Inv_Creditmemo(selectedPO);
            String creditMemono = String.valueOf(generateNo());
            sendKeys(invoiceNoid, creditMemono);
            logger.log(LogStatus.INFO, (new StringBuilder("creditmemo no: ")).append(creditMemono).toString());
            selectDate("txtInvoiceDate");
              //modified by Anisha on 5th July 2018
            Thread.sleep(2000);
            selectRemit_ToAddress();
            scroll_into_view_element(findElement(firstRowTableXpath));
            Thread.sleep(2000L);
            if(!findElement(itemLessId).isSelected())
                findElement(firstRowTableXpath).click();
            click_Submit();
            LogScreenshot("INFO", "entered all fields and clicked on Submit");
            waitUntilInvisibilityOfElement(By.xpath("//div[@id='status_overlay_saveInvoice']/div[contains(text(),'Saving')]"));
            try
            {
                do_not_show();
            }
            catch(Exception exception1) { }
            Thread.sleep(5000L);
            ViewInvoices objInvoice = new ViewInvoices(driver, logger);
            if(findElement(objInvoice.getInvoiceGridId()) != null)
            {
                logger.log(LogStatus.INFO, "successfully created credit memo against PO");
                status = true;
            }
        }
        catch(Exception exception) { }
        return status;
    }

    private WebDriver driver;
    //private Actions action;
    private By invoiceNoid;
    private By itemLessId;
    private By firstRowTableXpath;
    //private String credit_memoNo;
    private ExtentTest logger;
}
