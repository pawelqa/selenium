import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise1Test {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }


    @Test
    public void goToWikipediaAndNasaPage() {
        driver.navigate().to("https://pl.wikipedia.org/");
        driver.navigate().to("https://www.nasa.gov/");
        driver.navigate().back();
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitle, driver.getTitle());
        driver.navigate().forward();
        String nasaTitle = "NASA";
        Assertions.assertEquals(nasaTitle, driver.getTitle());
    }
}
