package testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pageObjectModel.CabPage;
import pageObjectModel.GiftPage;
import pageObjectModel.HomePage;
import pageObjectModel.HotelPage;
import testBase.BaseClass;
import utilities.ExcelUtilities;
import utilities.Screenshot;


public class TestCaseImplementation extends BaseClass{

	
	@Test(priority=1)
	@Description("Closing Registraion popup on Home Page")
	@Epic("EP001")
	@Feature("Feature1: Closing popup")
	@Story("Story: Closing popup")
	@Step("Verifying the popup closed")
	@Severity(SeverityLevel.BLOCKER)
	public void closingPopupInHomePage()
	{
		HomePage homepage=new HomePage(driver);
		homepage.closePopup();
	}

	@DataProvider(name="CabData")
	public String[][] getData() throws IOException{
		String path=".//src/test/java/utilities/Project_InputData.xlsx";
		ExcelUtilities xlutils=new ExcelUtilities(path);
		int totalcols=xlutils.getCellCount("Sheet1",0);
		String cabdata[][]=new String[1][totalcols];
			
			for(int j=0;j<totalcols;j++) 
			{
				cabdata[0][j]= xlutils.getCellData("Sheet1",0, j);  
			}
		
		return cabdata;
	}

	@Test(dependsOnMethods= "closingPopupInHomePage",dataProvider="CabData")
	@Description("Book one way outstation cab, From Delhi to Manali, himachal Pradesh, give future date & time & Car type should be SUV; Display the lowest charges")
	@Epic("EP001")
	@Feature("Feature2: Book one way outstation cab and Display the lowest charges")
	@Story("Story: Book one way outstation cab ")
	@Step("Verifying the cab charges and booking cab")
	@Severity(SeverityLevel.CRITICAL)
	public void implementCab(String pick,String dropLocation,String months,String date) throws InterruptedException, IOException
	{
		CabPage cabs=new CabPage(driver);
		cabs.cab(pick,dropLocation);
		cabs.date(months,date);
		cabs.cabtime();
		cabs.search();
		cabs.filter();
		cabs.lowestprice();
	}
	
	
	
	@DataProvider(name="GiftFormData")
	public String [][] getgiftData() throws IOException
	{
		String path=".//src/test/java/utilities/Project_InputData.xlsx";
		ExcelUtilities xlutil=new ExcelUtilities(path);
		
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String giftformdata[][]=new String[1][totalcols];	
			for(int j=0;j<totalcols;j++)  
			{
				giftformdata[0][j]= xlutil.getCellData("Sheet1",1, j);  
			}
	return giftformdata;
	}

	@Test(dependsOnMethods="implementCab",dataProvider = "GiftFormData")
	@Description("Find Group Gifting in Gift Cards, fill card details & give invalid email; capture & display the error message")
	@Epic("EP001")
	@Feature("Feature3: Find Group Gifting in Gift Cards")
	@Story("Story: capture & display the error message")
	@Step("Verifying the email address")
	@Severity(SeverityLevel.MINOR)
	public void implementGiftCard(String name,String mobileno,String email) throws InterruptedException, IOException{
	GiftPage gp = new GiftPage(driver);
	gp.hoverMenu(driver);
	gp.giftcardOption();
	gp.closeaipopup();
	gp.executeBcard(driver);

	gp.detailsForm(driver);
	gp.sendName(name);
	gp.sendMobileNo(mobileno);
	gp.sendEmail(email);

	gp.clickButton();

	gp.getDisplayedMsg();
	Screenshot.getScreenshot(driver,"GiftCardInvalidInputs");
	
	gp.scrollup(driver);
	
	//System.out.println(name+" >>> "+ mobileno+" >>> "+email);
	}

	@Test(dependsOnMethods= "implementGiftCard")
	@Description("On the Hotel booking page, extract all the numbers for Adult persons and store in a List; Display the same")
	@Epic("EP001")
	@Feature("Feature4: Extract all the numbers for Adult persons and store in a List")
	@Story("Story: The numbers for Adult persons and store in a List")
	@Step("Verifying the numbers for Adult persons")
	@Severity(SeverityLevel.NORMAL)
    public void hotelDetails() throws InterruptedException {
        HotelPage hotelPage = new HotelPage(driver);
        hotelPage.openHotelsPage();
        
        hotelPage.clickGuestDropdown();
        hotelPage.increaseAdultsCount();

	}
}

