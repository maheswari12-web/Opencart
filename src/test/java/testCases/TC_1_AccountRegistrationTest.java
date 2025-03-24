package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_1_AccountRegistrationTest extends BaseClass{
	

	@Test(groups= {"Regression","master"})//add groups in step7
	public void verify_account_registration()
	{
		
		logger.info("***********starting TC_1_AccountRegistrationTest************");//step2
	
		try//step2
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on my account........");//step2
		hp.clickRegister();
		logger.info("clicked on register link ..........");//step2
		
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		
		logger.info("providing customer details.........");//step2
		arp.setfirstname(randomString().toUpperCase());
		arp.setlastname(randomString().toUpperCase());
		arp.setemail(randomString()+"@gmail.com");//randomly generated email
		arp.settelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		arp.setpassword(password);
		arp.setconfirmpassword(password);
		arp.setprivacypolicy();
		arp.clickcontinue();
		
		logger.info("validating expected message..");//step2
		String confirmmsg=arp.getconfirmationMsg();
			if(confirmmsg.equals("Your Account Has Been Created!")) //step2
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("testfailed....");
				logger.debug("debug logs........."); //specifying debug option in xml is not enough you can specify test case(here) also.
				Assert.assertTrue(false);
			}
			
		//Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("***********finished TC_1_AccountRegistrationTest***********");//step2
	}
	
	
	
}