package com.priceComparator.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class clearTripFlightListPage 
{
	@FindBy(xpath = "//span[text()='Arrival']")
	private WebElement arrivalBtn;
	
	@FindBy(xpath = "//p[text()='non-stop']/../../../../../div[2]/div[1]/div/div/div[3]/p[1]")
	private List<WebElement> operator;
	
	@FindBy(xpath = "//p[text()='non-stop']/../../../../../div[2]/div[1]/div/div/div[3]/p[2]")
	private List<WebElement> flightNum;
	
	@FindBy(xpath = "//p[text()='non-stop']/../../../../../..//div[@data-ct-handle='solutionPrice']/div/p[contains(@class,'neutral')]")
	private List<WebElement> allPrices;
	
	public clearTripFlightListPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getArrivalBtn() {
		return arrivalBtn;
	}

	public List<WebElement> getOperator() {
		return operator;
	}

	public List<WebElement> getFlightNum() {
		return flightNum;
	}

	public List<WebElement> getAllPrices() {
		return allPrices;
	}
	
	

}
