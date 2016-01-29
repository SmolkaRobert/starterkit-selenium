package booklibrary.abstracts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import booklibrary.pages.BookListPage;

@RunWith(SeleniumScreenshotJUnit4Runner.class)
public class AbstractSelenium {
	private WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(AbstractSelenium.class);
	protected WebDriverWait wait;

	@Before
	public void setUp() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("init.accept_languages", "en");
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	
	public BookListPage openBookList() {
		return PageFactory.initElements(driver, BookListPage.class);
	}
	
	public void takeScreenshot() throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\tmp\\"+screenshot.getName());
		FileUtils.copyFile(screenshot, destFile);
		logger.info(String.format("[[ATTACHMENT|%s]]", destFile.getAbsolutePath()));
	}
	
	@After
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
