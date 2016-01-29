package booklibrary.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booklibrary.abstracts.AbstractPageObject;
import booklibrary.rows.AuthorTableRow;

public class BookAddPage extends AbstractPageObject {
	@FindBy(id="book-add-title")
	private WebElement title;
	@FindBy(id="prepare-next-author-button")
	private WebElement addNextAuthorButton;
	@FindBy(id="book-save-button")
	private WebElement saveBookButton;
	
	private List<AuthorTableRow> authors = new ArrayList<AuthorTableRow>();
	
	
	public BookAddPage(WebDriver driver) {
		super(driver);
		updateAuthors();
	}
	
	public AddNextAuthorPage clickAddNextAuthorButton() {
		addNextAuthorButton.click();
		return PageFactory.initElements(driver, AddNextAuthorPage.class);
	}
	
	public boolean isPossibleToAddNextAuthor(){
		return addNextAuthorButton.isEnabled();
	}
	
	public void clickSaveBookButton(){
		saveBookButton.click();
	}

	public BookAddPage setTitle(String title) {
		this.title.sendKeys(title);
		return this;
	}
	
	public BookAddPage setFirstName(int authorIndex, String firstName) {
		getAuthor(authorIndex).setFirstName(firstName);
		return this;
	}
	
	public BookAddPage setLastName(int authorIndex, String lastName) {
		getAuthor(authorIndex).setLastName(lastName);
		return this;
	}

	private AuthorTableRow getAuthor(int authorIndex) {
		return authors.get(authorIndex);
	}
	
	private void updateAuthors() {
		authors.clear();
		for (WebElement singleRow : driver.findElements(By.id("book-add-author"))) {
			authors.add(new AuthorTableRow(singleRow.findElement(By.id("first-name")),
					singleRow.findElement(By.id("last-name"))));
		}
	}
}
