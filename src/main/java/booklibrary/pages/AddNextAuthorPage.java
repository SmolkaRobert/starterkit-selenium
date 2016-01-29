package booklibrary.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booklibrary.abstracts.AbstractPageObject;

public class AddNextAuthorPage extends AbstractPageObject {
	@FindBy(id="next-author-first-name")
	private WebElement firstName;
	@FindBy(id="next-author-last-name")
	private WebElement lastName;
    @FindBy(id="save-next-author-button")
	private WebElement saveNextAuthorButton;
	
	public AddNextAuthorPage(WebDriver driver) {
		super(driver);
	}
	
	public AddNextAuthorPage setFirstName(String firstName){
		this.firstName.sendKeys(firstName);
		return this;
	}
	
	public AddNextAuthorPage setLastName(String lastName){
		this.lastName.sendKeys(lastName);
		return this;
	}
	
	public void clickSaveNextAuthorButton() {
		saveNextAuthorButton.click();
	}
}
