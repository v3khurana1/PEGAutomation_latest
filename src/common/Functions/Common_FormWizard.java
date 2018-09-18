package common.Functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> FormWizard.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.createNewForm: user shall be able to filter by Status </br>
 * <br>
 * 2.addSection: user shall be able to filter by Received On </br>
 * <br>
 * 3.addfield </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Common_FormWizard extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String formName;
	private String formType;
	private String relatedProcess;
	//private String company;
	private String sectionName;
	private By pgHead = By.xpath("//h1[contains(text(),'Form Details and Scope')]");
	private String fieldType;
	private String fieldName;
	private String fieldDisplayName;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Common_FormWizard(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param formName
	 * @param formType
	 * @param relatedProcess
	 * @param company
	 * @param sectionName
	 * 
	 */

	public Common_FormWizard(WebDriver driver, ExtentTest logger, String formName, String formType, String relatedProcess,
			String company, String sectionName, String fieldType, String fieldName) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.formName = formName;
		this.formType = formType;
		this.relatedProcess = relatedProcess;
		//this.company = company;
		this.sectionName = sectionName;
		this.fieldType = fieldType;
		this.fieldName = fieldName + String.valueOf(generateNo());
	}

	public Common_FormWizard(WebDriver driver, ExtentTest logger, String formName, String formType, String relatedProcess,
			String company, String sectionName, String fieldType, String fieldName, String fieldDisplayName) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.formName = formName;
		this.formType = formType;
		this.relatedProcess = relatedProcess;
		//this.company = company;
		this.sectionName = sectionName;
		this.fieldType = fieldType;
		this.fieldName = fieldName + String.valueOf(generateNo());
		this.fieldDisplayName = fieldDisplayName;
	}

	/**
	 * <b>Function:</b> createNewForm
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param formDescription
	 * @param sectionDescription
	 * @param sectionLayout
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean createNewForm(String formDescription, String sectionDescription, String sectionLayout,
			String defaultValue, int maxChar, boolean hideField, boolean enterSpace, boolean enterSplChar,
			boolean mandatory) throws Exception {
		boolean result = false;
		int Iteration = 1;
		try {
			formName = formName + String.valueOf(generateNo());
			findElement(By.id("txtName")).sendKeys(formName);
			findElement(By.id("txtDesc")).sendKeys(formDescription);
			if (formType.equals("Header level form"))
				findElement(By.xpath("//input[@id='headerLevelEform']")).click();
			else if (formType.equals("Line level form"))
				findElement(By.xpath("//input[@id='lineLevelEform']")).click();
			findElement(By.xpath("//select[@id='slctProcess']/option[text()='" + relatedProcess + "']")).click();

			// eForm Scope
			selectEFormScope_new(Iteration);
			findElement(By.xpath("//input[@value='Save and Continue']")).click();
			waitUntilInvisibilityOfElement(By.id("status_overlay_saveForm"));
			while (findElement(By.xpath("//*[@id='scopesExistsMessageContainer']/div/div[2]")) != null) {
				Iteration++;
				selectEFormScope_new(Iteration);
				findElement(By.xpath("//input[@value='Save and Continue']")).click();
				waitUntilInvisibilityOfElement(By.id("status_overlay_saveForm"));
				// Thread.sleep(2000);
			}
			if (findElement(By.xpath("//label[text()='This eForm name has already been used']")) != null) {
				formName = formName + String.valueOf(generateNo());
				findElement(By.id("txtName")).clear();
				findElement(By.id("txtName")).sendKeys(formName);
				findElement(By.xpath("//input[@value='Save and Continue']")).click();
			}
			if (formType.equals("Header level form")) {
				if (addSection(sectionDescription, sectionLayout))
					result = addfield(defaultValue, maxChar, hideField, enterSpace, enterSplChar, mandatory) ? true
							: false;
			} else if (formType.equals("Line level form")) {
				// In case of navigation to Flexiform
				Common_FormListingPg objFormListing = new Common_FormListingPg(driver, logger);
				if (findElement(objFormListing.getPgHead()) != null)
					if (objFormListing.clickCreateNewFlexiFormBtn()) {
						Common_ConfigureFlexiForm objFlexi = new Common_ConfigureFlexiForm(driver, logger, sectionName, fieldType, fieldName, fieldDisplayName);
						//Thread.sleep(4000);
						if (objFlexi.addSection_flexiForm(sectionDescription, sectionLayout)){
							objFlexi.addfield_FlexiForm(defaultValue, maxChar, mandatory);
							result = objFlexi.saveFlexiForm()?true:false;
						}
						/*findElement(By.xpath("//input[@name='saveEform']")).click();
						Thread.sleep(6000);
						AllForms objForms = new AllForms(driver, logger);
						result = findElement(objForms.getPgHead()) != null ? true : false;*/
					}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addSection
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param description
	 * @param layout
	 * @return result - True/False
	 */

	private boolean addSection(String description, String layout) {
		boolean result = false;
		try {
			// Click on Add Section
			findElement(By.xpath("//div[@id='dynamicForm']//a")).click();

			if (findElement(By.xpath("//div[div/span[text()='Create section']]")).isDisplayed())
				findElement(By.id("dfSectionName")).sendKeys(sectionName);
			findElement(By.id("dfSectionDescription")).sendKeys(description);
			findElement(By.xpath("//select[@id='dfSectionLayout']/option[contains(text(),'" + layout + "')]")).click();
			findElement(By.id("dfSectionSave")).click();

			// Verifying if section Added
			if (findElement(By.xpath("//li[div/text()[contains(.,'" + sectionName + "')]]")) != null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * public boolean addSection_flexiForm(String description, String layout) {
	 * boolean result = false; try { // Click on Add Section
	 * findElement(By.xpath("//div[@id='zydf-dynamicForm']//a")).click();
	 * 
	 * if (findElement(By.xpath("//div[div/span[text()='Create Section']]")).
	 * isDisplayed())
	 * findElement(By.id("zydf-dfSectionName")).sendKeys(sectionName);
	 * findElement(By.id("zydf-dfSectionDescription")).sendKeys(description);
	 * findElement(By.xpath(
	 * "//select[@id='zydf-dfSectionLayout']/option[contains(text(),'" + layout
	 * + "')]")) .click(); findElement(By.id("zydf-dfSectionSave")).click();
	 * 
	 * // ADD CODE TO VERIFY IF SECTION ADDED if
	 * (findElement(By.xpath("//li/div/span[text()='" + sectionName + "']")) !=
	 * null) result = true; } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return result; }
	 */

	/**
	 * <b>Function:</b> addfield
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param sectionName
	 * @param fieldType
	 * @param fieldName
	 * @return result - True/False
	 */

	private boolean addfield(String defaultValue, int maxChar, boolean hideField, boolean enterSpace,
			boolean enterSplChar, boolean mandatory) {
		boolean result = false;
		try {
			// Click on Add Field
			findElement(By.xpath("//*[@id='dynamicForm']/ul/li[*]/div[contains(text(),'" + sectionName
					+ "')]/following-sibling::ul//a[span/following-sibling::text()[contains(.,'Add Field')]]")).click();
			if (findElement(By.xpath("//div[div/span[text()='Create Field']]")).isDisplayed())
				findElement(By.xpath("//*[@id='dfFieldSelect']//li[*]/a[text()='" + fieldType + "']")).click();
			enterFieldProperties(defaultValue, maxChar, hideField, enterSpace, enterSplChar, mandatory);

			// Verifying if the field is added to the Section
			if (fieldType.equals("descriptive field")) {
				if (findElement(By.xpath(
						"//li[div/div[contains(@class,'descriptive')]//div[text()='" + fieldName + "']]")) != null)
					result = true;
			} else {
				if (findElement(By.xpath("//li[div/div[label[contains(text(),'" + fieldName
						+ "')]]//input[contains(@class,'" + getFieldPartialID(fieldType) + "')]]")) != null)
					result = true;
			}
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
	 *//*

	private boolean addfield_FlexiForm(String defaultValue, int maxChar, boolean mandatory) {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@class='zydf-customField']")).click();
			Thread.sleep(3000);
			WebElement srcElement = findElement(
					By.xpath("//ul[@id='zydf-devLeftDragList']/li/a[span/following-sibling::text()[contains(.,'"
							+ fieldType + "')]]"));
			WebElement dstElement = findElement(
					By.xpath("//li/div[span[text()='Auto_Section']]/following-sibling::ul"));
			drag_drop(srcElement, dstElement);

			Thread.sleep(3000);
			// Click on Add Field
			
			 * findElement(By.xpath(
			 * "//*[@id='dynamicForm']/ul/li[*]/div[contains(text(),'" +
			 * sectionName +
			 * "')]/following-sibling::ul//a[span/following-sibling::text()[contains(.,'Add Field')]]"
			 * )).click(); if
			 * (findElement(By.xpath("//div[div/span[text()='Create Field']]")).
			 * isDisplayed())
			 * findElement(By.xpath("//*[@id='dfFieldSelect']//li[*]/a[text()='"
			 * + fieldType + "']")).click();
			 
			enterFieldProperties_FlexiForm(fieldDisplayName, defaultValue, maxChar, mandatory);

			// Verifying if the field is added to the Section
			if (fieldType.equals("descriptive field")) {
				if (findElement(By.xpath(
						"//li[div/div[contains(@class,'descriptive')]//div[text()='" + fieldName + "']]")) != null)
					result = true;
			} else {
				if (findElement(By.xpath("//li[div/div[label[contains(text(),'" + fieldName
						+ "')]]//input[contains(@class,'" + getFieldPartialID(fieldType) + "')]]")) != null)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	private String getFieldPartialID(String fieldType) {
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

	/*private String getFieldPartialName_FlexiForm(String fieldType) {
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
	}*/

	/**
	 * <b>Function:</b> enterFieldProperties
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param formDescription
	 * @param sectionDescription
	 * @param sectionLayout
	 * @return result - True/False
	 * @throws Exception
	 */

	public void enterFieldProperties(String fieldType, String header, String text, boolean hideField) {
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
	}
/*
	private void enterFieldProperties_FlexiForm(String fieldDisplayName, String defaultValue, int maxChar,
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
*/
	private void enterFieldProperties(String defaultValue, int maxChar, boolean hideField, boolean enterSpace,
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
/*
	private void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
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
*/
	public void enterFieldProperties(String fieldType, String fieldName, String defaultValue, int decimalPrecision,
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

	public void enterFieldProperties(String fieldType, String fieldName, String defaultValue, String choice,
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
	}
/*
	private void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
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
*/
	public void enterFieldProperties(String fieldType, String fieldName, int maxChar, boolean mandatory,
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
	}
/*
	private void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
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
*/
	public void enterFieldProperties(String fieldType, String fieldName, boolean enterPastDates,
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
	}
/*
	private void enterFieldProperties_FlexiForm(String fieldType, String fieldName, String fieldDisplayName,
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
*/
	/*
	 * private void enterFieldProperties(String fieldType) throws Exception {
	 * try { switch (fieldType) { case "Text Field": // Label
	 * findElement(By.name("dfTFName")).sendKeys(fieldName);
	 * findElement(By.name("dfTFDefaults")).sendKeys(defaultValue);
	 * findElement(By.name("dfTFMaxChar")).sendKeys(maxChar); if (!enterSpace)
	 * findElement(By.name("space")).click(); if (!enterSplChar)
	 * findElement(By.name("splChar")).click(); if (mandatory)
	 * findElement(By.xpath("//input[@name='mandatory' and @value='true']")).
	 * click(); findElement(By.name("dfTFInfo")).sendKeys(toolTip); if (key !=
	 * null) { WebElement objKey = findElement(By.name("dfTFKey"));
	 * objKey.sendKeys(key); } if (hideField)
	 * findElement(By.xpath("//input[@name='hide' and @value='true']")).click();
	 * findElement(By.xpath("//*[@id='frmFieldProperty']//input[@value='Save']")
	 * ).click(); break; case "Text Area":
	 * findElement(By.name("dfTFAame")).sendKeys(fieldName);
	 * findElement(By.name("dfTAMaxChar")).sendKeys(maxChar); if (mandatory)
	 * findElement(By.xpath("//input[@name='mandatory' and @value='true']")).
	 * click(); findElement(By.name("dfTAInfo")).sendKeys(toolTip); if (key !=
	 * null) { WebElement objKey = findElement(By.name("dfTAKey"));
	 * objKey.sendKeys(key); } if (hideField)
	 * findElement(By.xpath("//input[@name='hide' and @value='true']")).click();
	 * break; case "Descriptive Field":
	 * findElement(By.name("dfLBLHeader")).sendKeys(header);
	 * findElement(By.name("dfLBLText")).sendKeys(text); if (hideField)
	 * findElement(By.xpath("//input[@name='hide' and @value='true']")).click();
	 * break; case "Numeric":
	 * findElement(By.name("dfNMName")).sendKeys(fieldName);
	 * findElement(By.name("dfNMDefaults")).sendKeys(defaultValue);
	 * findElement(By.name("dfNMDecimal")).sendKeys(decimalPrecision); if
	 * (allowNegNos) findElement(By.name("allowNegative")).click();
	 * findElement(By.name("dfNMRestrictMin")).sendKeys("minValue");
	 * findElement(By.name("dfNMRestrictMax")).sendKeys("maxValue"); if
	 * (mandatory)
	 * findElement(By.xpath("//input[@name='mandatory' and @value='true']")).
	 * click(); findElement(By.name("dfNMInfo")).sendKeys(toolTip); if (key !=
	 * null) { WebElement objKey = findElement(By.name("dfNMKey"));
	 * objKey.sendKeys(key); } if (hideField)
	 * findElement(By.xpath("//input[@name='hide' and @value='true']")).click();
	 * break; case "Date & Time": break; case "Radio Button": break; case
	 * "Check box": break; case "Drop Down": break; case "Multi-Select": break;
	 * } } catch (Exception e) { e.printStackTrace(); throw new Exception(); } }
	 */

	/**
	 * <b>Function:</b> selectEFormScope
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @return None
	 * @throws Exception
	 */

	/*private void selectEFormScope(int iterationNo) throws Exception {
		// int iterationNo = 1;
		try {
			findElement(By.id("lnkBUs")).click();
			selectOrgUnit_and_Bank(iterationNo);
			findElement(By.xpath("//input[@value='Save and Continue']")).click();
			waitUntilInvisibilityOfElement(By.id("status_overlay_saveForm"));
			if (findElement(By.xpath("//*[@id='scopesExistsMessageContainer']/div/div[2]")) != null)
				iterationNo++;
			changeScope(iterationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	private void selectEFormScope_new(int iterationNo) throws Exception {
		// int iterationNo = 1;
		try {
			findElement(By.id("lnkBUs")).click();
			Thread.sleep(10000);
			if (iterationNo > 1)
				changeScope_new(iterationNo);
			selectOrgUnit_and_Bank(iterationNo);
			/*
			 * findElement(By.xpath("//input[@value='Save and Continue']")).
			 * click();
			 * waitUntilInvisibilityOfElement(By.id("status_overlay_saveForm"));
			 * if (findElement(By.xpath(
			 * "//*[@id='scopesExistsMessageContainer']/div/div[2]")) != null)
			 * iterationNo++; changeScope(iterationNo);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>Function:</b> changeScope
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param IteratioNo
	 * @return None
	 * @throws Exception
	 */

	/*private void changeScope(int IteratioNo) throws Exception {
		try {
			String businessUnitList_xpath = "//div[@class='treeContainer treeview']/ul";
			findElement(By.id("lnkBUs")).click();
			wait_untilVisible(findElement(By.xpath(businessUnitList_xpath)));
			findElement(By.xpath("//div[@class='treeContainer treeview']//input[@checked]")).click();
			selectOrgUnit_and_Bank(IteratioNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}*/

	private void changeScope_new(int IteratioNo) throws Exception {
		try {
			String businessUnitList_xpath = "//div[@class='treeContainer treeview']/ul";
			// findElement(By.id("lnkBUs")).click();
			wait_untilVisible(findElement(By.xpath(businessUnitList_xpath)));
			findElement(By.xpath("//div[@class='treeContainer treeview']//input[@checked]")).click();
			// selectOrgUnit_and_Bank(IteratioNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * <b>Function:</b> selectOrgUnit_and_Bank
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param IterationNo
	 * @return None
	 * @throws Exception
	 */

	private void selectOrgUnit_and_Bank(int IterationNo) throws Exception {
		try {
			String businessUnitList_xpath = "//div[@class='treeContainer treeview']/ul";
			if (findElement(By.xpath("//div[div/span[text()='Select Companies & Business Units']]")).isDisplayed()) {
				wait_untilVisible(findElement(By.xpath(businessUnitList_xpath)));
				if (findElement(By.xpath(businessUnitList_xpath)) != null) {
					// Selecting first Company & Business unit from the
					// displayed list tree
					findElement(By.xpath(
							"//div[@class='treeContainer treeview']//li[" + String.valueOf(IterationNo) + "]//input"))
									.click();
					try {
						findElement(By.xpath("//div[contains(@style,'block')]//input[@value='Save']")).click();
					} catch (Exception e) {
						if (findElement(By
								.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'Please select atleast one scope')]]"))
										.isDisplayed())
							findElement(By.xpath("//button/span[text()='OK']")).click();
						logger.log(LogStatus.INFO, "Please select atleast one scope - message displayed");
					}
				} else
					logger.log(LogStatus.INFO, "No results found - message displayed");

			}
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
