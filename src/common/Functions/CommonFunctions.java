package common.Functions;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Framework.CommonUtitlity;

public class CommonFunctions extends CommonUtitlity {
	private WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	private ExtentTest logger;

	private By filterBtnid = By.id("filter");
	private By table = By.xpath("//table[contains(@id,'Grid')]");
	private By poListxpath = By.xpath("//*[@id='poListing']//tr[2]/td[1]/input[@class='poRadio']");
	private By createInvDoneBtnId = By.id("createInvoiceAgainstPo");
	private By mktPricexpath = By
			.xpath("//div[@id='createNewItem']//input[contains(@class,'pricefield txtMarketPrice')]");
	private By quantityFieldxpath = By
			.xpath("(//div[@id='createNewItem']//*[@id='manTxtFields']/div/ol/li[10]/div[1]/input)[1]");
	private By itemNoXpath = By.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[2]/div[1]/input");
	private By descriptionXpath = By
			.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[3]/div[1]/textarea");
	private By productCategoryxpath = By
			.xpath("//div[@id='createNewItem']//*[@id='manTxtFields']/div//li[5]/div[1]/input");
	private By saveBtnId = By.id("saveItemSummary");

	private By popupId = By.id("catalog-upload-donotshow");
	private By submitBtnId = By.id("btnSubmit");
	private By searchPOid = By.id("customerSearch");

	public CommonFunctions(WebDriver driver, ExtentTest logger) throws Exception {
		super(driver);
		this.driver = driver;
		this.logger = logger;
	}

	public CommonFunctions() throws Exception {
		super();
	}

	/**
	 * ----------------------------------------
	 * 
	 * @function filter
	 * @param header
	 * @param valueinvoice
	 * @return ------------------------
	 * @throws Exception
	 */
	public boolean filter_by_checkbox(String header, String value) throws Exception {
		boolean result = false;
		WebElement element = findElement(By.xpath("//div[@class='fltrBx']//div[contains(@class,'fBx')][label[text()='"
				+ header + "']]//div[contains(@class,'stsBx')]/label/text()[contains(.,'" + value
				+ "')]/parent::label//input"));
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		scroll_down();
		click(filterBtnid);
		scroll_to_TopofPage();
		Thread.sleep(5000);
		// validate if filter worked fine
		if (header.contains("status") || header.contains("Status"))
			validate_status(value);
		else
			validate_type(value);
		result = true;
		return result;

	}

	/**
	 * @function generate 6 digit random no
	 */
	public int generateNo() {
		Random rnd = new Random();
		return (100000 + rnd.nextInt(900000));
	}

	/**
	 * @function select the PO against which you want to create invoice/credit
	 *           memo
	 * @return status
	 * @param PO
	 * @throws Exception
	 */
	// public boolean select_PO_to_create_Inv_Creditmemo(String POName) throws
	// Exception {
	public String select_PO_to_create_Inv_Creditmemo(String POName) throws Exception {
		boolean status = false;
		String PO_selected = null;
		try {
			waitUntilInvisibilityOfElement(By.id("poListing_processing"));
			if (POName != null && !POName.isEmpty()) {
				Thread.sleep(3000);
				sendKeys(searchPOid, POName);
				click(By.xpath("//*[@id='btnSearch']"));
				Thread.sleep(2000);
			} else {
				Thread.sleep(3000);
				click(poListxpath);
				PO_selected = findElement(By.xpath("//*[@id='poListing']/tbody/tr[2]/td[2]/a")).getText();
				LogScreenshot("INFO", "Selected PO is" + PO_selected);
			}

			WebElement btn = findElement(createInvDoneBtnId);
			if (btn.isEnabled()) {
				btn.click();
				status = true;
				logger.log(LogStatus.INFO, "selected PO to create PO invoice ");
			}
		} catch (Exception e) {
			System.out.println("Here is the issue");
			e.printStackTrace();
			// PO_selected = null;
			logger.log(LogStatus.INFO, "could not PO to create PO invoice ");
		}
		// return status;
		if (status == true)
			return PO_selected;
		else
			return null;
	}

	/**
	 * @param remit
	 *            to address
	 * @return
	 * @return result
	 * @throws Exception 
	 */
	public boolean selectRemit_ToAddress() throws Exception {
		boolean result = false;
	try{	WebElement remit_add = findElement(By.id("slctSupplierAddress"));
		remit_add.click();
		findElement(By.xpath("//div[@id='divSupplierAddressEdit']//li/a/div[1]")).click();
		if (remit_add.getText() != null)
			result = true;
	}catch(Exception e)
	{
		e.printStackTrace();
		throw new Exception();
	}
		return result;
	}

	/**
	 * 
	 */
	public void selectDropdown(String selectValue) {
		findElement(By.xpath(
				"//div[@id='cntInvoice']/ul[contains(@style,'block')]/li/a[contains(text(),'" + selectValue + "')]"))
						.click();
	}

	/**
	 * @param ship
	 *            to address
	 * @return
	 * @return result
	 * @throws Exception 
	 */
	public boolean selectShip_ToAddress() throws Exception {
		boolean result = false;
	try{	WebElement shipToadd = findElement(By.id("slctShipSupplierAddress"));
		scroll_into_view_element(shipToadd);
		wait_untilVisible(shipToadd);
		shipToadd.click();
		findElement(By.xpath("//div[@id='divShipSupplierAddressEdit']//li/a/div[1]")).click();
		if (shipToadd.getText() != null)
			result = true;
	}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
			
		return result;
	}

	private boolean validate_type(String type) {
		// TODO Auto-generated method stub
		List<WebElement> st = driver
				.findElements(By.xpath("//tbody/tr[@class='childRow']//tr/td[1]/div[@class='meta-cnt']"));
		for (WebElement webElement : st) {
			Assert.assertEquals(webElement.getText(), type);
		}
		return true;
	}

	private boolean validate_status(String status) {
		// TODO Auto-generated method stub
		List<WebElement> st = driver.findElements(By.xpath("//tbody/tr[*]/td[2]/label"));
		for (WebElement webElement : st) {

			Assert.assertEquals(webElement.getText(), status);
		}
		return true;
	}

	/**
	 * ------------------------------------------------
	 * 
	 * @function add items for invoice/creditmemo
	 * @return boolean
	 * @param item_no,description,product_cat
	 * @param market_prc,quantity
	 * @throws Exception
	 */
	public boolean add_item(String item_no, String description, String product_cat, String market_prc, String quantity)
			throws Exception {
		Boolean status = false;
		// System.out.println("Item No entered is "+item_no);
		try {
			sendKeys(itemNoXpath, item_no);

			sendKeys(descriptionXpath, description);
			WebElement prod_cat = findElement(productCategoryxpath);
			scroll_into_view_element(prod_cat);

			try {
				sendKeys(productCategoryxpath, product_cat);
				Thread.sleep(2000);
				click(By.xpath(
						"//div[@id='itemDialog']//ul[contains(@style,'block')]/li[text()='" + product_cat + "']"));
			} catch (Exception e) {
				LogScreenshot("INFO", "Product category " + product_cat + " is not available in the list");
				findElement(productCategoryxpath).clear();
				sendKeys(productCategoryxpath, "a");
				Thread.sleep(2000);
				click(By.xpath("//div[@id='itemDialog']//ul[contains(@style,'block')]/li[1]"));
			}

			Thread.sleep(4000);
			sendKeys(mktPricexpath, market_prc);
			sendKeys(quantityFieldxpath, quantity);
			click(saveBtnId);
			LogScreenshot("INFO", "added item successfully");
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * ------------------------------------------
	 * 
	 * @since function takes the row and perform the action
	 * @param action
	 * @throws Exception
	 */
	public boolean select_action(String action) throws Exception {
		int i, row = 0;
		boolean result = false;
		// Thread.sleep(3000);
		/*
		 * Select countrows = new Select(findElement(By.xpath(
		 * "//*[@id='uploadGrid_length']/label/select")));
		 * countrows.selectByValue("25");
		 */
		// findElement(By.xpath("//*[@id='uploadGrid_length']//select/option[text()='25']")).click();
		waitUntilInvisibilityOfElement(By.id("uploadGrid_processing"));
		if (findElement(table).isDisplayed()) {
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='uploadGrid']//tr[*]/td//td[2]//a"));
			int count = rows.size();
			System.out.println("count of rows is " + count);

			for (i = 2; i < count; i = i + 2) {
				// if
				// (findElement(By.xpath("//*[@id='uploadGrid']//tr["+i+"]/td[2]/div[@class='meta-cnt']/a")).getText().contains("-")){
				String text = findElement(By.xpath("//*[@id='uploadGrid']//tr[" + i + "]/td//td[2]//a")).getText();
				if (text.length() < 2) {
					System.out.println("invoice field is" + text);
					// System.out.println(findElement(By.xpath("//*[@id='uploadGrid']//tr["+i+"]/td//td[2]//a")).getText());
					row = i - 1;
					break;
				}
			}

			click(By.xpath("//table[contains(@id,'Grid')]/tbody/tr[" + row + "]/td[*]//div[@class='actBx']/a"));
			click(By.xpath(
					"//table[contains(@id,'Grid')]/tbody/tr[" + row + "]/td[*]//ul/li/a[text()='" + action + "']"));
			result = true;
		}
		return result;
	}

	public boolean clearFilter() throws Exception {
		boolean status = false;
		click(By.xpath("//a[@id='clearFltr']"));
		status = true;
		return status;
	}

	public boolean add_attachment(String filepath) {
		boolean status = false;
		try {
			click(By.id("invoiceAttachmentLink"));
			if (filepath == null) {
				findElement(By.id("uploadsListing")).isDisplayed();
				findElement(By.xpath("//*[@id='uploadsListing']/tbody/tr[1]/td[1]/input")).click();
			} else {
				// findElement(By.id("attachmentInput_invoiceAttachment")).click();
				sendKeys(By.id("attachmentInput_invoiceAttachment"), filepath);
				waitUntilVisibilityOfElement(By.xpath("//table[contains(@class,'attachmentsContainer')]/tbody/tr[2]"));
				// TODO upload the file
			}
			// DO NOT REMOVE THIS WAIT
			Thread.sleep(2000);
			// wait_untilVisible(findElement(By.xpath("//tr[contains(@id,'attachment_invoiceAttachment')][last()]/td[@class='status'
			// and text()='Uploaded']")));
			click(By.id("closeAttachments"));
			status = true;
		} catch (Exception e) {
		}
		return status;

	}

	/*
	 * select action for 2nd row line item
	 * 
	 */
	// TODO shipment type will be created only if PO type is release/not blanket
	public boolean select_actionPO(String action, int rowNum) throws Exception {
		boolean result = false;
		try {
			waitUntilInvisibilityOfElement(By.id("notification-overlay"));
			//if (findElement(table).isDisplayed()) {
			click(By.xpath("//table[contains(@id,'Grid')]/tbody/tr[" + rowNum + "]/td[*]//div[@class='actBx']/a"));
			click(By.xpath("//table[contains(@id,'Grid')]/tbody/tr[" + rowNum + "]/td[*]//ul/li/a[text()='" + action
					+ "']"));
			Thread.sleep(3000);
			LogScreenshot("INFO", "clicked " + action + " link");
			result = true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return result;
	}

	/**
	 * @function select today's date
	 * @param driver
	 * @return result
	 */
	public boolean selectDate(String date_id) {
		boolean result = false;
		WebElement date = findElement(By.id(date_id));
		date.click();
		findElement(By.xpath("//*[@id='ui-datepicker-div']/table//tr/td[contains(@class,'today')]")).click();
		if (date.getText() != null)
			result = true;
		else
			System.out.println("date field is empty");
		return result;
		/*
		 * findElement(By.id("txtShipmentDate")).click(); findElement(By.xpath(
		 * "//*[@id='ui-datepicker-div']/table//td[contains(@class,'today')]")).
		 * click();
		 */
	}

	public boolean selectDate(Date Dt) {
		boolean result = false;

		int dd = (Integer.parseInt(Dt.toString().split("/")[0]));
		int mm = (Integer.parseInt(Dt.toString().split("/")[1]));
		int yr = (Integer.parseInt(Dt.toString().split("/")[2]));

		findElement(By.xpath("//select[@class='ui-datepicker-year']/option[text()='" + yr + "']")).click();
		findElement(By.xpath("//select[@class='ui-datepicker-month']/option[@value='" + (mm - 1) + "']")).click();
		findElement(By.xpath("//*[@class='ui-datepicker-calendar']//a[text()='" + dd + "']")).click();
		result = true;
		return result;
	}

	/**
	 * --------------------------------------
	 * 
	 * @function select currency
	 * @param driver
	 * @return result
	 * @throws InterruptedException
	 *             ------------------------------------------
	 */
	public boolean selectCurrency(String currency_code) throws InterruptedException {
		Boolean result = false;
		WebElement currency = findElement(By.id("txtCurrency"));

		currency.sendKeys(currency_code);
		Thread.sleep(2000);
		findElement(By.xpath("//div[@id='cntInvoice']//li[contains(text(),'" + currency_code + "')]")).click();
		if (currency.getText() != null)
			result = true;
		return result;
	}

	/**
	 * ---------------------------------------
	 * 
	 * @function navigate to the desired path in ZCS
	 * @param path
	 * @param subpath
	 *            --------------------------------------------
	 * @param viewInvcustxpath
	 * @throws Exception
	 */
	public void navigate_path(String path, String subpath, By custxpath) throws Exception {
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		/*
		 * WebElement path_tab = driver
		 * .findElement(By.xpath("//*[@id='zsp-submenus-id']//a//span[text()='"
		 * + path + "']"));
		 */
		WebElement path_tab = waitFluent(By.xpath("//*[@id='zsp-submenus-id']//a//span[text()='" + path + "']"));
		action.click(path_tab).build().perform();
		WebElement path_subtab = findElement(By.xpath("//span[contains(@class,'" + subpath + "')]"));
		action.click(path_subtab).build().perform();
		waitUntilVisibilityOfElement(custxpath);
		click(custxpath);
		// do_not_show();

	}

	/**
	 * 
	 * @return result
	 * @throws Exception
	 */
	public boolean do_not_show() throws Exception {
		boolean result = false;
		try {
			// WebElement box = driver.findElement(popupId);
			if (driver.findElement(popupId).isDisplayed()) {
				click(popupId);
				result = true;
			}

		} catch (Exception ex) {
			// ex.printStackTrace();
			takeScreenshot();
			// ex.printStackTrace();
		}
		return result;
	}

	public void navigate_path1(String tab, String subTab, String childTab) throws Exception {
		try {
			Thread.sleep(3000);
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			Actions action = new Actions(driver);
			/*
			 * WebElement path_tab = driver .findElement(By.xpath(
			 * "//*[@id='zsp-submenus-id']//a//span[text()='" + path + "']"));
			 */
			WebElement path_tab = findElement(By.xpath("//*[@id='zsp-submenus-id']//a//span[text()='" + tab + "']"));
			// path_tab.click();
			// driver.findElement(By.xpath("//*[@id='zsp-submenus-id']//a//span[text()='"
			// + tab + "']")).click();
			action.click(path_tab).build().perform();
			WebElement path_subtab = findElement(By.xpath("//*[@id='zsp-submenus-id']//ul/li[span[text()='" + tab
					+ "']]/following-sibling::li//span[text()='" + subTab + "']"));
			// path_subtab.click();
			action.click(path_subtab).build().perform();
			WebElement path_childTab = findElement(By.xpath("//*[@id='zsp-submenus-id']//ul/li[span[text()='" + tab
					+ "']]/following-sibling::li/a[span/span[text()='" + subTab
					+ "']]/following-sibling::ul//span[text()='" + childTab + "']"));
			// path_childTab.click();
			action.click(path_childTab).build().perform();
			try {
				do_not_show();
			} catch (Exception e) {
			}
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean click_Submit() throws InterruptedException {
		boolean result = false;
		try {
			WebElement btn = findElement(submitBtnId);
			// scroll_to_BottomofaPage();
			// wait_untilVisible(btn);
			// if (btn.isDisplayed()) {
			btn.click();
			result = true;
			// }
		} catch (Exception e) {
			System.out.println("Submit button not clicked " + e.getMessage());
		}
		return result;
	}

	public boolean save_Draft() throws InterruptedException {
		boolean result = false;
		WebElement btn = findElement(By.id("lnkSaveInvoiceDraft"));
		scroll_into_view_element(btn);
		if (btn.isDisplayed()) {
			btn.click();
			result = true;
		}
		return result;
	}

	public boolean Cancel() throws InterruptedException {
		boolean result = false;
		WebElement btn = findElement(By.id("lnkCancel"));
		scroll_into_view_element(btn);
		if (btn.isDisplayed()) {
			btn.click();
			result = true;
		}
		return result;
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
	}

	public void LogScreenshot(String status) throws Exception {
		LogScreenshot(status, "");
	}

}
