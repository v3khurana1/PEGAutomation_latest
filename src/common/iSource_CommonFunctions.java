package common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import Framework.CommonUtility;

public class iSource_CommonFunctions extends CommonUtility {

	private WebDriver driver;
	//private ExtentTest logger;
	protected By processingLoader = By.xpath("//*[contains(@id,'_processing')]");
	private By filterBtnXpath = By.xpath(
			"//div[contains(@id,'qtip') and contains(@class,'focus')]//a[text()='Filter']");
	
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public iSource_CommonFunctions(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
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
	
	public boolean filterByText(String fieldName, String searchValue) {
		boolean result = false;
		try {
			int intColNo = getColNum(fieldName);
			findElement(By.xpath("//thead/tr[2]/th[" + intColNo + "]//input")).sendKeys(searchValue + Keys.ENTER);
			waitUntilInvisibilityOfElement(processingLoader);
			Thread.sleep(3000);
			List<WebElement> objfilteredTxtList = driver.findElements(By.xpath("//tbody//td[@class='filterGridTblTd'][" + intColNo + "]"));
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
	
	public boolean filterByChkbox(String checkBoxLbl, By displayedLabel) throws Exception {
		boolean result = false;
		try {
			//Thread.sleep(3000);
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
