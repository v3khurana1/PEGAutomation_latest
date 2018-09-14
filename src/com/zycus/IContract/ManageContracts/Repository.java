package com.zycus.IContract.ManageContracts;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class Repository extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By filterBtnXpath = By.xpath(
			"//div[contains(@class,'typeFilterContent') and contains(@style,'block')]//a[text()='Filter'][ancestor::div[@aria-hidden='false']]");
	private By contractSrcXpath = By.xpath("//table[@id='authorContractListing-grid']/tbody//td[4]/span");
	private By statusXpath = By.xpath("//table[@id='authorContractListing-grid']/tbody//td[6]/span");
	private By stageXpath = By
			.xpath("//table[@id='authorContractListing-grid']/tbody//td[8]//li[@class='inprogress']/span");
	private By pgHead = By.xpath("//div[@id='main-action-button']/h3[text()='Author Contract']");

	public Repository(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public boolean uploadContract(SelectContractType objSelectContract) throws Exception {
		boolean result = false;
		try {
			clickAndWaitUntilLoaderDisappears(By.id("addnew"));
			//SelectContractType objSelectContract = new SelectContractType(driver, logger, contractType, contractSubType);
			if (objSelectContract.getPgHead() != null){
				logger.log(LogStatus.INFO, "clicked on 'Upload Contract' button");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.INFO, "Not navigated to 'Select Contract Type' page");
			throw new Exception();
		}
		return result;
	}


	public boolean filterByTitle(String title) {
		boolean result = false;
		try {
			result = filterByText("Title", title) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByContractSource(String contractSrc) {
		boolean result = false;
		try {
			result = filterByChkbox(contractSrc, contractSrcXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByContractingParty(String contractingParty) {
		boolean result = false;
		try {
			result = filterByText("Contracting Party", contractingParty) ? true : false;
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

	public boolean filterByCreatedBy(String createdBy) {
		boolean result = false;
		try {
			result = filterByText("Created By", createdBy) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByStage(String stage) {
		boolean result = false;
		try {
			result = filterByChkbox(stage, stageXpath) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean logout() {
		boolean result = false;
		try {
			findElement(By.xpath("//label[@id='logout']")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean viewContract(String contractNum, ContractSummary objSummary){
		boolean result = false;
		try{
			clickAndWaitUntilLoaderDisappears(By.xpath("//*[@id='authorContractListing-grid']/tbody//td[2]/span[@title='"+contractNum+"']"));
			if(objSummary.getPgHead()!=null)
				result = true;
		}catch(Exception e){
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
			int intColNo = getColNum_IContract(fieldName);
			searchAndWaitUntilLoaderDisappears(By.xpath("//table[@id='authorContractListing-grid']/thead/tr[2]/th[" + intColNo + "]//input"), searchValue);
			Thread.sleep(3000);
			List<WebElement> objfilteredTxtList = driver
					.findElements(By.xpath("//table[@id='authorContractListing-grid']/tbody//td[" + intColNo + "]"));
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
