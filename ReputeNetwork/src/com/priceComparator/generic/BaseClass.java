package com.priceComparator.generic;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.priceComparator.pom.clearTripFlightListPage;
import com.priceComparator.pom.clearTripHomePage;
import com.priceComparator.pom.paytmFlightlistPage;
import com.priceComparator.pom.paytmHomePage;

public class BaseClass 
{
	public static WebDriver driver=new ChromeDriver();
	public WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	public FileUtility fileUtils= new FileUtility();
	public clearTripFlightListPage cleartripflightpage = new clearTripFlightListPage(driver);
	public clearTripHomePage cleartriphomepage = new clearTripHomePage(driver);
	public paytmFlightlistPage paytmflightpage = new paytmFlightlistPage(driver);
	public paytmHomePage paytmhomepage =new paytmHomePage(driver);
	@BeforeMethod
	public void configBeforeMethod() throws IOException 
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(fileUtils.readDataFromPropertyFile("clearTripUrl"));
	}
	@AfterMethod
	public void configAfterMethod() 
	{
		driver.manage().window().minimize();
		driver.quit();
	}


}
