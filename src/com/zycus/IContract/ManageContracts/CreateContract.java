package com.zycus.IContract.ManageContracts;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Framework.ConfigurationProperties;
import common.Functions.eInvoice_CommonFunctions;

public class CreateContract extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	// private By processingLoader =
	// By.xpath("//div[contains(@id,'Processing')]");
	private By processingLoader = By.xpath("//span[@class='zys-loader-icon']");
	private By pgHead = By.xpath("//div[@class='formTitle']/span[text()='Create Contract']");

	public CreateContract(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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

	public boolean startAuthoring(String contractType, String contractSubType, boolean isNegotationAllowed,
			String templateName, ContractDetails objDetails) throws Exception {
		boolean result = false;
		try {
			if (selectIntialDetails(contractType, contractSubType, isNegotationAllowed)) {
				boolean templateSelected = false;
				if (templateName == "")
					templateSelected = selectTemplate();
				else
					templateSelected = selectTemplate(templateName);
				if (templateSelected) {
					waitUntilInvisibilityOfElement(By.id("jqifade"));
					Thread.sleep(1000);
					clickAndWaitUntilLoaderDisappears(By.xpath("//input[@name='startAuthoring']"),processingLoader);
					Thread.sleep(3000);
					try {
						findElement(By.xpath("//div[@id='zys-popup-content']/following-sibling::div/button")).click();
					} catch (Exception e) {
					}
					waitUntilInvisibilityOfElement(processingLoader);
					if (objDetails.getPgHead() != null) {
						result = true;
						LogScreenshot("INFO",
								"navigated to Contract details page on clicking Start Authoring button");
					}
				} else {
					LogScreenshot("INFO", "template not selected");
					throw new Exception();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "not navigated to Contract details page");
		}
		return result;
	}

	public boolean startAuthoring(String contractType, String contractSubType, boolean isNegotationAllowed, ContractDetails objDetails) throws Exception {
		boolean result = false;
		try {
			if (selectIntialDetails(contractType, contractSubType, isNegotationAllowed)) {
				if (uploadExternaltemplate(objDetails))
					result = true;
				else {
					LogScreenshot("INFO", "template not selected");
					throw new Exception();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "not navigated to Contract details page");
		}
		return result;
	}

	/*public boolean uploadExternaltemplate() throws Exception {
		boolean result = false;
		try {
			findElement(By.id("externalButton")).click();
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			driver.findElement(By.xpath("//input[@type='button' and @value='Browse']/following-sibling::input"))
					.sendKeys(System.getProperty("user.dir") + config.getProperty("upload_docxfile_path"));
			waitUntilInvisibilityOfElement(By.xpath("//input[@id='extractClauses' and @disabled]"));
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'blockOverlay')]"));
			try {
				WebElement continueBtn = findElement(By.xpath("//input[@id='extractClauses']"));
				LogScreenshot("INFO", "file uploaded successfully");
				continueBtn.click();
			} catch (Exception e) {
				LogScreenshot("INFO", "file not uploaded successfully");
			}
			ContractDetails objDetails = new ContractDetails(driver, logger);
			if (objDetails.getPgHead() != null) {
				result = true;
				LogScreenshot("INFO", "navigated to Contract details page on cliking Continue button");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "not navigated to Contract details page");
		}
		return result;

	}*/
	
	public boolean uploadExternaltemplate(ContractDetails objDetails) throws Exception {
		boolean result = false;
		try {
			findElement(By.id("externalButton")).click();
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			driver.findElement(By.xpath("//input[@type='button' and @value='Browse']/following-sibling::input"))
					.sendKeys(System.getProperty("user.dir") + config.getProperty("upload_docxfile_path"));
			waitUntilInvisibilityOfElement(By.xpath("//input[@id='extractClauses' and @disabled]"));
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'blockOverlay')]"));
			try {
				WebElement continueBtn = findElement(By.xpath("//input[@id='extractClauses']"));
				LogScreenshot("INFO", "file uploaded successfully");
				continueBtn.click();
			} catch (Exception e) {
				LogScreenshot("INFO", "file not uploaded successfully");
			}
			//ContractDetails objDetails = new ContractDetails(driver, logger);
			if (objDetails.getPgHead() != null) {
				result = true;
				LogScreenshot("INFO", "navigated to Contract details page on cliking Continue button");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "not navigated to Contract details page");
		}
		return result;

	}

	public boolean startAuthoring(String contractType, String contractSubType, boolean isNegotationAllowed,
			boolean isExternalTemplate, ContractDetails objDetails) throws Exception {
		boolean result = false;
		try {
			if (isExternalTemplate)
				startAuthoring(contractType, contractSubType, isNegotationAllowed, objDetails);
			else
				startAuthoring(contractType, contractSubType, isNegotationAllowed, "", objDetails);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	private boolean selectIntialDetails(String contractType, String contractSubType, boolean isNegotiationAllowed)
			throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//select[@name='contractType']/option[text()='" + contractType + "']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			findElement(By.xpath("//select[@name='subTypeName']/option[text()='" + contractSubType + "']")).click();
			// waitUntilVisibilityOfElement(By.id("tempateContainer"));
			if (isNegotiationAllowed)
				try {
					findElement(By.xpath("//div[@id='bypassNegotiationWorkflowSelect']/span[@value='false']")).click();
					waitUntilVisibilityOfElement(By.xpath("//div[@id='suggestedTemplateDiv']/div[1]"));
					result = true;
				} catch (Exception e) {
					LogScreenshot("INFO", "Options to select Negotiation round for contract - not displayed");
				}
			else {
				LogScreenshot("INFO",
						"Contract Type : " + contractType + "& Contract Sub Type : " + contractSubType + " selected");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO",
					"Contract Type : " + contractType + "& Contract Sub Type : " + contractSubType + " not selected");
			throw new Exception();
		}
		return result;
	}

	private boolean selectTemplate(String templateName) throws Exception {
		boolean result = false;
		try {
			findElement(
					By.xpath("(//div[@class='tileTitle ellipsisBx' and contains(text(),'" + templateName + "')])[1]"))
							.click();
			try {
				findElement(By.xpath("//div[@id='zys-popup-content']/parent::div//button[text()='OK']")).click();
			} catch (Exception e) {
			}
			if (driver.findElements(By.xpath("//div[contains(@class,'selected')]")).size() > 0) {
				LogScreenshot("INFO", "Template : " + templateName + " selected");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "Template : " + templateName + " not selected");
		}
		return result;
	}

	private boolean selectTemplate() throws Exception {
		boolean result = false;
		try {
			WebElement firstTemplate = findElement(By.xpath("(//div[@class='tileTitle ellipsisBx'])[1]"));
			firstTemplate.click();
			try {
				findElement(By.xpath("//div[@id='zys-popup-content']/parent::div//button[text()='OK']")).click();
			} catch (Exception e) {
			}
			if (driver.findElements(By.xpath("//div[contains(@class,'selected')]")).size() > 0) {
				LogScreenshot("INFO", "Template : " + firstTemplate.getText() + " selected");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "No template selected");
		}
		return result;
	}

	public boolean navigate_ContractSubTabs(String tabName) throws Exception {
		boolean result = false;
		String displayedPgHeader = tabName;
		try {
			if (tabName.equals("Documents"))
				displayedPgHeader = "Contractual " + tabName;
			findElement(By.xpath("//div[@class='leftMenuDiv']/ul/li//span[contains(@class,'ellipsis')][@title='"
					+ tabName + "']/ancestor::span[@id]")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if (driver
					.findElement(By.xpath(
							"//span[@id='headerLabel']/following-sibling::h2[text()='" + displayedPgHeader + "']"))
					.isDisplayed()) {
				LogScreenshot("INFO", "navigated to contract sub Tab: " + displayedPgHeader);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "not navigated to contract sub Tab: " + displayedPgHeader);
			throw new Exception();
		}
		return result;

	}

}
