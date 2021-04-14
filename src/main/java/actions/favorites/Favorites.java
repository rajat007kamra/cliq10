package actions.favorites;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.favorites.model.FavoritesModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Favorites extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private FavoritesModel favoritesModel;
	static Logger logger = Logger.getLogger(Favorites.class);

	public Favorites(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.favoritesModel = new Gson().fromJson(jsonString, FavoritesModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-ITEM-TRG[title='ADD']")
	private WebElement addFavorites;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-ITEM input.ENTRYFLD-TXT")
	private WebElement inputFavorites;

	private String favoriteList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-ITEM-DSPLY";

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-ITEM-DSPLY")
	private WebElement favorList;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-HDR")
	private WebElement favElement;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			if (favoritesModel.getActionName().trim().toUpperCase().equals("ADD")) {
				PageHelper.click(this.driver, favElement);
				Thread.sleep(5000);
				logger.info("Existing list contains '" + this.favoritesModel.getFavoriteName().toUpperCase()
						+ "' favorite ::- " + countText(this.favoritesModel.getFavoriteName().toUpperCase()));
				clickPlusInFavorites();
				setValueInFavorites(this.favoritesModel.getFavoriteName().toUpperCase());
			} else if (favoritesModel.getActionName().trim().toUpperCase().equals("DELETE")) {
				// TO DO
			} else {

			}
			CommonVariables.actionTime = System.currentTimeMillis();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			endTime = Instant.now();
			if (favoritesModel.getActionName().trim().toUpperCase().equals("ADD")) {
				verifyAddedFavorites(this.favoritesModel.getFavoriteName().toUpperCase());
				logger.info("New list contains '" + this.favoritesModel.getFavoriteName().toUpperCase()
						+ "' favorite ::- " + countText(this.favoritesModel.getFavoriteName().toUpperCase()));
			} else if (favoritesModel.getActionName().trim().toUpperCase().equals("ADD")) {
				// TO DO
			} else {

			}
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	/**
	 * @param driver
	 * @return
	 */
	private boolean verifyPlusFavIcon(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(this.driver, addFavorites);
		String className = this.addFavorites.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either plus/add icon is disabled or not found");
			return false;
		} else {
			logger.info("Plus/Add icon is enabled");
			return true;
		}
	}

	@Step("Click plus in favorites")
	private void clickPlusInFavorites() {
		verifyPlusFavIcon(this.driver);
		PageHelper.click(this.driver, addFavorites);
		logger.info("Clicked on add favorites icon");
	}

	@Step("Set value in favorites")
	private void setValueInFavorites(String favText) {
		logger.info("verifying input field after plus/add click");
		PageHelper.waitForElementToBePresent(this.driver, inputFavorites);
		PageHelper.sendKeys(this.driver, inputFavorites, favText);
		logger.info("Set value in favorites : " + favText);
		PageHelper.sendKeys(driver, inputFavorites, Keys.ENTER, false);
	}

	// Method to verify the added favorite in list
	@Step("Verify added favorites")
	private void verifyAddedFavorites(String favText) {
		List<WebElement> favList = this.driver.findElements(By.cssSelector(favoriteList));
		for (int i = 0; i < favList.size(); i++) {
			String listText = favList.get(i).getText();
//			logger.info(listText);
			if (listText.contains(favText)) {
				logger.info(favText + " added successfully");
				break;
			} else {
				logger.error(favText + " not added");
			}
		}
	}

	/**
	 * Method counting number of same favorites names exists
	 * 
	 * @param favText
	 * @return
	 */
	private int countText(String favText) {
		int textCount = 0;
		int totalTextCount = 0;
		List<WebElement> favList = this.driver.findElements(By.cssSelector(favoriteList));
		for (int i = 0; i < favList.size(); i++) {
			String favListText = favList.get(i).getText();
			String updatedFavListText = favListText.replace(favListText.substring(favListText.indexOf('(')), "");
			if (updatedFavListText.trim().equals(favText)) {
				textCount++;
			}
			totalTextCount = textCount;
		}
		return totalTextCount;
	}
}
