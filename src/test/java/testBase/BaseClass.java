package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
public WebDriver driver;
public Logger logger; //import org.apache.logging.log4j.Logger; -----step2
public Properties p; //step4
	
	@BeforeClass(groups= {"sanity"," Regresion","master"})//add groups in step7
	@Parameters({"os","browser"})//step3
	public void setup(String os,String br) throws IOException //step3
	{
		//loading config.properties file ---step4
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass()); //import org.apache.logging.log4j.LogManager;--------- step2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("Remote"))//step 10
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else
			{
				System.out.println("NO matching os");
				return;
			}
			
			
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge"  : capabilities.setBrowserName("MicrosoftEdge");break;
			default      : System.out.println("no matching browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://192.168.29.98:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("Local")) 
		{
		
			switch(br.toLowerCase()) //step3
			{
			case "chrome" :driver=new ChromeDriver();
			break;
			case "edge" :driver=new EdgeDriver();
			break;
			case "firefox" :driver=new FirefoxDriver();
			break;
			default : System.out.println("Invalid browser....");
			return;
			}
			
		}
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("http://localhost/opencart/upload/index.php");
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appurl")); //reading appurl from config.properties file----step4
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups={"sanity"," Regresion","master"}) //add groups in step7
	public void tearDown()
	{
		driver.quit();
		
	}
	
	public String randomString()
	{
		String genstring=RandomStringUtils.randomAlphabetic(5);
		return genstring;
		
	}
	public String randomNumber()
	{
		String gennumber=RandomStringUtils.randomNumeric(10);
		return gennumber;
		
	}
	
	public String randomAlphaNumeric()
	{
		String genstring=RandomStringUtils.randomAlphabetic(3);
		String gennumber=RandomStringUtils.randomNumeric(3);
		return (genstring+"@"+gennumber);
		
	}
	
	public String captureScreen(String tname)  throws IOException //step8 
	{
		String timeStamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver;
	    File sourcefile = ts.getScreenshotAs(OutputType.FILE);
	    
	    String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\" + tname+ "_" + timeStamp + ".png";
	    File targetfile = new File(targetfilepath);
	    sourcefile.renameTo(targetfile);//copy sourcefile to targetfile
	    
	    return targetfilepath;
	}
	

}
