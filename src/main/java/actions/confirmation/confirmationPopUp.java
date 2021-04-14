package actions.confirmation;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.log4testng.Logger;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.confirmation.model.confirmationPopUpModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class confirmationPopUp extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private confirmationPopUpModel confirmModel;
	final static Logger logger = Logger.getLogger(confirmationPopUp.class);

	public confirmationPopUp(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.confirmModel = new Gson().fromJson(jsonString, confirmationPopUpModel.class);
	}
	
	@FindBy(css = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-YES")
	private WebElement yesButton;

	@FindBy(css = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-NO")
	private WebElement noButton;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG-LOADING")
	private WebElement iconLoading;
	
	private boolean isIconLoaded() {
		return PageHelper.waitForInvisiblity(this.driver, iconLoading);
	}
	
	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			clickConfirmation();
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
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		isIconLoaded();
		return false;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {
//		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
	}

	private void clickConfirmation() throws InterruptedException {
		if ((this.confirmModel.getConfirmation().toUpperCase()).equals("YES")) {
			Thread.sleep(5000);
			PageHelper.click(this.driver, yesButton);
			logger.info("Clicked on 'YES' as confirmation");
		} else if ((this.confirmModel.getConfirmation().toUpperCase()).equals("NO")) {
			Thread.sleep(5000);
			PageHelper.click(this.driver, noButton);
			logger.info("Clicked on 'NO' as confirmation");
		} else {
			logger.error("Confirmation option not exists");
		}
	}
}
