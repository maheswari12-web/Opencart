package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//h2[text()='My Account']") //my account page heading
	WebElement msgheading;
	
   @FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") //my account page logout ---added in step no 6
	WebElement logoutlink;

	public boolean isMyAccountPageExists() //verify account page is exists or not
	{
		try
		{
		return (msgheading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
  public void clicklogout()
	{
		logoutlink.click();
	}
}
