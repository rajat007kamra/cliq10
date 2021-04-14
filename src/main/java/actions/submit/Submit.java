package actions.submit;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.submit.model.SubmitModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Submit extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private SubmitModel submitModel;
	final static Logger logger = Logger.getLogger(Submit.class);

	public Submit(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.submitModel = new Gson().fromJson(jsonString, SubmitModel.class);
	}

	@Override
    public boolean execute() {
    	try {
//			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
    		Thread.sleep(10000);
			startTime = Instant.now();
			if (this.submitModel.getAccess() != null && !this.submitModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "SUBMIT", this.submitModel.getAccess().trim().toUpperCase(), submitIcon);
			} else {
				verifySubmit(this.driver);
				clickSubmit();
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
			checkIfIconIsDisabled(this.driver, "SUBMIT");
			logger.info("SUBMIT icon validated successfully");
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

	@Step("Click SUBMIT icon")
	private void clickSubmit() {
		PageHelper.click(this.driver, submitIcon);
		logger.info("Submit icon clicked");
	}
}