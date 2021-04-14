package actions.treeview;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.PageHelper;
import actions.ToolbarActions;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class TreeView extends ToolbarActions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(TreeView.class);

	public TreeView(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TREE-WGT div.TREE-WGT-CTRL[title='HORIZONTAL TREE']")
	private WebElement horizontalTreeViewIcon;

	@Override
	public boolean execute() {
		try {
			verifyTreeView(this.driver);
			startTime = Instant.now();
			clickTreeView();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(false, "Tree view not found");
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateTreeView();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click on tree view")
	private void clickTreeView() {
		PageHelper.click(this.driver, treeView);
		logger.info("click on tree view");
	}

	@Step("validate tree view")
	private void validateTreeView() {
		logger.info("waiting for horizontal tree view icon message popup");
		PageHelper.isElementDisplayed(horizontalTreeViewIcon);
		logger.info("Horizontal tree view icon is present");
	}
}
