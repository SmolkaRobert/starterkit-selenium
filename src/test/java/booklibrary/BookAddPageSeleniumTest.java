package booklibrary;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import booklibrary.abstracts.AbstractSelenium;
import booklibrary.pages.BookAddPage;

public class BookAddPageSeleniumTest extends AbstractSelenium {

	private static final int FIRST_AUTHOR_INDEX = 0;
	private static final String LAST_NAME = "Lovecraft";
	private static final String FIRST_NAME = "Howard";
	private static final String TITLE = "Zew Cthulhu";
	private BookAddPage bookAddPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		bookAddPage = openBookList().clickBookAddButton();
	}

	@Test
	public void shouldCheckIfLastNameIsRequired() {
		// given
		// when
		bookAddPage.setTitle(TITLE).setFirstName(FIRST_AUTHOR_INDEX, FIRST_NAME).clickSaveBookButton();
		// then
		Assertions.assertThat(bookAddPage.hasError()).isTrue();
	}

	@Test
	public void shouldCheckIfFirstNameIsRequired() {
		// given
		// when
		bookAddPage.setTitle(TITLE).setLastName(FIRST_AUTHOR_INDEX, LAST_NAME).clickSaveBookButton();
		// then
		Assertions.assertThat(bookAddPage.hasError()).isTrue();
	}

	@Test
	public void shouldCheckIfTitleIsRequired() {
		// given
		// when
		bookAddPage.setFirstName(FIRST_AUTHOR_INDEX, FIRST_NAME).setLastName(FIRST_AUTHOR_INDEX, LAST_NAME)
				.clickSaveBookButton();
		// then
		Assertions.assertThat(bookAddPage.hasError()).isTrue();
	}

	@Test
	public void shouldAddBook() {
		// given
		// when
		bookAddPage.setTitle(TITLE).setFirstName(FIRST_AUTHOR_INDEX, FIRST_NAME)
				.setLastName(FIRST_AUTHOR_INDEX, LAST_NAME).clickSaveBookButton();
		// then
		Assertions.assertThat(bookAddPage.hasError()).isFalse();
	}
	
	@Test
	public void shouldNotBePossibleToAddNewAuthorWithoutTitle() {
		// given
		// when
		bookAddPage.setFirstName(FIRST_AUTHOR_INDEX, FIRST_NAME).setLastName(FIRST_AUTHOR_INDEX, LAST_NAME);
		// then
		Assertions.assertThat(bookAddPage.isPossibleToAddNextAuthor()).isFalse();
	}
	
	@Test
	public void shouldNotBePossibleToAddNewAuthorWithoutFirstAuthorsFirstName() {
		// given
		// when
		bookAddPage.setTitle(TITLE).setLastName(FIRST_AUTHOR_INDEX, LAST_NAME);
		// then
		Assertions.assertThat(bookAddPage.isPossibleToAddNextAuthor()).isFalse();
	}
	
	@Test
	public void shouldNotBePossibleToAddNewAuthorWithoutFirstAuthorsLastName() {
		// given
		// when
		bookAddPage.setTitle(TITLE).setFirstName(FIRST_AUTHOR_INDEX, FIRST_NAME);
		// then
		Assertions.assertThat(bookAddPage.isPossibleToAddNextAuthor()).isFalse();
	}
	
	@Test
	public void shouldBePossibleToAddNewAuthor() {
		// given
		// when
		bookAddPage.setTitle(TITLE).setFirstName(FIRST_AUTHOR_INDEX, FIRST_NAME)
		.setLastName(FIRST_AUTHOR_INDEX, LAST_NAME);
		// then
		Assertions.assertThat(bookAddPage.isPossibleToAddNextAuthor()).isTrue();
	}

}