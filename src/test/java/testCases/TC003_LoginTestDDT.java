package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void loginTestDDT(String email, String password, String exp) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLoginBtn();
		
		MyAccountPage macp = new MyAccountPage(driver);
		boolean targetPage = macp.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage == true) {
				macp.clickLogoutBtn();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetPage == true) {
				macp.clickLogoutBtn();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}

	}
	
	
	
	
	
	
	
	
}
