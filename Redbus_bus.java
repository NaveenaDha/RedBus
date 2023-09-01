package demo;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;


public class Redbus_bus {
    WebDriver driver;
  WebDriverWait wait;
    public Redbus_bus()
    {
        System.out.println("Constructor: TestCases RedBUs Bus search");
        WebDriverManager.chromedriver().timeout(30).browserVersion("116.0.5845.0").setup();
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.redbus.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void endTest()
    {
        System.out.println("End Test: Closed RedBUs Bus search");
        driver.close();
        driver.quit();
    }

//bus icon
By busicon = By.xpath("(//ul/li)[1]");
//from
	By source = By.xpath("//input[@id='src']");
//list of source
	By sourcelist = By.xpath("//ul[@class='sc-dnqmqq eFEVtU']/li");
//to
	By destination = By.xpath("//input[@id='dest']");
//list of to
	By destlist = By.xpath("//ul[@class='sc-dnqmqq eFEVtU']/li");
//Date
	By date = By.xpath("//span[text()='Date']");
//search button
	By search = By.xpath("//button[@id='search_button']");
//buses available
	By busesavailable = By.xpath("//span[@class=\"f-bold busFound\"]");
	By buscnt = By.xpath("//div[@class=\"clearfix bus-item\"]");
	By busname = By.xpath("//div[@class=\"clearfix bus-item\"]/div/div/div/div[@class='travels lh-24 f-bold d-color']");
	By busfare = By.xpath("//div[@class=\"fare d-block\"]/span");
	By seatsavailable = By.xpath("//div[contains(@class,'seat-left m-top')]");


    public void clickbus() throws InterruptedException {
		driver.findElement(busicon).click();
		System.out.println("Bus Icon clicked successfully");
		Thread.sleep(3000);
	}
	
	public List<WebElement> getsource() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(source)).click();		
driver.findElement(source).sendKeys("Kar");
Thread.sleep(4000);
		List<WebElement> sourceOptions = driver.findElements(sourcelist);
System.out.println("Auto Suggest entered sourceList :" + sourceOptions.size());
for(WebElement sourceOption : sourceOptions)
{
	String srcname = sourceOption.getText();
	
	if(srcname.equals("Karur"))
	{System.out.println("Given Source name : " + srcname);
		sourceOption.click();
		Thread.sleep(3000);
		break;
	}
}
return sourceOptions;		
	}
	
	
	public List<WebElement> getdestiny() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(destination)).click();		
		driver.findElement(destination).sendKeys("Coim");
Thread.sleep(4000);
List<WebElement> destiOptions = driver.findElements(destlist);
System.out.println("Auto Suggest entered destinationList ::" + destiOptions.size());
for(WebElement destiOption : destiOptions)
{
	String destinyname = destiOption.getText();
	
	if(destinyname.equals("Coimbatore"))
	{System.out.println("Given Destination name : " + destinyname);
		destiOption.click();
		Thread.sleep(3000);
		break;
	}
}
return destiOptions;		
	}
	
	public void getyrandmonth(String desiredmonth)
	{
		wait.until(ExpectedConditions.elementToBeClickable(date));
		String actualmonth = driver.findElement(By.xpath("//div[@class=\"DayNavigator__IconBlock-qj8jdz-2 iZpveD\"][2]")).getText();
		while(!actualmonth.contains(desiredmonth))
		{
			driver.findElement(By.xpath("//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'][3]")).click();
			actualmonth = driver.findElement(By.xpath("//div[@class=\"DayNavigator__IconBlock-qj8jdz-2 iZpveD\"][2]")).getText();
			if(actualmonth.contains(desiredmonth))
			{
				System.out.println("Desired month found: " + actualmonth);
				break;
			}
		}}
	
	public void getdate(String desireddate) {
	
			List<WebElement> alldates = driver.findElements(By.xpath("//div[contains(@class,'fGGTDM')]/span/div/span"));
			for(WebElement dates : alldates)
			{
				String dateText = dates.getText();
				
				if(dateText.equals(desireddate))
				{
					System.out.println("Current Date: " + dateText);
					dates.click();
					break;
				}}}
	
	public void searchclick() throws InterruptedException
	{
		driver.findElement(search).click();
		System.out.println("Search button clicked successfully");
		Thread.sleep(3000);	
	}
	
	public void businfo() {
		wait.until(ExpectedConditions.presenceOfElementLocated(busesavailable));
		String buses = driver.findElement(busesavailable).getText();
		System.out.println("The total buses available for desired search include special bus: " + buses);

		List<WebElement> bname = driver.findElements(busname);
		List<WebElement> bfare = driver.findElements(busfare);
		List<WebElement> seats = driver.findElements(seatsavailable);
		int buscount = bname.size();
		System.out.println("The normal buses available for desired search : " + buscount);
    	for (int i = 0; i < buscount; i++) {
        String availablebusname = bname.get(i).getText();
        String availablebusfare = bfare.get(i).getText();
        String availablebusseats = seats.get(i).getText();
        System.out.println("Bus Name: " + availablebusname);
        System.out.println("Bus Fare: INR " + availablebusfare);
        System.out.println("Bus seats count: " + availablebusseats);
    }
	}
	public void takescreenshot() throws IOException
	{
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("C:\\Users\\navee\\OneDrive\\Pictures\\Screenshots\\Takescreenshot\\image.png"));
	}
	}
    
