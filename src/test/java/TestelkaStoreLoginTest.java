import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestelkaStoreLoginTest {
    WebDriver driver;

    String expectedLoginWithFakePasswordError = "BŁĄD: Dla adresu email mojekontotestowe38@gmail.com podano nieprawidłowe hasło. Nie pamiętasz hasła?";
    String expectedFakeLoginAndFakePasswordMessage = "Nieznana użytkownik. Proszę sprawdzić ponownie lub spróbować swój email.";
    String correctLogin = "mojekontotestowe38@gmail.com";
    String correctPassword = "fakehaslo123!";
    String fakeLogin = "fakelogin";
    String fakePassword = "fakepassword";
    String expectedLogin = "mojekontotestowe38";

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 700));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void properLoginAndPasswordTest() {
        enterCredentialsAndSubmit(correctLogin, correctPassword);
        Assertions.assertEquals(expectedLogin, getUsernameAfterLogin()
                , "### " + getUsernameAfterLogin() + " is not correct user, there should be " + expectedLogin + " ###");
    }

    @Test
    public void fakeLoginAndFakePasswordTest() {
        enterCredentialsAndSubmit(fakeLogin, fakePassword);
        Assertions.assertEquals(expectedFakeLoginAndFakePasswordMessage, getFakeLoginAndFakePasswordWarning()
                , " ### Expected error message "
                        + expectedFakeLoginAndFakePasswordMessage
                        + " , actual message: "
                        + getFakeLoginAndFakePasswordWarning());
    }

    @Test
    public void loginWithFakePasswordTest() {
        enterCredentialsAndSubmit(correctLogin, fakePassword);
        Assertions.assertEquals(expectedLoginWithFakePasswordError, getActualLoginWithFakePasswordWarning()
                , " ### Expected error message "
                        + expectedLoginWithFakePasswordError
                        + " , actual message: "
                        + getActualLoginWithFakePasswordWarning());
    }

    private String getActualLoginWithFakePasswordWarning() {
        return driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
    }

    private String getUsernameAfterLogin() {
        return driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content'] strong")).getText();
    }

    private String getFakeLoginAndFakePasswordWarning() {
        return driver.findElement(By.cssSelector("ul[class='woocommerce-error'] li")).getText();
    }

    public void enterCredentialsAndSubmit(String login, String password) {
        driver.findElement(By.cssSelector("input#username")).sendKeys(login);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

}
