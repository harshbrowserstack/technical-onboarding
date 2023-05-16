package com.browserstack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CloudDriverTest {

    static String username = "agarwal.harshnu@gmail.com";
	static String password = "Harsh@1202";
	
    
    static WebDriver driver = null;

	@BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");
        browserstackOptions.put("seleniumVersion", "4.0.0");
        caps.setCapability("bstack:options", browserstackOptions);
        driver = new RemoteWebDriver(new URL("https://" + System.getProperty("username") + ":" + System.getProperty("accessKey") + "@hub.browserstack.com/wd/hub"), caps);
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
