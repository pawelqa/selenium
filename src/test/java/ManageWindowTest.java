import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.text.Position;

public class ManageWindowTest {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(854,480));
        driver.manage().window().setPosition(new Point(445,30));
        driver.navigate().to("https://amazon.com");
    }

    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public void getWindowSizeAndPosition(){
        Point position = driver.manage().window().getPosition();
        Dimension size = driver.manage().window().getSize();
        Assertions.assertEquals(position,driver.manage().window().getPosition());
        Assertions.assertEquals(size,driver.manage().window().getSize());

        driver.manage().window().maximize();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.manage().window().fullscreen();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
