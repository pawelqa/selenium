import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestelkaStoreLoginTest {
    WebDriver driver;
    WebElement usernameWebELement;
    WebElement passwordWebElement;
    WebElement loginButtonWebElement;
    WebElement loginFormWebElement;
    String warningWebElement;
    String usernameAfterLogin;
    String correctLogin = "paweljuszczak@gmail.com";
    String correctPassword = "fakehaslo123!";
    String fakeLogin = "fakelogin";
    String fakePassword = "fakepassword";

    @BeforeEach
    public void driverSetup()  {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024,720));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");

        usernameWebELement = driver.findElement(By.cssSelector("input#username"));
        passwordWebElement = driver.findElement(By.cssSelector("input#password"));
        loginButtonWebElement = driver.findElement(By.cssSelector("button[name='login']"));
        loginFormWebElement = driver.findElement(By.cssSelector("form[class='woocommerce-form woocommerce-form-login login']"));

    }

    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }


    @Test
    public void properLoginAndPasswordTest() {
            usernameWebELement.sendKeys(correctLogin);
            passwordWebElement.sendKeys(correctPassword);
            loginButtonWebElement.click();
            usernameAfterLogin = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content'] strong")).getText() ;
            String expectedLogin = "paweljuszczak";
            Assertions.assertEquals(expectedLogin,usernameAfterLogin);

    }

    @Test
    public void fakeLoginAndfakePasswordTest(){
        usernameWebELement.sendKeys(fakeLogin);
        passwordWebElement.sendKeys(fakePassword);
        loginButtonWebElement.click();
        warningWebElement = driver.findElement(By.cssSelector("ul[class='woocommerce-error'] li")).getText();
        String expectedErrorMessage = "Nieznana użytkownik";
        Assertions.assertTrue(warningWebElement.contains(expectedErrorMessage));
    }

//    @Test
//    public void loginWithoutAnyInputsTest(){
//        loginButtonWebElement.click();
//        warningWebElement = driver.findElement(By.cssSelector("ul[class='woocommerce-error'] strong")).getText();
//        String expectedErrorMessage = "Bł";
//        Assertions.assertTrue(warningWebElement.contains(expectedErrorMessage));
//    }
}
