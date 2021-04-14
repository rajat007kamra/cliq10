package actions.dateandclarification;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.dateandclarification.model.DateAndClarificationModel;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class DateAndClarification  extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private DateAndClarificationModel dateAndClarificationModel;
	private DateClarificationHelper dateHelper;
	final static Logger logger = Logger.getLogger(DateAndClarification.class);

	public DateAndClarification(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.dateHelper = new DateClarificationHelper(this.driver);
		this.dateAndClarificationModel = new Gson().fromJson(jsonString, DateAndClarificationModel.class);
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			dateHelper.clickDateAndClarifications(this.dateAndClarificationModel.getColumn());
			return true;
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			Assert.assertEquals(validateDateAndClarifications().trim(),
					(dateHelper.expectedFlyClarifierHeaderText.trim()));
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	/*
	 * Required to move into helper class. Right now its showing failure when
	 * executing this method through helper class
	 */

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-HDR-MAIN-BTM-BOR")
	private WebElement flyClarifierHeader;

	@Step("verify date and clarifications flyout")
	private String validateDateAndClarifications() {
		PageHelper.waitForElementToBePresent(this.driver, flyClarifierHeader);
		String flyClarifierHeaderText = PageHelper.getText(this.driver, flyClarifierHeader);
		logger.info("Flyout header is ::- " + flyClarifierHeaderText);
		return flyClarifierHeaderText;
	}
}