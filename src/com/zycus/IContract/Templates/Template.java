package com.zycus.IContract.Templates;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

public class Template extends eInvoice_CommonFunctions {

	private WebDriver driver;
	//private ExtentTest logger;
	private By filterBtnXpath = By.xpath(
			"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]//a[text()='Filter'][ancestor::div[@aria-hidden='false']]");
	private By sourceXpath = By.xpath("//table[@id='TemplateGridImpl-grid']/tbody//td[2]/span");
	private By statusXpath = By.xpath("//table[@id='TemplateGridImpl-grid']/tbody//td[6]/span");

	public Template(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}

	/*
	 * public boolean createTemplate() { boolean result = false; try {
	 * findElement(By.xpath("//input[@name='createTemplate']")).click();
	 * CreateTemplate objCreateTemplate = new CreateTemplate(driver, logger);
	 * if(objCreateTemplate.getPgHead()!=null) result= true; } catch (Exception
	 * e) { e.printStackTrace(); } return result; }
	 */

	public boolean createTemplate(CreateTemplate objCreateTemplate) {
		boolean result = false;
		try {
			findElement(By.xpath("//input[@name='createTemplate']")).click();
			if (objCreateTemplate.getPgHead() != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByTemplateNum(String templateNumOrName) {
		boolean result = false;
		try {
			result = filterByText("Template No. - Name", templateNumOrName) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByContractType(String contractType) {
		boolean result = false;
		try {
			result = filterByText("Contract Type", contractType) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterBySubType(String subType) {
		boolean result = false;
		try {
			result = filterByText("Sub Type", subType) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterBySource(String source) {
		boolean result = false;
		try {
			result = filterByChkbox(source, sourceXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByStatus(String status) {
		boolean result = false;
		try {
			result = filterByChkbox(status, statusXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByText
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fieldName
	 * @param searchValue
	 * @return result - True/False
	 */

	public boolean filterByText(String fieldName, String searchValue) {
		boolean result = false;
		try {
			int intColNo = getColNum(fieldName);
			searchAndWaitUntilLoaderDisappears(By.xpath("//table[@id='TemplateGridImpl-grid']/thead/tr[2]/th[" + intColNo + "]//input"), searchValue);
			Thread.sleep(3000);
			List<WebElement> objfilteredTxtList = driver
					.findElements(By.xpath("//table[@id='TemplateGridImpl-grid']/tbody//td[" + intColNo + "]"));
			for (WebElement obj : objfilteredTxtList) {
				if (obj.getText().contains(searchValue))
					result = true;
				else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : filterByChkbox
	 * 
	 * @param checkBoxLbl
	 *            --------------------------------------------------------------
	 *            -------------------
	 * @throws Exception
	 */

	public boolean filterByChkbox(String checkBoxLbl, By displayedLabel) throws Exception {
		boolean result = false;
		try {
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"var objContainer = document.evaluate(\"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
							+ "document.evaluate(\"//input[following-sibling::label[contains(.,'" + checkBoxLbl
							+ "')]]\", objContainer, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()");
			clickAndWaitUntilLoaderDisappears(filterBtnXpath);
			List<WebElement> objfilteredList = driver.findElements(displayedLabel);
			for (WebElement obj : objfilteredList) {
				if (obj.getText().equals(checkBoxLbl))
					result = true;
				else {
					result = false;
					break;
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
