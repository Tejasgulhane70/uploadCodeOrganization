package Base_Class_POM;




import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


import COMMON_UTILS1.ExcelUtils;
import COMMON_UTILS1.FileUtils;
import COMMON_UTILS1.WebDriverUtils;
import POM.CreateContactPage;
import POM.HomePage;
import POM.VtigerLoginPage;


public class ContactBase 
{
	
    public static void main(String[] args) throws IOException, InterruptedException 
    {
    	final WebDriver driver;
    	
    	FileUtils futils = new FileUtils(); //  --->1st home page take a data from propertyfile
    	WebDriverUtils wutils = new WebDriverUtils();//-->call a webdriver methods
    	ExcelUtils eutils = new ExcelUtils();//---> 3rd contact page take a data from excelfile
    	
    	String BROWSER = futils.getDataFromPropertyFile("Browser");
    	String URL =futils.getDataFromPropertyFile("url");
    	String USERNAME =futils.getDataFromPropertyFile("username");
    	String PASSWORD =futils.getDataFromPropertyFile("password");
    	
    	
    	if(BROWSER.equalsIgnoreCase("Chrome"))
    	{
    		driver= new ChromeDriver();
    	}
    	else if(BROWSER.equals("Edge"))
    	{
    		driver= new EdgeDriver();
    	}
    	else
    	{
    		driver = new FirefoxDriver();
    	}
    	
    	//maximize the window
    	wutils.maximize(driver);
    	//to apply implicit wait
    	wutils.ImplicitWait(driver);
    	
    	//launch url
    	driver.get(URL);
    	
    	//1st loginpage //login to application 
    	VtigerLoginPage vlp= new VtigerLoginPage();
    	//for each and every class we can call this method 
    	PageFactory.initElements(driver, vlp);
    	vlp.getUsername().sendKeys(USERNAME);
    	vlp.getPassword().sendKeys(PASSWORD);
    	vlp.getLoginbutton().click();
    	
    	//2nd homepage
    	HomePage hp = new HomePage();
    	PageFactory.initElements(driver, hp);
    	//click on contact
    	hp.getContact().click();
    	//click on plus icon
    	hp.getContactPlusbtn().click();
    	
    	//3RD createContactPage
    	CreateContactPage cp = new CreateContactPage();
    	PageFactory.initElements(driver, cp);
    	
    	//handle 1st dropdown with taken data from excel sheet
    	String exceldata1 = eutils.getDataFromExcelFile2("Sheet1", 1, 0);
    	//dirct call the dropdownhandle method in webdrivrutils class
    	wutils.handledropdown(cp.getDropdown1(), exceldata1);
    	
    	//pass the data of firstname textfield taken from excelsheet
    	String exceldata2 = eutils.getDataFromExcelFile2("Sheet1", 1, 1);
    	cp.getFirstnametf().sendKeys(exceldata2);
    	
    	//pass the data of lastname textfield taken from excelsheet
    	String exceldata3 = eutils.getDataFromExcelFile2("Sheet1", 1, 2);
    	cp.getLastnametf().sendKeys(exceldata3);
    	
    	//click on contact plus button
    	 cp.getContactbtnplus().click();
    	 
    	 Thread.sleep(4000);
    	 //paretnts to child
    	// wutils.switchtoparenttochild(driver);
    	 //switch to for cotrol parrnt to child (multiple child) taken url of child from excel
    	String childurl = eutils.getDataFromExcelFile2("Sheet1", 1, 7);
    	wutils.swithchtowindow(driver, childurl);
 	   
 	     //PASS THE ORGANIZATION NAME IN SEARCH TEXTFIELD
 	     String orgname1 = eutils.getDataFromExcelFile2("Sheet1",1,5);
 	     cp.getContacttf().sendKeys(orgname1);
 	     
 	    //CLICK ON SEARCH BUTTON
 	     cp.getSearchbtn().click();
 	     
 	    //CLICK ON ORGNAME 
 	     cp.getContactText().click();
 	     Thread.sleep(2000);
 	    
 	    //transfer the control child to parent
 	     
 	    wutils.switchtochildtoparent(driver);
 	  
    	//click on group radiobutton
    	cp.getRadiobtn().click();
    	
    	//handle dropdown by visible text 
    	String exceldata4 =eutils.getDataFromExcelFile2("Sheet1", 1, 3);
    	wutils.handledropdown(cp.getGroupdropdown(), exceldata4);
    	
    	//-----to take screenshot of webpage screenshot call method-------->
    	
    	wutils.screenshotwebpage(driver);
   	    Thread.sleep(2000);
   	    
   	  //to perfrom scrolling
   	    wutils.scrolling(driver);
   	   
   	   //click on choose file uploading 
   	    WebElement e1 =  driver.findElement(By.xpath("//input[@type='file']"));
   	    
   	    Actions act = new Actions(driver);
   	    act.click(e1).perform();
   	  
   	    Runtime.getRuntime().exec("C:\\Autoiteditorr....22\\VtigerPOM.exe");
   	   
        //click on savebtn
    	cp.getSavebtn().click();
    	
    	//mousehover on admin img
    	wutils.Action(driver, cp.getSignoutimg());
    	
    	//click on signout
    	cp.getSignoutbutton().click();
    	
    	
    	
		
	}

	
	
	
	
	
}
