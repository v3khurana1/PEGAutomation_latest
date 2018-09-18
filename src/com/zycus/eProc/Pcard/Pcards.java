package com.zycus.eProc.Pcard;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> PCards.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.filterByStatus: user shall be able to filter by Status </br>
 * <br>
 * 2.filterByExpiry: </br>
 * <br>
 * 3.filterByIssuedTo: </br>
 * <br>
 * 4.filterByAmount: </br>
 * <br>
 * 5.filterByAmount: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Pcards extends eProc_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;

	/*private By processingLoader = By.id("pCardList_processing");
	private By statusXpath = By.xpath("//table[@id='pCardList']//td[1]/div");
	private By dateXpath = By.xpath("//table[@id='pCardList']//td[contains(@class,'submittedOn')]");
	private By amountXpath = By.xpath("//table[@id='pCardList']//td[contains(@class,'totalAmountReq')]");*/

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public Pcards(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
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

	public boolean filterByStatus(String checkBoxLbl) {
		boolean result = false;
		try {
			String colName = "Status";
			// findElement(By.xpath("//th[contains(@class,'pCardStatusFltrHdr')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByChkbox(checkBoxLbl);
			result = verifyFilteredStatus(colName, colNo, checkBoxLbl) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByExpiry -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromDt
	 *            - From Date , ToDt - To Date
	 * @return result - True/False
	 */

	public boolean filterByExpiry(Date fromDt, Date ToDt) {
		boolean result = false;
		try {
			String colName = "Expiry";
			// findElement(By.xpath("//th[contains(@class,'pCardExpiryFltrHdr')]//b"));
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			filterByDateRange(fromDt, ToDt);
			result = verifyFilteredDates(colNo, fromDt, ToDt) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByIssuedTo -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param issuedUserType
	 * @return result - True/False
	 */

	public boolean filterByIssuedTo(String issuedUserType) {
		boolean result = false;
		try {
			// findElement(By.xpath("//th[contains(@class,'pCardIssuedToFltrHdr')]//b"));
			if (this.filterByStatus(issuedUserType))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmount - To filter by 'Amount'
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromAmt
	 *            - From Amount , ToAmt - To Amount
	 * @return result - True/False
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt) {
		boolean result = false;
		try {
			String colName = "Amount";
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			// findElement(By.xpath("//th[contains(@class,'poamountFltrHdr')]//b"));
			filterByAmtRange(fromAmt, ToAmt);
			result = verifyFilteredAmount(colNo, fromAmt, ToAmt) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByAmount - To filter by 'Amount'
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param fromAmt
	 *            - From Amount , ToAmt - To Amount, currType - Type of currency
	 * @return result - True/False
	 */

	public boolean filterByAmount(float fromAmt, float ToAmt, String currType) {
		boolean result = false;
		String colName = "Amount";
		try {
			int colNo = getColNum(colName);
			findElement(By.xpath("//tr[2]/th[" + colNo + "]//b")).click();
			// findElement(By.xpath("//th[contains(@class,'poamountFltrHdr')]//b"));
			filterByAmtRange(fromAmt, ToAmt, currType);
			result = verifyFilteredAmount(colNo, fromAmt, ToAmt, currType) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
