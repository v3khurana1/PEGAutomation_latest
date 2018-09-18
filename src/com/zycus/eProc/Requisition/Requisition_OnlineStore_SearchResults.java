package com.zycus.eProc.Requisition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Functions.eProc_CommonFunctions;

/**
 * <p>
 * <b> Title: </b> Requisition_OnlineStore_SearchResults.java</br>
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

public class Requisition_OnlineStore_SearchResults extends eProc_CommonFunctions {
	private WebDriver driver;
	private ExtentTest logger;

	private By actionsList = By.id("cntToolbox");
	private By addToCartAction = By.id("lnkAddMultipleToCart");
	private By addToFavAction = By.id("lnkAddMultipleToFavorite");
	private By addToBasketAction = By.id("lnkAddMultipleToBasket");
	private By addToCompItemsAction = By.id("lnkCompareItems");
	private By itemsFound = By.id("totalItemsFound");
	private By processingLoader = By.id("tblSearchItemList_processing");
	private By no_catalog_items_found = By.xpath("//*[@id='tblSearchItemList']//tr[@class='odd']");
	private By search_results = By.xpath("//*[@id='tblSearchItemList']//tr[contains(@class,'dragToCart')]");
	private By selectedItemsLblXpath = By.xpath("//div[@id='cart']/div[1]/span/span");
	private By itemPriceLblXpath = By.xpath("//div[@id='cart']/div[1]/span[3]");
	private By disabledCheckoutBtn = By.xpath("//*[@id='cart']/div[1]/a");

	/**
	 * Constructor for the class
	 * 
	 * @param driver
	 */

	public Requisition_OnlineStore_SearchResults(WebDriver driver, ExtentTest logger) { 
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
	}

	/**
	 * @return the search_results
	 */
	public By getSearch_results() {
		return search_results;
	}

	/**
	 * @param search_results
	 *            the search_results to set
	 */
	public void setSearch_results(By search_results) {
		this.search_results = search_results;
	}

	/**
	 * @return the no_catalog_items_found
	 */
	public By getNo_catalog_items_found() {
		return no_catalog_items_found;
	}

	/**
	 * @param no_catalog_items_found
	 *            the no_catalog_items_found to set
	 */
	public void setNo_catalog_items_found(By no_catalog_items_found) {
		this.no_catalog_items_found = no_catalog_items_found;
	}

	/**
	 * <b>Function:</b> addItemToCart_ByActions -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param quantity
	 * @return result - True/False
	 */

	public boolean addItemToCart_ByActions(int quantity) {
		boolean result = false;
		try {
			WebElement selectedItemsLbl = findElement(selectedItemsLblXpath);
			int numOfItems = Integer.parseInt(selectedItemsLbl.getText());

			WebElement itemPriceLbl = findElement(itemPriceLblXpath);
			float price = Float.parseFloat((itemPriceLbl.getText().split(" ", 2))[1]);

			// Select items in sequence - as per quantity
			for (int i = 1; i <= quantity; i++)
				findElement(By.xpath("(//input[contains(@id,'chk_item_list')])[i]")).click();
			if (findElement(actionsList) != null)
				findElement(addToCartAction).click();

			if (findElement(By.xpath("//div[text()='" + quantity + " item(s) were added to cart']")) != null) {
				logger.log(LogStatus.INFO, quantity + " item(s) added to the cart message displayed");
				if (findElement(By
						.xpath("(//div[@class='item-incart itemInCart']/span/following-sibling::text())[1]")) != null) {
					logger.log(LogStatus.INFO, "..already in the cart message displayed under Add to Cart button");
					if (numOfItems <= Integer.parseInt(selectedItemsLbl.getText())) {
						logger.log(LogStatus.INFO, "selected item count increased");
						if (price < Float.parseFloat((itemPriceLbl.getText().split(" ", 2))[1])) {
							logger.log(LogStatus.INFO, "Cart price increased on adding the item");
							if (findElement(disabledCheckoutBtn) == null) {
								logger.log(LogStatus.INFO, "Checkout button gets enabled");
								result = true;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addItemToFav_ByActions -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param quantity
	 * @return result - True/False
	 */

	public boolean addItemToFav_ByActions(int quantity) {
		boolean result = false;
		try {
			// Select items in sequence - as per quantity
			for (int i = 1; i <= quantity; i++)
				findElement(By.xpath("(//input[contains(@id,'chk_item_list')])[i]")).click();
			if (findElement(actionsList) != null)
				findElement(addToFavAction).click();

			if (findElement(By.xpath("//div[text()='" + quantity + " item(s) were added to My Favorites']")) != null) {
				logger.log(LogStatus.INFO, quantity + " item(s) added to My Favorites message displayed");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addItemToBasket_ByActions -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param quantity
	 * @return result - True/False
	 */

	public boolean addItemToBasket_ByActions(int quantity) {
		boolean result = false;
		try {
			// Select items in sequence - as per quantity
			for (int i = 1; i <= quantity; i++)
				findElement(By.xpath("(//input[contains(@id,'chk_item_list')])[i]")).click();
			if (findElement(actionsList) != null)
				findElement(addToBasketAction).click();

			Requisition_OnlineStore_AddToBasket objAddToBasket = new Requisition_OnlineStore_AddToBasket(driver, logger);
			if (findElement(objAddToBasket.getAddToBasketPg()) != null)
				logger.log(LogStatus.INFO, "Add to Basket page displayed");
			else
				logger.log(LogStatus.INFO, "Add to Basket page not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> compareItems_ByActions -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param quantity
	 * @return result - True/False
	 */

	public boolean compareItems_ByActions(int quantity) {
		boolean result = false;
		try {
			// Select items in sequence - as per quantity
			for (int i = 1; i <= quantity; i++)
				findElement(By.xpath("(//input[contains(@id,'chk_item_list')])[i]")).click();
			if (findElement(actionsList) != null)
				findElement(addToCompItemsAction).click();
			if (quantity <= 1) {
				if (findElement(By.xpath("//div//td[text()='Please select at least 2 items for comparison']")) != null)
					logger.log(LogStatus.INFO, "Alert message to select at least 2 items displayed");
				else
					logger.log(LogStatus.INFO, "Alert message to select at least 2 items not displayed");
			} else if (quantity > 4) {
				if (findElement(By.xpath("//div//td[text()='You can select upto 4 items for Comparison']")) != null)
					logger.log(LogStatus.INFO, "Alert message to select upto 4 items displayed");
				else
					logger.log(LogStatus.INFO, "Alert message to select upto 4 items displayed");
			} else if (quantity > 1 && quantity <= 4) {
				Requisition_OnlineStore_ProductComparison objProdComp = new Requisition_OnlineStore_ProductComparison(
						driver, logger);
				if (findElement(objProdComp.getCompareItemsPg()) != null)
					logger.log(LogStatus.INFO, "Product Comparison page displayed");
				else
					logger.log(LogStatus.INFO, "Product Comparison page not displayed");
			}

			if (findElement(By.xpath("//div[text()='" + quantity + " item(s) were added to My Favorites']")) != null) {
				logger.log(LogStatus.INFO, quantity + " item(s) added to My Favorites message displayed");
				
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addItemToCart -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param quantity
	 * @return result - True/False
	 */

	public boolean addItemToCart(int quantity) {
		boolean result = false;
		try {
			WebElement selectedItemsLbl = findElement(selectedItemsLblXpath);
			int numOfItems = Integer.parseInt(selectedItemsLbl.getText());

			WebElement itemPriceLbl = findElement(itemPriceLblXpath);
			float price = Float.parseFloat((itemPriceLbl.getText().split(" ", 2))[1]);

			// Add to Cart for First Search Result
			Thread.sleep(2000);
			if (quantity > 0)
				sendKeys(By.xpath("(//*[contains(@id,'quantity_list')])[1]"), String.valueOf(quantity));
			findElement(By.xpath("(//*[contains(@id,'addToCart_list')])[1]")).click();

			// Validations
			// if(findElement(By.xpath("//em[text()='"+quantity+" EA item(s)
			// added
			// to the cart']")) != null){
			/*
			 * try{ WebDriverWait wait = new WebDriverWait(driver, 20);
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
			 * xpath( "//div[@id='updFlash']/em[text()='"+
			 * quantity+" EA item(s) added to the cart']"))); }catch(Throwable
			 * e){ System.err.
			 * println("Error while waiting for the notification to appear: "+
			 * e.getMessage()); }
			 * 
			 * 
			 * WebElement elem =
			 * findElement(By.xpath("//div[@id='updFlash']//em"));
			 * logger.log(LogStatus.INFO, elem.getText());
			 */
			/*
			 * if(waitFluent(By.xpath("//div[@id='updFlash']//em[text()='"+
			 * quantity+" EA item(s) added to the cart']"))!=null)
			 * logger.log(LogStatus.INFO, quantity
			 * +" EA item(s) added to the cart message displayed");
			 */
			if (waitFluent(By.xpath("(//div[@class='item-incart itemInCart'])[1]")) != null)
				logger.log(LogStatus.INFO, "..already in the cart message displayed under Add to Cart button");
			if (numOfItems + 1 <= Integer.parseInt(selectedItemsLbl.getText()))
				logger.log(LogStatus.INFO, "selected item count increased if different item chosen");
			if (price < Float.parseFloat((itemPriceLbl.getText().split(" ", 2))[1]))
				logger.log(LogStatus.INFO, "Cart price increased on adding the item");
			if (!waitFluent(disabledCheckoutBtn).getAttribute("class").contains("disableMe")) {
				logger.log(LogStatus.INFO, "Checkout button gets enabled");
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> addItemToFavorites -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - True/False
	 */

	public boolean addItemToFavorites() {
		boolean result = false;
		try {
			// Select first search result to add to favorites
			WebElement addToFav = findElement(By.xpath("(//label[contains(@id,'favorite_list')])[1]"));
			addToFav.click();
			if (findElement(By.xpath("//em[text()='Item added to favorites successfully']")) != null)
				if (addToFav.getText() == "Remove from Favorites") {
					logger.log(LogStatus.INFO, "Item added to favorites");
					result = true;
				} else
					logger.log(LogStatus.INFO, "Item not added to favorites");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> removeItemFromFavorites -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - True/False
	 */

	public boolean removeItemFromFavorites() {
		boolean result = false;
		try {
			// Select first search result to remove from favorites
			WebElement removeFrmFav = findElement(By.xpath("(//label[contains(@id,'favorite_list')])[1]"));
			removeFrmFav.click();
			findElement(By.xpath("//button[span[text()='Yes']]")).click();
			if (findElement(By.xpath("//em[text()='Item removed from favorites successfully']")) != null)
				if (removeFrmFav.getText() == "Remove from Favorites") {
					logger.log(LogStatus.INFO, "Item removed from favorites");
					result = true;
				} else
					logger.log(LogStatus.INFO, "Item not removed from favorites");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> dragItemToCart -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param None
	 * @return result - True/False
	 */

	public boolean dragItemToCart() {
		boolean result = false;
		try {
			WebElement draggable = findElement(By.xpath("//*[@id='tblSearchItemList']/tbody/tr[1]"));
			WebElement droppable = findElement(By.xpath("//*[@id='cartHover']//span[@class='bld']"));
			new Actions(driver).dragAndDrop(draggable, droppable).build().perform();
			if (findElement(By.xpath(
					"//*[@id='cartHover']//span[@class='cartHoverTxt']/text()[contains(.,'added to cart')]")) != null)
				logger.log(LogStatus.INFO, "1 EA added to cart message displayed");
			else
				logger.log(LogStatus.INFO, "1 EA added to cart message not displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>Function:</b> filterByChkboxType -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param chkBoxType
	 * @param typeValue
	 * @return result - True/False
	 */

	public boolean filterByChkboxType(String chkBoxType, String typeValue) {
		boolean result = false;
		try {
			WebElement objItemsDisplayed = findElement(itemsFound);
			int itemsDisplayed = Integer.parseInt(objItemsDisplayed.getText());

			if ((chkBoxType == "All") | (chkBoxType == "None"))
				findElement(By.xpath("//div[@class='inptBx inptBxMltChk'][label[text()='" + chkBoxType
						+ "']]/div/div[1]/a[text()='" + typeValue + "']")).click();
			else
				findElement(By.xpath("//div[@class='inptBx inptBxMltChk'][label[text()='" + chkBoxType
						+ "']]/div/div[2]/label[@title='" + typeValue + "']")).click();
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				if (Integer.parseInt(objItemsDisplayed.getText()) <= itemsDisplayed)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ---------------------------------------------------------------------------------
	 * Function : filterByTextField
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return result
	 *         ---------------------------------------------------------------------------------
	 *//*
		 * 
		 * public boolean filterByTextField1(String fieldName, String
		 * fieldValue){ boolean result = false;
		 * 
		 * WebElement objItemsDisplayed = findElement(itemsFound); int
		 * itemsDisplayed = Integer.parseInt(objItemsDisplayed.getText());
		 * 
		 * sendKeys(By.xpath("//div[@class='inptBx'][label[text()='"+fieldName+
		 * "']]/div/input"), fieldValue);
		 * if(findElement(processingLoader).getAttribute("style").contains(
		 * "block")){
		 * if(Integer.parseInt(objItemsDisplayed.getText())<=itemsDisplayed)
		 * result = true; } return result; }
		 */

	/**
	 * <b>Function:</b> filterByRange -
	 * 
	 * @author Varun Khurana
	 * @since April 2018
	 * @param rangeName
	 * @param minVal
	 * @return result - True/False
	 */

	public boolean filterByRange(String rangeName, String minVal) {
		boolean result = false;
		try {
			WebElement objItemsDisplayed = findElement(itemsFound);
			int itemsDisplayed = Integer.parseInt(objItemsDisplayed.getText());

			sendKeys(By.xpath("//div[@class='inptBx itmBxSlider'][label[text()='" + rangeName
					+ "']]//input[@class='inptTxt minVal']"), minVal);
			if (findElement(processingLoader).getAttribute("style").contains("block")) {
				if (Integer.parseInt(objItemsDisplayed.getText()) <= itemsDisplayed)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
