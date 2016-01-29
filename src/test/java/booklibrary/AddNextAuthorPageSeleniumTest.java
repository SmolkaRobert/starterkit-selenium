package booklibrary;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import booklibrary.abstracts.AbstractSelenium;
import booklibrary.pages.AddNextAuthorPage;

public class AddNextAuthorPageSeleniumTest extends AbstractSelenium {

	private static final int FIRST_AUTHOR_INDEX = 0;
	private static final String FIRST_AUTHOR_FIRST_NAME = "Arthur";
	private static final String FIRST_AUTHOR_LAST_NAME = "Clarke";
	private static final String SECOND_AUTHOR_FIRST_NAME = "Stephen";
	private static final String SECOND_AUTHOR_LAST_NAME = "Baxter";
	private static final String TITLE = "Oko czasu";
	private AddNextAuthorPage addNextAuthorPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		addNextAuthorPage = openBookList().clickBookAddButton()
				.setTitle(TITLE).
				setFirstName(FIRST_AUTHOR_INDEX, FIRST_AUTHOR_FIRST_NAME)
				.setLastName(FIRST_AUTHOR_INDEX, FIRST_AUTHOR_LAST_NAME).
				clickAddNextAuthorButton();
	}

	@Test
	public void shouldCheckIfFirstNameIsRequired() {
		//given
		//when
		addNextAuthorPage.setLastName(SECOND_AUTHOR_LAST_NAME);
		//then
		Assertions.assertThat(addNextAuthorPage.hasError()).isTrue();
	}
	
	@Test
	public void shouldCheckIfLastNameIsRequired() {
		//given
		//when
		addNextAuthorPage.setFirstName(SECOND_AUTHOR_FIRST_NAME);
		//then
		Assertions.assertThat(addNextAuthorPage.hasError()).isTrue();
	}
	
	@Test
	public void shouldAddNextAuthor() {
		//given
		//when
		addNextAuthorPage.setFirstName(SECOND_AUTHOR_FIRST_NAME).setLastName(SECOND_AUTHOR_LAST_NAME);
		//then
		Assertions.assertThat(addNextAuthorPage.hasError()).isFalse();
	}

}