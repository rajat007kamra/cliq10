package actions.switchBrowsertab;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.browsernavigation.BrowserNavigation;
import io.qameta.allure.Step;
import selenium.driver.Driver;

public class SwitchBrowserTab extends Driver implements Actions {
	private WebDriver driver;
	private SwitchBrowserTabModel model;
	final static Logger logger = Logger.getLogger(BrowserNavigation.class);

	public SwitchBrowserTab(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.model = new Gson().fromJson(jsonString, SwitchBrowserTabModel.class);
	}

	@Override
	public boolean execute() {
		try {
			Thread.sleep(10000);
			// PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			tabSwitch(this.model.getswitchtab());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Switch Browser Tab {expectedString}")
	public void tabSwitch(String expectedString) throws InterruptedException {
		((JavascriptExecutor) this.driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
		if (expectedString.trim().toUpperCase().equals("CHILD")) {
			this.driver.switchTo().window(tabs.get(1));
			logger.info("Driver is now on Child Window");
			Thread.sleep(5000);
			this.driver.get("https://qanon-b.mezocliq.com/mezocliq/looqs.html");
		} else if (expectedString.trim().toUpperCase().equals("PARENT")) {
			this.driver.switchTo().window(tabs.get(0));
			logger.info("Driver is now on Parent Window");
		} else {
			logger.info(expectedString + " Not Valid ");
		}
	}

}
