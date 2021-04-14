package actions.equation;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.Actions;
import actions.PageHelper;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import selenium.driver.Driver;

public class Equation extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Equation.class);

	public Equation(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-TRG-PAD-MAIN-EQUATION[title*='SHOW']")
	private WebElement equation;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-HDR-MAIN[title*='EQUATION']")
	private WebElement equationFlyout;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			clickEquation();
			CommonVariables.actionTime = System.currentTimeMillis();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		if (PageHelper.isElementDisplayed(equationFlyout)) {
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} else
			return false;

	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click equation")
	private void clickEquation() throws InterruptedException {
		if (PageHelper.isElementEnabled(equation)) {
			Thread.sleep(5000);
			PageHelper.click(this.driver, equation);
			logger.info("Click equation icon");
		} else {
			logger.error("Either equation icon is disabled or not found");
		}
	}
}