package actions.selectmultiplerows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SelectMultipleRowsHelper {
	private WebDriver driver;
	
	public SelectMultipleRowsHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	private String tableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] div.GRID-WGT-TBLS table.TABLE-WGT tr";	

	public void selectMultipleRows() {
		List<WebElement> numOfRows = this.driver.findElements(By.cssSelector(tableRows));
	    Actions actions = new Actions(driver);
	    actions.click(numOfRows.get(1)).keyDown(Keys.CONTROL).click(numOfRows.get(6)).keyUp(Keys.CONTROL).build().perform();
	}
}