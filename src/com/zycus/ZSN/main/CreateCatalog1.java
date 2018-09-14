// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateCatalog.java

package com.zycus.ZSN.main;

import common.Functions.CommonFunctions1;
import Framework.ConfigurationProperties;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCatalog1 extends CommonFunctions1
{

    public CreateCatalog1(WebDriver driver, ExtentTest logger, String catalog_name, String supplier_name, String supplierPartID, String prodCategory, String price)
        throws Exception
    {
        super(driver, logger);
        catalogNamefieldId = By.id("txtCatalogName");
        supplierPartIDfieldid = By.id("txtItemNumber");
        descFieldid = By.id("txtItemname");
        prodCategoryfieldxpath = By.xpath("//*[@id='txtCatName']");
        priceFieldxpath = By.xpath("//*[@id='txtPrice']");
        addBtnid = By.id("addItem");
        continueBtnid = By.id("btnPublish");
        createNewcatalogBtnxpath = By.xpath("//*[@id='lnkOnlineCatalog']");
        addItemBtnxpath = By.xpath("//*[@id='addItemBx1']/a");
        btnContinuexpath = By.xpath("//*[@id='btnContinue']");
        statusLabelxpath = By.xpath("//*[@id='catalogList']//tr[1]/td[2]/a[2]");
        uploadFilebtnxpath = By.xpath("//div[@id='uploadOnParsing']/a[contains(text(),'Upload')]");
        selectBtnxpath = By.xpath("//*[@id='attachmentInput_uploaderPopUp']");
        fileUploadfieldxpath = By.xpath("//*[@id='uploader_uploaderPopUp']//div/span[@class='fileName']");
        Uploadbtnid = By.id("btnUploadOnParsing");
        supplierFieldXpath = By.xpath("//*[@id='txtSupplierCompany']");
        this.driver = driver;
        this.catalog_name = catalog_name;
        this.logger = logger;
        this.supplier_name = supplier_name;
        this.supplierPartID = supplierPartID;
        this.prodCategory = prodCategory;
        this.price = price;
    }

    public boolean create_catalog_online()
        throws Exception
    {
        boolean result = false;
     /*   this;
        catalog_name;
        JVM INSTR new #136 <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        String.valueOf(generateNo());
        append();
        toString();
        catalog_name;*/
        catalog_name = catalog_name+String.valueOf(generateNo());
        sendKeys(catalogNamefieldId, catalog_name);
        logger.log(LogStatus.INFO, (new StringBuilder("catalog name is: ")).append(catalog_name).toString());
        if(driver.findElements(By.xpath("//form[@id='frmpublishCatalog']//label[@for='txtSupplierCompany']/ancestor::li//label[@id='txtSupplierCompany']")).size()==0)
        { 
        sendKeys(supplierFieldXpath, supplier_name);
        Thread.sleep(2000L);
        findElement(By.xpath((new StringBuilder("//ul[contains(@style,'block')]/li[contains(text(),'")).append(supplier_name).append("')]").toString())).click();
        logger.log(LogStatus.INFO, (new StringBuilder("selected supplier name is: ")).append(supplier_name).toString());
        } 
        findElement(continueBtnid).click();
        findElement(createNewcatalogBtnxpath).click();
        Thread.sleep(2000L);
        findElement(addItemBtnxpath).click();
        Thread.sleep(3000L);
        String is_item_added = String.valueOf(addItem(catalog_name, supplierPartID, prodCategory, price));
        LogScreenshot(is_item_added, "succesfully added an item to the catalog");
        Thread.sleep(3000L);
        if(findElement(btnContinuexpath).isEnabled())
        	findElement(btnContinuexpath).click();
        Thread.sleep(2000L);
        findElement(continueBtnid).click();
        waitUntilInvisibilityOfElement(By.id("status_overlay_publish"));
        Thread.sleep(3000L);
        try
        {
            do_not_show();
        }
        catch(Exception exception) { }
        LogScreenshot("INFO", (new StringBuilder("Created online catalog : ")).append(catalog_name).toString());
        Thread.sleep(2000L);
        waitFluent(By.id("catalogList"));
        WebElement status = findElement(statusLabelxpath);
        logger.log(LogStatus.INFO, (new StringBuilder("Validated status of newly created catalog, status : ")).append(status.getText()).toString());
        result = true;
      //  break MISSING_BLOCK_LABEL_379;
        //Exception e;
       // e;
        //e.printStackTrace();
        return result;
    }

    private boolean addItem(String catalog_name, String supplierPartID, String prodCategory, String price)
    {
        boolean result = false;
        try
        {
            sendKeys(supplierPartIDfieldid, supplierPartID);
            sendKeys(descFieldid, (new StringBuilder(String.valueOf(catalog_name))).append("_desc").toString());
            try
            {
                sendKeys(prodCategoryfieldxpath, prodCategory);
                Thread.sleep(2000L);
                findElement(By.xpath((new StringBuilder("//div[@id='additemDialog']//ul[contains(@style,'block')]/li[text()='")).append(prodCategory).append("']").toString())).click();
            }
            catch(Exception e)
            {
                LogScreenshot("INFO", (new StringBuilder("Product category ")).append(prodCategory).append(" is not available in the list").toString());
                findElement(prodCategoryfieldxpath).clear();
                sendKeys(prodCategoryfieldxpath, "a");
                Thread.sleep(2000L);
                findElement(By.xpath("//div[@id='additemDialog']//ul[contains(@style,'block')]/li[1]")).click();
            }
            findElement(priceFieldxpath).sendKeys(new CharSequence[] {
                price
            });
            WebElement addButton = findElement(addBtnid);
            if(addButton.isDisplayed())
            {
            	findElement(addBtnid).click();
                result = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create_catalog_offline(ConfigurationProperties configurationProperties)
        throws Exception
    {
        boolean status = false;
        WebDriverWait wait = new WebDriverWait(driver, 30L);
        findElement(catalogNamefieldId).sendKeys(new CharSequence[] {
            catalog_name
        });
        findElement(continueBtnid).submit();
        findElement(createNewcatalogBtnxpath).click();
        findElement(uploadFilebtnxpath).click();
        WebElement select = findElement(selectBtnxpath);
        String uploadPath = configurationProperties.getProperty("upload_path");
        select.sendKeys(new CharSequence[] {
            uploadPath
        });
        WebElement loc = findElement(fileUploadfieldxpath);
        Thread.sleep(2000L);
        try
        {
            if(!loc.getText().isEmpty())
            	findElement(Uploadbtnid).click();
            wait.until(ExpectedConditions.visibilityOf(findElement(By.xpath("//table/tbody/tr/th[@class='aLft']"))));
            WebElement src = findElement(By.xpath("//*[@id='header-supplierpartid/servicenumber']/td[2]/div"));
            WebElement dest = findElement(By.id("SUPPLIER_PART_ID"));
            drag_drop(src, dest);
            WebElement src1 = findElement(By.xpath("//*[@id='header-shortdescription']/td[2]/div"));
            WebElement dest1 = findElement(By.id("NAME"));
            drag_drop(src1, dest1);
            WebElement src2 = findElement(By.xpath("//*[@id='header-spsccode/productcategorycode']/td[2]/div"));
            WebElement dest2 = findElement(By.id("CATEGORY_CODE"));
            drag_drop(src2, dest2);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        findElement(By.id("btnContinue")).click();
        return status;
    }

    private WebDriver driver;
    private String catalog_name;
    private String uploadPath;
    private By catalogNamefieldId;
    private By supplierPartIDfieldid;
    private By descFieldid;
    private By prodCategoryfieldxpath;
    private By prodCatSelectxpath;
    private By priceFieldxpath;
    private By addBtnid;
    private By continueBtnid;
    private By createNewcatalogBtnxpath;
    private By addItemBtnxpath;
    private By btnContinuexpath;
    private By statusLabelxpath;
    private By uploadFilebtnxpath;
    private By selectBtnxpath;
    private By fileUploadfieldxpath;
    private By Uploadbtnid;
    private ExtentTest logger;
    private String supplier_name;
    private String supplierPartID;
    private String prodCategory;
    private String price;
    private By supplierFieldXpath;
}
