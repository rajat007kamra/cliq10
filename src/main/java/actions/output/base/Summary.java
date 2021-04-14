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

public class Summary {
	private WebDriver driver;
	private String summary = "SUMMARY";
	private Map<String, String> valueMap;

	public Summary(WebDriver driver) {
		this.driver = driver;
		valueMap = new HashMap<String, String>();
	}

	final static Logger logger = Logger.getLogger(Summary.class);
	private String mainSectionLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-FLEX-SCT-DETAILS div.EZ-WGT-SUPER-SCT-MAIN div.EZ-WGT-ITEM";
	private String header = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-SUPER-SCT-MAIN div.EZ-WGT-SPR-SCT-HDR-DSPL-MAIN";
	private String variableNameLocator = "div.EZ-WGT-FLD-DSPLY-MAIN";
	private String variableValueLocator = "div.ENTRYFLD-EZ-WGT";
	private String profileCollapsed = "div.EZ-WGT-SUPER-SCT-BDY-OUTER[style='height: 0px;']";

	private WebElement getMainSection() {
		By mainSectBy = By.cssSelector(header);
		PageHelper.waitForElementVisibility(this.driver, mainSectBy, PageHelper.DEFAULT_TIMEOUT_SEC);
		List<WebElement> maiSectElms = this.driver.findElements(mainSectBy);
		for (WebElement elm : maiSectElms) {
			if (elm.getText().equalsIgnoreCase(summary)) {
				List<WebElement> collapsed = elm.findElements(By.cssSelector(profileCollapsed));
				if (collapsed.size() > 0) {
					PageHelper.click(this.driver, elm);
				}
				return elm;
			}
		}
		return null;
	}

	public Map<String, String> getValues() {
		try {
			getMainSection();
			List<WebElement> mainsectElms = this.driver.findElements(By.cssSelector(mainSectionLocator));
			for (WebElement elem : mainsectElms) {
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
