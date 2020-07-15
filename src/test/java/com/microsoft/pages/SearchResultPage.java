package com.microsoft.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

	private String firstElementPrice;
	
	public SearchResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
	@FindAll({@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Software')]/parent::h2/parent::div//ul//li//span[@itemprop='price']")})
	public List<WebElement> pricesResultList;
	
	@FindBy(how = How.XPATH, using = "//button[@id='R1MarketRedirect-close']")
	public WebElement randomModal;
	
	public void printThreeFirstPrices() {
		try {
			randomModal.click();
		} catch (Exception e) {
			System.out.println("Continue... details page");
		}
		
		System.out.println("\n |-------- Three First Prices --------| \n");
//		System.out.println("|-- Size => " + pricesResultList.size() + " --|");
		if (pricesResultList.size() > 0) {
			firstElementPrice = pricesResultList.get(0).getText(); 
			for (int i = 0; i < 3; i++) {
				System.out.println(pricesResultList.get(i).getText());
			}
		}
		System.out.println("Fist element price: " + firstElementPrice);
		System.out.println("\n |-------- Three First Prices --------| \n");	
	}
	
	public void clickOnFirstResult() {
		pricesResultList.get(0).click();
	}
	
	public String getfirstElementPrice() {
		return firstElementPrice;
	}
}
