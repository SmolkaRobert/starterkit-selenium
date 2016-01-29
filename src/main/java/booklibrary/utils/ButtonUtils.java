package booklibrary.utils;

import org.openqa.selenium.WebElement;

public class ButtonUtils {
	public static boolean isWebElementClickable(WebElement button){
		return (button.isDisplayed() && button.isEnabled());
	}
}
