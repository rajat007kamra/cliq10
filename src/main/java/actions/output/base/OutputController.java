package actions.output.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.output.pojo.Outcome;
import actions.output.pojo.Quicklist;
import actions.output.report.pojo.OutcomeReport;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class OutputController {
	private WebDriver driver;
	private Outcome outcomeModel;
	private String testName;
	private String testDescription;
	private OutcomeReport outcomeReport;
	final static Logger logger = Logger.getLogger(OutputController.class);
	private String suffleLocatorCollapsed = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.REALM-HD-RGHT-QONCIERGE div[class*='shuffle-trg TRG-BASE trg-base material-icons'][title='EXPAND']";
	private String suffleLocatorExpand = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.REALM-HD-RGHT-QONCIERGE div[class*='shuffle-trg TRG-BASE trg-base material-icons'][title='COLLAPSE']";
	private Map<String, String> valuemap;

	public OutputController(WebDriver driver, String jsonString, String testName, String testDescription) {
		this.driver = driver;
		this.testName = testName;
		this.testDescription = testDescription;
		this.outcomeModel = new Gson().fromJson(jsonString, Outcome.class);
		this.outcomeReport = new OutcomeReport();
		this.valuemap = new HashMap<String, String>();
	}

	private void expandEditZone() {
		List<WebElement> editZoneSuffle = this.driver.findElements(By.cssSelector(suffleLocatorCollapsed));
		if (editZoneSuffle.size() > 0) {
			Actions actions = new Actions(this.driver);
			actions.moveToElement(this.driver.findElement(By.cssSelector(suffleLocatorCollapsed))).build().perform();
			PageHelper.clickUsingJs(this.driver, this.driver.findElement(By.cssSelector(suffleLocatorCollapsed)));
		}
	}

	
	private void closeEditZone() {
		List<WebElement> editZoneSuffle = this.driver.findElements(By.cssSelector(suffleLocatorExpand));
		if (editZoneSuffle.size() > 0) {
			Actions actions = new Actions(this.driver);
			actions.moveToElement(this.driver.findElement(By.cssSelector(suffleLocatorExpand))).build().perform();
			PageHelper.clickUsingJs(this.driver, this.driver.findElement(By.cssSelector(suffleLocatorExpand)));
		}
	}

	
	public Map<String, String> readData() {
		expandEditZone();
		if (this.outcomeModel.getProfile() != null) {
			Map<String, String> filterMap = new HashMap<String, String>();
			Profile profile = new Profile(this.driver);
			Map<String, String> values = profile.getValues();
			List<String> list = this.outcomeModel.getProfile();
			for (String key : list) {
				if (values.get(key) != null) {
					filterMap.put(key, values.get(key));
				}
			}
			this.valuemap.putAll(filterMap);
		}

		if (this.outcomeModel.getSummary() != null) {
			Summary summary = new Summary(this.driver);
			Map<String, String> filterMap = new HashMap<String, String>();
			Map<String, String> values = summary.getValues();
			List<String> list = this.outcomeModel.getSummary();
			for (String key : list) {
				if (values.get(key) != null) {
					filterMap.put(key, values.get(key));
				}
			}
			this.valuemap.putAll(filterMap);
		}

		if (this.outcomeModel.getQuicklist() != null) {
			List<String> triggers = new ArrayList<String>();
			Map<String, String> filterMap = new HashMap<String, String>();
			List<Quicklist> input_QuicklistList = this.outcomeModel.getQuicklist();
			for (Quicklist quicklist : input_QuicklistList) {
				triggers.add(quicklist.getPath());
			}
			actions.output.base.Quicklist quicklist = new actions.output.base.Quicklist(this.driver, triggers);
			Map<String, Map<String, String>> readFlyoutsValue = quicklist.readFlyoutsValue();
			List<Quicklist> list = this.outcomeModel.getQuicklist();

			Set<String> keySet = readFlyoutsValue.keySet();
			for (String key : keySet) {
				Map<String, String> map = readFlyoutsValue.get(key);
				for (Quicklist quicklist__ : list) {
					List<String> flyouts = quicklist__.getFlyout();
					for (String flyout : flyouts) {
						if (map.get(flyout) != null) {
							filterMap.put(flyout, map.get(flyout));
						}
					}
				}
				this.valuemap.putAll(filterMap);
			}
		}
		if (this.outcomeModel.getWorkflow() != null) {
			// TODO
		}

		if (this.outcomeModel.getAttachment() != null) {
			// TODO
		}

		// Get Test name here
		valuemap.put("TEST NAME", this.testName);
		valuemap.put("TEST DESCRIPTION", this.testDescription);
		closeEditZone();
		return valuemap;
	}
}
