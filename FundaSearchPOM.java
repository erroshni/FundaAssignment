/**
 PAGE OBJECT MODEL FOR FINDING THE WEB ELEMENTS 
 */
package funda.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FundaSearchPOM {
	
	WebDriver driver;
	
	// Define all Page Web Elements
	By cookie = By.className("cookie-policy-buttons");
	By koop = By.xpath("//*[@id=\"content\"]/div[1]/div[3]/form/nav/ul/li[1]");
	By huur = By.xpath("//*[@id=\"content\"]/div[1]/div[3]/form/nav/ul/li[2]");
	By nieuwbouw = By.xpath("//*[@id=\"content\"]/div[1]/div[3]/form/nav/ul/li[3]");
	By plaats = By.name("filter_location");
	By autoplaats = By.xpath("//*[@id=\"autocomplete-list\"]/li");
	By distance = By.name("filter_Straal");
	By selectdistance = By.xpath("//*[@id=\"Straal\"]/option");
    By minimum = By.id("range-filter-selector-select-filter_koopprijsvan");
    By minimumcustom = By.xpath("//*[@id='content']/div[1]/div[3]/form/div[1]/div/fieldset[2]/div[1]/div/div[2]/div/input");
    By selectminimum = By.xpath("//*[@id=\"range-filter-selector-select-filter_koopprijsvan\"]/option");
    By maximum = By.id("range-filter-selector-select-filter_koopprijstot");
    By maximumcustom = By.xpath("//*[@id=\"content\"]/div[1]/div[3]/form/div[1]/div/fieldset[2]/div[2]/div/div[2]/div/input");
    By selectmaximum = By.xpath("//*[@id=\"range-filter-selector-select-filter_koopprijstot\"]/option");
	By search = By.className("button-primary-alternative");
	By searchresult = By.className("search-output-result-count");
	By lastsearch = By.className("link-alternative");
	
	public FundaSearchPOM(WebDriver driver) //Constructor class
	{
		this.driver = driver;
	}
	
	// Method to accept cookies
	public void acceptCookie()
	{
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.presenceOfElementLocated(cookie)); //Explicit Wait to prevent No Such Element Found error.
		driver.findElement(cookie).click();
	}
	
	// This method clicks the Huur tab
	public void clickHuur()
	{	
		driver.findElement(huur).click();
	}

	// This method selects the search string based on passed parameter (userPlaats) and index
	public void enterPlaats(String userPlaats, int index) throws InterruptedException
	{	
		driver.findElement(plaats).sendKeys(userPlaats);
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.visibilityOfElementLocated(autoplaats));
		List<WebElement> autoSuggest = driver.findElements(autoplaats);
		System.out.println("Test List Index " + autoSuggest.get(0).getText());
		autoSuggest.get(index).click();
	}

	// This method selects the range/distance based on passed index parameter for distance.
	public void enterDistance(int index)
	{	
		driver.findElement(distance).click();		
		List<WebElement> range = driver.findElements(selectdistance);
		range.get(index).click();
	}
	
	// This method selects minimum price from the dropdown or enters the value for a custom price. 
	public void enterminValue(String minval)
	{
	    int exist = 0;
		driver.findElement(minimum).click();		
		List<WebElement> minprice = driver.findElements(selectminimum);
		for(int i=0;i<minprice.size();i++)
		{			
			if(minprice.get(i).getAttribute("value").equals(minval))
			{
				System.out.println("Value already exists in minprice List");
				minprice.get(i).click();
				exist = 1;
			}
		
		}
		if (exist != 1)
		{
			minprice.get(0).click();
			driver.findElement(minimumcustom).sendKeys(minval);
		}
	}

	// This method selects maximum price from the dropdown or enters the value for a custom price.
	public void entermaxValues(String maxval)
	{
	    int exist = 0;
		driver.findElement(maximum).click();		
		List<WebElement> maxprice = driver.findElements(selectmaximum);
		for(int i=0;i<maxprice.size();i++)
		{			
			if(maxprice.get(i).getAttribute("value").equals(maxval))
			{
				System.out.println("Value already exists in maxprice List");
				maxprice.get(i).click();
				exist = 1;
			}
		}
		if (exist != 1)
		{
			maxprice.get(0).click();
			driver.findElement(maximumcustom).sendKeys(maxval);
		}
	}

	// This method clicks the Koop tab
	public void clickKoop()
	{
	
		driver.findElement(koop).click();
	}
	
	// This method clicks the Nieuwbouw tab
	public void clicknieuwbouw()
	{
		driver.findElement(nieuwbouw).click();
	}
		
	// This method submits search and returns the URL of the search result page	
	public String getSearchResult()
	{
		driver.findElement(search).click();
		WebElement result = driver.findElement(searchresult);
		String searchresult = result.getText();
		System.out.println("Searched result is " + searchresult);
		String searchURL = driver.getCurrentUrl();
		driver.navigate().back();
		driver.navigate().refresh();
		return searchURL;
	}

	// This method checks that the Last search link is correctly displayed by comparing the URL from the last search result page to the link URL. 
	public void checkLastSearch(String searchPageURL)
	{
		String lastSeachedCriteriaURL = driver.findElement(lastsearch).getAttribute("href");
		System.out.println("Last Search Result Page URL : " + searchPageURL);
		System.out.println("Last Search Criteria Link URL : " + lastSeachedCriteriaURL);
		if (searchPageURL.equals(lastSeachedCriteriaURL))
			System.out.println("Last Search Criteria is correctly displayed");
	}
  }
	

