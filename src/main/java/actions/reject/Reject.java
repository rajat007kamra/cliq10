package actions.reject;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.reject.model.RejectModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Reject extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private RejectModel rejectModel;
	final static Logger logger = Logger.getLogger(Reject.class);
	
	public Reject(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.rejectModel = new Gson().fromJson(jsonString, RejectModel.class);
	}
	
	@Override
    public boolean execute() {
    	try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.rejectModel.getAccess() != null && !this.rejectModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "DENY", this.rejectModel.getAccess().trim().toUpperCase(), denyIcon);
			} else {
				verifyReject(this.driver);
				clickDeny();
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
			checkIfIconIsDisabled(this.driver, "DENY");
			logger.info("DENY icon validated successfully");
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

	@Step("Click DENY icon")
	private void clickDeny() {
		PageHelper.click(this.driver, denyIcon);
		logger.info("Deny icon clicked");
	}
}
