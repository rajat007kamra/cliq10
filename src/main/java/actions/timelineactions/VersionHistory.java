package actions.timelineactions;

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
import actions.timelineactions.model.VersionHistoryModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class VersionHistory extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private VersionHistoryModel versionHistoryModel;
	final static Logger logger = Logger.getLogger(VersionHistory.class);

	public VersionHistory(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.versionHistoryModel = new Gson().fromJson(jsonString, VersionHistoryModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-HSTRY[title*='VERSION HISTORY']")
	private WebElement versionHistory;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY-HDR")
	private WebElement verHistoryHeader;

	private String versionHistoryList = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.STND-PAD-BODY div.STND-PAD-DSPL";

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			clickVersionHistory();
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
			validateVersionHistory();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			if ((this.versionHistoryModel.getDisplayHistory().toUpperCase().trim()).equals("YES")) {
				displayVersionHistory();
			}
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

	@Step("Click version history icon in timeline")
	private void clickVersionHistory() {
		if (PageHelper.isElementEnabled(versionHistory)) {
			PageHelper.click(this.driver, versionHistory);
			logger.info("Click version history icon");
		} else {
			logger.error("Either version history icon is disabled or not found");
		}
	}

	@Step("Display list of all version history")
	private void displayVersionHistory() {
		List<WebElement> historyList = this.driver.findElements(By.cssSelector(versionHistoryList));
		logger.info("Version history is as follow :- ");
		for (WebElement list : historyList) {
			logger.info(list.getText());
		}
	}

	@Step("Validate version history")
	private void validateVersionHistory() {
		String header = PageHelper.getText(this.driver, verHistoryHeader);
		int sepPos = header.lastIndexOf(":");
		String updateHeader = header.substring(0, sepPos);
		Assert.assertEquals(updateHeader, "TIMELINE");
		logger.info("Version history list opened successfully");
	}
}
