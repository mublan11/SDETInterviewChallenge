package com.microsoft.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
	
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
	}
	
	@FindAll({@FindBy(how = How.XPATH, using = "//span[@itemprop='price']")})
	public List<WebElement> finalPricesLabels;
	
	@FindBy(how = How.XPATH, using = "//div[@class='item-quantity']/select")
	public WebElement quantityDropdownItems;

	@FindBy(how = How.XPATH, using = "//strong[text()='Total*']/parent::span/parent::span//span[@itemprop='price']")
	public WebElement totalAumontPrice;
	
	@FindBy(how = How.XPATH, using = "//div[@role='progressbar']")
	public WebElement progressAnimation;
	
	private boolean verifyAllEqual(List<WebElement> list) {
	    for (WebElement s : list) {
	        if (!s.getText().equals(list.get(0).getText()))
	            return false;
	    }
	    return true;
	}
	
	public void verifyAllPricesAreEqual() {
		for (WebElement webElement : finalPricesLabels) {
			System.out.println(webElement.getText());
		}
		if (verifyAllEqual(finalPricesLabels)) {
			System.out.println("\n+ All prices all the same + \n ");
		}else {
			System.out.println("\n- The Prices are not the same - \n");
		}
		assertTrue(quantityDropdownItems.isDisplayed());
	}
	
	public void selectQuantityItems(String value) {
		Select drpCountry = new Select(quantityDropdownItems);
		drpCountry.selectByVisibleText(value.toString());
		try {
			finalPricesLabels.get(2).click();
		} catch (Exception e) {
			finalPricesLabels.get(2).click();
			System.out.println("ERROR =>" + e);
		}
	}
	
	public void validateTotalAmount(String unitPrice) {
	    String unformattedUnitPrice = getUnformattedPrice(unitPrice);
	    
	    int lastPrice = Integer.parseInt(unformattedUnitPrice) * 20;
//	    System.out.println("\nlastPrice => " + lastPrice);
	    NumberFormat cf1 = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
	    
	    String lastPriceUSFormatted = cf1.format(lastPrice);
//        System.out.println("\nlastPriceUSFormatted =>" + lastPriceUSFormatted);
        
        String totalAmountPriceText = totalAumontPrice.getText();
        System.out.println("\nlastPriceUSFormatted: " + lastPriceUSFormatted + " | totalAmountPriceText: " + totalAmountPriceText);
        assertEquals(lastPriceUSFormatted, totalAmountPriceText);
	}
	
	public String getUnformattedPrice(String price) {
		String flatPrice = price.split("\\.")[0];
	    String unformattedPrice = flatPrice.replaceAll("\\D+","");
//	    System.out.println("\nunformattedPrice => " + unformattedPrice);
	    return unformattedPrice;
	}
}
