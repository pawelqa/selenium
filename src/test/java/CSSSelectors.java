import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class CSSSelectors {
    ChromeDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public void getElementsByCssLocators(){
        driver.findElement(By.cssSelector("input#username"));
        driver.findElement(By.cssSelector("input#reg_email"));
        driver.findElement(By.cssSelector("[name='password'][id='password'][autocomplete='current-password']"));
        driver.findElement(By.cssSelector("button[name='login']"));
    }

}
