package actions.output.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import actions.PageHelper;
import actions.closewidget.CloseWidget;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class Quicklist {
	private WebDriver driver;
	private String quickList = "QUICKLIST";
	private List<String> triggers;
	final static Logger logger = Logger.getLogger(Quicklist.class);

	public Quicklist(WebDriver driver, List<String> triggers) {
		this.driver = driver;
		this.triggers = triggers;
	}

	private String mainSectionLOcator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-SUPER-SCT-MAIN";
	private String header = "div.EZ-WGT-SPR-SCT-HDR-DSPL-MAIN";
	private String triggerLoc = "div.ez-wgt-qcklst-inner span.ez-wgt-wide-itm-lbl";
	private String profileCollapsed = "div.EZ-WGT-SUPER-SCT-BDY-OUTER[style='height: 0px;']";

	private WebElement getMainSection() {
		By mainSectBy = By.cssSelector(mainSectionLOcator);
		PageHelper.waitForElementVisibility(this.driver, mainSectBy, PageHelper.DEFAULT_TIMEOUT_SEC);
		List<WebElement> maiSectElms = this.driver.findElements(mainSectBy);
		for (WebElement elm : maiSectElms) {
			WebElement headerElm = elm.findElement(By.cssSelector(header));
			if (headerElm.getText().equalsIgnoreCase(quickList)) {
				List<WebElement> collapsed = elm.findElements(By.cssSelector(profileCollapsed));
				if (collapsed.size() > 0) {
					PageHelper.click(this.driver, headerElm);
				}
				return elm;
			}
		}
		return null;
	}

	private void clickTrigger(String triggername) {
		WebElement mainSectionElem = getMainSection();
		List<WebElement> triggerElems = mainSectionElem.findElements(By.cssSelector(triggerLoc));
		for (WebElement triggerElem : triggerElems) {
			if (triggerElem.getText().equalsIgnoreCase(triggername)) {
				Actions actions = new Actions(this.driver);
				actions.moveToElement(triggerElem).build().perform();
				PageHelper.clickUsingJs(this.driver, triggerElem);
				break;
			}
		}
	}

	public Map<String, Map<String, String>> readFlyoutsValue() {
		Map<String, Map<String, String>> quickListMap = new HashMap<String, Map<String, String>>();
		for (String trigger : triggers) {
			clickTrigger(trigger);
			Flyout flyout = new Flyout(this.driver);
			Map<String, String> values = flyout.getValues();
			quickListMap.put(trigger, values);
		}
		CloseWidget closeWidget = new CloseWidget(this.driver, "");
		closeWidget.execute();
		return quickListMap;
	}
}
