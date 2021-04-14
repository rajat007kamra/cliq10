package actions.globalsearch;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.globalsearch.model.GlobalSearchModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class GlobalSearch extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private GlobalSearchModel globalSearchModel;
	final static Logger logger = Logger.getLogger(GlobalSearch.class);

	public GlobalSearch(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		globalSearchModel = new Gson().fromJson(jsonString, GlobalSearchModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] input.SRCH-WGT-ENTRYFLD-top-cptn")
	private WebElement globalSearch;

	@FindBy(css = "div.suggestPopupMiddleCenterInner.suggestPopupContent")
	private WebElement suggestPopUpList;
	
	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			enterInSearchTextbox(this.globalSearchModel.getGlobalSearch().trim().toUpperCase());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			isSuggestListOpen(this.globalSearchModel.getGlobalSearch().trim().toUpperCase());
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			Thread.sleep(5000);
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

	@Step("Enter search text")
	private void enterInSearchTextbox(String searchValue) {
		PageHelper.sendKeys(this.driver, globalSearch, searchValue);
		logger.info("Enter " +searchValue+ " text in global search textbox");
	}
	
	@Step("Verify suggestion list")
	private void isSuggestListOpen(String searchValue) {
		logger.info("Check is suggestion list present");
		if (PageHelper.isElementDisplayed(suggestPopUpList)) {
			logger.info("Searched text exists in suggestion list");
			globalSearch.sendKeys(Keys.ENTER);
		} else {
			logger.error("No recent searches for " +searchValue);
			Assert.fail("No recent searches for " +searchValue);
		}
	}
}