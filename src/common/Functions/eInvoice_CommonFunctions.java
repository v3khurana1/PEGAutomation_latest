package common.Functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;

import Framework.CommonUtility;
import Framework.ConfigurationProperties;

public class eInvoice_CommonFunctions extends CommonUtility {

	private WebDriver driver;

	private By filterAppliedIconXpath = By.xpath("//span[@class='icon fltr-clear' and contains(@style,'inline')]");
	private By processingLoader = By.xpath("//*[contains(@id,'_processing')]");

	private By filterBtnXpath = By.xpath(
			"//div[contains(@id,'qtip') and contains(@class,'focus')]//div[contains(@class,'FilterBtnbx')]//a[text()='Filter']");
	private By fromDtPickBtnXpath = By.xpath(
			"(//div[contains(@id,'qtip') and contains(@style,'block')]//img[@class='ui-datepicker-trigger'])[1]");
	private By ToDtPickBtnXpath = By.xpath(
			"(//div[contains(@id,'qtip') and contains(@style,'block')]//img[@class='ui-datepicker-trigger'])[2]");
	private By minAmtInputXpath = By.xpath("//div[contains(@id,'qtip')]//input[contains(@class, 'minAmount')]");
	private By maxAmtInputXpath = By.xpath("//div[contains(@id,'qtip')]//input[contains(@class, 'maxAmount')]");
	private By clrAllFiltersIconXpath = By.xpath("//span[contains(@class,'clearAllFilters')]");

	private By mktPricexpath = By
			.xpath("//div[@id='createNewItem']//input[contains(@class,'pricefield txtMarketPrice')]");
	/*private By quantityFieldxpath = By
			.xpath("(//div[@id='createNewItem']//*[@id='manTxtFields']/div/ol/li[10]/div[1]/input)[1]");
	private By itemNoXpath = By.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[2]/div[1]/input");
	*/
	private By descriptionXpath = By
			.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[3]/div[1]/textarea");
	private By productCategoryxpath = By
			.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[5]/div[1]/input");
	private By saveBtnId = By.id("saveItemSummary");

	private ExtentTest logger;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	//private String product;

	public eInvoice_CommonFunctions(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
		this.driver = driver;
		this.logger = logger;
	}

	public eInvoice_CommonFunctions(WebDriver driver, ExtentTest logger, String product) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		//this.product = product;
	}

	public void navigate_path(String tab) {
		try {
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
			findElement(By.xpath("//div[@id='tab-fit']//a[contains(@id,'top')][text()[contains(.,'" + tab + "')]]"))
					.click();
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private String returnLandingTabs(String configSet) {

		String prodName = null;
		String tab = null;
		try {
			for (String productLandingTab : configSet.split(";")) {
				prodName = productLandingTab.split(":")[0];
				if (prodName.equals(product)) {
					tab = productLandingTab.split(":")[1];
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tab;

	}*/

	public void navigate_path(String tab, String subTab) throws Exception {
		Actions action = new Actions(driver);
		try {
			/*ConfigurationProperties config = ConfigurationProperties.getInstance();
			String landingTab = returnLandingTabs(config.getProperty("landingTab"));
			String subLandingTab = returnLandingTabs(config.getProperty("landingSubTab"));
			if (landingTab.equals(tab) && subLandingTab.equals(subTab)) {
			} else {*/
				Thread.sleep(3000);
				waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
				
				WebElement path_tab = findElement(
						By.xpath("//div[@id='subHeaderNav']//a[text()[contains(.,'" + tab + "')]]"));
				//if(path_tab.getAttribute("class") != "activeSubHeaderTab"){
					action.click(path_tab).build().perform();
					WebElement subpath_tab = findElement(By.xpath("//div[@id='subHeaderNav']//a[text()[contains(.,'" + tab
							+ "')]]/parent::span//div[@class='subHdrUl']//a[label/text()[contains(.,'" + subTab + "')]]"));
					if(!subpath_tab.findElement(By.xpath("./label")).getAttribute("class").equals("subNavSelected")){
						subpath_tab.click();
						waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
					}
					LogScreenshot("INFO", "Navigated to "+ tab +" - "+ subTab);
					//logger.log(LogStatus.INFO, "Navigated to "+ tab +" - "+ subTab);
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(LogStatus.INFO, "Not Navigated to "+ tab +" - "+ subTab);
			throw new Exception();
		}
	}
	
	public void navigateToMainPage(String displayStyle, String tab, String subTab) throws Exception {
		if(displayStyle.equals("Rainbow")){
			if(subTab!="")
				navigate_Rainbowpath(tab,subTab);
			else
				navigate_Rainbowpath(tab);
		}else{
			if(subTab!="")
				navigate_path(tab,subTab);
			else
				navigate_path(tab);
		}
	}
	
	public void navigate_Rainbowpath(String tab) throws Exception {
		try {
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
			/*findElement(By.xpath("//div[@id='rainbowHeader']/a/span/*")).click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rb-header-wrap-bg' and contains(@style,'block')]")));

			Actions action = new Actions(driver);
			WebElement path_tab = findElement(
					By.xpath("//ul[@id='containerForZycusMenuItems']/li/a[span/text()='"+tab+"']"));
			action.click(path_tab).build().perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='containerForZycusMenuItems']/li/a[span/text()='"+tab+"']/../ul[contains(@style,'block')]")));
			*/
			WebElement path_tab = findElement(By.xpath("//ul[contains(@style,'block')]/li/a[span[text()='"+tab+"']]"));
			path_tab.click();
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			LogScreenshot("INFO", "Navigated to "+ tab);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(LogStatus.INFO, "Not Navigated to "+ tab);
			throw new Exception();
		}
	}
	
	public void navigate_Rainbowpath(String tab, String subTab) throws Exception {
		try {
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
			WebElement path_tab = findElement(By.xpath("//ul[contains(@style,'block')]/li/a[span[text()='"+tab+"']]"));
			path_tab.click();
			WebElement subpath_tab = findElement(By.xpath("//ul[@class='rb-smenu-sub-sub']/li/a[span[text()='"+subTab+"']]"));
			subpath_tab.click();
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			LogScreenshot("INFO", "Navigated to "+ tab +" - "+ subTab);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(LogStatus.INFO, "Not Navigated to "+ tab +" - "+ subTab);
			throw new Exception();
		}
	}

	public void navigate_path1(String tab, String subTab) throws Exception {
		try {
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
			Actions action = new Actions(driver);
			WebElement path_tab = findElement(
					By.xpath("//div[@id='tab-fit']//a[contains(@id,'top')][text()[contains(.,'" + tab + "')]]"));
			action.click(path_tab).build().perform();
			findElement(By.xpath("//div[@id='tab-fit']//a[contains(@id,'sub')][text()[contains(.,'" + subTab + "')]]"))
					.click();
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean selectCurrency(String currency_value) throws Exception {
		boolean status = false;
		try {
			By currency = By.id("txtSupplierCurrency");
			if (findElement(currency).getText() == null) {
				findElement(currency).clear();
				sendKeys(currency, currency_value);
				findElement(By.xpath("//div[@id='cntInvoice']//ul[contains(@style,'block')]/li")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	public boolean createNewInvoiceOrCreditMemo(String paymentTerm, String purchaseType, String supplierName,
			String currency_value, String invoiceOrCreditMemoDate, String description, String product_cat,
			String market_prc, String quantity, String GLType) {
		boolean result = false;
		String category = null;

		By supplierNameId = By.id("txtSupplierName");
		By paymentTermXpath = By.xpath("//*[@id='slctPaymentTerms']/option[contains(text(),'" + paymentTerm + "')]");
		By invoiceOrCreditMemoNoId = By.id("txtInvoiceNumber");
		By invoiceDateImgXpath = By.xpath("//input[@name='txtInvoiceDate']/following-sibling::img");
		By addItemImgXpath = By.xpath(".//*[@id='addMoreItems']//span[contains(text(),'Add Item')]");
		By purchaseTypeXpath = By.xpath("//*[@id='slctPurchaseType']/option[contains(text(),'" + purchaseType + "')]");
		By btnSubmitId = By.xpath("//a[@id='btnSubmit' and text()='Submit']");
		By confirmationPopupXpath = By
				.xpath("//div[contains(@class,'workflowDialog ')]//input[contains(@class,'dev_submit ')]");
		By successBoxId = By.xpath("//div[@id='hedaerSuccessBox']//li");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String invoiceOrCreditMemoNo = String.valueOf(generateNo());
		try {
			enterText_AutoComplete(supplierNameId, supplierName);
			if (paymentTerm != "") {
				category = "Invoice - Non PO";
				waitUntilInvisibilityOfElement(By.xpath("//*[@id='slctPaymentTerms'][@disabled]"));
				findElement(paymentTermXpath).click();
			} else
				category = "Credit Memo - Without Reference";
			waitUntilInvisibilityOfElement(
					By.xpath("//div[@id='divSupplierAddressEdit']//input[@id='slctSupplierAddress'][@disabled]"));
			selectAddress();
			waitUntilInvisibilityOfElement(By.xpath("//span[@id='spnCurrencyEdit']/input[@disabled]"));
			selectCurrency(currency_value);
			sendKeys(invoiceOrCreditMemoNoId, invoiceOrCreditMemoNo);
			js.executeScript("arguments[0].click()", findElement(invoiceDateImgXpath));
			selectDate_v1(invoiceOrCreditMemoDate);
			findElement(purchaseTypeXpath).click();
			addAttachment();
			WebElement objAddItem = findElement(addItemImgXpath);
			scroll_into_view_element(objAddItem);
			objAddItem.click();
			waitUntilVisibilityOfElement(
					By.xpath("//div[@aria-describedby='changeItemSummary'][contains(@style,'block')]"));
			if (add_item(description, product_cat, market_prc, quantity, GLType)) {
				findElement(btnSubmitId).click();
				findElement(confirmationPopupXpath).click();
				waitUntilVisibilityOfElement(successBoxId);
				logger.log(LogStatus.INFO, "created " + category + " " + invoiceOrCreditMemoNo);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean createNewInvoiceOrCreditMemo(String purchaseType, String supplierName, String currency_value,
			String invoiceOrCreditMemoDate, String description, String product_cat, String market_prc, String quantity,
			String GLType) {
		boolean result = false;
		try {
			createNewInvoiceOrCreditMemo("", purchaseType, supplierName, currency_value, invoiceOrCreditMemoDate,
					description, product_cat, market_prc, quantity, GLType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean selectAddress() throws Exception {
		boolean status = false;
		try {
			findElement(By.id("slctSupplierAddress")).click();
			findElement(By.xpath("//div[@id='cntInvoice']//ul[contains(@style,'block')]/li[1]")).click();
			Thread.sleep(2000);

			// Remit to address
			By remitAddress = By.id("slctSupplierAddressRemit");
			if (findElement(remitAddress).getText() != null) {
				findElement(remitAddress).click();
				findElement(By.xpath("//div[@id='cntInvoice']//ul[contains(@style,'block')]/li[1]")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	public boolean addAttachment() throws Exception {
		boolean result = false;
		try {
			Thread.sleep(2000);
			findElement(By.id("lnkInvoiceAttachments")).click();
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			sendKeys(By.id("attachmentInput_invoiceAttachment"),
					System.getProperty("user.dir") + config.getProperty("upload_file_path"));
			Thread.sleep(3000);
			if (findElement(By.xpath("//table//tr[2]//td[@class='status']")).getText().contains("Uploaded"))
				findElement(By.xpath(".//*[@id='attachmentsDOM']//input[@value='Done']")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean selectActionPO(String action) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//*[@id='polisting']/tbody/tr[1]//a[text()='Actions']")).click();
			findElement(By.xpath("//*[@id='polisting']/tbody/tr[1]//li/a[contains(text(),'" + action + "')]")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean selectActionInvoice(String action) throws Exception {
		boolean result = false;
		try {
			findElement(By.xpath("//*[@id='invoicelisting']/tbody/tr[1]//a[text()='Actions']")).click();
			findElement(By.xpath("//*[@id='invoicelisting']/tbody/tr[1]//li/a[contains(text(),'" + action + "')]"))
					.click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// copied from Common functions of ZSN, need to place it in another file
	/**
	 * ------------------------------------------------
	 * 
	 * @function add items for invoice/creditmemo
	 * @return boolean
	 * @param item_no,description,product_cat
	 * @param market_prc,quantity
	 * @throws Exception
	 */
	/*
	 * public boolean add_item(String item_no, String description, String
	 * product_cat, String market_prc, String quantity, String GLType, String
	 * GLAccount) throws Exception {
	 */
	public boolean add_item(String description, String product_cat, String market_prc, String quantity, String GLType)
			throws Exception {
		boolean result = false;
		try {
			int existingItems = driver
					.findElements(By.xpath("//table[@id='vng-collapsibleGrid']/tbody/tr[contains(@id,'itemRow')]"))
					.size();
			// sendKeys(itemNoXpath, item_no);
			sendKeys(descriptionXpath, description);
			WebElement prod_cat = findElement(productCategoryxpath);
			scroll_into_view_element(prod_cat);
			try {
				enterText_AutoComplete(productCategoryxpath, product_cat);
			} catch (Exception e) {
				logger.log(LogStatus.INFO, "Product category " + product_cat + " is not available in the list");
				findElement(productCategoryxpath).clear();
				sendKeys(productCategoryxpath, "a");
				Thread.sleep(2000);
				findElement(By.xpath("//div[@id='itemDialog']//ul[contains(@style,'block')]/li[1]")).click();
			}
			Thread.sleep(4000);
			sendKeys(mktPricexpath, market_prc);
			WebElement creditQty = findElement(
					By.xpath("(//div[@class='frmInpt']//input[contains(@class,'txtQuantity')])[2]"));
			scroll_into_view_element(creditQty);
			creditQty.sendKeys(quantity);

			// click on Accounts tab
			findElement(By.xpath("(//li[@id='tabAccounting']/a[text()='Accounting'])[2]")).click();
			try {
				findElement(By.xpath("//select[contains(@id,'accountType')]/option[contains(text(),'" + GLType + "')]"))
						.click();
				findElement(By.xpath("//input[contains(@id,'generalLedger_tmp')]")).click();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			findElement(saveBtnId).click();
			int noOfItems = driver
					.findElements(By.xpath("//table[@id='vng-collapsibleGrid']/tbody/tr[contains(@id,'itemRow')]"))
					.size();
			if (noOfItems == existingItems + 1) {
				logger.log(LogStatus.INFO, "added item successfully");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : clrAllFilters
	 * 
	 * @return result
	 *         -----------------------------------------------------------------
	 *         ----------------
	 */

	public boolean clrAllFilters() {
		boolean result = false;
		try {
			clearAllFilters();
			List<WebElement> objfilterIconsList = driver.findElements(filterAppliedIconXpath);
			if (objfilterIconsList.size() == 0)
				result = true;
			else
				result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : clearAllFilters
	 * 
	 * @return funcCompletion
	 *         -----------------------------------------------------------------
	 *         ----------------
	 * @throws InterruptedException
	 */

	private void clearAllFilters() throws InterruptedException {
		try {
			waitUntilInvisibilityOfElement(processingLoader);
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement objClrFilter = wait.until(ExpectedConditions.presenceOfElementLocated(clrAllFiltersIconXpath));
			// WebElement objClrFilter = findElement(clrAllFiltersIconXpath);
			if (!objClrFilter.getAttribute("style").contains("none")) {
				objClrFilter.click();
				waitUntilVisibilityOfElement(processingLoader);
				waitUntilInvisibilityOfElement(processingLoader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			findElement(By.xpath("//thead/tr[2]/th[" + intColNo + "]//input")).sendKeys(searchValue + Keys.ENTER);
			waitUntilInvisibilityOfElement(processingLoader);
			Thread.sleep(3000);
			List<WebElement> objfilteredTxtList = driver.findElements(By.xpath("//tbody//td[" + intColNo + "]"));
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

	public boolean filterByText_AutoComplete(String fieldName, String searchValue) {
		boolean result = false;
		try {
			int intColNo = getColNum(fieldName);
			if (enterText_AutoComplete(By.xpath("//thead/tr[2]/th[" + intColNo + "]//input"), searchValue)) {
				waitUntilVisibilityOfElement(processingLoader);
				List<WebElement> objfilteredTxtList = driver.findElements(By.xpath("//tbody//td[" + intColNo + "]"));
				for (WebElement obj : objfilteredTxtList) {
					if (obj.getText().contains(searchValue)) {
						logger.log(LogStatus.INFO, obj.getText());
						result = true;
					} else {
						result = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean enterText_AutoComplete(By field, String text) {
		boolean result = false;
		try {
			findElement(field).sendKeys(text);
			waitUntilInvisibilityOfElement(By.xpath("//input[contains(@class,'ui-autocomplete-loading')]"));
			findElement(By.xpath("//ul[contains(@style,'block')]//*[text()='" + text + "']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	protected int getColNum(String fieldName) {
		int intColNum = 0;
		try {
			// List<WebElement> headerList =
			// driver.findElements(By.xpath("//thead/tr[1]/th/div/text()[contains(.,'"+fieldName+"')]"));
			List<WebElement> headerList = driver.findElements(By.xpath("//thead/tr[1]/th/*[text()]"));
			for (WebElement header : headerList) {
				intColNum++;
				if (header.getText().equals(fieldName))
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intColNum;
	}
	
	protected int getColNum_IContract(String fieldName) {
		int intColNum = 0;
		try {
			List<WebElement> headerList = driver.findElements(By.xpath("//thead/tr[1]/th/label"));
			for (WebElement header : headerList) {
				intColNum++;
				if (header.getText().equals(fieldName))
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intColNum;
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
					"var objContainer = document.evaluate(\"//div[contains(@class,'qtip qtip-default filterPopups') and contains(@style,'block')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
							+ "document.evaluate(\".//div[contains(@id,'qtip')]//input[following-sibling::text()[contains(.,'"
							+ checkBoxLbl
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
	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : filterByDateRange
	 * 
	 * @param fromDt
	 * @param ToDt
	 *            --------------------------------------------------------------
	 *            -------------------
	 * @throws Exception
	 */

	public void enterDateRange(Date fromDt, Date ToDt) {
		try {
			findElement(fromDtPickBtnXpath).click();
			selectDate(fromDt);
			findElement(ToDtPickBtnXpath).click();
			selectDate(ToDt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean filterByDateRange(Date fromDt, Date ToDt, By displayedDate) throws Exception {
		boolean result = false;
		try {
			enterDateRange(fromDt, ToDt);
			findElement(filterBtnXpath).click();
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilteredDateList = driver.findElements(displayedDate);
				for (WebElement obj : objfilteredDateList) {
					DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
					Date dt = format.parse(obj.getText());
					if (dt.compareTo(fromDt) >= 0 && dt.compareTo(ToDt) <= 0)
						result = true;
					else {
						result = false;
						break;
					}
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : filterByAmtRange
	 * 
	 * @param typeValue
	 * @return funcCompletion
	 * @throws Exception
	 * @throws ParseException
	 *             -------------------------------------------------------------
	 *             --------------------
	 */

	public boolean filterByAmtRange(float fromAmt, float ToAmt, By displayedAmount) throws Exception {
		boolean result = false;
		try {
			findElement(minAmtInputXpath).sendKeys(String.valueOf(fromAmt));
			findElement(maxAmtInputXpath).sendKeys(String.valueOf(ToAmt));
			findElement(filterBtnXpath).click();
			List<WebElement> objfilteredAmtList = driver.findElements(displayedAmount);
			for (WebElement obj : objfilteredAmtList) {
				Float amount = Float.parseFloat((obj.getText().split(" "))[1]);
				if (amount >= fromAmt && amount <= ToAmt)
					result = true;
				else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	/**
	 * -------------------------------------------------------------------------
	 * -------- Function : filterByAmtRange
	 * 
	 * @param typeValue
	 * @return funcCompletion
	 * @throws Exception
	 * @throws ParseException
	 *             -------------------------------------------------------------
	 *             --------------------
	 */

	public boolean filterByAmtRange(float fromAmt, float ToAmt, String currType, By displayedAmount) throws Exception {
		boolean result = false;
		try {
			findElement(minAmtInputXpath).sendKeys(String.valueOf(fromAmt));
			findElement(maxAmtInputXpath).clear();
			sendKeys(maxAmtInputXpath, String.valueOf(ToAmt));
			findElement(By.xpath("//div[contains(@id,'qtip')]//input[contains(@class, 'inputCurrencies') and @value ='"
					+ currType + "']")).click();
			findElement(filterBtnXpath).click();
			List<WebElement> objfilteredAmtList = driver.findElements(displayedAmount);
			for (WebElement obj : objfilteredAmtList) {
				Float amount = Float.parseFloat((obj.getText().split(" "))[1]);
				String currencyType = null;
				if (amount >= fromAmt && amount <= ToAmt) {
					if (currType != "")
						currencyType = (obj.getText().split(" "))[0];
					if (currencyType == currType)
						result = true;
					else
						result = true;
				} else {
					result = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	public int generateNo() {
		Random rnd = new Random();
		return (100000 + rnd.nextInt(900000));
	}

	public boolean selectTodayDate() {
		boolean result = false;
		WebElement date = findElement(By.id("txtInvoiceDate"));
		date.click();
		findElement(By.xpath("//*[@id='ui-datepicker-div']/table//tr/td[contains(@class,'today')]")).click();
		if (date.getText() != null)
			result = true;
		else
			logger.log(LogStatus.INFO, "date field is empty");
		return result;
	}
}
