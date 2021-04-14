package actions.preferences;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.preferences.model.PreferencesModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Preferences extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private PreferencesModel preferencesModel;
	static Logger logger = Logger.getLogger(Preferences.class);

	public Preferences(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.preferencesModel = new Gson().fromJson(jsonString, PreferencesModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.USER-AVATAR-LBL")
	private WebElement preferences;

	@FindBy(xpath = "//div[@class='user-setts-options']//div[text()='settings']")
	private WebElement settings;

	@FindBy(xpath = "//div[@class='user-setts-options']//div[text()='settings_backup_restore']")
	private WebElement resetSettings;

	@FindBy(xpath = "//div[@class='user-setts-options']//div[text()='power_settings_new']")
	private WebElement logout;

	@FindBy(css = "div.MODAL-BUTTON[title='LOGIN']")
	private WebElement loginButton;

	@FindBy(css = "div.prefs-title")
	private WebElement verifyIsSettingsOpen;

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement verifyResetSettings;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(10000);
			startTime = Instant.now();
			clickPreferences();
			if ((this.preferencesModel.getPreferenceOption().trim().toUpperCase()).equals("SETTINGS")) {
				clickSettings();
			} else if ((this.preferencesModel.getPreferenceOption().trim().toUpperCase()).equals("RESET SETTINGS")) {
				clickResetSettings();
			} else if ((this.preferencesModel.getPreferenceOption().trim().toUpperCase()).equals("LOGOUT")) {
				clickLogout();
			} else {
				logger.error("User passed invalid preference option " + this.preferencesModel.getPreferenceOption());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			if ((this.preferencesModel.getPreferenceOption().trim().toUpperCase()).equals("SETTINGS")) {
				Assert.assertEquals(validateSettings().trim(), "MY PROFILE");
			} else if ((this.preferencesModel.getPreferenceOption().trim().toUpperCase()).equals("RESET SETTINGS")) {
				Assert.assertEquals(validateResetSetting().trim(), "REFRESHED!");
			} else if ((this.preferencesModel.getPreferenceOption().trim().toUpperCase()).equals("LOGOUT")) {
				Assert.assertEquals(validateLogout().trim(), "LOGIN");
			} else {
				logger.error("User passed invalid preference option " + this.preferencesModel.getPreferenceOption());
			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click preferences")
	private void clickPreferences() {
		logger.info("Click preferences !!");
		PageHelper.clickUsingJs(this.driver, preferences);
	}

	@Step("Click settings")
	private void clickSettings() {
		logger.info("Click settings !!");
		PageHelper.clickUsingJs(this.driver, settings);
	}

	@Step("Click reset settings")
	private void clickResetSettings() {
		logger.info("Click reset settings !!");
		PageHelper.clickUsingJs(this.driver, resetSettings);
	}

	@Step("Click logout")
	private void clickLogout() {
		logger.info("Click logout !!");
		PageHelper.clickUsingJs(this.driver, logout);
	}

	@Step("Verify settings")
	private String validateSettings() {
		logger.info("Verify settings!!");
		PageHelper.waitForElementToBeDisplayed(this.driver, verifyIsSettingsOpen);
		logger.info("Verified text found ::- " + PageHelper.getText(this.driver, verifyIsSettingsOpen));
		return PageHelper.getText(this.driver, verifyIsSettingsOpen);
	}

	@Step("verify reset settings")
	private String validateResetSetting() {
		logger.info("waiting for reset settings message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, verifyResetSettings);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, verifyResetSettings);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}

	@Step("Verify logout")
	private String validateLogout() {
		logger.info("waiting for login page");
		PageHelper.waitForElementToBeDisplayed(this.driver, loginButton);
		logger.info("Verified text found ::- " + PageHelper.getText(this.driver, loginButton));
		return PageHelper.getText(this.driver, loginButton);
	}
}
