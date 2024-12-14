package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccountLink;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerLink;

	public void clickMyAccount() {
		myAccountLink.click();
	}

	public void clickRegisterLink() {
		registerLink.click();
	}

}
