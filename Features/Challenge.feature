Feature: Validate total price amount of products
	
	Scenario: Verification of the total price amount in cart
		Given The user open the browser and launch the application
		And Validate menu items are present
		When Click on Windows menu item
		Then Verify Windows Page is opened 
		And Click on "Windows 10" button
		And Print all Elements of the dropdown
		And Click on Search button
		And Search "Visual Studio"
		And Print the price for the three first elements listed in Software result list
		And Click on the first one to go to the details page
		Then Validate detail page is opened
		And Validate both prices are the same
		And Click on Add to Cart button
		And Verify all three prices amounts are the same
		And Select "20" items
		Then Validate the total amount is the multiplication of unit price per items amount
		And Quit browser