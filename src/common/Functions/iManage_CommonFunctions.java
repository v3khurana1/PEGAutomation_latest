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
	
	public boolean addAttachment(By uploadBtn, String uploadfileConfigProp) throws Exception {
		boolean result = false;
		try {
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			sendKeys(uploadBtn,
					System.getProperty("user.dir") + config.getProperty(uploadfileConfigProp));
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
}
