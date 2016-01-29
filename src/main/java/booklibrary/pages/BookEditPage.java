package booklibrary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booklibrary.abstracts.AbstractPageObject;

public class BookEditPage extends AbstractPageObject {
	@FindBy(id="new-title")
	private WebElement newTitle;
	@FindBy(id="save-new-title-button")
	private WebElement saveNewTitleButton;

	public BookEditPage(WebDriver driver) {
		super(driver);
	}

	public BookEditPage setNewTitle(String newTitle) {
		this.newTitle.clear();
		this.newTitle.sendKeys(newTitle);
		return this;
	}
	
	public void clickSaveNewTitleButton(){
		saveNewTitleButton.click();
	}
}
