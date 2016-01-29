package booklibrary.rows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import booklibrary.pages.BookEditPage;

public class BookTableRow {
	private WebDriver driver;
	private WebElement title;
	private WebElement authors;
	private WebElement deleteBookButton;
	private WebElement editBookButton;
	
	public BookTableRow(WebElement singleRow, WebDriver driver) {
		this.title = singleRow.findElement(By.id("title"));
		this.authors = singleRow.findElement(By.id("authors"));
		this.deleteBookButton = singleRow.findElement(By.id("delete-button"));
		this.editBookButton = singleRow.findElement(By.id("edit-button"));
		this.driver = driver;
	}

	public String getTitle() {
		return title.getText();
	}

	public String getAuthors() {
		return authors.getText();
	}
	
	public void clickDeleteBookButton() {
		deleteBookButton.click();
	}
	
	public BookEditPage clickEditBookButton() {
		editBookButton.click();
		return PageFactory.initElements(driver, BookEditPage.class);
	}
}
