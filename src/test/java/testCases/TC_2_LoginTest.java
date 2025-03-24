package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_2_LoginTest extends BaseClass{

	
		@Test(groups= {"sanity","master"})//add groups in step7
		public void Verify_login()
		{
			logger.info("*********starting TC_2_LoginTest *******");
			
			try 
			{
				//home page
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();
				hp.clicklogin();
				
				//login
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(p.getProperty("email"));
				lp.setPassword(p.getProperty("password"));
				lp.clicklogin();
				
				//my account page
				MyAccountPage map=new MyAccountPage(driver);
				boolean page =map.isMyAccountPageExists();
				//Assert.assertEquals(page, true," login failed");
				Assert.assertTrue(page);
				
			}
			catch(Exception e)
			{
				Assert.fail();
				
			}
			logger.info("**********finished TC_2_LoginTest*******");
		}
	}


