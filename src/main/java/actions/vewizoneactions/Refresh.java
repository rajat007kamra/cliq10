package actions.vewizoneactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class Refresh extends ViewZoneAction {
	private static String action = "REFRESH";
	private static String popUpMessage = "REFRESHED!";
	final static Logger logger = Logger.getLogger(Refresh.class);

	public Refresh(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}

	@Override
	public boolean validate() {
		confirmRefresh(true);
		return validateRefreshPopupMessage(popUpMessage);
	}
}
