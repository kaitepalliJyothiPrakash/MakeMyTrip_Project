package pageObjectModel;
import java.time.Duration;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class HotelPage
{
	private WebDriver driver;
	private WebDriverWait wait;
 
 
// Constructor
	public HotelPage(WebDriver driver)
	{
	this.driver = driver;
	PageFactory.initElements(driver, this);
	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
 
 
	@FindBy(xpath = "//span[@class='commonModal__close']")
	private WebElement closePopup;
 
	@FindBy(xpath = "//input[@id='guest']")
	private WebElement guestDropdown;
 
	@FindBy(xpath = "//button[@class='counter__button counter__button--increment' and contains(@aria-label,'Increase value from 2')]")
	private WebElement plusButton;
 
	@FindBy(xpath = "//div[contains(@aria-label,'Adults counter')]//span")
	private WebElement valueSpan;
 
	 @FindBy(xpath="//span[@class='headerIconTextAlignment chNavText darkGreyText'][normalize-space()='Hotels']")
	 private WebElement hotel;
	
	public void openHotelsPage()
	{
		hotel.click();
	}
 
	public void clickGuestDropdown()
	{
		wait.until(ExpectedConditions.elementToBeClickable(guestDropdown)).click();
	}
 
	public void increaseAdultsCount()
	{
	wait.until(ExpectedConditions.visibilityOf(plusButton));
	int val = Integer.parseInt(valueSpan.getText());
	int newVal = Integer.MIN_VALUE;
 
	while (val > newVal)
	{
	plusButton.click();
	newVal = val;
	val += 1;
		if (!plusButton.isEnabled())
		{
		break;
	    }
	}
	    System.out.println("Final Adults Count: " + val);
	}
}