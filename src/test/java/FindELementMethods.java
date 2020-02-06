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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FindELementMethods {
    WebDriver driver;
    WebDriverWait wait;


    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 1200));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(" https://fakestore.testelka.pl/metody-na-elementach/");
        driver.findElement(By.cssSelector("a.woocommerce-store-notice__dismiss-link")).click();
    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void getInfoIfHomeButtonIsDisable() {
        String yellowColor = "rgba(245, 233, 101, 1)";
        WebElement homeButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement sailingButton = driver.findElement(By.cssSelector("a[name='sailing']"));
        WebElement climbingButton = driver.findElement(By.cssSelector("a[name='climbing']"));
        WebElement windsurfingButton = driver.findElement(By.cssSelector("a[name='windsurfing']"));
        WebElement yogaButton = driver.findElement(By.cssSelector("a[name='yoga']"));
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement notSelectedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement selectedRadio = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notSelectedRadio = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));

        List<WebElement> butttonList = driver.findElements(By.cssSelector("[class='button']"));

        Assertions.assertAll("Metody na elementach - test",
                () -> assertFalse(homeButton.isEnabled(), "Home button is not displayed"),
                () -> assertFalse(sailingButton.isDisplayed(), "Sailling e button is not hide"),
                () -> assertEquals(climbingButton.getCssValue("background-color"), yellowColor, "actual color: " + climbingButton.getCssValue("background-color")),
                () -> assertEquals(windsurfingButton.getCssValue("background-color"), yellowColor, "actual color: " + windsurfingButton.getCssValue("background-color")),
                () -> assertEquals(yogaButton.getCssValue("background-color"), yellowColor, "actual color: " + yogaButton.getCssValue("background-color")),
                () -> assertTrue(selectedCheckbox.isSelected(),"checkbox is not selected"),
                () -> assertFalse(notSelectedCheckbox.isSelected(),"checkbox is selected"),
                () -> assertTrue(selectedRadio.isSelected(),"radio button is not selected"),
                () -> assertFalse(notSelectedRadio.isSelected(),"radio button is selected"),
                () -> assertThatButtonsAreTagA(butttonList)
        );

//        for (WebElement button: butttonList) {
//            Assertions.assertTrue(button.getTagName().equals("a"));
//        }

    }

    public void assertThatButtonsAreTagA(List<WebElement> buttons){
        for (WebElement button: buttons) {
            Assertions.assertTrue(button.getTagName().equals("a"),"Element is not tag A");
        }
    }



}
