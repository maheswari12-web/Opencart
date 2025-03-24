package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage  extends BasePage {
	
	public  AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkpolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	public void setfirstname(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	
	public void setlastname(String lname)
	{
		txtlastname.sendKeys(lname);
	}
	
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void settelephone(String tel)
	{
		txttelephone.sendKeys(tel);
	}
	
	public void setpassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void setconfirmpassword(String pwd)
	{
		txtconfirmpassword.sendKeys(pwd);
	}
	
	public void setprivacypolicy()
	{
		checkpolicy.click();
	}
	
	public void clickcontinue()
	{
		//sol1
		btncontinue.click();
		
		//sol2
		//btncontinue.submit();
		
		//sol3
		//Actions act =new Actions(driver);
		//act.moveToElement(btncontinue).click().perform();
		
		//sol4
		//JavascriptExecutor js =new(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();",btncontinue);
		
		//sol5
		//btncontinue.sendKeys(Keys.RETURN);
		
		//sol6
		//WebDriverWait mywait =new WebDriverWait(driver, Duratioin.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btncontinue)).click();
	}
		
		
		public String getconfirmationMsg()
		{
			try {
				return(msgconfirmation.getText());
			}
			catch(Exception e)
			{
				return (e.getMessage());
			}
		}
	}
	
	


