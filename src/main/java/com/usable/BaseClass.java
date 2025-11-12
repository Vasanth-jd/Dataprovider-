package com.usable;
import static org.testng.Assert.assertEquals;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {
	
	public static WebDriver driver;
	public static ExtentReports extentReport;
	public static File file;
	protected static void launchBrowser(String browser) {
		
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				 driver= new ChromeDriver();
			}
			else if (browser.equalsIgnoreCase("firefox")) {
				driver= new FirefoxDriver();
			}
			else if (browser.equalsIgnoreCase("edge")) {
				driver= new EdgeDriver();
			}
			driver.manage().window().maximize();
		} catch (Exception e) {
		Assert.fail("ERROR: OCCURE DURING LAUNCHING BROWSER");
			
		}
	}
	protected static void launchUrl(String url) {
		
		driver.get(url);
	}
	
   protected  static void sendKeys(WebElement element ,String value) {
	   element.sendKeys(value);
	   }
   
   protected static void robot_Class( String type) throws AWTException {
	   Robot r = new Robot();
	   if(type.equalsIgnoreCase("enter")) {
	   r.keyPress(KeyEvent.VK_ENTER);
	   r.keyRelease(KeyEvent.VK_ENTER);
	   }
	   else if(type.equalsIgnoreCase("down")) {
		   r.keyPress(KeyEvent.VK_DOWN);
		   r.keyRelease(KeyEvent.VK_DOWN);
	   }
	   else if(type.equalsIgnoreCase("up")) {
		   r.keyPress(KeyEvent.VK_UP);
		   r.keyRelease(KeyEvent.VK_UP);
		   }
	    }
    protected static void navigateUrl(String url) {
	   
	   driver.navigate().to(url);
   }
   protected static void navigateMethods(String type) {
	   if(type.equalsIgnoreCase("back")) {
		   driver.navigate().back();
	   }
	   else if(type.equalsIgnoreCase("forward")) {
		   driver.navigate().forward();
	   }
	   else if(type.equalsIgnoreCase("refresh")) {
		   driver.navigate().refresh();
	   }
	     }
   protected static void dropdownMethods(WebElement element,String type,String value) {
	   
	   Select s = new Select(element);
	   if (type.equalsIgnoreCase("text")) {
		   s.selectByVisibleText(value);
		
	   }
	   else if(type.equalsIgnoreCase("value")) {
		   s.selectByValue(value);
		   
	   }
	   else if (type.equalsIgnoreCase("index")) {
		   s.selectByIndex(Integer.parseInt(value));
		     }
   }
   protected static void implicitlyWait(String type,int value) {
	   if(type.equalsIgnoreCase("seconds")) {
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
	   }
	   else if(type.equalsIgnoreCase("miniutes")) {
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
	   }
	   else if(type.equalsIgnoreCase("hours")) {
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
	   }
   }
   protected static void explicitlyWait(WebElement element,String type , int sec ) {
	   
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
	   if(type.equalsIgnoreCase("visiblity")) {
		   wait.until(ExpectedConditions.visibilityOf(element));
	   }
	   else if(type.equalsIgnoreCase("element")) {
		   wait.until(ExpectedConditions.elementToBeClickable(element));
	   }
	   
   }
   protected static void elementClick(WebElement element) {
	   element.click();
	   
   }
   protected static void screenshotMethod(String location) throws IOException {
	   Date date = new Date();
	   String dateFile = date.toString().replace(" ", "_").replace(":", "_");
	   File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileHandler.copy(screenshot,new File(location+dateFile+".png"));
   }
   protected static void getTitle() {
	   String title = driver.getTitle();
	   System.out.println("Title is="+title);
   }
   protected static void getOptions(WebElement element) {
	   Select s = new Select(element);
	   List<WebElement> options = s.getOptions();
	   for(WebElement newOptions:options) {
		   String text = newOptions.getText();
		   System.out.println(text);
		   } }
 
   protected static void getAttribute(WebElement element,String atValue) {
	   String attribute = element.getAttribute(atValue);
	   System.out.println("Attribute value is="+attribute);
	    element.getAttribute(atValue);
	   System.out.println("attribute value is="+atValue);
	   
   }
   protected static void alertMethods(WebElement element,String type ) {
	  if(type.equalsIgnoreCase("simple")) {
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		    }
		  else if(type.equalsIgnoreCase("confirm")) {
		  Alert alert = driver.switchTo().alert();
		  alert.dismiss();
	  }
	      else if(type.equalsIgnoreCase("prompt")) {
		  Alert alert = driver.switchTo().alert();
		  alert.sendKeys("vasanth");
		  alert.accept();
	  }
	  driver.switchTo().defaultContent();
	   }
   protected static void scrollIntoView(WebElement element) {
	   try {
		JavascriptExecutor j= (JavascriptExecutor)driver;
		   j.executeScript("arguments[0].scrollIntoView(true);", element);
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING SCROLL INTO VIEW");
	}
	  }
   protected static void javascriptClick(WebElement element) {
	   try {
		JavascriptExecutor j= (JavascriptExecutor)driver;
		   j.executeScript("arguments[0].click();", element);
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING JAVASCRIPT CLICK");
	}
	   
   }
   
   protected static void geCurrenttUrl() {
	   try {
		String currentUrl = driver.getCurrentUrl();
		   System.out.println(currentUrl);
	} catch (Exception e) {
		Assert.fail("Error: OCCUR DURING GET CURRENT URL");
	}
	   
   }
   protected static void isMultiple(WebElement element) {
	   try {
		Select s = new Select(element);
		   boolean multiple = s.isMultiple();
		   System.out.println("Element is multiple="+multiple);
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING CHECK ISMULTIPLE");
	}
   }
  protected static void fieldValidation(WebElement element,String type) {
	  try {
		if (type.equalsIgnoreCase("enable")) {
			  boolean enabled = element.isEnabled();
			  System.out.println(enabled);
		  }
		  else if(type.equalsIgnoreCase("display")) {
			  boolean displayed = element.isDisplayed();
			  System.out.println(displayed);
		  }
		  else if(type.equalsIgnoreCase("select")) {
			  boolean selected = element.isSelected();
			  System.out.println(selected);
		  }
	} catch (Exception e) {
	Assert.fail("ERROR: OCCUR DURING FIELD VALIDATION");
	}
  }
   protected static void windowsHandle(WebElement element){
   try {
	String windowHandle = driver.getWindowHandle();
	   Set<String> windowHandles = driver.getWindowHandles();
	   List<String> allWindow = new ArrayList<String>(windowHandles);
	   String currentWindow = allWindow.get(1);
	   driver.switchTo().window(currentWindow);
} catch (Exception e) {
   Assert.fail("ERROR: OCCUR DURING HANDLING WINDOWS CHECK THE WINDOWS");
}
   
   
}
   protected static String getText(WebElement element) {
	   try {
		String text = element.getText();
		   System.out.println(text);
		   return text;
		   
	} catch (Exception e) {
		
		Assert.fail("ERROR: OCCUR DURING GET TEXT");
	}
	return null ;
	
   }
                 
   protected static void action_Class(WebElement element,String type) {
	   try {
		Actions a = new Actions(driver);
		   if(type.equalsIgnoreCase("moveToElement")) {
			   a.moveToElement(element).perform();
			   }
		   else if(type.equalsIgnoreCase("rightClick")) {
			   a.contextClick().perform();
		   }
		   else if (type.equalsIgnoreCase("doubleClick")) {
			   a.doubleClick().perform();
		   }
	} catch (Exception e) {
		Assert.fail("ERROR:OCCUR IN PERFORM ACTION CHECK ACTION CLASS");
	}
	    }
   protected static void frames(String type,String value) {
	   try {
		if(type.equalsIgnoreCase("index")) {
			   driver.switchTo().frame(Integer.parseInt(value));
		   }
		   else if(type.equalsIgnoreCase("variable")) {
			   driver.switchTo().frame(value);
			     }
		   else if (type.equalsIgnoreCase("webelement")) {
			   driver.switchTo().frame(value);
		   }
		   
		   driver.switchTo().defaultContent();
	} catch (NumberFormatException e) {
		Assert.fail("ERROR: OCCUR DURING SWITCH TO FRAME CHECK FRAMES");
	}
	   
   }
   protected static void terminateBrowser() {
	   try {
		driver.quit();
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING TERMINATE BROWSER");
	}
   }
   
   protected static void staticWait(int value) throws InterruptedException {
	   try {
		Thread.sleep(value);
	} catch (InterruptedException e) {
		Assert.fail("ERROR: OCCUR DURING STATIC WAIT");
	}
   }
   
   protected static void closeWindow() {
	   try {
		driver.close();
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING CLOSE THE CURRENT WINDOW  ");
	}
	   
   }
   protected static void radioButton(WebElement element) {
	   try {
		element.click();
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING CLICK RADIO BUTTON");
	}
	   
   }
   protected static void hardAssert(String getText,String expText) {
	   try {
		//String actualText = string.getText();
		   assertEquals(getText,expText);
	} catch (Exception e) {
		Assert.fail("ERROR: OCCUR DURING HARD ASSERT");
	}
	   
   }
   
   public static void extentReportStart(String locations) {
	   try {
		extentReport = new ExtentReports();
		   file= new File(locations);
		   ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		   extentReport.attachReporter(sparkReporter);
		   extentReport.setSystemInfo("OS",System.getProperty("os.name"));
		   extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	} catch (Exception e) {
	Assert.fail("ERROR: OCCUR DURING EXTENT REPORT START");
	}
   }
   
   public static void extentReportTearDown(String location) throws IOException {
	   extentReport.flush();
	   file= new File(location);
	   Desktop.getDesktop().browse((file).toURI());
   }
   
	public String takeScreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File scrfile = screenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File("Screenshot\\.png" + "_" + timeStamp + ".png");
		FileHandler.copy(scrfile, destfile);
		return destfile.getAbsolutePath();
	}
	protected static  void validation(WebElement element, String expectedText) {
		String actualText = element.getText();
		if(actualText.equalsIgnoreCase(expectedText)) {
			System.out.println(actualText+"=" +"validation pass");
		}
		else {
			System.out.println("Validation fail");
		}
		
		}
	protected static void  clear(WebElement element) {
		element.clear();
		}
	protected static void scrollUp(int range) {
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,-"+range+");");
		
	}
	
	protected static void scrollDown(int range) {
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,"+range+");");
	}
	
	protected static void fileUpload(String location) {
		
			try {
				StringSelection selection = new StringSelection(location);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
   Robot robot = new Robot();
   robot.delay(2000);
  
   robot.keyPress(KeyEvent.VK_CONTROL);
robot.keyPress(KeyEvent.VK_V);
robot.keyPress(KeyEvent.VK_ENTER);
			} catch (HeadlessException e) {
				
			} catch (AWTException e) {
				Assert.fail("ERROR:OCCUR DURING KEYBOARD ACTION");
			
			}
   
   
		}
	}
	


