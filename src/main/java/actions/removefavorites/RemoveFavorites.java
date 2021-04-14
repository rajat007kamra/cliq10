package actions.removefavorites;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.removefavorites.model.RemoveFavoritesModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class RemoveFavorites extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private RemoveFavoritesModel removeFavoritesModel;
	static Logger logger = Logger.getLogger(RemoveFavorites.class);

	public RemoveFavorites(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.removeFavoritesModel = new Gson().fromJson(jsonString, RemoveFavoritesModel.class);
	}

	private String favoriteList = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FAVRTS-WGT-ITEM";
	private String favoriteListText = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-ITEM-DSPLY";
	private String removeFavorite = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FAVRTS-WGT-ITEM-TRG[title='DELETE']";

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FAVRTS-WGT-HDR")
	private WebElement favoriteElement;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			PageHelper.click(this.driver, favoriteElement);
			countText();
			removeFavorites(this.removeFavoritesModel.getColumn());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			countText();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Reading favorites")
	private WebElement getFavoriteElement(String favoriteName) {
		List<WebElement> favorList = this.driver.findElements(By.cssSelector(favoriteList));
		WebElement favorElem = null;
		for (WebElement elm : favorList) {
			String favListText = PageHelper.getText(this.driver, elm);
			String updatedFavListText = favListText.replace(favListText.substring(favListText.indexOf('(')), "");
			if (updatedFavListText.trim().equals(favoriteName)) {
				favorElem = elm;
				break;
			}
		}
		return favorElem;
	}

	@Step("Removing favorites")
	private void removeFavorite(String favoriteName) throws Exception {
		WebElement favorElem = getFavoriteElement(favoriteName);
		if (favorElem != null) {
			Thread.sleep(3000);
			WebElement closeElem = favorElem.findElement(By.cssSelector(removeFavorite));
			PageHelper.click(this.driver, closeElem);
			if (getFavoriteElement(favoriteName) == null) {
				logger.info(favoriteName + " Favorite is not exists in active favorites list now");
			}
		} else {
			logger.error(favoriteName + " Favorite name does not exists");
		}
	}

	// Method calling removeFavorite
	private void removeFavorites(List<String> favorList) throws Exception {
		for (String col : favorList) {
			removeFavorite(col);
		}
	}

	// Method counting number of same favorites names exists
	public void countText() {
		List<WebElement> favList = this.driver.findElements(By.cssSelector(favoriteListText));
		logger.info("Total favorites in existing list ::- " + favList.size());
	}
}
