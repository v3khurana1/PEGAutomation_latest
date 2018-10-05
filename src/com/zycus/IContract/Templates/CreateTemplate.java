package com.zycus.IContract.Templates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.Functions.eInvoice_CommonFunctions;

public class CreateTemplate extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//div[@id='main-action-button']/h3[text()='Create Template']");
	private String templateNo;
	private String templateTitle;
	private String assocBaseType;
	private String contractSubType;
	private String templateFor;
	private String language;

	public CreateTemplate(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public CreateTemplate(WebDriver driver, ExtentTest logger, String templateNo, String templateTitle,
			String assocBaseType, String contractSubType, String templateFor, String language) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.templateNo = templateNo;
		this.templateTitle = templateTitle;
		this.assocBaseType = assocBaseType;
		this.contractSubType = contractSubType;
		this.templateFor = templateFor;
		this.language = language;
	}

	public boolean enterTemplateDetails(String templateDesc, String region, String category, String businessUnit) {
		boolean result = false;
		try {
			findElement(By.id("templateNo")).sendKeys(templateNo);
			findElement(By.id("templateTitle")).sendKeys(templateTitle);
			findElement(By.id("templateDescription")).sendKeys(templateDesc);
			findElement(By.xpath("//select[@id='baseTypeList']/option[text()='" + assocBaseType + "']")).click();
			findElement(By.xpath("//select[@id='contractTypeSubType']/option[text()='" + contractSubType + "']"))
					.click();
			selectDropdownChkBx("regionSearchBx", region);
			selectDropdownChkBx("categorySearchBx", category);
			selectDropdownChkBx("busUnitSearchBx", businessUnit);
			findElement(By.xpath("//select[@id='templateFor']/option[text()='" + templateFor + "']")).click();
			findElement(By.xpath("//select[@id='languageFor']/option[text()='" + language + "']")).click();
			// Click Continue button
			findElement(By.id("saveAndContinue")).click();
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

	public boolean addSection(String sectionName) {
		boolean result = false;
		try {
			findElement(By.xpath("//input[@title='Add Section']")).click();
			int noOfSectionAdded = driver
					.findElements(By.xpath("//tbody[@id='sectionClauses']//div[contains(@class,'newSection')]")).size();
			if (noOfSectionAdded == 1)
				findElement(By.xpath("//tbody[@id='sectionClauses']//div[contains(@class,'newSection')]"))
						.sendKeys(sectionName);
			findElement(By.xpath("//tbody[@id='sectionClauses']//input[@value='Save']")).click();
			if (driver.findElements(By.xpath("//tbody[@id='sectionClauses']//div/span")).size() > 0) {
				logger.log(LogStatus.INFO, "section added successfully");
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addClause(String sectionName, String clauseTitle) {
		boolean result = false;
		try {
			int existingClauses = driver
					.findElements(
							By.xpath("//tr[@class='MoveableRow']/td[div/span[contains(text(),'" + clauseTitle + "')]]"))
					.size();
			clickAndWaitUntilLoaderDisappears(By.xpath("//tbody[@id='sectionClauses']//tr[td/div/span[text()='" + sectionName
					+ "']]//span[@name='addClause']"));
			if (findElement(By.xpath("//div[@aria-describedby='zydev-popup'][div/span[text()='Clause List']]"))
					.isDisplayed())
				findElement(By.xpath("(//table[@id='selective']/tbody[2]/tr[td[position()=3 and span[contains(text(),'"
						+ clauseTitle + "')]]])[1]//input")).click();
			findElement(By.xpath("//div[@aria-describedby='zydev-popup']//button[span[text()='Done']]")).click();
			int noOfClauses = driver
					.findElements(
							By.xpath("//tr[@class='MoveableRow']/td[div/span[contains(text(),'" + clauseTitle + "')]]"))
					.size();
			if (noOfClauses == existingClauses + 1) {
				logger.log(LogStatus.INFO, "Clause added successfully");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
