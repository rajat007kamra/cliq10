package actions.editzone.workbar;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Revise extends EditZoneWorkbar {
	private static String action = "REVISE";
	private final static Logger logger = Logger.getLogger(Revise.class);

	public Revise(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
