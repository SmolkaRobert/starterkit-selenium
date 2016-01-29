package booklibrary;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import booklibrary.abstracts.AbstractSelenium;
import booklibrary.pages.BookEditPage;

public class BookEditPageSeleniumTest extends AbstractSelenium {

	private static final String NEW_TITLE = "Diuna";
	private static final String EMPTY_TITLE = "";
	private static final String ALL_BOOKS = EMPTY_TITLE;
	private static final int INDEX_OF_EDITED_BOOK = 0;

	private BookEditPage bookEditPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		bookEditPage = openBookList().setBookSearchTitle(ALL_BOOKS).clickBookSearchButton()
									.getBooksSearchResult().get(INDEX_OF_EDITED_BOOK).clickEditBookButton();
	}

	@Test
	public void shouldCheckIfNewTitleCanBeEmpty() {
		// given
		String titleToSet = EMPTY_TITLE;
		// when
		bookEditPage.setNewTitle(titleToSet);
		// then
		Assertions.assertThat(bookEditPage.hasError()).isTrue();
	}

	@Test
	public void shouldChangeTitle() {
		// given
		String titleToSet= NEW_TITLE;
		// when
		bookEditPage.setNewTitle(titleToSet);
		// then
		Assertions.assertThat(bookEditPage.hasError()).isFalse();
	}
}