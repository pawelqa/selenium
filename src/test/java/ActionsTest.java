import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ActionsTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
        driver.manage().window().setSize(new Dimension(1295, 900));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();

    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void contextMenuTest() {
        WebElement menu = driver.findElement(By.cssSelector("a#menu-link"));
        WebElement basket = driver.findElement(By.cssSelector("a[href='/koszyk/']"));
        actions.contextClick(menu).click(basket).build().perform();
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://fakestore.testelka.pl/koszyk/", url
                , "Actual url is not " + url);
    }

    @Test
    public void doubleClick() {
        WebElement square = driver.findElement(By.cssSelector("div#double-click"));
        actions.doubleClick(square).build().perform();
        String squareColor = square.getCssValue("background-color");
        System.out.println(squareColor);
        Assertions.assertEquals("rgba(245, 93, 122, 1)", squareColor);
    }

    @Test
    public void insertCharacters() {
        String enteredText = "Lalalala";
        WebElement input = driver.findElement(By.cssSelector("input#input"));
        actions.sendKeys(input, enteredText).build().perform();
        driver.findElement(By.cssSelector("button[onclick='zatwierdzTekst()']")).click();
        WebElement displayedText = driver.findElement(By.cssSelector("p#output"));
        Assertions.assertEquals("Wprowadzony tekst: " + enteredText, displayedText.getText());
    }

    @Test
    public void actionsWithCTRL() {
        WebElement button1 = driver.findElement(By.cssSelector("li[name='1']"));
        WebElement button2 = driver.findElement(By.cssSelector("li[name='2']"));
        WebElement button5 = driver.findElement(By.cssSelector("li[name='5']"));
        actions.keyDown(Keys.CONTROL).click(button1).click(button2).click(button5).build().perform();

        Assertions.assertAll("Selected buttons ",
                () -> Assertions.assertEquals("rgba(243, 152, 20, 1)", button1.getCssValue("background-color")),
                () -> Assertions.assertEquals("rgba(243, 152, 20, 1)", button2.getCssValue("background-color")),
                () -> Assertions.assertEquals("rgba(243, 152, 20, 1)", button5.getCssValue("background-color"))
        );
    }
}
