package booklibrary.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class AbstractPageObject {
	protected WebDriver driver;
	
	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean hasError() {
		try {
			driver.findElement(By.cssSelector("input:invalid"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
