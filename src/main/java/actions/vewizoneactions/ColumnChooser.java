package actions.vewizoneactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * TBD
 * 
 * @author SurendraShekhawat
 *
 */
public class ColumnChooser extends ViewZoneAction {

	private static String action = "COLUMN CHOOSER";
	final static Logger logger = Logger.getLogger(ColumnChooser.class);

	public ColumnChooser(WebDriver driver, String jsonString) {
		super(driver, action, logger);
	}
}
