import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScrollPageJavaScript {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 800));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(" https://fakestore.testelka.pl/product/fuerteventura-sotavento/");

    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void ProductBannerByScrollingPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        WebElement productBanner = driver.findElement(By.cssSelector("section[class='storefront-sticky-add-to-cart storefront-sticky-add-to-cart--slideInDown']"));
        Assertions.assertNotNull(productBanner);
    }

    @Test
    public void ProductBannerByToAnotherElementView(){
        WebElement relatedProducts = driver.findElement(By.cssSelector("section[class='related products']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()",relatedProducts);
        List<WebElement> productBanner = driver.findElements(By.cssSelector("section[class='storefront-sticky-add-to-cart storefront-sticky-add-to-cart--slideInDown']"));
        Assertions.assertEquals(1, productBanner.size());
//        WebElement productBanner = driver.findElement(By.cssSelector("section[class='storefront-sticky-add-to-cart storefront-sticky-add-to-cart--slideInDown']"));
//        Assertions.assertNotNull(productBanner);
    }

}
