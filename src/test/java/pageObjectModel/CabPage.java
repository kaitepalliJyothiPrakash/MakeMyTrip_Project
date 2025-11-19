package pageObjectModel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CabPage extends BasePage{
	String months;
	String datecab;
	String pick;
	String droplocation;
	public CabPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy (xpath="//span[text()='Cabs'  and @class='headerIconTextAlignment chNavText darkGreyText']") WebElement cabs;
	@FindBy (xpath="//span[text()='From']") WebElement froms;
	@FindBy (xpath="//input[contains(@title,'From')]") WebElement picks;
	@FindBy (xpath="//div[@class='react-autosuggest__section-title']") WebElement dropdown;
	@FindBy (xpath="//ul[contains(@role,'listbox')]//li[3]") WebElement option;
	@FindBy (xpath="//p[text()='POPULAR CITIES']") WebElement dropdown2;
	@FindBy (xpath="//ul[contains(@role,'listbox')]//li[1]") WebElement option2;
	@FindBy (xpath="//label[contains(@for,'departure')]") WebElement dates;
	@FindBy (xpath="//div[@class='DayPicker-Caption' and contains(@role,'heading')]//div") List<WebElement> month_option;
	@FindBy (xpath="//span[@class='DayPicker-NavButton DayPicker-NavButton--next']") WebElement next_button;
	@FindBy (xpath="//div[@class='DayPicker-Day']") List<WebElement> date_pick;
	@FindBy (xpath="//label[contains(@for,'pickupTime')]") WebElement pick_option;
	@FindBy (xpath="//ul[@class='newTimeSlotHrUl']//li[3]") WebElement hour;
	@FindBy (xpath="//ul[@class='newTimeSlotMinUl']//li[5]") WebElement min;
	@FindBy (xpath="//ul[@class='newTimeSlotMerUl']//li[2]") WebElement AM_PM;
	@FindBy (xpath="//span[@class='applyBtnText']") WebElement Apply;
	@FindBy (xpath="//p[@class='makeFlex vrtlCenter']") WebElement Search;
	@FindBy (xpath="//span[text()='SUV']") WebElement SUV;
	@FindBy (xpath="//span[@class='cabDetailsCard_price__SHN6W']") List<WebElement> price;
	
	public void excel() throws IOException {
		XSSFWorkbook workbook=new XSSFWorkbook("C://Users//2445031//Documents//MakeMyTrip_Project//src//test//java//utilities//Project.xlsx/");
		XSSFSheet sheet=workbook.getSheetAt(0);
		XSSFRow row=sheet.getRow(0);
		XSSFCell cell1=row.getCell(0);
		pick=cell1.toString();
		XSSFCell cell2=row.getCell(1);
		droplocation=cell2.toString();
		XSSFCell cell3=row.getCell(2);
		months=cell3.toString();
		XSSFCell cell4=row.getCell(3);
		datecab=cell4.toString();
	}
	
	
	public void cab() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		cabs.click();
		froms.click();
		
		//pickup location
		picks.sendKeys(pick);
		// Waiting for dropdown
		wait.until(ExpectedConditions.visibilityOfAllElements(dropdown));
		Thread.sleep(5000);
		// Selecting an option
		option.click();
		
		//entering the drop location
		WebElement drop=driver.findElement(By.xpath("//input[contains(@title,'To')]"));
		drop.sendKeys(droplocation);
		//waiting for dropdown
		wait.until(ExpectedConditions.visibilityOfAllElements(dropdown2));
		Thread.sleep(5000);
		//Selecting an option
		option2.click();
	}
	
	public void date() {
		dates.click();
		
		//selecting month 
		while(true) {
	    	List<WebElement> options=month_option;
		    String current_month=options.get(0).getText();
	    	if(!current_month.equalsIgnoreCase(months)) {
	    		//Thread.sleep(2000);
	            next_button.click();
	         }
	    	else {
	    		break;
	    	}
	    }
	    //selecting date
	    List<WebElement> selects=date_pick;
	    for(WebElement i:selects) {
	        if(!i.getText().equalsIgnoreCase(datecab)) {
	        		 continue;	        		 
	        }
	        else {
	        		 i.click();
	        		 break;
	        }
	     }

	}
	
	public void cabtime() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(pick_option)).click();
	    hour.click();
	    min.click();
	    AM_PM.click();
	    Apply.click();
	}
	
	public void search() {
	    Search.click();
	}
	
	public void filter() {
	    SUV.click();
	}
	
	public void lowestprice() {
		List<WebElement> prices=price;
	    int low=Integer.MAX_VALUE;
	    for(WebElement priceElement:prices) {
	    	String lowest=priceElement.getText().replaceAll("[^0-9]","").trim();
	    	if(!lowest.isEmpty()) {
	    		int price=Integer.parseInt(lowest);
	    		if(price<low) {
	    			low=price;
	    		}
	    	}
	    }
	    System.out.println("Lowest cab price is: "+low);
	}
	
	
	
	
	
	
}
