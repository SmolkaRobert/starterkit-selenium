package booklibrary.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import booklibrary.abstracts.AbstractPageObject;
import booklibrary.rows.BookTableRow;

public class BookListPage extends AbstractPageObject {
	@FindBy(id = "book-add-button")
	private WebElement bookAddButton;
	@FindBy(id = "book-search-button")
	private WebElement bookSearchButton;
	@FindBy(id = "book-search-title")
	private WebElement bookSearchTitle;

	private List<BookTableRow> booksSearchResult = new ArrayList<BookTableRow>();

	public BookListPage(WebDriver driver) {
		super(driver);
		this.driver.get("http://localhost:9000/#/books/book-list");
	}

	public BookListPage setBookSearchTitle(String bookSearchTitle) {
		this.bookSearchTitle.clear();
		this.bookSearchTitle.sendKeys(bookSearchTitle);
		return this;
	}

	public BookListPage clickBookSearchButton() {
		bookSearchButton.click();
		updateBooksSearchResult();
		return this;
	}

	private void updateBooksSearchResult() {
		booksSearchResult.clear();
		for (WebElement singleRow : driver.findElements(By.id("found-book"))) {
			booksSearchResult.add(new BookTableRow(singleRow, driver));
		}
	}

	public List<BookTableRow> getBooksSearchResult() {
		return booksSearchResult;
	}

	public BookAddPage clickBookAddButton() {
		bookAddButton.click();
		return PageFactory.initElements(driver, BookAddPage.class);
	}
}