package actions.timelineactions;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.timelineactions.model.AsOfVersionsModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class AsOfVersions extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private AsOfVersionsModel asOfVersionsModel;
	final static Logger logger = Logger.getLogger(AsOfVersions.class);

	public AsOfVersions(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.asOfVersionsModel = new Gson().fromJson(jsonString, AsOfVersionsModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-ASOF-TGL[title*='AS-OF VERSIONS']")
	private WebElement asOfVersion;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-ASOF-TGL[title*='HISTORY VERSIONS']")
	private WebElement historyVersion;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			clickAsOfVersion();
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
			if (historyVersion.isDisplayed()) {
				logger.info("As-of-version working fine");
				endTime = Instant.now();
				logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
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

	@Step("Click as-Of version icon in timeline")
	private void clickAsOfVersion() throws InterruptedException {
		if (PageHelper.isElementEnabled(asOfVersion)) {
			Thread.sleep(5000);
			PageHelper.click(this.driver, asOfVersion);
			logger.info("Click as-Of version icon");
		} else {
			logger.error("Either as-Of version icon is disabled or not found");
		}
	}
}