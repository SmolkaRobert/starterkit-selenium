package booklibrary.rows;

import org.openqa.selenium.WebElement;

public class AuthorTableRow {
	private WebElement firstName;
	private WebElement lastName;
	
	public AuthorTableRow(WebElement firstName, WebElement lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AuthorTableRow setFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
		return this;
	}

	public AuthorTableRow setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
		return this;
	}
	
}
