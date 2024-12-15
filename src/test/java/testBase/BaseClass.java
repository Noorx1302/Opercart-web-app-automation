package testBase;

import java.time.Duration;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost/opencart/upload/index.php?route=account/success");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {

		RandomStringGenerator randomstr = new RandomStringGenerator.Builder().withinRange('a', 'z').get();
		return randomstr.generate(8);

	}

	public String randomNum() {

		RandomStringGenerator randomstr = new RandomStringGenerator.Builder().selectFrom('0', '9').get();
		return randomstr.generate(10);

	}

	public String randomAlphaNumWithSpecial() {
		RandomStringGenerator randomAlphaNumwithSpecial = new RandomStringGenerator.Builder()
				.selectFrom("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:',.<>?/"
						.toCharArray())
				.get();
		return randomAlphaNumwithSpecial.generate(10);
	}

}
