package com.zycus.IContract.Setup.ProductConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import common.Functions.eInvoice_CommonFunctions;

public class ProdConfig extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;

	public ProdConfig(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	public boolean selectWorkflow(String category, String subCategory) {
		boolean result = false;
		try {
			result = clickAndWaitUntilElementAppears(
					By.xpath("//h2[@class='hd' and text()='" + category + "']/following-sibling::div/a[text()='"
							+ subCategory + "']"),
					By.xpath("//h3[contains(@class,'mainhead')]/label[text()='" + subCategory + "']")) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
