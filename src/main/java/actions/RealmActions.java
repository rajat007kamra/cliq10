package actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public abstract class RealmActions extends Driver implements Actions {
	final static Logger logger = Logger.getLogger(RealmActions.class);

	public RealmActions(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.logout-trg[title='LOGOUT']")
	public WebElement logoutIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.HELP-TRG")
	public WebElement helpIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG-RESET")
	public WebElement resetIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG-REFRESH")
	public WebElement refreshIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG-CHOOSER")
	public WebElement columnsChooserIcon;

	// Method to verify whether the LOGOUT button is enabled or disabled
	public boolean verifyLogout(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.logoutIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either logout icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Logout icon is enabled");
			return true;
		}
	}

	// Method to verify whether the HELP button is enabled or disabled
	public boolean verifyHelp(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.helpIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either help icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Help icon is enabled");
			return true;
		}
	}

	// Method to verify whether the RESET button is enabled or disabled
	public boolean verifyReset(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.resetIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either reset icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Reset icon is enabled");
			return true;
		}
	}

	// Method to verify whether the REFRESH button is enabled or disabled
	public boolean verifyRefresh(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.refreshIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either refresh icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Refresh icon is enabled");
			return true;
		}
	}

	// Method to verify whether the COLUMN CHOOSER button is enabled or disabled
	public boolean verifyColumnChooser(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.columnsChooserIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either column chooser icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Column chooser icon is enabled");
			return true;
		}
	}
}