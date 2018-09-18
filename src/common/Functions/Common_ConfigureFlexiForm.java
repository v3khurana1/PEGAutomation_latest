package common.Functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
//import common.eForms.Common_ConfigureFlexiForm;

public class Common_ConfigureFlexiForm extends common.eInvoice_CommonFunctions {

	private WebDriver driver;
	private By pgHead = By.xpath("//div[@class='zydf-productInfo']/h1[contains(text(),'Configure FlexiForm')]");
	private ExtentTest logger;
	private String sectionName;
	private String fieldType;
	private String fieldName;
	private String fieldDisplayName;
	//private String product;

	public Common_ConfigureFlexiForm(WebDriver driver, ExtentTest logger, String product) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		//this.product = product;
	}
	

	public Common_ConfigureFlexiForm(WebDriver driver, ExtentTest logger, String sectionName, String fieldType,
			String fieldName, String fieldDisplayName) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.sectionName = sectionName;
		this.fieldType = fieldType;
		this.fieldName = fieldName + String.valueOf(generateNo());
		this.fieldDisplayName = fieldDisplayName;
	}

	public boolean addSection_flexiForm(String description, String layout) {
		boolean result = false;
		try {
			// Click on Add Section
			findElement(By.xpath("//div[@id='zydf-dynamicForm']//a")).click();

			if (findElement(By.xpath("//div[div/span[text()='Create Section']]")).isDisplayed())
				findElement(By.id("zydf-dfSectionName")).sendKeys(sectionName);
			findElement(By.id("zydf-dfSectionDescription")).sendKeys(description);
			findElement(By.xpath("//select[@id='zydf-dfSectionLayout']/option[contains(text(),'" + layout + "')]"))
					.click();
			findElement(By.id("zydf-dfSectionSave")).click();

			// ADD CODE TO VERIFY IF SECTION ADDED
			if (findElement(By.xpath("//li/div/span[text()='" + sectionName + "']")) != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * <b>Function:</b> addfield_FlexiForm
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param sectionName
	 * @param fieldType
	 * @param fieldName
	 * @return result - True/False
	 */

	public boolean addfield_FlexiForm(String defaultValue, int maxChar, boolean mandatory) {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@class='zydf-customField']")).click();
			//JavascriptExecutor js = (JavascriptExecutor)driver;
			//js.executeScript("var srcElement");
			//document.evaluate(\"//div[contains(@class,'qtip qtip-default filterPopups') and contains(@style,'block')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue
			//DO NOT REMOVE ELSE DRAG-DROP WILL NOT WORK
			Thread.sleep(3000);
			waitUntilVisibilityOfElement(By.xpath("//div[@class='zydf-customField']/div[contains(@style,'block')]"));
			/*WebElement srcElement = driver.findElement(
					By.xpath("//ul[@id='zydf-devLeftDragList']/li/a[span/following-sibling::text()[contains(.,'"
							+ fieldType + "')]]"));*/
			WebElement srcElement = driver.findElement(
					By.xpath("//*[@id='zydf-devLeftDragList']//a/span[following-sibling::text()[contains(.,'"+fieldType+"')]]"));
			
			WebElement dstElement = driver.findElement(
					By.xpath("//li/div[span[text()='Auto_Section']]/following-sibling::ul"));
			drag_drop(srcElement, dstElement);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[div/span[contains(text(),'Field Properties')]]")));
			
			//WebElement obj = ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[div/span[contains(text(),'Field Properties')]]"))
			//Thread.sleep(5000);
			// Click on Add Field
			/*
			 * findElement(By.xpath(
			 * "//*[@id='dynamicForm']/ul/li[*]/div[contains(text(),'" +
			 * sectionName +
			 * "')]/following-sibling::ul//a[span/following-sibling::text()[contains(.,'Add Field')]]"
			 * )).click(); if
			 * (findElement(By.xpath("//div[div/span[text()='Create Field']]")).
			 * isDisplayed())
			 * findElement(By.xpath("//*[@id='dfFieldSelect']//li[*]/a[text()='"
			 * + fieldType + "']")).click();
			 */
			enterFieldProperties_FlexiForm(fieldDisplayName, defaultValue, maxChar, mandatory);

			// Verifying if the field is added to the Section
			if (fieldType.equals("descriptive field")) {
				if (findElement(By.xpath(
						"//li[div/div[contains(@class,'descriptive')]//div[text()='" + fieldName + "']]")) != null)
					result = true;
			} else {
				if (findElement(By.xpath("//li[div/div[label/span[contains(text(),'" + fieldName
						+ "')]]//input]")) != null)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFlexiForm() {
		boolean result = false;
		try {
			findElement(By.xpath("//input[@name='saveEform']")).click();
			waitUntilInvisibilityOfElement(By.id("status_overlay_saveFlexiForm"));
			Common_AllForms objForms = new Common_AllForms(driver, logger);
			result = driver.findElements(objForms.getPgHead()).size()>0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getFieldPartialName_FlexiForm(String fieldType) {
		String partialName = null;
		switch (fieldType) {
		case "Text Field":
			partialName = "TEXT";
			break;
		case "Text Area":
			partialName = "TEXT_AREA";
			break;
		case "Numeric Field":
			partialName = "NUMERIC";
			break;
		case "Date Field":
			partialName = "DATE_TIME";
			break;
		case "Radio Field":
			partialName = "RADIO";
			break;
		case "Checkbox":
			partialName = "CHECKBOX";
			break;
		case "Dropdown":
			partialName = "DROP_DOWN";
			break;
		case "Multi-Select":
			partialName = "MULTI_SELECT";
			break;
		case "Attachment":
			partialName = "FILE_UPLOAD";
			break;
		}
		return partialName;
	}

	public String getFieldPartialID(String fieldType) {
		String partialID = null;
		switch (fieldType) {
		case "Text Field":
			partialID = "dfTF";
			break;
		case "Text Area":
			partialID = "dfTA";
			break;
		case "Descriptive Field":
			partialID = "descriptive";
			break;
		case "Numeric Field":
			partialID = "dfNM";
			break;
		case "Date Field":
			partialID = "dfDT";
			break;
		case "Radio Field":
			partialID = "dfRF";
			break;
		case "Checkbox":
			partialID = "dfCF";
			break;
		case "Dropdown":
			partialID = "dfDD";
			break;
		case "Multi-Select":
			partialID = "dfMS";
			break;
		}
		return partialID;
	}

	/*private void enterFieldProperties(String fieldType, String header, String text, boolean hideField) {
		try {
			switch (fieldType) {
			case "Descriptive Field":
				findElement(By.xpath("//input[@name='dfLBLHeader']")).sendKeys(header);
				findElement(By.xpath("//input[@name='dfLBLText']")).sendKeys(text);
				if (hideField)
					findElement(By.xpath("//input[@name='hide' and @value='false']")).click();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	private void enterFieldProperties_FlexiForm(String fieldDisplayName, String defaultValue, int maxChar,
			boolean mandatory) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			WebElement objFieldName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Name']")));
			
			//findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Name']"))
			objFieldName.sendKeys(fieldName);
			WebElement displayName = findElement(
					By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Display_Name']"));
			displayName.clear();
			displayName.sendKeys(fieldDisplayName);
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Defaults']"))
					.sendKeys(defaultValue);
			switch (fieldType) {
			case "Text Field":
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_MaxChar']"))
						.sendKeys(String.valueOf(maxChar));
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType)
						+ "_Mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Key']"));
			findElement(By.xpath("//form[@id='zydf-frmFieldProperty']//input[@value='Save']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void enterFieldProperties(String defaultValue, int maxChar, boolean hideField, boolean enterSpace,
			boolean enterSplChar, boolean mandatory) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'Name']")).sendKeys(fieldName);
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'Defaults']"))
					.sendKeys(defaultValue);
			switch (fieldType) {
			case "Text Field":
				findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'MaxChar']"))
						.sendKeys(String.valueOf(maxChar));
				if (!enterSpace)
					findElement(By.xpath("//input[@name='space']")).click();
				if (!enterSplChar)
					findElement(By.xpath("//input[@name='splChar']")).click();
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialID(fieldType) + "'Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'Key']"));
			if (hideField)
				findElement(By.xpath("//input[@name='hide' and @value='false']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	public void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
			String defaultValue, int decimalPrecision, boolean allowNegNos, int minRestrictedVal, int maxRestrictedVal,
			boolean mandatory) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Name']"))
					.sendKeys(fieldName);
			WebElement displayName = findElement(
					By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Display_Name']"));
			displayName.clear();
			displayName.sendKeys(fieldDisplayName);
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Defaults']"))
					.sendKeys(defaultValue);
			switch (fieldType) {
			case "Numeric Field":
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Decimal']"))
						.sendKeys(String.valueOf(decimalPrecision));
				if (allowNegNos)
					findElement(
							By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_AllowNegative']"))
									.click();
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_RestrictMin']"))
						.sendKeys(String.valueOf(minRestrictedVal));
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_RestrictMax']"))
						.sendKeys(String.valueOf(maxRestrictedVal));
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType)
						+ "_Mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Key']"));
			findElement(By.xpath("//form[@id='zydf-frmFieldProperty']//input[@value='Save']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void enterFieldProperties(String fieldType, String fieldName, String defaultValue, int decimalPrecision,
			boolean allowNegNos, int minRestrictedVal, int maxRestrictedVal, boolean mandatory, boolean hideField) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Name']")).sendKeys(fieldName);
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Defaults']"));
			switch (fieldType) {
			case "Numeric Field":
				findElement(By.xpath("//input[@name='dfNMDecimal']")).sendKeys(String.valueOf(decimalPrecision));
				if (allowNegNos)
					findElement(By.xpath("//input[@name='allowNegative']")).click();
				findElement(By.xpath("//input[@name='dfNMRestrictMin']")).sendKeys(String.valueOf(minRestrictedVal));
				findElement(By.xpath("//input[@name='dfNMRestrictMax']")).sendKeys(String.valueOf(maxRestrictedVal));
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialID(fieldType) + "+'Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Key']"));
			if (hideField)
				findElement(By.xpath("//input[@name='hide' and @value='false']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enterFieldProperties(String fieldType, String fieldName, String defaultValue, String choice,
			boolean choicesAlphabetically, boolean mandatory, boolean hideField) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'Name']")).sendKeys(fieldName);
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'Defaults']"))
					.sendKeys(defaultValue);
			switch (fieldType) {
			case "Radio Field":
			case "Checkbox":
			case "Dropdown":
			case "Multi-Select":
				findElement(By.xpath("//textarea[@name='" + getFieldPartialID(fieldType) + "'Choice']"))
						.sendKeys(choice);
				if (choicesAlphabetically)
					findElement(By.xpath("//input[@name='alphabetically']")).click();
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialID(fieldType) + "'Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "'Key']"));
			if (hideField)
				findElement(By.xpath("//input[@name='hide' and @value='false']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
			String defaultValue, String choice, boolean choicesAlphabetically, boolean mandatory) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Name']"))
					.sendKeys(fieldName);
			WebElement displayName = findElement(
					By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Display_Name']"));
			displayName.clear();
			displayName.sendKeys(fieldDisplayName);
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Defaults']"))
					.sendKeys(defaultValue);
			switch (fieldType) {
			case "Radio Field":
			case "Checkbox":
			case "Dropdown":
			case "Multi-Select":
				findElement(By.xpath("//textarea[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Choice']"))
						.sendKeys(choice);
				if (choicesAlphabetically)
					findElement(By
							.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Alphabetically']"))
									.click();
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType)
						+ "_Mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Key']"));
			findElement(By.xpath("//form[@id='zydf-frmFieldProperty']//input[@value='Save']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void enterFieldProperties(String fieldType, String fieldName, int maxChar, boolean mandatory,
			boolean hideField) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Name']")).sendKeys(fieldName);
			switch (fieldType) {
			case "Text Area":
				findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'MaxChar']"))
						.sendKeys(String.valueOf(maxChar));
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialID(fieldType) + "+'Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Key']"));
			if (hideField)
				findElement(By.xpath("//input[@name='hide' and @value='false']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
			int maxChar, boolean mandatory) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Name']"))
					.sendKeys(fieldName);
			WebElement displayName = findElement(
					By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Display_Name']"));
			displayName.clear();
			displayName.sendKeys(fieldDisplayName);
			switch (fieldType) {
			case "Text Area":
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_MaxChar']"))
						.sendKeys(String.valueOf(maxChar));
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType)
						+ "_Mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Key']"));
			findElement(By.xpath("//form[@id='zydf-frmFieldProperty']//input[@value='Save']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void enterFieldProperties(String fieldType, String fieldName, boolean enterPastDates,
			boolean enterFutureDates, boolean mandatory, boolean hideField) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Name']")).sendKeys(fieldName);
			switch (fieldType) {
			case "Date Field":
				if (enterPastDates)
					findElement(By.xpath("//input[@name='pastdate' and @checked='checked']")).click();
				if (enterFutureDates)
					findElement(By.xpath("//input[@name='futuredate']")).click();
				break;
			}
			if (mandatory)
				findElement(By.xpath("//input[@name='mandatory' and @value='true']")).click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialID(fieldType) + "+'Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialID(fieldType) + "+'Key']"));
			if (hideField)
				findElement(By.xpath("//input[@name='hide' and @value='false']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
			boolean enterPastDates, boolean enterFutureDates, boolean mandatory) {
		try {
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Name']"))
					.sendKeys(fieldName);
			WebElement displayName = findElement(
					By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Display_Name']"));
			displayName.clear();
			displayName.sendKeys(fieldDisplayName);
			switch (fieldType) {
			case "Date Field":
				if (enterPastDates)
					findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType)
							+ "_Pastdate' and @checked='checked']")).click();
				if (enterFutureDates)
					findElement(By.xpath("[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Futuredate']"))
							.click();
				break;
			}
			if (mandatory)
				findElement(By.xpath(
						"[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Mandatory' and @value='true']"))
								.click();
			findElement(By.xpath("//textarea[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Info']"));
			findElement(By.xpath("//input[@name='" + getFieldPartialName_FlexiForm(fieldType) + "_Key']"));
			findElement(By.xpath("//form[@id='zydf-frmFieldProperty']//input[@value='Save']")).click();
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
