package actions.vewizoneactions;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.Actions;
import common.variables.CommonVariables;
import selenium.driver.Driver;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class ViewZoneAction extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private Logger logger;
	private ViewZoneActionHelper vzActionHelper;
	private String actionName;

	public ViewZoneAction(WebDriver driver, String actionName, Logger logger) {
		super(driver);
		this.logger = logger;
		vzActionHelper = new ViewZoneActionHelper(driver);
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			logger.info("Click at action " + actionName);
			CommonVariables.actionTime = new Date().getTime();
			vzActionHelper.clickAction(actionName);
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			logger.info("Verify if " + actionName + " is clicked");
			boolean actionClicked = vzActionHelper.isActionClicked(actionName);
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			Assert.assertTrue(actionClicked, "Action is not clicked");
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	public void confirmRefresh(boolean refresh) {
		try {
			logger.info("Click for refresh confirmation " + refresh);
			vzActionHelper.clickRefreshConfirmation(refresh);
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
		}
	}

	public boolean validateRefreshPopupMessage(String popupMessage) {
		try {
			logger.info("Verify refresh popup message");
			Assert.assertEquals(vzActionHelper.getPopUpMessage().trim(), (popupMessage).trim());
			CommonVariables.responseTime = new Date().getTime();
			CommonVariables.timeTaken = CommonVariables.responseTime - CommonVariables.actionTime;
			logger.info("[RESPTIME] " + actionName.toUpperCase() + "-" + CommonVariables.timeTaken + " milliseconds");
			return true;
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
