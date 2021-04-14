package actions.openflyouts;

import org.openqa.selenium.WebDriver;

import actions.Actions;
import selenium.driver.Driver;

public abstract class OpenFlyoutsLocators extends Driver implements Actions {

	public OpenFlyoutsLocators(WebDriver driver) {
		super(driver);
	}

	public static String innerQuicklistFlex = "div.DECKLAYER-PARENT[style*= 'z-index: 1;'] div.ez-wgt-qcklst div.ez-wgt-wide-itm span.ez-wgt-wide-itm-lbl";

	public static String quicklistFlyoutHeader = "div.DECKLAYER-PARENT[style*= 'z-index: 1;'] div.FLY div.FLY-HDR";

	public static String innerWorkflowFlexClass = "div.DECKLAYER-PARENT[style*= 'z-index: 1;'] div.ez-wgt-wrkflow div.ez-wgt-wide-itm span.ez-wgt-wide-itm-lbl";

	public static String workflowFlyoutHeader = "div.DECKLAYER-PARENT[style*= 'z-index: 1;'] div.FLY div.STND-PAD-CNT-WRP div.STND-PAD-HDR-MAIN";
}