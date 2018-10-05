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
import Framework.ConfirmationDialog;

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
	
	public boolean selectCategory(String category){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@value='Select Category']")).click();
			if(driver.findElement(By.xpath("//div[@id='selectPopUp']/preceding-sibling::div/../div/span[text()='Select Category']/../..")).getAttribute("style").contains("block")){
				if(searchCategoryOrBusinessUnit(category))
					result = true;
			}else
				logger.log(LogStatus.INFO, "Select Category Pop up not displayed");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean selectBusinessUnit(String businessUnit){
		boolean result = false;
		try{
			findElement(By.xpath("//input[@value='Select Business Unit']")).click();
			if(driver.findElement(By.xpath("//div[@id='selectPopUp']/preceding-sibling::div/../div/span[text()='Select Business Unit']/../..")).getAttribute("style").contains("block")){
				if(searchCategoryOrBusinessUnit(businessUnit))
					result = true;
			}else
				logger.log(LogStatus.INFO, "Select Business Unit Pop up not displayed");
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean searchCategoryOrBusinessUnit(String categoryOrBusinessUnit){
		boolean result = false;
		try{
			findElement(By.xpath("//select[@id='searchCatSelect']/option[text()='Exact']")).click();
			driver.findElement(By.id("searchCatText")).sendKeys(categoryOrBusinessUnit);
			findElement(By.xpath("//input[@value='Go']")).click();
			waitUntilVisibilityOfElement(By.xpath("//table[@id='_jstree_search_table']"));
			WebElement searchResult = driver.findElement(By.xpath("//table[@id='_jstree_search_table']"));
			if(searchResult.isDisplayed()){
				searchResult.findElement(By.xpath("//tr[2]/td[1]/input")).click();
				findElement(By.xpath("//input[@title='Done']")).click();
			}else
				logger.log(LogStatus.INFO, "no results displayed for the searched string");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addPhase(String phaseTitle){
		boolean result = false;
		try{
			driver.findElement(By.id("phaseTitle")).sendKeys(phaseTitle);
			driver.findElement(By.id("phaseDesc")).sendKeys("phaseDesc");
			addAttachment(By.id("fileUploader"), "iManage_PhaseDocfile_path");
			waitUntilVisibilityOfElement(By.xpath("//tr[@id='row0']/input"));
			if(!driver.findElement(By.xpath("//tr[@id='row0']/td")).getText().equals("No Document")){
				logger.log(LogStatus.PASS, "Phase document uploaded");
				findElement(By.id("btnSavePhase")).click();
				if(driver.findElement(By.xpath("//div[@id='msgDiv' and text()='Saved Successfully']")).isDisplayed())
					result = true;
			}else
				logger.log(LogStatus.FAIL, "Phase document not uploaded");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addAnotherPhase(String phaseTitle){
		boolean result = false;
		try{
			int noOfPhasesDisplayedBefore = driver.findElements(By.xpath("//ul[@id='sortable']/li")).size(); 
			findElement(By.id("addNewPhaseButtonHolder")).click();
			int noOfPhasesDisplayedAfter = driver.findElements(By.xpath("//ul[@id='sortable']/li")).size();
			if(noOfPhasesDisplayedAfter==noOfPhasesDisplayedBefore+1){
				if(driver.findElement(By.xpath("//ul[@id='sortable']/li[last()]")).getAttribute("class").contains("active")){
					logger.log(LogStatus.INFO, "Another phase added and is active");
					if(addPhase(phaseTitle))
						result = true;
				}else
					logger.log(LogStatus.INFO, "Another phase added but is not active");
			}else
				logger.log(LogStatus.INFO, "Another phase not added");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean removePhase(){
		boolean result = false;
		try{
			int noOfPhasesDisplayedBefore = driver.findElements(By.xpath("//ul[@id='sortable']/li")).size();
			findElement(By.id("btnRemovePhase")).click();
			if(driver.findElement(ConfirmationDialog.getDialogTitle()).equals("Confirmation")){
				if(driver.findElement(ConfirmationDialog.getPopupMsg()).equals("want to remove this phase")){
					findElement(ConfirmationDialog.getDialogYesBtn()).click();
					int noOfPhasesDisplayedAfter = driver.findElements(By.xpath("//ul[@id='sortable']/li")).size();
					if(noOfPhasesDisplayedAfter==noOfPhasesDisplayedBefore-1)
						result = true;
				}else
					logger.log(LogStatus.FAIL, "Incorrect Confirmation dialog displayed");
			}else
				logger.log(LogStatus.FAIL, "Confirmation dialog not displayed");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addProjectDetails(String projectCategory, String progName, String projTitle, String projDesc, String projPriority, String projStartDt, String projEndDt, String currency){
		boolean result = false;
		try{
			findElement(By.xpath("//div[@class='projectRedioHolder']//li[label[text()='"+projectCategory+"']]/preceding-sibling::li[1]/input")).click();
			findElement(By.xpath("//select[@id='programSearchCombobox']/option[text()='"+progName+"']")).click();
			driver.findElement(By.id("project-title")).sendKeys(projTitle);
			driver.findElement(By.id("project-desc")).sendKeys(projDesc);
			findElement(By.xpath("//select[@name='project-priority']/option[text()='"+projPriority+"']")).click();
			
			//Select Start Date
			findElement(By.id("startDateCal")).click();
			selectDate_v1(projStartDt);
			//Select End date
			findElement(By.id("endDateCal")).click();
			selectDate_v1(projEndDt);
			
			driver.findElement(By.id("auto_sugg_project-currency")).clear();
			enterText_AutoComplete(By.id("auto_sugg_project-currency"), currency);
			
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addprojectScope(int potentialSpend, int potentialSavings, int TargetSpend, int TargetSavings){
		boolean result = false;
		try{
			//Add Potential Savings
			driver.findElement(By.id("potentialSpend")).sendKeys(String.valueOf(potentialSpend));
			driver.findElement(By.id("potentialSavings")).sendKeys(String.valueOf(potentialSavings));
			
			//Add Target Savings
			driver.findElement(By.id("estimatedSpend")).sendKeys(String.valueOf(TargetSpend));
			driver.findElement(By.id("targetSaving")).sendKeys(String.valueOf(TargetSavings));
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
