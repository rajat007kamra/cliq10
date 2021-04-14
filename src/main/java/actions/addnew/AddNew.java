package actions.addnew;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.addnew.model.AddNewModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class AddNew extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private AddNewModel addNewModel;
	final static Logger logger = Logger.getLogger(AddNew.class);

	public AddNew(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.addNewModel = new Gson().fromJson(jsonString, AddNewModel.class);
	}

	private String addNewDisabled = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-NEW.TRG-BASE-DISABLED";

	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.addNewModel.getAccess() != null && !this.addNewModel.getAccess().isEmpty()) {
				checkAddNewIcon();
			} else {
				verifyAddNew(this.driver);
				clickAddNew();
			}
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
			validateAddNew();
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
		logger.info("Login action setup is not yet configured");
	}

	@Override
	public void tearDown() {
		logger.info("Login action teardown is not yet configured");
	}

	@Step("Click Add New icon")
	private void clickAddNew() {
		PageHelper.click(this.driver, plusIcon);
		logger.info("Clicked on plus icon");
		PageHelper.waitForPageLoad(this.driver);
	}

	@Step("Validate Add New icon")
	private void validateAddNew() {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.reviseIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.info("Add New icon validated successfully");
		} else {
			String erroMsg = "Add New icon is still enabled";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
		}
	}

	/**
	 * @param realm
	 */
	@Step("Check is add new disabled")
	public boolean checkIfAddNewIsDisabled() {
		boolean isAddNewActive = false;
		logger.info("Verify Add New icon");
		PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
		if(this.driver.findElements(By.cssSelector(addNewDisabled)).size()>0)
		{
			isAddNewActive = true;
		}			
		return isAddNewActive;
	}

	public void checkAddNewIcon() {
		String isAccess = this.addNewModel.getAccess().trim().toUpperCase();
		if (isAccess.equals("YES")) {
			if (!checkIfAddNewIsDisabled()) {
				logger.info("Add new icon is enabled");
			} else {
				Assert.fail("Add new icon is disabled but expected enabled");
			}
		} else if (isAccess.equals("NO")) {
			if (checkIfAddNewIsDisabled()) {
				logger.info("Add new icon is disabled");
			} else {
				Assert.fail("Add new icon is enabled but expected disabled");
			}
		} else {
			Assert.fail("Access could be YES/NO only");
		}
	}
}
