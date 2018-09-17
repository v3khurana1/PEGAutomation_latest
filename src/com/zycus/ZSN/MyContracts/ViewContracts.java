package com.zycus.ZSN.MyContracts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import common.Functions.CommonFunctions1;
//import common.Functions.eInvoice_CommonFunctions;
import com.relevantcodes.extentreports.ExtentTest;

public class ViewContracts extends CommonFunctions1 {

	private WebDriver driver;
	private ExtentTest logger;
	private By processingLoader = By.xpath("//div[contains(@id,'Processing')]");

	public ViewContracts(WebDriver driver, ExtentTest logger) throws Exception {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean performAction(String contractNo, String action) throws Exception {
		boolean result = false;
		int rowNo = 1;
		List<WebElement> list = driver.findElements(By.xpath("//table[@id='AuthoringTableId1']//tr[*]/td[1]"));
		for (WebElement webElement : list) {
			rowNo++;
			System.out.println("row no is " + rowNo);
			if (webElement.getAttribute("title").equals(contractNo))
				break;
		}
		findElement(By.xpath("//table[@id='AuthoringTableId1']//tr[" + rowNo + "]/td[6]//div[@class='actBx']/a"))
				.click();
		findElement(By.xpath(
				"//table//tr[" + rowNo + "]/td[6]//div[@class='actBx']/ul//a[contains(text(),'" + action + "')]"))
						.click();
		switch (action) {
		case "Mark as Reviewed":
			waitUntilVisibilityOfElement(By.xpath("//div[@id='zyDevPopUp']"));
			findElement(By.id("txtAreaComment")).sendKeys("marking as reviewed");
			findElement(By.xpath("//div[@class='primaryButton']/input[@title='Submit']")).click();
			waitUntilInvisibilityOfElement(processingLoader);
			result = true;
			break;
		case "View/Modify Contract":
			break;
		case "Edit":
			break;
		case "Download":
			break;
		case "Undo All Modifications":
			break;
		default:
			LogScreenshot("INFO", "please select valid action");
			break;
		}
		LogScreenshot("INFO", "Contract marked as "+ action);
		return result;
	}

}
