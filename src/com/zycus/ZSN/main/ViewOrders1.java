// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ViewOrders.java

package com.zycus.ZSN.main;

import common.Functions.*;
import com.relevantcodes.extentreports.ExtentTest;
import java.io.PrintStream;
import org.openqa.selenium.*;

public class ViewOrders1 extends CommonFunctions
{

    public ViewOrders1(WebDriver driver, ExtentTest logger)
        throws Exception
    {
        super(driver, logger);
        confirmDatexpath = By.xpath("//*[@id='ui-datepicker-div']/table//tr/td[contains(@class,'today')]");
        dateBtnid = By.id("dialogPoEDDText");
        dateBtnxpath = By.xpath("//input[@id='dialogPoEDDText']/following-sibling::img");
        commentFieldid = By.id("confirmCommentTextArea");
        confirmBtnid = By.id("confirmPOAction");
        firstRowstatusxpath = By.xpath("//*[contains(@id,'Grid')]/tbody/tr[1]/td[2]/label");
        rejectCommentfieldid = By.id("rejectCommentTextArea");
        rejectBtnid = By.id("rejectPOAction");
        desc_confirm = "confirming the PO";
        searchPOfieldxpath = By.xpath("//*[@id='customerSearch']");
        clearFilterxpath = By.xpath("//*[@id='clearFltr']");
        this.driver = driver;
        this.logger = logger;
    }

    public boolean confirmPO()
        throws Exception
    {
        boolean result = false;
        String POname;
        Thread.sleep(4000L);
        clearFilter();
        filter_by_checkbox("PO Status", "Unconfirmed");
        filter_by_checkbox("PO Type", "Standard");
        Thread.sleep(2000L);
        int rowNum = 3;
        POname = findElement(By.xpath((new StringBuilder("//table[contains(@id,'Grid')]/tbody/tr[")).append(rowNum).append("]/td[2]/a").toString())).getText();
        try
        {
            select_actionPO("Confirm", rowNum);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
        click(dateBtnxpath);
        click(confirmDatexpath);
        sendKeys(commentFieldid, desc_confirm);
        Thread.sleep(4000L);
        findElement(confirmBtnid).click();
   //     click(confirmBtnid);
        Thread.sleep(4000L);
        try
        {
            System.out.println("in try block");
            if(findElement(By.xpath("//div/span[text()='Alert']")).isDisplayed())
            {
                click(By.xpath("//div/span[text()='Alert']/following-sibling::button/span[1]"));
                LogScreenshot("INFO", (new StringBuilder("status for PO ")).append(POname).append(" is not confirmed").toString());
                return result;
            }
        }
        catch(Exception e)
        {
            System.out.println("in catch block");
            e.printStackTrace();
        }
        Thread.sleep(4000L);
        clearFilter();
        Thread.sleep(4000L);
        sendKeys(searchPOfieldxpath, (new StringBuilder(String.valueOf(POname))).append(Keys.ENTER).toString());
        Thread.sleep(4000L);
        WebElement status = findElement(firstRowstatusxpath);
        if(status.getText().contains("Confirmed"))
        {
            LogScreenshot("INFO", (new StringBuilder("status for PO ")).append(POname).append(" is confirmed").toString());
            result = true;
        }
        clearFilter();
        //  goto _L1
      //  Exception e;
       
    //    e.printStackTrace();
//_L1:
        return result;
    }

    public boolean rejectPO(String description)
        throws Exception
    {
        boolean result = false;
        String POname;
        filter_by_checkbox("PO Status", "Unconfirmed");
        filter_by_checkbox("PO Type", "Standard");
        int rowNum = 3;
        POname = findElement(By.xpath((new StringBuilder("//table[contains(@id,'Grid')]/tbody/tr[")).append(rowNum).append("]/td[2]/a").toString())).getText();
        try
        {
            select_actionPO("Reject", rowNum);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        sendKeys(rejectCommentfieldid, description);
        click(rejectBtnid);
        Thread.sleep(1000L);
        WebElement status;
        try
        {
            System.out.println("in try block");
            if(!findElement(By.xpath("//div/span[text()='Alert']")).isDisplayed());
            LogScreenshot("INFO");
            click(By.xpath("//div/span[text()='Alert']/following-sibling::button/span[1]"));
            return result;
        }
        catch(Exception e)
        {
            System.out.println("in catch block");
            e.printStackTrace();
            Thread.sleep(3000L);
            wait_untilVisible(findElement(By.xpath("//a[@id='clearFltr']")));
            clearFilter();
            Thread.sleep(3000L);
            sendKeys(searchPOfieldxpath, (new StringBuilder(String.valueOf(POname))).append(Keys.ENTER).toString());
            Thread.sleep(3000L);
            status = findElement(firstRowstatusxpath);
        }
        if(status.getText().contains("Rejected"))
        {
            LogScreenshot("INFO", (new StringBuilder("status for PO ")).append(POname).append(" is rejected").toString());
            result = true;
        } else
        {
            LogScreenshot("INFO", (new StringBuilder("status for PO ")).append(POname).append(" is not rejected").toString());
        }
        clearFilter();
      //    goto _L1
        Exception e;
      //  e;
    //    e.printStackTrace();
_L1:
        return result;
    }

    private WebDriver driver;
    private ExtentTest logger;
    private By confirmDatexpath;
    private By dateBtnid;
    private By dateBtnxpath;
    private By commentFieldid;
    private By confirmBtnid;
    private By firstRowstatusxpath;
    private By rejectCommentfieldid;
    private By rejectBtnid;
    private String desc_confirm;
    private By searchPOfieldxpath;
    private By clearFilterxpath;
}
