package com.microsoft.pages;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	public WebDriverWait wait;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Office']")
	public WebElement officeMenuItem;
	
	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Windows']")
	public WebElement windowsMenuItem;

	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Surface']")
	public WebElement surfaceMenuItem;
	
	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Xbox']")
	public WebElement xboxMenuItem;
	
	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Deals']")
	public WebElement dealsMenuItem;
	
	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='single-link js-nav-menu uhf-menu-item']/a[text()='Support']")
	public WebElement supportMenuItem;

	public void validateMenuItemsPresence() {
		wait.until(ExpectedConditions.visibilityOf(officeMenuItem));
		wait.until(ExpectedConditions.visibilityOf(supportMenuItem));
		assertTrue(officeMenuItem.isDisplayed());
		assertTrue(windowsMenuItem.isDisplayed());
		assertTrue(surfaceMenuItem.isDisplayed());
		assertTrue(xboxMenuItem.isDisplayed());
		assertTrue(dealsMenuItem.isDisplayed());
		assertTrue(supportMenuItem.isDisplayed());
	}
	
	public void clickOnWindowsMenuItem() {
		windowsMenuItem.click();
	}
}
