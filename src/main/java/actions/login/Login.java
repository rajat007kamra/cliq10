package actions.login;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.login.model.LoginModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * This class is responsible for login input expected: "action": { "type":
 * "LOGIN", "context": { "username": "", "password": "", "subtenent": "" } },
 * 
 *
 */
public class Login extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private LoginModel loginModel;
	final static Logger logger = Logger.getLogger(Login.class);

	public Login(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.loginModel = new Gson().fromJson(jsonString, LoginModel.class);
	}

	@FindBy(css = "input.LOGIN-FIELD-USER")
	private WebElement usernameField;

	@FindBy(css = "input.LOGIN-FIELD-PASSWORD")
	private WebElement passwordField;

	@FindBy(css = "div.MODAL-BUTTON[title='LOGIN']")
	private WebElement loginButton;

	@FindBy(css = "input.ENTRYFLD-TXT-MODAL-DEFAULT[placeholder='SUBTENANT']")
	private WebElement subtenantField;

	@FindBy(css = "div.MODAL-BUTTON-SUBMIT")
	private WebElement submitButton;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.USER-AVATAR-LBL div:nth-child(2)")
	private WebElement readSubTenant;

	@FindBy(css = "input.ENTRYFLD-TXT-MODAL-DEFAULT[placeholder='SUBTENANT']+div.ENTRYFLD-TRG-EXPOSED")
	private WebElement subTenantDropDownExposed;

	@FindBy(css = "input.ENTRYFLD-TXT-MODAL-DEFAULT[placeholder='SUBTENANT']+div.ENTRYFLD-TRG-MODAL-DROP")
	private WebElement subTenantDropDown;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.MODAL-MSG")
	private WebElement errorMessage;

	private String innerSection = "div.MODAL-SECTION-TITLE";
	private String new_pwd = "input[class*='ENTRYFLD-TXT-MODAL-DEFAULT'][placeholder='NEW PASSWORD']";
	private String cnf_new_pwd = "input[class*='ENTRYFLD-TXT-MODAL-DEFAULT'][placeholder='CONFIRM NEW PASSWORD']";
	private String sendButton = "div.MODAL-BUTTON-SUBMIT";

	@Override
	public boolean execute() {
		try {
			login();
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
			if (this.loginModel.getExpected_error() != null) {
				verifyErrorMessage(this.loginModel.getExpected_error());
			} else {
				verifySubtenantNameHomePage();
			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			Assert.assertTrue(false, "Subtenant not verified after login");
			return false;
		}

	}

	@Override
	public void setup() {
		logger.info("Login action setup is not yet configured");
	}

	@Override
	public void tearDown() {
		logger.info("Login action teardown is not yet configured");
	}

	@Step("Enter username {username}")
	private void setUserName(String username) {
		PageHelper.sendKeys(this.driver, usernameField, username);
	}

	@Step("Enter password {password}")
	private void setPassword(String password) {
		PageHelper.sendKeys(this.driver, passwordField, password);
	}

	@Step("Select subtenant {subtanentName}")
	private boolean setSubTanent(String subtanentName) {
		try {
			PageHelper.clearText(this.driver, subtenantField);
			PageHelper.sendKeys(this.driver, subtenantField, subtanentName);
			PageHelper.waitForElementToBeDisplayed(this.driver, subTenantDropDownExposed);
			subtenantField.sendKeys(Keys.TAB);
			PageHelper.waitForElementToBeDisplayed(this.driver, subTenantDropDown);
			return true;
		} catch (Exception e) {
			Assert.assertTrue(false, "Subtenant not found on login page");
			return false;
		}
	}

	@Step("Login button click")
	private void clickLoginButton() {
		PageHelper.click(this.driver, loginButton);
	}

	@Step("Submit button click")
	private void clickSubmitButton() {
		PageHelper.click(this.driver, submitButton);
		if (isReloginNeeded()) {
			login();
		}
	}

	@Step("Verify subtenant name at home page after login")
	private void verifySubtenantNameHomePage() {
		PageHelper.waitForElementToBeDisplayed(this.driver, readSubTenant);
		if (this.loginModel.getSubtenant() != null) {
			Assert.assertEquals(PageHelper.getText(this.driver, readSubTenant), this.loginModel.getSubtenant().trim());
		} else {
			Assert.assertEquals(PageHelper.getText(this.driver, readSubTenant),
					DbHelper.getSubTenant(this.loginModel.getUser()).trim());
		}
	}

	@Step("Verify the error message {errorMessage}")
	private void verifyErrorMessage(String errorMessage) {
		PageHelper.waitForElementToBeDisplayed(this.driver, this.errorMessage);
		PageHelper.waitForTextToPresent(this.driver, this.errorMessage, errorMessage.trim(),
				PageHelper.DEFAULT_TIMEOUT_SEC);
		String errorText = PageHelper.getText(this.driver, this.errorMessage);
		Assert.assertEquals(errorMessage.trim(), errorText.trim(), "Error message must be poped up");
	}

	private boolean canViewSubtenantText() {
		boolean canView = false;
		try {
			PageHelper.getText(this.driver, readSubTenant);
			canView = this.readSubTenant.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return canView;
	}

	private boolean isReloginNeeded() {
		if (!canViewSubtenantText()) {
			String errorText = PageHelper.getText(this.driver, this.errorMessage);
			logger.info("Login error text: " + errorText);
			if (errorText.contains("RECORD CHANGED")) {
				return true;
			}
		}
		return false;
	}

	private void login() {
		logger.info("Set username " + loginModel.getUser());
		setUserName(this.loginModel.getUser());
		if (this.loginModel.getPassword() != null) {
			logger.info("Set password " + this.loginModel.getPassword());
			setPassword(this.loginModel.getPassword());
		} else {
			logger.info("Get Password from DB");
			String password = DbHelper.getPassword(this.loginModel.getUser());
			logger.info("Set password " + password);
			setPassword(password);
		}
		logger.info("Click on login button");
		startTime = Instant.now();
		clickLoginButton();
		if (checkNewPasswordPopup()) {
			String newPassword = getNewPassword(this.loginModel.getUser());
			setNewPassword(newPassword);
		}

		if (this.loginModel.getSubtenant() != null) {
			logger.info("Set subtenant " + loginModel.getSubtenant());
			setSubTanent(this.loginModel.getSubtenant());
			logger.info("Click submit button");
			clickSubmitButton();
		}
	}

	private boolean checkInnerSection() {
		List<WebElement> innersections = this.driver.findElements(By.cssSelector(innerSection));
		if (innersections.size() > 0) {
			return true;
		}
		return false;
	}

	private boolean checkNewPasswordPopup() {
		if (checkInnerSection()) {
			String new_password_reqired = PageHelper.getText(this.driver,
					this.driver.findElement(By.cssSelector(innerSection)));
			if (new_password_reqired.contains("NEW PASSWORD REQUIRED")) {
				return true;
			}

		}
		return false;
	}

	private String setNewPassword(String password) {
		if (checkNewPasswordPopup()) {
			WebElement new_pwd_elem = this.driver.findElement(By.cssSelector(new_pwd));
			WebElement cnf_new_pwd_elem = this.driver.findElement(By.cssSelector(cnf_new_pwd));
			WebElement send_elem = this.driver.findElement(By.cssSelector(sendButton));
			PageHelper.sendKeys(this.driver, new_pwd_elem, password);
			PageHelper.sendKeys(this.driver, cnf_new_pwd_elem, password);
			PageHelper.click(this.driver, send_elem);

			return password;
		}
		return null;
	}

	private String getNewPassword(String userId) {
		String password = DbHelper.getPassword(userId);
		String lastWord = password.substring(password.lastIndexOf(" ") + 1);
		Integer valueOf = Integer.valueOf(lastWord);
		DecimalFormat formatter = new DecimalFormat("00");
		String aFormatted = formatter.format(valueOf + 1);
		password = password.substring(0, password.lastIndexOf(" ")) + " " + aFormatted;
		DbHelper.updatePassword(userId, password);

		return password;
	}

}