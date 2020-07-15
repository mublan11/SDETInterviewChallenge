package com.microsoft.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	public SearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@FindBy(how = How.CSS, using = "input[type='search']")
	public WebElement searchInput;
	
	@FindBy(how = How.XPATH, using = "//button[@id='R1MarketRedirect-close']")
	public WebElement randomLanguageModal;
	
	@FindBy(how = How.XPATH, using = "//button[@aria-label='Cancelar']")
	public WebElement randomLanguageModalCloseButton;

	@FindBy(how = How.XPATH, using = "//a[@href='https://myapplications.microsoft.com/' and contains(text(),'My Apps')]")
	public WebElement randomSearchPage;
	
	public void searchItem(String text) {
		try {
			randomLanguageModalCloseButton.click();
			System.out.println("\n Clicked... searchItem \n");
		} catch (Exception e) {
			System.out.println("\n Continue... searchItem \n");
		}
		
		searchInput.sendKeys(text);
		searchInput.sendKeys(Keys.RETURN);
	}
}
