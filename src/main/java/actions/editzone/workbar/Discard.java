package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Discard extends EditZoneWorkbar {
	private static String action = "DISCARD";
	private final static Logger logger = Logger.getLogger(Discard.class);

	public Discard(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
