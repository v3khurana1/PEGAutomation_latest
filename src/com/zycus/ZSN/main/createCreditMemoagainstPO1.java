// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   createCreditMemoagainstPO.java

package com.zycus.ZSN.main;
import common.Functions.*;
import Invoice.ViewInvoices;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElements;

// Referenced classes of package Invoice:
//            ViewInvoices

public class createCreditMemoagainstPO1 extends CommonFunctions {

	public createCreditMemoagainstPO1(WebDriver driver, ExtentTest logger) throws Exception {
		super(driver, logger);
		invoiceNoid = By.id("txtInvoiceNumber");
		itemLessId = By.id("itemLess");
		this.driver = driver;
		this.logger = logger;
	}

	public boolean Create_new_Credit_Memo(String selectedPO) throws Exception {
		boolean status = false;
		try {
			select_PO_to_create_Inv_Creditmemo(selectedPO);
			String creditMemono = String.valueOf(generateNo());
			sendKeys(invoiceNoid, creditMemono);
			logger.log(LogStatus.INFO, (new StringBuilder("creditmemo no: ")).append(creditMemono).toString());
			selectDate("txtInvoiceDate");
			// modified by Anisha on 5th July 2018
			Thread.sleep(2000);
			selectRemit_ToAddress();
			List<WebElement> rownum1 = driver.findElements(By.xpath("//table[@class='zytbl invItemsGrid']/tbody/tr"));
			int rownum = rownum1.size();
			System.out.println("row number is " + rownum);
			scroll_into_view_element(findElement(By.xpath("//table[@class='zytbl invItemsGrid']")));
			Thread.sleep(2000L);
			try {
				if (!findElement(itemLessId).isSelected()) {
					int i = 1;
					do {
						System.out.println("in do block");
						clickRowTableXpath = By.xpath(
								"//*[@id='frmInvoice']//table//tr[" + i + "]/td[1]/input[@class='input chkItem']");
						click(clickRowTableXpath);
					
						if (findElement(By.xpath("//table[@class='zytbl invItemsGrid']/tbody/tr["+i+"]/td[11]//span[contains(@class,'iError')]")).isDisplayed())
						{
							System.out.println("inside if block when error is displayed");
							click(clickRowTableXpath);
							i++;
						}
						else 
							break;
					} while (i <= rownum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 Thread.sleep(2000);
			scroll_into_view_element(driver.findElement(By.id("btnSubmit")));
						// scroll_down();
			scroll_to_BottomofaPage();
			click_Submit();
			LogScreenshot("INFO", "entered all fields and clicked on Submit");
			waitUntilInvisibilityOfElement(
					By.xpath("//div[@id='status_overlay_saveInvoice']/div[contains(text(),'Saving')]"));
			try {
				do_not_show();
			} catch (Exception exception1) {
			}
			Thread.sleep(5000L);
			ViewInvoices objInvoice = new ViewInvoices(driver, logger);
			if (findElement(objInvoice.getInvoiceGridId()) != null) {
				logger.log(LogStatus.INFO, "successfully created credit memo against PO");
				status = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return status;
	}

	private WebDriver driver;
	private Actions action;
	private By invoiceNoid;
	private By itemLessId;
	private By clickRowTableXpath;
	private String credit_memoNo;
	private ExtentTest logger;
}
