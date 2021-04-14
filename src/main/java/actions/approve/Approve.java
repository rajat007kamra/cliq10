package actions.approve;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.approve.model.ApproveModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Approve extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ApproveModel approveModel;
	final static Logger logger = Logger.getLogger(Approve.class);
	
	public Approve(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.approveModel = new Gson().fromJson(jsonString, ApproveModel.class);
	}
	
	@Override
    public boolean execute() {
    	try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.approveModel.getAccess() != null && !this.approveModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "APPROVE", this.approveModel.getAccess().trim().toUpperCase(), approveIcon);
			} else {
				verifyApprove(this.driver);
				clickApprove();
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
			checkIfIconIsDisabled(this.driver, "APPROVE");
			logger.info("APPROVE icon validated successfully");
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

	@Step("Click APPROVE icon")
	private void clickApprove() {
		PageHelper.click(this.driver, approveIcon);
		logger.info("Approve icon clicked");
	}
}
