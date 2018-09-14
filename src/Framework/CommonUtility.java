package Framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

public class CommonUtility {

	private WebDriver driver;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	private ExtentTest logger;

	public CommonUtility() {
		super();
	}

	public CommonUtility(WebDriver driver, ExtentTest logger) {
		super();
		this.driver = driver;
		this.logger = logger;

	}
	
	public void LogScreenshot(String status, String msg) throws Exception {

		// LogStatus logStat = status ?LogStatus.PASS :LogStatus.FAIL;
		LogStatus logStat = null;
		switch (status.toLowerCase()) {
		case "pass":
		case "true":
			logStat = LogStatus.PASS;
			break;
		case "fail":
		case "false":
			logStat = LogStatus.FAIL;
			break;
		case "info":
			logStat = LogStatus.INFO;
			break;
		}
		String screenshotPath = getScreenhot(driver, "screenshot_anisha");
		logger.log(logStat, msg + logger.addScreenCapture(screenshotPath));
		Thread.sleep(300);
	}

	public void LogScreenshot(String status) throws Exception {
		LogScreenshot(status, "");
	}

	public WebElement waitFluent(By element) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		// .pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class);
		// .withTimeout(Duration.ofSeconds(100),TimeUnit.SECONDS)
		WebElement foo = wait.until(d -> d.findElement(element));

		return foo;
	}

	/**
	 * 
	 * @param element
	 */
	public void waitUntilInvisibilityOfElement(By element) {

		/*
		 * Wait<WebDriver> wait = new
		 * FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
		 * .pollingEvery(1,
		 * TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		 */
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**
	 * 
	 * @param element
	 */
	/*
	 * public void waitUntilVisibilityOfElement(By element) { try {
	 * 
	 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(180,
	 * TimeUnit.SECONDS) .pollingEvery(3,
	 * TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
	 * 
	 * wait.until(ExpectedConditions.presenceOfElementLocated(element)); //
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)); }
	 * catch (Exception e) { e.getMessage(); e.printStackTrace(); } //
	 * wait.until(d -> d.findElement(element)); }
	 */

	public void waitUntilVisibilityOfElement(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForElementToBeDisplayed(WebElement element, WebDriver driver, int specifiedTimeout) {
		WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
		ExpectedCondition<Boolean> elementIsDisplayed = arg0 -> element.isDisplayed();
		wait.until(elementIsDisplayed);
	}

	public void waitUntilElementDisplayed(final WebElement webElement, WebDriver driver) {
		final int TIMEOUT = 10;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return true;
				} catch (NoSuchElementException e) {
					return false;
				} catch (StaleElementReferenceException f) {
					return false;
				}
			}
		};
		wait.until(elementIsDisplayed);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/*
	 * public WebElement waitFluent(By element) {
	 * 
	 * Wait<WebDriver> wait = new
	 * FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
	 * .pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.
	 * class);
	 * 
	 * WebElement foo = wait.until(d -> d.findElement(element));
	 * 
	 * return foo; }
	 */

	/**
	 * @param Locator
	 * @return WebElement
	 */
	/*
	 * public WebElement findElement(By elem) { WebElement objElem = null; try {
	 * objElem = waitFluent(elem); return objElem; } catch (Exception e) {
	 * System.out.println(e.getMessage()); } return objElem; }
	 */

	/**
	 * @param Locator
	 * @return WebElement
	 */
	public WebElement findElement(By elem) {
		WebElement objElem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			objElem = wait.until(ExpectedConditions.elementToBeClickable(elem));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return objElem;
	}

	/*
	 * WebDriverWait wait = new WebDriverWait (driver, 20);
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//img[@id='rdp_ico_0']")));
	 */

	/**
	 * data provider
	 * 
	 * @param element
	 */

	/**
	 * get Test case name
	 */
	public String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;

		try {

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");

			value = value.substring(posi + 1);

			return value;

		} catch (Exception e) {

			throw (e);

		}

	}

	/*
	 * public void click(By elem) throws Exception { try {
	 * waitFluent(elem).click(); } catch (Exception e) {
	 * System.out.println(e.getMessage()); takeScreenshot(); throw new
	 * Exception(); } }
	 * 
	 * public void click(By elem, String printMsg) throws Exception { try {
	 * waitFluent(elem).click(); System.out.println(printMsg); } catch
	 * (Exception e) { System.out.println(e.getMessage()); takeScreenshot();
	 * throw new Exception(); } }
	 */
	public void sendKeys(By elem, String txt) throws Exception {
		try {
			waitFluent(elem).sendKeys(txt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * @function take screenshot
	 * 
	 */
	public void takeScreenshot() throws Exception {
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		screenShotName = new File(
				System.getProperty("user.dir") + "\\test-output\\screenshot\\img" + timeStamp + ".png");

		// C:\\Users\\anisha.jain\\workspace\\Automation_P2P\\Copy of
		// GD_Automation_demo
		FileUtils.copyFile(scrFile, screenShotName);

		String filePath = screenShotName.toString();
		String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
		Reporter.log(path);

	}

	/**
	 * 
	 */
	public String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	/**
	 * 
	 * @param element_to_view
	 * @throws InterruptedException
	 */
	public void scroll_into_view_element(WebElement element_to_view) throws InterruptedException {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_to_view);
		} catch (Exception e) {
			// e.printStackTrace();
			Thread.sleep(5000);
		}

	}

	public void scroll_to_BottomofaPage() throws InterruptedException {
		// driver.navigate().to(URL);
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			Thread.sleep(2000);
			// e.printStackTrace();
		}
	}

	public void scroll_to_TopofPage() throws InterruptedException {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
		} catch (Exception e) {
			Thread.sleep(2000);
			// e.printStackTrace();
		}
	}

	public void scroll_down() {
		try {
			js.executeScript("window.scrollBy(0,1000)");
		} catch (Exception e) {

		}
	}

	public void scroll_up() {
		try {
			js.executeScript("window.scrollBy(0,-1000)");
		} catch (Exception e) {

		}
	}

	/**
	 * --------------------------------------------------------
	 * 
	 * @function drag and drop element from source to dest
	 * @param srcElement
	 * @param dstElement
	 *            -----------------------------------------------------------
	 */
	public void drag_drop(WebElement srcElement, WebElement dstElement) {
		Actions action = new Actions(driver);
		action.dragAndDrop(srcElement, dstElement).build().perform();
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		// js.executeScript("var dragMock = require('drag-mock');var dragSource
		// = document.querySelector('.draggable');var dropTarget =
		// document.querySelector('.drop-zone');dragMock.dragStart(dragSource).drop(dropTarget);");
		/*
		 * action .clickAndHold(srcElement) .moveToElement(dstElement)
		 * .release() .perform();
		 */
	}

	public Boolean wait_untilVisible(WebElement e) {
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		Boolean status = false;
		if (wait1.until(ExpectedConditions.visibilityOf(e)) != null)
			status = true;
		return status;

	}

	public Object[][] dataProvider(String sheetname, String filepath) throws Exception {
		// TODO Auto-generated method stub
		Object[][] testObjArray = null;
		ExcelUtils.setExcelFile(filepath, sheetname);
		List<Integer> RowList = ExcelUtils.getRowContains("Y", 0);
		testObjArray = ExcelUtils.getTableArray1(filepath, sheetname, RowList);
		return testObjArray;
	}

	public boolean verifyTableSorting(By elem, String chosenTxt) {
		boolean is_sorted = true;

		List<WebElement> objList = driver.findElements(elem);
		for (WebElement obj : objList) {
			if (!obj.getText().equals(chosenTxt)) {
				is_sorted = false;
				break;
			}
		}
		return is_sorted;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : uploadFile
	 * 
	 * @param filePath
	 * @return funcCompletion
	 * @throws IOException
	 * @throws InterruptedException
	 *             ---------------------------------------------------------------------------------
	 */

	public void uploadFile(String filePath) throws IOException, InterruptedException {
		driver.findElement(By.xpath("//*[contains(@id,'attachmentInput')]")).sendKeys(filePath);
		// Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " +
		// "C:\\Users\\anisha.jain\\Documents\\log.txt"+Keys.ENTER);
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + Keys.ENTER);

	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : selectDate
	 * 
	 * @param dateType
	 * @param Dt
	 *            ---------------------------------------------------------------------------------
	 */

	public void selectDate(Date Dt) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(Dt);

		int dd = (Integer.parseInt(strDate.split("/")[0]));
		int mm = (Integer.parseInt(strDate.split("/")[1]));
		int yr = (Integer.parseInt(strDate.split("/")[2]));

		findElement(By
				.xpath("//select[@class='ui-datepicker-year'][ancestor::div[contains(@id,'ui-datepicker-div') and contains(@style,'block')]]/option[text()='"
						+ yr + "']")).click();
		findElement(By
				.xpath("//select[@class='ui-datepicker-month'][ancestor::div[contains(@id,'ui-datepicker-div') and contains(@style,'block')]]/option[@value='"
						+ (mm - 1) + "']")).click();
		findElement(By
				.xpath("//*[@class='ui-datepicker-calendar'][ancestor::div[contains(@id,'ui-datepicker-div') and contains(@style,'block')]]//a[text()='"
						+ dd + "']")).click();
		waitUntilInvisibilityOfElement(By.id("ui-datepicker-div"));
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : selectDate
	 * 
	 * @param dateType
	 * @param Dt
	 *            ---------------------------------------------------------------------------------
	 * @throws ParseException
	 */

	public void selectDate_v1(String Dt) throws ParseException {

		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(Dt);
		selectDate(date1);
	}

	public void waitAndClick(By identifer) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(identifer));
		elem.click();
	}

	public void writeText(By identifer, String strText) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(identifer));
		if (strText != "")
			elem.sendKeys(strText);
	}

	/*
	 * public void switchWindowHandles(String parent, String child_title) {
	 * Set<String> s1 = driver.getWindowHandles(); Iterator<String> I1 =
	 * s1.iterator(); while (I1.hasNext()) { String child_window = I1.next(); if
	 * (!parent.equals(child_window)) { String title =
	 * driver.switchTo().window(child_window).getTitle(); if (title ==
	 * child_title) break; } } }
	 */

	public boolean switchWindowHandles(String parent, String child_title) {
		boolean result = false;
		try {
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> I1 = s1.iterator();
			while (I1.hasNext()) {
				String child_window = I1.next();
				if (!parent.equals(child_window)) {
					String title = driver.switchTo().window(child_window).getTitle();
					Thread.sleep(4000);
					if (title.equals(child_title)) {
						result = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getWorkingWindowTitle(String parent) {
		String workingView = "Rainbow";
		try {
			if (switchWindowHandles(parent, "Product Selection"))
				workingView = "Classic";
			else
				switchWindowHandles(parent, "Rainbow Home");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workingView;
	}

	public WebElement getWhenVisible(By locator, int timeout, WebDriver driver) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}
	
	public boolean clickAndWaitUntilLoaderDisappears(By elemLocator, By waitingElem){
		boolean result = false;
		try{
			WebElement elem = findElement(elemLocator);
			elem.click();
			waitUntilInvisibilityOfElement(waitingElem);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean clickAndWaitUntilLoaderDisappears(By elemLocator){
		boolean result = false;
		try{
			clickAndWaitUntilLoaderDisappears(elemLocator, By.xpath("//div[contains(@id,'Processing')]"));
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean searchAndWaitUntilLoaderDisappears(By elemLocator, String searchValue, By waitingElem){
		boolean result = false;
		try{
			WebElement elem = driver.findElement(elemLocator);
			elem.sendKeys(searchValue + Keys.ENTER);
			waitUntilInvisibilityOfElement(waitingElem);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean searchAndWaitUntilLoaderDisappears(By elemLocator, String searchValue){
		boolean result = false;
		try{
			searchAndWaitUntilLoaderDisappears(elemLocator,searchValue, By.xpath("//div[contains(@id,'Processing')]"));
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean clickAndWaitUntilElementAppears(By elemLocator, By waitingElem){
		boolean result = false;
		try{
			WebElement elem = findElement(elemLocator);
			elem.click();
			waitUntilVisibilityOfElement(waitingElem);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
