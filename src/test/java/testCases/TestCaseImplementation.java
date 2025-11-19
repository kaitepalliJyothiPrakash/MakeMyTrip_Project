package testCases;

import org.testng.annotations.Test;

import pageObjectModel.HomePage;
import testBase.BaseClass;

public class TestCaseImplementation extends BaseClass{

	
	@Test(priority=1)
	public void closingPopupInHomePage()
	{
		HomePage homepage=new HomePage(driver);
		homepage.closePopup();
	}
	
}
