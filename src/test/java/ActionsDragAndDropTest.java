import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ActionsDragAndDropTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    WebElement elementToDrag;
    WebElement elementDroppable;


    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
        driver.manage().window().setSize(new Dimension(1295, 900));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();
        elementToDrag = driver.findElement(By.cssSelector("div#draggable"));
        elementDroppable = driver.findElement(By.cssSelector("div#droppable"));

        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",elementToDrag);

    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void simpleDragAndDropTest() {
        actions.dragAndDrop(elementToDrag, elementDroppable).build().perform();
        Assertions.assertEquals("Dropped!", elementDroppable.getText());
    }

    @Test
    public void DragAndDropSecondTest() {
        actions.clickAndHold(elementToDrag).moveToElement(elementDroppable, 50, 50).release().build().perform();
        Assertions.assertEquals("Dropped!", elementDroppable.getText());
    }

    @Test
    public void DragAndDropThirdTest() {
        // actions.dragAndDropBy(elementToDrag,160,40).build().perform();
        actions.clickAndHold(elementToDrag).moveByOffset(160, 40).release().build().perform();
        Assertions.assertEquals("Dropped!", elementDroppable.getText());
    }
}
