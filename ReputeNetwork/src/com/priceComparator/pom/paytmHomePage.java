package com.priceComparator.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paytmHomePage
{
	@FindBy(id="from")
	private WebElement fromBtn;
	@FindBy(id="flightSearch")
	private WebElement flightSearchBtn;
	public WebElement getFlightSearchBtn() {
		return flightSearchBtn;
	}

	@FindBy(id="text-box")
	private WebElement clearfromTextBox;
	
	@FindBy(xpath = "//div[text()='Delhi']")
	private WebElement delhiSuggestion;
	
	@FindBy(id="to")
	private WebElement toBtn;
	
	@FindBy(xpath="//i[@class='gA7KZ _3nECU']")
	private WebElement arrowBtn;
	public WebElement getArrowBtn() {
		return arrowBtn;
	}

	public WebElement getFromBtn() {
		return fromBtn;
	}

	public WebElement getClearfromTextBox() {
		return clearfromTextBox;
	}

	public WebElement getDelhiSuggestion() {
		return delhiSuggestion;
	}

	public WebElement getToBtn() {
		return toBtn;
	}

	public WebElement getClearTotextBtn() {
		return clearTotextBtn;
	}

	public WebElement getToSenddestination() {
		return toSenddestination;
	}

	public WebElement getChandigarhSuggestion() {
		return ChandigarhSuggestion;
	}

	public WebElement getCalenderDepartureBtn() {
		return CalenderDepartureBtn;
	}

	@FindBy(id="text-box")
	private WebElement clearTotextBtn;
	
	@FindBy(id="text-box")
	private WebElement toSenddestination;
	
	@FindBy(xpath = "//div[text()='Chandigarh']")
	private WebElement ChandigarhSuggestion;
	
	@FindBy(xpath = "//span[text()='Departure Date']/..")
	private WebElement CalenderDepartureBtn;
	
	public paytmHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	

}
