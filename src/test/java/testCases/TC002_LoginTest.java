package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Regression", "Master"})
	public void verify_login() {
		logger.info("Starting test case: Login test");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginBtn();
		
		MyAccountPage macp = new MyAccountPage(driver);
		boolean targetPage = macp.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true, "Login Failed");
		Assert.assertTrue(targetPage);
		} catch(Exception e) {
			Assert.fail();
		}
		logger.info("Test case completed");
		
	}

}
