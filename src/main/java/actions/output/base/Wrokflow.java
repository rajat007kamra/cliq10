package actions.output.base;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import actions.output.pojo.Workflow;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class Wrokflow {
	private WebDriver driver;
	private String workflow = "WORKFLOW";
	private List<String> triggers;
	final static Logger logger = Logger.getLogger(Workflow.class);

	public Wrokflow(WebDriver driver, List<String> triggers) {
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
			List<WebElement> collapsed = elm.findElements(By.cssSelector(profileCollapsed));
			if (collapsed.size() > 0) {
				PageHelper.click(this.driver, headerElm);
			}
			if (headerElm.getText().equalsIgnoreCase(workflow)) {
				return elm;
			}
		}
		return null;
	}

	private void clickTrigger(String triggername) {
		WebElement mainSectionElem = getMainSection();
		WebElement triggerElem = mainSectionElem.findElement(By.cssSelector(triggerLoc));
		if (triggerElem.getText().equalsIgnoreCase(triggername)) {
			PageHelper.click(this.driver, triggerElem);
		}
	}

	public void navigateToTrigger() {
		for (String trigger : triggers) {
			clickTrigger(trigger);
		}
	}
}
