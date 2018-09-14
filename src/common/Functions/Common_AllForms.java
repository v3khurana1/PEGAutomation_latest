package common.Functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.relevantcodes.extentreports.ExtentTest;

//import common.eForms.Common_AllForms;

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

public class Common_AllForms extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private By pgHead = By.xpath("//h1[@class='pgHead'][contains(text(),'All Forms')]");
	private String product;
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 * 
	 */
	
	public Common_AllForms(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	/*public Common_AllForms(WebDriver driver, ExtentTest logger, String product) {
		super(driver, logger, product);
	}*/

	/**
	 * <b>Function:</b> selectNewFormCreationProcess
	 * 
	 * @author Varun Khurana
	 * @since May 2018
	 * @param formCreationProcess
	 * @return result - True/False
	 * @throws Exception
	 */

	/*public boolean selectNewFormCreationProcess(String formCreationProcess) throws Exception {
		boolean result = false;
		try {
			WebElement hoverElem = findElement(By.xpath("//a[span[text()='New eForm']]"));
			Actions builder = new Actions(driver);
			builder.moveToElement(hoverElem).build().perform();
			findElement(By.xpath("//a[span[text()='"+formCreationProcess+"']]")).click();
			FormWizard objWizard = new FormWizard(driver, logger);
			if(objWizard.getPgHead()!=null)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
*/
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
