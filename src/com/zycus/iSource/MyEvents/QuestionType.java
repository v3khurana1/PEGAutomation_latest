package com.zycus.iSource.MyEvents;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import Framework.ConfigurationProperties;
import common.iSource_CommonFunctions;

public class QuestionType extends iSource_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	private String sectionName;
	private String[] questionTypes;
	private By saveBtn = By.xpath("//label[@title='Save']");
	private By backBtn = By.xpath("//div[@class='RFXToppartBg']/a[@title='Back']");

	
	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 * @param logger
	 */

	public QuestionType(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}
	
	public QuestionType(WebDriver driver, ExtentTest logger, String sectionName, String...questionType) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		this.sectionName = sectionName;
		this.questionTypes = questionType;
	}
	
	
	public boolean importSection(){
		boolean result = false;
		try{
			findElement(By.id("importDraftSec")).click();
			findElement(By.id("importFileAnchorId")).click();
			ConfigurationProperties config = ConfigurationProperties.getInstance();
			sendKeys(By.id("importFile"),
					System.getProperty("user.dir") + config.getProperty("upload_file_path"));
			findElement(By.xpath("//div[@id='BrowseFile']//input[@title='Upload']")).click();
			waitUntilInvisibilityOfElement(By.id("LoadingMainDiv1"));
			findElement(By.xpath("//div[@class='impSummaryPopUp']//input[@value='OK']")).click();
			List <WebElement> listOfTabs = driver.findElements(By.xpath("//div[@id='SecTabList']//a[@id='newTab']"));
			if(listOfTabs.size() == 1){
				if(listOfTabs.get(0).getAttribute("title").equals("Type Section Name over here"))
					LogScreenshot("Section not uploaded");
			}else
				LogScreenshot("Section uploaded sucessfully");
			/*try{
				findElement(By.xpath("//div[@aria-describedby='jmsAsyncDivPopUp']//button/span[text()='Ok']")).click();
				waitUntilInvisibilityOfElement(By.xpath("//div[@class='blockUI blockMsg blockPage']"));
				findElement(By.xpath("//div[@aria-describedby='jmsAsyncDivPopUp']//button/span[text()='Ok']")).click();
			}catch(Exception e){}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void addSection(){
		try{
			findElement(By.id("sectScreenName")).sendKeys(sectionName);
			for(String questionType:questionTypes){
				addQuesTypeToSection(questionType);
				prepareQuestions(questionType);
			}
			findElement(saveBtn).click();
			waitUntilInvisibilityOfElement(By.xpath("//div[@class='blockUI blockMsgRfx blockElement']"));
			waitUntilVisibilityOfElement(By.xpath("//div[@id='TransLayer_2' and contains(@style,'none')]"));			
			findElement(backBtn).click();
			waitUntilVisibilityOfElement(By.id("loadingMessageGlobally1"));
			waitUntilInvisibilityOfElement(By.id("loadingMessageGlobally1"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean addQuesTypeToSection(String quesType){
		boolean result = false;
		try{
			WebElement srcElement = driver.findElement(
					By.xpath("//div[@id='ToolbarContainer']/ul/li/div[@class='sidemenu']/div[@class='text1' and text()='"+quesType+"']"));
			
			WebElement dstElement = driver.findElement(
					By.xpath("//ul[@id='screen']"));
			drag_drop(srcElement, dstElement);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean prepareQuestions(String quesType){
		boolean result = false;
		String elemID = "null";
		try{
			switch(quesType){
			case "Text":
				elemID = "TextQuestionPanel";
				prepareTextQues(elemID);
				break;
			case "Yes/No":
				elemID = "YesNoQuestionPanel";
				break;
			case "Single Choice":
				elemID = "ChoiceQuestionPanel";
				break;
			case "Multiple Choice":
				elemID = "MultiChoiceQuestionPanel";
				break;
			case "Attachment":
				elemID = "AttachmentQuestionPanel";
				break;
			case "Comment":
				elemID = "CommentQuestionPanel";
				break;
			case "Numeric":
				elemID = "NumberQuestionPanel";
				break;
			case "Table":
				elemID = "";
				break;
			case "Pricing Lot":
				elemID = "LotQuestionPanel";
				findElement(By.xpath("//ul[@id='screen']/li[@id='"+elemID+"']")).click();
				ItemSettings objSettings = new ItemSettings(driver, logger);
				objSettings.setItemSettings();
				waitUntilVisibilityOfElement(By.xpath("//div[@id='itemTableAnswerContainer']//div[@class='itemListing']/table"));
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private void prepareTextQues(String elemID){
		
	}

}
