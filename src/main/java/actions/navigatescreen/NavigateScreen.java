package actions.navigatescreen;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.navigatescreen.model.NavigateScreenModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class NavigateScreen extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;

	private WebDriver driver;
	private NavigateScreenModel navigateScreenModel;
	private NavigationHelper navigationHelper;
	final static Logger logger = Logger.getLogger(NavigateScreen.class);

	/**
	 *
	 * @param driver
	 * @param jsonString
	 */
	public NavigateScreen(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		navigateScreenModel = new Gson().fromJson(jsonString, NavigateScreenModel.class);
		navigationHelper = new NavigationHelper(this.driver);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean execute() {
		try {
			navigationHelper.closeAdvanceSearch();
			startTime = Instant.now();
			if (navigateScreenModel.getAccess() != null && !navigateScreenModel.getAccess().isEmpty()) {
				String accessType = navigateScreenModel.getAccess().trim().toUpperCase();
				if (navigateScreenModel.getProcess() != null && !navigateScreenModel.getProcess().isEmpty()) {
					navigationHelper.clickOnRealm(navigateScreenModel.getRealm(), "YES");
					navigationHelper.searchForProcess(navigateScreenModel.getRealm(), navigateScreenModel.getProcess(),
							accessType);
				} else {
					navigationHelper.clickOnRealm(navigateScreenModel.getRealm(), accessType);
				}
			} else {
				navigationHelper.clickOnRealm(navigateScreenModel.getRealm(), "YES");
				if (navigateScreenModel.getProcess() != null && !navigateScreenModel.getProcess().isEmpty()) {
					navigationHelper.searchForProcess(navigateScreenModel.getRealm(), navigateScreenModel.getProcess(),
							"YES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		if (navigateScreenModel.getColumn() != null && !navigateScreenModel.getColumn().isEmpty()) {
			logger.info("Filter for column");
			navigationHelper.filter(navigateScreenModel.getColumn());
		}
		return true;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}
}
