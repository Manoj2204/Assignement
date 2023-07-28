package com.priceComparator.testscript;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;
import com.priceComparator.generic.BaseClass;

public class PriceComparator extends BaseClass {
	@Test
public void testDemo() throws IOException, InterruptedException {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the date");
		int date = s.nextInt();
		System.out.println("Enter the month");
		String month = s.next();
		System.out.println("Enter the year");
		int year=s.nextInt();
		String fromLocation = fileUtils.readDataFromPropertyFile("from");
		cleartriphomepage.getWhereFromBtn().sendKeys(fromLocation);
		cleartriphomepage.getNewDelhiSuggestionOption().click();
		String destinationLocation = fileUtils.readDataFromPropertyFile("to");
		cleartriphomepage.getWhereToBtn().sendKeys(destinationLocation);
		cleartriphomepage.getChandigarhSuggestionOption().click();
		cleartriphomepage.getCalenderBtn().click();
		
		
		
		Actions a = new Actions(driver);
		a.scrollByAmount(0,300).perform();
		while(true) {
		try {
		driver.findElement(By.xpath("//div[text()='"+month+" "+year+"']/../..//div[text()='"+date+"']")).click();	
		break;
		}catch(NoSuchElementException ele) 
		{
			cleartriphomepage.getRightarrowBtn().click();
			//driver.findElement(By.cssSelector("svg[data-testid='rightArrow']")).click();
		}
		}
		cleartriphomepage.getSearchFlightBtn().click();
		cleartripflightpage.getArrivalBtn().click();
		List<WebElement> operators = cleartripflightpage.getOperator();
		List<WebElement> flightNum = cleartripflightpage.getFlightNum();
		List<WebElement> allPrices = cleartripflightpage.getAllPrices();
		int count = allPrices.size();
		String csvFile = "data.csv";
		FileWriter writer = new FileWriter(csvFile);
		String[] header = {"Flight Operator On Cleartrip ", "Flight Number", "Price on Cleartrip", "Flight Operator On Paytm" ,"Price on PayTm"};
		CSVWriter csvWriter = new CSVWriter(writer);
		csvWriter.writeNext(header);
		String[][]data=new String[count][5];
		for(int i=0;i<count;i++) {
			String flightName = operators.get(i).getText();
			String flightNumber = flightNum.get(i).getText();
			String[] priceInClearTrip = allPrices.get(i).getText().split("₹");
			data[i][0]=flightName;
			data[i][1]=flightNumber;
			data[i][2]=priceInClearTrip[1];
		}
		String paytmUrl = fileUtils.readDataFromPropertyFile("paytmUrl");
		driver.navigate().to(paytmUrl);
		paytmhomepage.getFromBtn().click();
		paytmhomepage.getClearfromTextBox().clear();
		paytmhomepage.getDelhiSuggestion().click();
		paytmhomepage.getToBtn().click();
		paytmhomepage.getClearfromTextBox().sendKeys(Keys.CONTROL+"A");
		paytmhomepage.getClearfromTextBox().sendKeys(destinationLocation);
		Thread.sleep(1000);
		paytmhomepage.getChandigarhSuggestion().click();
		paytmhomepage.getCalenderDepartureBtn().click();
		while(true) {
			try {
			driver.findElement(By.xpath("//td[text()='"+month+" "+year+"']/../../..//div[@class='calendar__day'or@class='calendarday calendar_holiday']/div[text()='"+date+"']")).click();
			break;
			}catch (ElementNotInteractableException e) {
				paytmhomepage.getArrowBtn().click();
			}
			}
		paytmhomepage.getFlightSearchBtn().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Arrival']")));
		paytmflightpage.getArrivalBtn().click();
		List<WebElement> allPrice = paytmflightpage.getAllPrice();
		List<WebElement> operator = paytmflightpage.getOperator();
		for(int i=0;i<count;i++) {
			 String[] priceInPayTM = allPrice.get(i).getText().split("₹");
			 String operatorName = operator.get(i).getText();
			data[i][3]=operatorName;
			data[i][4]=priceInPayTM[1];
		}
		for (String[] d: data) {
				csvWriter.writeNext(d);
		}
		csvWriter.close();
}
}
