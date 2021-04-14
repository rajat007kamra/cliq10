package actions.timelineactions;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.Actions;
import actions.PageHelper;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class VariableHistory extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(VariableHistory.class);

	public VariableHistory(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT-TRG-LST[title*='VARIABLE HISTORY']")
	private WebElement variableHistory;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY-HDR")
	private WebElement varHistoryHeader;
	
	private String headerText = "VARIABLE HISTORY";

	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
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

	@Step("Click variable history icon in timeline")
	private void clickVariableHistory() {
		if (PageHelper.isElementEnabled(variableHistory)) {
			PageHelper.click(this.driver, variableHistory);
			logger.info("Variable history icon clicked");
		} else {
			logger.error("Either variable history icon is disabled or not found");
		}
	}

	@Step("Validate variable history")
	private void validateVersionHistory() {
		String header = PageHelper.getText(this.driver, varHistoryHeader);
//		System.out.println(header);
		int sepPos = header.lastIndexOf(":");
		String updateHeader = header.substring(0, sepPos);
		Assert.assertEquals(updateHeader, headerText);
		logger.info("Variable history list opened successfully");
	}
}
