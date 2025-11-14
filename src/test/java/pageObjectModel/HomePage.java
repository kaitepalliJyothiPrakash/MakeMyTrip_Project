package pageObjectModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[@class='commonModal__close']")WebElement clsPopup;
	
	public void closePopup() {
		clsPopup.click();
	}

	
}
