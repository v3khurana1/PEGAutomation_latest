package com.zycus.eProc.Requisition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.zycus.eProc.*;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * <p>
 * <b> Title: </b> Requisition_ViewOrders.java</br>
 * <br>
 * <b> Description: </b> To perform operations on pages Approval & All Requests
 * </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.takeAction: </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Requisition_ViewOrders extends RequisitionDetails {

	private WebDriver driver;
	private ExtentTest logger;
	private By actionsLinkXpath = By.xpath("//table[contains(@class,'dataTable')]//tr[1]/td[last()]//a[text()='Actions']");
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public Requisition_ViewOrders(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> takeAction -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param action
	 * @return result - True/False
	 */

	public boolean takeAction(String action) {
		boolean result = false;
		try {
			findElement(actionsLinkXpath).click();
			findElement(By.xpath("(//table[contains(@class,'dataTable')]//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='" + action
					+ "']")).click();

			if (super.verifyDisplayedAction(action)) {

				WebElement objRequisitionNum = findElement(By.xpath("(//table[@id='reqList']//td[2]/a)[1]"));
				String requisitionNum = objRequisitionNum.getText();

				WebElement objRequisitionName = findElement(By.xpath("(//table[@id='reqList']//td[3])[1]"));
				String requisitionName = objRequisitionName.getText();

				switch (action) {
				case "Create Receipt":
					CreateReceipt_page objCreateReceipt = new CreateReceipt_page(driver, logger);
					if (findElement(objCreateReceipt.getHeaderReqNum()).getText() == requisitionNum
							&& findElement(objCreateReceipt.getHeaderReqName()).getText() == requisitionName)
						result = true;
					else {
						logger.log(LogStatus.INFO, "Requested Requisition not opened for Create Receipt");
						result = false;
					}
					break;
				case "View":
					break;
				case "Download":
					break;
				}
			} else
				logger.log(LogStatus.INFO, "Requisition is not in required status for the requested action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
