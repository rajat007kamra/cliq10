package actions.revise;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.revise.model.ReviseModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Revise extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ReviseModel reviseModel;
	final static Logger logger = Logger.getLogger(Revise.class);

	public Revise(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.reviseModel = new Gson().fromJson(jsonString, ReviseModel.class);
	}

	@Override
    public boolean execute() {
    	try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.reviseModel.getAccess() != null && !this.reviseModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "REVISE", this.reviseModel.getAccess().trim().toUpperCase(), reviseIcon);
			} else {
				verifyRevise(this.driver);
				Thread.sleep(5000);
				clickRevise();
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
			checkIfIconIsDisabled(this.driver, "REVISE");
			logger.info("REVISE icon validated successfully");
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

	}

	@Override
	public void tearDown() {

	}

	@Step("Click REVISE icon")
	private void clickRevise() {
		PageHelper.click(this.driver, reviseIcon);
		logger.info("Revise icon clicked");
	}
}
