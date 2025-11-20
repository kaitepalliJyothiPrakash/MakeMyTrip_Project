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


public class TestCaseImplementation extends BaseClass{

	
	@Test(priority=1)
	public void closingPopupInHomePage()
	{
		HomePage homepage=new HomePage(driver);
		homepage.closePopup();
	}

	@Test(priority=2)
	public void method2() throws InterruptedException, IOException 
	{
		CabPage cabs=new CabPage(driver);
		cabs.excel();
		cabs.cab();
		cabs.date();
		cabs.cabtime();
		cabs.search();
		cabs.filter();
		cabs.lowestprice();
	}
	
	
	@DataProvider(name="GiftFormData")
	public String [][] getData() throws IOException
	{
		String path=".\\utilities\\Project.xlsx";
		ExcelUtilities xlutil=new ExcelUtilities(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)  
		{		
			for(int j=0;j<totalcols;j++)  //i is rows j is column
			{
				logindata[i][j]= xlutil.getCellData("Sheet1",i, j);  
			}
		}
	return logindata;
	}

	@Test(dependsOnMethods={"method2"})
	public void implementGiftCard(String name,String mobileno,String email) throws InterruptedException{
	GiftPage gp = new GiftPage(driver);
	gp.hoverMenu(driver);
	gp.giftcardOption();
	gp.executeBcard(driver);

	gp.detailsForm(driver);
	gp.sendName(name);
	gp.sendMobileNo(mobileno);
	gp.sendEmail(email);

	gp.clickButton();

	gp.getDisplayedMsg();
	}

	@Test
    public void method4() throws InterruptedException {
        HotelPage hotelPage = new HotelPage(driver);
        hotelPage.openHotelsPage();
        Thread.sleep(5000); 
        hotelPage.closePopupIfPresent();
        hotelPage.clickGuestDropdown();
        hotelPage.increaseAdultsCount();

	}
}

