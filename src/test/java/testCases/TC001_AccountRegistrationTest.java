package testCases;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.text.RandomStringGenerator;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups={"Sanity", "Master"})
	public void verify_account_registration() {

		logger.info("****Starting test case 001****");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegisterLink();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

		regpage.setFirstname(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setTelephoneNumber(randomNum());

		String password = randomAlphaNumWithSpecial();

		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.selectNewsletter();
		regpage.checkPolicy();
		regpage.clickContinuebtn();

		logger.info("****validating test case 001****");
		String confmsg = regpage.getRegistrationMessage();
//		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}

	}

}
