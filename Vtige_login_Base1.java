package Base_Class_POM;

import java.io.IOException;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import COMMON_UTILS1.ExcelUtils;
import COMMON_UTILS1.FileUtils;
import COMMON_UTILS1.JavaUtils;
import COMMON_UTILS1.WebDriverUtils;
import POM.CreateOraganizationPage;
import POM.HomePage;
import POM.VtigerLoginPage;

public class Vtige_login_Base1 
{
//create organization
    
  
  
public static void main(String[] args) throws IOException, InterruptedException 

   {
        
	  final WebDriver driver;
		//create object of fileutils class
	   FileUtils futils = new FileUtils();
	   
	   JavaUtils jutils = new JavaUtils();
	   
	   //login the webpage to take data from propertyfile
	   String BROWSER =   futils.getDataFromPropertyFile2("Browser");
	   String URL =   futils.getDataFromPropertyFile2("url");
	   String USERNAME  = futils.getDataFromPropertyFile2("username");
	   String PASSWORD= futils.getDataFromPropertyFile2("password");
		
	   if(BROWSER.equals("Chrome"))
		{
			
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("Edge"))
		{
			driver= new EdgeDriver();
		}
		else 
		{
		  driver= new FirefoxDriver();
		}
	   
	   //launch browser
	   driver.get(URL);
	   
	  
	   //create a object of vtigerloginpag pass the arguments
       VtigerLoginPage vlp = new VtigerLoginPage();
       PageFactory.initElements(driver, vlp);
	   vlp.getUsername().sendKeys(USERNAME);
	   vlp.getPassword().sendKeys(PASSWORD);
       vlp.getLoginbutton().click();
       
        WebDriverUtils wutils = new WebDriverUtils();
        //maximize the window
        wutils.maximize(driver);
        //to apply implicit wait
        wutils.ImplicitWait(driver);
        
       //pagefactory it is a class and init element it is a method it is also called as subset of pom 
	  //pass the webfriverreferance and 
        
        //homepage 
        HomePage hp = new HomePage();
        PageFactory.initElements(driver, hp);
        //click on organization
        hp.getOrganization().click();
        //click on plus button
        hp.getPlusbtn().click();
        
        
       //organizationPage //get data from excelsheet
        
       ExcelUtils eutils = new ExcelUtils();
       
       String data1 = eutils.getDataFromExcelFile1("Sheet1", 1, 0);
       CreateOraganizationPage org = new CreateOraganizationPage();
       PageFactory.initElements(driver, org);
       org.getOrgname().sendKeys(data1);
        //click on radio Group button
       org.getRadiobtn().click();
        
       //select dropdown by visible text
        String Group = eutils.getDataFromExcelFile1("Sheet1", 1, 1);
        wutils.handledropdown(org.getDropdown(), Group);
        
        //screenshot
        wutils.screenshotwebpage(driver);
        
        //click on save button
        org.getSavebtn().click();
        
        Thread.sleep(4000);
        //mouse over on signout
        wutils.Action(driver, org.getSignoutimg());
        Thread.sleep(4000);   // not come exception now
        //click on signout
        org.getSignoutbutton().click();	   
	   
	
}
}
