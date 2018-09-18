package com.zycus.eProc.Budget;

//import java.text.ParseException;
import java.util.Date;
//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.zycus.eProc.Budget.Budget_AddPeriod;

import common.Functions.eInvoice_CommonFunctions;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Budget.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.createNewBudget_ByLink: </br>
 * <br>
 * 2.createNewBudget_ByAddbtn: </br>
 * <br>
 * 3.selectPeriod: </br>
 * <br>
 * 4.addPeriod: </br>
 * <br>
 * 4.filterByStatus: </br>
 * <br>
 * 4.filterByPlannedAmount: </br>
 * <br>
 * 4.filterByReservedAmount: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Budget extends eProc_CommonFunctions {
	private WebDriver driver;
	private ExtentTest logger;

	private By createBudgetBtn = By.id("createBudgetBtn");
	private By createNewBudgetLink = By.xpath("//table[contains(@class,'dataTable')]//a[@title='Create new budget']");
	//private By processingLoader = By.id("budgetList_processing");
	private By statusXpath = By.xpath("//table[contains(@class,'dataTable')]//td[1]/div");
	private By plannedAmountXpath = By.xpath("//table[contains(@class,'dataTable')]//td[contains(@class,'totalAmountReq')]");
	private By reservedAmountXpath = By.xpath("//table[contains(@class,'dataTable')]//td[contains(@class,'totalAmountReq')]");
	private By actionsLinkXpath = By.xpath("//table[contains(@class,'dataTable')]//tr[1]/td[last()]//a[text()='Actions']");

	public Budget(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	eInvoice_CommonFunctions objFunctions = new eInvoice_CommonFunctions(driver, logger);

	/**
	 * <b>Function:</b> createNewBudget_ByLink
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - true/false
	 */

	public boolean createNewBudget_ByLink() {
		boolean result = false;
		try {
			findElement(createNewBudgetLink).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> createNewBudget_ByAddbtn
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - true/false
	 */

	public boolean createNewBudget_ByAddbtn(String ownerName, String budgetName, String companyName, String currType, Date ToDt) {
		boolean result = false;
		try {
			findElement(createBudgetBtn).click();
			Budget_NewBudget objNewBudget = new Budget_NewBudget(driver, logger, ownerName, budgetName, companyName, currType, ToDt);
			if(findElement(objNewBudget.getNewBudgetLbl())!=null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> selectPeriod
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param period
	 * @return result - true/false
	 */

	public boolean selectPeriod(String period) {
		boolean result = false;
		try {
			findElement(By.xpath("//div[@id='budgerPeriodDropDown']/div/ol/li/a[text()='" + period + "']")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addPeriod
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param StartDt
	 * @param EndDt
	 * @param periodName
	 * @param description
	 * @return result - true/false
	 */

	public boolean addPeriod(Date StartDt, Date EndDt, String periodName, String description) {
		boolean result = false;
		try {
			findElement(By.id("addBudgetPeriod")).click();
			Budget_AddPeriod objAddPeriod = new Budget_AddPeriod(driver, logger, StartDt, EndDt, periodName);
			if (objAddPeriod.addPeriod(description))
				result = true;
			else
				logger.log(LogStatus.INFO, "Period not added to the list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByStatus - To filter by Status
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param checkBoxLbl
	 *            - Label of the checkbox which is to be selected
	 * @return result - True/False
	 */

	/*
	 * public boolean filterByStatus(String checkBoxLbl){ boolean result =
	 * false; try{ String colName = "Status";
	 * //click(By.xpath("//th[contains(@class,'budgetStatusFltrHdr')]//b")); int
	 * colNo = getColNum(colName);
	 * findElement(By.xpath("//tr[2]/th["+colNo+"]//b")).click();
	 * filterByChkbox(checkBoxLbl); result = verifyFilteredStatus(colName,
	 * colNo, checkBoxLbl)?true:false; }catch(Exception e){ e.printStackTrace();
	 * } return result; }
	 */
	public boolean filterByStatus(String checkBoxLbl) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'budgetStatusFltrHdr')]//b")).click();
			result = objFunctions.filterByChkbox(checkBoxLbl, statusXpath) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByPlannedAmount - To filter by 'Planned Amount'
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromAmt
	 *            - From Amount , ToAmt - To Amount
	 * @return result - True/False
	 */

	/*
	 * public boolean filterByPlannedAmount(float fromAmt, float ToAmt){ boolean
	 * result = false; try{ String colName = "Amount to be approved"; int colNo
	 * = getColNum(colName);
	 * findElement(By.xpath("//tr[2]/th["+colNo+"]//b")).click();
	 * //click(By.xpath("//th[contains(@class,'budgetAmountFltrHdr')]//b"));
	 * filterByAmtRange(fromAmt, ToAmt); result = verifyFilteredAmount(colNo,
	 * fromAmt, ToAmt)?true:false; }catch(Exception e){ e.printStackTrace(); }
	 * return result; }
	 */

	public boolean filterByPlannedAmount(float fromAmt, float ToAmt) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'budgetAmountFltrHdr')]//b")).click();
			result = objFunctions.filterByAmtRange(fromAmt, ToAmt, plannedAmountXpath) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByReservedAmount - To filter by 'Reserved Amount'
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromAmt
	 *            - From Amount , ToAmt - To Amount
	 * @return result - True/False
	 */

	/*
	 * public boolean filterByReservedAmount(float fromAmt, float ToAmt){
	 * boolean result = false; try{ String colName = "Amount to be approved";
	 * int colNo = getColNum(colName);
	 * findElement(By.xpath("//tr[2]/th["+colNo+"]//b")).click();
	 * //click(By.xpath("//th[contains(@class,'budgetUtilizationFltrHdr')]//b"))
	 * ; filterByAmtRange(fromAmt, ToAmt); result = verifyFilteredAmount(colNo,
	 * fromAmt, ToAmt)?true:false; }catch(Exception e){ e.printStackTrace(); }
	 * return result; }
	 */

	public boolean filterByReservedAmount(float fromAmt, float ToAmt) {
		boolean result = false;
		try {
			findElement(By.xpath("//th[contains(@class,'budgetUtilizationFltrHdr')]//b")).click();
			result = objFunctions.filterByAmtRange(fromAmt, ToAmt, reservedAmountXpath) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByBudgetName(String budgetName) {
		boolean result = false;
		try {
			result = objFunctions.filterByText("Budget Name", budgetName) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByCompany(String company) {
		boolean result = false;
		try {
			result = objFunctions.filterByText_AutoComplete("Company", company) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean filterByOwner(String owner) {
		boolean result = false;
		try {
			result = objFunctions.filterByText_AutoComplete("Owner", owner) ? true : false;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean selectAction(String action) {
		boolean result = false;
		try {
			findElement(actionsLinkXpath).click();
			findElement(By
					.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
							+ action + "']")).click();
			switch (action) {
			case "View":
				WebElement objbudgetName = findElement(By.xpath("//td[contains(@class,'name qtipFltrHdr')]"));
				String budgetName = objbudgetName.getText();
				
				BudgetDetails objDetails = new BudgetDetails(driver, logger);
				if (findElement(objDetails.getBudgetlabel()).getText() == budgetName)
					result = true;
				break;
			case "Deactivate":
				if(driver.findElements(By.xpath("//div[contains(@class,'promptbx')][div/span[text()='Confirm']]")).size()>0){
					/*findElement(By.xpath("//div[contains(@class,'promptbx')][div/span[text()='Confirm']]//button[span[text()='Yes']]")).click();
					waitUntilInvisibilityOfElement(processingLoader);*/
					clickAndWaitUntilLoaderDisappears(By.xpath("//div[contains(@class,'promptbx')][div/span[text()='Confirm']]//button[span[text()='Yes']]"));
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public boolean selectAction(String action, String updatedEndDt) {
		boolean result = false;
		try {
			findElement(actionsLinkXpath).click();
			findElement(By
					.xpath("(//*[@id='workflowApproval']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
							+ action + "']")).click();
			switch (action) {
			case "Update validity":
				findElement(By.xpath("//input[@id='updateValidityEndDate']/following-sibling::img")).click();
				selectDate_v1(updatedEndDt);
				findElement(By.xpath("//input[@value ='Update']")).click();
				waitUntilInvisibilityOfElement(By.xpath("//div[contains(@class,'overlay')]"));
				result = true;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
