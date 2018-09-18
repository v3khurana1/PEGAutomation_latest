package com.zycus.eInvoice.Uploads;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zycus.eInvoice.PO.PurchaseOrder;

import common.Functions.eInvoice_CommonFunctions;

public class Uploads extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By actionsLinkXpath = By.xpath("//table[contains(@class,'dataTable')]//tr[1]/td[last()]//a[text()='Actions']");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Uploads(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> searchDisplayName
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param displayName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean searchDisplayName(String displayName) throws Exception {
		boolean result = true;
		try {
			result = filterByText("Display Name", displayName) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> searchSupplierName
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param supplierName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean searchSupplierName(String supplierName) throws Exception {
		boolean result = true;
		try {
			result = filterByText("Supplier Name", supplierName) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> uploadNewFile
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param filePath
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean uploadNewFile(String filePath) {
		boolean result = false;
		try {
			findElement(By.id("lnkInvoiceAttachments")).click();
			sendKeys(By.xpath("//*[contains(@id,'attachmentInput')]"), filePath);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//tr[contains(@class,'attachmentRow')]//td[@class='status' and text()='Uploaded']")));
			findElement(By.xpath("//*[@id='attachmentsDOM']//input[@value='Done']")).click();
			/*String uploadedFileTitle = findElement(By.xpath("//table[contains(@class,'dataTable')]/tbody/tr[1]/td[2]/a"))
					.getAttribute("title");*/
			result = true;
			// WRITE CODE TO VERIFY THAT THE FILE IS UPLOADED AND A ROW IS
			// CREATED
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.INFO,"File not uploaded");
		}
		return result;
	}

	/**
	 * <b>Function:</b> takeAction
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param action
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean takeAction(String action) throws Exception {
		boolean result = false;
		try {
			findElement(actionsLinkXpath).click();
			/*findElement(By.xpath("(//*[@id='uploadsListing']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']")).click();
			waitUntilInvisibilityOfElement(By.id("status_overlay_loading_msg"));*/
			clickAndWaitUntilLoaderDisappears(By.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
					+ action + "']"), By.id("status_overlay_loading_msg"));
			switch (action) {
			case "Create Non-PO Invoice":
				break;
			case "Create PO Invoice":
				if (findElement(By
						.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'You will be redirected to PO Listing')]]"))
								.isDisplayed()) {
					findElement(By.xpath("//button/span[text()='OK']")).click();
					PurchaseOrder objPurchaseOrder = new PurchaseOrder(driver, logger);
					waitUntilInvisibilityOfElement(objPurchaseOrder.getProcessingLoader());
					if (findElement(objPurchaseOrder.getAlertBoxmsg()).getText()
							.equals("To create an invoice against a PO, select the PO from the list below"))
						logger.log(LogStatus.INFO, "navigated to Purchase Orders page");
					else
						logger.log(LogStatus.INFO,
								"not navigated to Purchase Orders page or alertBox message not displayed");
				} else
					logger.log(LogStatus.INFO, "You will be directed to PO Listing - confirm promptBox not displayed");
				break;
			case "Create Credit Memo":
				if (findElement(By
						.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'You will be redirected to PO Listing')]]"))
								.isDisplayed()) {
					findElement(By.xpath("//button/span[text()='OK']")).click();
					PurchaseOrder objPurchaseOrder = new PurchaseOrder(driver, logger);
					waitUntilInvisibilityOfElement(objPurchaseOrder.getProcessingLoader());
					if (findElement(objPurchaseOrder.getAlertBoxmsg()).getText()
							.equals("Open a released PO from the list of POs to create a Credit Memo against it"))
						logger.log(LogStatus.INFO, "navigated to Purchase Orders page");
					else
						logger.log(LogStatus.INFO,
								"not navigated to Purchase Orders page or alertBox message not displayed");
				} else
					logger.log(LogStatus.INFO, "You will be directed to PO Listing - confirm promptBox not displayed");
				break;
			case "Create Credit Memo w/o ref":
				break;
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

}
