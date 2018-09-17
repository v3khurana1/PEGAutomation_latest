import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class TestIContract extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader = By.xpath("//div[contains(@id,'processing')]");

	public TestIContract(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/*
	 * public static void main(String[] args){ Random rnd = new Random();
	 * System.out.println(0 + rnd.nextInt(32));
	 * 
	 * 
	 * }
	 */

	public void fillContractDetails() {
		List<WebElement> mandatoryFields = driver
				.findElements(By.xpath("//*[@aria-required][not(contains(@class,'edited'))][not(@readonly)][not(@disabled)]"));
		for (WebElement field : mandatoryFields) {
			String fieldType = field.getAttribute("fieldType");
			switch (fieldType) {
			case "INTEGER":
				Random rnd = new Random();
				field.sendKeys(String.valueOf(rnd.nextInt(32)));
				break;
			case "DATE":
				break;
			case "DECIMAL":
				break;
			case "DROPDOWN":
				break;
			case "STRING":
				break;
			}
		}
	}

	/*
	 * String getXPath(Node node) { Node parent = node.getParentNode(); if
	 * (parent == null) { return ""; } return getXPath(parent) + "/" +
	 * node.getNodeName(); }
	 */

	/*private String generateXPATH(WebElement childElement, String current) {
		String childTag = childElement.getTagName();
		if (childTag.equals("html")) {
			return "/html[1]" + current;
		}
		WebElement parentElement = childElement.findElement(By.xpath(".."));
		List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
		int count = 0;
		for (int i = 0; i < childrenElements.size(); i++) {
			WebElement childrenElement = childrenElements.get(i);
			String childrenElementTag = childrenElement.getTagName();
			if (childTag.equals(childrenElementTag)) {
				count++;
			}
			if (childElement.equals(childrenElement)) {
				return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
			}
		}
		return null;
	}*/

	public boolean addLineItem(String itemNo, String desc, String measuremenUnit, int qty, String category,
			String priceType, String currency, float cost, String pricingType, String copyPricingFrm) {
		boolean result = false;
		try {
			int existingLineItems = driver
					.findElements(By.xpath("//table[@id='lineItems-grid']/tbody/tr[contains(@class,'dataRow')]"))
					.size();

			// Line Item Information
			findElement(By.id("lineItemNumber")).sendKeys(itemNo);
			findElement(By.id("description")).sendKeys(desc);
			findElement(By.xpath("//select[@id='uomId']/option[text()='" + measuremenUnit + "']"));
			findElement(By.id("lineItemQuantity")).sendKeys(String.valueOf(qty));
			findElement(By.id("category")).sendKeys(category);
			findElement(By.xpath("//select[@id='priceType']/option[text()='" + priceType + "']"));
			findElement(By.xpath("//select[@id='currency']/option[@currencyname='" + currency + "']"));
			findElement(By.id("lineItemCost")).sendKeys(String.valueOf(cost));

			// Line Item Pricing
			findElement(By.xpath("//select[@id='pricingTypeId']/option[text()='" + pricingType + "']"));
			findElement(By.xpath("//select[@id='lineItemCopyId']/option[text()='" + copyPricingFrm + "']"));

			findElement(By.xpath("//button/span[text()='Save']")).click();
			int lineItems = driver
					.findElements(By.xpath("//table[@id='lineItems-grid']/tbody/tr[contains(@class,'dataRow')]"))
					.size();
			if (lineItems == existingLineItems + 1)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addContractingParty(String contractingParty) {
		boolean result = false;
		try {
			findElement(By.id("addVendor")).click();
			waitUntilVisibilityOfElement(By.id("vendor-grid"));
			findElement(By.xpath("//table[@id='vendor-grid']/tbody//span[contains(text(),'" + contractingParty
					+ "')]/ancestor::tr/td")).click();
			String contractPartyFullNm = findElement(
					By.xpath("//table[@id='vendor-grid']/tbody//span[contains(text(),'" + contractingParty + "')]"))
							.getText();
			findElement(By.xpath("//div[@id='contractingPartyButtons']/input")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			String contractingPartyTitle = findElement(By.xpath("//div[@id='contractingPartyDiv']/div/span")).getText();
			if (contractingPartyTitle.equals(contractPartyFullNm))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean showDBA() {
		boolean result = false;
		try {
			findElement(By.xpath("//div[contains(@class,'dbaToggleBtn')]//li[text()='ShowDBA']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if (findElement(By.xpath("//table[@id='vendor-grid']/thead//label[contains(text(),'DBA Name')]"))
					.isDisplayed())
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean hideDBA() {
		boolean result = false;
		try {
			findElement(By.xpath("//div[contains(@class,'dbaToggleBtn')]//li[text()='HideDBA']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			if (!findElement(By.xpath("//table[@id='vendor-grid']/thead//label[contains(text(),'DBA Name')]"))
					.isDisplayed())
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addContractMilestone(String milestoneTitle, String milestoneStatus, String milestoneType,
			String deliverableType, String description, String DueDt, String notifyTimeCategory, int notificationDays,
			String notifyOn, String repeatNotification, boolean calendarInvite, String contractPercentage,
			float amountPayable) {
		boolean result = false;
		try {
			findElement(By.id("milestoneTitle")).sendKeys(milestoneTitle);
			findElement(By.xpath("//select[@id='milestoneStatus']/option[@value='" + milestoneStatus + "']")).click();
			findElement(By.xpath("//input[@name='milestoneType' and @value='" + milestoneType + "']")).click();
			findElement(By.xpath("//select[@id='deliverableType']/option[@value='" + deliverableType + "']")).click();
			findElement(By.id("description")).sendKeys(description);
			findElement(By.xpath("//input[@id='dueDate']/following-sibling::span")).click();
			selectDate_v1(DueDt);
			findElement(By.xpath("//select[@id='notification']/option[text()='" + notifyTimeCategory + "']")).click();
			if (notifyTimeCategory == "Before")
				findElement(By.xpath(
						"//select[@id='notificationDays']/option[@value='" + String.valueOf(notificationDays) + "']"))
								.click();
			else if (notifyTimeCategory == "Before") {
				findElement(By.xpath("//input[@id='notifyMeOn']/following-sibling::span")).click();
				selectDate_v1(notifyOn);
			}
			findElement(By.xpath("//select[@id='reccuranceDuration']/option[text()='" + repeatNotification + "']"))
					.click();
			if (!calendarInvite)
				findElement(By.id("isOutLookEventSend")).click();
			findElement(By.id("percentage")).sendKeys(contractPercentage);
			findElement(By.id("amountPayable")).sendKeys(String.valueOf(amountPayable));

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Available for emirates
	public boolean addNewDateAlert(String alertTitle, String description, String priority, String triggerAlertFor) {
		boolean result = false;
		try {
			// Alert Details
			findElement(By.id("addNewAlert")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			findElement(By.id("AlertTitle")).sendKeys(alertTitle);
			findElement(By.id("AlertDescription")).sendKeys(description);
			findElement(By.xpath("//ul[@id='AlertPriority']/li[text()='" + priority + "']")).click();
			// Alert Conditions
			findElement(By.xpath("//h3[span[@id='conditionsHeader']]")).click();
			findElement(By.xpath("//select[@id='AlertTriggerMetaData']/option[@value='" + triggerAlertFor + "']"))
					.click();

			// Alert Recipients
			findElement(By.xpath("//h3[span[@id='recipientsHeader']]")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean createClause(String clauseTitle, String clauseCategory, String language, String description) {
		boolean result = false;
		try {
			findElement(By.id("clauseTitle_")).sendKeys(clauseTitle);
			findElement(By.xpath("//select[@id='clauseCategory_']/option[@value='" + clauseCategory + "']")).click();
			findElement(By.xpath("//select[@id='language_']/option[@value='" + language + "']")).click();
			findElement(By.id("description_")).sendKeys(description);
			// Add code to enter Clause text
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean createTemplate(String templateNo, String templateTitle, String templateDesc, String assocBaseType,
			String contractSubType, String region, String category, String businessUnit, String templateFor,
			String language, String sectionName) {
		boolean result = false;
		try {
			findElement(By.id("templateNo")).sendKeys(templateNo);
			findElement(By.id("templateTitle")).sendKeys(templateTitle);
			findElement(By.id("templateDescription")).sendKeys(templateDesc);
			findElement(By.xpath("//select[@id='baseTypeList']/option[text()='" + assocBaseType + "']")).click();
			findElement(By.xpath("//select[@id='contractTypeSubType']/option[text()='" + contractSubType + "']"))
					.click();
			// Select Region
			findElement(By.xpath("//input[@id='regionSearchBx']/following-sibling::span")).click();
			findElement(By.xpath("//input[@id='region' and contains(text(),'" + region + "')]")).click();
			findElement(By.xpath("//input[@id='regionSearchBx']/following-sibling::span")).click();
			// Select Category
			findElement(By.xpath("//input[@id='categorySearchBx']/following-sibling::span")).click();
			findElement(By.xpath("//input[@id='category' and contains(text(),'" + category + "')]")).click();
			findElement(By.xpath("//input[@id='categorySearchBx']/following-sibling::span")).click();
			// Select Business Unit
			findElement(By.xpath("//input[@id='busUnitSearchBx']/following-sibling::span")).click();
			findElement(By.xpath("//input[@id='businessUnit' and contains(text(),'" + businessUnit + "')]")).click();
			findElement(By.xpath("//input[@id='busUnitSearchBx']/following-sibling::span")).click();
			findElement(By.xpath("//select[@id='templateFor']/option[text()='" + templateFor + "']")).click();
			findElement(By.xpath("//select[@id='languageFor']/option[text()='" + language + "']")).click();
			// Click Continue button
			findElement(By.id("saveAndContinue")).click();
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

	public boolean searchContract(String contractType, String contractSubType) {
		boolean result = false;
		try {

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean searchContract(String contractType) {
		boolean result = false;
		try {
			findElement(By.xpath("//ul[@id='browseByTpye']/li/a[span[text()='" + contractType + "']]")).click();
			List<WebElement> objContractTypeList;
			if (driver.findElements(By.id("searchResultsTileView")).size() > 0) {
				objContractTypeList = driver.findElements(By.xpath("//div[@class='contractTile']/div[2]/div[3]/span"));
				for (WebElement objContractType : objContractTypeList) {
					String contType = (objContractType.getAttribute("title").split(":"))[0];
					if (contractType.equals(contType))
						result = true;
					else {
						result = false;
						logger.log(LogStatus.INFO, "");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// div[@class='subHeader' and contains(@style,'block')]//table[@class='zytbl genericDetailsTbl']/tbody/tr/td[not(contains(@style,'none'))]/div/*[@fieldtype]
	// form[@id='zydf-dynamicForm']//*[@data-field-type]

	// if dropdown in flexiform already has a value then
	// element class attribute contains 'zydf-hideMe'
}
