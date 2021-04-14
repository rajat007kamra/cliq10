package actions.delete;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.WorkbarActions;
import actions.delete.model.DeleteModel;
import io.qameta.allure.Step;
import junit.framework.Assert;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Delete extends WorkbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private DeleteModel deleteModel;
	final static Logger logger = Logger.getLogger(Delete.class);

	public Delete(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.deleteModel = new Gson().fromJson(jsonString, DeleteModel.class);
	}

	private String yesButton = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-YES";
	private String deleteDisabled = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-DELETE.TRG-BASE-DISABLED[title='DELETE']";

	@Override
	public boolean execute() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			startTime = Instant.now();
			if (this.deleteModel.getAccess() != null && !this.deleteModel.getAccess().isEmpty()) {
				checkDeleteIcon();
			} else {
				verifyDelete(this.driver);
				clickDelete();
			}			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateDelete();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click DELETE icon")
	private void clickDelete() {
		PageHelper.click(this.driver, deleteIcon);
		logger.info("Delete icon clicked");
	}

	@Step("Validate DELETE icon")
	private void validateDelete() {
		if(this.deleteModel.getAccess().trim().toUpperCase()=="YES" || this.deleteModel.getAccess()==null) {
			if (this.driver.findElements(By.cssSelector(yesButton)).size() > 0) {
				logger.info("Delete icon validated successfully");
			} else {
				Assert.fail("Either confirmation popup not opened or YES not found on confirmation popup");
			}
		}
	}

	/**
	 * @param realm
	 */
	@Step("Check is save disabled")
	public boolean checkIfDeleteIsDisabled(String icon) {
		boolean isDeleteActive = false;
		logger.info("Verify " + icon + " icon");
		PageHelper.waitForElementVisibility(this.driver, By.cssSelector(String.format(deleteDisabled, icon)),
				PageHelper.X_TIMEOUT_SEC);
		WebElement activeRealmElem = this.driver.findElement(By.cssSelector(String.format(deleteDisabled, icon)));
		PageHelper.waitForPageLoad(driver);
		isDeleteActive = PageHelper.isElementDisplayed(activeRealmElem);
		return isDeleteActive;
	}

	public void checkDeleteIcon() {
		String isAccess = this.deleteModel.getAccess().trim().toUpperCase();
		if (isAccess.equals("YES")) {
			if (!checkIfDeleteIsDisabled("DELETE")) {
				logger.info("Delete icon is enabled");
				clickDelete();
			} else {
				Assert.fail("Delete icon is disabled but expected enabled");
			}
		} else if (isAccess.equals("NO")) {
			if (checkIfDeleteIsDisabled("DELETE")) {
				logger.info("Delete icon is disabled");
			} else {
				Assert.fail("Delete icon is enabled but expected disabled");
			}
		} else {
			Assert.fail("Access could be YES/NO only");
		}
	}
}
