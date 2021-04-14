package actions.editzone.workbar;

import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.Actions;
import actions.editzone.workbar.model.WorkbarModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class EditZoneWorkbar extends Driver implements Actions {
	private WebDriver driver;
	private WorkbarModel workbarModel;
	private WorkbarHelper workbarHelper;
	private Logger logger;

	public EditZoneWorkbar(WebDriver driver, String action, Logger logger) {
		super(driver);
		this.driver = driver;
		workbarModel = new WorkbarModel();
		workbarModel.setAction(action);
		workbarHelper = new WorkbarHelper(this.driver);
		this.logger = logger;
	}

	@Override
	public boolean execute() {
		try {
			logger.info("Click at action " + workbarModel.getAction());
			workbarHelper.clickAction(workbarModel.getAction());
			CommonVariables.actionTime = new Date().getTime();
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			logger.info("Verify if action " + workbarModel.getAction() + " is disabled");
			boolean actionEnabled = workbarHelper.isActionEnabled(workbarModel.getAction());
			CommonVariables.responseTime = new Date().getTime();
			CommonVariables.timeTaken = CommonVariables.responseTime - CommonVariables.actionTime;
			logger.info("[RESPTIME] " + workbarModel.getAction().toUpperCase() + "- " + CommonVariables.timeTaken);
			Assert.assertFalse(actionEnabled, "Aaction " + workbarModel.getAction() + " must be disabled");
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	public boolean validatePopUpMessage(String popupMessage, String actionName) {
		try {
			Assert.assertEquals(workbarHelper.getPopupMessage().trim(), (popupMessage).trim());
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
	}

	@Override
	public void tearDown() {
	}
}
