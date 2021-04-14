package actions.discard;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.PageHelper;
import actions.WorkbarActions;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Discard extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Discard.class);

	public Discard(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	
	private String yesButton = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-YES";
	
	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			verifyDiscard(this.driver);
			clickDiscard();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateDiscard();
			endTime = Instant.now();
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

	@Step("Click DISCARD icon")
	private void clickDiscard() {
		PageHelper.click(this.driver, discardIcon);
		logger.info("Discard icon clicked");
	}
	@Step("Validate DISCARD icon")
	private void validateDiscard() {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		if(this.driver.findElements(By.cssSelector(yesButton)).size()>0) {
			logger.info("Discard icon validated");
		}
		else {
			Assert.fail("Either confirmation pop up not opened or yesbutton not found");
		}
	}
}