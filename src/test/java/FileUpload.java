import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;


/**
 * Created by user on 7/19/17.
 */
public class FileUpload {
    WebDriver driver;
    Robot r = new Robot();
    String FileName = "0-02-05-5895786b570f7f4b89f37944f23038dd1fe8b155de938d7041c4a9babf241c9a_full.mp4";

    public FileUpload() throws AWTException {
    }

    void mouseClick(Integer x, Integer y) {
        r.mouseMove(x,y);
        r.mousePress(InputEvent.BUTTON1_MASK );
        r.mouseRelease(InputEvent.BUTTON1_MASK );
    }

    @BeforeTest
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/user/IdeaProjects/AutotestTestNG/ChromeDriver/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.box.com/");
    }
    @Test
    void Login() {

        driver.findElement(By.cssSelector("a.user-menu_log-in")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("rvalek@intersog.de");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("welcome2hillel");
        driver.findElement(By.cssSelector("button[type='submit']")).submit();
    }
    @Test (dependsOnMethods = "Login")
    void uploadFile() throws InterruptedException, AWTException {
        driver.findElement(By.cssSelector("a.upload-menu-btn")).click();
        driver.findElement(By.cssSelector("li.upload-file-handler-target")).click();
        if ( OS.isFamilyMac() ) {
            mouseClick(350, 330);
            mouseClick(570, 140);
            mouseClick(960, 450);
        }
        Thread.sleep(10000);

        System.out.println("i found 0 element from all elements: " + driver.findElements(By.cssSelector("a" +
                ".item-name-link")).get(0)
                .getAttribute("innerHTML"));

        WebElement BoxContent = driver.findElement(By.cssSelector("a.item-name-link"));
        String Txt = BoxContent.getText();
        System.out.println("i found such text: " + Txt);
        System.out.print("i found innerHTML:  " + BoxContent.getAttribute("innerHTML"));
        Assert.assertTrue(FileName.equals(Txt));
    }

    @AfterTest
    void quitDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }





}
