package actions.output.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Profile {
	private WebDriver driver;
	private String profile = "PROFILE";
	private Map<String, String> valueMap;

	public Profile(WebDriver driver) {
		this.driver = driver;
		valueMap = new HashMap<String, String>();
	}

	final static Logger logger = Logger.getLogger(Profile.class);
	private String mainSectionLOcator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-SUPER-SCT-MAIN";
	private String header = "div.EZ-WGT-SPR-SCT-HDR-DSPL-MAIN";
	private String variableNameLocator = "div.ENTRYFLD-EZ-WGT";
	private String variableValueLocator = "div.EZ-WGT-ITEM input";
	private String profileCollapsed = "div.EZ-WGT-SUPER-SCT-BDY-OUTER[style='height: 0px;']";


	
	private WebElement getMainSection() {
		By mainSectBy = By.cssSelector(mainSectionLOcator);
		PageHelper.waitForElementVisibility(this.driver, mainSectBy, PageHelper.DEFAULT_TIMEOUT_SEC);
		List<WebElement> maiSectElms = this.driver.findElements(mainSectBy);
		for (WebElement elm : maiSectElms) {

			WebElement headerElm = elm.findElement(By.cssSelector(header));
			if (headerElm.getText().equalsIgnoreCase(profile)) {
				List<WebElement> collapsed = elm.findElements(By.cssSelector(profileCollapsed));
				if(collapsed.size() > 0) {
					PageHelper.click(this.driver, headerElm);
				}
				return elm;
			}
		}
		return null;
	}

	public Map<String, String> getValues() {
		WebElement mainSection = getMainSection();
		List<WebElement> nameElems = mainSection.findElements(By.cssSelector(variableNameLocator));
		for (WebElement nameElem : nameElems) {
			WebElement valueElem = nameElem.findElement(By.cssSelector(variableValueLocator));
			valueMap.put(nameElem.getAttribute("title"), valueElem.getText());
		}

		return valueMap;
	}

}
