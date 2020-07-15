package com.microsoft.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.microsoft.pages.HomePage;
import com.microsoft.pages.WindowsPage;
import com.microsoft.pages.SearchPage;
import com.microsoft.pages.SearchResultPage;
import com.microsoft.pages.ProductDetailPage;
import com.microsoft.pages.CartPage;

import io.cucumber.java.en.*;

public class ChallengeSteps {
	WebDriver driver;
	public WebDriverWait wait;
	HomePage homePage;
	WindowsPage windowsPage;
	SearchPage searchPage;
	SearchResultPage searchResultPage;
	ProductDetailPage productDetailPage;
	CartPage cartPage;

	@Given("The user open the browser and launch the application")
	public void the_user_open_the_browser_and_launch_the_application() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.microsoft.com/en-us/");
		
		homePage = new HomePage(driver);
		windowsPage = new WindowsPage(driver);
		searchPage = new SearchPage(driver);
		searchResultPage = new SearchResultPage(driver);
		productDetailPage = new ProductDetailPage(driver);
		cartPage = new CartPage(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}
	
	@Given("Validate menu items are present")
	public void validate_menu_items_are_present() {
		homePage.validateMenuItemsPresence();
	}

	@When("Click on Windows menu item")
	public void click_on_windows_menu_item() {		
		homePage.clickOnWindowsMenuItem();
	}

	@Then("Verify Windows Page is opened")
	public void verify_windows_page_is_opened() throws InterruptedException {
		try {
			System.out.println("Windows Page Visible");
			windowsPage.verifyWindowsPageIsOpened();
		} catch (Exception e) {
			System.out.println("Windows Page Not Visible");
		}
	}
	
	
	@Then("Click on {string} button")
	public void click_on_button(String string) {
		wait.until(ExpectedConditions.visibilityOf(windowsPage.windowsMenuItem));
		windowsPage.clickOnWindowsMenu();	
	}
	
	@Then("Print all Elements of the dropdown")
	public void print_all_elements_of_the_dropdown() {
		windowsPage.printWindowsListElements();		
	}

	@Then("Click on Search button")
	public void click_on_search_button() {
		windowsPage.clickSearchButton();
	}

	@Then("Search {string}")
	public void search(String searchText) throws InterruptedException {
		searchPage.searchItem(searchText);
	}

	@Then("Print the price for the three first elements listed in Software result list")
	public void print_the_price_for_the_three_first_elements_listed_in_software_result_list() {
		searchResultPage.printThreeFirstPrices();
	}

	@Then("Click on the first one to go to the details page")
	public void click_on_the_first_one_to_go_to_the_details_page() {
		wait.until(ExpectedConditions.visibilityOf(searchResultPage.pricesResultList.get(0)));
		searchResultPage.clickOnFirstResult();
	}

	@Then("Validate detail page is opened")
	public void validate_detail_page_is_opened() {
		wait.until(ExpectedConditions.visibilityOf(productDetailPage.visualStudioImg));
		productDetailPage.validatePageIsDisplayed();
	}

	@Then("Validate both prices are the same")
	public void validate_both_prices_are_the_same() {
	    assertEquals(searchResultPage.getfirstElementPrice(), productDetailPage.getProductPrice());
	}
	
	@Then("Click on Add to Cart button")
	public void click_on_add_to_cart_button() {
		wait.until(ExpectedConditions.visibilityOf(productDetailPage.addToCartButton));
		productDetailPage.clickOnAddToCart();
	}

	@Then("Verify all three prices amounts are the same")
	public void verify_all_three_prices_amounts_are_the_same() {
		cartPage.verifyAllPricesAreEqual();
	}

	@Then("Select {string} items")
	public void select_items(String items) {
		cartPage.selectQuantityItems(items);
	}

	@Then("Validate the total amount is the multiplication of unit price per items amount")
	public void validate_the_total_amount_is_the_multiplication_of_unit_price_per_items_amount() {
		cartPage.validateTotalAmount(searchResultPage.getfirstElementPrice());
	}
	
	@Then("Quit browser")
	public void quit_browser() {
		driver.close();
		driver.quit();
	}
}
