package com.priceComparator.testscript;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;
import com.priceComparator.generic.FileUtility;

public class PriceComparatorAssignment {
	@Test
	public void testPriceComparator() throws InterruptedException, IOException {
		FileUtility fileUtils=new FileUtility();
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the date");
		int date = s.nextInt();
		System.out.println("Enter the month");
		String month = s.next();
		System.out.println("Enter the year");
		int year=s.nextInt();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		String clearTripUrl = fileUtils.readDataFromPropertyFile("clearTripUrl");
		driver.get(clearTripUrl);
		String fromLocation = fileUtils.readDataFromPropertyFile("from");
		driver.findElement(By.xpath("//input[@placeholder='Where from?']")).sendKeys(fromLocation);
		driver.findElement(By.xpath("//p[contains(text(),'New Delhi')]")).click();
		String destinationLocation = fileUtils.readDataFromPropertyFile("to");
		driver.findElement(By.xpath("//input[@placeholder='Where to?']")).sendKeys(destinationLocation);
		driver.findElement(By.xpath("//p[contains(text(),'Chandigarh')]")).click();
		driver.findElement(By.xpath("(//div[contains(@class,'Calender')]/button)[1]")).click();
		Actions a = new Actions(driver);
		a.scrollByAmount(0,300).perform();
		while(true) {
		try {
		driver.findElement(By.xpath("//div[text()='"+month+" "+year+"']/../..//div[text()='"+date+"']")).click();	
		break;
		}catch(NoSuchElementException ele) {
			driver.findElement(By.cssSelector("svg[data-testid='rightArrow']")).click();
		}
		}
		driver.findElement(By.xpath("//span[text()='Search flights']")).click();
		driver.findElement(By.xpath("//span[text()='Arrival']")).click();
		List<WebElement> operator = driver.findElements(By.xpath("//p[text()='non-stop']/../../../../../div[2]/div[1]/div/div/div[3]/p[1]"));
		List<WebElement> flightNum = driver.findElements(By.xpath("//p[text()='non-stop']/../../../../../div[2]/div[1]/div/div/div[3]/p[2]"));
		List<WebElement> allPrices = driver.findElements(By.xpath("//p[text()='non-stop']/../../../../../..//div[@data-ct-handle='solutionPrice']/div/p[contains(@class,'neutral')]"));
		int count = allPrices.size();
		String csvFile = "data.csv";
		FileWriter writer = new FileWriter(csvFile);
		String[] header = {"Flight Operator ", "Flight Number", "Price on Cleartrip", "Price on PayTm"};
		CSVWriter csvWriter = new CSVWriter(writer);
		csvWriter.writeNext(header);
		String[][]data=new String[count][4];
		for(int i=0;i<count;i++) {
			String flightName = operator.get(i).getText();
			String flightNumber = flightNum.get(i).getText();
			String[] priceInClearTrip = allPrices.get(i).getText().split("₹");
			data[i][0]=flightName;
			data[i][1]=flightNumber;
			data[i][2]=priceInClearTrip[1];
		}
		String paytmUrl = fileUtils.readDataFromPropertyFile("paytmUrl");
		driver.navigate().to(paytmUrl);
		driver.findElement(By.id("from")).click();
		driver.findElement(By.id("text-box")).clear();
		driver.findElement(By.xpath("//div[text()='Delhi']")).click();
		driver.findElement(By.id("to")).click();
		driver.findElement(By.id("text-box")).sendKeys(Keys.CONTROL+"A");
		driver.findElement(By.id("text-box")).sendKeys(destinationLocation);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Chandigarh']")).click();
		driver.findElement(By.xpath("//span[text()='Departure Date']/..")).click();
		while(true) {
		try {
		driver.findElement(By.xpath("//td[text()='"+month+" "+year+"']/../../..//div[@class='calendar__day'or@class='calendarday calendar_holiday']/div[text()='"+date+"']")).click();
		break;
		}catch (ElementNotInteractableException e) {
		driver.findElement(By.xpath("//i[@class='gA7KZ _3nECU']")).click();
		}
		}
		driver.findElement(By.id("flightSearch")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Arrival']")));
		driver.findElement(By.xpath("//span[text()='Arrival']")).click();
		List<WebElement> allPrice = driver.findElements(By.xpath("//span[text()='Non-Stop ']/../../div[5]/div[1]"));
		for(int i=0;i<count;i++) {
			 String[] priceInPayTM = allPrice.get(i).getText().split("₹");
			data[i][3]=priceInPayTM[1];
		}
		for (String[] d: data) {
				csvWriter.writeNext(d);
		}	
		csvWriter.close();
		driver.manage().window().minimize();
		driver.quit();
	}
}
