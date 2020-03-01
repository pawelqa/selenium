import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebStorageTest {
    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 900));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/fuerteventura-sotavento/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();


    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void webStorageTest1(){
        int localStorageSize = driver.getLocalStorage().size();
        long sizeLocalJS = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length;");

        int sessionStorageSize = driver.getSessionStorage().size();
        long sessionSizeJS = (long) ((JavascriptExecutor) driver).executeScript("return sessionStorage.length;");
    }

    @Test
    public void cartCreatedTest(){
        driver.findElement(By.cssSelector("button[name='add-to-cart']")).click();

        // wait until SessionStorage will have 3 elements
        wait.until(d -> driver.getSessionStorage().size()==3);
        Assertions.assertTrue(driver.getSessionStorage().keySet().contains("wc_cart_created"),
                "wc_cart_created was not added to Session Storage");
    }
}
