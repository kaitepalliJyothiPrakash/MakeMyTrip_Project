package testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjectModel.CabPage;
import pageObjectModel.GiftPage;
import pageObjectModel.HomePage;
import pageObjectModel.HotelPage;
import testBase.BaseClass;
import utilities.ExcelUtilities;
import utilities.Screenshot;


public class TestCaseImplementation extends BaseClass{

	
	@Test(priority=1)
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
    public void hotelDetails() throws InterruptedException {
        HotelPage hotelPage = new HotelPage(driver);
        hotelPage.openHotelsPage();
        Thread.sleep(3000); 
        hotelPage.clickGuestDropdown();
        hotelPage.increaseAdultsCount();

	}
}

