package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Framework.CommonUtility;

import com.relevantcodes.extentreports.ExtentTest;

/**
 * <p>
 * <b> Title: </b> eProc_CommonFunctions.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.clrAllFilters: </br>
 * <br>
 * 2.filterByText: </br>
 * <br>
 * 3.verifyFilteredStatus: </br>
 * <br>
 * 4.verifyFilteredDates: </br>
 * <br>
 * 5.verifyFilteredAmount: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */
public class eProc_CommonFunctions extends CommonUtility {

	private WebDriver driver;

	private By filterAppliedIconXpath = By.xpath("//span[@class='icon fltr-clear' and contains(@style,'inline')]");
	// private By processingLoader =
	// By.xpath("//*[contains(@id,'List_processing')]");
	private By processingLoader 		= By.xpath("//div[contains(@id,'processing')]");
	private By filterBtnXpath 	   		   = By.xpath("//div[contains(@id,'qtip') and @aria-hidden='false']//div[contains(@class,'FilterBtnbx')]//a[text()='Filter']");
	private By fromDtPickBtnXpath 		   = By.xpath("(//img[@class='ui-datepicker-trigger'])[1]");
	private By ToDtPickBtnXpath 		   = By.xpath("(//img[@class='ui-datepicker-trigger'])[2]");
	private By minAmtInputXpath 		   = By.xpath("//div[contains(@id,'qtip')]//input[contains(@class, 'minAmount')]");
	private By maxAmtInputXpath 		   = By.xpath("//div[contains(@id,'qtip')]//input[contains(@class, 'maxAmount')]");
	private By clrAllFiltersIconXpath	   = By.xpath("//span[contains(@class,'clearAllFilters')]");
	
	//private ExtentTest logger;
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public eProc_CommonFunctions(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		//this.logger = logger;
	}
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */
	
	public eProc_CommonFunctions() throws Exception {
		super();
	}
	
	
	public void navigate_path1(String tab, String subTab, String childTab) throws Exception {
		try {
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
			Actions action = new Actions(driver);
			/*
			 * WebElement path_tab = driver .findElement(By.xpath(
			 * "//*[@id='zsp-submenus-id']//a//span[text()='" + path + "']"));
			 */
			WebElement path_tab = findElement(By.xpath("//*[@id='zsp-submenus-id']//a//span[text()='" + tab + "']"));
			// path_tab.click();
			// findElement(By.xpath("//*[@id='zsp-submenus-id']//a//span[text()='"
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
			
			waitUntilInvisibilityOfElement(By.xpath("//div[contains(@id,'processing')]"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <b>Function:</b> clrAllFilters
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - True/False
	 */

	public boolean clrAllFilters() {
		boolean result = false;
		try {
			clearAllFilters();
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilterIconsList = driver.findElements(filterAppliedIconXpath);
				if (objfilterIconsList.size() == 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
	 * ---------------------------------------------------------------------------------
	 * Function : clearAllFilters
	 * @return funcCompletion
	 * ---------------------------------------------------------------------------------
	 */
	
	private void clearAllFilters(){
		WebElement objClrFilter = findElement(clrAllFiltersIconXpath);
		if(objClrFilter.getAttribute("style").contains("block"))
			objClrFilter.click();
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
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilteredTxtList = driver.findElements(By.xpath("//tbody//td[" + intColNo + "]"));
				for (WebElement obj : objfilteredTxtList) {
					if (obj.getText().contains(searchValue))
						result = true;
					else {
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
	
	protected int getColNum(String fieldName){
		int intColNum = 0;
		try{
			List<WebElement> headerList = driver.findElements(By.xpath("//thead/tr[1]/th/div/text()"));
			for(WebElement header : headerList){
				intColNum++;
				if(header.getText().equals(fieldName))
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return intColNum;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : dragItemToCart
	 * 
	 * @return ---------------------------------------------------------------------------------
	 */
	/*
	 * public boolean dragItemToCart(){ boolean result = false; WebElement
	 * draggable =
	 * findElement(By.xpath("//*[@id='tblSearchItemList']/tbody/tr[1]"));
	 * WebElement droppable =
	 * findElement(By.xpath("//*[@id='cartHover']//span[@class='bld']")); new
	 * Actions(driver).dragAndDrop(draggable, droppable).build().perform();
	 * return result; }
	 * 
	 *//**
		 * ---------------------------------------------------------------------------------
		 * Function : filterByChkboxType
		 * 
		 * @param chkBoxType
		 * @param typeValue
		 * @return ---------------------------------------------------------------------------------
		 */
	/*
	 * public boolean filterByChkboxType(String chkBoxType, String typeValue){
	 * boolean result = false; if((chkBoxType=="All") | (chkBoxType=="None"))
	 * click(By.xpath("//div[@class='inptBx inptBxMltChk'][label[text()='"
	 * +chkBoxType+"']]/div/div[1]/a[text()='"+typeValue+"']")); else
	 * click(By.xpath("//div[@class='inptBx inptBxMltChk'][label[text()='"
	 * +chkBoxType+"']]/div/div[2]/label[@title='"+typeValue+"']")); return
	 * result; }
	 * 
	 *//**
		 * ---------------------------------------------------------------------------------
		 * Function : filterByTextField
		 * 
		 * @param fieldName
		 * @param fieldValue
		 * @return ---------------------------------------------------------------------------------
		 */

	/*
	 * public boolean filterByTextField(String fieldName, String fieldValue){
	 * boolean result = false;
	 * sendKeys(By.xpath("//div[@class='inptBx'][label[text()='"+fieldName+
	 * "']]/div/input"), fieldValue); return result; }
	 * 
	 *//**
		 * ---------------------------------------------------------------------------------
		 * Function : filterByRange
		 * 
		 * @param rangeName
		 * @param minVal
		 * @param maxVal
		 * @return ---------------------------------------------------------------------------------
		 *//*
		 * 
		 * public boolean filterByRange(String rangeName, String minVal, String
		 * maxVal){ boolean result = false;
		 * sendKeys(By.xpath("//div[@class='inptBx itmBxSlider'][label[text()='"
		 * +rangeName+"']]//input[@class='inptTxt minVal']"), minVal);
		 * sendKeys(By.xpath("//div[@class='inptBx itmBxSlider'][label[text()='"
		 * +rangeName+"']]//input[@class='inptTxt maxVal']"), maxVal); return
		 * result; }
		 */
	
	
	/**
	 * <b>Function:</b> verifyFilteredStatus
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param colName
	 * @param colPos
	 * @param checkBoxLbl
	 * @return result - True/False
	 */
	
	protected boolean verifyFilteredStatus(String colName, int colPos, String checkBoxLbl) {
		boolean result = false;
		String elemXpath = "";
		try {
			elemXpath = "//td[" + colPos + "]/div";
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilteredStatusList = driver.findElements(By.xpath(elemXpath));
				for (WebElement obj : objfilteredStatusList) {
					if (obj.getText().equals(checkBoxLbl))
						result = true;
					else {
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
	
	/**
	 * <b>Function:</b> verifyFilteredDates
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param colPos
	 * @param fromDt
	 * @param ToDt
	 * @return result - True/False
	 */

	protected boolean verifyFilteredDates(int colPos, Date fromDt, Date ToDt) throws ParseException {
		boolean result = false;
		String elemXpath = "";
		try {
			elemXpath = "//td[" + colPos + "]";
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilteredDateList = driver.findElements(By.xpath(elemXpath));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <b>Function:</b> verifyFilteredAmount
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param colPos
	 * @param fromAmt
	 * @param ToAmt
	 * @return result - True/False
	 */
	
	protected boolean verifyFilteredAmount(int colPos, float fromAmt, float ToAmt) throws ParseException {
		boolean result = false;
		String elemXpath = "";
		try {
			elemXpath = "//td[" + colPos + "]";
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilteredAmtList = driver.findElements(By.xpath(elemXpath));
				for (WebElement obj : objfilteredAmtList) {
					Float amount = Float.parseFloat((obj.getText().split(" "))[1]);
					if (amount >= fromAmt && amount <= ToAmt)
						result = true;
					else {
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

	/**
	 * <b>Function:</b> verifyFilteredAmount
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param colPos
	 * @param fromAmt
	 * @param ToAmt
	 * @param currType
	 * @return result - True/False
	 */
	protected boolean verifyFilteredAmount(int colPos, float fromAmt, float ToAmt, String currType)
			throws ParseException {
		boolean result = false;
		String elemXpath = "";
		try {
			elemXpath = "//td[" + colPos + "]";
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				List<WebElement> objfilteredAmtList = driver.findElements(By.xpath(elemXpath));
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 *--------------------------------------------------------------------------------- 
	 * Function : filterByChkbox
	 * @param checkBoxLbl
	 * ---------------------------------------------------------------------------------
	 */
	
	public void filterByChkbox(String checkBoxLbl){
		findElement(By.xpath("(//div[contains(@id,'qtip')]//input[following-sibling::text()[contains(.,'"+checkBoxLbl+"')]])[1]")).click();
		findElement(filterBtnXpath).click();
	}
	
	/**
	 * --------------------------------------------------------------------------------- 
	 * Function : filterByDateRange
	 * @param fromDt
	 * @param ToDt
	 * ---------------------------------------------------------------------------------
	 */
	
	
	public void filterByDateRange(Date fromDt, Date ToDt) throws ParseException{
		
		findElement(fromDtPickBtnXpath).click();
		this.selectDate(fromDt);
		findElement(ToDtPickBtnXpath).click();
		this.selectDate(ToDt);
		findElement(filterBtnXpath).click();
	}
	
	/**
	 *--------------------------------------------------------------------------------- 
	 * Function : filterByAmtRange
	 * @param typeValue
	 * @return funcCompletion
	 * @throws ParseException 
	 * ---------------------------------------------------------------------------------
	 */
	
	
	public void filterByAmtRange(float fromAmt, float ToAmt){
		findElement(minAmtInputXpath).sendKeys(String.valueOf(fromAmt));
		findElement(maxAmtInputXpath).sendKeys(String.valueOf(ToAmt));
		findElement(filterBtnXpath).click();
	}
	
	/**
	 *--------------------------------------------------------------------------------- 
	 * Function : filterByAmtRange
	 * @param typeValue
	 * @return funcCompletion
	 * @throws ParseException 
	 * ---------------------------------------------------------------------------------
	 */
	
	public void filterByAmtRange(float fromAmt, float ToAmt, String currType){
		findElement(minAmtInputXpath).sendKeys(String.valueOf(fromAmt));
		findElement(maxAmtInputXpath).sendKeys(String.valueOf(ToAmt));
		findElement(By.xpath("//div[contains(@id,'qtip')]//input[contains(@class, 'inputCurrencies') and @value ='"+currType+"']")).click();
		findElement(filterBtnXpath).click();
	}

}
