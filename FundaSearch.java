/**
TEST CASES FOR FUNDA SEARCH COMPONENT
- To capture and manage test cases, cucumber or testNG like frameworks can be used)
- Maven can be used as a build tool.
 */
package funda.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FundaSearch {
	public static void main(String[] args) throws InterruptedException {
		
		/* Create a new instance of the Chrome driver */
		WebDriver driver = new ChromeDriver();
		String searchPageURL;

		/* Open funda.nl home page */
		driver.get("https://www.funda.nl/");
		driver.manage().window().maximize();
		
		/* Create an object based on POM (FundaSearch) */
		FundaSearchPOM searchComponent = new FundaSearchPOM(driver);
		
		/* Accept page cookie */
		searchComponent.acceptCookie();
		
		/* Scenario 1 - Huur search: Street - Raphaelstraat. Range - 2 KM. Minimum Price - Default. Maximum Price - Default */
		searchComponent.clickHuur(); 
		searchComponent.enterPlaats("Raphael", 4);
		searchComponent.enterDistance(2); // index 2 for 2 KM
		searchPageURL = searchComponent.getSearchResult();
		searchComponent.checkLastSearch(searchPageURL);
		
		/* Scenario 2 - Koop search: City - Amsterdam-Duivendrecht. Range - 0 KM. Minimum Price - 37000 (custom).
		 * Maximum Price - 13000 (custom). Minimum Price > Maximum Price */
		searchComponent.clickKoop();
		searchComponent.enterPlaats("Amster", 2);
		searchComponent.enterDistance(0);
		searchComponent.enterminValue("37000");
		searchComponent.entermaxValues("13000");
		searchPageURL = searchComponent.getSearchResult();
		searchComponent.checkLastSearch(searchPageURL);
		
		/* Scenario 3 - Koop search: Post Code - 1077. Range - 0 KM. Minimum Price - 23000 (custom).
		 * Maximum Price - 50000 (enumeration) */
		searchComponent.clickKoop();
		searchComponent.enterPlaats("1077", 0);
		searchComponent.enterDistance(0);
		searchComponent.enterminValue("23000");
		searchComponent.entermaxValues("50000");
		searchPageURL = searchComponent.getSearchResult();
		searchComponent.checkLastSearch(searchPageURL);
				
		/* Scenario 4 - Koop search: Post Code - 1077PV. Range - 0 KM. Minimum Price - Default. Maximum Price - 67000 (custom) */
		searchComponent.clickKoop();
		searchComponent.enterPlaats("1077PV", 0);
		searchComponent.enterDistance(5);
		searchComponent.entermaxValues("525000");
		searchPageURL = searchComponent.getSearchResult();
		searchComponent.checkLastSearch(searchPageURL);
		
		/* Scenario 5 - NieuwBouw search: Gemeente - Eindhoven. Range - 5 KM */
		searchComponent.clicknieuwbouw();
		searchComponent.enterPlaats("Eindho", 1);
		searchComponent.enterDistance(5); // index 5 for 15 KM
		searchPageURL = searchComponent.getSearchResult();
		searchComponent.checkLastSearch(searchPageURL);
} 
}
