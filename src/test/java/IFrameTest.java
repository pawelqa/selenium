import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IFrameTest {
    WebDriver driver;
    WebDriverWait wait;
    String homeURL = "https://fakestore.testelka.pl/";


    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 1200));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(" https://fakestore.testelka.pl/cwiczenia-z-ramek/");
        driver.findElement(By.cssSelector("a.woocommerce-store-notice__dismiss-link")).click();
    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void getIFrameElements(){
        WebElement mainFrame = driver.findElement(By.cssSelector("iframe[id='main-frame']"));
        driver.switchTo().frame(mainFrame);
        WebElement homeButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        Assertions.assertFalse(homeButton.isEnabled(),"Button is active, but should be inactive");

        WebElement imageFrame = driver.findElement(By.cssSelector("iframe[name='image']"));
        driver.switchTo().frame(imageFrame);
        //String imageVacationURL = driver.findElement(By.cssSelector("img[alt='Wakacje']")).getAttribute("src");
        String imageVacationURL = driver.findElement(By.xpath(".//img[@alt='Wakacje']/..")).getAttribute("href");
        Assertions.assertEquals(homeURL,imageVacationURL, "Wron image url, actual image url is: "+imageVacationURL);

        WebElement bottomFrame = driver.findElement(By.cssSelector("[src='https://fakestore.testelka.pl/ramka-button-do-strony-glownej/']"));
        driver.switchTo().frame(bottomFrame);
        WebElement bottomMainPageButton = driver.findElement(By.cssSelector("[class='button']"));
        Assertions.assertTrue(bottomMainPageButton.isDisplayed());

        //[src='https://fakestore.testelka.pl/ramka-button-do-strony-glownej/']

        // [class='button']
    }

}
