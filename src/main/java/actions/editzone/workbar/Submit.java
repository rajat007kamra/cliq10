package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Submit extends EditZoneWorkbar {
	private final static String action = "SUBMIT";
	private final static String popUpMessage = "SUBMITTED!";
	private final static Logger logger = Logger.getLogger(Submit.class);

	public Submit(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}

	@Override
	public boolean validate() {
		return validatePopUpMessage(popUpMessage, action);
	}
}
