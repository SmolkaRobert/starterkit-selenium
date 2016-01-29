package booklibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import booklibrary.abstracts.AbstractSelenium;
import booklibrary.pages.BookAddPage;
import booklibrary.pages.BookEditPage;
import booklibrary.pages.BookListPage;
import booklibrary.rows.BookTableRow;

public class BookListSeleniumTest extends AbstractSelenium {

	private static final String AUTHORS_DELIMITER = ", ";
	private static final String NAMES_DELIMITER = " ";
	
	private static final int FIRST_AUTHOR_INDEX = 0;
	private static final String FIRST_AUTHOR_FIRST_NAME = "Arthur";
	private static final String FIRST_AUTHOR_LAST_NAME = "Clarke";
	private static final String SECOND_AUTHOR_FIRST_NAME = "Stephen";
	private static final String SECOND_AUTHOR_LAST_NAME = "Baxter";

	private static final String FIRST_BOOK_TITLE = "Koniec dzieciństwa";
	private static final String SECOND_BOOK_TITLE = "Oko czasu";
	private static final String CHANGED_BOOK_TITLE = "Diuna";
	
	private static final String ALL_BOOKS = "";
	private static final String SEARCHED_TITLE_PREFIX = "p";
	private static final int INDEX_OF_BOOK_TO_DELETE = 0;
	private static final int INDEX_OF_EDITED_BOOK = 0;

	private String firstAuthorFullName;
	private String secondAuthorFullName;
	private BookListPage bookListPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		bookListPage = openBookList();
		initializeAuthors();
	}

	private void initializeAuthors() {
		List<String> firstAuthorNames = new ArrayList<String>();
		firstAuthorNames.add(FIRST_AUTHOR_FIRST_NAME);
		firstAuthorNames.add(FIRST_AUTHOR_LAST_NAME);
		List<String> secondAuthorNames = new ArrayList<String>();
		secondAuthorNames.add(SECOND_AUTHOR_FIRST_NAME);
		secondAuthorNames.add(SECOND_AUTHOR_LAST_NAME);
		
		firstAuthorFullName = createAuthorFullName(firstAuthorNames);
		secondAuthorFullName = createAuthorFullName(secondAuthorNames);
	}

	private String createAuthorFullName(List<String> authorNames) {
		return String.join(NAMES_DELIMITER, authorNames);
	}

	@Test
	public void shouldSearchForAllBooks() {
		// given
		// when
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		List<BookTableRow> foundBooks = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(foundBooks).isNotNull().isNotEmpty();
	}

	@Test
	public void shouldSearchForBooksStartingWithLetterP() {
		// given
		String titleQuery = SEARCHED_TITLE_PREFIX;
		// when
		bookListPage.setBookSearchTitle(titleQuery).clickBookSearchButton();
		List<BookTableRow> foundBooks = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(foundBooks).isNotNull().isNotEmpty();
		for (BookTableRow singleBook : foundBooks) {
			Assertions.assertThat(singleBook.getTitle().toLowerCase()).startsWith(titleQuery.toLowerCase());
		}
	}

	@Test
	public void shouldAddNewBookWithOneAuthor() {
		// given

		bookListPage.clickBookAddButton().setTitle(FIRST_BOOK_TITLE)
				.setFirstName(FIRST_AUTHOR_INDEX, FIRST_AUTHOR_FIRST_NAME)
				.setLastName(FIRST_AUTHOR_INDEX, FIRST_AUTHOR_LAST_NAME).clickSaveBookButton();
		// when
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("book-add-modal-window")));
		bookListPage.setBookSearchTitle(FIRST_BOOK_TITLE).clickBookSearchButton();
		List<BookTableRow> foundBooks = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(foundBooks).isNotNull().isNotEmpty()
					.extracting(BookTableRow::getTitle, BookTableRow::getAuthors).contains(Assertions.tuple(FIRST_BOOK_TITLE, firstAuthorFullName));
	}

	@Test
	public void shouldAddNewBookWithTwoAuthors() {
		// given
		BookAddPage bookAddPage = bookListPage.clickBookAddButton().setTitle(SECOND_BOOK_TITLE)
				.setFirstName(FIRST_AUTHOR_INDEX, FIRST_AUTHOR_FIRST_NAME)
				.setLastName(FIRST_AUTHOR_INDEX, FIRST_AUTHOR_LAST_NAME);

		bookAddPage.clickAddNextAuthorButton().setFirstName(SECOND_AUTHOR_FIRST_NAME)
				.setLastName(SECOND_AUTHOR_LAST_NAME).clickSaveNextAuthorButton();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("author-add-modal-window")));
		// when
		bookAddPage.clickSaveBookButton();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("book-add-modal-window")));
		bookListPage.setBookSearchTitle(SECOND_BOOK_TITLE).clickBookSearchButton();
		List<BookTableRow> foundBooks = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(foundBooks).isNotNull().isNotEmpty();
		Assertions.assertThat(foundBooks).extracting(BookTableRow::getTitle).contains(SECOND_BOOK_TITLE);
		Assertions.assertThat(foundBooks).extracting(BookTableRow::getAuthors).contains(secondAuthorFullName);
	}
	
	@Test
	public void shouldDeleteBook() {
		// given
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		BookTableRow bookToDelete = bookListPage.getBooksSearchResult().get(INDEX_OF_BOOK_TO_DELETE);
		String titleOfBookToDelete = bookToDelete.getTitle();
		// when
		bookToDelete.clickDeleteBookButton();
		bookListPage.setBookSearchTitle(titleOfBookToDelete).clickBookSearchButton();
		List<BookTableRow> booksAfterDeletion = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(booksAfterDeletion).isNotNull().isEmpty();
		
	}
	
	@Test
	public void shouldChangeBookTitle() {
		// given
		String titleAfterChange = CHANGED_BOOK_TITLE;
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		BookTableRow bookToChange = bookListPage.getBooksSearchResult().get(INDEX_OF_EDITED_BOOK);
		String titleBeforeChange = bookToChange.getTitle();
		bookToChange.clickEditBookButton().setNewTitle(titleAfterChange).clickSaveNewTitleButton();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("edit-book-window")));
		// when
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		List<BookTableRow> booksAfterChange = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(booksAfterChange).isNotNull().isNotEmpty()
					.extracting(BookTableRow::getTitle).contains(titleAfterChange).doesNotContain(titleBeforeChange);
	}
}