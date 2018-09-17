package com.zycus.IContract.ClauseLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

public class CreateClause extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//div[@id='dialog-confirm']//span[contains(text(),'Create Clause')]");
	private String clauseTitle;
	private String clauseCategory;
	private String assocBaseType;
	private String language;
	private String clauseText;


	public CreateClause(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public CreateClause(WebDriver driver, ExtentTest logger, String clauseTitle, String clauseCategory,
			String assocBaseType, String language, String clauseText) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.clauseTitle = clauseTitle;
		this.clauseCategory = clauseCategory;
		this.assocBaseType = assocBaseType;
		this.language = language;
		this.clauseText = clauseText;
	}

	public boolean createClause(String description) {
		boolean result = false;
		try {
			findElement(By.id("clauseTitle_")).sendKeys(clauseTitle);
			findElement(By.xpath("//select[@id='clauseCategory_']/option[@value='" + clauseCategory + "']")).click();
			selectDropdownChkBx("basetypeSearchBx", assocBaseType);
			findElement(By.xpath("//select[@id='language_']/option[@value='" + language + "']")).click();
			findElement(By.id("description_")).sendKeys(description);
			// Add code to enter Clause text
			findElement(By.xpath("//input[@title='Save and Activate']")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void selectDropdownChkBx(String txtInputObjId, String checkBxLabel) {
		try {
			findElement(By.xpath("//input[@id='" + txtInputObjId + "']/following-sibling::span")).click();
			findElement(By.xpath("//input[@id='" + txtInputObjId + "']/parent::div//li[contains(text(),'" + checkBxLabel
					+ "')]/input[@type='checkbox']")).click();
			findElement(By.xpath("//input[@id='" + txtInputObjId + "']/following-sibling::span")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the pgHead
	 */
	public By getPgHead() {
		return pgHead;
	}

	/**
	 * @param pgHead
	 *            the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}

}
