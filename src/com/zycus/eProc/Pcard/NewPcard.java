package com.zycus.eProc.Pcard;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> NewPcard.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.filterByStatus: user shall be able to filter by Status </br>
 * <br>
 * 2.filterByReceivedOn: user shall be able to filter by Received On </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class NewPcard extends eProc_CommonFunctions {

	//private WebDriver driver;
	//private ExtentTest logger;
	
	private String issuedTo;
	private String cardNo;
	private Date lastBilledOn;
	private String issuingBank;
	private String stmtFreq;
	private int creditDays;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param issuedTo
	 * @param cardNo
	 * @param lastBilledOn
	 * @param issuingBank
	 * @param stmtFreq
	 * @param creditDays
	 */
	
	public NewPcard(WebDriver driver, ExtentTest logger, String issuedTo, String cardNo, Date lastBilledOn, String issuingBank, 
			String stmtFreq, int creditDays) {
		super(driver, logger);
		//this.driver = driver;
		//this.logger = logger;
		this.issuedTo = issuedTo;
		this.cardNo = cardNo;
		this.lastBilledOn = lastBilledOn;
		this.issuingBank = issuingBank;
		this.stmtFreq = stmtFreq;
		this.creditDays = creditDays;
	}

	/**
	 * <b>Function:</b> addNewPcard -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param type
	 * @param mm
	 * @param yy
	 * @param issuedUserType
	 * @param nameOnCard
	 * @param allowedOrgUnits
	 * @param spendPerTransaction
	 * @param transactionsPerDay
	 * @param spendPerDay
	 * @param transactionsPerCycle
	 * @param spendPerCyc
	 * @return result - True/False
	 */

	public boolean addNewPcard(String type, int mm, int yy, String issuedUserType, String nameOnCard,
			String allowedOrgUnits, int spendPerTransaction, int transactionsPerDay, int spendPerDay,
			int transactionsPerCycle, int spendPerCycle) {
		boolean result = true;
		try {
			findElement(By.xpath("//select[@id='txtType']/option[text()='" + type + "']")).click();
			findElement(By.xpath("//select[@id='txtExpiryMonth']/option[@value='" + mm + "']")).click();
			findElement(By.xpath("//select[@id='txtExpiryYear']/option[@value='" + yy + "']")).click();
			findElement(By.xpath("//select[@id='txtExpiryYear']/option[@value='" + yy + "']")).click();
			findElement(By.xpath("//label[input[contains(@id,'issuedTo')]]/text()[contains(.,'" + issuedUserType + "')]")).click();
			sendKeys(By.id("txtIssuedTo"), issuedTo);
			sendKeys(By.id("txtCardNo"), cardNo);
			findElement(By.xpath("//input[@id='txtStatementDate']/following-sibling::img")).click();
			selectDate(lastBilledOn);
			sendKeys(By.id("txtBankName"), issuingBank);
			findElement(By.xpath("//select[@id='txtBillingCycle']/option[@value='" + stmtFreq + "']")).click();
			sendKeys(By.id("txtCreditDays"), String.valueOf(creditDays));
			sendKeys(By.id("txtNameOnCard"), nameOnCard);
			findElement(By.xpath("//select[@id='txtOrganizationUnits']/option[@value='" + allowedOrgUnits + "']")).click();
			sendKeys(By.id("txtSpendPerTransaction"), String.valueOf(spendPerTransaction));
			sendKeys(By.id("txtTransactionsPerDay"), String.valueOf(transactionsPerDay));
			sendKeys(By.id("txtSpendPerDay"), String.valueOf(spendPerDay));
			sendKeys(By.id("txtTransactionsPerCycle"), String.valueOf(transactionsPerCycle));
			sendKeys(By.id("txtSpendPerCycle"), String.valueOf(spendPerCycle));
			findElement(By.id("btnSubmitPCard")).click();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}
