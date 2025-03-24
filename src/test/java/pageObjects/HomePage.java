package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	WebDriver driver;

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") //step1
	WebElement linkmyAcount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")//step1
	WebElement linkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']") //login link added in step 5
	WebElement linklogin;
	
	public void clickMyAccount()//step1
	{
		linkmyAcount.click();
	}
	
	public void clickRegister()//step1
	{
		linkRegister.click();
	}
	
	public void  clicklogin()//step5
	{
		linklogin.click();
	}
	
}
