import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DropDownTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 900));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();

    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void productOrderByAsc() {
        driver.findElement(By.cssSelector("a[href*='windsurfing']")).click();
        WebElement sortWebElement = driver.findElements(By.cssSelector("select[name=orderby]")).get(0);
        Select sortDropDownList = new Select(sortWebElement);
        sortDropDownList.selectByValue("price");
        WebElement productPrice = driver.findElements(By.cssSelector("span.price")).get(0);
        String productPriceText = productPrice.getText();
        //System.out.println(productPriceText);
        Assertions.assertEquals("2 900,00 zł", productPriceText
                , "Selected product is not with the lowest price");
    }

    @Test
    public void productOrderByDesc() {
        driver.findElement(By.cssSelector("a[href*='windsurfing']")).click();
        WebElement sortWebElement = driver.findElements(By.cssSelector("select[name=orderby]")).get(0);
        Select sortDropDownList = new Select(sortWebElement);
        sortDropDownList.selectByValue("price-desc");
        WebElement productPrice = driver.findElements(By.cssSelector("span.price")).get(0);
        String productPriceText = productPrice.getText();
        // System.out.println(productPriceText);
        Assertions.assertEquals("5 399,00 zł", productPriceText
                , "Selected product is not with the highest price");
    }
}
