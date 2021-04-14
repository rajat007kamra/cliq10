package actions.clarifier;

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

public class Clarifier extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Clarifier.class);

	public Clarifier(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-TRG-PAD-MAIN-CLARIFIER[title*='SHOW']")
	private WebElement showClarifier;
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-HDR-MAIN")
	private WebElement flyHeader;
	
	private String flyHeaderText = "APPROVERS CLARIFICATION";
	
	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			clickClarifier();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateClarifier();
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

	@Step("Click clarifier")
	private void clickClarifier()
	{
		PageHelper.click(this.driver, showClarifier);
		logger.info("Click clarifier icon");
	}
	
	@Step("Validate Clarifier")
	private void validateClarifier() {
		String header = PageHelper.getText(this.driver, flyHeader);
		int sepPos = header.lastIndexOf(":");
		String updateHeader = header.substring(0, sepPos);
		Assert.assertEquals(updateHeader, flyHeaderText);
		logger.info("Clarifier flyout opened successfully");
	}
}
