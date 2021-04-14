package actions.timelineactions;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.timelineactions.model.TimeMachineModel;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

public class TimeMachine extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private TimeMachineModel timeModel;
	final static Logger logger = Logger.getLogger(TimeMachine.class);

	public TimeMachine(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.timeModel = new Gson().fromJson(jsonString, TimeMachineModel.class);
	}

	private String isTimeMachineDisabled = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TIMELINE-WGT.disabled";
	
	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.timeModel.getAccess() != null && !this.timeModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "TIME-MACHINE", this.timeModel.getAccess().trim().toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Access is mandatory for TIME-MACHINE context", false);
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * @param iconName
	 */
	@Step("Check is {iconName} disabled")
	public boolean checkIfIconIsDisabled(WebDriver driver, String iconName) {
		boolean isActive = false;
		logger.info("Verify " + iconName + " icon");
		PageHelper.waitInSeconds(driver, PageHelper.L_TIMEOUT_SEC);
		if(driver.findElements(By.cssSelector(isTimeMachineDisabled)).size()>0)
		{
			isActive = true;
		}
		return isActive;
	}

	//Check whether user has access of any icon
	public void checkIconAccess(WebDriver driver, String iconName, String isAccess) {
		if (isAccess.equals("YES")) {
			if (!checkIfIconIsDisabled(driver, iconName)) {
				logger.info(iconName +" icon is enabled");
			} else {
				Assert.fail(iconName +" icon is disabled but expected enabled");
			}
		} else if (isAccess.equals("NO")) {
			if (checkIfIconIsDisabled(driver, iconName)) {
				logger.info(iconName +" icon is disabled");
			} else {
				Assert.fail(iconName +" icon is enabled but expected disabled");
			}
		} else {
			Assert.fail("Access could be YES/NO only");
		}
	}
}
