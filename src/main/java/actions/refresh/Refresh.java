package actions.refresh;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

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

public class Refresh extends RealmActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Refresh.class);

	public Refresh(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	private String yesButton = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-YES";
	private String closewidget = "div.FLY div.FLY-CLOSE-TRG[title='CLOSE']";

	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			checkAndClosewidget();
			startTime = Instant.now();			
			verifyRefresh(this.driver);
			clickRefresh();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Refresh not found", false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateRefresh();
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

	@Step("Click REFRESH icon")
	private void clickRefresh() {
		try {
		TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
		PageHelper.click(this.driver, refreshIcon);
		logger.info("Refresh icon clicked");}
		catch(Exception e) {
			logger.info("Exception in clicking Refresh" + e );	
		}
	}

	@Step("Validate REFRESH icon")
	private void validateRefresh() {
		if (this.driver.findElements(By.cssSelector(yesButton)).size() > 0) {
			logger.info("Refresh icon validated successfully");
		} else {
			Assert.fail("Either confirmation popup not opened or YES not found on confirmation popup");
		}
	}
	
	//Method to verify is any widget opened and if yes then close it
	public void checkAndClosewidget() {
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
		if (this.driver.findElements(By.cssSelector(closewidget)).size() > 0) {
			try {
			TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
			this.driver.findElement(By.cssSelector(closewidget)).click();
			logger.info("Close icon clicked on widget");
			}
			catch (Exception e) {
				logger.info("Exception "+ e);
			}
		}
	}
}