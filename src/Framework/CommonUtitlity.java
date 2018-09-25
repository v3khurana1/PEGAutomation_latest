package Framework;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/*import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

public class CommonUtitlity {

	private WebDriver driver;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public CommonUtitlity() {
		super();
	}

	public CommonUtitlity(WebDriver driver) {
		super();
		this.driver = driver;

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
	 * @param Locator
	 * @return WebElement
	 */
	public WebElement findElement(By elem) {
		WebElement objElem = null;
		try {
			objElem = waitFluent(elem);
			return objElem;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return objElem;
	}
	
	public void click(By elem) throws Exception {
		try {
			waitFluent(elem).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			takeScreenshot();
			throw new Exception();
		}
	}


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

	/**
	 * 
	 * @param element
	 */
	public void waitUntilInvisibilityOfElement(By element) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**
	 * 
	 * @param element
	 */
	public void waitUntilVisibilityOfElement(By element) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void click(By elem, String printMsg) throws Exception {
		try {
			waitFluent(elem).click();
			System.out.println(printMsg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			takeScreenshot();
		}
	}

	public void sendKeys(By elem, String txt) throws Exception {
		try {
			waitFluent(elem).sendKeys(txt);
			System.out.println(txt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// takeScreenshot();
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

}
