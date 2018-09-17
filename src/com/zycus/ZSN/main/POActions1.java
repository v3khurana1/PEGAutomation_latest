// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   POActions.java

package com.zycus.ZSN.main;

import common.Functions.CommonFunctions1;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class POActions1 extends CommonFunctions1 {

	public POActions1(WebDriver driver, Actions action, ExtentTest logger) throws Exception {
		super(driver, logger);
		downloadBtnid = By.id("pdfPOLink");
		optionBtnxpath = By.id("actDrop");
		createShipBtnxpath = By.xpath("//*[@id='actDrop']/div//a[contains(@class,'createAsnLink')]");
		carrierFieldid = By.id("txtShippingCarrier");
		shipNoid = By.id("txtShipmentNo");
		shipViaid = By.id("txtShipmentVia-customBtn");
		serviceLevelid = By.id("txtServiceLevel-customBtn");
		clearShipNoid = By.id("clearShipmentNoticeDate");
		poXMLid = By.id("poCxmlLink");
		XmlDataId = By.id("preCxmlData");
		selectItemCheckboxXpath = By
				.xpath("//*[@id='frmShipmentNoticeCreate']//table//tr[1]/td/input[contains(@id,'chkItemNo')]");
		this.driver = driver;
		this.action = action;
		this.logger = logger;
	}

	public boolean downloadPOasPDF() throws Exception {
		boolean result = false;
		try {
			findElement(optionBtnxpath).click();
			findElement(downloadBtnid).click();
			result = true;
			logger.log(LogStatus.INFO, "your pdf is stored in C:\\Users\\anisha.jain\\Downloads");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean previewPOascXML() throws Exception {
		boolean status = false;
		try {
			if (!findElement(optionBtnxpath).getAttribute("class").contains("open"))
				findElement(optionBtnxpath).click();
			findElement(poXMLid).click();
			Thread.sleep(2000L);
			if (findElement(XmlDataId).isDisplayed()) {
				action.sendKeys(new CharSequence[] { Keys.ESCAPE }).build().perform();
				LogScreenshot("INFO", "able to preview the XML");
				status = true;
			}
		} catch (Exception exception) {
		}
		return status;
	}

	public boolean createShipmentNotice(String action, String carrier, String Shipment_No, String Shipped_Via,
			String Service_level) throws Exception {
		Boolean status = Boolean.valueOf(false);
		try {
			if (!findElement(optionBtnxpath).getAttribute("class").contains("open"))
				findElement(optionBtnxpath).click();
			Thread.sleep(2000L);
			findElement(createShipBtnxpath).click();
			LogScreenshot("INFO", "navigated to New Shipping Notice page");
			sendKeys(carrierFieldid, (new StringBuilder(String.valueOf(carrier))).append(Keys.ENTER).toString());
			sendKeys(shipNoid, Shipment_No);
			findElement(shipViaid).click();
			findElement(By.xpath((new StringBuilder("//ul/li/a[text()='")).append(Shipped_Via).append("']").toString()))
					.click();
			findElement(clearShipNoid).click();
			selectDate("txtShipmentDate");
			findElement(serviceLevelid).click();
			findElement(
					By.xpath((new StringBuilder("//ul/li/a[text()='")).append(Service_level).append("']").toString()))
							.click();
			selectDate("txtShippingEdd");
			findElement(selectItemCheckboxXpath).click();
			String s;
			switch ((s = action).hashCode()) {
			default:
				break;

			case -1807668168:
				if (s.equals("Submit")) {
					click_Submit();
					LogScreenshot("INFO", "clicked on Submit, Shipment created successfully");
				}
				break;

			case 66292097:
				if (s.equals("Draft")) {
					save_Draft();
					LogScreenshot("INFO", "Draft status");
				}
				break;

			case 2011110042:
				if (s.equals("Cancel")) {
					Cancel();
					LogScreenshot("INFO", "Cancel status");
				}
				break;
			}
			status = Boolean.valueOf(true);
		} catch (Exception exception) {
		}
		return status.booleanValue();
	}

	private WebDriver driver;
	private Actions action;
	private ExtentTest logger;
	private By downloadBtnid;
	private By optionBtnxpath;
	private By createShipBtnxpath;
	private By carrierFieldid;
	private By shipNoid;
	private By shipViaid;
	private By serviceLevelid;
	private By clearShipNoid;
	private By poXMLid;
	private By XmlDataId;
	private By selectItemCheckboxXpath;
}
