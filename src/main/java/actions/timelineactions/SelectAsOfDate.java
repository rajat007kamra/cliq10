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
import actions.timelineactions.model.SelectAsOfDateModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

public class SelectAsOfDate extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private SelectAsOfDateModel selectAsOfDateModel;
	final static Logger logger = Logger.getLogger(SelectAsOfDate.class);

	public SelectAsOfDate(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.selectAsOfDateModel = new Gson().fromJson(jsonString, SelectAsOfDateModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-ASOF-TGL[title*='AS-OF VERSIONS']")
	private WebElement asOfVersion;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-ASOF[title*='SELECT AS-OF DATE']")
	private WebElement selectAsOfDate;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.STND-PAD-HDR-MAIN[title*='CALENDAR']")
	private WebElement calendar;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			clickSelectAsOfDate();
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
			if (calendar.isDisplayed()) {
				logger.info("Calendar opened successfully");
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

	@Step("Click select as-of date icon in timeline")
	private boolean verifySelectAsOfDate() {
		String className = selectAsOfDate.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either select as-of date icon is disabled or not found");
			return false;
		} else {
			logger.info("Select as-of date icon is enabled");
			return true;
		}
	}

	@Step("Click select as-of date icon in timeline")
	private void clickSelectAsOfDate() throws InterruptedException {
		PageHelper.waitForElementToBeDisplayed(driver, selectAsOfDate);
		String className = this.selectAsOfDate.getAttribute("class");
		if (className.contains("DISABLED")) {
			Thread.sleep(5000);
			logger.error("Select as-Of date icon is disabled");
			PageHelper.click(this.driver, asOfVersion);
			logger.info("Click as-Of version icon to enable select as-of date");
			Thread.sleep(5000);
			verifySelectAsOfDate();
			PageHelper.click(this.driver, selectAsOfDate);
			logger.info("Click select as-of date");
		} else {
			Thread.sleep(5000);
			PageHelper.click(this.driver, selectAsOfDate);
			logger.info("Click select as-Of date icon");
		}
	}
}