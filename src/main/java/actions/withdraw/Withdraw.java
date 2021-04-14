package actions.withdraw;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.withdraw.model.WithdrawModel;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Withdraw extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private WithdrawModel withdrawModel;
	final static Logger logger = Logger.getLogger(Withdraw.class);

	public Withdraw(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.withdrawModel = new Gson().fromJson(jsonString, WithdrawModel.class);
	}

	@Override
    public boolean execute() {
    	try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.withdrawModel.getAccess() != null && !this.withdrawModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "WITHDRAW", this.withdrawModel.getAccess().trim().toUpperCase(), withdrawIcon);
			} else {
				verifyWithdraw(this.driver);
				clickWithdraw();
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
			checkIfIconIsDisabled(this.driver, "WITHDRAW");
			logger.info("WITHDRAW icon validated successfully");
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

	@Step("Click WITHDRAW icon")
	private void clickWithdraw() {
		PageHelper.click(this.driver, withdrawIcon);
		logger.info("Withdraw icon clicked");
	}
}