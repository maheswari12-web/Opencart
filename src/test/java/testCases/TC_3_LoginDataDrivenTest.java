package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


/*Data is valid ---login success---test pass---logout
 * data is valid---login failed---test fail
 * 
 * data is invalid--login success--test fail--logout
 * data is invalid--login failed--test pass
 */
public class TC_3_LoginDataDrivenTest extends BaseClass {
   
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class) //,groups= "Datadriven") //getting data provider from different
	public void Verify_loginDDT(String email,String pwd,String exp)
	{ 
		
		logger.info("*********started TC_3_LoginDataDrivenTest******");
		
		try
		{
			
	    //home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		//loginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clicklogin();
		
		//MyAccountPage
		MyAccountPage map=new MyAccountPage(driver);
		boolean page =map.isMyAccountPageExists();
		
		
		/*Data is valid ---login success---test pass---logout
		 * data is valid---login failed---test fail
		 */
		if(exp.equalsIgnoreCase("valid")) //data is valid
		{
			if(page==true) //login success
			{
				
				map.clicklogout(); //logout
				Assert.assertTrue(true); //test pass
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
	
	 /* data is invalid--login success--test fail--logout
	 * data is invalid--login failed--test pass
	 */
		
		if(exp.equalsIgnoreCase("invalid")) //data is invalid
		{
			if(page==true) //login sucess
			{
				
				map.clicklogout(); //logout
				Assert.assertTrue(false); //test fail
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
			}catch(Exception e)
				{
					Assert.fail();
				}
		
	
	        logger.info("*********finished TC_3_LoginDataDrivenTest***********");
	}
	
}
