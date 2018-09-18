package com.zycus.IContract.ClauseLibrary;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class ClauseLibrary extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By filterBtnXpath = By.xpath(
			"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]//a[text()='Filter'][ancestor::div[@aria-hidden='false']]");
	private By sourceXpath = By.xpath("//table[@id='clause-grid']/tbody//td[2]/span");
	private By statusXpath = By.xpath("//table[@id='clause-grid']/tbody//td[7]/span");
	private By alternateXpath = By.xpath("//table[@id='clause-grid']/tbody//td[4]/span");
	private By fallbackXpath = By.xpath("//table[@id='clause-grid']/tbody//td[5]/span");

	public ClauseLibrary(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	
	/*public boolean addClause() {
		boolean result = false;
		try {
			findElement(By.xpath("//input[@id='newClause']")).click();
			CreateClause objCreateClause = new CreateClause(driver, logger);
			if (objCreateClause.getPgHead() != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public boolean addClause(CreateClause objCreateClause) {
		boolean result = false;
		try {
			findElement(By.xpath("//input[@id='newClause']")).click();
			if (objCreateClause.getPgHead() != null){
				logger.log(LogStatus.INFO, "Navigated to 'Create Clause' page");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByClauseTitle(String clauseTitle) {
		boolean result = false;
		try {
			result = filterByText("Clause Title", clauseTitle) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByClauseCategory(String category) {
		boolean result = false;
		try {
			result = filterByText("Clause Category", category) ? true : false;
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

	public boolean filterByAlternate(String alternateCategory) {
		boolean result = false;
		try {
			result = filterByChkbox1(alternateCategory, alternateXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByFallback(String fallbackCategory) {
		boolean result = false;
		try {
			result = filterByChkbox1(fallbackCategory, fallbackXpath) ? true : false;
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

	/*public boolean filterByChkbox(String checkBoxLbl, By displayedLabel) throws Exception {
		boolean result = false;
		try {
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"var objContainer = document.evaluate(\"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
							+ "document.evaluate(\"//input[following-sibling::label[contains(.,'" + checkBoxLbl
							+ "')]]\", objContainer, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()");
			findElement(filterBtnXpath).click();
			waitUntilInvisibilityOfElement(processingLoader);
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
	}*/
	
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

	/*public boolean filterByChkbox1(String checkBoxLbl, By displayedLabel) throws Exception {
		boolean result = false;
		try {
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"var objContainer = document.evaluate(\"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
							+ "document.evaluate(\"//input[following-sibling::label[contains(.,'" + checkBoxLbl
							+ "')]]\", objContainer, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()");
			findElement(filterBtnXpath).click();
			waitUntilInvisibilityOfElement(processingLoader);
			List<WebElement> objfilteredList = driver.findElements(displayedLabel);
			for (WebElement obj : objfilteredList) {
				if (checkBoxLbl.contains("without")) {
					if (obj.getText().equals("Add"))
						result = true;
					else {
						result = false;
						break;
					}
				}else
					if (!obj.getText().equals("Add"))
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
	}*/
	
	
	public boolean filterByChkbox1(String checkBoxLbl, By displayedLabel) throws Exception {
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
				if (checkBoxLbl.contains("without")) {
					if (obj.getText().equals("Add"))
						result = true;
					else {
						result = false;
						break;
					}
				}else
					if (!obj.getText().equals("Add"))
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
	
	public boolean workflowSettings() throws Exception{
		boolean result = false;
		WebElement objSkipWorkflow = driver.findElement(By.xpath("//input[@id='skippingEnable']/../div[contains(@class,'text-off-btn')]"));
		flipSwitch(objSkipWorkflow);
		return result;
	}
	
	private void flipSwitch(WebElement obj){
		Random rnd = new Random();
		int temp = 0;
		try {
			temp = rnd.nextInt(1);
			String objClass = obj.getAttribute("class");
			if((temp==0)&&(!objClass.contains(" on"))||(temp==1)&&(objClass.contains(" on")))
				obj.click();				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
