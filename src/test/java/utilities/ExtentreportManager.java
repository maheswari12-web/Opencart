package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentreportManager implements ITestListener
{
	public ExtentSparkReporter sparkreporter;   //ui of the report
	public ExtentReports extent;   //populate common information  on the report
	public ExtentTest test;      //creating test cases entries in the reports and update status of the test methods
	
	String repName;
	
	public void onStart(ITestContext context) {
		
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt=new Date();
		 String currentdatetimestamp=df.format(dt);
		*/
		
		String timestamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		
		repName="Test-Report-"+timestamp+".html";
		

		sparkreporter =new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		
		sparkreporter.config().setDocumentTitle("Opencart Automation Report"); //title of the report
		sparkreporter.config().setReportName("Opencart functionalTesting");//name of the report
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub module","customer");
		extent.setSystemInfo("User name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system",os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser",browser);
		
		List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
		
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, result.getName()+"got successfully executed");//update status p/f/s
		
	  }
	
	public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got FAILED ");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got SKIPPED ");
		test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
		 
		extent.flush();
		
		String PathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport =new File(PathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		 
	/*
	   try  
	   {
		 URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	 
	 //create the email message
	  
	  ImageHtmlEmail email=new ImageHtmlEmail();
	  email.setDataSourceResolver(new DataSourceUrlResolver(url));
	  email.setHostName("smtp.googlemail.com");
	  email.setSmtpPort(465);
	  email.setAuthenticator(new DefaultAuthenticator("pavanoltrainig@gmail.com","password"));
	  email.setSSLOnConnect(true);
	  email.setFrom("pavanoltrainig@gmail.com"); //sender
	  email.setSubject("TestResults");
	  email.setMsg("please find attached report");
	  email.addTo("pavankumar.busyqa@gmail.com");//receiver
	  email.attach(url,"extent report","please check report....");
	  email.send();//send the mail
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 
	 }
	 
	 */
	} 
}

