package actions.subtab;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.subtab.model.SubtabModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Subtab extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private SubtabModel subtabModel;
	final static Logger logger = Logger.getLogger(Subtab.class);

	public Subtab(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.subtabModel = new Gson().fromJson(jsonString, SubtabModel.class);
	}

	private String subtab = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.VZ-SUBTAB-EMPTY";

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] input.ENTRYFLD-TXT-VZSUBTAB")
	private WebElement setSubtab;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.VZ-SUBTAB-DSPLY-SELECTED")
	private WebElement selectedSubtab;

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMessage;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			setSubtabTitle(this.subtabModel.getSubtabTitle());
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
			Assert.assertEquals("SAVED!", checkMessage().trim());
			Assert.assertEquals(this.subtabModel.getSubtabTitle().trim().toUpperCase(), verifySubtab().trim());
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

	@Step("Create subtab")
	private void setSubtabTitle(String tabTitle) {
		List<WebElement> subTabList = this.driver.findElements(By.cssSelector(subtab));
		subTabList.get(0).click();
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		PageHelper.sendKeys(this.driver, setSubtab, tabTitle);
		PageHelper.sendKeys(this.driver, setSubtab, Keys.ENTER, false);
	}

	@Step("Verify message")
	private String checkMessage() {
		logger.info("waiting for refresh message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, checkMessage);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, checkMessage);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}

	@Step("Verify subtab")
	private String verifySubtab() {
		logger.info("waiting for new subtab");
		PageHelper.waitForElementToBeDisplayed(this.driver, selectedSubtab);
		String newSubtab = PageHelper.getText(this.driver, selectedSubtab);
		logger.info("Now selected subtab title is ::- " + newSubtab);
		return newSubtab;
	}
}