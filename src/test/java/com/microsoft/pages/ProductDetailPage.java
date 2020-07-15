package com.microsoft.pages;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {
	public WebDriverWait wait;
	JavascriptExecutor executor;
	
	public ProductDetailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		executor = (JavascriptExecutor) driver;
	}
	
	@FindBy(how = How.XPATH, using = "//div[@aria-label='Sign up for Microsoft Store Newsletter']")
	public WebElement newsletterModal;
	
	@FindBy(how = How.XPATH, using = "//div[@aria-label='Sign up for Microsoft Store Newsletter']//div[@aria-label='Close']")
	public WebElement newsletterModalCloseButton;
	
	@FindBy(how = How.ID, using = "buttonPanel_AddToCartButton")
	public WebElement addToCartButton;
	
	@FindBy(how = How.ID, using = "ProductPrice_productPrice_PriceContainer")
	public WebElement priceProductContainer;
	
	@FindBy(how = How.XPATH, using = "//img[contains(@alt,'Visual Studio')]")
	public WebElement visualStudioImg;
	
	@FindBy(how = How.XPATH, using = "//div[@role='progressbar']")
	public WebElement progressAnimation;
	
	public void validatePageIsDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(newsletterModal));
		if (newsletterModal.isDisplayed()) {
			newsletterModalCloseButton.click();
		}
		wait.until(ExpectedConditions.visibilityOf(visualStudioImg));
		assertTrue(addToCartButton.isDisplayed());
		assertTrue(priceProductContainer.isDisplayed());
		assertTrue(visualStudioImg.isDisplayed());
	}
	
	public String getProductPrice() {
		return priceProductContainer.getText();
	}
	
	public void clickOnAddToCart() {
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
	    executor.executeScript("arguments[0].click();", addToCartButton);
	}
}
