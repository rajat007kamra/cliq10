package actions.clearfilter;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class ClearFilter extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(ClearFilter.class);

	public ClearFilter(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	private String gridFilter = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT[style*='z-index: 1;'] div.GRID-WGD-HDR-CTRL[title*='FILTER']";

	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			startTime = Instant.now();
			clickClearFilter();
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
			validateClearFilter();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		}
		catch (Exception e) {
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

	@Step("Click CLEAR FILTER icon")
	private void clickClearFilter() {
		WebElement filter = this.driver.findElement(By.cssSelector(gridFilter));
		String text = filter.getAttribute("class");
		if (text.contains("ACTIVE")) {
			PageHelper.click(this.driver, filter);
			logger.info("Clear filter icon is active and clicked");
		} else {
			logger.info("Filter is already inactive");
		}
	}
	
	@Step("Validate CLEAR FILTER icon")
	private void validateClearFilter() throws InterruptedException {
		Thread.sleep(3000);
		WebElement filter = this.driver.findElement(By.cssSelector(gridFilter));
		String text = filter.getAttribute("class");
		if (text.contains("ACTIVE")) {
			String errorMsg = "Filter is still active";
			logger.info(errorMsg);
			Assert.fail(errorMsg);
		} else {
			logger.info("Clear filter icon become inactive");
		}
	}
}
