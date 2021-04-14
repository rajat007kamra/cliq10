package actions.output.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class Flyout {
	private Map<String, String> valueMap;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Flyout.class);

	public Flyout(WebDriver driver) {
		this.driver = driver;
		valueMap = new HashMap<String, String>();
	}

	private String flyoutLoc = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.DEFAULT-SCROLL-STYLE[style*='z-index: 1']";
	private String flyoutItems = "div.EZ-WGT-ITEM";
	private String variableNameLocator = "div.EZ-WGT-FLD-DSPLY-FLYOUT";
	private String variableValueLocator = "div.ENTRYFLD-EZ-WGT";

	private List<WebElement> getWidgetItems() {
		By byFlyoutLoc = By.cssSelector(flyoutLoc);
		PageHelper.waitForElementVisibility(this.driver, byFlyoutLoc, PageHelper.DEFAULT_TIMEOUT_SEC);
		return this.driver.findElement(byFlyoutLoc).findElements(By.cssSelector(flyoutItems));
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> getValues() {
		try {
			List<WebElement> widgetItems = getWidgetItems();
			for (WebElement elem : widgetItems) {
				WebElement nameElem = elem.findElement(By.cssSelector(variableNameLocator));
				WebElement valueElem = elem.findElement(By.cssSelector(variableValueLocator));
				if (PageHelper.isAttribtuePresent(valueElem, "title")) {
					valueElem.click();
					TimeUnit.SECONDS.sleep(1);
					valueMap.put(nameElem.getText(), valueElem.getAttribute("title"));
				} else {
					valueMap.put(nameElem.getText(), "NO VALUE");
				}
			}
		} catch (InterruptedException e) {
		}
		return valueMap;
	}

}
