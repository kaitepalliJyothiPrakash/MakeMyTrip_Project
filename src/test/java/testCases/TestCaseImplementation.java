package testCases;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.Test;

import pageObjectModel.CabPage;
import pageObjectModel.HomePage;
import pageObjectModel.HotelPage;
import testBase.BaseClass;

public class TestCaseImplementation extends BaseClass{

	
	@Test
	public void method1()
	{
		HomePage homepage=new HomePage(driver);
		homepage.closePopup();
	}
	@Test
	public void method2() throws InterruptedException, IOException {
		CabPage cabs=new CabPage(driver);
		cabs.excel();
		cabs.cab();
		cabs.date();
		cabs.cabtime();
		cabs.search();
		cabs.filter();
		cabs.lowestprice();
	}

	
	
	@Test
    public void method4() throws InterruptedException {
        HotelPage hotelPage = new HotelPage(driver);
        hotelPage.openHotelsPage();
        Thread.sleep(5000); 
        hotelPage.closePopupIfPresent();
        hotelPage.clickGuestDropdown();
        hotelPage.increaseAdultsCount();

	
	
}}
