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

public class AlertsTest {
    WebDriver driver;
    WebDriverWait wait;

    String expectedCancelAlertText = "Wybrana opcja to Cancel!";
    String ExpectedAcceptAlertText = "Wybrana opcja to OK!";

    String expectedCancelPromptText = "Użytkownik anulował akcję.";
    String name = "Pawel";
    String ExpectedAcceptPromptText = "Cześć " + name + "! Jak leci?";


    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().setSize(new Dimension(1295, 1200));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(" https://www.w3schools.com/code/tryit.asp?filename=FZ9IOGP56P0O");
        driver.findElement(By.cssSelector("button[class='w3-button w3-bar-item w3-green w3-hover-white w3-hover-text-green']")).click();
        driver.switchTo().frame("iframeResult");
    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void cancelConfirmAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='confirmFunction()']"))).click();
        driver.switchTo().alert().dismiss();
        String ActualCancelAlertText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#demo"))).getText();
        Assertions.assertEquals(ActualCancelAlertText, expectedCancelAlertText
                , "There is message: " + ActualCancelAlertText + " but should be: " + expectedCancelAlertText);
    }

    @Test
    public void acceptConfirmAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='confirmFunction()']"))).click();
        driver.switchTo().alert().accept();
        String ActualAcceptAlertText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#demo"))).getText();
        Assertions.assertEquals(ActualAcceptAlertText, ExpectedAcceptAlertText
                , "Alert message should be: " + ExpectedAcceptAlertText + " but there is: " + ActualAcceptAlertText);
    }

    @Test
    public void cancelConfirmPrompt() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='promptFunction()']"))).click();
        driver.switchTo().alert().dismiss();
        String ActualCancelPromptText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#prompt-demo"))).getText();
        Assertions.assertEquals(ActualCancelPromptText, expectedCancelPromptText
                , "There is message: " + ActualCancelPromptText + " but should be: " + expectedCancelPromptText);
    }

    @Test
    public void acceptConfirmPrompt() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='promptFunction()']"))).click();
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
        String ActualAcceptPromptText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#prompt-demo"))).getText();
        Assertions.assertEquals(ActualAcceptPromptText, ExpectedAcceptPromptText
                , "There is message: " + ActualAcceptPromptText + " but should be: " + ExpectedAcceptPromptText);
    }

}
