package pageObjectModel;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GiftPage extends BasePage {
	
	public GiftPage(WebDriver driver) {
		super(driver);
	}
	
	//Drop down
	@FindBy(xpath="//span[@class='arrow arrowDown']")
	WebElement drpdwn;
	
	//giftcards option
	@FindBy(xpath="//a[@data-cy='submenu_Giftcards']")
	WebElement giftcrds;
	
	//Birthday card
	@FindBy(xpath="//div[@class='gc__right']/div[3]/ul/li[2]")
	WebElement bdcard;
	
	//Details form
	@FindBy(xpath="//div[@id='deliveredSection']")
	WebElement inputform;
	
	//Sender name
	@FindBy(xpath="//div[@class='form__field']/input[@name='senderName']")
	WebElement senderName;
	
	//Mobile Number
	@FindBy(xpath="//div[@class='form__field']/input[@name='senderMobileNo']")
	WebElement mobNo;
	
	//Email id
	@FindBy(xpath="//div[@class='form__field']/input[@name='senderEmailId']")
	WebElement emailid;
	
	//Buy Button
	@FindBy(xpath="//button[@data-cy='BookingDetails_440']")
	WebElement buybtn;
	
	//Invalid alert text
	@FindBy(xpath="//p[@class='red-text font11 append-top5']")
	WebElement msgDisplay;
	
	
	//Hover to drop down
	public void hoverMenu(WebDriver driver) throws InterruptedException{
		Actions action = new Actions(driver);
		action.moveToElement(drpdwn).perform();
		Thread.sleep(2000);
	}
	
	//Select giftcard option
	public void giftcardOption() {
		giftcrds.click();
	}
	
	//Scroll to birthday gift card and click
	public void executeBcard(WebDriver driver) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].scrollIntoView();", bdcard);
	    Thread.sleep(2000);
	    js.executeScript("arguments[0].click();", bdcard);
	}
	
	//Scroll to Details form
	public void detailsForm(WebDriver driver) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", inputform);
		Thread.sleep(2000);
	}
	
	//Fill the form details
	
	public void sendName(String name) {
		senderName.sendKeys(name);
	}
	
	public void sendMobileNo(String mobileno) {
		mobNo.sendKeys(mobileno);
	}
	
	public void sendEmail(String email) {
		emailid.sendKeys(email);
	}
	
	public void clickButton() {
		buybtn.click();
	}
	
	public void getDisplayedMsg() throws InterruptedException{
		System.out.println(msgDisplay.getText());
		Thread.sleep(2000);
	}
}
