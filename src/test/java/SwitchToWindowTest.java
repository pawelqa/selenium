import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SwitchToWindowTest {
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
    public void removeProductFromWishList(){
        driver.findElement(By.cssSelector("li#menu-item-198")).click();
        driver.findElement(By.linkText("Windsurfing")).click();
        driver.findElement(By.cssSelector("a[href=\"https://fakestore.testelka.pl/product/egipt-el-gouna/\"]")).click();
        driver.findElement(By.xpath(".//div[@class=\"yith-wcwl-add-button\"]//span")).click();
        driver.findElement(By.cssSelector("li#menu-item-248")).click();


        Set<String> windows = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        windows.remove(parentWindow);
        String secondWindow = windows.iterator().next();
        driver.switchTo().window(secondWindow);

       // WebElement productName = driver.findElement(By.xpath(".//table[@class='shop_table cart wishlist_table wishlist_view traditional responsive']//tr[@id='yith-wcwl-row-386']/td[@class='product-name']/a"));

        By removeFromWishList = By.cssSelector("a[class='remove remove_from_wishlist']");
        driver.findElement(removeFromWishList).click();
        By emptyWishList = By.cssSelector("td.wishlist-empty");
        wait.until(ExpectedConditions.presenceOfElementLocated(emptyWishList));

        //oczekuje aż poniższy wait nie rzuci wyjątku
        Assertions.assertDoesNotThrow(()->wait.until(ExpectedConditions.presenceOfElementLocated(emptyWishList))
                ,"Wishlist is not empty");
    }
}
