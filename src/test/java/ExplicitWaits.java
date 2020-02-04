import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExplicitWaits {
    WebDriver driver;
    WebDriverWait wait;

    String couponCode10percent = "10procent";
    String emptyCouponCode = "";
    String expectedBasketAlertAfterProperCouponCodeUse = "Kupon został pomyślnie użyty.";
    String expectedBasketAlertAfterEmptyCouponCodeUse = "Proszę wpisać kod kuponu.";
    String expectedBasketAlertAfterRemoveCoupon = "Kupon został usunięty.";
    String expectedAddProductToBasketAlert = "został dodany do koszyka";

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 1200));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/egipt-el-gouna/");
    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void addProductToBasket() {
        driver.findElement(By.cssSelector("button[class='single_add_to_cart_button button alt']")).click();
        String actualAddProductToBasketAlert = getCouponActivateMessage();
        Assertions.assertTrue(actualAddProductToBasketAlert.contains(expectedAddProductToBasketAlert)
                , "Product was not added to the Basket");
    }

    @Test
    public void alertMessageAfterProperCouponCodeUse() {
        activateCouponCode(couponCode10percent);
        String actualBasketAlert = getCouponActivateMessage();
        Assertions.assertEquals(expectedBasketAlertAfterProperCouponCodeUse, actualBasketAlert
                , "Coupon Code is not added, message : " + actualBasketAlert);
    }

    @Test
    public void alertMessageAfterEmptyCouponCodeUse() {
        activateCouponCode("");
        String actualBasketAlert = getCouponActivateMessage();
        Assertions.assertEquals(expectedBasketAlertAfterEmptyCouponCodeUse, actualBasketAlert
                , "Message : " + actualBasketAlert);
    }


//    @Test
//    public void alertMessageAfterRemoveCouponCodeUse() {
//        activateCouponCode(couponCode10percent);
//        waitForProcessEnding();
//        //driver.findElement(By.linkText("[Usuń]")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("[Usuń]"))).click();
//        waitForProcessEnding();
//        String actualBasketAlert = getCouponActivateMessage();
//        Assertions.assertEquals(expectedBasketAlertAfterRemoveCoupon, actualBasketAlert
//                , "Coupon Code is not added, message : " + actualBasketAlert);
//        //By.linkText("Żeglarstwo")
//    }



    public void activateCouponCode(String coupon) {
        driver.findElement(By.cssSelector("button[class='single_add_to_cart_button button alt']")).click();
        driver.findElement(By.cssSelector("a[class='cart-contents']")).click();
        driver.findElement(By.cssSelector("input#coupon_code")).clear();
        driver.findElement(By.cssSelector("input#coupon_code")).sendKeys(coupon);
        driver.findElement(By.cssSelector("button[name='apply_coupon']")).click();
    }

    public void waitForProcessEnding() {
        By blockedUI = By.cssSelector("div.blockUI");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(blockedUI, 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(blockedUI, 0));
    }


    public String getCouponActivateMessage() {
        By alertMessage = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();
    }
}