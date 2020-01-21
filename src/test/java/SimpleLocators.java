import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleLocators {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public void getSampleElemnts(){
        driver.findElement(By.id("woocommerce-product-search-field-0"));
        driver.findElement(By.className("search-field"));
        driver.findElement(By.name("username"));
        driver.findElement(By.id("password"));
        driver.findElement(By.name("login"));
        driver.findElement(By.id("rememberme"));
        driver.findElement(By.linkText("Nie pamiętasz hasła?"));
        driver.findElement(By.linkText("Żeglarstwo"));

    }
}
