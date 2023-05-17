package com.browserstack;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RemoteDriverTest {

    /*
    Initialize Hub using below command
    java -jar lib/selenium-server-standalone-3.9.0.jar -role hub

    Initialize Node using below command
    java -Dwebdriver.chrome.driver=driver/chromedriver -jar lib/selenium-server-standalone-3.9.0.jar -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome, maxInstances=1"
    */

    // mvn -Dtest=com.browserstack.RemoteDriverTest test-compile surefire:test
    static WebDriver driver = null;

	@BeforeTest
    public void setUp() throws MalformedURLException {
        String URL = "http://192.168.29.121:4444/wd/hub";
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);
        driver = new RemoteWebDriver(new URL(URL), options);
    }

	@AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test(priority=1)
    public void validBSLogin() {
    	driver.get("https://www.browserstack.com/users/sign_in");
    	
//        driver.findElement(By.xpath("//*[@id=\"accept-cookie-notification\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"primary-menu\"]/li[5]/a")).click();
        driver.findElement(By.id("user_email_login")).sendKeys(username);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.id("user_submit")).click();

    }
    
    
    @Test(priority=2)
    public void validOpenLiveDashboard() {
    	driver.get("https://live.browserstack.com/dashboard");
    	
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        System.out.println("Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }
    
    @Test(priority=3)
    public void validOpenBrowserFromLive() {
    	driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).click();

    }
    
    @Test(priority=4)
    public void invalidBSLogin() {
    	driver.close();
    	driver.get("https://www.browserstack.com/users/sign_in");
    	
        driver.findElement(By.xpath("//*[@id=\"accept-cookie-notification\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"primary-menu\"]/li[5]/a")).click();
        driver.findElement(By.id("user_email_login")).sendKeys(username);
        driver.findElement(By.id("user_password")).sendKeys("1234");
        driver.findElement(By.id("user_submit")).click();

    }
    
    @Test(priority=5)
    public void invalidOpenLiveDashboard() {
        driver.findElement(By.xpath(" /html/body/div[1]/header/div/div/nav/ul[1]/li[1]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/header/div/div/nav/ul[1]/li[1]/div[2]/div/div[1]/ul[1]/li[2]/a/div[1]")).click();
        String expectedTitle = "Rashboard";
        String actualTitle = driver.getTitle();
        System.out.println("Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }
    
    
    @Test(priority=6)
    public void invalidOpenBrowserFromLive() {
    	String actualTitle = driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).getText();
    	driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).click();
 
        String expectedTitle = "113";
        System.out.println("Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }
    
}
