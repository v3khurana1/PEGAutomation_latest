// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   createPOInvoice.java

package com.zycus.ZSN.main;

import common.Functions.CommonFunctions1;
import Framework.ConfigurationProperties;
import Invoice.ViewInvoices;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;

// Referenced classes of package Invoice:
//            ViewInvoices

public class createPOInvoice1 extends CommonFunctions1
{

    public createPOInvoice1(WebDriver driver, ExtentTest logger)
        throws Exception
    {
        super(driver, logger);
        
   //     (//table/tbody//tr[contains(@class,'itemRows')]//input[not(@disabled)])[1]/ancestor::tr//input[contains(@class,'txtInvoiceQuantity')]
      //  qtyInvoicefieldxpath = By.xpath("//*[@id='frmInvoice']//table//tr/td[11]//input[contains(@class,'txtInvoiceQuantity')]");
     //   marketPricefieldxpath = By.xpath("//*[@id='frmInvoice']//table//tr/td[6]//input[contains(@class,'txtPrice')]");
        qtyInvoicefieldxpath = By.xpath("(//table/tbody//tr[contains(@class,'itemRows')]//input[not(@disabled)])[1]/ancestor::tr//input[contains(@class,'txtInvoiceQuantity')]");
        marketPricefieldxpath = By.xpath("(//table/tbody//tr[contains(@class,'itemRows')]//input[not(@disabled)])[1]/ancestor::tr//input[contains(@class,'txtPrice')]");
        
        invoicenoId = By.id("txtInvoiceNumber");
        configurationProperties = ConfigurationProperties.getInstance();
        this.driver = driver;
        this.logger = logger;
    }

    public boolean create_invoice()
        throws Exception
    {
        boolean result = false;
        try
        {
            String invoice_no = String.valueOf(generateNo());
            sendKeys(invoicenoId, invoice_no);
            logger.log(LogStatus.INFO, (new StringBuilder("entered invoice no: ")).append(invoice_no).toString());
            selectDate("txtInvoiceDate");
            selectRemit_ToAddress();
            selectShip_ToAddress();
            LogScreenshot("INFO");
            WebElement foo = driver.findElement(By.xpath(
					"(//table/tbody//tr[contains(@class,'itemRows')]//input[not(@disabled)])[1]")); 
            
            
        //  WebElement e = findElement(By.xpath("//*[@id='frmInvoice']//table//tr/th[1]/input"));
          //  WebElement e = findElement(By.xpath("*[@id='frmInvoice']//table/tbody/tr[1]//td/input"));
          
            add_attachment((new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append(configurationProperties.getProperty("upload_file_path")).toString());
            if(findElement(By.xpath("//*[@id='carouselSlider']/div/ul/li[text()='No attachments to be displayed']")) == null)
                logger.log(LogStatus.INFO, "able to upload file and view in side by side panel ");
            foo.click();
            if(findElement(qtyInvoicefieldxpath).getText().contentEquals(""))
            {
                sendKeys(qtyInvoicefieldxpath, "1");
                logger.log(LogStatus.INFO, "quantity was empty, hence entered the value");
            }
            WebElement price = findElement(marketPricefieldxpath);
            if(price.getText().contentEquals(""))
            {
                sendKeys(marketPricefieldxpath, "10");
                logger.log(LogStatus.INFO, "market price was empty, hence entered the value");
            }
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
                logger.log(LogStatus.INFO, (new StringBuilder("successfully created invoice no ")).append(invoice_no).toString());
                result = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public String create_inv(String path)
        throws Exception
    {
        boolean result = false;
        String selectedPO = null;
        if(path != "View Orders")
            selectedPO = select_PO_to_create_Inv_Creditmemo("");
        result = create_invoice();
        if(result)
        	return selectedPO;
        else 
        	return null;
    }

    private WebDriver driver;
    private By qtyInvoicefieldxpath;
    private By marketPricefieldxpath;
    private By invoicenoId;
    private String invoice_no;
    private ExtentTest logger;
    ConfigurationProperties configurationProperties;
}
