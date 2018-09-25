// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewUploads.java

package com.zycus.ZSN.main;

import common.Functions.*;
import Framework.ConfigurationProperties;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class UploadsNew extends CommonFunctions
{

    public UploadsNew(WebDriver driver, ExtentTest logger, String credit_memoNo, String invoice_no)
        throws Exception
    {
        super(driver, logger);
        this.driver = driver;
        this.logger = logger;
    }

    public UploadsNew(WebDriver driver, ExtentTest logger)
        throws Exception
    {
        super(driver, logger);
        this.driver = driver;
        this.logger = logger;
    }

    public boolean upload_file(ConfigurationProperties configurationProperties)
        throws Exception
    {
        boolean status = false;
        click(By.id("uploadNewFile"));
        if(driver.findElements(By.xpath("//label[@for='supplierCompany']/ancestor::li/div/div[text()]")).size()==0){
        Select suppDropdown = new Select(findElement(By.id("supplierCompany")));
        suppDropdown.selectByIndex(1);
        logger.log(LogStatus.INFO, "Selectes 1st supplier from the dropdown");
        }
        By selectfile = By.id("attachmentInput_uploadFileAttachment");
        sendKeys(selectfile, (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append(configurationProperties.getProperty("upload_file_path")).toString());
        WebDriverWait wait = new WebDriverWait(driver, 30L);
        WebElement locator = findElement(By.xpath("//table/tbody/tr[contains(@id,'attachment_uploadFileAttachment')]/td[3]"));
        if(((Boolean)wait.until(ExpectedConditions.textToBePresentInElement(locator, "Uploaded"))).booleanValue())
        {
            logger.log(LogStatus.INFO, "Uploaded the file to ZSN!!");
            click(By.id("closeAttachments"));
            status = true;
        }
        return status;
    }

    private WebDriver driver;
    private String credit_memoNo;
    private String invoice_no;
    private ExtentTest logger;
}
