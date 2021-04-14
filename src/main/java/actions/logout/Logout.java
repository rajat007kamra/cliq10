package actions.logout;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

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

/**
 * 
 * @author Arun.Kapoor
 *
 */


/**
 * TODO: Input model goes here.
 */
public class Logout extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;

	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Logout.class);

	public Logout(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.USER-AVATAR-LBL")
	private WebElement userSettings;

	@FindBy(css = "div.MODAL-BUTTON[title='LOGIN']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[@class='user-setts-options']//div[text()='power_settings_new']")
	private WebElement logoutButton;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(10000);
			logger.info("open user settings");
			clickUserSettings();
			logger.info("Wait for the Logout button to be displayed");
			PageHelper.waitForElementToBeDisplayed(this.driver, logoutButton);
			startTime = Instant.now();
			clickLogoutButton();
			CommonVariables.actionTime = new Date().getTime();
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
			Assert.assertEquals(verifyLogout().trim(), "LOGIN");
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {
		logger.info("To Be configured");
	}

	@Override
	public void tearDown() {
		logger.info("To Be configured");
	}

	@Step("Click user settings")
	private void clickUserSettings() {
		logger.info("Click user settings !!");
		PageHelper.clickUsingJs(this.driver, userSettings);
	}

	@Step("Click logout button")
	private void clickLogoutButton() {
		logger.info("Click on logout button");
		PageHelper.clickUsingJs(driver, logoutButton);
	}

	@Step("Validate logout button")
	private String verifyLogout() {
		logger.info("After logout verified text found ::- " + PageHelper.getText(this.driver, loginButton));
		return PageHelper.getText(this.driver, loginButton);
	}
}
