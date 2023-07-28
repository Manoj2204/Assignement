package com.priceComparator.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class clearTripHomePage 
{
	@FindBy(xpath = "//input[@placeholder='Where from?']")
	private WebElement WhereFromTB;
	
	@FindBy(xpath = "//p[contains(text(),'New Delhi')]")
	private WebElement NewDelhiSuggestionOption;
	
	@FindBy(xpath = "//input[@placeholder='Where to?']")
	private WebElement WhereToTB;
	
	@FindBy(xpath = "//p[contains(text(),'Chandigarh')]")
	private WebElement ChandigarhSuggestionOption;
	
	@FindBy(xpath = "//div[contains(@class,'Calender')]/button[1]")
	private WebElement CalenderBtn;
	

	
	@FindBy(css = "svg[data-testid='rightArrow']")
	private WebElement RightarrowBtn;
	
	@FindBy(xpath="//span[text()='Search flights']")
	private WebElement searchFlightBtn;
	
	
	
	public clearTripHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getWhereFromBtn() {
		return WhereFromTB;
	}

	public WebElement getNewDelhiSuggestionOption() {
		return NewDelhiSuggestionOption;
	}

	public WebElement getWhereToBtn() {
		return WhereToTB;
	}

	public WebElement getChandigarhSuggestionOption() {
		return ChandigarhSuggestionOption;
	}

	public WebElement getCalenderBtn() {
		return CalenderBtn;
	}


	public WebElement getRightarrowBtn() {
		return RightarrowBtn;
	}

	public WebElement getSearchFlightBtn() {
		return searchFlightBtn;
	}

}
