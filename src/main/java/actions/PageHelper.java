package actions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import selenium.driver.Driver;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class PageHelper {
	public static int DEFAULT_TIMEOUT_SEC = 60;
	public static int X_TIMEOUT_SEC = 5;
	public static int XX_TIMEOUT_SEC = 10;
	public static int XXX_TIMEOUT_SEC = 20;
	public static int DEFAULT_POLLINNG_TIME = 1;
	public static int L_TIMEOUT_SEC = 3;
	public static int FILE_UPLOAD_TIME = 300;

	public static void maximizeWindow() {
		Driver.getDriver().manage().window().maximize();
	}

	public static void click(WebDriver driver, WebElement element) {
		waitForElementToBeClickable(driver, element);
		element.click();
	}

	public static void locateElement(WebDriver driver, WebElement element) {
		PageHelper.waitForElementToBeDisplayed(driver, element);
		boolean isVisible = PageHelper.isElementDisplayed(element);
		Assert.assertTrue(isVisible, "Element is Present");
	}

	public static void clickUsingJs(WebDriver driver, WebElement element) {
		waitForElementToBeClickable(driver, element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void clickUsingJsNoWait(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void clickNoWait(WebDriver driver, WebElement element) {
		element.click();
	}

	public static void clickRadioButton(WebDriver driver, WebElement element) {
		waitForElementToBeDisplayed(driver, element);
		element.click();
	}

	public static String getText(WebDriver driver, WebElement element) {
		waitForElementToBePresent(driver, element);
		return element.getText();
	}

	public static void clearText(WebDriver driver, WebElement element) {
		waitForElementToBePresent(driver, element);
		element.clear();
	}

	public static void sendKeys(WebDriver driver, WebElement element, String text) {
		waitForElementToBePresent(driver, element);
		waitForElementToBeClickable(driver, element);
		clearText(driver, element);
		element.sendKeys(text);
	}

	public static WebElement waitForElement(WebDriver driver, WebElement element, int timoutSec, int pollingSec,
			ExpectedCondition ec) {
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timoutSec))
				.pollingEvery(Duration.ofSeconds(pollingSec))
				.ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);

		try {
			fWait.until(ec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return element;
	}

	public static void waitForElementToBeDisplayed(WebDriver driver, WebElement element) {
		waitForElement(driver, element, DEFAULT_TIMEOUT_SEC, DEFAULT_POLLINNG_TIME,
				ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementToBePresent(WebDriver driver, WebElement element) {
		waitForElement(driver, element, DEFAULT_TIMEOUT_SEC, DEFAULT_POLLINNG_TIME,
				ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		waitForElement(driver, element, DEFAULT_TIMEOUT_SEC, DEFAULT_POLLINNG_TIME,
				ExpectedConditions.elementToBeClickable(element));
	}

	public static boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public static boolean isElementPresent(List<WebElement> element) {
		if (element.size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public static void selectByValue(WebDriver driver, WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static void selectByVisibleText(WebDriver driver, WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public static void waitForPageLoad(WebDriver driver) {
		new WebDriverWait(driver, DEFAULT_TIMEOUT_SEC).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				return (Boolean) js.executeScript("return document.readyState").equals("complete");
			}
		});
	}

	public static void mouseMoveToElement(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public static String getRandomeString(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static void moveSlider(WebDriver driver, WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
	}

//	    public static void moveSlider(WebDriver driver, WebElement element, int x, int y) {
//	        Actions move = new Actions(driver);
//	        Action action = (Action) move.dragAndDropBy(element, x, y).build();
//	        action.perform();
//	    }

	public static boolean waitForInvisiblity(WebDriver driver, WebElement element) {
		try {
			waitForElement(driver, element, DEFAULT_TIMEOUT_SEC, DEFAULT_POLLINNG_TIME,
					ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void sendKeys(WebDriver driver, WebElement element, Keys keys, boolean clearText) {
		waitForElementToBePresent(driver, element);
		waitForElementToBeClickable(driver, element);
		if (clearText) {
			clearText(driver, element);
		}
		element.sendKeys(keys);
	}

	public static void waitInSeconds(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public static void waitForElementVisibility(WebDriver driver, By by, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static void waitForElementPresence(WebDriver driver, By by, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static void waitForElementStaleness(WebDriver driver, WebElement elem, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(elem)));
	}

	public static void waitForTextToPresent(WebDriver driver, WebElement element, String expectedString,
			int specifiedTimeout) {
		WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
		ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> element.getText().equals(expectedString);
		wait.until(elementTextEqualsString);
	}

	public static String appendHashCode(WebDriver driver, String value) {
		String updatedvalue = value;
		if (value.contains("HASHCODE")) {
			updatedvalue = value.replace("HASHCODE", String.valueOf(driver.hashCode()));
		}
		return updatedvalue;
	}

	public static boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}
}
