package com.microsoft.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowsPage {
	public WebDriverWait wait;
	
	public WindowsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@FindBy(how = How.ID, using = "home-mosaic")
	public WebElement mosaicHomePictures;
	
	@FindBy(how = How.XPATH, using = "//a[@href='https://www.microsoft.com/en-us/windows']/span[text()='Windows']")
	public WebElement windowsLogo;

	@FindBy(how = How.XPATH, using = "//section[@id='static-banner']//a[text()='Shop Windows 10 PCs on sale. SAVE NOW > ']")
	public WebElement shopWindowsLink;
	
	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='nested-menu uhf-menu-item']//button[text()='Windows 10']")
	public WebElement windowsMenuItem;

	@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='nested-menu uhf-menu-item']//button[text()='Windows 10']/parent::div/ul")
	public WebElement windowsList;

	@FindAll({@FindBy(how = How.XPATH, using = "//nav[@id='uhf-g-nav']//li[@class='nested-menu uhf-menu-item']//button[text()='Windows 10']/parent::div/ul/li/a")})
	public List<WebElement> windowsDropdownElementsList;

	@FindBy(how = How.ID, using = "search")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//a[@href='https://myapplications.microsoft.com/' and contains(text(),'My Apps')]")
	public WebElement randomPage;
	
	public void verifyWindowsPageIsOpened() {
		wait.until(ExpectedConditions.visibilityOf(mosaicHomePictures));
		assertTrue(mosaicHomePictures.isDisplayed());
	}
	
	public void clickOnWindowsMenu() {
		windowsMenuItem.click();
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public void printWindowsListElements() {
		System.out.println("\n |----- Elements in the dropdown -----| \n");
//		System.out.println("|-- Size => " + windowsDropdownElementsList.size() + " --|");
		if (windowsDropdownElementsList.size() > 0) {		
			for (int i = 0; i < windowsDropdownElementsList.size(); i++) {
				System.out.println(windowsDropdownElementsList.get(i).getText());
			}
		}
		System.out.println("\n |----- Elements in the dropdown -----| \n");
	}
}
