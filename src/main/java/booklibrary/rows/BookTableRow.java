package booklibrary.rows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import booklibrary.pages.BookEditPage;
import booklibrary.utils.ButtonUtils;

public class BookTableRow {
	private WebDriver driver;
	private WebElement deleteBookButton;
	private WebElement editBookButton;
	
	public BookTableRow(WebElement singleRow, WebDriver driver) {
		this.deleteBookButton = singleRow.findElement(By.id("delete-button"));
		this.editBookButton = singleRow.findElement(By.id("edit-button"));
		this.driver = driver;
	}

	public void clickDeleteBookButton() {
		deleteBookButton.click();
	}
	
	public boolean isDeleteBookButtonClickable() {
		return ButtonUtils.isWebElementClickable(deleteBookButton);
	}
	
	public BookEditPage clickEditBookButton() {
		editBookButton.click();
		return PageFactory.initElements(driver, BookEditPage.class);
	}
	
	public boolean isEditBookButtonClickable() {
		return ButtonUtils.isWebElementClickable(editBookButton);
	}
}
