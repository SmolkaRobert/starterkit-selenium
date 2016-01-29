package booklibrary;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import booklibrary.abstracts.AbstractSelenium;
import booklibrary.pages.BookAddPage;
import booklibrary.pages.BookListPage;
import booklibrary.rows.BookTableRow;

public class BookListPageSeleniumTest extends AbstractSelenium {

	private static final String ALL_BOOKS = "";
	private static final String SEARCHED_TITLE_PREFIX = "p";
	private static final int INDEX_OF_BOOK_TO_DELETE = 0;
	private static final int INDEX_OF_EDITED_BOOK = 0;

	private BookListPage bookListPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		bookListPage = openBookList();
	}

	@Test
	public void shouldCheckIfSearchTextCanBeEmpty() {
		// given
		// when
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		List<BookTableRow> foundBooks = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(bookListPage.isBookSearchButtonClickable()).isTrue();
		Assertions.assertThat(foundBooks).isNotNull().isNotEmpty();
	}

	@Test
	public void shouldCheckIfSearchTextCanBeFiled() {
		// given
		String titleQuery = SEARCHED_TITLE_PREFIX;
		// when
		bookListPage.setBookSearchTitle(titleQuery).clickBookSearchButton();
		List<BookTableRow> foundBooks = bookListPage.getBooksSearchResult();
		// then
		Assertions.assertThat(bookListPage.isBookSearchButtonClickable()).isTrue();
		Assertions.assertThat(foundBooks).isNotNull().isNotEmpty();
	}
	
	@Test
	public void shouldCheckIfAddBookCanBeClicked() {
		// given
		// when
		BookAddPage bookAddPage = bookListPage.clickBookAddButton();
		// then
		Assertions.assertThat(bookAddPage).isNotNull();
	}
	
	@Test
	public void shouldCheckIfDeleteCanBeClicked() {
		// given
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		// when
		BookTableRow bookToDelete = bookListPage.getBooksSearchResult().get(INDEX_OF_BOOK_TO_DELETE);
		// then
		Assertions.assertThat(bookToDelete.isDeleteBookButtonClickable()).isTrue();
	}
	
	@Test
	public void shouldCheckIfEditBookCanBeClicked() {
		// given
		bookListPage.setBookSearchTitle(ALL_BOOKS).clickBookSearchButton();
		// when
		BookTableRow bookToChange = bookListPage.getBooksSearchResult().get(INDEX_OF_EDITED_BOOK);
		// then
		Assertions.assertThat(bookToChange.isEditBookButtonClickable()).isTrue();
	}
}