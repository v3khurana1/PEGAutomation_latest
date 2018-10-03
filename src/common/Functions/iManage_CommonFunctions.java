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

public class iManage_CommonFunctions extends CommonUtility {

	private WebDriver driver;

	private By filterAppliedIconXpath = By.xpath("//span[@class='icon fltr-clear' and contains(@style,'inline')]");
	protected By processingLoader = By.xpath("//*[contains(@id,'_processing')]");

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
	/*
	 * private By quantityFieldxpath = By .xpath(
	 * "(//div[@id='createNewItem']//*[@id='manTxtFields']/div/ol/li[10]/div[1]/input)[1]"
	 * ); private By itemNoXpath = By.xpath(
	 * "//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[2]/div[1]/input"
	 * );
	 */
	private By descriptionXpath = By
			.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[3]/div[1]/textarea");
	private By productCategoryxpath = By
			.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[5]/div[1]/input");
	private By saveBtnId = By.id("saveItemSummary");

	private ExtentTest logger;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public iManage_CommonFunctions(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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

	public void navigate_path(String tab, String subTab) throws Exception {
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

	public void navigateToMainPage(String displayStyle, String product, String tab, String subTab) throws Exception {
		if (displayStyle.equals("Rainbow"))
			navigate_Rainbowpath(product, tab, subTab);
		else
			navigate_path(product, tab, subTab);
	}

	public void navigate_path(String product, String tab, String subTab) throws Exception {
		Actions action = new Actions(driver);
		try {
			selectClassicProduct(product);
			/*
			 * ConfigurationProperties config =
			 * ConfigurationProperties.getInstance(); String landingTab =
			 * returnLandingTabs(config.getProperty("landingTab")); String
			 * subLandingTab =
			 * returnLandingTabs(config.getProperty("landingSubTab")); if
			 * (landingTab.equals(tab) && subLandingTab.equals(subTab)) { } else
			 * {
			 */
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));

			WebElement path_tab = findElement(By.xpath("//div[@id='tab-all']//a[text()[contains(.,'" + tab + "')]]"));
			// if(path_tab.getAttribute("class") != "activeSubHeaderTab"){
			action.click(path_tab).build().perform();
			/*
			 * WebElement subpath_tab = findElement(By.xpath(
			 * "//div[@id='subHeaderNav']//a[text()[contains(.,'" + tab +
			 * "')]]/parent::span//div[@class='subHdrUl']//a[label/text()[contains(.,'"
			 * + subTab + "')]]"));
			 */
			WebElement subpath_tab = findElement(By.xpath("//div[@id='tab-all']//a[text()[contains(.,'" + tab
					+ "')]]/parent::span//div//a[text()[contains(.,'" + subTab + "')]]"));
			// if(!subpath_tab.findElement(By.xpath("./label")).getAttribute("class").equals("subNavSelected")){
			String elemClass = driver
					.findElement(By.xpath("//div[@id='tab-all']//span[a[text()[contains(.,'" + tab + "')]]]"))
					.getAttribute("class");
			System.out.println(elemClass);
			if (elemClass.contains("activeHover")) {
				subpath_tab.click();
				waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			}
			// LogScreenshot("INFO", "Navigated to "+ tab +" - "+ subTab);
			logger.log(LogStatus.INFO, "Navigated to " + tab + " - " + subTab);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.log(LogStatus.INFO, "Not Navigated to " + tab + " - " + subTab);
			throw new Exception();
		}
	}

	public void navigateToMainPage(String displayStyle, String product, String[] navigationTabs) throws Exception {
		if (navigationTabs.length == 1)
			navigateToMainPage(displayStyle, product, navigationTabs[0]);
		else
			navigateToMainPage(displayStyle, product, navigationTabs[0], navigationTabs[1]);
	}

	public void navigateToMainPage(String displayStyle, String product, String tab) throws Exception {
		if (displayStyle.equals("Rainbow"))
			navigate_Rainbowpath(product, tab);
		else
			navigate_path(product, tab);
	}

	public boolean selectRainbowProduct(String product) {
		boolean result = false;
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		try {
			findElement(By.xpath("(//div[@id='rainbowHeader']/a/span/*)[1]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='rb-header-wrap-bg' and contains(@style,'block')]")));

			WebElement productLink = findElement(
					By.xpath("//ul[@id='containerForZycusMenuItems']/li/a[span/text()='" + product + "']"));
			action.click(productLink).build().perform();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//ul[@id='containerForZycusMenuItems']/li/a[span/text()='"
							+ product + "']/../ul[contains(@style,'block')]")));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean selectClassicProduct(String product) {
		boolean result = false;
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String modifiedXpath = "activePan_" + product;
			WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(modifiedXpath)));
			// element = findElement(By.id(modifiedXpath));
			while (!js.executeScript("return document.readyState").equals("complete")) {
				System.out.println(js.executeScript("return document.readyState"));
				Thread.sleep(100);
			}
			js.executeScript("arguments[0].click();", elem);
			// String prodHeaderXpath =
			// "//div[@id='newHeaderLowerPart']//label[@class='newhdrProdNm']";
			String prodHeaderXpath = "//div[@class='h-clearfix h-topBand']/div/div/span/span[2]";
			waitUntilVisibilityOfElement(By.xpath(prodHeaderXpath));
			if (driver.findElement(By.xpath(prodHeaderXpath)).getText().equals(product))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void navigate_Rainbowpath(String product, String tab) throws Exception {
		try {
			selectRainbowProduct(product);
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
			/*
			 * findElement(By.xpath("//div[@id='rainbowHeader']/a/span/*")).
			 * click(); WebDriverWait wait = new WebDriverWait(driver, 20);
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
			 * xpath("//div[@class='rb-header-wrap-bg' and contains(@style,'block')]"
			 * )));
			 * 
			 * Actions action = new Actions(driver); WebElement path_tab =
			 * findElement( By.xpath(
			 * "//ul[@id='containerForZycusMenuItems']/li/a[span/text()='"+tab+
			 * "']")); action.click(path_tab).build().perform();
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
			 * ("//ul[@id='containerForZycusMenuItems']/li/a[span/text()='"+tab+
			 * "']/../ul[contains(@style,'block')]")));
			 */
			WebElement path_tab = findElement(
					By.xpath("//ul[contains(@style,'block')]/li/a[span[text()='" + tab + "']]"));
			path_tab.click();
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			// LogScreenshot("INFO", "Navigated to "+ tab);
			// logger.log(LogStatus.INFO, "Navigated to "+ tab);
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.log(LogStatus.INFO, "Not Navigated to "+ tab);
			throw new Exception();
		}
	}

	public void navigate_Rainbowpath(String product, String tab, String subTab) throws Exception {
		try {
			selectRainbowProduct(product);
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
			WebElement pathtabRow = driver
					.findElement(By.xpath("//ul[contains(@style,'block')]/li[a[span[text()='" + tab + "']]]"));
			// driver.findElement(By.xpath("//ul[contains(@style,'block')]/li/a[span[text()='"+tab+"']]")).click();
			Actions action = new Actions(driver);
			action.moveToElement(pathtabRow).build().perform();
			// path_tab.click();
			Thread.sleep(2000);
			WebElement subpathTabRow = pathtabRow.findElement(
					By.xpath("//ul[contains(@class,'rb-smenu-sub-sub')]/li[a[span[text()='" + subTab + "']]]"));
			if (!subpathTabRow.getAttribute("class").contains("--active")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(
						"var objContainer = document.evaluate(\"//ul[contains(@class,'rb-smenu-sub-sub')]/li[a[span[text()='"
								+ subTab
								+ "']]]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
								+ "document.evaluate(\".//a\", objContainer, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()");
				// WebElement subpath_tab =
				// driver.findElement(By.xpath("//ul[contains(@class,'rb-smenu-sub-sub')]/li/a[span[text()='"+subTab+"']]"));
				// action.moveToElement(subpathTabRow).click().build().perform();
				// WebElement subpath_tab =
				// subpathTabRow.findElement(By.xpath("//a"));
				// driver.findElement(By.xpath("//ul[contains(@class,'rb-smenu-sub-sub
				// rb-smenu-sub-sub')]/li/a[span[text()='"+subTab+"']]")).click();
				// Thread.sleep(3000);
				// subpath_tab.click();
				// action.moveToElement(path_tab).click().build().perform();
			}
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			// LogScreenshot("INFO", "Navigated to "+ tab +" - "+ subTab);
			// logger.log(LogStatus.INFO, "Navigated to "+ tab +" - "+ subTab);
		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.log(LogStatus.INFO, "Not Navigated to "+ tab +" - "+
			// subTab);
			throw new Exception();
		}
	}

	public boolean addAttachment(By uploadBtn, String uploadfileConfigProp) throws Exception {
		boolean result = false;
		try {
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			sendKeys(uploadBtn, System.getProperty("user.dir") + config.getProperty(uploadfileConfigProp));
			Thread.sleep(3000);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

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

}
