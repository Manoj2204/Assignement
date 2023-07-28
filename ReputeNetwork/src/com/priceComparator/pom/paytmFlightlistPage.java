package com.priceComparator.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paytmFlightlistPage 
{
	@FindBy(xpath = "//span[text()='Arrival']")
	private WebElement arrivalBtn;
	
	@FindBy(xpath = "//span[text()='Non-Stop ']/../../div[5]/div[1]")
	private List<WebElement> allPrice;
	
	@FindBy(xpath = "//span[text()='Non-Stop ']/../../div[1]/div[1]/div[1]/span")
	private List<WebElement> operator;
	
	public paytmFlightlistPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getArrivalBtn() {
		return arrivalBtn;
	}

	public List<WebElement> getAllPrice() {
		return allPrice;
	}

	public List<WebElement> getOperator() {
		return operator;
	}
	
	

}
