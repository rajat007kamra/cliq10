package actions.editzone;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import actions.EditZoneActions;
import actions.PageHelper;
import io.qameta.allure.Step;

public class EditZone extends EditZoneActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(EditZone.class);

	public EditZone(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			logger.info("click on edit/revise icon");
			clickEditIcon();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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

	@Step("Click edit icon")
	public void clickEditIcon() {
		try {
			Thread.sleep(3000);
			verifyEditZoneRevise(this.driver);
			logger.info("wait for edit/revise icon to be present and verify");
			PageHelper.click(this.driver, editZoneReviseIcon);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
