package actions.closewidget;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.Actions;
import actions.PageHelper;
import selenium.driver.Driver;

public class CloseWidget extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(CloseWidget.class);

	private String closewidget = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.FLY-CLOSE-TRG[title='CLOSE']";
	private String closeTreeWidget = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TREE-WGT div.TREE-WGT-CTRLS-OUTER div.FLY-CLOSE-TRG.material-icons[title='CLOSE']";

	public CloseWidget(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			closewidget();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {
	}

	@Override
	public void tearDown() {
	}

	public void closewidget() {
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
		if (this.driver.findElements(By.cssSelector(closewidget)).size() > 0) {
			try {
			TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
			//PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			this.driver.findElement(By.cssSelector(closewidget)).click();
			logger.info("Close icon clicked on widget");
			}
			catch (Exception e) {
				logger.info("Exception "+ e);
			}
		} else if (this.driver.findElements(By.cssSelector(closeTreeWidget)).size() > 0) {
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			this.driver.findElement(By.cssSelector(closeTreeWidget)).click();
			logger.info("Close icon clicked on widget");
		} else {
			String errorMsg = "Close icon not found for open widget";
			logger.error(errorMsg);
			Assert.fail(errorMsg);
		}
	}
}