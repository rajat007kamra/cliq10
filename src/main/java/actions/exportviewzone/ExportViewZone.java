package actions.exportviewzone;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.ToolbarActions;
import actions.exportviewzone.model.ExportViewZoneModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class ExportViewZone extends ToolbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ExportViewZoneModel exportViewZoneModel;
	final static Logger logger = Logger.getLogger(ExportViewZone.class);

	public ExportViewZone(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.exportViewZoneModel = new Gson().fromJson(jsonString, ExportViewZoneModel.class);
	}

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMessage;

	@Override
	public boolean execute() {
		try {
			clickMoreOptions(this.driver);
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.exportViewZoneModel.getAccess() != null && !this.exportViewZoneModel.getAccess().isEmpty()) {
				checkIconAccess(this.driver, "EXPORT LIST", this.exportViewZoneModel.getAccess().trim().toUpperCase());
			} else {
				verifyExportView(this.driver);
				clickExportViewZone();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Export list not found", false);
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			if (this.exportViewZoneModel.getAccess() != null && !this.exportViewZoneModel.getAccess().isEmpty()) {
				logger.info("Access is- " +this.exportViewZoneModel.getAccess());
			}
			else {
				Assert.assertEquals(validateExportViewZone().trim(), (this.exportViewZoneModel.getVerifyMessage()).trim());
			}
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

	@Step("Click on export view zone")
	private void clickExportViewZone() {
		PageHelper.click(this.driver, exportListIcon);
		logger.info("click on export view zone");
	}

	@Step("Get message")
	private String validateExportViewZone() {
		logger.info("waiting for export viewzone message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, checkMessage);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, checkMessage);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}
}
