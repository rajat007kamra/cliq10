package actions.directory;

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

public class Directory extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
    private WebDriver driver;
    final static Logger logger = Logger.getLogger(Directory.class);
    
	public Directory(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-TRG-PAD-MAIN-DIRECTORY[title*='SHOW']")
	private WebElement directory;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-HDR-MAIN[title*='NAME']")
	private WebElement directoryFlyout;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			clickDirectory();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		if (PageHelper.isElementDisplayed(directoryFlyout)) {
			logger.info("Directory flyout opened successfully and verified");
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

	@Step("Click directory")
	private void clickDirectory() throws InterruptedException {
		if (PageHelper.isElementEnabled(directory)) {
			Thread.sleep(5000);
			PageHelper.click(this.driver, directory);
			logger.info("Click directory icon");
		} else {
			logger.error("Either directory icon is disabled or not found");
		}
	}
}