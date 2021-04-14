package actions.navigatescreen;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import actions.PageHelper;
import actions.filterrow.FilterRow;
import actions.filterrow.model.Column;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class NavigationHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(NavigationHelper.class);
	private String realmLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div[title='%1s']";
	private String disabledRealmLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REALM-WGT-TRG.TRG-BASE-DISABLED[title='%1s']";
	private String activeRealmLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REALM-WGT-TRG-ACTIVE[title='%1s']";
	private String loadingRealmLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TRG-BASE-LOADING[title='%1s']";
	private String activeProcessTableLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.VZ-TAB-SELECTED div[title='%1s']";
	private String searchFieldLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] input.SRCH-WGT-ENTRYFLD-NAV";
	private String suggestionPopupLocator = "div.SRCH-WGT-SUGG";
	private String suggestionPopup = "div.SRCH-WGT-RSLTS";
	private String dummyLoc = "div.DECKLAYER-PARENT[style*='z-index: 1'] input.SRCH-WGT-ENTRYFLD-ENTRP";
	private String activeAdvanceFilter = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REALM-HD-CNT div.ENTRP-SEARCH div.SEARCH-FLT-TRG.TRG-BASE-ACTIVE[title*='CLOSE ADVANCED SEARCH']";

	public NavigationHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param realm
	 */
	@Step("Check is realm is already selected")
	public boolean checkIfRealmIsAlreadySelected(String realm) {
		boolean isRealmActive = false;
		try {
			PageHelper.waitForElementVisibility(this.driver, By.cssSelector(String.format(activeRealmLocator, realm)),
					PageHelper.X_TIMEOUT_SEC);
			WebElement activeRealmElem = this.driver
					.findElement(By.cssSelector(String.format(activeRealmLocator, realm)));
			PageHelper.waitForPageLoad(driver);
			isRealmActive = PageHelper.isElementDisplayed(activeRealmElem);
		} catch (Exception e) {
			// Absorb the error if any occurs here
		}
		return isRealmActive;
	}

	/**
	 * @param realm
	 */
	@Step("Check is realm is disabled")
	public boolean checkIfRealmIsDisabled(String realm) {
		boolean isRealmActive = false;
		try {
			logger.info("Verify " + realm + " realm");
			PageHelper.waitForElementVisibility(this.driver, By.cssSelector(String.format(disabledRealmLocator, realm)),
					PageHelper.X_TIMEOUT_SEC);
			WebElement activeRealmElem = this.driver
					.findElement(By.cssSelector(String.format(disabledRealmLocator, realm)));
			PageHelper.waitForPageLoad(driver);
			isRealmActive = PageHelper.isElementDisplayed(activeRealmElem);
		} catch (Exception e) {
			// Absorb the error if any occurs here
		}
		return isRealmActive;
	}

	/**
	 * @param realm
	 * @throws InterruptedException
	 */
	@Step("Click in realm {realm}")
	public void clickOnRealm(String realm, String access) throws InterruptedException {
		if (access.equals("YES")) {
			if (!checkIfRealmIsDisabled(realm)) {
				logger.info(realm + " realm is enabled");
				logger.info("Click on realm " + realm);
				PageHelper.waitForElementVisibility(this.driver, By.cssSelector(String.format(realmLocator, realm)),
						PageHelper.DEFAULT_TIMEOUT_SEC);
				WebElement realmElement = this.driver.findElement(By.cssSelector(String.format(realmLocator, realm)));
				PageHelper.waitForElementToBeClickable(this.driver, realmElement);
				// Wait if realm is loading
				try {
					WebElement loadingRealmElement = this.driver
							.findElement(By.cssSelector(String.format(loadingRealmLocator, realm)));
					if (PageHelper.isElementDisplayed(loadingRealmElement)) {
						PageHelper.waitForInvisiblity(this.driver, loadingRealmElement);
					}
				} catch (Exception e) {
					// Absorb the error if any occurs here
				}
				if (!checkIfRealmIsAlreadySelected(realm)) {
					PageHelper.clickUsingJs(driver, realmElement);
				}
				logger.info("Verify if user is navigated to realm " + realm);
				verifyNavigationOnRealm(realm);
				mouseHover(realm, "HOVER");
			} else {
				String errorMsg = realm + " realm is disabled but expected enabled";
				logger.error(errorMsg);
				Assert.fail(errorMsg);
			}
		} else if (access.equals("NO")) {
			if (checkIfRealmIsDisabled(realm)) {
				logger.info(realm + " realm is disabled");
			} else {
				String errorMsg = realm + " realm is enabled but expected disabled";
				logger.error(errorMsg);
				Assert.fail(errorMsg);
			}
		} else {
			Assert.assertTrue(false, "Access value must be YES/NO");
		}
	}

	/**
	 * @param processName
	 * @throws InterruptedException
	 */
	@Step("Search for the process {processName}")
	public void searchForProcess(String realm, String processName, String access) throws InterruptedException {
		WebElement searchFieldElem = this.driver.findElement(By.cssSelector(searchFieldLocator));
		TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
		PageHelper.clickUsingJs(this.driver, searchFieldElem);
		PageHelper.sendKeys(driver, searchFieldElem, processName);
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		if (access.equals("YES")) {
			if (this.driver.findElements(By.cssSelector(suggestionPopup)).size() > 0) {
				logger.info(processName + " exists");
				clickOnProcessInSearchList(realm, processName);
				logger.info("Clicked on searched process " + processName);
				mouseHover(realm, "RELEASE");
				logger.info("Verify if user is navigated to process " + processName);
				verifyNavigationOnProcess(processName);
				Thread.sleep(5000);
			} else {
				String errorMsg = processName + " process does not exists while access value is " + access;
				logger.info(errorMsg);
				Assert.fail(errorMsg);
			}
		} else if (access.equals("NO")) {
			if (this.driver.findElements(By.cssSelector(suggestionPopup)).size() > 0) {
				String errorMsg = processName + " process exists while access value is " + access;
				logger.info(errorMsg);
				Assert.fail(errorMsg);
			} else {
				logger.info(processName + " does not exists");
			}
		} else {
			String errorMsg = "Access process value is not valid i.e. it could be only YES/NO";
			logger.info(errorMsg);
			Assert.fail(errorMsg);
		}
	}

	/**
	 * @param realmName
	 * @param processName
	 */
	@Step("Click on process in search list under realm {realmName} and process name {processName}")
	public void clickOnProcessInSearchList(String realmName, String processName) {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		List<WebElement> suggestions = this.driver.findElements(By.cssSelector(suggestionPopupLocator));
		for (WebElement suggestion : suggestions) {
			PageHelper.waitForElementToBeDisplayed(this.driver, suggestion);
			String suggestionText = PageHelper.getText(this.driver, suggestion);
			String[] strings = suggestionText.split(":");
			if (strings[0].trim().contains(realmName.trim()) && processName.trim().contains(strings[1].trim())) {
				PageHelper.clickUsingJs(this.driver, suggestion);
				break;
			}
		}
	}

	/**
	 * @param realmName
	 */
	@Step("Verify if user is navigated on realm {realmName}")
	public void verifyNavigationOnRealm(String realmName) {
		PageHelper.waitForElementVisibility(this.driver, By.cssSelector(String.format(activeRealmLocator, realmName)),
				PageHelper.DEFAULT_TIMEOUT_SEC);
		WebElement activeRealmElem = this.driver
				.findElement(By.cssSelector(String.format(activeRealmLocator, realmName)));
		PageHelper.waitForElementToBeDisplayed(this.driver, activeRealmElem);
		Assert.assertTrue(PageHelper.isElementDisplayed(activeRealmElem), "Not able to navigate to realm");
	}

	/**
	 * @param processName
	 */
	@Step("Verify if user is navigated on process {processName}")
	public void verifyNavigationOnProcess(String processName) {
		// Navigate to profile picture as a dummy move to close myzone
		By byDummyLoc = By.cssSelector(dummyLoc);
		PageHelper.waitForElementVisibility(this.driver, byDummyLoc, PageHelper.XX_TIMEOUT_SEC);
		WebElement userProfileElem = this.driver.findElement(byDummyLoc);
		PageHelper.waitForElementToBeClickable(this.driver, userProfileElem);
		Actions actions = new Actions(this.driver);
		actions.moveToElement(userProfileElem).build().perform();
		WebElement processTableElem = this.driver
				.findElement(By.cssSelector(String.format(activeProcessTableLocator, processName.toUpperCase())));
		PageHelper.waitForElementToBeDisplayed(this.driver, processTableElem);
		Assert.assertTrue(PageHelper.isElementDisplayed(processTableElem), "Not able to navigate to process");
	}

	/**
	 * In case user want to navigate till a record
	 *
	 * @param column
	 */
	public void filter(List<Column> column) {
		FilterRow filter = new FilterRow(this.driver, column);
		filter.setup();
		filter.execute();
		filter.validate();
		filter.tearDown();
	}

	/**
	 * @throws InterruptedException
	 */
	// Method to hover on REALMS
	public void mouseHover(String realm, String option) throws InterruptedException {
		Actions builder = new Actions(this.driver);
		WebElement element = this.driver.findElement(By.cssSelector(String.format(realmLocator, realm)));
		if (option.equals("HOVER")) {
			builder.moveToElement(element).build().perform();
		} else if (option.equals("RELEASE")) {
			Thread.sleep(5000);
//			builder.release(this.driver.findElement(By.cssSelector(String.format(realmLocator, realm))));
			this.driver.findElement(By.cssSelector("body")).click();
		} else {

		}
	}

	/**
	 * @throws InterruptedException
	 */
	public void closeAdvanceSearch() throws InterruptedException {
		Thread.sleep(5000);
		if (this.driver.findElements(By.cssSelector(activeAdvanceFilter)).size() > 0) {
			logger.info("Advance filter already opened");
			this.driver.findElement(By.cssSelector(activeAdvanceFilter)).click();
			logger.info("Advance filter closed");
		} else {
			logger.info("Advance filter not active");
		}
	}
}
