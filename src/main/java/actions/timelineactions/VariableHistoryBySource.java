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
import actions.timelineactions.model.VariableHistoryBySourceModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class VariableHistoryBySource extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private VariableHistoryBySourceModel variableHistoryBySourceModel;
	final static Logger logger = Logger.getLogger(VariableHistoryBySource.class);

	public VariableHistoryBySource(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.variableHistoryBySourceModel = new Gson().fromJson(jsonString, VariableHistoryBySourceModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-SRC[title*='VARIABLE HISTORY BY SOURCE']")
	private WebElement variableHistorySource;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY-HDR")
	private WebElement varHistorySourceHeader;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			clickVariableHistory();
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

	@Step("Click variable history by source icon in timeline")
	private void clickVariableHistory() {
		if (PageHelper.isElementEnabled(variableHistorySource)) {
			PageHelper.click(this.driver, variableHistorySource);
			logger.info("Click variable history by source icon");
		} else {
			logger.error("Either variable history by source icon is disabled or not found");
		}
	}

	@Step("Validate variable history by source")
	private void validateVersionHistory() {
		String header = PageHelper.getText(this.driver, varHistorySourceHeader);
//		System.out.println(header);
		int sepPos = header.lastIndexOf(":");
		String updateHeader = header.substring(0, sepPos);
		Assert.assertEquals(updateHeader, "SOURCE HISTORY");
		logger.info("Variable history by source list opened successfully");
	}
}
