package actions.reset;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actions.PageHelper;
import actions.RealmActions;
import io.qameta.allure.Step;
import junit.framework.Assert;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Reset extends RealmActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Reset.class);

	public Reset(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	private String yesButton = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-YES";

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			verifyReset(this.driver);
			clickReset();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Reset not found", false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateReset();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click RESET icon")
	private void clickReset() {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		PageHelper.click(this.driver, resetIcon);
		logger.info("Reset icon clicked");
	}

	@Step("Validate RESET icon")
	private void validateReset() {
		if (this.driver.findElements(By.cssSelector(yesButton)).size() > 0) {
			logger.info("Reset icon validated successfully");
		} else {
			Assert.fail("Either confirmation popup not opened or YES not found on confirmation popup");
		}
	}
}