package actions.checkmessage;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.checkmessage.model.CheckMessageModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class CheckMessage extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private CheckMessageModel checkMsgModel;
	final static Logger logger = Logger.getLogger(CheckMessage.class);

	public CheckMessage(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.checkMsgModel = new Gson().fromJson(jsonString, CheckMessageModel.class);
	}

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMainMessage;

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-top")
	private WebElement CheckTopMessage;
	
	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-bottom")
	private WebElement checkBottomMessage;
	
	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			startTime = Instant.now();
			Assert.assertEquals(getMessage(), this.checkMsgModel.getVerifyMessage().trim().toUpperCase());
//			getMessage();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, "Message not found");
			return false;
		}
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Get message")
	private String getMessage() {
		if (this.checkMsgModel.getActionName().trim().toUpperCase().equals("SAVE")
				|| this.checkMsgModel.getActionName().trim().toUpperCase().equals("SUBMIT")
				|| this.checkMsgModel.getActionName().trim().toUpperCase().equals("REFRESH")
				|| this.checkMsgModel.getActionName().trim().toUpperCase().equals("RESET")) {
			CommonVariables.notificationMsg = PageHelper.getText(this.driver, checkMainMessage);
			logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		} else if (this.checkMsgModel.getActionName().trim().toUpperCase().equals("DUPLICATE")
				|| this.checkMsgModel.getActionName().trim().toUpperCase().equals("DOWNLOAD")) {
			logger.info("waiting for downloading message popup");
			PageHelper.waitForElementToBeDisplayed(this.driver, CheckTopMessage);
			CommonVariables.notificationMsg = PageHelper.getText(this.driver, CheckTopMessage);
			logger.info("Top notification message found ::- " + CommonVariables.notificationMsg);
			String bottomNotification = PageHelper.getText(this.driver, checkBottomMessage);
			logger.info("Bottom notification message found ::- " + bottomNotification);
		}
		return CommonVariables.notificationMsg;
	}
}
