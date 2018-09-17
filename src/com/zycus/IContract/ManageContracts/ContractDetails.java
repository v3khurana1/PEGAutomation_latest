package com.zycus.IContract.ManageContracts;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Framework.ConfigurationProperties;
import common.Functions.eInvoice_CommonFunctions;

public class ContractDetails extends eInvoice_CommonFunctions {

	private WebDriver driver;
	private ExtentTest logger;
	//private By processingLoader = By.xpath("//span[@class='zys-loader-icon']");
	private By pgHead = By.xpath("//span[@id='headerLabel']/following-sibling::h2[text()='Contract Details']");

	//private String elemFinderXpath = "//div[@class='subHeader' and not(contains(@style,'none'))]//table[@class='zytbl genericDetailsTbl']/tbody/tr/td[not(contains(@style,'none'))]/div/*[@fieldtype][not(@readonly)][not(@disabled)]";
	private String elemFinderXpath = null;
	Random rnd = new Random();
	LocalDate currentDate = LocalDate.now();
	
	private int contractYr = Integer.parseInt((currentDate.toString().split("-"))[0]);
	private int contractMnth = Integer.parseInt((currentDate.toString().split("-"))[1]);
	private int contractDay = Integer.parseInt((currentDate.toString().split("-"))[2]);
	
	/*private int contractYr = 2000 + rnd.nextInt(2019 - 2000);
	private int contractMnth = 1 + rnd.nextInt(13);
	private int contractDay = 1;*/
	
	private int scrollValue = 0;
	private int scrollCtr= 0;

	public ContractDetails(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
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

	public void setElemFinderXpath(String elemFinderXpath) {
		this.elemFinderXpath = elemFinderXpath;
	}

	public void setContractYr(int contractYr) {
		this.contractYr = contractYr;
	}

	public void setContractMnth(int contractMnth) {
		this.contractMnth = contractMnth;
	}

	public void setContractDay(int contractDay) {
		this.contractDay = contractDay;
	}
	
	private void checkAndScroll() throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrolledValue = Integer.parseInt(js.executeScript("return window.pageYOffset").toString());
		if(scrolledValue>0){
			js.executeScript("window.scrollTo(0,0);");
			Thread.sleep(800);
		}
	}

	/*public boolean enterContractDetails_old() throws Exception {
		boolean result = false;
		boolean temp = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			waitUntilVisibilityOfElement(By.xpath("//ul[@id='headerTab']"));
			List<WebElement> headersList = driver.findElements(By.xpath("//ul[@id='headerTab']/li"));
			//System.out.println("Number of headers " + headersList.size());
			for (WebElement header : headersList) {
				if(headersList.size()>1){
					js.executeScript("arguments[0].click();", header);
					elemFinderXpath = "//div[@class='subHeader' and not(contains(@style,'none'))]//table[@class='zytbl genericDetailsTbl']/tbody/tr/td[not(contains(@style,'none'))]/div/*[@fieldtype][not(@readonly)][not(@disabled)]";
					Thread.sleep(2000);
				}
				int scrolledValue = Integer.parseInt(js.executeScript("return window.pageYOffset").toString());
				if(scrolledValue>0){
					js.executeScript("window.scrollTo(0,0);");
					Thread.sleep(500);
				}
				checkAndScroll();
				if (driver.findElements(By.xpath("//div[@class='subHeader' and not (contains(@style,'none'))]"))
						.size() > 0) {
					int formFillCtr = 1;
					while (driver.findElements(By.xpath(elemFinderXpath)).size() > 0) {
						if (formFillCtr > 5)
							break;
						String tempFinderXpath = elemFinderXpath;
						enterSubHeaderDetails(elemFinderXpath);
						Thread.sleep(2000);
						System.out.println("Here is the new xpath: " + tempFinderXpath
								+ "[not(contains(@class,'edited'))]" + "with size "
								+ driver.findElements(By.xpath(tempFinderXpath + "[not(contains(@class,'edited'))]"))
										.size());
						formFillCtr++;
					}
					temp = true;
				} else if (driver
						.findElements(By.xpath("//div[@id='flexiFormContainer' and not (contains(@style,'none'))]"))
						.size() > 0) {
					enterFlexiFormDetails();
					temp = true;
				}
				if (temp == true) {
					findElement(By.id("saveBtn")).click();
					waitUntilInvisibilityOfElement(processingLoader);
					ContractingParty objContParty = new ContractingParty(driver, logger);
					if (objContParty.getPgHead() != null){
						LogScreenshot("INFO", "entered details in Contract Details page");
						result = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "All details not entered in Contract Details page");
			throw new Exception();
		}
		return result;
		Long a =(Long)js.executeScript("var element = document.evaluate(\"//input[@class='tableSearchButton']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;return element.getBoundingClientRect().y;");
			//document.evaluate(\"//div[contains(@class,'qtip qtip-default filterPopups') and contains(@style,'block')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
			System.out.println(a);
	}*/
	
	public boolean enterContractDetails(ContractingParty objContParty) throws Exception {
		boolean result = false;
		boolean temp = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			LocalDate currentDate = LocalDate.now();
			int contractYr1 = Integer.parseInt((currentDate.toString().split("-"))[0]);
			int contractMnth1 = Integer.parseInt((currentDate.toString().split("-"))[1]);
			int contractDay1 = Integer.parseInt((currentDate.toString().split("-"))[2]);
			System.out.println("jeasfbgkj "+contractYr1+" hjds "+" sjrj "+contractMnth1+" jdhj "+contractDay1);
			
			
			waitUntilVisibilityOfElement(By.xpath("//ul[@id='headerTab']"));
			List<WebElement> headersList = driver.findElements(By.xpath("//ul[@id='headerTab']/li"));
			int i =1;
			for (WebElement header : headersList) {
				elemFinderXpath = "//div[@class='subHeader' and not(contains(@style,'none'))]//table[@class='zytbl genericDetailsTbl']/tbody/tr/td[not(contains(@style,'none'))]/div/*[@fieldtype][not(@readonly)][not(@disabled)]";
				if(headersList.size()>1){
					/*for(int i =0; i<headersList.size();i++)
						System.out.println(headersList.get(i).getText());*/
					js.executeScript("arguments[0].click();", header);
					i++;
					//header.click();
					scrollCtr = 0;
					//elemFinderXpath = "//div[@class='subHeader' and not(contains(@style,'none'))]//table[@class='zytbl genericDetailsTbl']/tbody/tr/td[not(contains(@style,'none'))]/div/*[@fieldtype][not(@readonly)][not(@disabled)]";
					Thread.sleep(1000);
				}
				checkAndScroll();
				if (driver.findElements(By.xpath("//div[@class='subHeader' and not (contains(@style,'none'))]"))
						.size() > 0) {
					int formFillCtr = 1;
					logger.log(LogStatus.INFO, "filling details in " + header.getText() + " form");
					while (driver.findElements(By.xpath(elemFinderXpath)).size() > 0) {
						if (formFillCtr > 5)
							break;
						//String tempFinderXpath = elemFinderXpath;
						enterFormDetails(elemFinderXpath);
						Thread.sleep(2000);
						formFillCtr++;
						driver.findElement(By.xpath("//div[@class='leftMenuDiv']")).click();
					}
					temp = true;
				} else if (driver
						.findElements(By.xpath("//div[@id='flexiFormContainer' and not (contains(@style,'none'))]"))
						.size() > 0) {
					LogScreenshot("INFO", "filling details in " + header.getText() + " flexi-form");
					enterFlexiFormDetails();
					temp = true;
				}
				System.out.println(temp);
				System.out.println(i);
				System.out.println(temp == true);
				System.out.println(i==headersList.size());
				System.out.println((temp == true) && (i==headersList.size()));
				if ((temp == true) && (i==headersList.size())) {
					LogScreenshot("INFO", "entered details in Contract Details page");
					clickAndWaitUntilLoaderDisappears(By.id("saveBtn"));
					//ContractingParty objContParty = new ContractingParty(driver, logger);
					if (objContParty.getPgHead() != null){
						result = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogScreenshot("INFO", "All details not entered in Contract Details page");
			throw new Exception();
		}
		return result;
	}
	
	


	/*private void enterSubHeaderDetails_old(String elemFinderXpath) {
		try {
			//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
			System.out.println("Here is the xpath " + elemFinderXpath);
			String elemID = null;
			Point objHeaderLoc = driver.findElement(By.xpath("//div[@class='zys-default-tab']")).getLocation();
			int headeryCoord = objHeaderLoc.getY();
			Point objFooterLoc = driver.findElement(By.id("addRepoContractFooter")).getLocation();
			int yCoord = objFooterLoc.getY();
			//scrollValue = yCoord-headeryCoord-100;
			List<WebElement> objAllElements = driver.findElements(By.xpath(elemFinderXpath));
			System.out.println("No of elements to choose " + objAllElements.size());
			
			int elemRelPos = 0;
			for (WebElement objElem : objAllElements) {
				 //Thread.sleep(2000);
				if(scrollCtr==0)
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue;
				else
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue*scrollCtr;
				if (elemRelPos > yCoord) {
				//if (objElem.getLocation().getY() > yCoord) {
					//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", objElem);
					scrollValue = yCoord-headeryCoord-100;
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+scrollValue+");", objElem);
					Thread.sleep(1000);
					scrollCtr++;
				}
				String fieldType = objElem.getAttribute("fieldtype");
				switch (fieldType) {
				case "INTEGER":
					try {
						objElem.sendKeys(String.valueOf(rnd.nextInt(32)));
						// ((JavascriptExecutor)
						// driver).executeScript("arguments[0].value="+String.valueOf(rnd.nextInt(32))+";",
						// objElem);
						//((JavascriptExecutor) driver).executeScript("arguments[0].value="+ String.valueOf(rnd.nextInt(32)),objElem);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DATE":
					try {
						if (!objElem.getAttribute("class").contains("edited")) {
							// ((JavascriptExecutor)
							// driver).executeScript("arguments[0].click();",
							// objElem);
							objElem.click();
							int randomYr = contractYr;
							int randomMnth = contractMnth;
							int randomDay = contractDay;
							if (randomMnth == 1 || randomMnth == 3 || randomMnth == 5 || randomMnth == 7
									|| randomMnth == 8 || randomMnth == 10 || randomMnth == 12)
								randomDay = rnd.nextInt(32);
							else if (randomMnth == 4 || randomMnth == 6 || randomMnth == 9 || randomMnth == 11)
								randomDay = rnd.nextInt(31);
							else
								randomDay = rnd.nextInt(29);

							String randomDt = String.valueOf(randomDay) + "/" + String.valueOf(randomMnth) + "/"
									+ String.valueOf(randomYr);
							selectDate_v1(randomDt);
							System.out.println(randomDt);
							this.setContractYr(randomYr + rnd.nextInt(2));
							if (contractYr > randomYr)
								this.setContractMnth(1 + rnd.nextInt(randomMnth));
							else
								this.setContractMnth(randomMnth + 1 + rnd.nextInt(12 - randomMnth));
							this.setContractDay(1 + rnd.nextInt(randomDay));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DECIMAL":
					try {
						// ((JavascriptExecutor)
						// driver).executeScript("arguments[0].value="+";",
						// objElem);
						// ((JavascriptExecutor)
						// driver).executeScript("arguments[0].value="+String.valueOf(rnd.nextFloat())+";",
						// objElem);
						objElem.clear();
						objElem.sendKeys(String.valueOf(rnd.nextInt(10) + rnd.nextFloat()));
						//((JavascriptExecutor) driver).executeScript("arguments[0].value="+ String.valueOf(rnd.nextInt(10) + rnd.nextFloat()),objElem);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DROPDOWN":
					try {
						if (!objElem.getAttribute("class").contains("edited")) {
							elemID = objElem.getAttribute("id");
							
							 * ((JavascriptExecutor)
							 * driver).executeScript("arguments[0].click();",
							 * findElement(By.xpath("//select[@id='" + elemID +
							 * "']/following-sibling::span//span[contains(@class,'arrow')]"
							 * )));
							 
							int optionsCount = driver
									.findElements(By.xpath(elemFinderXpath + "[@id='" + elemID + "']/option")).size();
							findElement(By.xpath("//select[@id='" + elemID+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();
							//Thread.sleep(2000);
							//String optionCalcXpath = elemFinderXpath + "[@id='" + elemID + "']/option";
							int optionsCount = driver
									.findElements(By.xpath(elemFinderXpath + "[@id='" + elemID + "']/option")).size();
							//int optionsCount = driver.findElements(By.xpath(optionCalcXpath)).size();
							
							System.out.println("No of options in " + optionsCount);
							String temp = elemFinderXpath + "[@id='" + elemID + "']/option["
									+ String.valueOf(2 + rnd.nextInt(optionsCount - 1)) + "]";
							System.out.println(temp);
							
							 * ((JavascriptExecutor)
							 * driver).executeScript("arguments[0].click();",
							 * findElement(By.xpath(temp)));
							 * ((JavascriptExecutor)
							 * driver).executeScript("arguments[0].click();",
							 * findElement(By.xpath("//select[@id='" + elemID +
							 * "']/following-sibling::span//span[contains(@class,'arrow')]"
							 * )));
							 
							findElement(By.xpath(temp)).click();
							findElement(By.xpath("//select[@id='" + elemID
									+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "TEXTAREA":
				case "STRING":
					try {
						// ((JavascriptExecutor)
						// driver).executeScript("arguments[0].value="+";",
						// objElem);
						// ((JavascriptExecutor)
						// driver).executeScript("arguments[0].value="+"Auto_" +
						// RandomStringUtils.random(6, true, false)+";",
						// objElem);
						objElem.clear();
						objElem.sendKeys("Auto_" + RandomStringUtils.random(6, true, false));
						//((JavascriptExecutor) driver).executeScript("arguments[0].value=Auto_"+ RandomStringUtils.random(6, true, false),objElem);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			this.setElemFinderXpath(elemFinderXpath + "[not(contains(@class,'edited'))]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
	}*/
	
	/*private void enterFormDetails_old1(String elemFinderXpath) {
		String elemID = null;
		int headerYCoord = 0;
		int footerYCoord = 0;
		int randomYr = 0;
		int randomMnth = 0;
		int randomDay = 0;
		try {
			Point objHeaderLoc = driver.findElement(By.xpath("//div[@class='zys-default-tab']")).getLocation();
			headerYCoord = objHeaderLoc.getY();
			Point objFooterLoc = driver.findElement(By.id("addRepoContractFooter")).getLocation();
			footerYCoord = objFooterLoc.getY();
			List<WebElement> objAllElements = driver.findElements(By.xpath(elemFinderXpath));
			int elemRelPos = 0;
			System.out.println("no of fields to choose "+ objAllElements.size());
			
			
			scrollValue = footerYCoord-headerYCoord-100;
			
			
			for (WebElement objElem : objAllElements) {
				if(objElem.getLocation().getY()-scrollValue*scrollCtr>footerYCoord-100)
				{
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ scrollValue +");", objElem);
				}
				
				if(scrollCtr==0)
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue;
				else
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue*scrollCtr;
				if (elemRelPos > footerYCoord) {
					scrollValue = footerYCoord-headerYCoord-100;
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ scrollValue +");", objElem);
					Thread.sleep(1500);
					scrollCtr++;
				}
				String fieldType = objElem.getAttribute("fieldtype");
				switch (fieldType) {
				case "INTEGER":
					try {
						clearAndSendKeys(objElem, String.valueOf(rnd.nextInt(32)));
						//objElem.sendKeys(String.valueOf(rnd.nextInt(32)));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DATE":
					try {
						if (!objElem.getAttribute("class").contains("edited")) {
							objElem.click();
							randomYr = contractYr;
							randomMnth = contractMnth;
							randomDay = contractDay;
							if (randomMnth == 1 || randomMnth == 3 || randomMnth == 5 || randomMnth == 7
									|| randomMnth == 8 || randomMnth == 10 || randomMnth == 12)
								randomDay = rnd.nextInt(32);
							else if (randomMnth == 4 || randomMnth == 6 || randomMnth == 9 || randomMnth == 11)
								randomDay = rnd.nextInt(31);
							else
								randomDay = rnd.nextInt(29);

							String randomDt = String.valueOf(randomDay) + "/" + String.valueOf(randomMnth) + "/"
									+ String.valueOf(randomYr);
							selectDate_v1(randomDt);
							this.setContractYr(randomYr + rnd.nextInt(2));
							if (contractYr > randomYr)
								this.setContractMnth(1 + rnd.nextInt(randomMnth));
							else
								this.setContractMnth(randomMnth + 1 + rnd.nextInt(12 - randomMnth));
							this.setContractDay(1 + rnd.nextInt(randomDay));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DECIMAL":
					try {
						clearAndSendKeys(objElem, String.valueOf(rnd.nextInt(10) + rnd.nextFloat()));
						objElem.clear();
						objElem.sendKeys(String.valueOf(rnd.nextInt(10) + rnd.nextFloat()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DROPDOWN":
					try {
						System.out.println(elemFinderXpath);
						//if (!objElem.getAttribute("class").contains("edited")) {
						if (driver.findElement(By.id("select2-"+objElem.getAttribute("id")+"-container")).getText().equals("")) {
							elemID = objElem.getAttribute("id");
							if(elemID.equals("SupportSLA")){
								System.out.println("i am in SLA thing");
								Thread.sleep(2000);
							}
							if(elemID.equals("rebate")){
								System.out.println("i am in rebate thing");
								Thread.sleep(2000);
							}
							int optionsCount = driver
									.findElements(By.xpath(elemFinderXpath + "[@id='" + elemID + "']/option")).size();
							System.out.println("No of options for "+ elemID+" is "+optionsCount);
							//WebElement elem = findElement(By.id(elemID));
							Select objSelect = new Select(elem);
							objSelect.selectByIndex(1 + rnd.nextInt(optionsCount - 1));
							System.out.println("contains edited : "+driver.findElement(By.id("select2-"+elemID+"-container")).getText());
							//if(driver.findElement(By.id("select2-"+elemID+"-container")).getText()!="")
								//((JavascriptExecutor)driver).executeScript("arguments[0].class='customSelect inputMaterial required select2-hidden-accessible edited'", objElem);
							
							
							findElement(By.xpath("//select[@id='" + elemID+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();
							String temp = elemFinderXpath + "[@id='" + elemID + "']/option["
									+ String.valueOf(2 + rnd.nextInt(optionsCount - 1)) + "]";
							//findElement(By.xpath(temp)).click();
							findElement(By.xpath("//select[@id='" + elemID
									+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();
							objElem.findElement(By.xpath("./option["
									+ String.valueOf(2 + rnd.nextInt(optionsCount - 1)) + "]")).click();
							driver.findElement(By.xpath("//div[@class='leftMenuDiv']")).click();
							System.out.println("contains edited : "+driver.findElement(By.id("select2-"+elemID+"-container")).getText());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "TEXTAREA":
				case "STRING":
					try {
						objElem.clear();
						objElem.sendKeys("Auto_" + RandomStringUtils.random(6, true, false));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			this.setElemFinderXpath(elemFinderXpath + "[not(contains(@class,'edited'))]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Deprecated
	private void enterFormDetails_withoutchanges(String elemFinderXpath) {
		String elemID = null;
		int headerYCoord = 0;
		int footerYCoord = 0;
		int randomYr = 0;
		int randomMnth = 0;
		int randomDay = 0;
		try {
			
			
			Point objHeaderLoc = driver.findElement(By.xpath("//div[@class='zys-default-tab']")).getLocation();
			headerYCoord = objHeaderLoc.getY();
			Point objFooterLoc = driver.findElement(By.id("addRepoContractFooter")).getLocation();
			footerYCoord = objFooterLoc.getY();
			List<WebElement> objAllElements = driver.findElements(By.xpath(elemFinderXpath));
			int elemRelPos = 0;
			System.out.println("no of fields to choose "+ objAllElements.size());
			
			
			scrollValue = footerYCoord-headerYCoord-100;
			String temp = elemFinderXpath + "[@id='" + elemID + "']";
			System.out.println(temp);
			
			JavascriptExecutor js = (JavascriptExecutor)driver; 
			//Long elemRelativeY =(Long)js.executeScript("var element = document.evaluate(\"//input[@class='"+scrollValue+"']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;return element.getBoundingClientRect().y;");
			Long elemRelativeY = (Long) js.executeScript("var element = document.evaluate(\""+elemFinderXpath+"\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;return element.getBoundingClientRect().y;");
			
			//System.out.println(elemRelativeY);
			System.out.println(elemRelativeY);
			
			for (WebElement objElem : objAllElements) {
				if(objElem.getLocation().getY()-scrollValue*scrollCtr>footerYCoord-100)
				{
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ scrollValue +");", objElem);
				}
				
				/*if(scrollCtr==0)
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue;
				else
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue*scrollCtr;
				if (elemRelPos > footerYCoord) {
					scrollValue = footerYCoord-headerYCoord-100;
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ scrollValue +");", objElem);
					Thread.sleep(1500);
					scrollCtr++;
				}*/
				String fieldType = objElem.getAttribute("fieldtype");
				switch (fieldType) {
				case "INTEGER":
					try {
						clearAndSendKeys(objElem, String.valueOf(rnd.nextInt(32)));
						//objElem.sendKeys(String.valueOf(rnd.nextInt(32)));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DATE":
					try {
						if (!objElem.getAttribute("class").contains("edited")) {
							objElem.click();
							randomYr = contractYr;
							randomMnth = contractMnth;
							randomDay = contractDay;
							if (randomMnth == 1 || randomMnth == 3 || randomMnth == 5 || randomMnth == 7
									|| randomMnth == 8 || randomMnth == 10 || randomMnth == 12)
								randomDay = rnd.nextInt(32);
							else if (randomMnth == 4 || randomMnth == 6 || randomMnth == 9 || randomMnth == 11)
								randomDay = rnd.nextInt(31);
							else
								randomDay = rnd.nextInt(29);

							String randomDt = String.valueOf(randomDay) + "/" + String.valueOf(randomMnth) + "/"
									+ String.valueOf(randomYr);
							selectDate_v1(randomDt);
							this.setContractYr(randomYr + rnd.nextInt(2));
							if (contractYr > randomYr)
								this.setContractMnth(1 + rnd.nextInt(randomMnth));
							else
								this.setContractMnth(randomMnth + 1 + rnd.nextInt(12 - randomMnth));
							this.setContractDay(1 + rnd.nextInt(randomDay));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DECIMAL":
					try {
						clearAndSendKeys(objElem, String.valueOf(rnd.nextInt(10) + rnd.nextFloat()));
						/*objElem.clear();
						objElem.sendKeys(String.valueOf(rnd.nextInt(10) + rnd.nextFloat()));*/
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "DROPDOWN":
					try {
						System.out.println(elemFinderXpath);
						//if (!objElem.getAttribute("class").contains("edited")) {
						if (driver.findElement(By.id("select2-"+objElem.getAttribute("id")+"-container")).getText().equals("")) {
							elemID = objElem.getAttribute("id");
							if(elemID.equals("SupportSLA")){
								System.out.println("i am in SLA thing");
								Thread.sleep(2000);
							}
							if(elemID.equals("rebate")){
								System.out.println("i am in rebate thing");
								Thread.sleep(2000);
							}
							int optionsCount = driver
									.findElements(By.xpath(elemFinderXpath + "[@id='" + elemID + "']/option")).size();
							System.out.println("No of options for "+ elemID+" is "+optionsCount);
							//WebElement elem = findElement(By.id(elemID));
							/*Select objSelect = new Select(elem);
							objSelect.selectByIndex(1 + rnd.nextInt(optionsCount - 1));
							System.out.println("contains edited : "+driver.findElement(By.id("select2-"+elemID+"-container")).getText());*/
							//if(driver.findElement(By.id("select2-"+elemID+"-container")).getText()!="")
								//((JavascriptExecutor)driver).executeScript("arguments[0].class='customSelect inputMaterial required select2-hidden-accessible edited'", objElem);
							
							
							findElement(By.xpath("//select[@id='" + elemID+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();
							/*String temp = elemFinderXpath + "[@id='" + elemID + "']/option["
									+ String.valueOf(2 + rnd.nextInt(optionsCount - 1)) + "]";*/
							//findElement(By.xpath(temp)).click();
							/*findElement(By.xpath("//select[@id='" + elemID
									+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();*/
							objElem.findElement(By.xpath("./option["
									+ String.valueOf(2 + rnd.nextInt(optionsCount - 1)) + "]")).click();
							driver.findElement(By.xpath("//div[@class='leftMenuDiv']")).click();
							System.out.println("contains edited : "+driver.findElement(By.id("select2-"+elemID+"-container")).getText());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "TEXTAREA":
				case "STRING":
					try {
						objElem.clear();
						objElem.sendKeys("Auto_" + RandomStringUtils.random(6, true, false));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			this.setElemFinderXpath(elemFinderXpath + "[not(contains(@class,'edited'))]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterFormDetails(String elemFinderXpath) {
		Long scrollPos = (long) 0;
		String elemID = null;
		int headerYCoord = 0;
		int firstElemYCoord = 0;
		int footerYCoord = 0;
		try {
			
			
			/*Point objHeaderLoc = driver.findElement(By.xpath("//div[@class='zys-default-tab']")).getLocation();
			headerYCoord = objHeaderLoc.getY();*/
			Point objFooterLoc = driver.findElement(By.id("addRepoContractFooter")).getLocation();
			footerYCoord = objFooterLoc.getY();
			List<WebElement> objAllElements = driver.findElements(By.xpath(elemFinderXpath));
			/*int elemRelPos = 0;
			System.out.println("no of fields to choose "+ objAllElements.size());*/
			
			
			Point firstElemLoc = driver.findElement(By.xpath("(//div[@class='formTitle'])[1]")).getLocation();
			firstElemYCoord = firstElemLoc.getY();		
					
			/*scrollValue = footerYCoord-headerYCoord-100;
			String temp = elemFinderXpath + "[@id='" + elemID + "']";
			System.out.println(temp);*/
			
			JavascriptExecutor js = (JavascriptExecutor)driver; 
			//Long elemRelativeY =(Long)js.executeScript("var element = document.evaluate(\"//input[@class='"+scrollValue+"']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;return element.getBoundingClientRect().y;");
			/*Long elemRelativeY = (Long) js.executeScript("var element = document.evaluate(\""+elemFinderXpath+"\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;return element.getBoundingClientRect().y;");
			System.out.println(elemRelativeY);*/
			
			for (WebElement objElem : objAllElements) {
				scrollPos = (Long) js.executeScript("return window.scrollY;");
				if(objElem.getLocation().getY()-scrollPos+100>footerYCoord)
				//if(objElem.getLocation().getY()-scrollValue*scrollCtr>footerYCoord-100)
				{
					scrollValue = (int) (objElem.getLocation().getY()-scrollPos-firstElemYCoord);
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ scrollValue +");", objElem);
				}
				
				/*if(scrollCtr==0)
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue;
				else
					elemRelPos = objElem.getLocation().getY() + 100-scrollValue*scrollCtr;
				if (elemRelPos > footerYCoord) {
					scrollValue = footerYCoord-headerYCoord-100;
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ scrollValue +");", objElem);
					Thread.sleep(1500);
					scrollCtr++;
				}*/
				String fieldType = objElem.getAttribute("fieldtype");
				switch (fieldType) {
				case "INTEGER":
					enterInteger(objElem);
					break;
				case "DATE":
					enterDate(objElem);
					break;
				case "DECIMAL":
					enterDecimal(objElem);
					break;
				case "DROPDOWN":
					selectDropdown(objElem);
					break;
				case "TEXTAREA":
				case "STRING":
					enterString(objElem);
					break;
				}
			}
			this.setElemFinderXpath(elemFinderXpath + "[not(contains(@class,'edited'))]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterInteger(WebElement obj){
		try {
			clearAndSendKeys(obj, String.valueOf(rnd.nextInt(32)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterDate(WebElement obj){
		int randomYr = 0;
		int randomMnth = 0;
		int randomDay = 0;
		try {
			if (!obj.getAttribute("class").contains("edited")) {
				obj.click();
				randomYr = contractYr;
				randomMnth = contractMnth;
				randomDay = contractDay;
				if (randomMnth == 1 || randomMnth == 3 || randomMnth == 5 || randomMnth == 7
						|| randomMnth == 8 || randomMnth == 10 || randomMnth == 12)
					randomDay = 1+rnd.nextInt(32);
				else if (randomMnth == 4 || randomMnth == 6 || randomMnth == 9 || randomMnth == 11)
					randomDay = 1+rnd.nextInt(31);
				else
					randomDay = 1+rnd.nextInt(29);

				String randomDt = String.valueOf(randomDay) + "/" + String.valueOf(randomMnth) + "/"
						+ String.valueOf(randomYr);
				System.out.println(randomDt);
				selectDate_v1(randomDt);
				int contYr = randomYr + rnd.nextInt(2);
				System.out.println(contYr);
				this.setContractYr(contYr);
				if (contractYr > randomYr){
					int contMnth = 1 + rnd.nextInt(randomMnth);
					System.out.println(contMnth);
					this.setContractMnth(contMnth);
				}
				else{
					int contMnth1 = randomMnth + 1 + rnd.nextInt(12 - randomMnth);
					System.out.println(contMnth1);
					this.setContractMnth(contMnth1);
				}
				System.out.println("randomDay : "+randomDay);
				int contrTDay = 1 + rnd.nextInt(randomDay);
				System.out.println(contrTDay);
				this.setContractDay(contrTDay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterDecimal(WebElement obj){
		try {
			clearAndSendKeys(obj, String.valueOf(rnd.nextInt(10) + rnd.nextFloat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectDropdown(WebElement obj){
		String elemID = null;
		try {
			if (driver.findElement(By.id("select2-"+obj.getAttribute("id")+"-container")).getText().equals("")) {
				elemID = obj.getAttribute("id");
				if(elemID.equals("SupportSLA")){
					Thread.sleep(2000);
				}
				if(elemID.equals("rebate")){
					Thread.sleep(2000);
				}
				int optionsCount = driver
						.findElements(By.xpath(elemFinderXpath + "[@id='" + elemID + "']/option")).size();
				findElement(By.xpath("//select[@id='" + elemID+ "']/following-sibling::span//span[contains(@class,'arrow')]")).click();
				obj.findElement(By.xpath("./option["
						+ String.valueOf(2 + rnd.nextInt(optionsCount - 1)) + "]")).click();
				/*if(driver.findElement(By.xpath("//div[@class='blockUI blockOverlay']")).isDisplayed()){
					System.out.println("yes displayed");
					Thread.sleep(1000);
				}*/
				/*waitUntilInvisibilityOfElement(By.xpath("//div[@class='blockUI blockOverlay']"));
				System.out.println("now clicking");*/
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[@class='leftMenuDiv']")).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterString(WebElement obj){
		try {
			obj.clear();
			obj.sendKeys("Auto_" + RandomStringUtils.random(6, true, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private boolean enterFlexiFormDetails_old() {
		boolean result = false;
		Random rnd = new Random();
		WebElement objElem = null;
		String fieldType = null;
		int optionsCount = 0;
		int numOfChoices = 0;
		List<WebElement> objChoices;
		int elemRelPos = 0;
		int randomYr = 0;
		int randomMnth = 0;
		int randomDay = 0;
		try {
			scrollCtr = 0;
			scrollValue = 0;
			Point objHeaderLoc = driver.findElement(By.xpath("//div[@class='zys-default-tab']")).getLocation();
			int headeryCoord = objHeaderLoc.getY();
			Point objFooterLoc = driver.findElement(By.id("addRepoContractFooter")).getLocation();
			int yCoord = objFooterLoc.getY();
			String elemFinderXpath1 = "//div[@id='flexiFormContainer' and not (contains(@style,'none'))]//form[@id='zydf-dynamicForm']//div[contains(@class,'zydf-form-val zydf-item-inline zydf-clearfix') or contains(@class,'zydf-form-val zydf-item-inline df-clearfix')]";
			List<WebElement> objAllElements = driver.findElements(By.xpath(elemFinderXpath1));
			waitUntilInvisibilityOfElement(By.id("jqifade"));
			for (WebElement objElemContainer : objAllElements) {
				findElement(By.xpath("(//h2)[1]")).click();
				if(scrollCtr==0)
					elemRelPos = objElemContainer.getLocation().getY() + 100-scrollValue;
				else
					elemRelPos = objElemContainer.getLocation().getY() + 100-scrollValue*scrollCtr;
				if (elemRelPos > yCoord) {
					scrollValue = yCoord-headeryCoord-100;
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+scrollValue+");", objElem);
					scrollCtr++;
				}
				
				List<WebElement> objList = objElemContainer.findElements(By.xpath(".//*[@data-field-type]"));
				if (objList.size() > 0) {
					objElem = objList.get(0);
					fieldType = objElem.getAttribute("data-field-type");
					switch (fieldType) {
					case "NUMERIC":
						try {
							objElem.clear();
							objElem.sendKeys(String.valueOf(rnd.nextInt(32)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "TEXT":
					case "TEXT_AREA":
						try {
							objElem.clear();
							objElem.sendKeys("Auto_" + RandomStringUtils.random(6, true, false));
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "DATE_TIME":
						try {
							objElem.click();
							randomYr = contractYr;
							randomMnth = contractMnth;
							randomDay = contractDay;
							if (randomMnth == 1 || randomMnth == 3 || randomMnth == 5 || randomMnth == 7
									|| randomMnth == 8 || randomMnth == 10 || randomMnth == 12)
								randomDay = rnd.nextInt(32);
							else if (randomMnth == 4 || randomMnth == 6 || randomMnth == 9 || randomMnth == 11)
								randomDay = rnd.nextInt(31);
							else
								randomDay = rnd.nextInt(29);

							String randomDt = String.valueOf(randomDay) + "/" + String.valueOf(randomMnth) + "/"
									+ String.valueOf(randomYr);
							selectDate_v1(randomDt);
							this.setContractYr(randomYr + rnd.nextInt(2));
							int a = 1;
							int b = 1;
							if (contractYr > randomYr){
								a = 1 + rnd.nextInt(randomMnth);
								System.out.println("Month to choose is "+a);
								this.setContractMnth(a);
							}else{
								b = randomMnth + 1 + rnd.nextInt(12 - randomMnth);
								System.out.println("Month1 to choose is "+b);
								this.setContractMnth(b);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "AUTO_COMPLETE":
						try {
							objElem.click();
							//((JavascriptExecutor) driver).executeScript("arguments[0].click()", objElem);
							waitUntilVisibilityOfElement(By.xpath("//ul[contains(@style,'block')]"));
							optionsCount = driver.findElements(By.xpath("//ul[contains(@style,'block')]/li")).size();
							findElement(By.xpath("//ul[contains(@style,'block')]/li["
									+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/a")).click();
							String temp = "//ul[contains(@style,'block')]/li["
									+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/div";
							System.out.println("dropdown to choose is "+temp);
							findElement(By.xpath(temp)).click();
							//((JavascriptExecutor) driver).executeScript("arguments[0].click()", findElement(By.xpath(temp)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "MULTI_SELECT":
						try {
							objElem.click();
							optionsCount = driver.findElements(By.xpath("//ul[contains(@style,'block')]/li")).size();
							String temp = "//ul[contains(@style,'block')]/li["
									+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/a";
							findElement(By.xpath(temp)).click();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "DROP_DOWN":
						try {
							objElem.click();
							//((JavascriptExecutor) driver).executeScript("arguments[0].click()", objElem);
							waitUntilVisibilityOfElement(By.xpath("//ul[contains(@style,'block')]"));
							optionsCount = driver.findElements(By.xpath("//ul[contains(@style,'block')]/li")).size();
							findElement(By.xpath("//ul[contains(@style,'block')]/li["
									+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/a")).click();
							String temp = "//ul[contains(@style,'block')]/li["
									+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/a";
							System.out.println("dropdown to choose is "+temp);
							findElement(By.xpath(temp)).click();
							//((JavascriptExecutor) driver).executeScript("arguments[0].click()", findElement(By.xpath(temp)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "RADIO":
					case "CHECKBOX":
						try {
							objChoices = objElemContainer.findElements(By.xpath("./label/input"));
							if (objChoices.size() > 0)
								objChoices.get(rnd.nextInt(objChoices.size())).click();
								//((JavascriptExecutor) driver).executeScript("arguments[0].click()", objChoices.get(rnd.nextInt(objChoices.size())));
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				} else {
					try {
						if (objElemContainer.findElements(By.xpath("./a[text()='Add Attachments']")).size() == 1) {
							objElemContainer.findElement(By.xpath("./a[text()='Add Attachments']")).click();
							waitUntilVisibilityOfElement(By
									.xpath("//div[contains(@aria-describedby,'zydf-dfaddAttachmentsDOM_df_flUpld')]"));
							driver.switchTo().frame(findElement(By.id("zydf-iframeAddAttachment")));
							ConfigurationProperties config = ConfigurationProperties.getInstance();
							driver.findElement(By.id("attachmentInput_eformAddAttachment"))
									.sendKeys(System.getProperty("user.dir") + config.getProperty("upload_file_path"));
							WebDriverWait wait = new WebDriverWait(driver, 120);
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//table//tr[2]//td[@class='status' and text()='Uploaded']")));
							driver.switchTo().defaultContent();
							findElement(By.xpath("//input[@value='Done']")).click();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	private void clearAndSendKeys(WebElement obj, String txtToEnter){
		obj.clear();
		obj.sendKeys(txtToEnter);
	}
	
	private boolean enterFlexiFormDetails() {
		boolean result = false;
		WebElement objElem = null;
		String fieldType = null;
		int optionsCount = 0;
		int numOfChoices = 0;
		List<WebElement> objChoices;
		int headerYCoord = 0;
		int footerYCoord = 0;
		int elemRelPos = 0;
		int randomYr = 0;
		int randomMnth = 0;
		int randomDay = 0;
		try {
			scrollCtr = 0;
			scrollValue = 0;
			Point objHeaderLoc = driver.findElement(By.xpath("//div[@class='zys-default-tab']")).getLocation();
			headerYCoord = objHeaderLoc.getY();
			Point objFooterLoc = driver.findElement(By.id("addRepoContractFooter")).getLocation();
			footerYCoord = objFooterLoc.getY();
			String elemFinderXpath1 = "//div[@id='flexiFormContainer' and not (contains(@style,'none'))]//form[@id='zydf-dynamicForm']//div[contains(@class,'zydf-form-val zydf-item-inline zydf-clearfix') or contains(@class,'zydf-form-val zydf-item-inline df-clearfix')]";
			List<WebElement> objAllElements = driver.findElements(By.xpath(elemFinderXpath1));
			waitUntilInvisibilityOfElement(By.id("jqifade"));
			for (WebElement objElemContainer : objAllElements) {
				findElement(By.xpath("(//h2)[1]")).click();
				if(scrollCtr==0)
					elemRelPos = objElemContainer.getLocation().getY() + 100-scrollValue;
				else
					elemRelPos = objElemContainer.getLocation().getY() + 100-scrollValue*scrollCtr;
				if (elemRelPos > footerYCoord) {
					scrollValue = footerYCoord-headerYCoord-100;
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+scrollValue+");", objElem);
					scrollCtr++;
				}
				
				List<WebElement> objList = objElemContainer.findElements(By.xpath(".//*[@data-field-type]"));
				if (objList.size() > 0) {
					objElem = objList.get(0);
					fieldType = objElem.getAttribute("data-field-type");
					switch (fieldType) {
					case "NUMERIC":
						enterNumber_flexi(objElem);
						break;
					case "TEXT":
					case "TEXT_AREA":
						enterText_flexi(objElem);
						break;
					case "DATE_TIME":
						enterDateTime_flexi(objElem);
						break;
					case "AUTO_COMPLETE":
						enterAutoComplete_flexi(objElem);
						break;
					case "MULTI_SELECT":
					case "DROP_DOWN":
						selectDropdown_flexi(objElem);
						break;
					case "RADIO":
					case "CHECKBOX":
						selectCheckbox_flexi(objElemContainer);
						break;
					default:
						break;
					}
				} else {
					try {
						if (objElemContainer.findElements(By.xpath("./a[text()='Add Attachments']")).size() == 1) {
							objElemContainer.findElement(By.xpath("./a[text()='Add Attachments']")).click();
							waitUntilVisibilityOfElement(By
									.xpath("//div[contains(@aria-describedby,'zydf-dfaddAttachmentsDOM_df_flUpld')]"));
							driver.switchTo().frame(findElement(By.id("zydf-iframeAddAttachment")));
							ConfigurationProperties config = ConfigurationProperties.getInstance();
							driver.findElement(By.id("attachmentInput_eformAddAttachment"))
									.sendKeys(System.getProperty("user.dir") + config.getProperty("upload_file_path"));
							WebDriverWait wait = new WebDriverWait(driver, 120);
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//table//tr[2]//td[@class='status' and text()='Uploaded']")));
							driver.switchTo().defaultContent();
							findElement(By.xpath("//input[@value='Done']")).click();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private void enterNumber_flexi(WebElement obj){
		try {
			clearAndSendKeys(obj, String.valueOf(rnd.nextInt(32)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterText_flexi(WebElement obj){
		try {
			clearAndSendKeys(obj, "Auto_" + RandomStringUtils.random(6, true, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterDateTime_flexi(WebElement obj){
		int randomYr = 0;
		int randomMnth = 0;
		int randomDay = 0;
		try {
			obj.click();
			randomYr = contractYr;
			randomMnth = contractMnth;
			randomDay = contractDay;
			if (randomMnth == 1 || randomMnth == 3 || randomMnth == 5 || randomMnth == 7
					|| randomMnth == 8 || randomMnth == 10 || randomMnth == 12)
				randomDay = rnd.nextInt(32);
			else if (randomMnth == 4 || randomMnth == 6 || randomMnth == 9 || randomMnth == 11)
				randomDay = rnd.nextInt(31);
			else
				randomDay = rnd.nextInt(29);

			String randomDt = String.valueOf(randomDay) + "/" + String.valueOf(randomMnth) + "/"
					+ String.valueOf(randomYr);
			selectDate_v1(randomDt);
			this.setContractYr(randomYr + rnd.nextInt(2));
			int a = 1;
			int b = 1;
			if (contractYr > randomYr){
				a = 1 + rnd.nextInt(randomMnth);
				this.setContractMnth(a);
			}else{
				b = randomMnth + 1 + rnd.nextInt(12 - randomMnth);
				this.setContractMnth(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enterAutoComplete_flexi(WebElement obj){
		int optionsCount = 0;
		try {
			obj.click();
			waitUntilVisibilityOfElement(By.xpath("//ul[contains(@style,'block')]"));
			optionsCount = driver.findElements(By.xpath("//ul[contains(@style,'block')]/li")).size();
			String temp = "//ul[contains(@style,'block')]/li["
					+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/div";
			findElement(By.xpath(temp)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectDropdown_flexi(WebElement obj){
		int optionsCount = 0;
		try {
			obj.click();
			waitUntilVisibilityOfElement(By.xpath("//ul[contains(@style,'block')]"));
			optionsCount = driver.findElements(By.xpath("//ul[contains(@style,'block')]/li")).size();
			String temp = "//ul[contains(@style,'block')]/li["
					+ String.valueOf(1 + rnd.nextInt(optionsCount)) + "]/a";
			findElement(By.xpath(temp)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectCheckbox_flexi(WebElement obj){
		List<WebElement> objChoices;
		try {
			objChoices = obj.findElements(By.xpath("./label/input"));
			if (objChoices.size() > 0)
				objChoices.get(rnd.nextInt(objChoices.size())).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
