package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Reject extends EditZoneWorkbar {
	private static String action = "REJECT";
	private String message = "REJECT!";
	private final static Logger logger = Logger.getLogger(Reject.class);

	public Reject(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}

	@Override
	public boolean validate() {
		return validatePopUpMessage(message, action);
	}
}
