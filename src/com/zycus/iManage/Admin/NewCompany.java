package com.zycus.iManage.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Framework.ConfirmationDialog;
import common.Functions.iManage_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> NewCompany.java</br>
 * <br>
 * <b> Description: </b> To perform iManage- Admin- New Company page </br>
 * <br>
 * <b> Functions: </br>
 * </b> None <br>
 * 
 * @author Varun Khurana
 * @since Oct 2018
 */

public class NewCompany extends iManage_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By categoryUploadDocBtn = By.id("categoryFileUploader");
	private By BusinessTreeUploadDocBtn = By.id("divisionFileUploader");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public NewCompany(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean saveCategoryTree() {
		boolean result = false;
		try {
			if (importCategoryTree()) {
				findElement(By.id("saveCategoryTreeBtn")).click();
				if (driver.findElement(ConfirmationDialog.getDialogTitle()).isDisplayed()) {
					if (driver.findElement(ConfirmationDialog.getPopupMsg()).getText()
							.contains("delete the earlier records")) {
						driver.findElement(ConfirmationDialog.getDialogYesBtn()).click();
						result = true;
					} else
						logger.log(LogStatus.INFO, "Incorrect Confirmation Dialog displayed");
				} else
					logger.log(LogStatus.INFO, "Confirmation Dialog not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean appendCategoryTree() {
		boolean result = false;
		try {
			if (importCategoryTree()) {
				findElement(By.id("appendCategoryTreeBtn")).click();
				if (driver.findElement(ConfirmationDialog.getDialogTitle()).isDisplayed()) {
					if (driver.findElement(ConfirmationDialog.getPopupMsg()).getText()
							.contains("merge the earlier and new records")) {
						driver.findElement(ConfirmationDialog.getDialogYesBtn()).click();
						result = true;
					} else
						logger.log(LogStatus.INFO, "Incorrect Confirmation Dialog displayed");
				} else
					logger.log(LogStatus.INFO, "Confirmation Dialog not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean importCategoryTree() {
		boolean result = false;
		boolean saveBtnEnabled = false;
		boolean appendBtnEnabled = false;
		try {
			addAttachment(categoryUploadDocBtn, "iManage_uploadCategoryfile_path");
			waitUntilVisibilityOfElement(
					By.xpath("//label[@id='categoryUploadedFileName' and contains(@style,'inline')]"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.INFO, "Category tree not uploaded");
		}
		// input[@id='categoryFileUploader']/../../../following-sibling::div/label[contains(@style,'inline')]
		if (driver.findElement(By.xpath("//input[@id='saveCategoryTreeBtn']")).isEnabled())
			saveBtnEnabled = true;
		else
			logger.log(LogStatus.INFO, "Save button not enabled or Category Tree not uploaded");
		if (driver.findElement(By.xpath("//input[@id='appendCategoryTreeBtn']")).isEnabled())
			appendBtnEnabled = true;
		else
			logger.log(LogStatus.INFO, "Append button not enabled or Category Tree not uploaded");
		if (saveBtnEnabled && appendBtnEnabled)
			result = true;
		return result;

	}

	public boolean saveBusinessTree() {
		boolean result = false;
		try {
			if (importBusinessTree()) {
				findElement(By.id("saveDivisionTreeBtn")).click();
				if (driver.findElement(ConfirmationDialog.getDialogTitle()).isDisplayed()) {
					if (driver.findElement(ConfirmationDialog.getPopupMsg()).getText()
							.contains("delete the earlier records")) {
						driver.findElement(ConfirmationDialog.getDialogYesBtn()).click();
						result = true;
					} else
						logger.log(LogStatus.INFO, "Incorrect Confirmation Dialog displayed");
				} else
					logger.log(LogStatus.INFO, "Confirmation Dialog not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean appendBusinessTree() {
		boolean result = false;
		try {
			if (importBusinessTree()) {
				findElement(By.id("appendDivisionTreeBtn")).click();
				if (driver.findElement(ConfirmationDialog.getDialogTitle()).isDisplayed()) {
					if (driver.findElement(ConfirmationDialog.getPopupMsg()).getText()
							.contains("delete the earlier records")) {
						driver.findElement(ConfirmationDialog.getDialogYesBtn()).click();
						result = true;
					} else
						logger.log(LogStatus.INFO, "Incorrect Confirmation Dialog displayed");
				} else
					logger.log(LogStatus.INFO, "Confirmation Dialog not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean importBusinessTree() {
		boolean result = false;
		try {
			addAttachment(BusinessTreeUploadDocBtn, "iManage_BusinessTreefile_path");
			waitUntilVisibilityOfElement(
					By.xpath("//label[@id='divisionUploadedFileName' and contains(@style,'inline')]"));
			if (driver.findElement(By.xpath("//label[@id='divisionUploadedFileName' and contains(@style,'inline')]"))
					.isDisplayed())
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.INFO, "Business tree not uploaded");
		}
		return result;

	}

	public boolean activateCurrency() {
		boolean result = false;
		try {
			findElement(By.id("activateCMDCurrency")).click();
			if (driver.findElement(By.xpath("//label[@id='currencyResponseLabel']")).getText()
					.contains("Activated Successfully"))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
