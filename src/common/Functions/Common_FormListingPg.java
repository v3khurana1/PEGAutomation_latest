package common.Functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
//import common.eForms.Common_FormListingPg;

/**
 * <p>
 * <b> Title: </b> AllForms.java</br>
 * <br>
 * <b> Description: </b> To perform select New eForm </br>
 * <br>
 * <b> Functions: </br>
 * </b> <br>
 * 1.createNewForm: user shall be able to filter by Status </br>
 * <br>
 * 2.addSection: user shall be able to filter by Received On </br>
 * <br>
 * 3.addfield </br>
 * 
 * @author Varun Khurana
 * @since April 2018
 */

public class Common_FormListingPg extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//div[@class='zydf-page_label' and contains(text(),'Form Listing Page')]");
	private String product;

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */

	public Common_FormListingPg(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * <b>Function:</b> selectNewFormCreationProcess
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param formCreationProcess
	 * @return result - True/False
	 * @throws Exception
	 */

	public boolean clickCreateNewFlexiFormBtn() throws Exception {
		boolean result = false;
		try {
			findElement(By.id("lnkAddForm")).click();
			Common_ConfigureFlexiForm objFlexi = new Common_ConfigureFlexiForm(driver, logger, product);
			if(objFlexi.getPgHead()!=null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the pgHead
	 */
	public By getPgHead() {
		return pgHead;
	}

	/**
	 * @param pgHead the pgHead to set
	 */
	public void setPgHead(By pgHead) {
		this.pgHead = pgHead;
	}

}
