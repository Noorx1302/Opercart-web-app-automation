package testBase;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups= {"Sanity", "Regression", "Datadriven", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(browser.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser"); return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL")); //reading URL from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity", "Regression", "Datadriven", "Master"})
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
	
	public String captureScreen(String tname) throws IOException{
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots"+ tname + timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}
