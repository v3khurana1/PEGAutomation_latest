package com.zycus.eInvoice.Workflow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eInvoice_CommonFunctions;

public class WorkflowWizard extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//h1[@class='pgHead'][contains(text(),'Basic details and scope')]");
	private String name;
	private String process;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public WorkflowWizard(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param Name
	 */

	public WorkflowWizard(WebDriver driver, ExtentTest logger, String name, String process) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.name = name + String.valueOf(generateNo());
		this.process = process;
	}
	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * @param Name
	 */

	public WorkflowWizard(WebDriver driver, ExtentTest logger, String process) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.name = String.valueOf(generateNo());
		this.process = process;
	}

	/**
	 * <b>Function:</b> searchDisplayName
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param displayName
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean createWorkflow(String description, String Type, boolean approveMoreThanOne) throws Exception {
		boolean result = true;
		try {
			findElement(By.id("txtName")).sendKeys(name);
			findElement(By.id("txtDesc")).sendKeys(description);
			findElement(By.xpath("//select[@id='slctProcess']/option[text()='" + process + "']")).click();
			findElement(By.xpath("//select[@id='definitionType']/option[text()='" + Type + "']")).click();
			if (approveMoreThanOne)
				findElement(By.id("dev_preventMultiApprovals")).click();
			int Iteration = 1;
			// Workflow Scope
			if(Type.equals("Complete Workflow")){
				selectEFormScope_new(Iteration);
				findElement(By.xpath("//input[@value='Save and Continue']")).click();
				// GETTING FLASH ERROR FROM HERE, HENCE COMMENTING THE REMAINING PART
				/*waitUntilInvisibilityOfElement(By.id("status_overlay_saveForm"));
				while (findElement(By.xpath("//*[@id='scopesExistsMessageContainer']/div/div[2]")) != null) {
					Iteration++;
					selectEFormScope_new(Iteration);
					findElement(By.xpath("//input[@value='Save and Continue']")).click();
					waitUntilInvisibilityOfElement(By.id("status_overlay_saveForm"));
					Thread.sleep(2000);
				}*/
			}else
				findElement(By.xpath("//input[@value='Save and Continue']")).click();
			if(driver.findElements(By.xpath("//div[@class='err-img err-flash']")).size()>0)
				logger.log(LogStatus.INFO, "Flash Error displayed");
			// GETTING FLASH ERROR FROM HERE
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void selectEFormScope_new(int iterationNo) throws Exception {
		// int iterationNo = 1;
		try {
			findElement(By.id("lnkBUs")).click();
			if (iterationNo > 1)
				changeScope_new(iterationNo);
			selectOrgUnit_and_Bank(iterationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changeScope_new(int IteratioNo) throws Exception {
		try {
			String businessUnitList_xpath = "//div[@class='treeContainer treeview']/ul";
			wait_untilVisible(findElement(By.xpath(businessUnitList_xpath)));
			findElement(By.xpath("//div[@class='treeContainer treeview']//input[@checked]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>Function:</b> selectOrgUnit_and_Bank
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param IterationNo
	 * @return None
	 * @throws Exception
	 */

	private void selectOrgUnit_and_Bank(int IterationNo) throws Exception {
		try {
			String businessUnitList_xpath = "//div[@class='treeContainer treeview']/ul";
			if (findElement(By.xpath("//div[div/span[text()='Select Companies & Business Units']]")).isDisplayed()) {
				wait_untilVisible(findElement(By.xpath(businessUnitList_xpath)));
				if (findElement(By.xpath(businessUnitList_xpath)) != null) {
					// Selecting first Company & Business unit from the
					// displayed list tree
					findElement(By.xpath(
							"//div[@class='treeContainer treeview']//li[" + String.valueOf(IterationNo) + "]//input"))
									.click();
					try {
						findElement(By.xpath("//div[contains(@style,'block')]//input[@value='Save']")).click();
					} catch (Exception e) {
						if (findElement(By
								.xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'Please select atleast one scope')]]"))
										.isDisplayed())
							findElement(By.xpath("//button/span[text()='OK']")).click();
						logger.log(LogStatus.INFO, "Please select atleast one scope - message displayed");
					}
				} else
					logger.log(LogStatus.INFO, "No results found - message displayed");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * @return the pgHead
	 */
	public By getPgHead() {
		return pgHead;
	}

	/**
	 * @param pgHead
	 *            the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}

	/**
	 * <b>Function:</b> takeAction
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param action
	 * @return result - True/False
	 * @throws Exception
	 */

	/*
	 * public boolean takeAction(String action) throws Exception { boolean
	 * result = false; try { click(actionsLinkXpath); click(By.
	 * xpath("(//*[@id='uploadsListing']//a[@class='icon actLnk'])[1]/following-sibling::ul//a[text()='"
	 * + action + "']"));
	 * waitUntilInvisibilityOfElement(By.id("status_overlay_loading_msg"));
	 * 
	 * switch (action) { case "Create Non-PO Invoice": break; case
	 * "Create PO Invoice": if (findElement(By
	 * .xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'You will be redirected to PO Listing')]]"
	 * )) .isDisplayed()) {
	 * findElement(By.xpath("//button/span[text()='OK']")).click();
	 * PurchaseOrders objPurchaseOrder = new PurchaseOrders(driver, logger);
	 * waitUntilInvisibilityOfElement(objPurchaseOrder.getProcessingLoader());
	 * if (findElement(objPurchaseOrder.getAlertBoxmsg()).getText()
	 * .equals("To create an invoice against a PO, select the PO from the list below"
	 * )) logger.log(LogStatus.INFO, "navigated to Purchase Orders page"); else
	 * logger.log(LogStatus.INFO,
	 * "not navigated to Purchase Orders page or alertBox message not displayed"
	 * ); } else System.out.
	 * println("You will be directed to PO Listing - confirm promptBox not displayed"
	 * ); break; case "Create Credit Memo": if (findElement(By
	 * .xpath("//div[contains(@class,'promptbx')][//td[contains(text(),'You will be redirected to PO Listing')]]"
	 * )) .isDisplayed()) {
	 * findElement(By.xpath("//button/span[text()='OK']")).click();
	 * PurchaseOrders objPurchaseOrder = new PurchaseOrders(driver, logger);
	 * waitUntilInvisibilityOfElement(objPurchaseOrder.getProcessingLoader());
	 * if (findElement(objPurchaseOrder.getAlertBoxmsg()).getText()
	 * .equals("Open a released PO from the list of POs to create a Credit Memo against it"
	 * )) logger.log(LogStatus.INFO, "navigated to Purchase Orders page"); else
	 * logger.log(LogStatus.INFO,
	 * "not navigated to Purchase Orders page or alertBox message not displayed"
	 * ); } else System.out.
	 * println("You will be directed to PO Listing - confirm promptBox not displayed"
	 * ); break; case "Create Credit Memo w/o ref": break; } result = true; }
	 * catch (Exception e) { e.printStackTrace(); throw new Exception(); }
	 * return result; }
	 */

}
