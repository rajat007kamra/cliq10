package actions.accordion;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.accordion.model.AccordionModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Accordion extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private AccordionModel accordionModel;
	final static Logger logger = Logger.getLogger(Accordion.class);

	public Accordion(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.accordionModel = new Gson().fromJson(jsonString, AccordionModel.class);
	}

	private String rightMenuClass = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.shuffle-trg-rght";
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.shuffle-trg-rght")
	private WebElement rightMenu;

	@Override
	public boolean execute() {
		try {
//			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			Thread.sleep(10000);
			startTime = Instant.now();
			if ((this.accordionModel.getAccordion().trim().toUpperCase()).equals("RIGHT")) {
				changeAccordionState(rightMenu, this.rightAccordionTitle().trim());
			}
			else {
				logger.error(this.accordionModel.getAccordion() + " accordion not exists in application");
			}
			CommonVariables.actionTime = System.currentTimeMillis();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			if ((this.accordionModel.getAccordion().trim().toUpperCase()).equals("RIGHT")) {
				verifyAccordionState(rightMenu, this.rightAccordionTitle().trim());
			} else {
				logger.error(this.accordionModel.getAccordionState() + " is not a valid accordion state");
			}
			endTime = Instant.now();
			Duration timeElapsed = Duration.between(startTime, endTime);
			logger.info("[RESPTIME] " + timeElapsed.toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Change accordion state")
	private void changeAccordionState(WebElement element, String title) {
		if ((this.accordionModel.getAccordionState().trim().toUpperCase()).equals("COLLAPSE")) {
			if (title.equals("COLLAPSE")) {
				logger.info("Collapsed " + this.accordionModel.getAccordion() + " menu!");
				PageHelper.click(this.driver, element);
			} else if (title.equals("EXPAND")) {
				logger.info(this.accordionModel.getAccordion() + " menu is already collapsed");
			}
		} else if ((this.accordionModel.getAccordionState().trim().toUpperCase()).equals("EXPAND")) {
			if (title.equals("COLLAPSE")) {
				logger.info(this.accordionModel.getAccordion() + " menu is already expand");
			} else if (title.equals("EXPAND")) {
				logger.info("Expand " + this.accordionModel.getAccordion() + " menu!");
				PageHelper.click(this.driver, element);
			}
		}
	}

	@Step("Get right accordion state title")
	private String rightAccordionTitle() {
		String rightMenuTitle = this.driver.findElement(By.cssSelector(rightMenuClass)).getAttribute("title");
		return rightMenuTitle;
	}

	@Step("Verify accordion state")
	private void verifyAccordionState(WebElement element, String title) {
		if ((this.accordionModel.getAccordionState().trim().toUpperCase()).equals("COLLAPSE")) {
			if (title.equals("COLLAPSE")) {
				logger.info("Collapsed " + this.accordionModel.getAccordion() + " menu!");
			} else if (title.equals("EXPAND")) {
				logger.info(this.accordionModel.getAccordion() + " menu is collapsed");
			}
		} else if ((this.accordionModel.getAccordionState().trim().toUpperCase()).equals("EXPAND")) {
			if (title.equals("COLLAPSE")) {
				logger.info(this.accordionModel.getAccordion() + " menu is expanded");
			} else if (title.equals("EXPAND")) {
				logger.info("Expand " + this.accordionModel.getAccordion() + " menu!");
			}
		}
	}
}
