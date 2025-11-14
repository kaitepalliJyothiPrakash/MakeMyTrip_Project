package testCases;

import org.testng.annotations.Test;

import pageObjectModel.HomePage;
import testBase.BaseClass;

public class TestCaseImplementation extends BaseClass{

	
	@Test
	public void method1()
	{
		HomePage homepage=new HomePage(driver);
		homepage.closePopup();
	}
}
