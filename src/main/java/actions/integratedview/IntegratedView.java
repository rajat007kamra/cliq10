package actions.integratedview;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.PageHelper;
import actions.ToolbarActions;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class IntegratedView extends ToolbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(IntegratedView.class);

	public IntegratedView(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TOP-TOOLBAR-TRG-IV.TOP-TOOLBAR-TRG-ACTIVE[title*='CLOSE INTEGRATED VIEW']")
	private WebElement activeIntegratedView;

	String clickIntegratedView = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TOP-TOOLBAR-TRG-IV.TOP-TOOLBAR-TRG-ACTIVE[title*='CLOSE INTEGRATED VIEW']";

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT[style*='z-index: 1'] div.intgrvew-close-trg[title='CLOSE']")
	private WebElement closeIntegratedView;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			clickIntegratedView();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Integrated view not found");
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateIntegratedView();
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

	@Step("Click on integrated view")
	private void clickIntegratedView() {
		try {
			if (this.driver.findElements(By.cssSelector(clickIntegratedView)).size() > 0) {
				this.driver.findElement(By.cssSelector(clickIntegratedView)).click();
			}
			verifyIntegratedView(this.driver);
			PageHelper.isElementDisplayed(integratedView);
			PageHelper.click(this.driver, integratedView);
			logger.info("Clicked on integrated view");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Step("validate integrated view")
	private void validateIntegratedView() {
		logger.info("Waiting for active integrated view");
		PageHelper.isElementDisplayed(activeIntegratedView);
		logger.info("Integrated view is active i.e. opened");
		PageHelper.click(this.driver, closeIntegratedView);
	}
}